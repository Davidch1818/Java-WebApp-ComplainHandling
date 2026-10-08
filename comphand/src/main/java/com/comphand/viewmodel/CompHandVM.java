package com.comphand.viewmodel;

import com.comphand.bo.ComphandBo;
import com.comphand.bo.MasterBo;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

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
import com.comphand.model.System_Logs;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CompHandVM {

    @WireVariable
    private ComphandBo comphandBo;
    @WireVariable
    private MasterBo masterBo;
    private Compdata compdata, compdataBe4;
    private CompOtor compotor;
    private String strFilePath;
    private String strFileName;
    private String strFileNamePengaduan,strFileNamePenyelesaian,strFileNamePerpanjangan,strSearch,strUserName,msg,strTitleMenu;
    private Date startDate;
    private Date endDate;
    private List<Compdata> complaints;
    private boolean activeReqID,activeSettNumber,activeextNumber,activeCustName,activeLoc,activeCbChannel,activeCbCity,activeCbBranch,activeAmount,
            activeATM,activeDesc,activeRecDate,activeIncDate,activeCompDate,activeCb601,activeCb601A,activeCb602,activeCb603,activeCb604,activeCb605,
            activeCb602A,activeCb603A,activeCb604A,activeCb605A,isFileClickable,bannedAccFile,showBtnSubmit,showDetComp,showBtnView,showBtnEdel,isBtnEditMode,
            isEditDisputeFile,isEditSettleFile,isEditExtFile;
    private List<Channel> channels;
    private List<City> cities;
    private List<Branch> branches,allBranches;
    private List<Code_601> code601Products;
    private List<Code_601_Info> code601Problems;
    private List<Code_602> code602Products;
    private List<Code_602_Info> code602Problems;
    private List<Code_603> code603Products;
    private List<Code_603_Info> code603Problems;
    private List<Code_604> code604Products;
    private List<Code_604_Info> code604Problems;
    private List<Code_605> code605Products;
    private List<Code_605_Info> code605Problems;
    private List<CompReport> reports;
    private List<CompOtor> lstOtor;
    private List<System_Logs> lstLogs;
    private System_Logs sysLogs;
    
    public String getrequestid(){
        if(isIsBtnEditMode())
        {
            return compdata.getRequest_id();
        }
        else{
            return compotor.getRequest_id();
        }
    }
    public void setrequestid(String param){
        if(isIsBtnEditMode())
        {
            compdata.setRequest_id(param);
        }
        else{
            compotor.setRequest_id(param);
        }
    }
    
    public String getsettnumber(){
        if(isIsBtnEditMode())
        {
            return compdata.getSettlement_number();
        }
        else{
            return compotor.getSettlement_number();
        }
    }
    
    public void setsettnumber(String param){
        if(isIsBtnEditMode())
        {
            compdata.setSettlement_number(param);
        }
        else{
            compotor.setSettlement_number(param);
        }
    }
    
    public String getexttnumber(){
        if(isIsBtnEditMode())
        {
            return compdata.getExtension_number();
        }
        else{
            return compotor.getExtension_number();
        }
    }
    
    public void setexttnumber(String param){
        if(isIsBtnEditMode())
        {
            compdata.setExtension_number(param);
        }
        else{
            compotor.setExtension_number(param);
        }
    }
    public String getcustname(){
        if(isIsBtnEditMode())
        {
            return compdata.getCustomer_name();
        }
        else{
            return compotor.getCustomer_name();
        }
    }
    
    public void setcustname(String param){
        if(isIsBtnEditMode())
        {
            compdata.setCustomer_name(param);
        }
        else{
            compotor.setCustomer_name(param);
        }
    }
    public String getLocation(){
        if(isIsBtnEditMode())
        {
            return compdata.getLocation();
        }
        else{
            return compotor.getLocation();
        }
    }
    
    public void setLocation(String param){
        if(isIsBtnEditMode())
        {
            compdata.setLocation(param);
        }
        else{
            compotor.setLocation(param);
        }
    }
    public Channel getchannel(){
        if(isIsBtnEditMode())
        {
            return compdata.getChannel_code();
        }
        else{
            return compotor.getChannel_code();
        }
    }
    
    public void setchannel(Channel param){
        if(isIsBtnEditMode())
        {
            compdata.setChannel_code(param);
        }
        else{
            compotor.setChannel_code(param);
        }
    }
    public City getcity(){
        if(isIsBtnEditMode())
        {
            return compdata.getCity_code();
        }
        else{
            return compotor.getCity_code();
        }
    }
    
    public void setcity(City param){
        if(isIsBtnEditMode())
        {
            compdata.setCity_code(param);
        }
        else{
            compotor.setCity_code(param);
        }
    }
    public Branch getbranch(){
        if(isIsBtnEditMode())
        {
            return compdata.getBranch_code();
        }
        else{
            return compotor.getBranch_code();
        }
    }
    
    public void setbranch(Branch param){
        if(isIsBtnEditMode())
        {
            compdata.setBranch_code(param);
        }
        else{
            compotor.setBranch_code(param);
        }
    }
    public int getAmount(){
        if(isIsBtnEditMode())
        {
            return compdata.getAmount();
        }
        else{
            return compotor.getAmount();
        }
    }
    
    public void setamount(int param){
        if(isIsBtnEditMode())
        {
            compdata.setAmount(param);
        }
        else{
            compotor.setAmount(param);
        }
    }
    public String getatmnumber(){
        if(isIsBtnEditMode())
        {
            return compdata.getAtm_card_number();
        }
        else{
            return compotor.getAtm_card_number();
        }
    }
    
    public void setatmnumber(String param){
        if(isIsBtnEditMode())
        {
            compdata.setAtm_card_number(param);
        }
        else{
            compotor.setAtm_card_number(param);
        }
    }
    public String getDescription(){
        if(isIsBtnEditMode())
        {
            return compdata.getDescription();
        }
        else{
            return compotor.getDescription();
        }
    }
    
    public void setDescription(String param){
        if(isIsBtnEditMode())
        {
            compdata.setDescription(param);
        }
        else{
            compotor.setDescription(param);
        }
    }
    public Date getreceiveddate(){
        if(isIsBtnEditMode())
        {
            return compdata.getReceived_date();
        }
        else{
            return compotor.getReceived_date();
        }
    }
    
    public void setreceiveddate(Date param){
        if(isIsBtnEditMode())
        {
            compdata.setReceived_date(param);
        }
        else{
            compotor.setReceived_date(param);
        }
    }
    public Date getincdate(){
        if(isIsBtnEditMode())
        {
            return compdata.getIncident_date();
        }
        else{
            return compotor.getIncident_date();
        }
    }
    
    public void setincdate(Date param){
        if(isIsBtnEditMode())
        {
            compdata.setIncident_date(param);
        }
        else{
            compotor.setIncident_date(param);
        }
    }
    public Date getcompletiondate(){
        if(isIsBtnEditMode())
        {
            return compdata.getCompletion_date();
        }
        else{
            return compotor.getCompletion_date();
        }
    }
    
    public void setcompletiondate(Date param){
        if(isIsBtnEditMode())
        {
            compdata.setCompletion_date(param);
        }
        else{
            compotor.setCompletion_date(param);
        }
    }
    public Code_601 getcode601product(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_601();
        }
        else{
            return compotor.getCode_601();
        }
    }
    
    public void setcode601product(Code_601 param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_601(param);
        }
        else{
            compotor.setCode_601(param);
        }
    }
    public Code_601_Info getcode601problem(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_601_problem();
        }
        else{
            return compotor.getCode_601_problem();
        }
    }
    
    public void setcode601problem(Code_601_Info param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_601_problem(param);
        }
        else{
            compotor.setCode_601_problem(param);
        }
    }
    public Code_602 getcode602product(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_602();
        }
        else{
            return compotor.getCode_602();
        }
    }
    
    public void setcode602product(Code_602 param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_602(param);
        }
        else{
            compotor.setCode_602(param);
        }
    }
    public Code_602_Info getcode602problem(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_602_problem();
        }
        else{
            return compotor.getCode_602_problem();
        }
    }
    
    public void setcode602problem(Code_602_Info param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_602_problem(param);
        }
        else{
            compotor.setCode_602_problem(param);
        }
    }
    public Code_603 getcode603product(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_603();
        }
        else{
            return compotor.getCode_603();
        }
    }
    
    public void setcode603product(Code_603 param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_603(param);
        }
        else{
            compotor.setCode_603(param);
        }
    }
    public Code_603_Info getcode603problem(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_603_problem();
        }
        else{
            return compotor.getCode_603_problem();
        }
    }
    
    public void setcode603problem(Code_603_Info param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_603_problem(param);
        }
        else{
            compotor.setCode_603_problem(param);
        }
    }
    public Code_604 getcode604product(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_604();
        }
        else{
            return compotor.getCode_604();
        }
    }
    
    public void setcode604product(Code_604 param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_604(param);
        }
        else{
            compotor.setCode_604(param);
        }
    }
    public Code_604_Info getcode604problem(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_604_problem();
        }
        else{
            return compotor.getCode_604_problem();
        }
    }
    
    public void setcode604problem(Code_604_Info param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_604_problem(param);
        }
        else{
            compotor.setCode_604_problem(param);
        }
    }
    public Code_605 getcode605product(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_605();
        }
        else{
            return compotor.getCode_605();
        }
    }
    
    public void setcode605product(Code_605 param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_605(param);
        }
        else{
            compotor.setCode_605(param);
        }
    }
    public Code_605_Info getcode605problem(){
        if(isIsBtnEditMode())
        {
            return compdata.getCode_605_problem();
        }
        else{
            return compotor.getCode_605_problem();
        }
    }
    
    public void setcode605problem(Code_605_Info param){
        if(isIsBtnEditMode())
        {
            compdata.setCode_605_problem(param);
        }
        else{
            compotor.setCode_605_problem(param);
        }
    }
    
    public String getStrFileName() {
        return strFileName;
    }

    public void setStrFileName(String strFileName) {
        this.strFileName = strFileName;
    }

    public Compdata getCompdata() {
        return compdata;
    }

    public void setCompdata(Compdata compdata) {
        this.compdata = compdata;
    }

    public CompOtor getCompotor() {
        return compotor;
    }

    public void setCompotor(CompOtor compotor) {
        this.compotor = compotor;
    }

    public String getStrFileNamePengaduan() {
        return strFileNamePengaduan;
    }

    public void setStrFileNamePengaduan(String strFileNamePengaduan) {
        this.strFileNamePengaduan = strFileNamePengaduan;
    }

    public String getStrFileNamePenyelesaian() {
        return strFileNamePenyelesaian;
    }

    public void setStrFileNamePenyelesaian(String strFileNamePenyelesaian) {
        this.strFileNamePenyelesaian = strFileNamePenyelesaian;
    }

    public String getStrFileNamePerpanjangan() {
        return strFileNamePerpanjangan;
    }

    public void setStrFileNamePerpanjangan(String strFileNamePerpanjangan) {
        this.strFileNamePerpanjangan = strFileNamePerpanjangan;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Compdata> getComplaints() {
        return complaints;
    }

    public void setComplaints(ListModelList<Compdata> complaints) {
        this.complaints = complaints;
    }

    public String getStrSearch() {
        return strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public boolean isActiveReqID() {
        return activeReqID;
    }

    public void setActiveReqID(boolean activeReqID) {
        this.activeReqID = activeReqID;
    }

    public boolean isActiveSettNumber() {
        return activeSettNumber;
    }

    public void setActiveSettNumber(boolean activeSettNumber) {
        this.activeSettNumber = activeSettNumber;
    }

    public boolean isActiveextNumber() {
        return activeextNumber;
    }

    public void setActiveextNumber(boolean activeextNumber) {
        this.activeextNumber = activeextNumber;
    }

    public boolean isActiveCustName() {
        return activeCustName;
    }

    public void setActiveCustName(boolean activeCustName) {
        this.activeCustName = activeCustName;
    }

    public boolean isActiveLoc() {
        return activeLoc;
    }

    public void setActiveLoc(boolean activeLoc) {
        this.activeLoc = activeLoc;
    }

    public boolean isActiveCbChannel() {
        return activeCbChannel;
    }

    public void setActiveCbChannel(boolean activeCbChannel) {
        this.activeCbChannel = activeCbChannel;
    }

    public boolean isActiveCbCity() {
        return activeCbCity;
    }

    public void setActiveCbCity(boolean activeCbCity) {
        this.activeCbCity = activeCbCity;
    }

    public boolean isActiveCbBranch() {
        return activeCbBranch;
    }

    public void setActiveCbBranch(boolean activeCbBranch) {
        this.activeCbBranch = activeCbBranch;
    }

    public boolean isActiveAmount() {
        return activeAmount;
    }

    public void setActiveAmount(boolean activeAmount) {
        this.activeAmount = activeAmount;
    }

    public boolean isActiveATM() {
        return activeATM;
    }

    public void setActiveATM(boolean activeATM) {
        this.activeATM = activeATM;
    }

    public boolean isActiveDesc() {
        return activeDesc;
    }

    public void setActiveDesc(boolean activeDesc) {
        this.activeDesc = activeDesc;
    }

    public boolean isActiveRecDate() {
        return activeRecDate;
    }

    public void setActiveRecDate(boolean activeRecDate) {
        this.activeRecDate = activeRecDate;
    }

    public boolean isActiveIncDate() {
        return activeIncDate;
    }

    public void setActiveIncDate(boolean activeIncDate) {
        this.activeIncDate = activeIncDate;
    }

    public boolean isActiveCompDate() {
        return activeCompDate;
    }

    public void setActiveCompDate(boolean activeCompDate) {
        this.activeCompDate = activeCompDate;
    }

    public boolean isActiveCb601() {
        return activeCb601;
    }

    public void setActiveCb601(boolean activeCb601) {
        this.activeCb601 = activeCb601;
    }

    public boolean isActiveCb601A() {
        return activeCb601A;
    }

    public void setActiveCb601A(boolean activeCb601A) {
        this.activeCb601A = activeCb601A;
    }

    public boolean isActiveCb602() {
        return activeCb602;
    }

    public void setActiveCb602(boolean activeCb602) {
        this.activeCb602 = activeCb602;
    }

    public boolean isActiveCb603() {
        return activeCb603;
    }

    public void setActiveCb603(boolean activeCb603) {
        this.activeCb603 = activeCb603;
    }

    public boolean isActiveCb604() {
        return activeCb604;
    }

    public void setActiveCb604(boolean activeCb604) {
        this.activeCb604 = activeCb604;
    }

    public boolean isActiveCb605() {
        return activeCb605;
    }

    public void setActiveCb605(boolean activeCb605) {
        this.activeCb605 = activeCb605;
    }

    public boolean isActiveCb602A() {
        return activeCb602A;
    }

    public void setActiveCb602A(boolean activeCb602A) {
        this.activeCb602A = activeCb602A;
    }

    public boolean isActiveCb603A() {
        return activeCb603A;
    }

    public void setActiveCb603A(boolean activeCb603A) {
        this.activeCb603A = activeCb603A;
    }

    public boolean isActiveCb604A() {
        return activeCb604A;
    }

    public void setActiveCb604A(boolean activeCb604A) {
        this.activeCb604A = activeCb604A;
    }

    public boolean isActiveCb605A() {
        return activeCb605A;
    }

    public void setActiveCb605A(boolean activeCb605A) {
        this.activeCb605A = activeCb605A;
    }

    public boolean isIsFileClickable() {
        return isFileClickable;
    }

    public void setIsFileClickable(boolean isFileClickable) {
        this.isFileClickable = isFileClickable;
    }

    public boolean isBannedAccFile() {
        return bannedAccFile;
    }

    public void setBannedAccFile(boolean bannedAccFile) {
        this.bannedAccFile = bannedAccFile;
    }

    public boolean isShowBtnSubmit() {
        return showBtnSubmit;
    }

    public void setShowBtnSubmit(boolean showBtnSubmit) {
        this.showBtnSubmit = showBtnSubmit;
    }

    public boolean isShowDetComp() {
        return showDetComp;
    }

    public void setShowDetComp(boolean showDetComp) {
        this.showDetComp = showDetComp;
    }

    public boolean isShowBtnView() {
        return showBtnView;
    }

    public void setShowBtnView(boolean showBtnView) {
        this.showBtnView = showBtnView;
    }

    public boolean isShowBtnEdel() {
        return showBtnEdel;
    }

    public void setShowBtnEdel(boolean showBtnEdel) {
        this.showBtnEdel = showBtnEdel;
    }

    public boolean isIsBtnEditMode() {
        return isBtnEditMode;
    }

    public void setIsBtnEditMode(boolean isBtnEditMode) {
        this.isBtnEditMode = isBtnEditMode;
    }

    public List<Channel> getChannels() {
        if(masterBo.lstChannels()== null)
            channels = new ListModelList<Channel>();
        else if(channels == null&&masterBo.lstChannels()!=null) {
            channels = new ListModelList<Channel>(masterBo.lstChannels());
        }
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<City> getCities() {
        if(masterBo.lstCities()== null)
            cities = new ListModelList<City>();
        else if(cities == null&&masterBo.lstCities()!=null) {
            cities = new ListModelList<City>(masterBo.lstCities());
        }
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<Branch> getBranches() {
//        if(masterBo.lstBranches()== null)
//            branches = new ListModelList<Branch>();
//        else if(branches == null&&masterBo.lstBranches()!=null) {
//            branches = new ListModelList<Branch>(masterBo.lstBranches());
//        }
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getAllBranches() {
        if(masterBo.lstBranches()== null)
            allBranches = new ListModelList<Branch>();
        else if(allBranches == null&&masterBo.lstBranches()!=null) {
            allBranches = new ListModelList<Branch>(masterBo.lstBranches());
        }
        return allBranches;
    }

    public void setAllBranches(List<Branch> allBranches) {
        this.allBranches = allBranches;
    }

    public List<Code_601> getCode601Products() {
        if(masterBo.lstCode601Products()== null)
            code601Products = new ListModelList<Code_601>();
        else if(code601Products == null&&masterBo.lstCode601Products()!=null) {
            code601Products = new ListModelList<Code_601>(masterBo.lstCode601Products());
        }
        return code601Products;
    }

    public void setCode601Products(List<Code_601> code601Products) {
        this.code601Products = code601Products;
    }

    public List<Code_601_Info> getCode601Problems() {
        if(masterBo.lstCode601Problems()== null)
            code601Problems = new ListModelList<Code_601_Info>();
        else if(code601Problems == null&&masterBo.lstCode601Problems()!=null) {
            code601Problems = new ListModelList<Code_601_Info>(masterBo.lstCode601Problems());
        }
        return code601Problems;
    }

    public void setCode601Problems(List<Code_601_Info> code601Problems) {
        this.code601Problems = code601Problems;
    }

    public List<Code_602> getCode602Products() {
        if(masterBo.lstCode602Products()== null)
            code602Products = new ListModelList<Code_602>();
        else if(code602Products == null&&masterBo.lstCode602Products()!=null) {
            code602Products = new ListModelList<Code_602>(masterBo.lstCode602Products());
        }
        return code602Products;
    }

    public void setCode602Products(List<Code_602> code602Products) {
        this.code602Products = code602Products;
    }

    public List<Code_602_Info> getCode602Problems() {
        if(masterBo.lstCode602Problems()== null)
            code602Problems = new ListModelList<Code_602_Info>();
        else if(code602Problems == null&&masterBo.lstCode602Problems()!=null) {
            code602Problems = new ListModelList<Code_602_Info>(masterBo.lstCode602Problems());
        }
        return code602Problems;
    }

    public void setCode602Problems(List<Code_602_Info> code602Problems) {
        this.code602Problems = code602Problems;
    }

    public List<Code_603> getCode603Products() {
        if(masterBo.lstCode603Products()== null)
            code603Products = new ListModelList<Code_603>();
        else if(code603Products == null&&masterBo.lstCode603Products()!=null) {
            code603Products = new ListModelList<Code_603>(masterBo.lstCode603Products());
        }
        return code603Products;
    }

    public void setCode603Products(List<Code_603> code603Products) {
        this.code603Products = code603Products;
    }

    public List<Code_603_Info> getCode603Problems() {
        if(masterBo.lstCode603Problems()== null)
            code603Problems = new ListModelList<Code_603_Info>();
        else if(code603Problems == null&&masterBo.lstCode603Problems()!=null) {
            code603Problems = new ListModelList<Code_603_Info>(masterBo.lstCode603Problems());
        }
        return code603Problems;
    }

    public void setCode603Problems(List<Code_603_Info> code603Problems) {
        this.code603Problems = code603Problems;
    }

    public List<Code_604> getCode604Products() {
        if(masterBo.lstCode604Products()== null)
            code604Products = new ListModelList<Code_604>();
        else if(code604Products == null&&masterBo.lstCode604Products()!=null) {
            code604Products = new ListModelList<Code_604>(masterBo.lstCode604Products());
        }
        return code604Products;
    }

    public void setCode604Products(List<Code_604> code604Products) {
        this.code604Products = code604Products;
    }

    public List<Code_604_Info> getCode604Problems() {
        if(masterBo.lstCode604Problems()== null)
            code604Problems = new ListModelList<Code_604_Info>();
        else if(code604Problems == null&&masterBo.lstCode604Problems()!=null) {
            code604Problems = new ListModelList<Code_604_Info>(masterBo.lstCode604Problems());
        }
        return code604Problems;
    }

    public void setCode604Problems(List<Code_604_Info> code604Problems) {
        this.code604Problems = code604Problems;
    }

    public List<Code_605> getCode605Products() {
        if(masterBo.lstCode605Products()== null)
            code605Products = new ListModelList<Code_605>();
        else if(code605Products == null&&masterBo.lstCode605Products()!=null) {
            code605Products = new ListModelList<Code_605>(masterBo.lstCode605Products());
        }
        return code605Products;
    }

    public void setCode605Products(List<Code_605> code605Products) {
        this.code605Products = code605Products;
    }

    public List<Code_605_Info> getCode605Problems() {
        if(masterBo.lstCode605Problems()== null)
            code605Problems = new ListModelList<Code_605_Info>();
        else if(code605Problems == null&&masterBo.lstCode605Problems()!=null) {
            code605Problems = new ListModelList<Code_605_Info>(masterBo.lstCode605Problems());
        }
        return code605Problems;
    }

    public void setCode605Problems(List<Code_605_Info> code605Problems) {
        this.code605Problems = code605Problems;
    }

    public List<CompReport> getReports() {
        return reports;
    }

    public void setReports(List<CompReport> reports) {
        this.reports = reports;
    }

    public String getStrTitleMenu() {
        return strTitleMenu;
    }

    public void setStrTitleMenu(String strTitleMenu) {
        this.strTitleMenu = strTitleMenu;
    }

    public List<CompOtor> getLstOtor() {
        return lstOtor;
    }

    public void setLstOtor(List<CompOtor> lstOtor) {
        this.lstOtor = lstOtor;
    }

    public List<System_Logs> getLstLogs() {
        return lstLogs;
    }

    public void setLstLogs(List<System_Logs> lstLogs) {
        this.lstLogs = lstLogs;
    }

    public boolean isIsEditDisputeFile() {
        return isEditDisputeFile;
    }

    public void setIsEditDisputeFile(boolean isEditDisputeFile) {
        this.isEditDisputeFile = isEditDisputeFile;
    }

    public boolean isIsEditSettleFile() {
        return isEditSettleFile;
    }

    public void setIsEditSettleFile(boolean isEditSettleFile) {
        this.isEditSettleFile = isEditSettleFile;
    }

    public boolean isIsEditExtFile() {
        return isEditExtFile;
    }

    public void setIsEditExtFile(boolean isEditExtFile) {
        this.isEditExtFile = isEditExtFile;
    }
    
    @Init
    public void init()
    {
        strUserName = "TEST2";
        compotor = new CompOtor();
        strFilePath = "C:\\BMIWebData\\ComplainFileOps\\";
        setStrSearch("");
        setStrFileNamePengaduan("");
        setStrFileNamePenyelesaian("");
        setStrFileNamePerpanjangan("");
        getAllBranches();
        branches = new ListModelList<Branch>();
        setStrTitleMenu("");
        if (getcity()== null) {
        setcity(new City()); // Sesuaikan dengan nama class/model kamu
    }
    }
    
    @Command
    public void showFormAdd(){
        newInput();
//        setIsFileClickable(false);
//        setBannedAccFile(false);
//        setShowBtnSubmit(true);
//        setShowDetComp(true);
//        setTxtBtnSubmit("Submit Complaint");
//        compdata.setREQUEST_ID(" ");
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void showFormView(){
        setShowBtnView(true);
        setShowBtnEdel(false);
        setStrTitleMenu("View Complaints");
        if(comphandBo.lstCompData(false)!=null)
        {
            complaints=new ListModelList<Compdata>(comphandBo.lstCompData(false));
        }
//        setIsDashboard(false);
//        setIsReport(false);
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void showFormEdel(){
        setShowBtnView(false);
        setShowBtnEdel(true);
        setStrTitleMenu("Edit Delete Complaints");
        if(comphandBo.lstCompData(false)!=null)
        {
            complaints=new ListModelList<Compdata>(comphandBo.lstCompData(false));
        }
//        setIsDashboard(false);
//        setIsReport(false);
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void showFormDash(){
//        setIsDashboard(true);
//        setIsReport(false);
        setStrTitleMenu("Outstanding Complaints");
        if(comphandBo.lstCompData(true)!=null)
        {
            complaints=new ListModelList<Compdata>(comphandBo.lstCompData(true));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void showFormReport(){
//        setIsReport(true);
//        setIsDashboard(false);
        setStrTitleMenu("Complaint Handling Report");
        if(comphandBo.lstCompRpt()!=null)
        {
            reports=new ListModelList<CompReport>(comphandBo.lstCompRpt());
        }
        if(comphandBo.lstCompData(false)!=null)
        {
            complaints=new ListModelList<Compdata>(comphandBo.lstCompData(false));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void showFormResReport(){
//        setIsReport(true);
//        setIsDashboard(false);
        setStrTitleMenu("Complaint Resolution Report");
        if(comphandBo.lstCompResRpt()!=null)
        {
            reports=new ListModelList<CompReport>(comphandBo.lstCompResRpt());
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void showFormOtor(){
        setStrTitleMenu("Authorize Complaints");
        if(comphandBo.lstCompOtor(strUserName)!=null)
        {
            lstOtor=new ListModelList<CompOtor>(comphandBo.lstCompOtor(strUserName));
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    @Command
    public void showFormLogs(){
        setStrTitleMenu("Complain Handling Logs");
        if(comphandBo.lstCompLogs()!=null)
        {
            lstLogs=new ListModelList<System_Logs>(comphandBo.lstCompLogs());
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void prsupl() throws IOException{
        String outMsg=" ";
        if(!isIsBtnEditMode())
        {
            compotor.setAging(0);
            compotor.setUser_input(strUserName);
            compotor.setIs_deleted("N");
            if(compotor.getRequest_id()==null||compotor.getRequest_id().isEmpty()||compotor.getCustomer_name().isEmpty()||compotor.getAmount()<0||compotor.getLocation().isEmpty()
                    ||compotor.getCity_code()==null||compotor.getBranch_code()==null||compotor.getAtm_card_number().isEmpty()||compotor.getDescription().isEmpty()
                    ||compotor.getReceived_date()==null||compotor.getIncident_date()==null||compotor.getChannel_code()==null||getStrFileNamePengaduan().isEmpty())
            {
                if(compotor.getDispute_filepath().isEmpty()&&!getStrFileNamePengaduan().isEmpty())
                {
                    delFile("Pengaduan",strFilePath+"Temp/");
                    setStrFileNamePengaduan("");
                }
                if(compotor.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compotor.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("Inputan yang diperlukan belum terisi semua");
            }
            else if(!validateDateInput(compotor.getIncident_date(), compotor.getReceived_date()))
            {
//                Messagebox.show("Received date tidak dapat lebih awal dari Incident date");
                if(compotor.getDispute_filepath().isEmpty()&&!getStrFileNamePengaduan().isEmpty())
                {
                    delFile("Pengaduan",strFilePath+"Temp/");
                    setStrFileNamePengaduan("");
                }
                if(compotor.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compotor.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("Received date tidak dapat lebih awal dari Incident date");
            }
            else if(getStrFileNamePenyelesaian().trim().isEmpty()&&!compotor.getSettlement_number().trim().isEmpty())
            {
//                Messagebox.show("File penyelesaian belum diupload untuk settlement number "+compotor.getSettlement_number().trim());
                if(compotor.getDispute_filepath().isEmpty()&&!getStrFileNamePengaduan().isEmpty())
                {
                    delFile("Pengaduan",strFilePath+"Temp/");
                    setStrFileNamePengaduan("");
                }
                if(compotor.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compotor.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File penyelesaian belum diupload untuk settlement number "+compotor.getSettlement_number().trim());
            }
            else if(!getStrFileNamePenyelesaian().trim().isEmpty()&&compotor.getSettlement_number().trim().isEmpty())
            {
//                Messagebox.show("File penyelesaian dengan nama "+getStrFileNamePenyelesaian().trim()+" belum terinput settlement numbernya");
                if(compotor.getDispute_filepath().isEmpty()&&!getStrFileNamePengaduan().isEmpty())
                {
                    delFile("Pengaduan",strFilePath+"Temp/");
                    setStrFileNamePengaduan("");
                }
                if(compotor.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compotor.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File penyelesaian dengan nama "+getStrFileNamePenyelesaian().trim()+" belum terinput settlement numbernya");
            }
            else if(getStrFileNamePerpanjangan().trim().isEmpty()&&!compotor.getExtension_number().trim().isEmpty())
            {
//                Messagebox.show("File perpanjangan belum diupload untuk extension number "+compotor.getExtension_number().trim());
                if(compotor.getDispute_filepath().isEmpty()&&!getStrFileNamePengaduan().isEmpty())
                {
                    delFile("Pengaduan",strFilePath+"Temp/");
                    setStrFileNamePengaduan("");
                }
                if(compotor.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compotor.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File perpanjangan belum diupload untuk extension number "+compotor.getExtension_number().trim());
            }
            else if(!getStrFileNamePerpanjangan().trim().isEmpty()&&compotor.getExtension_number().trim().isEmpty())
            {
//                Messagebox.show("File perpanjangan dengan nama "+getStrFileNamePerpanjangan().trim()+" belum terinput extension numbernya");
                if(compotor.getDispute_filepath().isEmpty()&&!getStrFileNamePengaduan().isEmpty())
                {
                    delFile("Pengaduan",strFilePath+"Temp/");
                    setStrFileNamePengaduan("");
                }
                if(compotor.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compotor.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File perpanjangan dengan nama "+getStrFileNamePerpanjangan().trim()+" belum terinput extension numbernya");
            }
            else
            {
                File dirPengaduan=new File(strFilePath+"PengaduanNotOtor");
                File dirPenyelesaian=new File(strFilePath+"PenyelesaianNotOtor");
                File dirPerpanjangan=new File(strFilePath+"PerpanjanganNotOtor");

                if(!dirPengaduan.exists()) dirPengaduan.mkdirs();
                if(!dirPenyelesaian.exists()) dirPenyelesaian.mkdirs();
                if(!dirPerpanjangan.exists()) dirPerpanjangan.mkdirs();

                if(!getStrFileNamePengaduan().isEmpty())
                {
                    compotor.setDispute_filepath(strFilePath+"Pengaduan/"+getStrFileNamePengaduan());
                }
                if(!getStrFileNamePenyelesaian().isEmpty())
                {
                    compotor.setSettlement_filepath(strFilePath+"Penyelesaian/"+getStrFileNamePenyelesaian());
                }
                if(!getStrFileNamePerpanjangan().isEmpty())
                {
                    compotor.setExtension_filepath(strFilePath+"Perpanjangan/"+getStrFileNamePerpanjangan());
                }

                outMsg = comphandBo.reqInsCompData(compotor);
//                outMsg = comphandBo.insCompData(compdata);
                if(outMsg.contains("berhasil"))
                {
                    if(!getStrFileNamePengaduan().isEmpty())
                    {
                        moveFile(strFilePath+"Temp/"+getStrFileNamePengaduan(), strFilePath+"PengaduanNotOtor/"+getStrFileNamePengaduan());
                    }
                    if(!getStrFileNamePenyelesaian().isEmpty())
                    {
                        moveFile(strFilePath+"Temp/"+getStrFileNamePenyelesaian(), strFilePath+"PenyelesaianNotOtor/"+getStrFileNamePenyelesaian());
                    }
                    if(!getStrFileNamePerpanjangan().isEmpty())
                    {
                        moveFile(strFilePath+"Temp/"+getStrFileNamePerpanjangan(), strFilePath+"PerpanjanganNotOtor/"+getStrFileNamePerpanjangan());
                    }
                    compotor=new CompOtor();
                    compotor.setChannel_code(null);
                    compotor.setCity_code(null);
                    compotor.setBranch_code(null);
                    compotor.setCode_601(null);
                    compotor.setCode_601_problem(null);
                    compotor.setCode_602(null);
                    compotor.setCode_602_problem(null);
                    compotor.setCode_603(null);
                    compotor.setCode_603_problem(null);
                    compotor.setCode_604(null);
                    compotor.setCode_604_problem(null);
                    compotor.setCode_605(null);
                    compotor.setCode_605_problem(null);
                    setStrFileNamePengaduan("");
                    setStrFileNamePenyelesaian("");
                    setStrFileNamePerpanjangan("");

                }
                else if(!outMsg.contains("berhasil"))
                {
                    if(!getStrFileNamePengaduan().isEmpty())
                    {
                        delFile("Pengaduan",strFilePath+"Temp/");
                        setStrFileNamePengaduan("");
                    }
                    if(!getStrFileNamePenyelesaian().isEmpty())
                    {
                        delFile("Penyelesaian",strFilePath+"Temp/");
                        setStrFileNamePenyelesaian("");
                    }
                    if(!getStrFileNamePerpanjangan().isEmpty())
                    {
                        delFile("Perpanjangan",strFilePath+"Temp/");
                        setStrFileNamePerpanjangan("");
                    }
                }

                Messagebox.show(outMsg);
            }
        }
        
        else if(isIsBtnEditMode())
        {
//            compdata.setFLAGOTOR("Y");
            compotor.setUser_input(strUserName);
            
            if(compdata.getAmount()<0||compdata.getLocation().isEmpty()||compdata.getAtm_card_number().isEmpty()||compdata.getDescription().isEmpty())
            {
//                Messagebox.show("Inputan yang diperlukan belum terisi semua");
                if(compdata.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compdata.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("Inputan yang diperlukan belum terisi semua");
            }
            else if(!validateDateInput(compdata.getIncident_date(), compdata.getReceived_date()))
            {
//                Messagebox.show("Received date tidak dapat lebih awal dari Incident date");
                if(compdata.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compdata.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("Received date tidak dapat lebih awal dari Incident date");
            }
            else if(getStrFileNamePenyelesaian().trim().isEmpty()&&!compdata.getSettlement_number().trim().isEmpty())
            {
//                Messagebox.show("File penyelesaian belum diupload untuk settlement number "+compdata.getSettlement_number().trim());
                if(compdata.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compdata.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File penyelesaian belum diupload untuk settlement number "+compdata.getSettlement_number().trim());
            }
            else if(!getStrFileNamePenyelesaian().trim().isEmpty()&&compdata.getSettlement_number().trim().isEmpty())
            {
//                Messagebox.show("File penyelesaian dengan nama "+getStrFileNamePenyelesaian().trim()+" belum terinput settlement numbernya");
                if(compdata.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compdata.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File penyelesaian dengan nama "+getStrFileNamePenyelesaian().trim()+" belum terinput settlement numbernya");
            }
            else if(getStrFileNamePerpanjangan().trim().isEmpty()&&!compdata.getExtension_number().trim().isEmpty())
            {
//                Messagebox.show("File perpanjangan belum diupload untuk extension number "+compdata.getExtension_number().trim());
                if(compdata.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compdata.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File perpanjangan belum diupload untuk extension number "+compdata.getExtension_number().trim());
            }
            else if(!getStrFileNamePerpanjangan().trim().isEmpty()&&compdata.getExtension_number().trim().isEmpty())
            {
//                Messagebox.show("File perpanjangan dengan nama "+getStrFileNamePerpanjangan().trim()+" belum terinput extension numbernya");
                if(compdata.getSettlement_filepath().isEmpty()&&!getStrFileNamePenyelesaian().isEmpty())
                {
                    delFile("Penyelesaian",strFilePath+"Temp/");
                    setStrFileNamePenyelesaian("");
                }
                if(compdata.getExtension_filepath().isEmpty()&&!getStrFileNamePerpanjangan().isEmpty())
                {
                    delFile("Perpanjangan",strFilePath+"Temp/");
                    setStrFileNamePerpanjangan("");
                }
                Messagebox.show("File perpanjangan dengan nama "+getStrFileNamePerpanjangan().trim()+" belum terinput extension numbernya");
            }
            else
            {
                compotor.setAging(compdata.getAging());
                if(compdata.getCompletion_date()!=null)
                {
                    if(validateDateInput(compdata.getReceived_date(), compdata.getCompletion_date()))
                    {
                        compotor.setAging((int)(compdata.getCompletion_date().getTime()-compdata.getReceived_date().getTime())/(1000 * 60 * 60 * 24));
                    }
                }
                File dirPengaduan=new File(strFilePath+"PengaduanNotOtor");
                File dirPenyelesaian=new File(strFilePath+"PenyelesaianNotOtor");
                File dirPerpanjangan=new File(strFilePath+"PerpanjanganNotOtor");
                if(!dirPengaduan.exists()) dirPengaduan.mkdirs();
                if(!dirPenyelesaian.exists()) dirPenyelesaian.mkdirs();
                if(!dirPerpanjangan.exists()) dirPerpanjangan.mkdirs();
                if(!getStrFileNamePengaduan().trim().isEmpty())
                {
                    compotor.setDispute_filepath(strFilePath+"Pengaduan/"+getStrFileNamePengaduan());
                }
                if(!getStrFileNamePenyelesaian().trim().isEmpty())
                {
                    compotor.setSettlement_filepath(strFilePath+"Penyelesaian/"+getStrFileNamePenyelesaian());
                }
                if(!getStrFileNamePerpanjangan().trim().isEmpty())
                {
                    compotor.setExtension_filepath(strFilePath+"Perpanjangan/"+getStrFileNamePerpanjangan());
                }
//                outMsg=comphandBo.updtCompData(compdata);
                compotor=new CompOtor(compdata.getRequest_id(), compdata.getSettlement_number(), compdata.getExtension_number(), compdata.getCustomer_name(), compdata.getLocation(), compdata.getChannel_code(), 
                        compdata.getCity_code(), compdata.getBranch_code(), compdata.getAmount(), compdata.getAtm_card_number(), compdata.getDescription(), compdata.getReceived_date(), compdata.getIncident_date(), 
                        compdata.getCompletion_date(), compdata.getCode_601(), compdata.getCode_601_problem(), compdata.getCode_602(), compdata.getCode_602_problem(), compdata.getCode_603(), 
                        compdata.getCode_603_problem(), compdata.getCode_604(), compdata.getCode_604_problem(), compdata.getCode_605(), compdata.getCode_605_problem(), compotor.getDispute_filepath(), 
                        compotor.getSettlement_filepath(), compotor.getExtension_filepath(), compotor.getUser_input(), "-", compotor.getAging(), compdata.getIs_deleted(), "Update");
                String logsCompareEdit=compareDataEdit(compdataBe4, compotor);
                outMsg=comphandBo.reqUpdtCompData(compotor,logsCompareEdit);
                if(outMsg.contains("berhasil"))
                {
                    if(!getStrFileNamePengaduan().isEmpty())
                    {
                        if(new File(strFilePath+"Temp/", getStrFileNamePengaduan()).exists())
                        moveFile(strFilePath+"Temp/"+getStrFileNamePengaduan(), strFilePath+"PengaduanNotOtor/"+getStrFileNamePengaduan());
                    }
                    if(!getStrFileNamePenyelesaian().isEmpty())
                    {
                        if(new File(strFilePath+"Temp/", getStrFileNamePenyelesaian()).exists())
                        moveFile(strFilePath+"Temp/"+getStrFileNamePenyelesaian(), strFilePath+"PenyelesaianNotOtor/"+getStrFileNamePenyelesaian());
                    }
                    if(!getStrFileNamePerpanjangan().isEmpty())
                    {
                        if(new File(strFilePath+"Temp/", getStrFileNamePerpanjangan()).exists())
                        moveFile(strFilePath+"Temp/"+getStrFileNamePerpanjangan(), strFilePath+"PerpanjanganNotOtor/"+getStrFileNamePerpanjangan());
                    }
                    compotor=new CompOtor();
                    setStrFileNamePengaduan("");
                    setStrFileNamePenyelesaian("");
                    setStrFileNamePerpanjangan("");

                }
                else if(!outMsg.contains("berhasil"))
                {
                    if(!getStrFileNamePengaduan().isEmpty())
                    {
                        delFile("Pengaduan",strFilePath+"Temp/");
                        setStrFileNamePengaduan("");
                    }
                    if(!getStrFileNamePenyelesaian().isEmpty())
                    {
                        delFile("Penyelesaian",strFilePath+"Temp/");
                        setStrFileNamePenyelesaian("");
                    }
                    if(!getStrFileNamePerpanjangan().isEmpty())
                    {
                        delFile("Perpanjangan",strFilePath+"Temp/");
                        setStrFileNamePerpanjangan("");
                    }
                }

//                Messagebox.show(outMsg);
                setShowDetComp(false);
                complaints=comphandBo.lstCompData(false);
                Messagebox.show(outMsg);
            }
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    @NotifyChange({"strFileNamePengaduan","strFileNamePenyelesaian","strFileNamePerpanjangan"})
    public void reqIDchange(){
        if(getStrFileNamePengaduan()!=null&&!getStrFileNamePengaduan().isEmpty())
        {
            delFile("Pengaduan",strFilePath+"Temp/");
        }
        if(getStrFileNamePenyelesaian()!=null&&!getStrFileNamePenyelesaian().isEmpty())
        {
            delFile("Penyelesaian",strFilePath+"Temp/");
        }
        if(getStrFileNamePerpanjangan()!=null&&!getStrFileNamePerpanjangan().isEmpty())
        {
            delFile("Perpanjangan",strFilePath+"Temp/");
        }
        setStrFileNamePengaduan("");
        setStrFileNamePenyelesaian("");
        setStrFileNamePerpanjangan("");
        
    }
    
    @Command @NotifyChange({"strFileNamePengaduan","strFileNamePenyelesaian","strFileNamePerpanjangan"})
    public void browseFile(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
            @BindingParam("section") String section) throws Exception {
        UploadEvent event = (UploadEvent)ctx.getTriggerEvent();
        
        if (event.getMedia().getContentType().equals("application/pdf")) {
            System.out.println("FILEPATH "+strFilePath);
            File dir=new File(strFilePath+"Temp/");
            if(section.equals("pengaduan"))
            {
//                dir = new File(strFilePath+"/Pengaduan");
                strFileNamePengaduan=compotor.getRequest_id()+"_Dispute.pdf";
                strFileName=strFileNamePengaduan;
                setIsEditDisputeFile(true);
//                compdata.setPENGADUAN_FILE_PATH(dir.getAbsolutePath()+strFileNamePengaduan);
            }
            else if(section.equals("penyelesaian"))
            {
//                dir = new File(strFilePath+"/Penyelesaian");
                strFileNamePenyelesaian=compotor.getRequest_id()+"_Settlement.pdf";
                strFileName=strFileNamePenyelesaian;
                setIsEditSettleFile(true);
//                compdata.setPENYELESAIAN_FILE_PATH(dir.getAbsolutePath()+strFileNamePenyelesaian);
                
            }
            else if(section.equals("perpanjangan"))
            {
//                dir = new File(strFilePath+"/Perpanjangan");
                strFileNamePerpanjangan=compotor.getRequest_id()+"_Extension.pdf";
                strFileName=strFileNamePerpanjangan;
                setIsEditExtFile(true);
//                compdata.setPERPANJANGAN_FILE_PATH(dir.getAbsolutePath()+strFileNamePerpanjangan);
                
            }
            
            if(!dir.exists()) dir.mkdirs();
            File file = new File(dir.getAbsolutePath(), strFileName);
//            File file = new File(dir.getAbsolutePath(), "/"+strFileName);
            OutputStream os = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            InputStream is = event.getMedia().getStreamData();
            BufferedInputStream bis = new BufferedInputStream(is);
            byte buffer[] = new byte[1024];
            int ch = bis.read(buffer);
            while (ch!=-1) {
                bos.write(buffer, 0, ch);
                ch = bis.read(buffer);
            }

            bos.close();
            bis.close();
            os.close();
            is.close();
            
        } else
            Messagebox.show("File Yang Diupload Bukan Format PDF.");
    }
    
    @Command
    public void searchComplaints(@BindingParam("condition") String condition){
//        compdata.setREQUEST_ID("");
        setShowDetComp(false);
        if((getStartDate()==null&&getEndDate()==null)||(getStartDate()!=null&&getEndDate()!=null))
        {
            if((getStartDate()!=null&&getEndDate()!=null)&&!validateDateInput(startDate, endDate))
            {
                Messagebox.show("End Date lebih awal dari Start Date");
            }
            else
            {
                if(condition.equals("Complaint Handling Report"))
//                if(condition.equals("true"))
                {
                    reports=comphandBo.lstCompRptByFilter(getStartDate(), getEndDate(), getStrSearch().trim());
                }
                else if(condition.equals("Complaint Resolution Report"))
                {
                    reports=comphandBo.lstCompResRptByFilter(getStartDate(), getEndDate(), getStrSearch().trim());
                }
                else if(condition.equals("Outstanding Complaints")||condition.equals("Edit Delete Complaints")||condition.equals("View Complaints"))
//                else if(condition.equals("false"))
                {
                    if(!getStrSearch().isEmpty())
                    complaints=comphandBo.lstCompDataByFilter(getStartDate(), getEndDate(), "%"+getStrSearch().trim()+"%");
                    else if(getStrSearch().isEmpty()&&getStartDate()!=null&&getEndDate()!=null)
                    complaints=comphandBo.lstCompDataByFilter(getStartDate(), getEndDate(), getStrSearch().trim());
                }
                else if(condition.equals("Authorize Complaints"))
                {
                    if(!getStrSearch().isEmpty())
                    lstOtor=comphandBo.lstCompOtorByFilter(getStartDate(), getEndDate(), "%"+getStrSearch().trim()+"%",strUserName);
                    else if(getStrSearch().isEmpty()&&getStartDate()!=null&&getEndDate()!=null)
                    lstOtor=comphandBo.lstCompOtorByFilter(getStartDate(), getEndDate(), getStrSearch().trim(),strUserName);
                }
                else if(condition.equals("Complain Handling Logs"))
                {
                    if(!getStrSearch().isEmpty())
                    lstLogs=comphandBo.lstCompLogsByFilter(getStartDate(), getEndDate(), "%"+getStrSearch().trim()+"%");
                    else if(getStrSearch().isEmpty()&&getStartDate()!=null&&getEndDate()!=null)
                    lstLogs=comphandBo.lstCompLogsByFilter(getStartDate(), getEndDate(), getStrSearch().trim());
                }
            }
        }
        else
        {
            Messagebox.show("tanggal blm lengkap");
        }
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void resetComplaints(@BindingParam("condition") String condition){
//        compdata.setREQUEST_ID("");
        setShowDetComp(false);
        if(condition.equals("Complaint Handling Report"))
//        if(condition.equals("true"))
        {
            reports=comphandBo.lstCompRpt();
        }
        else if(condition.equals("Complaint Resolution Report"))
        {
            reports=comphandBo.lstCompResRpt();
        }
        
        if(condition.equals("Outstanding Complaints"))
        {
            complaints=comphandBo.lstCompData(true);
        }
        else if(!condition.equals("Outstanding Complaints"))
        {
            complaints=comphandBo.lstCompData(false);
        }
        
        if(condition.equals("Authorize Complaints"))
        {
            lstOtor=comphandBo.lstCompOtor(strUserName);
        }
        if(condition.equals("Authorize Complaints"))
        {
            lstOtor=comphandBo.lstCompOtor(strUserName);
        }
        if(condition.equals("Complain Handling Logs"))
        {
            lstLogs=comphandBo.lstCompLogs();
        }
        setStartDate(null);
        setEndDate(null);
        setStrSearch("");
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void openLink(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
            @BindingParam("type") String type){
        if(isIsFileClickable())
        {            
            try 
            {
                File file=null;
                String filename="";
                if(type.equals("Pengaduan"))
                {
                    filename=compdata.getRequest_id()+"_Dispute.pdf";
//                    file = new File(strFilePath+"Temp/", compdata.getRequest_id()+"_Dispute.pdf");
//                    if(!file.exists())
//                    file = new File(strFilePath+"Pengaduan/", compdata.getRequest_id()+"_Dispute.pdf");
                    file = new File(strFilePath+"Temp/", filename);
                    if(!file.exists())
                    file = new File(strFilePath+"Pengaduan/", filename);
                    
                }
                else if(type.equals("Penyelesaian"))
                {
                    filename=compdata.getRequest_id()+"_Settlement.pdf";
//                    file = new File(strFilePath+"Temp/", compdata.getRequest_id()+"_Settlement.pdf");
//                    if(!file.exists())
//                    file = new File(strFilePath+"Penyelesaian/", compdata.getRequest_id()+"_Settlement.pdf");
                    file = new File(strFilePath+"Temp/", filename);
                    if(!file.exists())
                    file = new File(strFilePath+"Penyelesaian/", filename);
                }
                else if(type.equals("Perpanjangan"))
                {
                    filename=compdata.getRequest_id()+"_Extension.pdf";
//                    file = new File(strFilePath+"Temp/", compdata.getRequest_id()+"_Extension.pdf");
//                    if(!file.exists())
//                    file = new File(strFilePath+"Perpanjangan/", compdata.getRequest_id()+"_Extension.pdf");
                    file = new File(strFilePath+"Temp/", filename);
                    if(!file.exists())
                    file = new File(strFilePath+"Perpanjangan/", filename);
                }
                
                if (file.exists() && file.isFile()) {
                    sysLogs=new System_Logs();
                    sysLogs.setUser_input(strUserName);
                    sysLogs.setActivity_type("DOWNLOAD_APPENDIX");
                    sysLogs.setRequest_id(compdata.getRequest_id());
                    sysLogs.setDescription("User "+strUserName+" downloaded document '"+filename+"' for Request ID "+compdata.getRequest_id()+".");
                    sysLogs.setDesc_file(filename);
                    sysLogs.setUser_otor(compdata.getUser_otor());
                    comphandBo.insProctoLogs(sysLogs);
                    // Trigger file download popup, automatically detects mime type
                    Filedownload.save(file, null);
                } else {
                    Messagebox.show("File tidak ditemukan.");
                }
            } 
            catch (Exception e) 
            {
                Messagebox.show("Error download file.");
                e.printStackTrace();
            }
        }
    }
    
    @Command
    public void viewComplaint(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
            @BindingParam("id") String idSelected){
//        davidch 31-12-25 refresh option 60x saat menu view dari data yg ada isinya ke data yg memang blank
        compdata=new Compdata();
        for(Compdata cd:complaints)
        {
            if(cd.getRequest_id().equals(idSelected))
            {
                compdata=cd;
                break;
            }
        }
        viewForm();
        sysLogs=new System_Logs();
        sysLogs.setUser_input(strUserName);
        sysLogs.setActivity_type("INQ_COMPLAINT");
        sysLogs.setRequest_id(compdata.getRequest_id());
        sysLogs.setDescription("User "+strUserName+" view detail data complain of request id "+compdata.getRequest_id()+".");
        sysLogs.setDesc_file("-");
        sysLogs.setUser_otor("-");
        comphandBo.insProctoLogs(sysLogs);
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void editComplaint(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
            @BindingParam("id") String idSelected){
        compdata=new Compdata();
        compdataBe4=new Compdata();
        for(Compdata cd:complaints)
        {
            if(cd.getRequest_id().equals(idSelected))
            {
                compdata=cd.cloneData();
                compdataBe4=cd.cloneData();
//                compdata=cd;
                break;
            }
        }
        editForm();
        compotor.setRequest_id(compdata.getRequest_id());
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void deleteComplaint(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
            @BindingParam("id") String idSelected){
        compdata=new Compdata();
        for(Compdata cd:complaints)
        {
            if(cd.getRequest_id().equals(idSelected))
            {
                compdata=cd;
                break;
            }
        }
        
        setStrFileNamePengaduan(compdata.getDispute_filepath().substring(compdata.getDispute_filepath().lastIndexOf("/")+1));
        setStrFileNamePenyelesaian(compdata.getSettlement_filepath().substring(compdata.getSettlement_filepath().lastIndexOf("/")+1));
        setStrFileNamePerpanjangan(compdata.getExtension_filepath().substring(compdata.getExtension_filepath().lastIndexOf("/")+1));
        compotor=new CompOtor(compdata.getRequest_id(), compdata.getSettlement_number(), compdata.getExtension_number(), compdata.getCustomer_name(), compdata.getLocation(), compdata.getChannel_code(), 
                        compdata.getCity_code(), compdata.getBranch_code(), compdata.getAmount(), compdata.getAtm_card_number(), compdata.getDescription(), compdata.getReceived_date(), compdata.getIncident_date(), 
                        compdata.getCompletion_date(), compdata.getCode_601(), compdata.getCode_601_problem(), compdata.getCode_602(), compdata.getCode_602_problem(), compdata.getCode_603(), 
                        compdata.getCode_603_problem(), compdata.getCode_604(), compdata.getCode_604_problem(), compdata.getCode_605(), compdata.getCode_605_problem(), compdata.getDispute_filepath(), 
                        compdata.getSettlement_filepath(), compdata.getExtension_filepath(), strUserName, "-", compdata.getAging(), compdata.getIs_deleted(), "Delete");
        
        Messagebox.show("Apakah yakin request hapus data complain dengan request id "+compdata.getRequest_id()+" ?",
            "Confirm Delete",
            Messagebox.YES | Messagebox.NO,
            Messagebox.EXCLAMATION,new EventListener<Event>() {
                public void onEvent(Event event) throws Exception {
                    if (Messagebox.ON_YES.equals(event.getName())) {

                        msg=comphandBo.reqDelCompData(compotor);
//                        if(msg.contains("berhasil"))
//                        {
//                            if(compdata.getDispute_filepath()!=null&&compdata.getDispute_filepath().contains("Dispute.pdf"))
//                                delFile("Pengaduan", strFilePath+"Pengaduan/");
//                            if(compdata.getSettlement_filepath()!=null&&compdata.getSettlement_filepath().contains("Settlement.pdf"))
//                                delFile("Penyelesaian", strFilePath+"Penyelesaian/");
//                            if(compdata.getExtension_filepath()!=null&&compdata.getExtension_filepath().contains("Extension.pdf"))
//                                delFile("Perpanjangan", strFilePath+"Perpanjangan/");
//                        }
//                        complaints.clear();
//                        complaints.addAll(comphandBo.lstCompData(false));
//                        compdata = new Compdata();
//                        setStrFileNamePengaduan("");
//                        setStrFileNamePenyelesaian("");
//                        setStrFileNamePerpanjangan("");
                        Messagebox.show(msg);
                    } else {
                    }
                }
            });
        if(isShowDetComp())
            setShowDetComp(false);
        BindUtils.postNotifyChange(null, null, this, "*");
        
    }
    @Command
    public void sdhSelectCity(){
        setActiveCbBranch(false);
        compotor.setBranch_code(null);
        if(getBranches()!=null)
            getBranches().clear();
        for(Branch b:allBranches)
        {
            if(b.getCity_code().getCity_code().equals(compotor.getCity_code().getCity_code()))
            {
                getBranches().add(b);
            }
        }
//        setBranches(new ListModelList<Branch>(masterBo.lstBranchesByCity(compdata.getCITY_CODE().getCITY_CODE())));
//        BindUtils.postNotifyChange(null, null, this, "*");
        BindUtils.postNotifyChange(null, null, this, "branches");
        BindUtils.postNotifyChange(null, null, this, "branch");
        BindUtils.postNotifyChange(null, null, this, "activeCbBranch");
    }
    
    @Command
    public void expReport(@BindingParam("param") String param) throws FileNotFoundException, IOException{
        String xlsFilePath="";
        if((getStartDate()==null&&getEndDate()==null)||(getStartDate()!=null&&getEndDate()!=null))
        {
            if((getStartDate()!=null&&getEndDate()!=null)&&!validateDateInput(startDate, endDate))
            {
                Messagebox.show("End Date lebih awal dari Start Date");
            }
            else
            {
//                if(comphandBo.lstCompDataByFilter(getStartDate(), getEndDate(), getStrSearch().trim())!=null)
//                {
                    if(!getStrSearch().isEmpty())
                    complaints=comphandBo.lstCompDataByFilter(getStartDate(), getEndDate(), "%"+getStrSearch().trim()+"%");
                    else if(getStrSearch().isEmpty()&&getStartDate()!=null&&getEndDate()!=null)
                    complaints=comphandBo.lstCompDataByFilter(getStartDate(), getEndDate(), getStrSearch().trim());
//                }
            }
        }
        
        if((complaints!=null&&getStrTitleMenu().equals("Complaint Handling Report"))||(reports!=null&&getStrTitleMenu().equals("Complaint Resolution Report"))
                ||(lstLogs!=null&&getStrTitleMenu().equals("Complain Handling Logs")))
        {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet;
            int baris = 0;
            int indexjudul = 0;
            HSSFRow row;
            HSSFCell cell;


            if(param.equals("Complaint Handling Report"))
            {
                xlsFilePath=strFilePath+"ComplainReport"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".xls";
                sheet = workbook.createSheet("Report");
                row = sheet.createRow(baris);
                cell = row.createCell(indexjudul);

                cell.setCellValue("No.");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Request ID");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Tgl. Terima");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Nama Nasabah");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("No. Kartu ATM");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Tgl. Kejadian");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Tempat");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Wilayah");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Keterangan");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Nilai Transaksi");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Tgl. Selesai");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("No. Surat");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("601");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("601-P");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("602");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("602-P");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("603");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("603-P");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("604");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("604-P");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("605");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("605-P");
                indexjudul++;
                baris++;
                for(Compdata item : complaints)
                {
                    row = sheet.createRow(baris);

                    int kolz = 0;
                    cell = row.createCell(kolz);
                    cell.setCellValue(baris) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getRequest_id()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(item.getReceived_date())) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCustomer_name()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getAtm_card_number()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(item.getIncident_date())) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getLocation()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCity_code().getCity_name()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getDescription()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getAmount()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    if(item.getCompletion_date()==null)
                        cell.setCellValue("");
                    else
                        cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(item.getCompletion_date())) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    if(item.getSettlement_number()==null)
                        cell.setCellValue("") ;
                    else
                        cell.setCellValue(item.getSettlement_number()) ;
                    kolz++;

                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_601().getCode_601_name()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_601_problem().getCode_601p_name()) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_602()!=null ? item.getCode_602().getCode_602_name(): null);
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_602_problem()!=null ? item.getCode_602_problem().getCode_602p_name(): null) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_603()!=null ? item.getCode_603().getCode_603_name(): null) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_603_problem()!=null ? item.getCode_603_problem().getCode_603p_name(): null) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_604()!=null ? item.getCode_604().getCode_604_name(): null) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_604_problem()!=null ? item.getCode_604_problem().getCode_604p_name(): null) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_605()!=null ? item.getCode_605().getCode_605_name(): null) ;
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(item.getCode_605_problem()!=null ? item.getCode_605_problem().getCode_605p_name(): null) ;
                    kolz++;

                    baris++;
                }
                sheet.setForceFormulaRecalculation(true);
            }
            else if(param.equals("Complaint Resolution Report"))
            {
                xlsFilePath=strFilePath+"ComplainResolutionReport"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".xls";
                sheet = workbook.createSheet("Resolution Rpt");
                row = sheet.createRow(baris);
                cell = row.createCell(indexjudul);

                cell.setCellValue("Branch");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Resolution Status");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Total Complaints");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Average Resolution Time (days)");
                indexjudul++;
                baris++;
                for(CompReport cr:reports)
                {
                    row = sheet.createRow(baris);

                    int kolz = 0;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getBRANCH_CODE());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getKET());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getFREK());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getRATA());
                    kolz++;
                    baris++;
                }
                sheet.setForceFormulaRecalculation(true);
            }
            else if(param.equals("Complain Handling Logs"))
            {
                xlsFilePath=strFilePath+"ComplainHandlingLogs"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".xls";
                sheet = workbook.createSheet("Complain Handling Logs");
                row = sheet.createRow(baris);
                cell = row.createCell(indexjudul);

                cell.setCellValue("Activity Type");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Request ID");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Create Date");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Description");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Changed Fields");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("Description File");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("User Input");
                indexjudul++;
                cell = row.createCell(indexjudul);
                cell.setCellValue("User Otor");
                indexjudul++;
                baris++;
                for(System_Logs cr:lstLogs)
                {
                    row = sheet.createRow(baris);

                    int kolz = 0;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getActivity_type());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getRequest_id());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(cr.getTgl_input()));
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(formattedDescription(cr.getDescription()));
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getChanged_fields());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getDesc_file());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getUser_input());
                    kolz++;
                    cell = row.createCell(kolz);
                    cell.setCellValue(cr.getUser_otor());
                    kolz++;
                    baris++;
                }
                sheet.setForceFormulaRecalculation(true);
            }

            FileOutputStream fileOut = new FileOutputStream(new File(xlsFilePath));
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            if(!param.equals("Complain Handling Logs"))
            {
                sysLogs=new System_Logs();
                sysLogs.setUser_input(strUserName);
                sysLogs.setActivity_type("DOWNLOAD_REPORT");
                sysLogs.setRequest_id("-");
                sysLogs.setDescription("User "+strUserName+" download "+getStrTitleMenu()+".");
                sysLogs.setDesc_file("-");
                sysLogs.setUser_otor("-");
                comphandBo.insProctoLogs(sysLogs);
            }
            
            Filedownload.save(new File(xlsFilePath), null);
            Messagebox.show("File berhasil diunduh");
        }
        else if(complaints==null)
        {
            Messagebox.show("Data kosong");
        }
        
    }
    
    @Command
    public void clear60(@BindingParam("option") String option){
        if(isBtnEditMode)
        {
            switch (option) {
                case "601":
                    compdata.setCode_601(null);
                    compdata.setCode_601_problem(null);
                    break;
                case "602":
                    compdata.setCode_602(null);
                    compdata.setCode_602_problem(null);
                    break;
                case "603":
                    compdata.setCode_603(null);
                    compdata.setCode_603_problem(null);
                    break;
                case "604":
                    compdata.setCode_604(null);
                    compdata.setCode_604_problem(null);
                    break;
                case "605":
                    compdata.setCode_605(null);
                    compdata.setCode_605_problem(null);
                    break;
                default:
                    break;
            }
        }

        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    @Command
    public void otorApproved(@BindingParam("id") String idSelected){
        compotor=new CompOtor();
        for(CompOtor cd:lstOtor)
        {
            if(cd.getRequest_id().equals(idSelected))
            {
                compotor=cd;
                break;
            }
        }
        
        Messagebox.show("Apakah yakin otor data complain dengan request id "+idSelected+" ?",
        "Confirm Otor",
        Messagebox.YES | Messagebox.NO,
        Messagebox.INFORMATION,new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    compotor.setUser_otor(strUserName);
                    msg=comphandBo.approvedCompData(compotor,compotor.getAction());
//                    Messagebox.show(msg);
                    lstOtor.clear();
                    if(comphandBo.lstCompOtor(strUserName)!=null)
                    {
                        lstOtor.addAll(comphandBo.lstCompOtor(strUserName));
                    }
                    setStrFileNamePengaduan(compotor.getDispute_filepath().substring(compotor.getDispute_filepath().lastIndexOf("/")+1));
                    setStrFileNamePenyelesaian(compotor.getSettlement_filepath().substring(compotor.getSettlement_filepath().lastIndexOf("/")+1));
                    setStrFileNamePerpanjangan(compotor.getExtension_filepath().substring(compotor.getExtension_filepath().lastIndexOf("/")+1));
                    if(!getStrFileNamePengaduan().isEmpty())
                    {
                        if(compotor.getAction().equals("Add")||(compotor.getAction().equals("Update")&&new File(strFilePath+"PengaduanNotOtor/",getStrFileNamePengaduan()).exists()))
                        {
                            moveFile(strFilePath+"PengaduanNotOtor/"+getStrFileNamePengaduan(), strFilePath+"Pengaduan/"+getStrFileNamePengaduan());
                        }
                        else if(compotor.getAction().equals("Delete"))
                        {
                            delFile("Pengaduan", strFilePath+"Pengaduan/");
                        }
                    }
                    if(!getStrFileNamePenyelesaian().isEmpty())
                    {
                        if(compotor.getAction().equals("Add")||(compotor.getAction().equals("Update")&&new File(strFilePath+"PenyelesaianNotOtor/",getStrFileNamePenyelesaian()).exists()))
                        {
                            moveFile(strFilePath+"PenyelesaianNotOtor/"+getStrFileNamePenyelesaian(), strFilePath+"Penyelesaian/"+getStrFileNamePenyelesaian());
                        }
                        else if(compotor.getAction().equals("Delete"))
                        {
                            delFile("Penyelesaian", strFilePath+"Penyelesaian/");
                        }
                    }
                    if(!getStrFileNamePerpanjangan().isEmpty())
                    {
                        if(compotor.getAction().equals("Add")||(compotor.getAction().equals("Update")&&new File(strFilePath+"PerpanjanganNotOtor/",getStrFileNamePerpanjangan()).exists()))
                        {
                            moveFile(strFilePath+"PerpanjanganNotOtor/"+getStrFileNamePerpanjangan(), strFilePath+"Perpanjangan/"+getStrFileNamePerpanjangan());
                        }
                        else if(compotor.getAction().equals("Delete"))
                        {
                            delFile("Perpanjangan", strFilePath+"Perpanjangan/");
                        }
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
        compotor=new CompOtor();
        for(CompOtor cd:lstOtor)
        {
            if(cd.getRequest_id().equals(idSelected))
            {
                compotor=cd;
                break;
            }
        }
        
        Messagebox.show("Apakah yakin reject data complain dengan request id "+idSelected+" ?",
        "Confirm Reject",
        Messagebox.YES | Messagebox.NO,
        Messagebox.EXCLAMATION,new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    msg=comphandBo.rejectedCompData(compotor.getRequest_id(),compotor.getAction(),strUserName);
//                    Messagebox.show(msg);
                    lstOtor.clear();
                    if(comphandBo.lstCompOtor(strUserName)!=null)
                    {
                        lstOtor.addAll(comphandBo.lstCompOtor(strUserName));
                    }
                    
                    setStrFileNamePengaduan(compotor.getDispute_filepath().substring(compotor.getDispute_filepath().lastIndexOf("/")+1));
                    setStrFileNamePenyelesaian(compotor.getSettlement_filepath().substring(compotor.getSettlement_filepath().lastIndexOf("/")+1));
                    setStrFileNamePerpanjangan(compotor.getExtension_filepath().substring(compotor.getExtension_filepath().lastIndexOf("/")+1));
                    if(!getStrFileNamePengaduan().isEmpty()&&new File(strFilePath+"PengaduanNotOtor/",getStrFileNamePengaduan()).exists())
                    {
                        delFile("Pengaduan", strFilePath+"PengaduanNotOtor/");
                    }
                    if(!getStrFileNamePenyelesaian().isEmpty()&&new File(strFilePath+"PenyelesaianNotOtor/",getStrFileNamePenyelesaian()).exists())
                    {
                        delFile("Penyelesaian", strFilePath+"PenyelesaianNotOtor/");
                    }
                    if(!getStrFileNamePerpanjangan().isEmpty()&&new File(strFilePath+"PerpanjanganNotOtor/",getStrFileNamePerpanjangan()).exists())
                    {
                        delFile("Perpanjangan", strFilePath+"PerpanjanganNotOtor/");
                    }
                    Messagebox.show(msg);
                } else {
                }
            }
        });
        
        BindUtils.postNotifyChange(null, null, this, "*");
    }
    
    private void moveFile(String paramPathFr,String paramPathTo) throws IOException{
        Path pathTo,pathFr;
        pathTo = Paths.get(paramPathTo);
        pathFr = Paths.get(paramPathFr);
        Files.move(pathFr, pathTo, StandardCopyOption.REPLACE_EXISTING);
    }
    
    private void delFile(String jenis, String tempFilePath){
        File tempFile;
        if(jenis.equals("Pengaduan"))
        {
            tempFile=new File(tempFilePath, getStrFileNamePengaduan());
            if (tempFile.exists()) {
                // Attempt to delete the file
                if (tempFile.delete()) {
                    compotor.setDispute_filepath("");
                    System.out.println("File deleted successfully: " + tempFile.getAbsolutePath());
                } else {
                    System.out.println("Failed to delete the file: " + tempFile.getAbsolutePath());
                }
            } 
            else {
                System.out.println("File does not exist: " + tempFile.getAbsolutePath());
            }
        }
        else if(jenis.equals("Penyelesaian"))
        {
            tempFile=new File(tempFilePath, getStrFileNamePenyelesaian());
            if (tempFile.exists()) {
                // Attempt to delete the file
                if (tempFile.delete()) {
                    compotor.setSettlement_filepath("");
                    System.out.println("File deleted successfully: " + tempFile.getAbsolutePath());
                } else {
                    System.out.println("Failed to delete the file: " + tempFile.getAbsolutePath());
                }
            } 
            else {
                System.out.println("File does not exist: " + tempFile.getAbsolutePath());
            }
        }
        else if(jenis.equals("Perpanjangan"))
        {
            tempFile=new File(tempFilePath, getStrFileNamePerpanjangan());
            if (tempFile.exists()) {
                // Attempt to delete the file
                if (tempFile.delete()) {
                    compotor.setExtension_filepath("");
                    System.out.println("File deleted successfully: " + tempFile.getAbsolutePath());
                } else {
                    System.out.println("Failed to delete the file: " + tempFile.getAbsolutePath());
                }
            } 
            else {
                System.out.println("File does not exist: " + tempFile.getAbsolutePath());
            }
        }
    }
    
    private void newInput(){
        activeReqID = false;
        activeSettNumber = false;
        activeextNumber = false;
        activeCustName = false;
        activeLoc = false;
        activeCbChannel = false;
        activeCbCity = false;
        activeCbBranch = true;
//        activeCbBranch = false;
        activeAmount = false;
        activeATM = false;
        activeDesc = false;
        activeRecDate = false;
        activeIncDate = false;
        activeCompDate = false;
        activeCb601 = false;
        activeCb601A = false;
        activeCb602 = false;
        activeCb603 = false;
        activeCb604 = false;
        activeCb605 = false;
        activeCb602A = false;
        activeCb603A = false;
        activeCb604A = false;
        activeCb605A = false;
        setIsFileClickable(false);
        setBannedAccFile(false);
        setShowBtnSubmit(true);
        setShowDetComp(true);
        setIsBtnEditMode(false);
    }
    
    private void editForm(){
        activeReqID = true;
        activeSettNumber = false;
        activeextNumber = false;
        activeCustName = true;
        activeLoc = false;
        activeCbChannel = true;
        activeCbCity = true;
        activeCbBranch = true;
        activeAmount = false;
        activeATM = false;
        activeDesc = false;
        activeRecDate = true;
        activeIncDate = true;
        activeCompDate = false;
        activeCb601 = true;
        activeCb601A = true;
        activeCb602 = false;
        activeCb603 = true;
        activeCb604 = false;
        activeCb605 = false;
        activeCb602A = false;
        activeCb603A = true;
        activeCb604A = false;
        activeCb605A = false;
        setIsFileClickable(true);
        setBannedAccFile(false);
        setShowBtnSubmit(true);
        setShowDetComp(true);
        setIsBtnEditMode(true);
        setIsEditDisputeFile(false);
        setIsEditSettleFile(false);
        setIsEditExtFile(false);
        branches.clear();
        for(Branch b1:allBranches)
        {
            if(b1.getCity_code().getCity_code().equals(compdata.getCity_code().getCity_code()))
                branches.add(b1);
        }
        setStrFileNamePengaduan(compdata.getDispute_filepath().substring(compdata.getDispute_filepath().lastIndexOf("/")+1));
        setStrFileNamePenyelesaian(compdata.getSettlement_filepath().substring(compdata.getSettlement_filepath().lastIndexOf("/")+1));
        setStrFileNamePerpanjangan(compdata.getExtension_filepath().substring(compdata.getExtension_filepath().lastIndexOf("/")+1));
//        davidch 31-12-25 refresh option 60x saat menu view dari data yg ada isinya ke data yg memang blank
        refreshoption60();
    }
    
    private void viewForm() {
        activeReqID = true;
        activeSettNumber = true;
        activeextNumber = true;
        activeCustName = true;
        activeLoc = true;
        activeCbChannel = true;
        activeCbCity = true;
        activeCbBranch = true;
        activeAmount = true;
        activeATM = true;
        activeDesc = true;
        activeRecDate = true;
        activeIncDate = true;
        activeCompDate = true;
        activeCb601 = true;
        activeCb601A = true;
        activeCb602 = true;
        activeCb603 = true;
        activeCb604 = true;
        activeCb605 = true;
        activeCb602A = true;
        activeCb603A = true;
        activeCb604A = true;
        activeCb605A = true;
        setIsFileClickable(true);
        setBannedAccFile(true);
        setShowBtnSubmit(false);
        setShowDetComp(true);
        setIsBtnEditMode(true);
        branches.clear();
        for(Branch b1:allBranches)
        {
            if(b1.getCity_code().getCity_code().equals(compdata.getCity_code().getCity_code()))
                branches.add(b1);
        }
        setStrFileNamePengaduan(compdata.getDispute_filepath().substring(compdata.getDispute_filepath().lastIndexOf("/")+1));
        setStrFileNamePenyelesaian(compdata.getSettlement_filepath().substring(compdata.getSettlement_filepath().lastIndexOf("/")+1));
        setStrFileNamePerpanjangan(compdata.getExtension_filepath().substring(compdata.getExtension_filepath().lastIndexOf("/")+1));
//        davidch 31-12-25 refresh option 60x saat menu view dari data yg ada isinya ke data yg memang blank
        refreshoption60();
    }
    
    private boolean validateDateInput(Date dStart, Date dEnd)
    {
        boolean result=false;
        if(dEnd.before(dStart))
            result=false;
        else if(dEnd.after(dStart)||dEnd.equals(dStart))
            result=true;
        
        return result;
    }
    
    @Command
    @NotifyChange("compotor")
    public void trimLeadingWhitespace(
            @BindingParam("field") String field,
            @BindingParam("text") String text) {

        if (text == null) text = "";
        String trimmed = text.replaceAll("^\\s+", "").replaceAll("[^a-zA-Z0-9\\s\\-()]", "");
//        String trimmed = text.replaceAll("^\\s+", "").replaceAll("[^a-zA-Z0-9]", "");

        if(field.equals("DESCRIPTION"))
        compotor.setDescription(trimmed);
        else if(field.equals("ATM"))
        compotor.setAtm_card_number(trimmed);
        else if(field.equals("REQ"))
        compotor.setRequest_id(trimmed);
        else if(field.equals("SETT"))
        compotor.setSettlement_number(trimmed);
        else if(field.equals("EXT"))
        compotor.setExtension_number(trimmed);
        else if(field.equals("CUST"))
        compotor.setCustomer_name(trimmed);
        else if(field.equals("LOC"))
        compotor.setLocation(trimmed);
    }
    
//    davidch 31-12-25 refresh option 60x saat menu view dari data yg ada isinya ke data yg memang blank
    public void refreshoption60(){
        
        if(compdata.getCode_601()==null||compdata.getCode_601().getCode_601_code()==null||
                (compdata.getCode_601().getCode_601_code()!=null&&compdata.getCode_601().getCode_601_code().trim().isEmpty()))
        {
            clear60("601");
        }
        if(compdata.getCode_602()==null||compdata.getCode_602().getCode_602_code()==null||
                (compdata.getCode_602().getCode_602_code()!=null&&compdata.getCode_602().getCode_602_code().trim().isEmpty()))
        {
            clear60("602");
        }
        if(compdata.getCode_603()==null||compdata.getCode_603().getCode_603_code()==null||
                (compdata.getCode_603().getCode_603_code()!=null&&compdata.getCode_603().getCode_603_code().trim().isEmpty()))
        {
            clear60("603");
        }
        if(compdata.getCode_604()==null||compdata.getCode_604().getCode_604_code()==null||
                (compdata.getCode_604().getCode_604_code()!=null&&compdata.getCode_604().getCode_604_code().trim().isEmpty()))
        {
            clear60("604");
        }
        if(compdata.getCode_605()==null||compdata.getCode_605().getCode_605_code()==null||
                (compdata.getCode_605().getCode_605_code()!=null&&compdata.getCode_605().getCode_605_code().trim().isEmpty()))
        {
            clear60("605");
        }
    }
    
    public String compareDataEdit(Compdata databe4,CompOtor dataafter)
    {
        String hasil="";
        
        if(databe4.getSettlement_number().trim().isEmpty()&&!dataafter.getSettlement_number().trim().isEmpty())
        {
            hasil+=" Settlement number from blank to "+dataafter.getSettlement_number().trim()+".";
        }
        else if(!databe4.getSettlement_number().trim().equals(dataafter.getSettlement_number().trim()))
        {
            hasil+=" Settlement number from "+databe4.getSettlement_number().trim()+" to "+dataafter.getSettlement_number().trim()+".";
        }
        if(databe4.getExtension_number().trim().isEmpty()&&!dataafter.getExtension_number().trim().isEmpty())
        {
            hasil+=" Extension number from blank to "+dataafter.getExtension_number().trim()+".";
        }
        else if(!databe4.getExtension_number().trim().equals(dataafter.getExtension_number().trim()))
        {
            hasil+=" Extension number from "+databe4.getExtension_number().trim()+" to "+dataafter.getExtension_number().trim()+".";
        }
        if(!databe4.getLocation().trim().equals(dataafter.getLocation().trim()))
        {
            hasil+=" Location from "+databe4.getLocation().trim()+" to "+dataafter.getLocation().trim()+".";
        }
        if(databe4.getAmount()!=dataafter.getAmount())
        {
            hasil+=" Amount from "+databe4.getAmount()+" to "+dataafter.getAmount()+".";
        }
        if(!databe4.getAtm_card_number().trim().equals(dataafter.getAtm_card_number().trim()))
        {
            hasil+=" ATM Card Number from "+databe4.getAtm_card_number().trim()+" to "+dataafter.getAtm_card_number().trim()+".";
        }
        if(!databe4.getDescription().trim().equals(dataafter.getDescription().trim()))
        {
            hasil+=" Description from "+databe4.getDescription().trim()+" to "+dataafter.getDescription().trim()+".";
        }
        if(databe4.getCompletion_date()==null&&dataafter.getCompletion_date()!=null)
        {
            hasil+=" Completion date from blank to "+new SimpleDateFormat("dd-MM-yyyy").format(dataafter.getCompletion_date())+".";
        }
        else if(databe4.getCompletion_date()!=null&&!databe4.getCompletion_date().equals(dataafter.getCompletion_date()))
        {
            hasil+=" Completion date from "+new SimpleDateFormat("dd-MM-yyyy").format(databe4.getCompletion_date())+" to "+new SimpleDateFormat("dd-MM-yyyy").format(dataafter.getCompletion_date())+".";
        }
        if(databe4.getCode_601().getCode_601_name()==null&&dataafter.getCode_601()!=null)
        {
            hasil+=" 601 Product from blank to "+dataafter.getCode_601().getCode_601_name()+".";
        }
        else if((databe4.getCode_601().getCode_601_name()!=null&&!databe4.getCode_601().getCode_601_name().isEmpty()&&databe4.getCode_601()!=null&&dataafter.getCode_601()!=null&&dataafter.getCode_601().getCode_601_name()!=null)&&!databe4.getCode_601().equals(dataafter.getCode_601()))
        {
            hasil+=" 601 Product from "+databe4.getCode_601().getCode_601_name()+" to "+dataafter.getCode_601().getCode_601_name()+".";
        }
        if(databe4.getCode_602()==null&&dataafter.getCode_602()!=null)
        {
            hasil+=" 602 Product from blank to "+dataafter.getCode_602().getCode_602_name()+".";
        }
        else if((databe4.getCode_602()!=null&&!databe4.getCode_602().getCode_602_name().isEmpty()&&databe4.getCode_602()!=null&&dataafter.getCode_602()!=null&&dataafter.getCode_602().getCode_602_name()!=null)&&!databe4.getCode_602().equals(dataafter.getCode_602()))
        {
            hasil+=" 602 Product from "+databe4.getCode_602().getCode_602_name()+" to "+dataafter.getCode_602().getCode_602_name()+".";
        }
        if(databe4.getCode_604()==null&&dataafter.getCode_604()!=null)
        {
            hasil+=" 604 Product from blank to "+dataafter.getCode_604().getCode_604_name()+".";
        }
        else if((databe4.getCode_604()!=null&&!databe4.getCode_604().getCode_604_name().isEmpty()&&databe4.getCode_604()!=null&&dataafter.getCode_604()!=null&&dataafter.getCode_604().getCode_604_name()!=null)&&!databe4.getCode_604().equals(dataafter.getCode_604()))
        {
            hasil+=" 604 Product from "+databe4.getCode_604().getCode_604_name()+" to "+dataafter.getCode_604().getCode_604_name()+".";
        }
        if(databe4.getCode_605()==null&&dataafter.getCode_605()!=null)
        {
            hasil+=" 605 Product from blank to "+dataafter.getCode_605().getCode_605_name()+".";
        }
        else if((databe4.getCode_605()!=null&&!databe4.getCode_605().getCode_605_name().isEmpty()&&databe4.getCode_605()!=null&&dataafter.getCode_605()!=null&&dataafter.getCode_605().getCode_605_name()!=null)&&!databe4.getCode_605().equals(dataafter.getCode_605()))
        {
            hasil+=" 605 Product from "+databe4.getCode_605().getCode_605_name()+" to "+dataafter.getCode_605().getCode_605_name()+".";
        }
        if(databe4.getCode_601_problem().getCode_601p_name()==null&&dataafter.getCode_601_problem()!=null)
        {
            hasil+=" 601 Problem from blank to "+dataafter.getCode_601_problem().getCode_601p_name()+".";
        }
        else if((databe4.getCode_601_problem().getCode_601p_name()!=null&&!databe4.getCode_601_problem().getCode_601p_name().isEmpty()&&databe4.getCode_601_problem()!=null&&dataafter.getCode_601_problem()!=null&&dataafter.getCode_601_problem().getCode_601p_name()!=null)&&!databe4.getCode_601_problem().equals(dataafter.getCode_601_problem()))
        {
            hasil+=" 601 Problem from "+databe4.getCode_601_problem().getCode_601p_name()+" to "+dataafter.getCode_601_problem().getCode_601p_name()+".";
        }
        if(databe4.getCode_602_problem()==null&&dataafter.getCode_602_problem()!=null)
        {
            hasil+=" 602 Problem from blank to "+dataafter.getCode_602_problem().getCode_602p_name()+".";
        }
        else if((databe4.getCode_602_problem()!=null&&!databe4.getCode_602_problem().getCode_602p_name().isEmpty()&&databe4.getCode_602_problem()!=null&&dataafter.getCode_602_problem()!=null&&dataafter.getCode_602_problem().getCode_602p_name()!=null)&&!databe4.getCode_602_problem().equals(dataafter.getCode_602_problem()))
        {
            hasil+=" 602 Problem from "+databe4.getCode_602_problem().getCode_602p_name()+" to "+dataafter.getCode_602_problem().getCode_602p_name()+".";
        }
        if(databe4.getCode_604_problem()==null&&dataafter.getCode_604_problem()!=null)
        {
            hasil+=" 604 Problem from blank to "+dataafter.getCode_604_problem().getCode_604p_name()+".";
        }
        else if((databe4.getCode_604_problem()!=null&&!databe4.getCode_604_problem().getCode_604p_name().isEmpty()&&databe4.getCode_604_problem()!=null&&dataafter.getCode_604_problem()!=null&&dataafter.getCode_604_problem().getCode_604p_name()!=null)&&!databe4.getCode_604_problem().equals(dataafter.getCode_604_problem()))
        {
            hasil+=" 604 Problem from "+databe4.getCode_604_problem().getCode_604p_name()+" to "+dataafter.getCode_604_problem().getCode_604p_name()+".";
        }
        if(databe4.getCode_605_problem()==null&&dataafter.getCode_605_problem()!=null)
        {
            hasil+=" 605 Problem from blank to "+dataafter.getCode_605_problem().getCode_605p_name()+".";
        }
        else if((databe4.getCode_605_problem()!=null&&!databe4.getCode_605_problem().getCode_605p_name().isEmpty()&&databe4.getCode_605_problem()!=null&&dataafter.getCode_605_problem()!=null&&dataafter.getCode_605_problem().getCode_605p_name()!=null)&&!databe4.getCode_605_problem().equals(dataafter.getCode_605_problem()))
        {
            hasil+=" 605 Problem from "+databe4.getCode_605_problem().getCode_605p_name()+" to "+dataafter.getCode_605_problem().getCode_605p_name()+".";
        }
        if(isIsEditDisputeFile())
        {
            hasil+=" Dispute file changed.";
        }
        if(databe4.getSettlement_number().trim().isEmpty()&&!getStrFileNamePenyelesaian().trim().isEmpty())
        {
            hasil+=" Added new Settlement file with name "+getStrFileNamePenyelesaian().trim()+".";
        }
        else if(isIsEditSettleFile())
//        else if(!databe4.getSettlement_number().trim().substring(databe4.getSettlement_number().lastIndexOf("/")+1).equals(getStrFileNamePenyelesaian().trim()))
        {
            hasil+=" Settlement file changed.";
//            hasil+=" Settlement file from "+databe4.getSettlement_number().trim().substring(databe4.getSettlement_number().lastIndexOf("/")+1)+" to "+getStrFileNamePenyelesaian().trim()+".";
        }
        if(databe4.getExtension_filepath().trim().isEmpty()&&!getStrFileNamePerpanjangan().trim().isEmpty())
        {
            hasil+=" Added new Extension file with name "+getStrFileNamePerpanjangan().trim()+".";
        }
        else if(isIsEditExtFile())
//        else if(!databe4.getExtension_filepath().trim().substring(databe4.getExtension_filepath().lastIndexOf("/")+1).equals(getStrFileNamePerpanjangan().trim()))
        {
            hasil+=" Extension file changed.";
//            hasil+=" Extension file from "+databe4.getExtension_filepath().trim().substring(databe4.getExtension_filepath().lastIndexOf("/")+1)+" to "+getStrFileNamePerpanjangan().trim()+".";
        }
        return hasil;
    }
    
    public String formattedDescription(String desc) {
        if (desc == null) return "";
        return desc.replaceAll("\\. ", ".\n");
    }
}
