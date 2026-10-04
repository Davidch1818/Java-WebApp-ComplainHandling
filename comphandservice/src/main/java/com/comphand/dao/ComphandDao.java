package com.comphand.dao;

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
import com.comphand.model.CompOtor;
import com.comphand.model.CompReport;
import com.comphand.model.Compdata;
import com.comphand.model.MasterOtor;
import java.util.List;

import com.comphand.model.Product;
import com.comphand.model.System_Logs;
import java.util.Date;

public interface ComphandDao {

    public List<Compdata> lstCompData(boolean isOutStandComp);
    public Compdata getOneCompData(String reqid);
    public List<Compdata> lstCompDataByFilter(Date sDate,Date eDate,String keyString);
    public List<CompReport> lstCompRpt();
    public List<CompReport> lstCompRptByFilter(Date sDate,Date eDate,String keyString);
    public List<CompReport> lstCompResRpt();
    public List<CompReport> lstCompResRptByFilter(Date sDate,Date eDate,String keyString);
    public List<CompOtor> lstCompOtor(String userid);
    public String reqInsCompData(CompOtor compotor);
    public String reqUpdtCompData(CompOtor compotor, String logEdit);
    public String reqDelCompData(CompOtor compotor);
    public String approvedInsCompData(CompOtor compotor);
    public String rejectedCompData(String reqID, String action, String userName);
    public String approvedUpdtCompData(CompOtor compotor);
    public String approvedDelCompData(CompOtor compotor);
    public List<CompOtor> lstCompOtorByFilter(Date sDate,Date eDate,String keyString,String userid);
    public void insProctoLogs(System_Logs systemlogs);
    public List<System_Logs> lstCompLogs();
    public List<System_Logs> lstCompLogsByFilter(Date sDate,Date eDate,String keyString);
}
