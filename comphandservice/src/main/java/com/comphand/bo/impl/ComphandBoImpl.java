package com.comphand.bo.impl;

import com.comphand.bo.ComphandBo;
import com.comphand.bo.MasterBo;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.comphand.bo.ProductBo;
import com.comphand.dao.ComphandDao;
import com.comphand.dao.MasterDao;
import com.comphand.dao.ProductDao;
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
import com.comphand.model.Product;
import com.comphand.model.System_Logs;
import java.util.Date;

public class ComphandBoImpl implements ComphandBo {

    private ComphandDao comphandDao;

    public void setComphandDao(ComphandDao comphandDao) {
        this.comphandDao = comphandDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compdata> lstCompData(boolean isOutStandComp) {
        return comphandDao.lstCompData(isOutStandComp);
    }

    @Override
    @Transactional(readOnly = true)
    public Compdata getOneCompData(String reqid) {
        return comphandDao.getOneCompData(reqid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compdata> lstCompDataByFilter(Date sDate, Date eDate, String keyString) {
        return comphandDao.lstCompDataByFilter(sDate, eDate, keyString);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompReport> lstCompRpt() {
        return comphandDao.lstCompRpt();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompReport> lstCompRptByFilter(Date sDate, Date eDate, String keyString) {
        return comphandDao.lstCompRptByFilter(sDate, eDate, keyString);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompReport> lstCompResRpt() {
        return comphandDao.lstCompResRpt();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompReport> lstCompResRptByFilter(Date sDate, Date eDate, String keyString) {
        return comphandDao.lstCompResRptByFilter(sDate, eDate, keyString);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompOtor> lstCompOtor(String userid) {
        return comphandDao.lstCompOtor(userid);
    }

    @Override
    @Transactional
    public String reqInsCompData(CompOtor compotor) {
        return comphandDao.reqInsCompData(compotor);
    }

    @Override
    @Transactional
    public String reqUpdtCompData(CompOtor compotor, String logEdit) {
        return comphandDao.reqUpdtCompData(compotor, logEdit);
    }

    @Override
    @Transactional
    public String reqDelCompData(CompOtor compotor) {
        return comphandDao.reqDelCompData(compotor);
    }

    @Override
    @Transactional
    public String approvedCompData(CompOtor compotor, String action) {
        String hasil="";
        if(action.equals("Add"))
        {
            hasil=comphandDao.approvedInsCompData(compotor);
        }
        else if(action.equals("Update"))
        {
            hasil=comphandDao.approvedUpdtCompData(compotor);
        }
        else if(action.equals("Delete"))
        {
            hasil=comphandDao.approvedDelCompData(compotor);
        }
        return hasil;
    }

    @Override
    @Transactional
    public String rejectedCompData(String reqID, String action, String userName) {
        return comphandDao.rejectedCompData(reqID, action, userName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompOtor> lstCompOtorByFilter(Date sDate, Date eDate, String keyString, String userid) {
        return comphandDao.lstCompOtorByFilter(sDate, eDate, keyString, userid);
    }

    @Override
    @Transactional
    public void insProctoLogs(System_Logs systemlogs) {
        comphandDao.insProctoLogs(systemlogs);
    }

    @Override
    @Transactional(readOnly = true)
    public List<System_Logs> lstCompLogs() {
        return comphandDao.lstCompLogs();
    }

    @Override
    @Transactional(readOnly = true)
    public List<System_Logs> lstCompLogsByFilter(Date sDate, Date eDate, String keyString) {
        return comphandDao.lstCompLogsByFilter(sDate, eDate, keyString);
    }
}
