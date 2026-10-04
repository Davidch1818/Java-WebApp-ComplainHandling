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
public class Code_601 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code_601_code;
    private String code_601_name;
    private String is_deleted;
    private Date created_at;
    private Date update_at;
    private String user_input;
    private String user_otor;

    public String getCode_601_code() {
        return code_601_code;
    }

    public void setCode_601_code(String code_601_code) {
        this.code_601_code = code_601_code;
    }

    public String getCode_601_name() {
        return code_601_name;
    }

    public void setCode_601_name(String code_601_name) {
        this.code_601_name = code_601_name;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public String getUser_input() {
        return user_input;
    }

    public void setUser_input(String user_input) {
        this.user_input = user_input;
    }

    public String getUser_otor() {
        return user_otor;
    }

    public void setUser_otor(String user_otor) {
        this.user_otor = user_otor;
    }

    
}
