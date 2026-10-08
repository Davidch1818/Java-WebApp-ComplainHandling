package com.comphand.dao;

import com.comphand.model.CompOtor;
import com.comphand.model.CompReport;
import com.comphand.model.Compdata;
import java.util.List;

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
