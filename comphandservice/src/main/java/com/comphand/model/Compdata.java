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
public class Compdata implements Serializable {

    private static final long serialVersionUID = 1L;

    private String request_id;
    private Date create_at;
    private String user_input;
    private String user_otor;
    private Date received_date;
    private Date incident_date;
    private Date completion_date;
    private String customer_name;
    private String comp_type;
    private String req_type;
    private int amount;
    private String address;
    private Branch branch_code;
    private Channel channel_code;
    private City city_code;
    private String location;
    private String settlement_number;
    private String extension_number;
    private String dispute_filepath;
    private String settlement_filepath;
    private String extension_filepath;
    private Date update_at;
    private String is_deleted;
    private Code_601 code_601;
    private Code_601_Info code_601_problem;
    private Code_602 code_602;
    private Code_602_Info code_602_problem;
    private Code_603 code_603;
    private Code_603_Info code_603_problem;
    private Code_604 code_604;
    private Code_604_Info code_604_problem;
    private Code_605 code_605;
    private Code_605_Info code_605_problem;
    private String atm_card_number;
    private String description;
    private String incident_checkbox;
    private String status;
    private int aging;
    private String action;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
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

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public Date getIncident_date() {
        return incident_date;
    }

    public void setIncident_date(Date incident_date) {
        this.incident_date = incident_date;
    }

    public Date getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(Date completion_date) {
        this.completion_date = completion_date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getComp_type() {
        return comp_type;
    }

    public void setComp_type(String comp_type) {
        this.comp_type = comp_type;
    }

    public String getReq_type() {
        return req_type;
    }

    public void setReq_type(String req_type) {
        this.req_type = req_type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSettlement_number() {
        return settlement_number;
    }

    public void setSettlement_number(String settlement_number) {
        this.settlement_number = settlement_number;
    }

    public String getExtension_number() {
        return extension_number;
    }

    public void setExtension_number(String extension_number) {
        this.extension_number = extension_number;
    }

    public String getDispute_filepath() {
        return dispute_filepath;
    }

    public void setDispute_filepath(String dispute_filepath) {
        this.dispute_filepath = dispute_filepath;
    }

    public String getSettlement_filepath() {
        return settlement_filepath;
    }

    public void setSettlement_filepath(String settlement_filepath) {
        this.settlement_filepath = settlement_filepath;
    }

    public String getExtension_filepath() {
        return extension_filepath;
    }

    public void setExtension_filepath(String extension_filepath) {
        this.extension_filepath = extension_filepath;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getAtm_card_number() {
        return atm_card_number;
    }

    public void setAtm_card_number(String atm_card_number) {
        this.atm_card_number = atm_card_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIncident_checkbox() {
        return incident_checkbox;
    }

    public void setIncident_checkbox(String incident_checkbox) {
        this.incident_checkbox = incident_checkbox;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAging() {
        return aging;
    }

    public void setAging(int aging) {
        this.aging = aging;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Branch getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(Branch branch_code) {
        this.branch_code = branch_code;
    }

    public Channel getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(Channel channel_code) {
        this.channel_code = channel_code;
    }

    public City getCity_code() {
        return city_code;
    }

    public void setCity_code(City city_code) {
        this.city_code = city_code;
    }

    public Code_601 getCode_601() {
        return code_601;
    }

    public void setCode_601(Code_601 code_601) {
        this.code_601 = code_601;
    }

    public Code_601_Info getCode_601_problem() {
        return code_601_problem;
    }

    public void setCode_601_problem(Code_601_Info code_601_problem) {
        this.code_601_problem = code_601_problem;
    }

    public Code_602 getCode_602() {
        return code_602;
    }

    public void setCode_602(Code_602 code_602) {
        this.code_602 = code_602;
    }

    public Code_602_Info getCode_602_problem() {
        return code_602_problem;
    }

    public void setCode_602_problem(Code_602_Info code_602_problem) {
        this.code_602_problem = code_602_problem;
    }

    public Code_603 getCode_603() {
        return code_603;
    }

    public void setCode_603(Code_603 code_603) {
        this.code_603 = code_603;
    }

    public Code_603_Info getCode_603_problem() {
        return code_603_problem;
    }

    public void setCode_603_problem(Code_603_Info code_603_problem) {
        this.code_603_problem = code_603_problem;
    }

    public Code_604 getCode_604() {
        return code_604;
    }

    public void setCode_604(Code_604 code_604) {
        this.code_604 = code_604;
    }

    public Code_604_Info getCode_604_problem() {
        return code_604_problem;
    }

    public void setCode_604_problem(Code_604_Info code_604_problem) {
        this.code_604_problem = code_604_problem;
    }

    public Code_605 getCode_605() {
        return code_605;
    }

    public void setCode_605(Code_605 code_605) {
        this.code_605 = code_605;
    }

    public Code_605_Info getCode_605_problem() {
        return code_605_problem;
    }

    public void setCode_605_problem(Code_605_Info code_605_problem) {
        this.code_605_problem = code_605_problem;
    }

    
}
