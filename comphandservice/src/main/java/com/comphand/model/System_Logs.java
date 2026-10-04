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
public class System_Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    private String log_id;
    private String user_input;
    private String activity_type;
    private String request_id;
    private Date tgl_input;
    private String description;
    private String changed_fields;
    private String desc_file;
    private String user_otor;

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getUser_input() {
        return user_input;
    }

    public void setUser_input(String user_input) {
        this.user_input = user_input;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Date getTgl_input() {
        return tgl_input;
    }

    public void setTgl_input(Date tgl_input) {
        this.tgl_input = tgl_input;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChanged_fields() {
        return changed_fields;
    }

    public void setChanged_fields(String changed_fields) {
        this.changed_fields = changed_fields;
    }

    public String getDesc_file() {
        return desc_file;
    }

    public void setDesc_file(String desc_file) {
        this.desc_file = desc_file;
    }

    public String getUser_otor() {
        return user_otor;
    }

    public void setUser_otor(String user_otor) {
        this.user_otor = user_otor;
    }

    
}
