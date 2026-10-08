package com.comphand.bo.impl;

import com.comphand.bo.MasterBo;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.comphand.dao.MasterDao;
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
import com.comphand.model.MasterOtor;

public class MasterBoImpl implements MasterBo {

    private MasterDao masterDao;

    public void setMasterDao(MasterDao masterDao) {
        this.masterDao = masterDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Branch> lstBranches() {
        return masterDao.lstBranches();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Channel> lstChannels() {
        return masterDao.lstChannels();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> lstCities() {
        return masterDao.lstCities();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_601> lstCode601Products() {
        return masterDao.lstCode601Products();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_601_Info> lstCode601Problems() {
        return masterDao.lstCode601Problems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_602> lstCode602Products() {
        return masterDao.lstCode602Products();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_602_Info> lstCode602Problems() {
        return masterDao.lstCode602Problems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_603> lstCode603Products() {
        return masterDao.lstCode603Products();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_603_Info> lstCode603Problems() {
        return masterDao.lstCode603Problems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_604> lstCode604Products() {
        return masterDao.lstCode604Products();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_604_Info> lstCode604Problems() {
        return masterDao.lstCode604Problems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_605> lstCode605Products() {
        return masterDao.lstCode605Products();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Code_605_Info> lstCode605Problems() {
        return masterDao.lstCode605Problems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Branch> lstBranchesByCity(String city) {
        return masterDao.lstBranchesByCity(city);
    }

    @Override
    @Transactional
    public String insMstData(MasterOtor masterotor, String jenisMst, String prstype, MasterOtor masterotorb4) {
        return masterDao.insMstData(masterotor, jenisMst, prstype, masterotorb4);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MasterOtor> lstMstOtor(String puserinput) {
        return masterDao.lstMstOtor(puserinput);
    }

    @Override
    @Transactional
    public String approvedMstData(MasterOtor masterotor, String action, String typeprs) {
        String hasil="";
        System.out.println("PRSTYPE "+typeprs);
        if(typeprs.equals("Add"))
        {
            hasil=masterDao.approvedInsMstData(masterotor, action);
        }
        else if(typeprs.equals("Edit"))
        {
            hasil=masterDao.approvedEditMstData(masterotor, action);
        }
        else if(typeprs.equals("Delete"))
        {
            hasil=masterDao.approvedDelMstData(masterotor, action);
        }
        return hasil;
    }

    @Override
    @Transactional
    public String rejectedMstData(MasterOtor masterotor) {
        return masterDao.rejectedInsMstData(masterotor);
    }
}
