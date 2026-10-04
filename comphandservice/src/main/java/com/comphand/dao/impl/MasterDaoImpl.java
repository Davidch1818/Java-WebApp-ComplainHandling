package com.comphand.dao.impl;

import com.comphand.dao.MasterDao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.comphand.dao.ProductDao;
import com.comphand.model.Branch;
import com.comphand.model.Channel;
import com.comphand.model.City;
import com.comphand.model.Code_601;
import com.comphand.model.Code_601_Info;
import com.comphand.model.Code_602;
import com.comphand.model.Code_602_Info;
import com.comphand.model.Code_603;
import com.comphand.model.Code_603_Info;
import com.comphand.model.Code_604;
import com.comphand.model.Code_604_Info;
import com.comphand.model.Code_605;
import com.comphand.model.Code_605_Info;
import com.comphand.model.MasterOtor;
import com.comphand.model.Product;
import com.comphand.model.System_Logs;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;

public class MasterDaoImpl implements MasterDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public List<Branch> lstBranches() {
//        List<Branch> hasil = currentSession().createQuery(
//        "FROM Branch b WHERE b.is_deleted = 'N' ORDER BY b.branch_code").list();
        List<Branch> hasil = currentSession().createSQLQuery("SELECT branch_code,branch_name,city_code,is_deleted,created_at,update_at,user_input,user_otor "
                + "FROM BRANCH WHERE is_deleted = 'N' ORDER BY branch_code").addEntity(Branch.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Channel> lstChannels() {
        List<Channel> hasil = currentSession().createSQLQuery("SELECT channel_code,CHANNEL_NAME,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM CHANNEL WHERE is_deleted='N' ORDER BY channel_code").addEntity(Channel.class).list();
        currentSession().flush();
        return hasil;}

    @Override
    public List<City> lstCities() {
        List<City> hasil = currentSession().createSQLQuery("SELECT CITY_CODE,city_name,user_input,user_otor,is_deleted,created_at,update_at "
                + "FROM CITY WHERE is_deleted='N' ORDER BY CITY_CODE").addEntity(City.class).list();
        currentSession().flush();
        return hasil;}

    @Override
    public List<Code_601> lstCode601Products() {
        List<Code_601> hasil = currentSession().createSQLQuery("SELECT code_601_code,code_601_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM CODE_601 WHERE is_deleted='N' ORDER BY code_601_code").addEntity(Code_601.class).list();
        currentSession().flush();
        return hasil;}

    @Override
    public List<Code_601_Info> lstCode601Problems() {
        List<Code_601_Info> hasil = currentSession().createSQLQuery("SELECT code_601p_code,code_601p_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM code_601_problem WHERE is_deleted='N' ORDER BY code_601p_code").addEntity(Code_601_Info.class).list();
        currentSession().flush();
        return hasil;}

    @Override
    public List<Code_602> lstCode602Products() {
        List<Code_602> hasil = currentSession().createSQLQuery("SELECT code_602_code,code_602_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM CODE_602 WHERE is_deleted='N' ORDER BY code_602_code").addEntity(Code_602.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_602_Info> lstCode602Problems() {
        List<Code_602_Info> hasil = currentSession().createSQLQuery("SELECT code_602p_code,code_602p_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM code_602_problem WHERE is_deleted='N' ORDER BY code_602p_code").addEntity(Code_602_Info.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_603> lstCode603Products() {
        List<Code_603> hasil = currentSession().createSQLQuery("SELECT code_603_code,code_603_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM CODE_603 WHERE is_deleted='N' ORDER BY code_603_code").addEntity(Code_603.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_603_Info> lstCode603Problems() {
        List<Code_603_Info> hasil = currentSession().createSQLQuery("SELECT code_603p_code,code_603p_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM code_603_problem WHERE is_deleted='N' ORDER BY code_603p_code").addEntity(Code_603_Info.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_604> lstCode604Products() {
        List<Code_604> hasil = currentSession().createSQLQuery("SELECT code_604_code,code_604_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM CODE_604 WHERE is_deleted='N' ORDER BY code_604_code").addEntity(Code_604.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_604_Info> lstCode604Problems() {
        List<Code_604_Info> hasil = currentSession().createSQLQuery("SELECT code_604p_code,code_604p_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM code_604_problem WHERE is_deleted='N' ORDER BY code_604p_code").addEntity(Code_604_Info.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_605> lstCode605Products() {
        List<Code_605> hasil = currentSession().createSQLQuery("SELECT code_605_code,code_605_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM CODE_605 WHERE is_deleted='N' ORDER BY code_605_code").addEntity(Code_605.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Code_605_Info> lstCode605Problems() {
        List<Code_605_Info> hasil = currentSession().createSQLQuery("SELECT code_605p_code,code_605p_name,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM code_605_problem WHERE is_deleted='N' ORDER BY code_605p_code").addEntity(Code_605_Info.class).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Branch> lstBranchesByCity(String city) {
        List<Branch> hasil = currentSession().createSQLQuery("SELECT BRANCH_CODE,BRANCH_NAME,CITY_CODE,USER_INPUT,USER_OTOR,is_deleted,created_at,update_at "
                + "FROM BRANCH WHERE CITY_CODE=:CITY_CODE AND is_deleted='N' ").addEntity(Branch.class).setParameter("CITY_CODE", city).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public String insMstData(MasterOtor masterotor, String action, String prstype, MasterOtor masterotorb4) {
                System.out.println("AWALLLL 1 "+action);
        String outMsg="",querytamp1="",querytamp2="",querytamp3="";
        if(action.equals("Branch"))
        {
            querytamp1="BRANCH_CODE";
            querytamp2="BRANCH";
            querytamp3="Branch code";
        }
        else if(action.equals("Channel"))
        {
            querytamp1="channel_code";
            querytamp2="channel";
            querytamp3="Channel code";
        }
        else if(action.equals("City"))
        {
            querytamp1="CITY_CODE";
            querytamp2="CITY";
            querytamp3="City code";
        }
        else if(action.equals("Code_601"))
        {
            querytamp1="code_601_code";
            querytamp2="CODE_601";
            querytamp3="Product code 601";
        }
        else if(action.equals("Code_602"))
        {
            querytamp1="code_602_code";
            querytamp2="CODE_602";
            querytamp3="Product code 602";
        }
        else if(action.equals("Code_603"))
        {
            querytamp1="code_603_code";
            querytamp2="CODE_603";
            querytamp3="Product code 603";
        }
        else if(action.equals("Code_604"))
        {
            querytamp1="code_604_code";
            querytamp2="CODE_604";
            querytamp3="Product code 604";
        }
        else if(action.equals("Code_605"))
        {
            querytamp1="code_605_code";
            querytamp2="CODE_605";
            querytamp3="Product code 605";
        }
        else if(action.equals("Code_601_Info"))
        {
            querytamp1="code_601p_code";
            querytamp2="code_601_problem";
            querytamp3="Product info code 601";
        }
        else if(action.equals("Code_602_Info"))
        {
            querytamp1="code_602p_code";
            querytamp2="code_602_problem";
            querytamp3="Product info code 602";
        }
        else if(action.equals("Code_603_Info"))
        {
            querytamp1="code_603p_code";
            querytamp2="code_603_problem";
            querytamp3="Product info code 603";
        }
        else if(action.equals("Code_604_Info"))
        {
            querytamp1="code_604p_code";
            querytamp2="code_604_problem";
            querytamp3="Product info code 604";
        }
        else if(action.equals("Code_605_Info"))
        {
            querytamp1="code_605p_code";
            querytamp2="code_605_problem";
            querytamp3="Product info code 605";
        }
        System.out.println("AWALLLL 1AAA");
        int hasilCek = Integer.parseInt(currentSession().createSQLQuery("select count("+querytamp1+") hasilCek from "+querytamp2+" where "+querytamp1+"=:cCode").addScalar("hasilCek")
                .setParameter("cCode", masterotor.getCODE1()).list().get(0).toString());
        System.out.println("AWALLLL 1BBBB");
        int hasilCek2 = Integer.parseInt(currentSession().createSQLQuery("select count(CODE1) hasilCek from MASTER_OTOR where CODE1=:cCode and ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS").addScalar("hasilCek")
                .setParameter("cCode", masterotor.getCODE1()).setParameter("ACTION", masterotor.getACTION()).setParameter("TYPE_PRS", masterotor.getTYPE_PRS()).list().get(0).toString());
        System.out.println("AWALLLL 1CCCC");
        int cekMstCity = Integer.parseInt(currentSession().createSQLQuery("select count(CITY_CODE) hasilCek from CITY where CITY_CODE=:code AND is_deleted='Y'").addScalar("hasilCek")
                .setParameter("code", masterotor.getCODE2()).list().get(0).toString());
        System.out.println("AWALLLL 1DDDDD");
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id, 'MST"+prstype.toUpperCase()+"', '') AS INTEGER)), 0) + 1 as hasilCek \n" +
"from System_Logs \n" +
"where Log_Id like 'MST"+prstype.toUpperCase()+"%'").addScalar("hasilCek").list().get(0).toString());
//        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select nvl(max(to_number(replace(Log_Id,'MST"+prstype.toUpperCase()+"',''))),0)+1 as hasilCek from System_Logs where Log_Id like 'MST"+prstype.toUpperCase()+"%'").addScalar("hasilCek")
//                .list().get(0).toString());
        
        if(hasilCek>0&&prstype.equals("Add"))
//        if(hasilCek>0)
        {
            outMsg= querytamp3+" "+masterotor.getCODE1()+" sudah digunakan pada data existing";
            System.out.println("AWALLLL 1EEEEE");
        }
        else if(hasilCek2>0)
        {
            outMsg= querytamp3+" "+masterotor.getCODE1()+" sudah direquest";
            System.out.println("AWALLLL 1FFFFF");
        }
        else if(cekMstCity>0)
        {
            outMsg="City code "+masterotor.getCODE2()+" tidak dapat dipilih karena sudah nonaktif \n";
            System.out.println("AWALLLL 1GGGGG");
        }
//        else if((hasilCek<=0&&hasilCek2<=0&&prstype.equals("Add"))||(hasilCek2<=0&&prstype.equals("Edit"))||(hasilCek2<=0&&prstype.equals("Delete")))
        else if((hasilCek<=0&&hasilCek2<=0&&cekMstCity<=0&&prstype.equals("Add"))||(hasilCek2<=0&&cekMstCity<=0&&prstype.equals("Edit"))||(hasilCek2<=0&&cekMstCity<=0&&prstype.equals("Delete")))
//        else if(hasilCek<=0&&hasilCek2<=0)
        {
            System.out.println("AWALLLL 1HHHHH");
//            try {
                System.out.println("AWALLLL 1IIII");
                currentSession().createSQLQuery("INSERT INTO MASTER_OTOR (\n" +
                "  CODE1, NAME, CODE2, USER_INPUT, USER_OTOR, ACTION, TYPE_PRS " +
                ") VALUES (\n" +
                "  :CODE1, :NAME, :CODE2, :USER_INPUT, :USER_OTOR, :ACTION, :TYPE_PRS)").setParameter("CODE1", masterotor.getCODE1().trim())
                    .setParameter("NAME", masterotor.getNAME().trim()).setParameter("CODE2", masterotor.getCODE2().trim())
                    .setParameter("USER_INPUT", masterotor.getUSER_INPUT().trim()).setParameter("USER_OTOR", masterotor.getUSER_OTOR().trim())
                    .setParameter("ACTION", masterotor.getACTION().trim())
                    .setParameter("TYPE_PRS", masterotor.getTYPE_PRS().trim())
                    .executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                    + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                    + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                    + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "MST"+prstype.toUpperCase()+idxLogID).setParameter("USER_ID", masterotor.getUSER_INPUT())
                    .setParameter("ACTIVITY_TYPE", "REQ_"+prstype.toUpperCase()+"_MST_"+action.toUpperCase()).setParameter("REQUEST_ID", masterotor.getCODE1())
                    .setParameter("DESCRIPTION", prstype.equals("Add") ? "New Master "+action+" request added for code "+masterotor.getCODE1()+" by user "+masterotor.getUSER_INPUT() 
                            : prstype.equals("Edit") ? "Master "+action+" request updated for code "+masterotor.getCODE1()+" by user "+masterotor.getUSER_INPUT()+
                            " : Changed "+"Master "+action+" name from "+masterotorb4.getNAME()+" to "+masterotor.getNAME()+"."
                            : prstype.equals("Delete") ? "Master "+action+" request deleted for code "+masterotor.getCODE1()+" by user "+masterotor.getUSER_INPUT()
                            : "")
                    .setParameter("CHANGED_FIELDS", prstype.equals("Edit") ? "Master "+action+" name from "+masterotorb4.getNAME()+" to "+masterotor.getNAME()+"."  : "-")
                    .setParameter("DESC_FILE", "-")
                    .setParameter("USER_OTOR", masterotor.getUSER_OTOR()).executeUpdate();
                
                System.out.println("AWALLLL 4");
                currentSession().flush();
                System.out.println("AWALLLL 5");
                outMsg="Data dengan "+querytamp3+" "+masterotor.getCODE1()+" berhasil request "+prstype+".";
                System.out.println("AWALLLL 6 "+outMsg);
//            } catch (Exception e) {
//                try {
//                    throw new Exception(e.getCause().getMessage());
//                } catch (Exception ex) {
//                    Logger.getLogger(MasterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
//                outMsg="Data dengan "+querytamp3+" "+masterotor.getCODE1()+" gagal request "+prstype+".\n Penyebab errornya: "+e.getCause().getMessage();
//            }
        }
        return outMsg;
    }

    @Override
    public List<MasterOtor> lstMstOtor(String puserinput) {
                List<MasterOtor> result = new ArrayList<MasterOtor>();
        try {
            List<Object[]> rows = currentSession().createSQLQuery("SELECT CODE1, NAME, CODE2, USER_INPUT, USER_OTOR, ACTION, TYPE_PRS FROM\n" +
            " MASTER_OTOR WHERE USER_INPUT<>:USER_INPUT").setParameter("USER_INPUT", puserinput).list();
            
            for (Object[] row : rows) {
                String code1 = (String) row[0];
                String name = (String) row[1];
                String code2 = (String) row[2];
                String userinput = (String) row[3];
                String userotor = (String) row[4];
                String action = (String) row[5];
                String typeprs = (String) row[6];

                result.add(new MasterOtor(code1, name, code2, userinput, userotor, action, typeprs));
            }
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        System.out.println("ISI DR DAO IMPL"+result.size());
        return result;
    }

    @Override
    public String approvedInsMstData(MasterOtor masterotor, String action) {
                String outMsg="",txt1="",txt2="";
        if(action.equals("Channel"))
        {
            txt1="select count(channel_code) hasilCek from CHANNEL where channel_code=:reqid";
            txt2="INSERT INTO CHANNEL(channel_code,channel_name,user_input,user_otor,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Branch"))
        {
            txt1="select count(BRANCH_CODE) hasilCek from BRANCH where BRANCH_CODE=:reqid";
            txt2="INSERT INTO BRANCH(BRANCH_CODE,BRANCH_NAME,CITY_CODE,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,CODE2,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
            
        }
        else if(action.equals("City"))
        {
            txt1="select count(CITY_CODE) hasilCek from CITY where CITY_CODE=:reqid";
            txt2="INSERT INTO CITY(CITY_CODE,CITY_NAME,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_601"))
        {
            txt1="select count(code_601_code) hasilCek from CODE_601 where code_601_code=:reqid";
            txt2="INSERT INTO CODE_601(code_601_code,code_601_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_602"))
        {
            txt1="select count(code_602_code) hasilCek from CODE_602 where code_602_code=:reqid";
            txt2="INSERT INTO CODE_602(code_602_code,code_602_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_603"))
        {
            txt1="select count(code_603_code) hasilCek from CODE_603 where code_603_code=:reqid";
            txt2="INSERT INTO CODE_603(code_603_code,code_603_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_604"))
        {
            txt1="select count(code_604_code) hasilCek from CODE_604 where code_604_code=:reqid";
            txt2="INSERT INTO CODE_604(code_604_code,code_604_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_605"))
        {
            txt1="select count(code_605_code) hasilCek from CODE_605 where code_605_code=:reqid";
            txt2="INSERT INTO CODE_605(code_605_code,code_605_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_601_Info"))
        {
            txt1="select count(code_601p_code) hasilCek from code_601_problem where code_601p_code=:reqid";
            txt2="INSERT INTO code_601_problem(code_601p_code,code_601p_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_602_Info"))
        {
            txt1="select count(code_602p_code) hasilCek from code_602_problem where code_602p_code=:reqid";
            txt2="INSERT INTO code_602_problem(code_602p_code,code_602p_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_603_Info"))
        {
            txt1="select count(code_603p_code) hasilCek from code_603_problem where code_603p_code=:reqid";
            txt2="INSERT INTO code_603_problem(code_603p_code,code_603p_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_604_Info"))
        {
            txt1="select count(code_604p_code) hasilCek from code_604_problem where code_604p_code=:reqid";
            txt2="INSERT INTO code_604_problem(code_604p_code,code_604p_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        else if(action.equals("Code_605_Info"))
        {
            txt1="select count(code_605p_code) hasilCek from code_605_problem where code_605p_code=:reqid";
            txt2="INSERT INTO code_605_problem(code_605p_code,code_605p_name,USER_INPUT,USER_OTOR,is_deleted)"
                + " SELECT CODE1,NAME,USER_INPUT,:USER_OTOR,'N' FROM MASTER_OTOR "
                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
        }
        
        int hasilCek = Integer.parseInt(currentSession().createSQLQuery(txt1).addScalar("hasilCek")
                .setParameter("reqid", masterotor.getCODE1()).list().get(0).toString());
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'OKMSTADD','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'OKMSTADD%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        if(hasilCek>0)
        {
            outMsg= "Code "+masterotor.getCODE1()+" sudah digunakan pada data existing";
        }
        else if(hasilCek<=0)
        {
            try {
                
                currentSession().createSQLQuery(txt2).setParameter("CODE1", masterotor.getCODE1().trim())
                    .setParameter("ACTION", masterotor.getACTION().trim())
                    .setParameter("USER_OTOR", masterotor.getUSER_OTOR())
                    .setParameter("TYPE_PRS", masterotor.getTYPE_PRS())
                .executeUpdate();
                
                
                currentSession().createSQLQuery("DELETE FROM MASTER_OTOR WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ").setParameter("CODE1", masterotor.getCODE1().trim())
                        .setParameter("ACTION", masterotor.getACTION().trim())
                        .setParameter("TYPE_PRS", masterotor.getTYPE_PRS())
                    .executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                    + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                    + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                    + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "OKMSTADD"+idxLogID).setParameter("USER_ID", masterotor.getUSER_INPUT())
                    .setParameter("ACTIVITY_TYPE", "APP_ADD_MST_"+action.toUpperCase()).setParameter("REQUEST_ID", masterotor.getCODE1())
                    .setParameter("DESCRIPTION", "New Master "+action+" approve added for code "+masterotor.getCODE1()+" by user "+masterotor.getUSER_OTOR()
                            )
                    .setParameter("CHANGED_FIELDS", "-")
                    .setParameter("DESC_FILE", "-")
                    .setParameter("USER_OTOR", masterotor.getUSER_OTOR()).executeUpdate();
                
                currentSession().flush();
                
                outMsg="Data dengan Code "+masterotor.getCODE1()+" dan Action "+masterotor.getACTION()+" berhasil diotor.";
            } catch (Exception e) {
                try {
                    throw new Exception(e.getCause().getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(MasterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                outMsg="Data dengan Code "+masterotor.getCODE1()+" dan Action "+masterotor.getACTION()+" gagal diotor.\n Penyebab errornya: "+e.getCause().getMessage();
            }
        }
        return outMsg;
    }

    @Override
    public String approvedEditMstData(MasterOtor masterotor, String action) {
                String outMsg="",txt2="";
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'OKMSTEDIT','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'OKMSTEDIT%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        List<System_Logs> inqlogEditLatest = currentSession().createSQLQuery("select LOG_ID,USER_INPUT,ACTIVITY_TYPE,REQUEST_ID,TGL_INPUT,DESCRIPTION,CHANGED_FIELDS,DESC_FILE,USER_OTOR "
                    + "from (select LOG_ID,USER_INPUT,ACTIVITY_TYPE,REQUEST_ID,TGL_INPUT,DESCRIPTION,CHANGED_FIELDS,DESC_FILE,USER_OTOR from System_Logs "
                    + "where Request_Id=:Request_Id and Activity_Type=:Activity_Type "
                    + " order by Tgl_Input desc) LIMIT 1 ").addEntity(System_Logs.class)
                    .setParameter("Request_Id", masterotor.getCODE1().trim())
                    .setParameter("Activity_Type", "REQ_EDIT_MST_"+action.toUpperCase())
                .list();
        
        if(action.equals("Channel"))
        {
//            txt1="select count(channel_code) hasilCek from CHANNEL where channel_code=:reqid";
            txt2="MERGE INTO CHANNEL c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.channel_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                CHANNEL_NAME = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
            
            
        }
        else if(action.equals("Branch"))
        {
//            txt1="select count(BRANCH_CODE) hasilCek from BRANCH where BRANCH_CODE=:reqid";
            txt2="MERGE INTO BRANCH b\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (b.BRANCH_CODE = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                BRANCH_NAME = mo.NAME,\n" +
    "                CITY_CODE = mo.CODE2,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
            
        }
        else if(action.equals("City"))
        {
//            txt1="select count(CITY_CODE) hasilCek from CITY where CITY_CODE=:reqid";
            txt2="MERGE INTO CITY c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.CITY_CODE = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                CITY_NAME = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_601"))
        {
            txt2="MERGE INTO CODE_601 c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_601_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_601_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_602"))
        {
            txt2="MERGE INTO CODE_602 c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_602_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_602_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_603"))
        {
            txt2="MERGE INTO CODE_603 c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_603_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_603_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_604"))
        {
            txt2="MERGE INTO CODE_604 c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_604_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_604_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_605"))
        {
            txt2="MERGE INTO CODE_605 c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_605_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_605_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_601_Info"))
        {
            txt2="MERGE INTO code_601_problem c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_601p_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_601p_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_602_Info"))
        {
            txt2="MERGE INTO code_602_problem c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_602p_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_602p_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_603_Info"))
        {
            txt2="MERGE INTO code_603_problem c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_603p_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_603p_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_604_Info"))
        {
            txt2="MERGE INTO code_604_problem c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_604p_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_604p_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        else if(action.equals("Code_605_Info"))
        {
            txt2="MERGE INTO code_605_problem c\n" +
    "            USING (\n" +
    "                SELECT * FROM Master_Otor WHERE Action = :ACTION and CODE1=:CODE1 AND TYPE_PRS=:TYPE_PRS\n" +
    "            ) mo\n" +
    "            ON (c.code_605p_code = mo.CODE1)\n" +
    "            WHEN MATCHED THEN\n" +
    "            UPDATE SET\n" +
    "                code_605p_name = mo.NAME,\n" +
    "                USER_INPUT = mo.USER_INPUT,\n" +
    "                USER_OTOR = :USER_OTOR";
        }
        
//        int hasilCek = Integer.parseInt(session.createSQLQuery(txt1).addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("reqid", masterotor.getCODE1()).list().get(0).toString());
//        
//        if(hasilCek>0)
//        {
//            outMsg= "Code "+masterotor.getCODE1()+" sudah digunakan pada data existing";
//        }
//        else if(hasilCek<=0)
//        {
//            try {
                System.out.println("DATAAA1 "+masterotor.getCODE1());
                System.out.println("DATAAA2 "+masterotor.getACTION());
                System.out.println("DATAAA3 "+masterotor.getUSER_OTOR());
                System.out.println("DATAAA4 "+masterotor.getTYPE_PRS());
                currentSession().createSQLQuery(txt2).setParameter("CODE1", masterotor.getCODE1().trim())
                    .setParameter("ACTION", masterotor.getACTION().trim())
                    .setParameter("USER_OTOR", masterotor.getUSER_OTOR())
                    .setParameter("TYPE_PRS", masterotor.getTYPE_PRS())
                .executeUpdate();
                
                
                currentSession().createSQLQuery("DELETE FROM MASTER_OTOR WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS").setParameter("CODE1", masterotor.getCODE1().trim())
                        .setParameter("ACTION", masterotor.getACTION().trim())
                        .setParameter("TYPE_PRS", masterotor.getTYPE_PRS().trim())
                    .executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                    + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                    + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                    + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "OKMSTEDIT"+idxLogID).setParameter("USER_ID", masterotor.getUSER_INPUT())
                    .setParameter("ACTIVITY_TYPE", "APP_EDIT_MST_"+action.toUpperCase()).setParameter("REQUEST_ID", masterotor.getCODE1())
                    .setParameter("DESCRIPTION", inqlogEditLatest.get(0).getDescription().replace("request", "approve"))
                    .setParameter("CHANGED_FIELDS", inqlogEditLatest.get(0).getChanged_fields())
                    .setParameter("DESC_FILE", "-")
                    .setParameter("USER_OTOR", masterotor.getUSER_OTOR()).executeUpdate();
                
                currentSession().flush();
                
                outMsg="Data dengan Code "+masterotor.getCODE1()+" dan Action "+masterotor.getACTION()+" berhasil diotor.";
//            } catch (Exception e) {
//                try {
//                    throw new Exception(e.getCause().getMessage());
//                } catch (Exception ex) {
//                    Logger.getLogger(MasterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                outMsg="Data dengan Code "+masterotor.getCODE1()+" dan Action "+masterotor.getACTION()+" gagal diotor.\n Penyebab errornya: "+e.getCause().getMessage();
//            }
//        }
        return outMsg;
    }

    @Override
    public String approvedDelMstData(MasterOtor masterotor, String action) {
                String outMsg="",txt="",txt2="";
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'OKMSTDEL','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'OKMSTDEL%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        if(action.equals("Channel"))
        {
            
            txt2="UPDATE Channel SET is_deleted='Y' where channel_code=:CODE1";
        }
        else if(action.equals("Branch"))
        {
            txt2="UPDATE Branch SET is_deleted='Y' where Branch_Code=:CODE1";
        }
        else if(action.equals("City"))
        {
            txt2="UPDATE City SET is_deleted='Y' where CITY_CODE=:CODE1";
            txt="UPDATE Branch SET is_deleted='Y' where CITY_CODE=:CODE1";
            currentSession().createSQLQuery(txt).setParameter("CODE1", masterotor.getCODE1().trim())
            .executeUpdate();
        }
        else if(action.equals("Code_601"))
        {
            txt2="UPDATE Code_601 SET is_deleted='Y' where code_601_code=:CODE1";
        }
        else if(action.equals("Code_602"))
        {
            txt2="UPDATE Code_602 SET is_deleted='Y' where code_602_code=:CODE1";
        }
        else if(action.equals("Code_603"))
        {
            txt2="UPDATE Code_603 SET is_deleted='Y' where code_603_code=:CODE1";
        }
        else if(action.equals("Code_604"))
        {
            txt2="UPDATE Code_604 SET is_deleted='Y' where code_604_code=:CODE1";
        }
        else if(action.equals("Code_605"))
        {
            txt2="UPDATE Code_605 SET is_deleted='Y' where code_605_code=:CODE1";
        }
        else if(action.equals("Code_601_Info"))
        {
            txt2="UPDATE code_601_problem SET is_deleted='Y' where code_601p_code=:CODE1";
        }
        else if(action.equals("Code_602_Info"))
        {
            txt2="UPDATE code_602_problem SET is_deleted='Y' where code_602p_code=:CODE1";
        }
        else if(action.equals("Code_603_Info"))
        {
            txt2="UPDATE code_603_problem SET is_deleted='Y' where code_603p_code=:CODE1";
        }
        else if(action.equals("Code_604_Info"))
        {
            txt2="UPDATE code_604_problem SET is_deleted='Y' where code_604p_code=:CODE1";
        }
        else if(action.equals("Code_605_Info"))
        {
            txt2="UPDATE code_605_problem SET is_deleted='Y' where code_605p_code=:CODE1";
        }
        try {
//            txt="INSERT INTO MASTER_DELETED(CODE1,NAME,CODE2,USER_INPUT,USER_OTOR,ACTION)"
//                + " SELECT CODE1,NAME,CODE2,USER_INPUT,:USER_OTOR,ACTION FROM MASTER_OTOR "
//                + " WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ";
            currentSession().createSQLQuery(txt2).setParameter("CODE1", masterotor.getCODE1().trim())
            .executeUpdate();
            
            currentSession().createSQLQuery("DELETE FROM MASTER_OTOR WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS").setParameter("CODE1", masterotor.getCODE1().trim())
                    .setParameter("ACTION", masterotor.getACTION().trim())
                    .setParameter("TYPE_PRS", masterotor.getTYPE_PRS().trim())
                .executeUpdate();
            
            currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                    + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                    + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                    + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "OKMSTDEL"+idxLogID).setParameter("USER_ID", masterotor.getUSER_INPUT())
                    .setParameter("ACTIVITY_TYPE", "APP_DEL_MST_"+action.toUpperCase()).setParameter("REQUEST_ID", masterotor.getCODE1())
                    .setParameter("DESCRIPTION", "Master "+action+" approve deleted for code "+masterotor.getCODE1()+" by user "+masterotor.getUSER_OTOR())
                    .setParameter("CHANGED_FIELDS", "-")
                    .setParameter("DESC_FILE", "-")
                    .setParameter("USER_OTOR", masterotor.getUSER_OTOR()).executeUpdate();
            
            currentSession().flush();

            outMsg="Data dengan Code "+masterotor.getCODE1()+" dan Action "+masterotor.getACTION()+" berhasil diotor.";
        } catch (Exception e) {
            try {
                throw new Exception(e.getCause().getMessage());
            } catch (Exception ex) {
                Logger.getLogger(MasterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            outMsg="Data dengan Code "+masterotor.getCODE1()+" dan Action "+masterotor.getACTION()+" gagal diotor.\n Penyebab errornya: "+e.getCause().getMessage();
        }
        return outMsg;
    }

    @Override
    public String rejectedInsMstData(MasterOtor masterotor) {
        String outMsg="";
        try {

            currentSession().createSQLQuery("DELETE FROM MASTER_OTOR WHERE CODE1=:CODE1 AND ACTION=:ACTION AND TYPE_PRS=:TYPE_PRS ").setParameter("CODE1", masterotor.getCODE1().trim())
                    .setParameter("ACTION", masterotor.getACTION().trim())
                    .setParameter("TYPE_PRS", masterotor.getTYPE_PRS())
                .executeUpdate();
            currentSession().flush();

            outMsg="Data dengan kode "+masterotor.getCODE1()+" dan action "+masterotor.getACTION()+" berhasil direject.";
        } catch (Exception e) {
            try {
                throw new Exception(e.getCause().getMessage());
            } catch (Exception ex) {
                Logger.getLogger(MasterDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            outMsg="Data dengan kode "+masterotor.getCODE1()+" dan action "+masterotor.getACTION()+" gagal direject.\n Penyebab errornya: "+e.getCause().getMessage();
        }
        return outMsg;
    }
}
