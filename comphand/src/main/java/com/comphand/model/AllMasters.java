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
public class AllMasters implements Serializable {

    private static final long serialVersionUID = 1L;

    private String CITY_CODE;
    private String CITY_NAME;
    private String FLAGOTOR;
    private String BRANCH_CODE;
    private String BRANCH_NAME;
    private String CHANNELS_CODE;
    private String CHANNEL_NAME;
    private String PRODUCT_CODE;
    private String PRODUCT_NAME;
    private String PRODUCTINFO_CODE;
    private String PRODUCTINFO_NAME;
    private String USER_INPUT;
    private String USER_OTOR;
    private String IS_DELETED;

    public AllMasters(String CITY_CODE, String CITY_NAME, String FLAGOTOR, String BRANCH_CODE, String BRANCH_NAME, String CHANNELS_CODE, String CHANNEL_NAME, String PRODUCT_CODE, String PRODUCT_NAME, String PRODUCTINFO_CODE, String PRODUCTINFO_NAME, String USER_INPUT, String USER_OTOR, String IS_DELETED) {
        this.CITY_CODE = CITY_CODE;
        this.CITY_NAME = CITY_NAME;
        this.FLAGOTOR = FLAGOTOR;
        this.BRANCH_CODE = BRANCH_CODE;
        this.BRANCH_NAME = BRANCH_NAME;
        this.CHANNELS_CODE = CHANNELS_CODE;
        this.CHANNEL_NAME = CHANNEL_NAME;
        this.PRODUCT_CODE = PRODUCT_CODE;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.PRODUCTINFO_CODE = PRODUCTINFO_CODE;
        this.PRODUCTINFO_NAME = PRODUCTINFO_NAME;
        this.USER_INPUT = USER_INPUT;
        this.USER_OTOR = USER_OTOR;
        this.IS_DELETED = IS_DELETED;
    }

    
    public String getCITY_CODE() {
        return CITY_CODE;
    }

    public void setCITY_CODE(String CITY_CODE) {
        this.CITY_CODE = CITY_CODE;
    }

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }

    public String getFLAGOTOR() {
        return FLAGOTOR;
    }

    public void setFLAGOTOR(String FLAGOTOR) {
        this.FLAGOTOR = FLAGOTOR;
    }

    public String getBRANCH_CODE() {
        return BRANCH_CODE;
    }

    public void setBRANCH_CODE(String BRANCH_CODE) {
        this.BRANCH_CODE = BRANCH_CODE;
    }

    public String getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(String BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
    }

    public String getCHANNELS_CODE() {
        return CHANNELS_CODE;
    }

    public void setCHANNELS_CODE(String CHANNELS_CODE) {
        this.CHANNELS_CODE = CHANNELS_CODE;
    }

    public String getCHANNEL_NAME() {
        return CHANNEL_NAME;
    }

    public void setCHANNEL_NAME(String CHANNEL_NAME) {
        this.CHANNEL_NAME = CHANNEL_NAME;
    }

    public String getPRODUCT_CODE() {
        return PRODUCT_CODE;
    }

    public void setPRODUCT_CODE(String PRODUCT_CODE) {
        this.PRODUCT_CODE = PRODUCT_CODE;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCTINFO_CODE() {
        return PRODUCTINFO_CODE;
    }

    public void setPRODUCTINFO_CODE(String PRODUCTINFO_CODE) {
        this.PRODUCTINFO_CODE = PRODUCTINFO_CODE;
    }

    public String getPRODUCTINFO_NAME() {
        return PRODUCTINFO_NAME;
    }

    public void setPRODUCTINFO_NAME(String PRODUCTINFO_NAME) {
        this.PRODUCTINFO_NAME = PRODUCTINFO_NAME;
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

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }

    

    
}
