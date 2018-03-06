package extras;

/**
 * Created by Ranjeet on 3/5/2018.
 */

public class ProfileGetSetMethod {

    String id,village_name,surveyor_name,surveyor_id;

    public String getSurveyor_id() {
        return surveyor_id;
    }

    public void setSurveyor_id(String surveyor_id) {
        this.surveyor_id = surveyor_id;
    }

    public ProfileGetSetMethod(String id, String village_name, String surveyor_name, String surveyor_id) {
        this.id = id;
        this.village_name = village_name;
        this.surveyor_name = surveyor_name;
        this.surveyor_id=surveyor_id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getSurveyor_name() {
        return surveyor_name;
    }

    public void setSurveyor_name(String surveyor_name) {
        this.surveyor_name = surveyor_name;
    }
}
