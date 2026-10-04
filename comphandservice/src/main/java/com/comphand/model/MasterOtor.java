package com.comphand.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Plain model object for Product. See User for the note on why this
 * class must be byte-for-byte identical (including serialVersionUID)
 * in both projects. On comphandservice it is also the Hibernate-mapped
 * class -- see src/main/resources/com/comphand/model/Product.hbm.xml.
 */
public class MasterOtor implements Serializable {

    private static final long serialVersionUID = 1L;

    private String CODE1;
    private String NAME;
    private String CODE2;
    private String USER_INPUT;
    private String USER_OTOR;
    private String ACTION;
    private String TYPE_PRS;

    public MasterOtor(String CODE1, String NAME, String CODE2, String USER_INPUT, String USER_OTOR, String ACTION, String TYPE_PRS) {
        this.CODE1 = CODE1;
        this.NAME = NAME;
        this.CODE2 = CODE2;
        this.USER_INPUT = USER_INPUT;
        this.USER_OTOR = USER_OTOR;
        this.ACTION = ACTION;
        this.TYPE_PRS = TYPE_PRS;
    }

    
    public String getCODE1() {
        return CODE1;
    }

    public void setCODE1(String CODE1) {
        this.CODE1 = CODE1;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCODE2() {
        return CODE2;
    }

    public void setCODE2(String CODE2) {
        this.CODE2 = CODE2;
    }

    public String getUSER_INPUT() {
        return USER_INPUT;
    }

    public void setUSER_INPUT(String USER_INPUT) {
        this.USER_INPUT = USER_INPUT;
    }

    public String getUSER_OTOR() {
        return USER_OTOR;
    }

    public void setUSER_OTOR(String USER_OTOR) {
        this.USER_OTOR = USER_OTOR;
    }

    public String getACTION() {
        return ACTION;
    }

    public void setACTION(String ACTION) {
        this.ACTION = ACTION;
    }

    public String getTYPE_PRS() {
        return TYPE_PRS;
    }

    public void setTYPE_PRS(String TYPE_PRS) {
        this.TYPE_PRS = TYPE_PRS;
    }

    
}
