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
import com.comphand.model.MasterOtor;
import java.util.List;

import com.comphand.model.Product;

public interface MasterDao {

    public List<Channel> lstChannels();
    public List<City> lstCities();
    public List<Branch> lstBranches();
    public List<Code_601> lstCode601Products();
    public List<Code_601_Info> lstCode601Problems();
    public List<Code_602> lstCode602Products();
    public List<Code_602_Info> lstCode602Problems();
    public List<Code_603> lstCode603Products();
    public List<Code_603_Info> lstCode603Problems();
    public List<Code_604> lstCode604Products();
    public List<Code_604_Info> lstCode604Problems();
    public List<Code_605> lstCode605Products();
    public List<Code_605_Info> lstCode605Problems();
    public List<Branch> lstBranchesByCity(String city);
    public String insMstData(MasterOtor masterotor, String action, String prstype, MasterOtor masterotorb4);
    public List<MasterOtor> lstMstOtor(String puserinput);
    public String approvedInsMstData(MasterOtor masterotor,String action);
    public String approvedEditMstData(MasterOtor masterotor,String action);
    public String approvedDelMstData(MasterOtor masterotor,String action);
    public String rejectedInsMstData(MasterOtor masterotor);
}
