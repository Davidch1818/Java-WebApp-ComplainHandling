package com.comphand.model;

import java.io.Serializable;

/**
 * Plain model object for User -- carries data between layers on the
 * backend AND travels over the wire as the Spring HttpInvoker
 * request/response payload (Java serialization), so it must implement
 * Serializable with a fixed serialVersionUID.
 *
 * IMPORTANT: this exact class (same package, same fields) exists in
 * BOTH the comphand and comphandservice projects. Keep both copies
 * identical, including serialVersionUID, or deserialization on the
 * frontend will fail after any change made only on one side.
 *
 * On comphandservice this class is also the Hibernate-mapped class --
 * see src/main/resources/com/comphand/model/User.hbm.xml.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
