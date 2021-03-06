package extras;

/**
 * Created by Narendra on 2/15/2018.
 */

public class FamilyDetailGetSet {

    int  religion, cast, rcard, familytype;
    String  dist_code;
    String project_code;
    String sector_code;
    String awc_code;
    String surveyor_id;
    String familyid;

    public String getVillage_code() {
        return village_code;
    }

    public void setVillage_code(String village_code) {
        this.village_code = village_code;
    }

    String village_code;

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    String is_new;

    public FamilyDetailGetSet(String familyid, int religion, int cast, int rcard, int familytype, String dist_code, String project_code, String sector_code, String awc_code, String surveyor_id,String village_code,String is_new) {
        this.familyid = familyid;
        this.religion = religion;
        this.cast = cast;
        this.rcard = rcard;
        this.familytype = familytype;
        this.dist_code = dist_code;
        this.project_code = project_code;
        this.sector_code = sector_code;
        this.awc_code = awc_code;
        this.surveyor_id = surveyor_id;
        this.village_code=village_code;
        this.is_new=is_new;
    }

    public String getFamilyid() {
        return familyid;
    }

    public void setFamilyid(String familyid) {
        this.familyid = familyid;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public int getCast() {
        return cast;
    }

    public void setCast(int cast) {
        this.cast = cast;
    }

    public int getRcard() {
        return rcard;
    }

    public void setRcard(int rcard) {
        this.rcard = rcard;
    }

    public int getFamilytype() {
        return familytype;
    }

    public void setFamilytype(int familytype) {
        this.familytype = familytype;
    }

    public String getDist_code() {
        return dist_code;
    }

    public void setDist_code(String dist_code) {
        this.dist_code = dist_code;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getSector_code() {
        return sector_code;
    }

    public void setSector_code(String sector_code) {
        this.sector_code = sector_code;
    }

    public String getAwc_code() {
        return awc_code;
    }

    public void setAwc_code(String awc_code) {
        this.awc_code = awc_code;
    }

    public String getSurveyor_id() {
        return surveyor_id;
    }

    public void setSurveyor_id(String surveyor_id) {
        this.surveyor_id = surveyor_id;
    }
}
