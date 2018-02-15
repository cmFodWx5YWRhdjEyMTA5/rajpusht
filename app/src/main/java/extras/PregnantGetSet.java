package extras;

/**
 * Created by Narendra on 2/15/2018.
 */

public class PregnantGetSet {

    int pregnantuid;
    String pregnancyid, surveyid, orderofpregnancy, lmpdate, surveyorid, timestamp, source;

    public PregnantGetSet(int pregnantuid, String pregnancyid, String surveyid, String orderofpregnancy, String lmpdate, String surveyorid, String timestamp, String source) {
        this.pregnantuid = pregnantuid;
        this.pregnancyid = pregnancyid;
        this.surveyid = surveyid;
        this.orderofpregnancy = orderofpregnancy;
        this.lmpdate = lmpdate;
        this.surveyorid = surveyorid;
        this.timestamp = timestamp;
        this.source = source;
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

    public String getOrderofpregnancy() {
        return orderofpregnancy;
    }

    public void setOrderofpregnancy(String orderofpregnancy) {
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
