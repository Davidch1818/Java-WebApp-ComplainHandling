# comphand + comphandservice

Two independently deployable Maven WAR projects for a Java web app on
Apache Tomcat, using ZK, Spring, Hibernate, and PostgreSQL.

- **comphand** (frontend) — ZK (MVVM) pages/ViewModels handling user
  input and output.
- **comphandservice** (backend) — the Bo (business logic) and Dao
  (data access) layers, backed by Hibernate + PostgreSQL.

They are two separate WAR deployments that talk to each other over
HTTP using **Spring Remoting (HttpInvoker)** — not hand-written REST
controllers or an HTTP client. Each project has exactly one Spring
config file, `applicationContext.xml`, that wires everything.

## Why there's no REST controller or REST client file

With HttpInvoker, a plain Java interface becomes a network call purely
through XML configuration:

- **comphandservice** implements a Bo interface (e.g. `UserBo` →
  `UserBoImpl`) and exports it with an `HttpInvokerServiceExporter`
  bean in its `applicationContext.xml`.
- **comphand** never implements that interface. Its
  `applicationContext.xml` declares an `HttpInvokerProxyFactoryBean`
  for the same interface; Spring generates a dynamic proxy at runtime
  that serializes each method call, sends it over HTTP, and
  deserializes the result. ViewModels call `userBo.listUsers()` as if
  it were a local object.

That's why comphand's file list only has a `Bo` interface (no impl)
while comphandservice has both `Bo` and `BoImpl` — the "impl" on the
frontend side is the proxy Spring builds for you.

## File layout

**comphand**
```
src/main/java/com/comphand/
  bo/UserBo.java, ProductBo.java        # interfaces only (see above)
  model/User.java, Product.java         # plain Serializable POJOs
  viewmodel/UserViewModel.java, ProductViewModel.java
src/main/resources/application.properties
src/main/webapp/
  WEB-INF/applicationContext.xml        # HttpInvokerProxyFactoryBean per Bo
  WEB-INF/web.xml
  WEB-INF/zk.xml
  index.zul, products.zul
```

**comphandservice**
```
src/main/java/com/comphand/
  bo/UserBo.java, ProductBo.java        # identical copies of comphand's
  bo/impl/UserBoImpl.java, ProductBoImpl.java
  dao/UserDao.java, ProductDao.java
  dao/impl/UserDaoImpl.java, ProductDaoImpl.java
  model/User.java, Product.java         # identical copies of comphand's
src/main/resources/
  application.properties
  com/comphand/model/User.hbm.xml, Product.hbm.xml   # Hibernate mapping
src/main/webapp/
  WEB-INF/applicationContext.xml        # DataSource, SessionFactory, tx,
                                         # Dao/Bo beans, HttpInvoker exporters
  WEB-INF/web.xml
```

## Important: the shared classes must stay identical

`bo/UserBo.java`, `bo/ProductBo.java`, `model/User.java`, and
`model/Product.java` exist as **byte-for-byte identical files in both
projects** (same package, same class name, same fields). This isn't
just tidiness — Java's HttpInvoker relies on Java serialization, so:

- The `Bo` interface must match exactly for the dynamic proxy on
  comphand to implement the same contract comphandservice exposes.
- The `Model` classes must match exactly (including
  `serialVersionUID`) or comphand will fail to deserialize objects
  that comphandservice sends back.

If you change a method signature or a field, copy the change to both
projects' copies in the same commit. There's no shared JAR enforcing
this at compile time — see "Going further" below for that option.

## 1. Create the database

```sql
CREATE DATABASE zkdb;
```

`comphandservice/src/main/resources/application.properties` has the
connection details; `hibernate.hbm2ddl.auto=update` (set in
`applicationContext.xml`) creates the `app_user` and `product` tables
automatically on first start. Update the URL, username, and password
to match your instance.

## 2. Build both projects

```bash
cd comphandservice && mvn clean package && cd ..
cd comphand && mvn clean package && cd ..
```

Each produces a WAR in its `target/` folder:
`comphandservice/target/comphandservice.war` and
`comphand/target/comphand.war`.

## 3. Deploy to Tomcat

```
webapps/
  comphandservice.war   -> http://localhost:8080/comphandservice
  comphand.war          -> http://localhost:8080/comphand
```

If comphandservice is deployed somewhere other than localhost, update
`comphand/src/main/resources/application.properties`
(`comphandservice.base.url`) to match.

## 4. Try it

Open `http://localhost:8080/comphand/index.zul` — it lists users and
lets you add/delete them, with a link to `products.zul`. Every action
flows:

```
ZUL page -> UserViewModel (@WireVariable UserBo)
   -> HttpInvokerProxyFactoryBean proxy -> HTTP (Java serialization)
   -> HttpInvokerServiceExporter -> UserBoImpl (@Transactional)
   -> UserDaoImpl -> Hibernate -> PostgreSQL
```

## Two worked examples: User and Product

Both slices are fully wired end to end, so you can compare them side
by side as a pattern to copy:

| Layer | User | Product |
|---|---|---|
| comphand Bo | `bo/UserBo` | `bo/ProductBo` |
| comphand ViewModel / page | `viewmodel/UserViewModel`, `index.zul` | `viewmodel/ProductViewModel`, `products.zul` |
| comphandservice Bo/BoImpl | `bo/UserBo`, `bo/impl/UserBoImpl` | `bo/ProductBo`, `bo/impl/ProductBoImpl` |
| comphandservice Dao/DaoImpl | `dao/UserDao`, `dao/impl/UserDaoImpl` | `dao/ProductDao`, `dao/impl/ProductDaoImpl` |
| Model + mapping | `model/User`, `User.hbm.xml` | `model/Product`, `Product.hbm.xml` |

## Adding a third resource

Follow the same shape as User/Product:

1. **comphandservice**: add `model/Thing.java` (plain POJO,
   Serializable) + `Thing.hbm.xml`, `dao/ThingDao(Impl)`,
   `bo/ThingBo(Impl)`, then register `thingBo`/`/thingBo` beans in
   `applicationContext.xml` (mirroring the `userBo`/`/userBo` pair)
   and add the `.hbm.xml` to `sessionFactory`'s `mappingResources`.
2. **comphand**: copy `model/Thing.java` and `bo/ThingBo.java`
   byte-for-byte from comphandservice, add a `thingBo`
   `HttpInvokerProxyFactoryBean` bean to `applicationContext.xml`, then
   add `viewmodel/ThingViewModel.java` + `things.zul`.

## Going further

- **Shared module.** Pull `bo/*.java` and `model/*.java` into a third,
  small Maven module that both projects depend on, so the "must stay
  identical" rule above is enforced by the compiler instead of by
  convention.
- **Connection pool / Spring versions.** `hibernate-c3p0` and the
  `spring.version`/`zk.version`/`zkspring-core` version properties are
  pinned to versions that were current when this template was written
  — check each project's Maven repo for newer patch releases before
  you build, especially `zkspring-core` (see the comment above it in
  `comphand/pom.xml`).
