package extras;

/**
 * Created by Ranjeet on 2/23/2018.
 */

public class BeneficiaryList {
    String Members_id,family_id,name,child_id,childname,status,stage,sub_stage,lmp_date,dedelivery,current_sub_stage;

    public String getPregnancy_id() {
        return pregnancy_id;
    }

    public void setPregnancy_id(String pregnancy_id) {
        this.pregnancy_id = pregnancy_id;
    }

    String pregnancy_id;

    public String getIs_anc() {
        return is_anc;
    }

    public void setIs_anc(String is_anc) {
        this.is_anc = is_anc;
    }

    String is_anc;

    public String getMembers_id() {
        return Members_id;
    }

    public void setMembers_id(String members_id) {
        Members_id = members_id;
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

    public String getChild_id() {
        return child_id;
    }

    public void setChild_id(String child_id) {
        this.child_id = child_id;
    }

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
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

    public String getSub_stage() {
        return sub_stage;
    }

    public void setSub_stage(String sub_stage) {
        this.sub_stage = sub_stage;
    }

    public String getLmp_date() {
        return lmp_date;
    }

    public void setLmp_date(String lmp_date) {
        this.lmp_date = lmp_date;
    }

    public String getDedelivery() {
        return dedelivery;
    }

    public void setDedelivery(String dedelivery) {
        this.dedelivery = dedelivery;
    }

    public String getCurrent_sub_stage() {
        return current_sub_stage;
    }

    public void setCurrent_sub_stage(String current_sub_stage) {
        this.current_sub_stage = current_sub_stage;
    }

    public BeneficiaryList(String members_id, String family_id, String name, String child_id, String childname, String status, String stage, String sub_stage, String lmp_date, String dedelivery, String current_sub_stage,String pregnancy_id,String is_anc) {

        Members_id = members_id;
        this.family_id = family_id;
        this.name = name;
        this.child_id = child_id;
        this.childname = childname;
        this.status = status;
        this.stage = stage;
        this.sub_stage = sub_stage;
        this.lmp_date = lmp_date;
        this.dedelivery = dedelivery;
        this.current_sub_stage = current_sub_stage;
        this.pregnancy_id=pregnancy_id;
        this.is_anc = is_anc;
    }
}
