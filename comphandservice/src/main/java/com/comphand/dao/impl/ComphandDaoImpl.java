package com.comphand.dao.impl;

import com.comphand.dao.ComphandDao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.comphand.model.CompOtor;
import com.comphand.model.CompReport;
import com.comphand.model.Compdata;
import com.comphand.model.System_Logs;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.type.StandardBasicTypes;

public class ComphandDaoImpl implements ComphandDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Compdata> lstCompData(boolean isOutStandComp) {
        List<Compdata> hasil=null;
        String query="";
        if(!isOutStandComp)
        {
            query="SELECT \n" +
                    "REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
                    "channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
                    "RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
                    "CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
                    "CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
                    "CODE_605, CODE_605_PROBLEM, \n" +
                    "dispute_filepath, settlement_filepath, extension_filepath, \n" +
                    "USER_INPUT, USER_OTOR, AGING, action, is_deleted,create_at,comp_type,req_type,address,update_at,incident_checkbox,status "
                    + "FROM (SELECT \n" +
                    "REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
                    "channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
                    "RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
                    "CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
                    "CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
                    "CODE_605, CODE_605_PROBLEM, \n" +
                    "dispute_filepath, settlement_filepath, extension_filepath, \n" +
                    "USER_INPUT, USER_OTOR, AGING, action, is_deleted,create_at,comp_type,req_type,address,update_at,incident_checkbox,status \n" +
                    "FROM complaint WHERE is_deleted='N' ORDER BY RECEIVED_DATE DESC)\n" +
                    "LIMIT 50";
        }
        else if(isOutStandComp)
        {
            query="SELECT \n" +
                    "REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
                    "channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
                    "RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
                    "CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
                    "CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
                    "CODE_605, CODE_605_PROBLEM, \n" +
                    "dispute_filepath, settlement_filepath, extension_filepath, \n" +
                    "USER_INPUT, USER_OTOR, AGING, action, is_deleted,create_at,comp_type,req_type,address,update_at,incident_checkbox,status "
                    + "FROM (SELECT \n" +
                    "REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
                    "channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
                    "RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
                    "CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
                    "CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
                    "CODE_605, CODE_605_PROBLEM, \n" +
                    "dispute_filepath, settlement_filepath, extension_filepath, \n" +
                    "USER_INPUT, USER_OTOR, AGING, action, is_deleted,create_at,comp_type,req_type,address,update_at,incident_checkbox,status \n" +
                    "FROM complaint WHERE is_deleted='N' ORDER BY RECEIVED_DATE DESC)\n" +
                    " WHERE COMPLETION_DATE IS NULL LIMIT 50";
        }
        
        try {
            hasil = currentSession().createSQLQuery(query).addEntity(Compdata.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return hasil;
    }

    @Override
    public Compdata getOneCompData(String reqid) {
        Compdata hasil = (Compdata) currentSession().createSQLQuery("SELECT \n" +
            "  REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
            "  channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
            "  RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
            "  CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
            "  CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
            "  CODE_605, CODE_605_PROBLEM, \n" +
            "  dispute_filepath, settlement_filepath, extension_filepath, \n" +
            "  USER_INPUT, USER_OTOR, AGING, is_deleted,create_at,comp_type,req_type,address,update_at,incident_checkbox,status \n" +
            "FROM complaint WHERE REQUEST_ID=:pReqID").addEntity(Compdata.class).setParameter("pReqID", reqid).list().get(0);
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<Compdata> lstCompDataByFilter(Date sDate, Date eDate, String keyString) {
        List<Compdata> hasil = currentSession().createSQLQuery("SELECT \n" +
            "    co.REQUEST_ID, co.SETTLEMENT_NUMBER, co.EXTENSION_NUMBER, co.CUSTOMER_NAME, co.LOCATION, \n" +
            "    co.channel_code, co.CITY_CODE, co.BRANCH_CODE, co.AMOUNT, co.ATM_CARD_NUMBER, co.DESCRIPTION, \n" +
            "    co.RECEIVED_DATE, co.INCIDENT_DATE, co.COMPLETION_DATE, \n" +
            "    co.CODE_601, co.CODE_601_PROBLEM, co.CODE_602, co.CODE_602_PROBLEM, \n" +
            "    co.CODE_603, co.CODE_603_PROBLEM, co.CODE_604, co.CODE_604_PROBLEM, \n" +
            "    co.CODE_605, co.CODE_605_PROBLEM, \n" +
            "    co.dispute_filepath, co.settlement_filepath, co.extension_filepath, \n" +
            "    co.USER_INPUT, co.USER_OTOR, co.AGING, co.is_deleted, co.create_at,co.comp_type,co.req_type,"
                + "co.address,co.update_at,co.incident_checkbox,co.status,co.action \n" +
            "FROM complaint co\n" +
            "JOIN CITY c ON co.CITY_CODE = c.CITY_CODE\n" +
            "JOIN BRANCH b ON co.BRANCH_CODE = b.BRANCH_CODE\n" +
            "WHERE co.is_deleted = 'N' \n" +
            "  AND (\n" +
            "      CAST(co.RECEIVED_DATE AS DATE) BETWEEN :sDate AND :eDate OR\n" +
            "      CAST(co.INCIDENT_DATE AS DATE) BETWEEN :sDate AND :eDate OR\n" +
            "      CAST(co.COMPLETION_DATE AS DATE) BETWEEN :sDate AND :eDate OR\n" +
            "      CAST(co.REQUEST_ID AS TEXT) ILIKE :key OR\n" +
            "      co.CUSTOMER_NAME ILIKE :key OR\n" +
            "      c.CITY_NAME ILIKE :key OR\n" +
            "      b.BRANCH_NAME ILIKE :key\n" +
            "  ) \n" +
            "ORDER BY co.REQUEST_ID DESC").addEntity(Compdata.class).setParameter("sDate", (sDate != null) ? sDate : java.sql.Date.valueOf("1900-01-01"))
                .setParameter("eDate", (eDate != null) ? eDate : java.sql.Date.valueOf("1900-01-01"))
//        List<Compdata> hasil = currentSession().createSQLQuery("SELECT co.REQUEST_ID, co.SETTLEMENT_NUMBER, co.EXTENSION_NUMBER, co.CUSTOMER_NAME, co.LOCATION, \n" +
//"              co.channel_code, co.CITY_CODE, co.BRANCH_CODE, co.AMOUNT, co.ATM_CARD_NUMBER, co.DESCRIPTION, \n" +
//"              co.RECEIVED_DATE, co.INCIDENT_DATE, co.COMPLETION_DATE, \n" +
//"              co.CODE_601, co.CODE_601_PROBLEM, co.CODE_602, co.CODE_602_PROBLEM, \n" +
//"              co.CODE_603, co.CODE_603_PROBLEM, co.CODE_604, co.CODE_604_PROBLEM, \n" +
//"              co.CODE_605, co.CODE_605_PROBLEM, \n" +
//"              co.dispute_filepath, co.settlement_filepath, co.extension_filepath, \n" +
//"              co.USER_INPUT, co.USER_OTOR, co.AGING, co.is_deleted \n" +
//"            FROM complaint co, CITY c, BRANCH b\n" +
//"            WHERE co.is_deleted='N' AND co.CITY_CODE=c.CITY_CODE AND co.BRANCH_CODE=b.BRANCH_CODE AND (\n" +
//"            TRUNC(co.RECEIVED_DATE) between :sDate and :eDate OR\n" +
//"            TRUNC(co.INCIDENT_DATE) between :sDate and :eDate OR\n" +
//"            TRUNC(co.COMPLETION_DATE) between :sDate and :eDate OR\n" +
//"            UPPER(co.REQUEST_ID) LIKE :key OR\n" +
//"            UPPER(co.CUSTOMER_NAME) LIKE :key OR\n" +
//"            UPPER(c.CITY_NAME) LIKE :key OR\n" +
//"            UPPER(b.BRANCH_NAME) LIKE :key\n" +
//"            ) ORDER BY REQUEST_ID DESC ").addEntity(Compdata.class).setParameter("sDate", sDate).setParameter("eDate", eDate)
                .setParameter("key", keyString.toUpperCase()).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public List<CompReport> lstCompRpt() {
        List<CompReport> result = new ArrayList<CompReport>();
        try {
            List<Object[]> rows = currentSession().createSQLQuery("SELECT PRODUCT_TYPE, PROBLEM_TYPE, FREK \n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        COUNT(c.REQUEST_ID) AS FREK,\n" +
                "        '601-' || c6.CODE_601_NAME AS PRODUCT_TYPE, \n" +
                "        c6i.code_601p_name AS PROBLEM_TYPE,\n" +
                "        '601' AS KODE, \n" +
                "        c.CODE_601 AS RPOD, \n" +
                "        c.CODE_601_PROBLEM AS PROB \n" +
                "    FROM complaint c\n" +
                "    JOIN CODE_601 c6 ON c.CODE_601 = c6.CODE_601_CODE\n" +
                "    JOIN CODE_601_PROBLEM c6i ON c.CODE_601_PROBLEM = c6i.code_601p_code\n" +
                "    WHERE trim(c.CODE_601) IS NOT NULL \n" +
                "      AND trim(c.CODE_601_PROBLEM) IS NOT NULL \n" +
                "      AND c.is_deleted = 'N'\n" +
                "    GROUP BY c.CODE_601, c.CODE_601_PROBLEM, c6.CODE_601_NAME, c6i.code_601p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.REQUEST_ID) AS FREK,\n" +
                "        '602-' || c6.CODE_602_NAME AS PRODUCT_TYPE, \n" +
                "        c6i.code_602p_name AS PROBLEM_TYPE,\n" +
                "        '602' AS KODE, \n" +
                "        c.CODE_602 AS RPOD, \n" +
                "        c.CODE_602_PROBLEM AS PROB \n" +
                "    FROM complaint c\n" +
                "    JOIN CODE_602 c6 ON c.CODE_602 = c6.CODE_602_CODE\n" +
                "    JOIN CODE_602_PROBLEM c6i ON c.CODE_602_PROBLEM = c6i.code_602p_code\n" +
                "    WHERE trim(c.CODE_602) IS NOT NULL \n" +
                "      AND trim(c.CODE_602_PROBLEM) IS NOT NULL \n" +
                "      AND c.is_deleted = 'N'\n" +
                "    GROUP BY c.CODE_602, c.CODE_602_PROBLEM, c6.CODE_602_NAME, c6i.code_602p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.REQUEST_ID) AS FREK,\n" +
                "        '603-' || c6.CODE_603_NAME AS PRODUCT_TYPE, \n" +
                "        c6i.code_603p_name AS PROBLEM_TYPE,\n" +
                "        '603' AS KODE, \n" +
                "        c.CODE_603 AS RPOD, \n" +
                "        c.CODE_603_PROBLEM AS PROB \n" +
                "    FROM complaint c\n" +
                "    JOIN CODE_603 c6 ON c.CODE_603 = c6.CODE_603_CODE\n" +
                "    JOIN CODE_603_PROBLEM c6i ON c.CODE_603_PROBLEM = c6i.code_603p_code\n" +
                "    WHERE trim(c.CODE_603) IS NOT NULL \n" +
                "      AND trim(c.CODE_603_PROBLEM) IS NOT NULL \n" +
                "      AND c.is_deleted = 'N'\n" +
                "    GROUP BY c.CODE_603, c.CODE_603_PROBLEM, c6.CODE_603_NAME, c6i.code_603p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.REQUEST_ID) AS FREK,\n" +
                "        '604-' || c6.CODE_604_NAME AS PRODUCT_TYPE, \n" +
                "        c6i.code_604p_name AS PROBLEM_TYPE,\n" +
                "        '604' AS KODE, \n" +
                "        c.CODE_604 AS RPOD, \n" +
                "        c.CODE_604_PROBLEM AS PROB \n" +
                "    FROM complaint c\n" +
                "    JOIN CODE_604 c6 ON c.CODE_604 = c6.CODE_604_CODE\n" +
                "    JOIN CODE_604_PROBLEM c6i ON c.CODE_604_PROBLEM = c6i.code_604p_code\n" +
                "    WHERE trim(c.CODE_604) IS NOT NULL \n" +
                "      AND trim(c.CODE_604_PROBLEM) IS NOT NULL \n" +
                "      AND c.is_deleted = 'N'\n" +
                "    GROUP BY c.CODE_604, c.CODE_604_PROBLEM, c6.CODE_604_NAME, c6i.code_604p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.REQUEST_ID) AS FREK,\n" +
                "        '605-' || c6.CODE_605_NAME AS PRODUCT_TYPE, \n" +
                "        c6i.code_605p_name AS PROBLEM_TYPE,\n" +
                "        '605' AS KODE, \n" +
                "        c.CODE_605 AS RPOD, \n" +
                "        c.CODE_605_PROBLEM AS PROB \n" +
                "    FROM complaint c\n" +
                "    JOIN CODE_605 c6 ON c.CODE_605 = c6.CODE_605_CODE\n" +
                "    JOIN CODE_605_PROBLEM c6i ON c.CODE_605_PROBLEM = c6i.code_605p_code\n" +
                "    WHERE trim(c.CODE_605) IS NOT NULL \n" +
                "      AND trim(c.CODE_605_PROBLEM) IS NOT NULL \n" +
                "      AND c.is_deleted = 'N'\n" +
                "    GROUP BY c.CODE_605, c.CODE_605_PROBLEM, c6.CODE_605_NAME, c6i.code_605p_name\n" +
                ") RESULT \n" +
                "ORDER BY KODE, RPOD, PROB").list();
//            List<Object[]> rows = currentSession().createSQLQuery("select PRODUCT_TYPE,PROBLEM_TYPE,FREK from \n" +
//"                    (\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'601-'||c6.CODE_601_NAME AS PRODUCT_TYPE, c6i.code_601p_name AS PROBLEM_TYPE,'601' AS KODE, c.CODE_601 as RPOD, c.CODE_601_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_601 c6, CODE_601_PROBLEM c6i\n" +
//"                        where c.CODE_601=c6.CODE_601_CODE and c.CODE_601_PROBLEM=c6i.code_601p_code and trim(c.CODE_601) is not null and trim(c.CODE_601_PROBLEM) is not null and c.is_deleted='N'\n" +
//"                        group by c.CODE_601, c.CODE_601_PROBLEM,c6.CODE_601_NAME, c6i.code_601p_name,'601'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'602-'||c6.CODE_602_NAME AS PRODUCT_TYPE, c6i.code_602p_name AS PROBLEM_TYPE,'602' AS KODE, c.CODE_602 as RPOD, c.CODE_602_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_602 c6, CODE_602_PROBLEM c6i\n" +
//"                        where c.CODE_602=c6.CODE_602_CODE and c.CODE_602_PROBLEM=c6i.code_602p_code and trim(c.CODE_602) is not null and trim(c.CODE_602_PROBLEM) is not null and c.is_deleted='N'\n" +
//"                        group by c.CODE_602, c.CODE_602_PROBLEM,c6.CODE_602_NAME, c6i.code_602p_name,'602'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'603-'||c6.CODE_603_NAME AS PRODUCT_TYPE, c6i.code_603p_name AS PROBLEM_TYPE,'603' AS KODE, c.CODE_603 as RPOD, c.CODE_603_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_603 c6, CODE_603_PROBLEM c6i\n" +
//"                        where c.CODE_603=c6.CODE_603_CODE and c.CODE_603_PROBLEM=c6i.code_603p_code and trim(c.CODE_603) is not null and trim(c.CODE_603_PROBLEM) is not null and c.is_deleted='N'\n" +
//"                        group by c.CODE_603, c.CODE_603_PROBLEM,c6.CODE_603_NAME, c6i.code_603p_name,'603'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'604-'||c6.CODE_604_NAME AS PRODUCT_TYPE, c6i.code_604p_name AS PROBLEM_TYPE,'604' AS KODE, c.CODE_604 as RPOD, c.CODE_604_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_604 c6, CODE_604_PROBLEM c6i\n" +
//"                        where c.CODE_604=c6.CODE_604_CODE and c.CODE_604_PROBLEM=c6i.code_604p_code and trim(c.CODE_604) is not null and trim(c.CODE_604_PROBLEM) is not null and c.is_deleted='N'\n" +
//"                        group by c.CODE_604, c.CODE_604_PROBLEM,c6.CODE_604_NAME, c6i.code_604p_name,'604'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'605-'||c6.CODE_605_NAME AS PRODUCT_TYPE, c6i.code_605p_name AS PROBLEM_TYPE,'605' AS KODE, c.CODE_605 as RPOD, c.CODE_605_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_605 c6, CODE_605_PROBLEM c6i\n" +
//"                        where c.CODE_605=c6.CODE_605_CODE and c.CODE_605_PROBLEM=c6i.code_605p_code and trim(c.CODE_605) is not null and trim(c.CODE_605_PROBLEM) is not null and c.is_deleted='N'\n" +
//"                        group by c.CODE_605, c.CODE_605_PROBLEM,c6.CODE_605_NAME, c6i.code_605p_name,'605'\n" +
//"                    ) RESULT ORDER BY KODE,RPOD,PROB").list();
            
            for (Object[] row : rows) {
                String product = (String) row[0];
                String problem = (String) row[1];
                int frek = ((Number) row[2]).intValue();

                result.add(new CompReport(product, problem, frek, "", "", 0));
            }
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return result;
    }

    @Override
    public List<CompReport> lstCompRptByFilter(Date sDate, Date eDate, String keyString) {
        List<CompReport> result = new ArrayList<CompReport>();
        try {
            List<Object[]> rows = currentSession().createSQLQuery("SELECT \n" +
                "    product_type,\n" +
                "    problem_type,\n" +
                "    frek \n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        COUNT(c.request_id) AS frek,\n" +
                "        '601-' || c6.code_601_name AS product_type, \n" +
                "        c6i.code_601p_name AS problem_type,\n" +
                "        '601' AS kode, \n" +
                "        c.code_601 AS rpod, \n" +
                "        c.code_601_problem AS prob \n" +
                "    FROM complaint c\n" +
                "    JOIN code_601 c6 ON c.code_601 = c6.code_601_code\n" +
                "    JOIN code_601_problem c6i ON c.code_601_problem = c6i.code_601p_code\n" +
                "    WHERE NULLIF(TRIM(c.code_601), '') IS NOT NULL \n" +
                "      AND NULLIF(TRIM(c.code_601_problem), '') IS NOT NULL \n" +
                "      AND c.is_deleted = 'N' \n" +
                "      AND CAST(c.received_date AS TIMESTAMP) BETWEEN :sDate AND :eDate \n" +
                "    GROUP BY c.code_601, c.code_601_problem, c6.code_601_name, c6i.code_601p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.request_id) AS frek,\n" +
                "        '602-' || c6.code_602_name AS product_type, \n" +
                "        c6i.code_602p_name AS problem_type,\n" +
                "        '602' AS kode, \n" +
                "        c.code_602 AS rpod, \n" +
                "        c.code_602_problem AS prob \n" +
                "    FROM complaint c\n" +
                "    JOIN code_602 c6 ON c.code_602 = c6.code_602_code\n" +
                "    JOIN code_602_problem c6i ON c.code_602_problem = c6i.code_602p_code\n" +
                "    WHERE NULLIF(TRIM(c.code_602), '') IS NOT NULL \n" +
                "      AND NULLIF(TRIM(c.code_602_problem), '') IS NOT NULL \n" +
                "      AND c.is_deleted = 'N' \n" +
                "      AND CAST(c.received_date AS TIMESTAMP) BETWEEN :sDate AND :eDate \n" +
                "    GROUP BY c.code_602, c.code_602_problem, c6.code_602_name, c6i.code_602p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.request_id) AS frek,\n" +
                "        '603-' || c6.code_603_name AS product_type, \n" +
                "        c6i.code_603p_name AS problem_type,\n" +
                "        '603' AS kode, \n" +
                "        c.code_603 AS rpod, \n" +
                "        c.code_603_problem AS prob \n" +
                "    FROM complaint c\n" +
                "    JOIN code_603 c6 ON c.code_603 = c6.code_603_code\n" +
                "    JOIN code_603_problem c6i ON c.code_603_problem = c6i.code_603p_code\n" +
                "    WHERE NULLIF(TRIM(c.code_603), '') IS NOT NULL \n" +
                "      AND NULLIF(TRIM(c.code_603_problem), '') IS NOT NULL \n" +
                "      AND c.is_deleted = 'N' \n" +
                "      AND CAST(c.received_date AS TIMESTAMP) BETWEEN :sDate AND :eDate \n" +
                "    GROUP BY c.code_603, c.code_603_problem, c6.code_603_name, c6i.code_603p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.request_id) AS frek,\n" +
                "        '604-' || c6.code_604_name AS product_type, \n" +
                "        c6i.code_604p_name AS problem_type,\n" +
                "        '604' AS kode, \n" +
                "        c.code_604 AS rpod, \n" +
                "        c.code_604_problem AS prob \n" +
                "    FROM complaint c\n" +
                "    JOIN code_604 c6 ON c.code_604 = c6.code_604_code\n" +
                "    JOIN code_604_problem c6i ON c.code_604_problem = c6i.code_604p_code\n" +
                "    WHERE NULLIF(TRIM(c.code_604), '') IS NOT NULL \n" +
                "      AND NULLIF(TRIM(c.code_604_problem), '') IS NOT NULL \n" +
                "      AND c.is_deleted = 'N' \n" +
                "      AND CAST(c.received_date AS TIMESTAMP) BETWEEN :sDate AND :eDate \n" +
                "    GROUP BY c.code_604, c.code_604_problem, c6.code_604_name, c6i.code_604p_name\n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        COUNT(c.request_id) AS frek,\n" +
                "        '605-' || c6.code_605_name AS product_type, \n" +
                "        c6i.code_605p_name AS problem_type,\n" +
                "        '605' AS kode, \n" +
                "        c.code_605 AS rpod, \n" +
                "        c.code_605_problem AS prob \n" +
                "    FROM complaint c\n" +
                "    JOIN code_605 c6 ON c.code_605 = c6.code_605_code\n" +
                "    JOIN code_605_problem c6i ON c.code_605_problem = c6i.code_605p_code\n" +
                "    WHERE NULLIF(TRIM(c.code_605), '') IS NOT NULL \n" +
                "      AND NULLIF(TRIM(c.code_605_problem), '') IS NOT NULL \n" +
                "      AND c.is_deleted = 'N' \n" +
                "      AND CAST(c.received_date AS TIMESTAMP) BETWEEN :sDate AND :eDate \n" +
                "    GROUP BY c.code_605, c.code_605_problem, c6.code_605_name, c6i.code_605p_name\n" +
                ") result \n" +
                "ORDER BY kode, rpod, prob").setParameter("sDate", sDate).setParameter("eDate", eDate).list();
//            List<Object[]> rows = currentSession().createSQLQuery("select PRODUCT_TYPE,PROBLEM_TYPE,FREK from \n" +
//"                    (\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'601-'||c6.PRODUCT_NAME AS PRODUCT_TYPE, c6i.PRODUCTINFO_NAME AS PROBLEM_TYPE,'601' AS KODE, c.CODE_601 as RPOD, c.CODE_601_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_601 c6, CODE_601_PROBLEM c6i\n" +
//"                        where c.CODE_601=c6.PRODUCT_CODE and c.CODE_601_PROBLEM=c6i.PRODUCTINFO_CODE and trim(c.CODE_601) is not null and trim(c.CODE_601_PROBLEM) is not null and c.is_deleted='N' and TRUNC(c.RECEIVED_DATE) between :sDate and :eDate \n" +
//"                        group by c.CODE_601, c.CODE_601_PROBLEM,c6.PRODUCT_NAME, c6i.PRODUCTINFO_NAME,'601'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'602-'||c6.PRODUCT_NAME AS PRODUCT_TYPE, c6i.PRODUCTINFO_NAME AS PROBLEM_TYPE,'602' AS KODE, c.CODE_602 as RPOD, c.CODE_602_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_602 c6, CODE_602_PROBLEM c6i\n" +
//"                        where c.CODE_602=c6.PRODUCT_CODE and c.CODE_602_PROBLEM=c6i.PRODUCTINFO_CODE and trim(CODE_602) is not null and trim(CODE_602_PROBLEM) is not null and c.is_deleted='N' and TRUNC(c.RECEIVED_DATE) between :sDate and :eDate \n" +
//"                        group by c.CODE_602, c.CODE_602_PROBLEM,c6.PRODUCT_NAME, c6i.PRODUCTINFO_NAME,'602'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'603-'||c6.PRODUCT_NAME AS PRODUCT_TYPE, c6i.PRODUCTINFO_NAME AS PROBLEM_TYPE,'603' AS KODE, c.CODE_603 as RPOD, c.CODE_603_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_603 c6, CODE_603_PROBLEM c6i\n" +
//"                        where c.CODE_603=c6.PRODUCT_CODE and c.CODE_603_PROBLEM=c6i.PRODUCTINFO_CODE and trim(CODE_603) is not null and trim(CODE_603_PROBLEM) is not null and c.is_deleted='N' and TRUNC(c.RECEIVED_DATE) between :sDate and :eDate \n" +
//"                        group by c.CODE_603, c.CODE_603_PROBLEM,c6.PRODUCT_NAME, c6i.PRODUCTINFO_NAME, '603'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'604-'||c6.PRODUCT_NAME AS PRODUCT_TYPE, c6i.PRODUCTINFO_NAME AS PROBLEM_TYPE,'604' AS KODE, c.CODE_604 as RPOD, c.CODE_604_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_604 c6, CODE_604_PROBLEM c6i\n" +
//"                        where c.CODE_604=c6.PRODUCT_CODE and c.CODE_604_PROBLEM=c6i.PRODUCTINFO_CODE and trim(CODE_604) is not null and trim(CODE_604_PROBLEM) is not null and c.is_deleted='N' and TRUNC(c.RECEIVED_DATE) between :sDate and :eDate \n" +
//"                        group by c.CODE_604, c.CODE_604_PROBLEM,c6.PRODUCT_NAME, c6i.PRODUCTINFO_NAME, '604'\n" +
//"                        union\n" +
//"                        select count(c.REQUEST_ID) AS FREK,'605-'||c6.PRODUCT_NAME AS PRODUCT_TYPE, c6i.PRODUCTINFO_NAME AS PROBLEM_TYPE,'605' AS KODE, c.CODE_605 as RPOD, c.CODE_605_PROBLEM as PROB \n" +
//"                        from complaint c, CODE_605 c6, CODE_605_PROBLEM c6i\n" +
//"                        where c.CODE_605=c6.PRODUCT_CODE and c.CODE_605_PROBLEM=c6i.PRODUCTINFO_CODE and trim(CODE_605) is not null and trim(CODE_605_PROBLEM) is not null and c.is_deleted='N' and TRUNC(c.RECEIVED_DATE) between :sDate and :eDate \n" +
//"                        group by c.CODE_605, c.CODE_605_PROBLEM,c6.PRODUCT_NAME, c6i.PRODUCTINFO_NAME, '605'\n" +
//"                    ) RESULT ORDER BY KODE,RPOD,PROB").setParameter("sDate", sDate).setParameter("eDate", eDate).list();
            
            for (Object[] row : rows) {
                String product = (String) row[0];
                String problem = (String) row[1];
                int frek = ((Number) row[2]).intValue();

                result.add(new CompReport(product, problem, frek, "", "", 0));
            }
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return result;
    }

    @Override
    public List<CompReport> lstCompResRpt() {
        List<CompReport> result = new ArrayList<CompReport>();
        try {
            List<Object[]> rows = currentSession().createSQLQuery("SELECT BRANCH_CODE, KET, FREK, RATA FROM\n" +
            "(\n" +
            "    select BRANCH_CODE, 'Pending' AS KET, COUNT(REQUEST_ID) AS FREK, 0 AS RATA from complaint where is_deleted='N' AND COMPLETION_DATE IS NULL GROUP BY BRANCH_CODE \n" +
            "    UNION\n" +
            "    select BRANCH_CODE, 'Resolved' AS KET, COUNT(REQUEST_ID) AS FREK, AVG(AGING) AS RATA from complaint where is_deleted='N' AND COMPLETION_DATE IS NOT NULL GROUP BY BRANCH_CODE\n" +
            ")\n" +
            "ORDER BY BRANCH_CODE").list();
            
            for (Object[] row : rows) {
                String branch = (String) row[0];
                String status = (String) row[1];
                int frek = ((Number) row[2]).intValue();
                int rata = ((Number) row[3]).intValue();

                result.add(new CompReport("", "", frek, branch, status, rata));
            }
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return result;
    }

    @Override
    public List<CompReport> lstCompResRptByFilter(Date sDate, Date eDate, String keyString) {
        List<CompReport> result = new ArrayList<CompReport>();
        try {
            List<Object[]> rows = currentSession().createSQLQuery("SELECT \n" +
                "    branch_code, \n" +
                "    ket, \n" +
                "    frek, \n" +
                "    rata \n" +
                "FROM (\n" +
                "    SELECT \n" +
                "        branch_code, \n" +
                "        'Pending' AS ket, \n" +
                "        COUNT(request_id) AS frek, \n" +
                "        0 AS rata \n" +
                "    FROM complaint \n" +
                "    WHERE is_deleted = 'N' \n" +
                "      AND completion_date IS NULL \n" +
                "      AND DATE_TRUNC('day', received_date) BETWEEN :sDate AND :eDate\n" +
                "    GROUP BY branch_code \n" +
                "\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT \n" +
                "        branch_code, \n" +
                "        'Resolved' AS ket, \n" +
                "        COUNT(request_id) AS frek, \n" +
                "        AVG(aging) AS rata \n" +
                "    FROM complaint \n" +
                "    WHERE is_deleted = 'N' \n" +
                "      AND completion_date IS NOT NULL \n" +
                "      AND DATE_TRUNC('day', received_date) BETWEEN :sDate AND :eDate\n" +
                "    GROUP BY branch_code\n" +
                ") result\n" +
                "ORDER BY branch_code").setParameter("sDate", sDate).setParameter("eDate", eDate).list();
            
            for (Object[] row : rows) {
                String branch = (String) row[0];
                String status = (String) row[1];
                int frek = ((Number) row[2]).intValue();
                int rata = ((Number) row[3]).intValue();

                result.add(new CompReport("", "", frek, branch, status, rata));
            }
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return result;
    }

    @Override
    public List<CompOtor> lstCompOtor(String userid) {
        List<CompOtor> hasil = currentSession().createSQLQuery("SELECT REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
        "channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
        "RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
        "CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
        "CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
        "CODE_605, CODE_605_PROBLEM, \n" +
        "dispute_filepath, settlement_filepath, extension_filepath, \n" +
        "USER_INPUT, USER_OTOR, AGING, is_deleted, ACTION,create_at,comp_type,req_type,address,update_at,incident_checkbox,status \n" +
        "FROM complaint_otor WHERE USER_INPUT <> :USER_INPUT ORDER BY RECEIVED_DATE DESC").addEntity(CompOtor.class).setParameter("USER_INPUT", userid).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public String reqInsCompData(CompOtor compotor) {
        String outMsg="";
        int hasilCek = Integer.parseInt(currentSession().createSQLQuery("select count(REQUEST_ID) hasilCek from complaint where REQUEST_ID=:reqid").addScalar("hasilCek")
                .setParameter("reqid", compotor.getRequest_id()).list().get(0).toString());
        int hasilCek2 = Integer.parseInt(currentSession().createSQLQuery("select count(REQUEST_ID) hasilCek from complaint_otor where REQUEST_ID=:reqid").addScalar("hasilCek")
                .setParameter("reqid", compotor.getRequest_id()).list().get(0).toString());
//        int cekMstBranch = Integer.parseInt(currentSession().createSQLQuery("select count(BRANCH_CODE) hasilCek from BRANCH where BRANCH_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getBranch_code().getBranch_code()).list().get(0).toString());
//        int cekMstChannel = Integer.parseInt(currentSession().createSQLQuery("select count(CHANNELS_CODE) hasilCek from CHANNEL where CHANNELS_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getChannel_code().getChannel_code()).list().get(0).toString());
//        int cekMstCity = Integer.parseInt(currentSession().createSQLQuery("select count(CITY_CODE) hasilCek from CITY where CITY_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCity_code().getCity_code()).list().get(0).toString());
//        int cekMstCode601 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_601 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_601().getCode_601_code()).list().get(0).toString());
//        int cekMstCode602 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_602 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_602().getCode_601_code()).list().get(0).toString());
//        int cekMstCode603 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_603 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_603().getCode_601_code()).list().get(0).toString());
//        int cekMstCode604 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_604 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_604().getCode_601_code()).list().get(0).toString());
//        int cekMstCode605 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_605 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_605().getCode_601_code()).list().get(0).toString());
//        int cekMstCode601I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_601_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_601_problem().getCode_601p_code()).list().get(0).toString());
//        int cekMstCode602I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_602_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_602_problem().getCode_601p_code()).list().get(0).toString());
//        int cekMstCode603I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_603_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_603_problem().getCode_601p_code()).list().get(0).toString());
//        int cekMstCode604I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_604_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_604_problem().getCode_601p_code()).list().get(0).toString());
//        int cekMstCode605I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_605_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCode_605_problem().getCode_601p_code()).list().get(0).toString());
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'ADD','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'ADD%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        
        if(hasilCek>0)
        {
            outMsg= "Request ID "+compotor.getRequest_id()+" sudah digunakan pada data existing";
        }
        else if(hasilCek2>0)
        {
            outMsg= "Request ID "+compotor.getRequest_id()+" sudah direquest";
        }
//        else if(cekMstBranch>0||cekMstCity>0||cekMstChannel>0||cekMstCode601>0||cekMstCode602>0||cekMstCode603>0||cekMstCode604>0||cekMstCode605>0
//                ||cekMstCode601I>0||cekMstCode602I>0||cekMstCode603I>0||cekMstCode604I>0||cekMstCode605I>0)
//        {
//            if(cekMstBranch>0)
//                outMsg="Branch "+compotor.getBranch_code().getBRANCH_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCity>0)
//                outMsg+="City "+compotor.getCity_code().getCITY_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstChannel>0)
//                outMsg+="Channel "+compotor.getChannel_code().getCHANNEL_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode601>0)
//                outMsg+="Code 601 "+compotor.getCode_601().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode602>0)
//                outMsg+="Code 602 "+compotor.getCode_602().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode603>0)
//                outMsg+="Code 603 "+compotor.getCode_603().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode604>0)
//                outMsg+="Code 604 "+compotor.getCode_604().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode605>0)
//                outMsg+="Code 605 "+compotor.getCode_605().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode601I>0)
//                outMsg+="Code 601 Problem "+compotor.getCode_601_problem().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode602I>0)
//                outMsg+="Code 602 Problem "+compotor.getCode_602_problem().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode603I>0)
//                outMsg+="Code 603 Problem "+compotor.getCode_603_problem().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode604I>0)
//                outMsg+="Code 604 Problem "+compotor.getCode_604_problem().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode605I>0)
//                outMsg+="Code 605 Problem "+compotor.getCode_605_problem().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//        }
//        else if(hasilCek<=0&&hasilCek2<=0&&cekMstBranch<=0&&cekMstCity<=0&&cekMstChannel<=0&&cekMstCode601<=0&&cekMstCode602<=0&&cekMstCode603<=0&&cekMstCode604<=0&&cekMstCode605<=0
//                &&cekMstCode601I<=0&&cekMstCode602I<=0&&cekMstCode603I<=0&&cekMstCode604I<=0&&cekMstCode605I<=0)
        else if(hasilCek<=0&&hasilCek2<=0)
        {
//            try {
                currentSession().createSQLQuery("INSERT INTO complaint_otor (\n" +
                "  REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION,\n" +
                "  channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION,\n" +
                "  RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE,\n" +
                "  CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM,\n" +
                "  CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM,\n" +
                "  CODE_605, CODE_605_PROBLEM,\n" +
                "  dispute_filepath, settlement_filepath, extension_filepath,\n" +
                "  USER_INPUT, USER_OTOR, AGING, is_deleted, ACTION\n" +
                ") VALUES (\n" +
                "  :REQUEST_ID, :SETTLEMENT_NUMBER, :EXTENSION_NUMBER, :CUSTOMER_NAME, :LOCATION,\n" +
                "  :CHANNEL, :CITY_CODE, :BRANCH_CODE, :AMOUNT, :ATM_CARD_NUMBER, :DESCRIPTION,\n" +
                "  :RECEIVED_DATE, :INCIDENT_DATE, CAST(:COMPLETION_DATE AS timestamp),\n" +
                "  :CODE_601, :CODE_601_PROBLEM, :CODE_602, :CODE_602_PROBLEM,\n" +
                "  :CODE_603, :CODE_603_PROBLEM, :CODE_604, :CODE_604_PROBLEM,\n" +
                "  :CODE_605, :CODE_605_PROBLEM,\n" +
                "  :dispute_filepath, :settlement_filepath, :extension_filepath,\n" +
                "  :USER_INPUT, :USER_OTOR, :AGING, :is_deleted, 'Add')").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .setParameter("SETTLEMENT_NUMBER", compotor.getSettlement_number().trim()).setParameter("EXTENSION_NUMBER", compotor.getExtension_number().trim())
                    .setParameter("CUSTOMER_NAME", compotor.getCustomer_name().trim()).setParameter("LOCATION", compotor.getLocation().trim())
                    .setParameter("CHANNEL", compotor.getChannel_code().getChannel_code()).setParameter("CITY_CODE", compotor.getCity_code().getCity_code()).setParameter("BRANCH_CODE", compotor.getBranch_code().getBranch_code())
                    .setParameter("AMOUNT", compotor.getAmount()).setParameter("ATM_CARD_NUMBER", compotor.getAtm_card_number().trim()).setParameter("DESCRIPTION", compotor.getDescription().trim())
                    .setParameter("RECEIVED_DATE", compotor.getReceived_date(),StandardBasicTypes.TIMESTAMP).setParameter("INCIDENT_DATE", compotor.getIncident_date(),StandardBasicTypes.TIMESTAMP).setParameter("COMPLETION_DATE", compotor.getCompletion_date(),StandardBasicTypes.TIMESTAMP)
                    .setParameter("CODE_601", compotor.getCode_601().getCode_601_code()).setParameter("CODE_601_PROBLEM", compotor.getCode_601_problem().getCode_601p_code())
                    .setParameter("CODE_602", compotor.getCode_602() != null ? compotor.getCode_602().getCode_602_code(): null,StandardBasicTypes.STRING).setParameter("CODE_602_PROBLEM", compotor.getCode_602_problem()!= null ? compotor.getCode_602_problem().getCode_602p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("CODE_603", compotor.getCode_603() != null ? compotor.getCode_603().getCode_603_code(): null,StandardBasicTypes.STRING).setParameter("CODE_603_PROBLEM", compotor.getCode_603_problem()!= null ? compotor.getCode_603_problem().getCode_603p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("CODE_604", compotor.getCode_604() != null ? compotor.getCode_604().getCode_604_code(): null,StandardBasicTypes.STRING).setParameter("CODE_604_PROBLEM", compotor.getCode_604_problem()!= null ? compotor.getCode_604_problem().getCode_604p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("CODE_605", compotor.getCode_605() != null ? compotor.getCode_605().getCode_605_code(): null,StandardBasicTypes.STRING).setParameter("CODE_605_PROBLEM", compotor.getCode_605_problem()!= null ? compotor.getCode_605_problem().getCode_605p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("dispute_filepath", compotor.getDispute_filepath()).setParameter("settlement_filepath", compotor.getSettlement_filepath())
                    .setParameter("extension_filepath", compotor.getExtension_filepath()).setParameter("USER_INPUT", compotor.getUser_input())
                    .setParameter("USER_OTOR", compotor.getUser_otor())
                    .setParameter("AGING", compotor.getAging()).setParameter("is_deleted", compotor.getIs_deleted()).executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "ADD"+idxLogID).setParameter("USER_ID", compotor.getUser_input())
                        .setParameter("ACTIVITY_TYPE", "REQ_ADD_COMPLAINT").setParameter("REQUEST_ID", compotor.getRequest_id())
                        .setParameter("DESCRIPTION", "New complaint request added for RequestID "+compotor.getRequest_id()+" by user "+compotor.getUser_input()+".").setParameter("CHANGED_FIELDS", "-")
                        .setParameter("DESC_FILE", compotor.getDispute_filepath().substring(compotor.getDispute_filepath().lastIndexOf("/")+1)+
                                compotor.getSettlement_filepath().substring(compotor.getSettlement_filepath().lastIndexOf("/")+1)+
                                compotor.getExtension_filepath().substring(compotor.getExtension_filepath().lastIndexOf("/")+1))
                        .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
                
                currentSession().flush();
                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" berhasil request input.";
//            } catch (Exception e) {
//                try {
//                    throw new Exception(e.getCause().getMessage());
//                } catch (Exception ex) {
//                    Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                System.out.println("REQIDIDDDDDDDDDDDDDDDDDD "+compotor.getRequest_id());
//                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" gagal request input.\n Penyebab errornya: "+e.getCause().getMessage();
//            }
        }
        return outMsg;
    }

    @Override
    public String reqUpdtCompData(CompOtor compotor, String logEdit) {
        String outMsg="";
        int hasilCek2 = Integer.parseInt(currentSession().createSQLQuery("select count(REQUEST_ID) hasilCek from COMPLAINT_OTOR where REQUEST_ID=:reqid").addScalar("hasilCek")
                .setParameter("reqid", compotor.getRequest_id()).list().get(0).toString());
//        int cekMstBranch = Integer.parseInt(currentSession().createSQLQuery("select count(BRANCH_CODE) hasilCek from BRANCH where BRANCH_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getBRANCH_CODE().getBRANCH_CODE()).list().get(0).toString());
//        int cekMstChannel = Integer.parseInt(currentSession().createSQLQuery("select count(CHANNELS_CODE) hasilCek from CHANNEL where CHANNELS_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCHANNEL().getCHANNELS_CODE()).list().get(0).toString());
//        int cekMstCity = Integer.parseInt(currentSession().createSQLQuery("select count(CITY_CODE) hasilCek from CITY where CITY_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCITY_CODE().getCITY_CODE()).list().get(0).toString());
//        int cekMstCode601 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_601 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_601_PRODUCT().getPRODUCT_CODE()).list().get(0).toString());
//        int cekMstCode602 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_602 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_602_PRODUCT().getPRODUCT_CODE()).list().get(0).toString());
//        int cekMstCode603 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_603 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_603_PRODUCT().getPRODUCT_CODE()).list().get(0).toString());
//        int cekMstCode604 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_604 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_604_PRODUCT().getPRODUCT_CODE()).list().get(0).toString());
//        int cekMstCode605 = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCT_CODE) hasilCek from CODE_605 where PRODUCT_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_605_PRODUCT().getPRODUCT_CODE()).list().get(0).toString());
//        int cekMstCode601I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_601_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_601_PROBLEM().getPRODUCTINFO_CODE()).list().get(0).toString());
//        int cekMstCode602I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_602_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_602_PROBLEM().getPRODUCTINFO_CODE()).list().get(0).toString());
//        int cekMstCode603I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_603_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_603_PROBLEM().getPRODUCTINFO_CODE()).list().get(0).toString());
//        int cekMstCode604I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_604_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_604_PROBLEM().getPRODUCTINFO_CODE()).list().get(0).toString());
//        int cekMstCode605I = Integer.parseInt(currentSession().createSQLQuery("select count(PRODUCTINFO_CODE) hasilCek from CODE_605_PROBLEM where PRODUCTINFO_CODE=:code AND is_deleted='Y'").addScalar("hasilCek",Hibernate.INTEGER)
//                .setParameter("code", compotor.getCODE_605_PROBLEM().getPRODUCTINFO_CODE()).list().get(0).toString());
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'EDIT','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'EDIT%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        if(hasilCek2>0)
        {
            outMsg= "Request ID "+compotor.getRequest_id()+" sudah direquest update sebelumnya";
        }
//        else if(cekMstBranch>0||cekMstCity>0||cekMstChannel>0||cekMstCode601>0||cekMstCode602>0||cekMstCode603>0||cekMstCode604>0||cekMstCode605>0
//                ||cekMstCode601I>0||cekMstCode602I>0||cekMstCode603I>0||cekMstCode604I>0||cekMstCode605I>0)
//        {
//            if(cekMstBranch>0)
//                outMsg="Branch "+compotor.getBRANCH_CODE().getBRANCH_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCity>0)
//                outMsg+="City "+compotor.getCITY_CODE().getCITY_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstChannel>0)
//                outMsg+="Channel "+compotor.getCHANNEL().getCHANNEL_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode601>0)
//                outMsg+="Code 601 "+compotor.getCODE_601_PRODUCT().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode602>0)
//                outMsg+="Code 602 "+compotor.getCODE_602_PRODUCT().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode603>0)
//                outMsg+="Code 603 "+compotor.getCODE_603_PRODUCT().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode604>0)
//                outMsg+="Code 604 "+compotor.getCODE_604_PRODUCT().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode605>0)
//                outMsg+="Code 605 "+compotor.getCODE_605_PRODUCT().getPRODUCT_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode601I>0)
//                outMsg+="Code 601 Problem "+compotor.getCODE_601_PROBLEM().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode602I>0)
//                outMsg+="Code 602 Problem "+compotor.getCODE_602_PROBLEM().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode603I>0)
//                outMsg+="Code 603 Problem "+compotor.getCODE_603_PROBLEM().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode604I>0)
//                outMsg+="Code 604 Problem "+compotor.getCODE_604_PROBLEM().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//            if(cekMstCode605I>0)
//                outMsg+="Code 605 Problem "+compotor.getCODE_605_PROBLEM().getPRODUCTINFO_NAME()+" tidak dapat dipilih karena sudah nonaktif \n";
//        }
//        else if(hasilCek2<=0&&cekMstBranch<=0&&cekMstCity<=0&&cekMstChannel<=0&&cekMstCode601<=0&&cekMstCode602<=0&&cekMstCode603<=0&&cekMstCode604<=0&&cekMstCode605<=0
//                &&cekMstCode601I<=0&&cekMstCode602I<=0&&cekMstCode603I<=0&&cekMstCode604I<=0&&cekMstCode605I<=0)
        else if(hasilCek2<=0)
        {
//            try {
                currentSession().createSQLQuery("INSERT INTO COMPLAINT_OTOR (\n" +
                "  REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION,\n" +
                "  channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION,\n" +
                "  RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE,\n" +
                "  code_601, CODE_601_PROBLEM, code_602, CODE_602_PROBLEM,\n" +
                "  code_603, CODE_603_PROBLEM, code_604, CODE_604_PROBLEM,\n" +
                "  code_605, CODE_605_PROBLEM,\n" +
                "  dispute_filepath, settlement_filepath, extension_filepath,\n" +
                "  USER_INPUT, USER_OTOR, AGING, is_deleted, ACTION\n" +
                ") VALUES (\n" +
                "  :REQUEST_ID, :SETTLEMENT_NUMBER, :EXTENSION_NUMBER, :CUSTOMER_NAME, :LOCATION,\n" +
                "  :CHANNEL, :CITY_CODE, :BRANCH_CODE, :AMOUNT, :ATM_CARD_NUMBER, :DESCRIPTION,\n" +
                "  :RECEIVED_DATE, :INCIDENT_DATE, CAST(:COMPLETION_DATE AS timestamp),\n" +
                "  :code_601, :CODE_601_PROBLEM, :code_602, :CODE_602_PROBLEM,\n" +
                "  :code_603, :CODE_603_PROBLEM, :code_604, :CODE_604_PROBLEM,\n" +
                "  :code_605, :CODE_605_PROBLEM,\n" +
                "  :dispute_filepath, :settlement_filepath, :extension_filepath,\n" +
                "  :USER_INPUT, :USER_OTOR, :AGING, :is_deleted, 'Update')").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .setParameter("SETTLEMENT_NUMBER", compotor.getSettlement_number().trim()).setParameter("EXTENSION_NUMBER", compotor.getExtension_number().trim())
                    .setParameter("CUSTOMER_NAME", compotor.getCustomer_name().trim()).setParameter("LOCATION", compotor.getLocation().trim())
                    .setParameter("CHANNEL", compotor.getChannel_code().getChannel_code()).setParameter("CITY_CODE", compotor.getCity_code().getCity_code()).setParameter("BRANCH_CODE", compotor.getBranch_code().getBranch_code())
                    .setParameter("AMOUNT", compotor.getAmount()).setParameter("ATM_CARD_NUMBER", compotor.getAtm_card_number().trim()).setParameter("DESCRIPTION", compotor.getDescription().trim())
                    .setParameter("RECEIVED_DATE", compotor.getReceived_date()).setParameter("INCIDENT_DATE", compotor.getIncident_date()).setParameter("COMPLETION_DATE", compotor.getCompletion_date(),StandardBasicTypes.TIMESTAMP)
                    .setParameter("code_601", compotor.getCode_601().getCode_601_code()).setParameter("CODE_601_PROBLEM", compotor.getCode_601_problem().getCode_601p_code())
                    .setParameter("code_602", compotor.getCode_602() != null ? compotor.getCode_602().getCode_602_code(): null,StandardBasicTypes.STRING).setParameter("CODE_602_PROBLEM", compotor.getCode_602_problem()!= null ? compotor.getCode_602_problem().getCode_602p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("code_603", compotor.getCode_603() != null ? compotor.getCode_603().getCode_603_code(): null,StandardBasicTypes.STRING).setParameter("CODE_603_PROBLEM", compotor.getCode_603_problem()!= null ? compotor.getCode_603_problem().getCode_603p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("code_604", compotor.getCode_604() != null ? compotor.getCode_604().getCode_604_code(): null,StandardBasicTypes.STRING).setParameter("CODE_604_PROBLEM", compotor.getCode_604_problem()!= null ? compotor.getCode_604_problem().getCode_604p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("code_605", compotor.getCode_605() != null ? compotor.getCode_605().getCode_605_code(): null,StandardBasicTypes.STRING).setParameter("CODE_605_PROBLEM", compotor.getCode_605_problem()!= null ? compotor.getCode_605_problem().getCode_605p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("dispute_filepath", compotor.getDispute_filepath()).setParameter("settlement_filepath", compotor.getSettlement_filepath())
                    .setParameter("extension_filepath", compotor.getExtension_filepath()).setParameter("USER_INPUT", compotor.getUser_input())
                    .setParameter("USER_OTOR", compotor.getUser_otor())
                    .setParameter("AGING", compotor.getAging()).setParameter("is_deleted", compotor.getIs_deleted()).executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "EDIT"+idxLogID).setParameter("USER_ID", compotor.getUser_input())
                        .setParameter("ACTIVITY_TYPE", "REQ_UPDATE_COMPLAINT").setParameter("REQUEST_ID", compotor.getRequest_id())
                        .setParameter("DESCRIPTION", "Complaint "+compotor.getRequest_id()+" request updated by user "+compotor.getUser_input()+": Changed "+logEdit).setParameter("CHANGED_FIELDS", logEdit)
                        .setParameter("DESC_FILE", logEdit.contains("Dispute file changed") ? compotor.getDispute_filepath().substring(compotor.getDispute_filepath().lastIndexOf("/")+1)+" "
                                : logEdit.contains("Settlement file changed")||logEdit.contains("Added new Settlement file") ? compotor.getSettlement_filepath().substring(compotor.getSettlement_filepath().lastIndexOf("/")+1)+" "
                                : logEdit.contains("Extension file changed")||logEdit.contains("Added new Extension file") ? compotor.getExtension_filepath().substring(compotor.getExtension_filepath().lastIndexOf("/")+1)
                                : "-")
                        .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
                
                currentSession().flush();
                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" berhasil request update.";
//            } catch (Exception e) {
//                try {
//                    throw new Exception(e.getCause().getMessage());
//                } catch (Exception ex) {
//                    Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" gagal request update.\n Penyebab errornya: "+e.getCause().getMessage();
//            }
        }
        return outMsg;
    }

    @Override
    public String reqDelCompData(CompOtor compotor) {
        String outMsg="";
        int hasilCek2 = Integer.parseInt(currentSession().createSQLQuery("select count(REQUEST_ID) hasilCek from COMPLAINT_OTOR where REQUEST_ID=:reqid").addScalar("hasilCek")
                .setParameter("reqid", compotor.getRequest_id()).list().get(0).toString());
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'DEL','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'DEL%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        if(hasilCek2>0)
        {
            outMsg= "Request ID "+compotor.getRequest_id()+" sudah direquest delete sebelumnya";
        }
        else if(hasilCek2<=0)
        {
            try {
                currentSession().createSQLQuery("INSERT INTO COMPLAINT_OTOR (\n" +
                "  REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION,\n" +
                "  channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION,\n" +
                "  RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE,\n" +
                "  code_601, CODE_601_PROBLEM, code_602, CODE_602_PROBLEM,\n" +
                "  code_603, CODE_603_PROBLEM, code_604, CODE_604_PROBLEM,\n" +
                "  code_605, CODE_605_PROBLEM,\n" +
                "  dispute_filepath, settlement_filepath, extension_filepath,\n" +
                "  USER_INPUT, USER_OTOR, AGING, is_deleted, ACTION\n" +
                ") VALUES (\n" +
                "  :REQUEST_ID, :SETTLEMENT_NUMBER, :EXTENSION_NUMBER, :CUSTOMER_NAME, :LOCATION,\n" +
                "  :CHANNEL, :CITY_CODE, :BRANCH_CODE, :AMOUNT, :ATM_CARD_NUMBER, :DESCRIPTION,\n" +
                "  :RECEIVED_DATE, :INCIDENT_DATE, CAST(:COMPLETION_DATE AS timestamp),\n" +
                "  :code_601, :CODE_601_PROBLEM, :code_602, :CODE_602_PROBLEM,\n" +
                "  :code_603, :CODE_603_PROBLEM, :code_604, :CODE_604_PROBLEM,\n" +
                "  :code_605, :CODE_605_PROBLEM,\n" +
                "  :dispute_filepath, :settlement_filepath, :extension_filepath,\n" +
                "  :USER_INPUT, :USER_OTOR, :AGING, :is_deleted, 'Delete')").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .setParameter("SETTLEMENT_NUMBER", compotor.getSettlement_number().trim()).setParameter("EXTENSION_NUMBER", compotor.getExtension_number().trim())
                    .setParameter("CUSTOMER_NAME", compotor.getCustomer_name().trim()).setParameter("LOCATION", compotor.getLocation().trim())
                    .setParameter("CHANNEL", compotor.getChannel_code().getChannel_code()).setParameter("CITY_CODE", compotor.getCity_code().getCity_code()).setParameter("BRANCH_CODE", compotor.getBranch_code().getBranch_code())
                    .setParameter("AMOUNT", compotor.getAmount()).setParameter("ATM_CARD_NUMBER", compotor.getAtm_card_number().trim()).setParameter("DESCRIPTION", compotor.getDescription().trim())
                    .setParameter("RECEIVED_DATE", compotor.getReceived_date()).setParameter("INCIDENT_DATE", compotor.getIncident_date()).setParameter("COMPLETION_DATE", compotor.getCompletion_date(),StandardBasicTypes.TIMESTAMP)
                    .setParameter("code_601", compotor.getCode_601().getCode_601_code()).setParameter("CODE_601_PROBLEM", compotor.getCode_601_problem().getCode_601p_code())
                    .setParameter("code_602", compotor.getCode_602() != null ? compotor.getCode_602().getCode_602_code(): null,StandardBasicTypes.STRING).setParameter("CODE_602_PROBLEM", compotor.getCode_602_problem()!= null ? compotor.getCode_602_problem().getCode_602p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("code_603", compotor.getCode_603() != null ? compotor.getCode_603().getCode_603_code(): null,StandardBasicTypes.STRING).setParameter("CODE_603_PROBLEM", compotor.getCode_603_problem()!= null ? compotor.getCode_603_problem().getCode_603p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("code_604", compotor.getCode_604() != null ? compotor.getCode_604().getCode_604_code(): null,StandardBasicTypes.STRING).setParameter("CODE_604_PROBLEM", compotor.getCode_604_problem()!= null ? compotor.getCode_604_problem().getCode_604p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("code_605", compotor.getCode_605() != null ? compotor.getCode_605().getCode_605_code(): null,StandardBasicTypes.STRING).setParameter("CODE_605_PROBLEM", compotor.getCode_605_problem()!= null ? compotor.getCode_605_problem().getCode_605p_code(): null,StandardBasicTypes.STRING)
                    .setParameter("dispute_filepath", compotor.getDispute_filepath()).setParameter("settlement_filepath", compotor.getSettlement_filepath())
                    .setParameter("extension_filepath", compotor.getExtension_filepath()).setParameter("USER_INPUT", compotor.getUser_input())
                    .setParameter("USER_OTOR", compotor.getUser_otor())
                    .setParameter("AGING", compotor.getAging()).setParameter("is_deleted", compotor.getIs_deleted()).executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "DEL"+idxLogID).setParameter("USER_ID", compotor.getUser_input())
                        .setParameter("ACTIVITY_TYPE", "REQ_DELETE_COMPLAINT").setParameter("REQUEST_ID", compotor.getRequest_id())
                        .setParameter("DESCRIPTION", "Complaint "+compotor.getRequest_id()+" request deleted by user "+compotor.getUser_input()).setParameter("CHANGED_FIELDS", "-")
                        .setParameter("DESC_FILE", "-")
                        .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
                
                currentSession().flush();
                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" berhasil request delete.";
            } catch (Exception e) {
                try {
                    throw new Exception(e.getCause().getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" gagal request delete.\n Penyebab errornya: "+e.getCause().getMessage();
            }
        }
        return outMsg;
    }

    @Override
    public String approvedInsCompData(CompOtor compotor) {
        String outMsg="";
        int hasilCek = Integer.parseInt(currentSession().createSQLQuery("select count(REQUEST_ID) hasilCek from complaint where REQUEST_ID=:reqid").addScalar("hasilCek")
                .setParameter("reqid", compotor.getRequest_id()).list().get(0).toString());
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'OKADD','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'OKADD%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        if(hasilCek>0)
        {
            outMsg= "Request ID "+compotor.getRequest_id()+" sudah digunakan pada data existing";
        }
        else if(hasilCek<=0)
        {
            try {
                currentSession().createSQLQuery("INSERT INTO complaint (\n" +
                "  REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION,\n" +
                "  channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION,\n" +
                "  RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE,\n" +
                "  CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM,\n" +
                "  CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM,\n" +
                "  CODE_605, CODE_605_PROBLEM,\n" +
                "  dispute_filepath, settlement_filepath, extension_filepath,\n" +
                "  USER_INPUT, USER_OTOR, AGING, is_deleted\n" +
                ") SELECT REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION,\n" +
                "  channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION,\n" +
                "  RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE,\n" +
                "  CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM,\n" +
                "  CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM,\n" +
                "  CODE_605, CODE_605_PROBLEM,\n" +
                "  dispute_filepath, settlement_filepath, extension_filepath,\n" +
                "  USER_INPUT, :USER_OTOR, AGING, is_deleted FROM complaint_otor "
                        + " WHERE REQUEST_ID=:REQUEST_ID ").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                        .setParameter("USER_OTOR", compotor.getUser_otor())
                    .executeUpdate();
                
                currentSession().createSQLQuery("DELETE FROM complaint_otor WHERE REQUEST_ID=:REQUEST_ID ").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .executeUpdate();
                
                currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "OKADD"+idxLogID).setParameter("USER_ID", compotor.getUser_input())
                        .setParameter("ACTIVITY_TYPE", "APP_ADD_COMPLAINT").setParameter("REQUEST_ID", compotor.getRequest_id())
                        .setParameter("DESCRIPTION", "New complaint approved added for RequestID "+compotor.getRequest_id()+" by user "+compotor.getUser_otor()+".").setParameter("CHANGED_FIELDS", "-")
                        .setParameter("DESC_FILE", compotor.getDispute_filepath().substring(compotor.getDispute_filepath().lastIndexOf("/")+1)+
                                compotor.getSettlement_filepath().substring(compotor.getSettlement_filepath().lastIndexOf("/")+1)+
                                compotor.getExtension_filepath().substring(compotor.getExtension_filepath().lastIndexOf("/")+1))
                        .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
                currentSession().flush();
                
                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" berhasil diotor.";
            } catch (Exception e) {
                try {
                    throw new Exception(e.getCause().getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                outMsg="Data dengan Req ID "+compotor.getRequest_id()+" gagal diotor.\n Penyebab errornya: "+e.getCause().getMessage();
            }
        }
        return outMsg;
    }

    @Override
    public String rejectedCompData(String reqID, String action, String userName) {
        String outMsg="";
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'NOTOK','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'NOTOK%'").addScalar("hasilCek")
                .list().get(0).toString());
        try {

            List<CompOtor> lstcompotor = currentSession().createSQLQuery("SELECT REQUEST_ID, SETTLEMENT_NUMBER, EXTENSION_NUMBER, CUSTOMER_NAME, LOCATION, \n" +
                    "channel_code, CITY_CODE, BRANCH_CODE, AMOUNT, ATM_CARD_NUMBER, DESCRIPTION, \n" +
                    "RECEIVED_DATE, INCIDENT_DATE, COMPLETION_DATE, \n" +
                    "CODE_601, CODE_601_PROBLEM, CODE_602, CODE_602_PROBLEM, \n" +
                    "CODE_603, CODE_603_PROBLEM, CODE_604, CODE_604_PROBLEM, \n" +
                    "CODE_605, CODE_605_PROBLEM, \n" +
                    "dispute_filepath, settlement_filepath, extension_filepath, \n" +
                    "USER_INPUT, USER_OTOR, AGING, is_deleted, ACTION, create_at,comp_type,req_type,address,update_at,incident_checkbox,status \n" +
                    "FROM complaint_otor WHERE REQUEST_ID = :REQUEST_ID ORDER BY RECEIVED_DATE DESC").addEntity(CompOtor.class).setParameter("REQUEST_ID", reqID.trim()).list();
            currentSession().createSQLQuery("DELETE FROM complaint_otor WHERE REQUEST_ID=:REQUEST_ID ").setParameter("REQUEST_ID", reqID.trim())
                .executeUpdate();
            
            currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "NOTOK"+idxLogID).setParameter("USER_ID", lstcompotor.get(0).getUser_input())
                        .setParameter("ACTIVITY_TYPE", action.equals("Add") ? "REJECT_ADD_COMPLAINT" : action.equals("Update") ? "REJECT_EDIT_COMPLAINT"
                                : action.equals("Delete") ? "REJECT_DEL_COMPLAINT" : ""
                                ).setParameter("REQUEST_ID", lstcompotor.get(0).getRequest_id())
                        .setParameter("DESCRIPTION", action+" Complaint rejected for RequestID "+lstcompotor.get(0).getRequest_id()+" by user "+userName+".").setParameter("CHANGED_FIELDS", "-")
                        .setParameter("DESC_FILE", "-")
                        .setParameter("USER_OTOR", userName).executeUpdate();
            
            currentSession().flush();

            outMsg="Data dengan Req ID "+reqID+" berhasil direject.";
        } catch (Exception e) {
            try {
                throw new Exception(e.getCause().getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            outMsg="Data dengan Req ID "+reqID+" gagal direject.\n Penyebab errornya: "+e.getCause().getMessage();
        }
        return outMsg;
    }

    @Override
    public String approvedUpdtCompData(CompOtor compotor) {
        String outMsg="";
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'OKEDIT','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'OKEDIT%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        try {
            List<System_Logs> inqlogEditLatest = currentSession().createSQLQuery("select LOG_ID,USER_INPUT,ACTIVITY_TYPE,REQUEST_ID,TGL_INPUT,DESCRIPTION,CHANGED_FIELDS,DESC_FILE,USER_OTOR "
                    + "from (select LOG_ID,USER_INPUT,ACTIVITY_TYPE,REQUEST_ID,TGL_INPUT,DESCRIPTION,CHANGED_FIELDS,DESC_FILE,USER_OTOR from System_Logs "
                    + "where Request_Id=:Request_Id and Activity_Type='REQ_UPDATE_COMPLAINT' "
                    + " order by Tgl_Input desc) LIMIT 1 ").addEntity(System_Logs.class)
                    .setParameter("Request_Id", compotor.getRequest_id().trim())
                .list();
            
            currentSession().createSQLQuery("MERGE INTO complaint c\n" +
            "USING (\n" +
            "    SELECT * FROM complaint_otor WHERE REQUEST_ID = :REQUEST_ID\n" +
            ") o\n" +
            "ON (c.REQUEST_ID = o.REQUEST_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "UPDATE SET\n" +
            "    AMOUNT = o.AMOUNT,\n" +
            "    LOCATION = o.LOCATION,\n" +
            "    ATM_CARD_NUMBER = o.ATM_CARD_NUMBER,\n" +
            "    DESCRIPTION = o.DESCRIPTION,\n" +
            "    COMPLETION_DATE = o.COMPLETION_DATE,\n" +
            "    SETTLEMENT_NUMBER = o.SETTLEMENT_NUMBER,\n" +
            "    EXTENSION_NUMBER = o.EXTENSION_NUMBER,\n" +
            "    CODE_602 = o.CODE_602,\n" +
            "    CODE_602_PROBLEM = o.CODE_602_PROBLEM,\n" +
            "    CODE_604 = o.CODE_604,\n" +
            "    CODE_604_PROBLEM = o.CODE_604_PROBLEM,\n" +
            "    CODE_605 = o.CODE_605,\n" +
            "    CODE_605_PROBLEM = o.CODE_605_PROBLEM,\n" +
            "    dispute_filepath = o.dispute_filepath,\n" +
            "    settlement_filepath = o.settlement_filepath,\n" +
            "    extension_filepath = o.extension_filepath,\n" +
            "    AGING = o.AGING,\n" +
            "    USER_INPUT = o.USER_INPUT,\n" +
            "    USER_OTOR = :USER_OTOR").setParameter("REQUEST_ID", compotor.getRequest_id())
                    .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
            
            currentSession().createSQLQuery("DELETE FROM complaint_otor WHERE REQUEST_ID=:REQUEST_ID ").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .executeUpdate();
            
            currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "OKEDIT"+idxLogID).setParameter("USER_ID", compotor.getUser_input())
                        .setParameter("ACTIVITY_TYPE", "APP_UPDATE_COMPLAINT").setParameter("REQUEST_ID", compotor.getRequest_id())
                        .setParameter("DESCRIPTION", inqlogEditLatest.get(0).getDescription().replace("request", "approve")).setParameter("CHANGED_FIELDS", inqlogEditLatest.get(0).getChanged_fields())
                        .setParameter("DESC_FILE", inqlogEditLatest.get(0).getDesc_file())
                        .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
            
            currentSession().flush();
            outMsg="Data dengan Req ID "+compotor.getRequest_id()+" berhasil diotor.";
        } catch (Exception e) {
            try {
                throw new Exception(e.getCause().getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            outMsg="Data dengan Req ID "+compotor.getRequest_id()+" gagal diotor.\n Penyebab errornya: "+e.getCause().getMessage();
        }
        return outMsg;
    }

    @Override
    public String approvedDelCompData(CompOtor compotor) {
        String outMsg="";
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'OKDEL','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'OKDEL%'").addScalar("hasilCek")
                .list().get(0).toString());
        
        try {
            currentSession().createSQLQuery("UPDATE complaint SET is_deleted = 'Y', USER_OTOR = :USER_OTOR WHERE REQUEST_ID = :REQUEST_ID").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
            currentSession().createSQLQuery("DELETE FROM complaint_otor WHERE REQUEST_ID=:REQUEST_ID ").setParameter("REQUEST_ID", compotor.getRequest_id().trim())
                    .executeUpdate();
            
            currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "OKDEL"+idxLogID).setParameter("USER_ID", compotor.getUser_input())
                        .setParameter("ACTIVITY_TYPE", "APP_DELETE_COMPLAINT").setParameter("REQUEST_ID", compotor.getRequest_id())
                        .setParameter("DESCRIPTION", "Complaint "+compotor.getRequest_id()+" approved deleted by user "+compotor.getUser_otor()).setParameter("CHANGED_FIELDS", "-")
                        .setParameter("DESC_FILE", "-")
                        .setParameter("USER_OTOR", compotor.getUser_otor()).executeUpdate();
            
            currentSession().flush();
            outMsg="Data dengan Req ID "+compotor.getRequest_id()+" berhasil diotor.";
        } catch (Exception e) {
            try {
                throw new Exception(e.getCause().getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            outMsg="Data dengan Req ID "+compotor.getRequest_id()+" gagal diotor.\n Penyebab errornya: "+e.getCause().getMessage();
        }
        return outMsg;
    }

    @Override
    public List<CompOtor> lstCompOtorByFilter(Date sDate, Date eDate, String keyString, String userid) {
        List<CompOtor> hasil = currentSession().createSQLQuery("SELECT co.REQUEST_ID, co.SETTLEMENT_NUMBER, co.EXTENSION_NUMBER, co.CUSTOMER_NAME, co.LOCATION, \n" +
"              co.CHANNEL, co.CITY_CODE, co.BRANCH_CODE, co.AMOUNT, co.ATM_CARD_NUMBER, co.DESCRIPTION, \n" +
"              co.RECEIVED_DATE, co.INCIDENT_DATE, co.COMPLETION_DATE, \n" +
"              co.CODE_601, co.CODE_601_PROBLEM, co.CODE_602, co.CODE_602_PROBLEM, \n" +
"              co.CODE_603, co.CODE_603_PROBLEM, co.CODE_604, co.CODE_604_PROBLEM, \n" +
"              co.CODE_605, co.CODE_605_PROBLEM, \n" +
"              co.dispute_filepath, co.settlement_filepath, co.extension_filepath, \n" +
"              co.USER_INPUT, co.USER_OTOR, co.AGING, co.is_deleted, co.ACTION \n" +
"            FROM complaint_otor co, CITY c, BRANCH b\n" +
"            WHERE co.USER_INPUT <> :USER_INPUT AND co.CITY_CODE=c.CITY_CODE AND co.BRANCH_CODE=b.BRANCH_CODE AND (\n" +
"            TRUNC(co.RECEIVED_DATE) between :sDate and :eDate OR\n" +
"            TRUNC(co.INCIDENT_DATE) between :sDate and :eDate OR\n" +
"            TRUNC(co.COMPLETION_DATE) between :sDate and :eDate OR\n" +
"            UPPER(co.REQUEST_ID) LIKE :key OR\n" +
"            UPPER(co.CUSTOMER_NAME) LIKE :key OR\n" +
"            UPPER(c.CITY_NAME) LIKE :key OR\n" +
"            UPPER(b.BRANCH_NAME) LIKE :key\n" +
"            ) ORDER BY REQUEST_ID DESC ").addEntity(CompOtor.class).setParameter("sDate", sDate).setParameter("eDate", eDate)
                .setParameter("key", keyString.toUpperCase()).setParameter("key", keyString.toUpperCase())
                .setParameter("USER_INPUT", userid).list();
        currentSession().flush();
        return hasil;
    }

    @Override
    public void insProctoLogs(System_Logs systemlogs) {
        
        int idxLogID = Integer.parseInt(currentSession().createSQLQuery("select COALESCE(max(CAST(replace(Log_Id,'INQ','') AS INTEGER)),0)+1 as hasilCek from System_Logs where Log_Id like 'INQ%'").addScalar("hasilCek")
                .list().get(0).toString());
        try {
            currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "INQ"+idxLogID).setParameter("USER_ID", systemlogs.getUser_input())
                        .setParameter("ACTIVITY_TYPE", systemlogs.getActivity_type()).setParameter("REQUEST_ID", systemlogs.getRequest_id())
                        .setParameter("DESCRIPTION", systemlogs.getDescription())
                        .setParameter("CHANGED_FIELDS", "-").setParameter("DESC_FILE", systemlogs.getDesc_file())
                        .setParameter("USER_OTOR", systemlogs.getUser_otor()).executeUpdate();
//            currentSession().createSQLQuery("INSERT INTO SYSTEM_LOGS (LOG_ID, USER_INPUT, ACTIVITY_TYPE,"
//                        + " REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, DESC_FILE, USER_OTOR)"
//                        + " VALUES (:LOG_ID, :USER_ID, :ACTIVITY_TYPE, :REQUEST_ID, NOW(), :DESCRIPTION, "
//                        + ":CHANGED_FIELDS, :DESC_FILE, :USER_OTOR)").setParameter("LOG_ID", "INQ"+idxLogID,Hibernate.STRING).setParameter("USER_ID", compdata.getUser_input(),Hibernate.STRING)
//                        .setParameter("ACTIVITY_TYPE", "DOWNLOAD_APPENDIX",Hibernate.STRING).setParameter("REQUEST_ID", compdata.getRequest_id(),Hibernate.STRING)
//                        .setParameter("DESCRIPTION", "User "+compdata.getUser_input()+" downloaded document '"+desc+"' for Request ID "+compdata.getRequest_id()+".",Hibernate.STRING)
//                        .setParameter("CHANGED_FIELDS", "-",Hibernate.STRING).setParameter("DESC_FILE", desc,Hibernate.STRING)
//                        .setParameter("USER_OTOR", compdata.getUser_otor(),Hibernate.STRING).executeUpdate();
            
            currentSession().flush();
        } catch (Exception e) {
            try {
                throw new Exception(e.getCause().getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ComphandDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<System_Logs> lstCompLogs() {
        List<System_Logs> result = new ArrayList<System_Logs>();
        try {
            result = currentSession().createSQLQuery("SELECT LOG_ID, USER_INPUT, ACTIVITY_TYPE, REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS,"
                    + "DESC_FILE, USER_OTOR FROM ("
                    + "SELECT LOG_ID, USER_INPUT, ACTIVITY_TYPE, REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, "
                    + " DESC_FILE, USER_OTOR FROM SYSTEM_LOGS " +
            "ORDER BY TGL_INPUT DESC) LIMIT 50 ").addEntity(System_Logs.class).list();
            
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return result;
    }

    @Override
    public List<System_Logs> lstCompLogsByFilter(Date sDate, Date eDate, String keyString) {
        List<System_Logs> result = new ArrayList<System_Logs>();
        try {
            result = currentSession().createSQLQuery("SELECT \n" +
                "    LOG_ID, \n" +
                "    USER_INPUT, \n" +
                "    ACTIVITY_TYPE, \n" +
                "    REQUEST_ID, \n" +
                "    TGL_INPUT, \n" +
                "    DESCRIPTION, \n" +
                "    CHANGED_FIELDS, \n" +
                "    DESC_FILE, \n" +
                "    USER_OTOR \n" +
                "FROM SYSTEM_LOGS \n" +
                "WHERE CAST(TGL_INPUT AS DATE) BETWEEN CAST(:sDate AS DATE) AND CAST(:eDate AS DATE)\n" +
                "  OR (\n" +
//                "  AND (\n" +
                "      USER_INPUT ILIKE :KEY OR \n" +
                "      ACTIVITY_TYPE ILIKE :KEY OR \n" +
                "      CAST(REQUEST_ID AS TEXT) ILIKE :KEY OR \n" +
                "      DESCRIPTION ILIKE :KEY OR \n" +
                "      CHANGED_FIELDS ILIKE :KEY OR \n" +
                "      DESC_FILE ILIKE :KEY OR \n" +
                "      USER_OTOR ILIKE :KEY\n" +
                "  ) \n" +
                "ORDER BY TGL_INPUT DESC").addEntity(System_Logs.class).setParameter("sDate", sDate,StandardBasicTypes.TIMESTAMP).setParameter("eDate", eDate,StandardBasicTypes.TIMESTAMP)
//            result = currentSession().createSQLQuery("SELECT LOG_ID, USER_INPUT, ACTIVITY_TYPE, REQUEST_ID, TGL_INPUT, DESCRIPTION, CHANGED_FIELDS, "
//                    + " DESC_FILE, USER_OTOR FROM SYSTEM_LOGS WHERE TRUNC(TGL_INPUT) BETWEEN CAST(:sDate AS timestamp) and CAST(:eDate AS timestamp) "
//                    + " OR (USER_INPUT=:KEY OR ACTIVITY_TYPE=:KEY OR REQUEST_ID=:KEY "
//                    + "OR DESCRIPTION=:KEY OR CHANGED_FIELDS=:KEY OR DESC_FILE=:KEY OR USER_OTOR=:KEY ) " +
//            "ORDER BY TGL_INPUT DESC ").addEntity(System_Logs.class).setParameter("sDate", sDate,StandardBasicTypes.TIMESTAMP).setParameter("eDate", eDate,StandardBasicTypes.TIMESTAMP)
                    .setParameter("KEY", keyString).list();
            System.out.println("SIZENYAAAAAA "+result.size());
            
        } catch (Exception e) {
            e.printStackTrace(); // or log to file
            System.err.println("SOAP fault reason: " + e.getMessage());
        }
        
        currentSession().flush();
        return result;
    }
}
