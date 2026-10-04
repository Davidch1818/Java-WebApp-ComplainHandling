package com.comphand.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Plain model object for Product. See User for the note on why this
 * class must be byte-for-byte identical (including serialVersionUID)
 * in both projects. On comphandservice it is also the Hibernate-mapped
 * class -- see src/main/resources/com/comphand/model/Product.hbm.xml.
 */
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

private String branch_code;
    private String branch_name;
    private String is_deleted;
    private Date created_at;
    private Date update_at;
    private String user_input;
    private String user_otor;
    private City city_code;

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
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
    
    public City getCity_code() {
        return city_code;
    }

    public void setCity_code(City city_code) {
        this.city_code = city_code;
    }

    

    
}
