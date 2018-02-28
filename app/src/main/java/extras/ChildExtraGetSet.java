package extras;

/**
 * Created by Narendra on 2/15/2018.
 */

public class ChildExtraGetSet {
    int keyid;

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getISEdited() {
        return ISEdited;
    }

    public void setISEdited(String ISEdited) {
        this.ISEdited = ISEdited;
    }

    String isApproved,isNew,ISEdited;

    String survey_id, do_delivery, delivery_place, child_order, birth_wl, full_term, whenfirst_bf, iffeed_khees, currently_bf, whenstop_bf, anythingbefore_bf, ifstarted_solidfood, whichmonth_solidfood, childimmunization_status;

    public ChildExtraGetSet( String survey_id, String do_delivery, String delivery_place, String child_order, String birth_wl, String full_term, String whenfirst_bf, String iffeed_khees,
                             String currently_bf, String whenstop_bf, String anythingbefore_bf, String ifstarted_solidfood,
                             String whichmonth_solidfood, String childimmunization_status,String isApproved,String isNew,String ISEdited) {

        this.survey_id = survey_id;
        this.do_delivery = do_delivery;
        this.delivery_place = delivery_place;
        this.child_order = child_order;
        this.birth_wl = birth_wl;
        this.full_term = full_term;
        this.whenfirst_bf = whenfirst_bf;
        this.iffeed_khees = iffeed_khees;
        this.currently_bf = currently_bf;
        this.whenstop_bf = whenstop_bf;
        this.anythingbefore_bf = anythingbefore_bf;
        this.ifstarted_solidfood = ifstarted_solidfood;
        this.whichmonth_solidfood = whichmonth_solidfood;
        this.childimmunization_status = childimmunization_status;
    }


    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getDo_delivery() {
        return do_delivery;
    }

    public void setDo_delivery(String do_delivery) {
        this.do_delivery = do_delivery;
    }

    public String getDelivery_place() {
        return delivery_place;
    }

    public void setDelivery_place(String delivery_place) {
        this.delivery_place = delivery_place;
    }

    public String getChild_order() {
        return child_order;
    }

    public void setChild_order(String child_order) {
        this.child_order = child_order;
    }

    public String getBirth_wl() {
        return birth_wl;
    }

    public void setBirth_wl(String birth_wl) {
        this.birth_wl = birth_wl;
    }

    public String getFull_term() {
        return full_term;
    }

    public void setFull_term(String full_term) {
        this.full_term = full_term;
    }

    public String getWhenfirst_bf() {
        return whenfirst_bf;
    }

    public void setWhenfirst_bf(String whenfirst_bf) {
        this.whenfirst_bf = whenfirst_bf;
    }

    public String getIffeed_khees() {
        return iffeed_khees;
    }

    public void setIffeed_khees(String iffeed_khees) {
        this.iffeed_khees = iffeed_khees;
    }

    public String getCurrently_bf() {
        return currently_bf;
    }

    public void setCurrently_bf(String currently_bf) {
        this.currently_bf = currently_bf;
    }

    public String getWhenstop_bf() {
        return whenstop_bf;
    }

    public void setWhenstop_bf(String whenstop_bf) {
        this.whenstop_bf = whenstop_bf;
    }

    public String getAnythingbefore_bf() {
        return anythingbefore_bf;
    }

    public void setAnythingbefore_bf(String anythingbefore_bf) {
        this.anythingbefore_bf = anythingbefore_bf;
    }

    public String getIfstarted_solidfood() {
        return ifstarted_solidfood;
    }

    public void setIfstarted_solidfood(String ifstarted_solidfood) {
        this.ifstarted_solidfood = ifstarted_solidfood;
    }

    public String getWhichmonth_solidfood() {
        return whichmonth_solidfood;
    }

    public void setWhichmonth_solidfood(String whichmonth_solidfood) {
        this.whichmonth_solidfood = whichmonth_solidfood;
    }

    public String getChildimmunization_status() {
        return childimmunization_status;
    }

    public void setChildimmunization_status(String childimmunization_status) {
        this.childimmunization_status = childimmunization_status;
    }

    public int getKeyid() {
        return keyid;
    }

    public void setKeyid(int keyid) {
        this.keyid = keyid;
    }
}
