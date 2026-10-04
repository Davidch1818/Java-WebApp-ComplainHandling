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
public class CompReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String PRODUCT_TYPE;
    private String PROBLEM_TYPE;
    private int FREK;
    
    private String BRANCH_CODE;
    private String KET;
    private int RATA;

    public String getPRODUCT_TYPE() {
        return PRODUCT_TYPE;
    }

    public void setPRODUCT_TYPE(String PRODUCT_TYPE) {
        this.PRODUCT_TYPE = PRODUCT_TYPE;
    }

    public String getPROBLEM_TYPE() {
        return PROBLEM_TYPE;
    }

    public void setPROBLEM_TYPE(String PROBLEM_TYPE) {
        this.PROBLEM_TYPE = PROBLEM_TYPE;
    }

    public int getFREK() {
        return FREK;
    }

    public void setFREK(int FREK) {
        this.FREK = FREK;
    }

    public String getBRANCH_CODE() {
        return BRANCH_CODE;
    }

    public void setBRANCH_CODE(String BRANCH_CODE) {
        this.BRANCH_CODE = BRANCH_CODE;
    }

    public String getKET() {
        return KET;
    }

    public void setKET(String KET) {
        this.KET = KET;
    }

    public int getRATA() {
        return RATA;
    }

    public void setRATA(int RATA) {
        this.RATA = RATA;
    }

    
    
}
