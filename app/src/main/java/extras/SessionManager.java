package extras;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import in.co.rajpusht.rajpusht.Login;

/**
 * Created by Narendra on 6/8/2017.
 */

public class SessionManager {

    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;
    SharedPreferences sharedprefernceCoupon;
    SharedPreferences.Editor editorCoupon;
    Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME="sharedcheckLogin";
    private static final String PREF_NAME2="sharedcheckLogin2";
    private static final String User_Id="userid";
    private static final String IS_LOGIN="is_login";
    private static final String User_name="username";
    private static final String SURVEYOR_NAME="surveyour_name";
    private static final String PROJECT_CODE="project_code";
    private static final String SECTOR_CODE="sector_code";
    private static final String VILLAGE_CODE="village_code";
    private static final String VILLAGE_ENG="village_eng";
    private static final String VILLAGE_HINDI="village_hindi";
    private static final String SURVEYOR_ID="surveyor_id";
    private static final String AWC_CODE="awc_code";
    private static final String DIST_CODE="distcode";
    private static final String LOCATION_ID="location_id";
    private static final String AWC_ENG="awc_eng";
    private static final String Langugae="language_selection";
    private static final String VILLAGE_SELECTED_ID="village_selected_id";
    private static final String PRESENT_DATE="present_date";









    public SessionManager(Context context)
    {

        this.context =  context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor=sharedprefernce.edit();

        sharedprefernceCoupon=context.getSharedPreferences(PREF_NAME2,PRIVATE_MODE);
        editorCoupon=sharedprefernceCoupon.edit();

    }

    public void setPresentDate(long presentDate)
    {
        editor.putLong(PRESENT_DATE,presentDate).apply();
        editor.commit();

    }
    public long getPresentDate()
    {

        return  sharedprefernce.getLong(PRESENT_DATE,0);

    }

    public void setVillageSelectedId(String villageSelectedId){
        editor.putString(VILLAGE_SELECTED_ID,villageSelectedId);
        editor.commit();
    }
    public String getVillageSelectedId() {

        return sharedprefernce.getString(VILLAGE_SELECTED_ID,"");
    }
    public Boolean isLogin(){
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }

    public void setUserID(String id ){

     editor.putString(User_Id,id);
     editor.commit();


    }

    public void setLangugae(String langugae){
        editor.putString(Langugae,langugae);
        editor.commit();
    }
    public String getLangugae(){
        return sharedprefernce.getString(Langugae,"en");
    }

    public void setAwcEng(String awc_eng){
        editor.putString(AWC_ENG,awc_eng);
        editor.commit();
    }
    public String getAwcEng(){
        return sharedprefernce.getString(AWC_ENG,"DEFAULT");
    }

    public void setLocationId(String locationid){
        editor.putString(LOCATION_ID,locationid);
        editor.commit();
    }
    public String getLocationId(){
        return sharedprefernce.getString(LOCATION_ID,"DEFAULT");
    }

    public void setSurveyorName(String servyorname){
        editor.putString(SURVEYOR_NAME,servyorname);
        editor.commit();
         }

    public String getSurveyorName(){
        return sharedprefernce.getString(SURVEYOR_NAME,"DEFAULT");
    }

    public void setProjectCode(String projectCode){
        editor.putString(PROJECT_CODE,projectCode);
        editor.commit();
    }
    public String getProjectCode(){

        return sharedprefernce.getString(PROJECT_CODE,"DEFAULT");
    }

    public void setSectorCode(String sectorCode){
        editor.putString(SECTOR_CODE,sectorCode);
        editor.commit();
    }
    public String getSectorCode(){

        return sharedprefernce.getString(SECTOR_CODE,"DEFULT");
    }

    public void setVillageCode(String villageCode){
        editor.putString(VILLAGE_CODE,villageCode);
        editor.commit();
    }

    public String getVillageCode(){
        return sharedprefernce.getString(VILLAGE_CODE,"DEFAULT");
    }

    public void setVillageEng(String villageEng){
        editor.putString(VILLAGE_ENG,villageEng);
        editor.commit();
    }
    public String getVillageEng(){

        return  sharedprefernce.getString(VILLAGE_ENG,"DEFAULT");
    }
    public void setVillageHindi(String villageHindi){
        editor.putString(VILLAGE_HINDI,villageHindi);
        editor.commit();
    }
    public String getVillageHindi(){
        return sharedprefernce.getString(VILLAGE_HINDI,"DEFAULT");
    }




    public String getUserID(){

        return  sharedprefernce.getString(User_Id,"DEFAULT");
    }

    public void setUser_name(String name){
        editor.putString(User_name,name);
        editor.commit();
    }

    public String getUser_name(){
        return sharedprefernce.getString(User_name,"DEFAULT");
    }

    public void setSurveyorId(String surveyorId){
        editor.putString(SURVEYOR_ID,surveyorId);
        editor.commit();
    }
    public String getSurveyorId(){
        return sharedprefernce.getString(SURVEYOR_ID,"DEAFULT");

    }


    public void setAwcCode(String awc){
        editor.putString(AWC_CODE,awc);
        editor.commit();
    }
    public String getAwcCode(){
        return sharedprefernce.getString(AWC_CODE,"DEFAULT");
    }

    public void setDistCode(String distcide){
        editor.putString(DIST_CODE,distcide);
        editor.commit();
    }
    public String getDistCode(){
        return sharedprefernce.getString(DIST_CODE,"DEFAULT");
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);


    }

}


