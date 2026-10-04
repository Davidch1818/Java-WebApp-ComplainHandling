package com.comphand.viewmodel;

import com.comphand.bo.MasterBo;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.comphand.bo.ProductBo;
import com.comphand.model.AllMasters;
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
import com.comphand.model.Product;
import java.util.ArrayList;
import java.util.Date;
import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CompMasterVM {

    @WireVariable
    private MasterBo masterBo;

    private String strTitleMenuMaster="",strTitleMenu="",lblField1="",lblField2="",lblField3="",strSessionId,strUserName,msg="";
    private List<AllMasters> lstMasters=new ArrayList<AllMasters>();
    private List<MasterOtor> lstMasterOtor;
    private List<City> cities;
    private City city;
    private boolean showDetMst=false,inputCityCode=false;
    private MasterOtor masterotor=new MasterOtor();
    private MasterOtor masterotorbe4=new MasterOtor();

    public String getStrTitleMenuMaster() {
        return strTitleMenuMaster;
    }

    public void setStrTitleMenuMaster(String strTitleMenuMaster) {
        this.strTitleMenuMaster = strTitleMenuMaster;
    }

    public List<AllMasters> getLstMasters() {
        return lstMasters;
    }

    public void setLstMasters(List<AllMasters> lstMasters) {
        this.lstMasters = lstMasters;
    }

    public String getStrTitleMenu() {
        return strTitleMenu;
    }

    public void setStrTitleMenu(String strTitleMenu) {
        this.strTitleMenu = strTitleMenu;
    }

    public boolean isShowDetMst() {
        return showDetMst;
    }

    public void setShowDetMst(boolean showDetMst) {
        this.showDetMst = showDetMst;
    }

    public String getLblField1() {
        return lblField1;
    }

    public void setLblField1(String lblField1) {
        this.lblField1 = lblField1;
    }

    public String getLblField2() {
        return lblField2;
    }

    public void setLblField2(String lblField2) {
        this.lblField2 = lblField2;
    }

    public String getLblField3() {
        return lblField3;
    }

    public void setLblField3(String lblField3) {
        this.lblField3 = lblField3;
    }

    public MasterOtor getMasterotor() {
        return masterotor;
    }

    public void setMasterotor(MasterOtor masterotor) {
        this.masterotor = masterotor;
    }
    
        public List<MasterOtor> getLstMasterOtor() {
        if(masterBo.lstMstOtor(strUserName)== null){
            lstMasterOtor = new ListModelList<MasterOtor>();
        }
        else if(lstMasterOtor == null&&masterBo.lstMstOtor(strUserName)!=null) {
            lstMasterOtor = new ListModelList<MasterOtor>(masterBo.lstMstOtor(strUserName));
        }
        return lstMasterOtor;
    }

    public void setLstMasterOtor(List<MasterOtor> lstMasterOtor) {
        this.lstMasterOtor = lstMasterOtor;
    }

    public boolean isInputCityCode() {
        return inputCityCode;
    }

    public void setInputCityCode(boolean inputCityCode) {
        this.inputCityCode = inputCityCode;
    }

    public List<City> getCities() {
        if(masterBo.lstCities()== null){
            cities = new ListModelList<City>();
        }
        else if(cities == null&&masterBo.lstCities()!=null) {
            cities = new ListModelList<City>(masterBo.lstCities());
        }
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
    @Init
    public void init()
    {
        strUserName="TEST";
    }

    @Command
    public void formMstCity(){
        setStrTitleMenu("City");
        setStrTitleMenuMaster("Master City");
        for(City c:masterBo.lstCities())
        {
            if(c.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters(c.getCity_code(), c.getCity_name(), "","", "", "", "", "", "", "", "", c.getUser_input(), c.getUser_otor(),c.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstBranch(){
        setStrTitleMenu("Branch");
        setStrTitleMenuMaster("Master Branch");
        
        for(Branch b:masterBo.lstBranches())
        {
            System.out.println("citynyaaa "+b.getCity_code().getCity_name());
            if(b.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters(b.getCity_code().getCity_code(), b.getCity_code().getCity_name(), "",b.getBranch_code(), b.getBranch_name(), "", "", "", "", "", "", b.getUser_input(), b.getUser_otor(), b.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstChannel(){
        setStrTitleMenu("Channel");
        setStrTitleMenuMaster("Master Channel");
        for(Channel ch:masterBo.lstChannels())
        {
            if(ch.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", ch.getChannel_code(), ch.getChannel_name(), "", "", "", "", ch.getUser_input(), ch.getUser_otor(), ch.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode601(){
        setStrTitleMenu("Code_601");
        setStrTitleMenuMaster("Master Code 601");
        for(Code_601 c6:masterBo.lstCode601Products())
        {
            if(c6.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", c6.getCode_601_code(), c6.getCode_601_name(), "", "", c6.getUser_input(), c6.getUser_otor(), c6.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode602(){
        setStrTitleMenu("Code_602");
        setStrTitleMenuMaster("Master Code 602");
        for(Code_602 c6:masterBo.lstCode602Products())
        {
            if(c6.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", c6.getCode_602_code(), c6.getCode_602_name(), "", "", c6.getUser_input(), c6.getUser_otor(), c6.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode603(){
        setStrTitleMenu("Code_603");
        setStrTitleMenuMaster("Master Code 603");
        for(Code_603 c6:masterBo.lstCode603Products())
        {
            if(c6.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", c6.getCode_603_code(), c6.getCode_603_name(), "", "", c6.getUser_input(), c6.getUser_otor(), c6.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode604(){
        setStrTitleMenu("Code_604");
        setStrTitleMenuMaster("Master Code 604");
        for(Code_604 c6:masterBo.lstCode604Products())
        {
            if(c6.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", c6.getCode_604_code(), c6.getCode_604_name(), "", "", c6.getUser_input(), c6.getUser_otor(), c6.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode605(){
        setStrTitleMenu("Code_605");
        setStrTitleMenuMaster("Master Code 605");
        for(Code_605 c6:masterBo.lstCode605Products())
        {
            if(c6.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", c6.getCode_605_code(), c6.getCode_605_name(), "", "", c6.getUser_input(), c6.getUser_otor(), c6.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode601p(){
        setStrTitleMenu("Code_601_Info");
        setStrTitleMenuMaster("Master Code 601 Problems");
        for(Code_601_Info c6i:masterBo.lstCode601Problems())
        {
            if(c6i.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", "", "", c6i.getCode_601p_code(), c6i.getCode_601p_name(), c6i.getUser_input(), c6i.getUser_otor(), c6i.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode602p(){
        setStrTitleMenu("Code_602_Info");
        setStrTitleMenuMaster("Master Code 602 Problems");
        for(Code_602_Info c6i:masterBo.lstCode602Problems())
        {
            if(c6i.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", "", "", c6i.getCode_602p_code(), c6i.getCode_602p_name(), c6i.getUser_input(), c6i.getUser_otor(), c6i.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode603p(){
        setStrTitleMenu("Code_603_Info");
        setStrTitleMenuMaster("Master Code 603 Problems");
        for(Code_603_Info c6i:masterBo.lstCode603Problems())
        {
            if(c6i.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", "", "", c6i.getCode_603p_code(), c6i.getCode_603p_name(), c6i.getUser_input(), c6i.getUser_otor(), c6i.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode604p(){
        setStrTitleMenu("Code_604_Info");
        setStrTitleMenuMaster("Master Code 604 Problems");
        for(Code_604_Info c6i:masterBo.lstCode604Problems())
        {
            if(c6i.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", "", "", c6i.getCode_604p_code(), c6i.getCode_604p_name(), c6i.getUser_input(), c6i.getUser_otor(), c6i.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstCode605p(){
        setStrTitleMenu("Code_605_Info");
        setStrTitleMenuMaster("Master Code 605 Problems");
        for(Code_605_Info c6i:masterBo.lstCode605Problems())
        {
            if(c6i.getIs_deleted().equals("N"))
            lstMasters.add(new AllMasters("", "", "", "", "", "", "", "", "", c6i.getCode_605p_code(), c6i.getCode_605p_name(), c6i.getUser_input(), c6i.getUser_otor(), c6i.getIs_deleted()));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void formMstOtor(){
        setStrTitleMenuMaster("MasterOtor");
        System.out.println("ISI MST OTORRRRRRRRRRR "+lstMasterOtor.size());
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    
    @Command
    public void addMst(){
        masterotor.setTYPE_PRS("Add");
//        setBtnAction("add");
        initFormDetail(null);
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void insMst(@BindingParam("param") String pStrTitleMenu,@BindingParam("btnaction") String pBtnAction){
//        String msg="";
        masterotor.setUSER_INPUT(strUserName);
        masterotor.setUSER_OTOR("-");
        masterotor.setACTION(pStrTitleMenu);
        if(masterotor.getCODE1()==null||masterotor.getNAME()==null||masterotor.getCODE1().isEmpty()||masterotor.getNAME().isEmpty()||(pStrTitleMenu.equals("Branch")&&getCity()==null))
        {
            Messagebox.show("Inputan belum lengkap");
        }
        else if(!masterotor.getCODE1().isEmpty()&&!masterotor.getNAME().isEmpty())
        {
            if(!pStrTitleMenu.equals("Branch"))
            {
                masterotor.setCODE2("-");
            }
            else if(pStrTitleMenu.equals("Branch"))
            {
                masterotor.setCODE2(getCity().getCity_code());
            }
            msg=masterBo.insMstData(masterotor,getStrTitleMenu(),masterotor.getTYPE_PRS(),masterotorbe4);
            masterotor=new MasterOtor();
            masterotorbe4=new MasterOtor();
            setCity(null);
            setShowDetMst(false);
            Messagebox.show(msg);
        }
        
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void otorApproved(@BindingParam("id") String id,@BindingParam("btnaction") String pBtnAction){
        masterotor=new MasterOtor();
        for(MasterOtor mo:lstMasterOtor)
        {
            if((mo.getCODE1()+mo.getACTION()).equals(id))
            {
                masterotor=mo;
                break;
            }
        }
        
        Messagebox.show("Apakah yakin otor data master dengan kode "+masterotor.getCODE1()+" dan action "+masterotor.getACTION()+" ?",
        "Confirm Otor",
        Messagebox.YES | Messagebox.NO,
        Messagebox.INFORMATION,new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    masterotor.setUSER_OTOR(strUserName);
                    msg=masterBo.approvedMstData(masterotor,masterotor.getACTION(),masterotor.getTYPE_PRS());
//                    Messagebox.show(msg);
                    lstMasterOtor.clear();
                    if(masterBo.lstMstOtor(strUserName)!=null)
                    {
                        lstMasterOtor.addAll(masterBo.lstMstOtor(strUserName));
                    }
                    Messagebox.show(msg);
                } else {
                }
            }
        });
        
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void otorRejected(@BindingParam("id") String idSelected){
        masterotor=new MasterOtor();
        for(MasterOtor mo:lstMasterOtor)
        {
            if((mo.getCODE1()+mo.getACTION()).equals(idSelected))
            {
                masterotor=mo;
                break;
            }
        }
        
        Messagebox.show("Apakah yakin reject data master dengan kode "+masterotor.getCODE1()+" dan action "+masterotor.getACTION()+" ?",
        "Confirm Reject",
        Messagebox.YES | Messagebox.NO,
        Messagebox.EXCLAMATION,new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    msg=masterBo.rejectedMstData(masterotor);
//                    Messagebox.show(msg);
                    lstMasterOtor.clear();
                    if(masterBo.lstMstOtor(strUserName)!=null)
                    {
                        lstMasterOtor.addAll(masterBo.lstMstOtor(strUserName));
                    }
                    Messagebox.show(msg);
                } else {
                }
            }
        });
        
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void editMst(@BindingParam("id") AllMasters all){
//        if(getStrTitleMenu().equals("Branch"))
//            
//        Messagebox.show("ISINYAA YG SELECT "+all.getBRANCH_CODE());
        
//        setBtnAction("edit");\
        masterotor.setTYPE_PRS("Edit");
        initFormDetail(all);
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void delMst(@BindingParam("id") AllMasters all){
        masterotor.setTYPE_PRS("Delete");
        masterotor.setACTION(getStrTitleMenu());
        initFormDetail(all);

        Messagebox.show("Apakah yakin hapus data master dengan kode "+masterotor.getCODE1()+" dan action "+masterotor.getACTION()+" ?",
        "Confirm Delete",
        Messagebox.YES | Messagebox.NO,
        Messagebox.EXCLAMATION,new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    masterotor.setUSER_INPUT(strUserName);
                    masterotor.setUSER_OTOR("-");
    //                    if(!getStrTitleMenu().equals("Branch"))
    //                    {
    //                        masterotor.setCODE2("-");
    //                    }
    //                    else if(getStrTitleMenu().equals("Branch"))
    //                    {
    //                        masterotor.setCODE2(getCity().getCITY_CODE());
    //                    }
                    msg=masterBo.insMstData(masterotor, getStrTitleMenu(), masterotor.getTYPE_PRS(),masterotorbe4);
                    Messagebox.show(msg);
                } else {
                }
            }
        });
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    @NotifyChange("masterotor")
    public void trimLeadingWhitespace(
            @BindingParam("field") String field,
            @BindingParam("text") String text) {

        if (text == null) text = "";
        String trimmed = text.replaceAll("^\\s+", "").replaceAll("[^a-zA-Z0-9\\s\\-()]", "");
        String trimmednumerik = text.replaceAll("^\\s+", "").replaceAll("[^0-9]", "");
//        String trimmed = text.replaceAll("^\\s+", "").replaceAll("[^a-zA-Z0-9]", "");

        if(field.equals("FIELD1")&&!getStrTitleMenu().contains("Channel")&&!getStrTitleMenu().contains("Branch")&&!getStrTitleMenu().contains("City"))
        {
            masterotor.setCODE1(trimmed);
        }
        else if(field.equals("FIELD1")&&(getStrTitleMenu().contains("Channel")||getStrTitleMenu().contains("Branch")||getStrTitleMenu().contains("City")))
        {
            masterotor.setCODE1(trimmednumerik);
        }
        else if(field.equals("FIELD2"))
            masterotor.setNAME(trimmed);
        else if(field.equals("FIELD3"))
            masterotor.setCODE2(trimmed);
    }
    
    public void initFormDetail(AllMasters pAll){
        setShowDetMst(true);
        setInputCityCode(false);
        if(masterotor.getTYPE_PRS().equals("Add"))
        {
            masterotor.setCODE1("");
            masterotor.setNAME("");
            city=null;
        }
        
        if(getStrTitleMenu().equals("Branch"))
        {
            setLblField1("Branch Code");
            setLblField2("Branch Name");
            setLblField3("City Code");
            setInputCityCode(true);
            if(!masterotor.getTYPE_PRS().equals("Add"))
            {
                masterotor.setCODE1(pAll.getBRANCH_CODE());
                masterotor.setNAME(pAll.getBRANCH_NAME());
                masterotorbe4.setCODE1(pAll.getBRANCH_CODE());
                masterotorbe4.setNAME(pAll.getBRANCH_NAME());
                for(AllMasters alm:lstMasters)
                {
                    if(alm.getBRANCH_CODE().equals(pAll.getBRANCH_CODE()))
                    {
                        if(getCities()!=null)
                        {
                            for(City c:getCities())
                            {
                                if(c.getCity_code().equals(alm.getCITY_CODE()))
                                {
                                    setCity(c);
                                }
                            }
                        }
//                        city=new City(alm.getCITY_CODE(), alm.getCITY_NAME(), "N",new Date(),new Date(),alm.getUSER_INPUT(), alm.getUSER_OTOR());
//                        System.out.println("****************************city selected "+city.getCity_name());
                    }
                }
                masterotor.setCODE2(getCity().getCity_code());
                masterotorbe4.setCODE2(getCity().getCity_code());
            }
        }
        else if(getStrTitleMenu().equals("City"))
        {
            setLblField1("City Code");
            setLblField2("City Name");
            if(!masterotor.getTYPE_PRS().equals("Add"))
            {
                masterotor.setCODE1(pAll.getCITY_CODE());
                masterotor.setNAME(pAll.getCITY_NAME());
                masterotor.setCODE2("-");
                masterotorbe4.setCODE1(pAll.getCITY_CODE());
                masterotorbe4.setNAME(pAll.getCITY_NAME());
                masterotorbe4.setCODE2("-");
            }
        }
        else if(getStrTitleMenu().equals("Channel"))
        {
            setLblField1("Channel Code");
            setLblField2("Channel Name");
            if(!masterotor.getTYPE_PRS().equals("Add"))
            {
                masterotor.setCODE1(pAll.getCHANNELS_CODE());
                masterotor.setNAME(pAll.getCHANNEL_NAME());
                masterotor.setCODE2("-");
                masterotorbe4.setCODE1(pAll.getCHANNELS_CODE());
                masterotorbe4.setNAME(pAll.getCHANNEL_NAME());
                masterotorbe4.setCODE2("-");
            }
        }
        else if(getStrTitleMenu().equals("Code_601")||getStrTitleMenu().equals("Code_602")||getStrTitleMenu().equals("Code_603")||getStrTitleMenu().equals("Code_604")||getStrTitleMenu().equals("Code_605"))
        {
            setLblField1("Product Code");
            setLblField2("Product Name");
            if(!masterotor.getTYPE_PRS().equals("Add"))
            {
                masterotor.setCODE1(pAll.getPRODUCT_CODE());
                masterotor.setNAME(pAll.getPRODUCT_NAME());
                masterotor.setCODE2("-");
                masterotorbe4.setCODE1(pAll.getPRODUCT_CODE());
                masterotorbe4.setNAME(pAll.getPRODUCT_NAME());
                masterotorbe4.setCODE2("-");
            }
        }
        else if(getStrTitleMenu().equals("Code_601_Info")||getStrTitleMenu().equals("Code_602_Info")||getStrTitleMenu().equals("Code_603_Info")||getStrTitleMenu().equals("Code_604_Info")||getStrTitleMenu().equals("Code_605_Info"))
        {
            setLblField1("Product Info Code");
            setLblField2("Product Info Name");
            if(!masterotor.getTYPE_PRS().equals("Add"))
            {
                masterotor.setCODE1(pAll.getPRODUCTINFO_CODE());
                masterotor.setNAME(pAll.getPRODUCTINFO_NAME());
                masterotor.setCODE2("-");
                masterotorbe4.setCODE1(pAll.getPRODUCTINFO_CODE());
                masterotorbe4.setNAME(pAll.getPRODUCTINFO_NAME());
                masterotorbe4.setCODE2("-");
            }
        }
    }
}
