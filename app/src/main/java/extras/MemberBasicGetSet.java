package extras;

/**
 * Created by Narendra on 2/15/2018.
 */

public class MemberBasicGetSet {
    String isNew;

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getIsEditedMember() {
        return isEditedMember;
    }

    public void setIsEditedMember(String isEditedMember) {
        this.isEditedMember = isEditedMember;
    }

    public String getIsApprovedMember() {
        return isApprovedMember;
    }

    public void setIsApprovedMember(String isApprovedMember) {
        this.isApprovedMember = isApprovedMember;
    }

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    String isEditedMember;
    String isApprovedMember;

    String MemberId;
    String family_id;
    String name;
    String husband;
    String isApprove;
    String pctsid;
    String dor;
    String doentry;
    String doexit;
    String dob;
    String age;
    String ifdobasum;
    String dod;
    String aadhar;
    String aadharenrol;
    String aadhardate;
    String aadhartime;
    String bhamasha;
    String mobile;
    String relation;
    String sex;
    String handicap;
    String ifmarried;
    String motherid;
    String status;
    String stage;
    String substage;
    String trackstatus;
    String surveyor_id;
    String timestamp;
    String source;

    public MemberBasicGetSet(String MemberId, String family_id, String name, String husband, String pctsid, String dor, String doentry, String doexit, String dob, String age, String ifdobasum, String dod, String aadhar, String aadharenrol, String aadhardate, String aadhartime, String bhamasha, String mobile, String relation, String sex, String handicap, String ifmarried, String motherid, String status, String stage, String substage, String trackstatus, String surveyor_id, String timestamp, String source,
                             String isNew,String isEditedMember,String isApprovedMember) {
        this.MemberId = MemberId;
        this.family_id = family_id;
        this.name = name;
        this.husband = husband;
        this.pctsid = pctsid;
      //  this.isApprove = isApprove;
        this.dor = dor;
        this.doentry = doentry;
        this.doexit = doexit;
        this.dob = dob;
        this.age = age;
        this.ifdobasum = ifdobasum;
        this.dod = dod;
        this.aadhar = aadhar;
        this.aadharenrol = aadharenrol;
        this.aadhardate = aadhardate;
        this.aadhartime = aadhartime;
        this.bhamasha = bhamasha;
        this.mobile = mobile;
        this.relation = relation;
        this.sex = sex;
        this.handicap = handicap;
        this.ifmarried = ifmarried;
        this.motherid = motherid;
        this.status = status;
        this.stage = stage;
        this.substage = substage;
        this.trackstatus = trackstatus;
        this.surveyor_id = surveyor_id;
        this.timestamp = timestamp;
        this.source = source;
        this.isApprovedMember=isApprovedMember;
        this.isNew=isNew;
        this.isEditedMember=isEditedMember;
    }

    public String getmemberId() {
        return MemberId;
    }

    public void getmemberId(String memeberId) {
        this.MemberId = memeberId;
    }

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHusband() {
        return husband;
    }

    public void setHusband(String husband) {
        this.husband = husband;
    }

    public String getPctsid() {
        return pctsid;
    }

    public void setPctsid(String pctsid) {
        this.pctsid = pctsid;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getDoentry() {
        return doentry;
    }

    public void setDoentry(String doentry) {
        this.doentry = doentry;
    }

    public String getDoexit() {
        return doexit;
    }

    public void setDoexit(String doexit) {
        this.doexit = doexit;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIfdobasum() {
        return ifdobasum;
    }

    public void setIfdobasum(String ifdobasum) {
        this.ifdobasum = ifdobasum;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAadharenrol() {
        return aadharenrol;
    }

    public void setAadharenrol(String aadharenrol) {
        this.aadharenrol = aadharenrol;
    }

    public String getAadhardate() {
        return aadhardate;
    }

    public void setAadhardate(String aadhardate) {
        this.aadhardate = aadhardate;
    }

    public String getAadhartime() {
        return aadhartime;
    }

    public void setAadhartime(String aadhartime) {
        this.aadhartime = aadhartime;
    }

    public String getBhamasha() {
        return bhamasha;
    }

    public void setBhamasha(String bhamasha) {
        this.bhamasha = bhamasha;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getIfmarried() {
        return ifmarried;
    }

    public void setIfmarried(String ifmarried) {
        this.ifmarried = ifmarried;
    }

    public String getMotherid() {
        return motherid;
    }

    public void setMotherid(String motherid) {
        this.motherid = motherid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSubstage() {
        return substage;
    }

    public void setSubstage(String substage) {
        this.substage = substage;
    }

    public String getTrackstatus() {
        return trackstatus;
    }

    public void setTrackstatus(String trackstatus) {
        this.trackstatus = trackstatus;
    }

    public String getSurveyor_id() {
        return surveyor_id;
    }

    public void setSurveyor_id(String surveyor_id) {
        this.surveyor_id = surveyor_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
