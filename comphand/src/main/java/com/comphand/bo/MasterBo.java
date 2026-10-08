package com.comphand.bo;

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

import org.springframework.stereotype.Service;

/**
 * Business Object contract for Product. See UserBo for the note on why
 * this interface must be byte-for-byte identical in both projects.
 */
@Service
public interface MasterBo {

    public List<Branch> lstBranches();
    public List<Channel> lstChannels();
    public List<City> lstCities();
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
    public String insMstData(MasterOtor masterotor,String jenisMst,String prstype, MasterOtor masterotorb4);
    public List<MasterOtor> lstMstOtor(String puserinput);
    public String approvedMstData(MasterOtor masterotor,String action,String typeprs);
    public String rejectedMstData(MasterOtor masterotor);
}
