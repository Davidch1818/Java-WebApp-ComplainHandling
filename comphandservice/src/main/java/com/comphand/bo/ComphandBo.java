package com.comphand.bo;

import com.comphand.model.CompOtor;
import com.comphand.model.CompReport;
import com.comphand.model.Compdata;
import java.util.List;

import com.comphand.model.System_Logs;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * Business Object contract for Product. See UserBo for the note on why
 * this interface must be byte-for-byte identical in both projects.
 */
@Service
public interface ComphandBo {

    public List<Compdata> lstCompData(boolean isOutStandComp);
    public Compdata getOneCompData(String reqid);
    public List<Compdata> lstCompDataByFilter(Date sDate,Date eDate,String keyString);
    public List<CompReport> lstCompRpt();
    public List<CompReport> lstCompRptByFilter(Date sDate,Date eDate,String keyString);
    public List<CompReport> lstCompResRpt();
    public List<CompReport> lstCompResRptByFilter(Date sDate,Date eDate,String keyString);
    public List<CompOtor> lstCompOtor(String userid);
    public String reqInsCompData(CompOtor compotor);
    public String reqUpdtCompData(CompOtor compotor,String logEdit);
    public String reqDelCompData(CompOtor compotor);
    public String approvedCompData(CompOtor compotor,String action);
    public String rejectedCompData(String reqID,String action, String userName);
    public List<CompOtor> lstCompOtorByFilter(Date sDate,Date eDate,String keyString,String userid);
    public void insProctoLogs(System_Logs systemlogs);
    public List<System_Logs> lstCompLogs();
    public List<System_Logs> lstCompLogsByFilter(Date sDate,Date eDate,String keyString);
}
