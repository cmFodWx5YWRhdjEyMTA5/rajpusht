package extras;

/**
 * Created by Ranjeet on 3/5/2018.
 */

public class ProfileGetSetMethod {

    String id;
    String village_name;
    String surveyor_name;
    String surveyor_id;
    String loginchecked;

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    String sectorName;
    String ProjectName;

    public String getSurveyor_id() {
        return surveyor_id;
    }

    public void setSurveyor_id(String surveyor_id) {
        this.surveyor_id = surveyor_id;
    }

    public ProfileGetSetMethod(String id, String village_name, String surveyor_name, String surveyor_id,String loginchecked,String projectName,String sectorName) {
        this.id = id;
        this.village_name = village_name;
        this.surveyor_name = surveyor_name;
        this.surveyor_id=surveyor_id;
        this.loginchecked=loginchecked;
        this.ProjectName=projectName;
        this.sectorName=sectorName;

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

    public String getLoginchecked() {
        return loginchecked;
    }

    public void setLoginchecked(String loginchecked) {
        this.loginchecked = loginchecked;
    }
}
