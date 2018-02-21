package extras;

/**
 * Created by Narendra on 2/15/2018.
 */

public class PregnantGetSet {

    int pregnantuid,orderofpregnancy;
    String pregnancyid, surveyid, lmpdate, surveyorid, timestamp, source;
    String IS_ACTIVE;
    String IS_EDITED;
    String IS_NEW;
    String IS_APPROVED;

    public String getIS_APPROVED() {
        return IS_APPROVED;
    }

    public void setIS_APPROVED(String IS_APPROVED) {
        this.IS_APPROVED = IS_APPROVED;
    }

    public String getIS_ACTIVE() {

        return IS_ACTIVE;
    }

    public void setIS_ACTIVE(String IS_ACTIVE) {
        this.IS_ACTIVE = IS_ACTIVE;
    }

    public String getIS_EDITED() {
        return IS_EDITED;
    }

    public void setIS_EDITED(String IS_EDITED) {
        this.IS_EDITED = IS_EDITED;
    }

    public String getIS_NEW() {
        return IS_NEW;
    }

    public void setIS_NEW(String IS_NEW) {
        this.IS_NEW = IS_NEW;
    }

    public PregnantGetSet(String pregnancyid, String surveyid, int orderofpregnancy, String lmpdate, String surveyorid, String timestamp, String source,String IS_ACTIVE,String IS_EDITED,String IS_NEW ,String IS_APPROVED)  {

        this.pregnancyid = pregnancyid;
        this.surveyid = surveyid;
        this.orderofpregnancy = orderofpregnancy;
        this.lmpdate = lmpdate;
        this.surveyorid = surveyorid;
        this.timestamp = timestamp;
        this.source = source;
        this.IS_ACTIVE=IS_ACTIVE;
        this.IS_APPROVED=IS_APPROVED;
        this.IS_EDITED=IS_EDITED;
        this.IS_NEW=IS_NEW;
    }

    public int getPregnantuid() {
        return pregnantuid;
    }

    public void setPregnantuid(int pregnantuid) {
        this.pregnantuid = pregnantuid;
    }

    public String getPregnancyid() {
        return pregnancyid;
    }

    public void setPregnancyid(String pregnancyid) {
        this.pregnancyid = pregnancyid;
    }

    public String getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(String surveyid) {
        this.surveyid = surveyid;
    }

    public int getOrderofpregnancy() {
        return orderofpregnancy;
    }

    public void setOrderofpregnancy(int orderofpregnancy) {
        this.orderofpregnancy = orderofpregnancy;
    }

    public String getLmpdate() {
        return lmpdate;
    }

    public void setLmpdate(String lmpdate) {
        this.lmpdate = lmpdate;
    }

    public String getSurveyorid() {
        return surveyorid;
    }

    public void setSurveyorid(String surveyorid) {
        this.surveyorid = surveyorid;
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
