package in.co.rajpusht.rajpusht;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import extras.BaseUrl;
import extras.DbHelper;
import extras.SessionManager;

public class Login extends AppCompatActivity {
    SessionManager session;
//    public static String awc_code="08110080101";
//    public static String dist_code="08";
//    public static String project_code="11";
//    public static String sector_code="008";
//    public static String village_code="101";
////    if()
//    public   String surveyerId="11";
//    public static String awc_code="08110080101";

public static String selectedId="";

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DbHelper db = new DbHelper(this);

        session= new SessionManager(Login.this);


        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().length()==0 || password.getText().toString().length()==0) {
                    Toast.makeText(Login.this, "Enter  Username and  Password", Toast.LENGTH_SHORT).show();
                }
                else {
//validateLogin();

                    LoginDetails login = new LoginDetails();
                    login.execute(username.getText().toString(),password.getText().toString());
//
                }
            }
        });

    }

    public void validateLogin(){

    }

    public class LoginDetails  extends AsyncTask<String, String, String>

    {

        String uid;
        String result;
        String value;
        ProgressDialog asyncDialog ;

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String json = null;



            String name=params[0];
            String password=params[1];
//            String lon=params[2];

            try {

                HttpResponse response;

                JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("UserId",session.getUserID());

//                jsonObject.accumulate("Lat",lat);
//                jsonObject.accumulate("Long",lon);
//                jsonObject.accumulate("Remarks",name);



                json = jsonObject.toString();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(new BaseUrl().base_url+"restservice/surveyor/user/"+name+"/"+password);
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setEntity(new StringEntity(json, "UTF-8"));
//  httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

//                httpPost.setHeader("Accept-Encoding", "application/json");
//                httpPost.setHeader("Accept-Language", "en-US");
                response = httpClient.execute(httpPost);

//                String the_string_response = convertResponseToString(response);
                String sresponse = response.getEntity().toString();
                Log.w("QueingSystem", sresponse);
//                Log.w("QueingSystem", EntityUtils.toString(response.getEntity()));
                value= EntityUtils.toString(response.getEntity());

                Log.w("LoginDetails",value);


            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());

            }

            return value;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            asyncDialog = new ProgressDialog(Login.this);
            asyncDialog.setMessage("Please wait data Validating..");
            asyncDialog.setCanceledOnTouchOutside(false);
            asyncDialog.setCancelable(false);
            asyncDialog.show();


        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            Log.d("LoginDetails",aVoid);
//


            String flushLocation="delete from assigned_location";
            String flushUser="delete from SURVEYOR_LOGIN";

//            SQLiteDatabase dbsflush = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
//
//            Cursor cFlush = dbsflush.rawQuery(flush,null);
//            cFlush.moveToFirst();
//            cFlush.close();

            ExeuteQuery(flushLocation);
            ExeuteQuery(flushUser);

            try {

                JSONObject josnMainObject = new JSONObject(aVoid);
                JSONArray jsonArrayAssignedLocation = josnMainObject.getJSONArray("assignedLocation");
                JSONArray jsonArraySurveyorDetails = josnMainObject.getJSONArray("surveyorData");
                for(int i=0;i<jsonArrayAssignedLocation.length();i++){

                    JSONObject jsonSubObject = jsonArrayAssignedLocation.getJSONObject(i);
                    String awcNameHindi = jsonSubObject.getString("awcNameHindi");
                    String sectorcode = jsonSubObject.getString("sectorcode");
                    String distcode = jsonSubObject.getString("distcode");
                    String awccode = jsonSubObject.getString("awccode");
                    String projectcode = jsonSubObject.getString("projectcode");
                    String surveyorId = jsonSubObject.getString("surveyorId");
                    String villageName = jsonSubObject.getString("villageName");
                    String awcName = jsonSubObject.getString("awcName");
                    String villageNameHindi = jsonSubObject.getString("villageNameHindi");
                    String villageCode = jsonSubObject.getString("villageCode");


                    String insertLocation="INSERT INTO ASSIGNED_LOCATION (DIST_CODE,PROJECT_CODE,SECTOR_CODE,VILLAGE_CODE," +
                            "VILLAGE_ENG,VILLAGE_HINDI,AWC_CODE,SURVEYOR_NAME,SURVEYOR_ID,AWC_ENG,AWC_HINDI) VALUES ('"
                            + distcode +"','"+projectcode +"','"+ sectorcode + "','"+ villageCode +"','"+ villageName +
                            "','"+ villageNameHindi + "','"+ awccode +"','soumya',"+surveyorId +",'"+awcName+"','"+awcNameHindi+"')";

                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                    Cursor c = dbs.rawQuery(insertLocation,null);
                    c.moveToFirst();
                    c.close();

                }

                for(int survid= 0;survid<jsonArraySurveyorDetails.length();survid++){

                    JSONObject jsonObjectSurvey = jsonArraySurveyorDetails.getJSONObject(survid);

                    String Name=jsonObjectSurvey.getString("name");
//                     String password=jsonObjectSurvey.getString("");
                     String  email=jsonObjectSurvey.getString("email");
                    String phone=jsonObjectSurvey.getString("mobile");
//                            String loged=jsonObjectSurvey.getString("Yes");
                            String userId=jsonObjectSurvey.getString("surveyorId");
                    session.setLogin();
                    session.setSurveyorName(Name);
session.setUserID(userId);
                            String insertSurvoyourId="INSERT INTO SURVEYOR_LOGIN (USER_ID,PASSWORD,LOGED,EMAIL,MOBILE,NAME) VALUES ("+userId+",'"+ password.getText().toString() +"','Y','"+email+"','"+phone+"','"+Name+"')";
                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                    Cursor c = dbs.rawQuery(insertSurvoyourId,null);
                    c.moveToFirst();
                    c.close();
                }





             PullDataWebservice pulldata = new PullDataWebservice();
                pulldata.execute(session.getUserID());

                asyncDialog.dismiss();

            } catch (Exception e) {
                Log.d("Ranjeet", "ranjeet Error" + e.toString());
                Toast.makeText(Login.this, "Enter Valid Username and Password", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
                asyncDialog.dismiss();
//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);
//                finish();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }


    }


    public String  locationcheck(){
        String status;

        String checkQuery="SELECT * FROM ASSIGNED_LOCATION WHERE LOGIN='Y'";
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(checkQuery,null);
        if(c.getCount()>0){

            if (c.moveToFirst()) {
                do {

                    session.setAwcCode(c.getString(c.getColumnIndex("awc_code")));
                    session.setDistCode(c.getString(c.getColumnIndex("dist_code")));
                    session.setProjectCode(c.getString(c.getColumnIndex("project_code")));
                    session.setSectorCode(c.getString(c.getColumnIndex("sector_code")));
                    session.setVillageCode(c.getString(c.getColumnIndex("village_code")));
                    session.setVillageEng(c.getString(c.getColumnIndex("village_eng")));
                    session.setVillageHindi(c.getString(c.getColumnIndex("village_hindi")));
                    session.setSurveyorName(c.getString(c.getColumnIndex("surveyor_name")));
                    session.setSurveyorId(c.getString(c.getColumnIndex("surveyor_id")));


                } while (c.moveToNext());
            }

            status="true";
        }else{
            status="false";

        }

        return status;
    }



    public void ExeuteQuery(String query){
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c1 = dbs.rawQuery(query,null);
        c1.moveToFirst();
        c1.close();
    }

    public class PullDataWebservice  extends AsyncTask<String, String, String>

    {

        String uid;
        String result;
        String value;
        ProgressDialog asyncDialog ;

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String json = null;



            String stringName=params[0];


            try {

                HttpResponse response;

                JSONObject jsonObject = new JSONObject();

                Log.d("dashboradUrl",new BaseUrl().base_url+"restservice/surveyor/getSurveyList/"+stringName);
//
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(new BaseUrl().base_url+"restservice/surveyor/getSurveyList/"+stringName);
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setEntity(new StringEntity(stringName, "UTF-8"));
//  httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Accept-Language", "en-US");
                response = httpClient.execute(httpPost);

//                String the_string_response = convertResponseToString(response);
                String sresponse = response.getEntity().toString();
                Log.w("QueingSystem", sresponse);
//                Log.w("QueingSystem", EntityUtils.toString(response.getEntity()));
                value= EntityUtils.toString(response.getEntity());

                Log.w("LoginDetails",value.toString());


            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());

            }

            return value;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            asyncDialog = new ProgressDialog(Login.this);
            asyncDialog.setMessage("Please wait data is sync is going on..");
            asyncDialog.show();


        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            Log.d("LoginDetails",aVoid);
//

            try {


                pullDataMethod(aVoid);

                String loginStatus = locationcheck();

                if(loginStatus.equalsIgnoreCase("true")){

                    Intent intentprofile = new Intent(getApplicationContext(),DashBoard.class);
                    startActivity(intentprofile);
                    finish();

                }
                else{

                    Intent intentProfile = new Intent(getApplicationContext(),Profile.class);
                    startActivity(intentProfile);
                    finish();
                }

            }catch(Exception e){

                Log.d("syncActivity",e.toString()) ;
                asyncDialog.dismiss();

            }
            asyncDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }


    }



    public  String pullDataMethod(String userId){

        String completed="";
        try {

            String deletemember ="DELETE FROM memberbasic";
            String deletefamily ="DELETE FROM familydata";
            String deletePREGNANT ="DELETE FROM PREGNANT";
            String deleteCHILD_EXTRA ="DELETE FROM CHILDEXTRA";
            String deleteWOMEN_EXTRA =" DELETE FROM WOMENEXTRA";
            String deleteCHILD_TRACKING ="DELETE FROM CHILD_TRACKING";
            String deletePW_TRACKING ="DELETE FROM PW_TRACKING";
            String deleteDIET ="DELETE FROM DIET";


            ExeuteQuery(deletemember);
            ExeuteQuery(deletefamily);
            ExeuteQuery(deletePREGNANT);
            ExeuteQuery(deleteCHILD_EXTRA);
            ExeuteQuery(deleteWOMEN_EXTRA);
            ExeuteQuery(deleteCHILD_TRACKING);
            ExeuteQuery(deletePW_TRACKING);
            ExeuteQuery(deleteDIET);

            JSONObject jsonObject = new JSONObject(userId);
            JSONArray jsonArrayMember = jsonObject.getJSONArray("memberBasic");

            for(int i =0;i<jsonArrayMember.length();i++){
                JSONObject jsonobjectMember = jsonArrayMember.getJSONObject(i);

                String   dodeath = jsonobjectMember.getString("dodeath");
                String    aaddharTimeStamp = jsonobjectMember.getString("aaddharTimeStamp");
                String   isToTrack = jsonobjectMember.getString("isToTrack");
                String     dor = jsonobjectMember.getString("dor");
                String       aaddharDateStamp = jsonobjectMember.getString("aaddharDateStamp");
                String      source = jsonobjectMember.getString("source");
                String      aaddhar = jsonobjectMember.getString("aaddhar");
                String   relation = jsonobjectMember.getString("relation");
                String      membersId = jsonobjectMember.getString("membersId");
                String   surveyorId = jsonobjectMember.getString("surveyorId");
                String     doexit = jsonobjectMember.getString("doexit");
                String     doentry = jsonobjectMember.getString("doentry");
                String      ifMarried = jsonobjectMember.getString("ifMarried");
                String     isApproved = jsonobjectMember.getString("isApproved");
                String      aaddharEnrolNo = jsonobjectMember.getString("aaddharEnrolNo");
                String     ifDobAssumed = jsonobjectMember.getString("ifDobAssumed");
                String    handicap = jsonobjectMember.getString("handicap");
                String    sex = jsonobjectMember.getString("sex");
                String     mobile = jsonobjectMember.getString("mobile");
                String    timeStamp = jsonobjectMember.getString("timeStamp");
                String    familyId = jsonobjectMember.getString("familyId");
                String     motherId = jsonobjectMember.getString("motherId");
                String      bhamasha = jsonobjectMember.getString("bhamasha");
                String      recordStatus = jsonobjectMember.getString("recordStatus");
                String     stage = jsonobjectMember.getString("stage");
                String      dob = jsonobjectMember.getString("dob");
                String      name = jsonobjectMember.getString("name");
                String     subStage = jsonobjectMember.getString("subStage");
                String    age = jsonobjectMember.getString("age");
                String     status = jsonobjectMember.getString("status");


                String insertmemberBasic="INSERT INTO memberbasic (MEMBERS_ID,FAMILY_ID,NAME,DOR,DOENTRY,DOEXIT,DOB,AGE,IF_DOB_ASSUMED,DODEATH," +
                        "AADDHAR,AADDHAR_ENROL_NO,AADDHAR_DATE_STAMP,AADDHAR_TIME_STAMP,BHAMASHA, MOBILE,RELATION,SEX,HANDICAP," +
                        "IF_MARRIED,MOTHER_ID,STATUS,STAGE,SUB_STAGE,IS_TO_TRACK,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) VALUES (" +
                        "'" +membersId +"','"+ familyId + "','"+ name +"','"+ dor +"','"+ doentry +"','"+ doexit +"','"
                        + dob +"','"+ age +"','"+ ifDobAssumed +"','"+ dodeath +"','"+ aaddhar +"','"
                        + aaddharEnrolNo +"','"+ aaddharDateStamp+ "','"+ aaddharTimeStamp +"','"+ bhamasha +"','"
                        + mobile + "','"+ relation +"','"+ sex +"','"+ handicap +"','"+ ifMarried +"','"+ motherId +"','"
                        + status +"','"+ stage +"','"+ subStage +"','"+ isToTrack +"','"+ surveyorId +"','"+ timeStamp +"','"
                        + source +"','"+ isApproved +"','')";

                ExeuteQuery(insertmemberBasic);



            }

            JSONArray jsonarraypwTracking = jsonObject.getJSONArray("pwTracking");

            for(int pw=0;pw<jsonarraypwTracking.length();pw++){

                JSONObject jsonObjectTracking = jsonarraypwTracking.getJSONObject(pw);
                String ancDate= jsonObjectTracking.getString("ancDate");
                String isAvailable= jsonObjectTracking.getString("isAvailable");
                String pregnancyId= jsonObjectTracking.getString("pregnancyId");
                String ifCounselOnBf= jsonObjectTracking.getString("ifCounselOnBf");
                String weight= jsonObjectTracking.getString("weight");
                String source= jsonObjectTracking.getString("source");
                String timeStamp= jsonObjectTracking.getString("timeStamp");
                String isAnc= jsonObjectTracking.getString("isAnc");
                String recordStatus= jsonObjectTracking.getString("recordStatus");
                String stage= jsonObjectTracking.getString("stage");
                String membersId= jsonObjectTracking.getString("membersId");
                String spendOnFood= jsonObjectTracking.getString("spendOnFood");
                String surveyorId= jsonObjectTracking.getString("surveyorId");
                String ifCounselOnSelffeed= jsonObjectTracking.getString("ifCounselOnSelffeed");
                String subStage= jsonObjectTracking.getString("subStage");
                String isApproved= jsonObjectTracking.getString("isApproved");
                String height= jsonObjectTracking.getString("height");
                String naReason= jsonObjectTracking.getString("naReason");



                String pw_tracking="INSERT INTO PW_TRACKING (PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,NA_REASON," +
                        "IS_ANC,ANC_DATE,IF_COUNSEL_ON_SELFFEED,IF_COUNSEL_ON_BF,SPEND_ON_FOOD,HEIGHT,WEIGHT, SURVEYOR_ID,TIME_STAMP,SOURCE," +
                        "IS_APPROVED,IS_NEW) VALUES ('"+ pregnancyId +"','"+ membersId +"','"+ stage +"','"+ subStage +"','"+
                        isAvailable +
                        "','"+ naReason +"','"+ isAnc +"','"+ ancDate +"','"+ ifCounselOnSelffeed +"','"+ ifCounselOnBf +"',"+
                        spendOnFood +","+ height+ ","+ weight +"," +surveyorId +",'"+ timeStamp +"','"+ source +"','"+ isApproved +"','')";
                ExeuteQuery(pw_tracking);
            }


            JSONArray jsonArraydiet = jsonObject.getJSONArray("diet");

            for(int diet= 0;diet<jsonArraydiet.length();diet++){

                JSONObject jsonObjectDiet = jsonArraydiet.getJSONObject(diet);
                String feedGnos = jsonObjectDiet.getString("feedGnos");
                String feedEnos  = jsonObjectDiet.getString("feedEnos");
                String feedCnos  = jsonObjectDiet.getString("feedCnos");
                String feedAnos = jsonObjectDiet.getString("feedAnos");
                String feedInos  = jsonObjectDiet.getString("feedInos");
                String feedKnos  = jsonObjectDiet.getString("feedKnos");
                String feedMnos  = jsonObjectDiet.getString("feedMnos");
                String membersId  = jsonObjectDiet.getString("membersId");
                String isApproved  = jsonObjectDiet.getString("isApproved");
                String feedJ  = jsonObjectDiet.getString("feedJ");
                String feedK  = jsonObjectDiet.getString("feedK");
                String feedL  = jsonObjectDiet.getString("feedL");
                String feedHnos  = jsonObjectDiet.getString("feedHnos");
                String feedM  = jsonObjectDiet.getString("feedM");
                String pregnancyId  = jsonObjectDiet.getString("pregnancyId");
                String feedFnos  = jsonObjectDiet.getString("feedFnos");
                String feedDnos  = jsonObjectDiet.getString("feedDnos");
                String feedF  = jsonObjectDiet.getString("feedF");
                String feedG  = jsonObjectDiet.getString("feedG");
                String feedH  = jsonObjectDiet.getString("feedH");
                String feedI  = jsonObjectDiet.getString("feedI");
                String feedLnos  = jsonObjectDiet.getString("feedLnos");
                String feedB  = jsonObjectDiet.getString("feedB");
                String feedC  = jsonObjectDiet.getString("feedC");
                String feedBnos = jsonObjectDiet.getString("feedBnos");
                String feedD  = jsonObjectDiet.getString("feedD");
                String feedE  = jsonObjectDiet.getString("feedE");
                String feedJnos= jsonObjectDiet.getString("feedJnos");
                String feedA  = jsonObjectDiet.getString("feedA");
                String recordStatus  = jsonObjectDiet.getString("recordStatus");
                String stage  = jsonObjectDiet.getString("stage");
                String subStage  = jsonObjectDiet.getString("subStage");

                String dietQuery="INSERT INTO DIET (PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS," +
                        "FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E, FEED_E_NOS, FEED_F,FEED_F_NOS,FEED_G, FEED_G_NOS, FEED_H," +
                        "FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS," +
                        "IS_APPROVED,IS_NEW) VALUES ('"+ pregnancyId +"','"+ membersId +"','"+ stage +"','"+ subStage +"','"+
                        feedA +"','" + feedAnos +"','"+ feedB +"','"+ feedBnos +"','"+ feedC +"','"+ feedCnos +"','"+ feedD +
                        "','"+ feedDnos +"','"+ feedE +"','"+feedEnos+"','"+ feedF +"','"+ feedFnos +"','"+ feedG +"','"+
                        feedGnos +"','"+ feedH +"','"+ feedHnos +"','"+ feedI +"','"+ feedInos+"','"+ feedJ+ "','"+
                        feedJnos +"','"+ feedK + "','"+ feedKnos+"','"+ feedL +"','"+ feedLnos +"','"+ feedM +"','"+
                        feedMnos +"','"+ isApproved +"','')";

                ExeuteQuery(dietQuery);
            }

            JSONArray jsonArrayfamilyDetails=jsonObject.getJSONArray("familyDetails");
            for(int fdetails=0;fdetails<jsonArrayfamilyDetails.length();fdetails++){

                JSONObject jsonObjectFDetails= jsonArrayfamilyDetails.getJSONObject(fdetails);

                String familyType = jsonObjectFDetails.getString("familyType");
                String sectorCode=jsonObjectFDetails.getString("sectorCode");
                String religion=jsonObjectFDetails.getString("religion");
                String villageCode=jsonObjectFDetails.getString("villageCode");
                String distCode=jsonObjectFDetails.getString("distCode");
                String familyId=jsonObjectFDetails.getString("familyId");
                String cast=jsonObjectFDetails.getString("cast");
                String recordStatus=jsonObjectFDetails.getString("recordStatus");
                String projectCode=jsonObjectFDetails.getString("projectCode");
                String rcard=jsonObjectFDetails.getString("rcard");
                String surveyorId=jsonObjectFDetails.getString("surveyorId");
                String awcCode=jsonObjectFDetails.getString("awcCode");
                String isApproved=jsonObjectFDetails.getString("isApproved");

                String familyQuery="INSERT INTO familydata (FAMILY_ID,DIST_CODE,PROJECT_CODE,SECTOR_CODE,AWC_CODE,VILLAGE_CODE,RELIGION," +
                        "CASTE,RCARD,FAMILY_TYPE,SURVEYOR_ID,IS_APPROVED,IS_NEW) VALUES ('"+ familyId +"','"+ distCode +"','"+ projectCode +
                        "','"+ sectorCode +"','"+ awcCode +"','"+ villageCode +"',"+ religion +","+ cast +","+ rcard +","+ familyType +"," +
                        ""+surveyorId+",'"+isApproved+"','')";
                ExeuteQuery(familyQuery);

            }

            JSONArray jsonArraywomenExtra=jsonObject.getJSONArray("womenExtra");
            for(int wExtrs =0 ; wExtrs<jsonArraywomenExtra.length();wExtrs++){

                JSONObject jsonWExtra= jsonArraywomenExtra.getJSONObject(wExtrs);
                String postOfficeAc = jsonWExtra.getString("postOfficeAc");
                String education= jsonWExtra.getString("education");
                String decisionmakerOwnHealth= jsonWExtra.getString("decisionmakerOwnHealth");
                String bankName= jsonWExtra.getString("bankName");
                String postofficeName= jsonWExtra.getString("postofficeName");
                String hoemoCode= jsonWExtra.getString("hoemoCode");
                String bankDistance= jsonWExtra.getString("bankDistance");
                String postofficeAddress= jsonWExtra.getString("postofficeAddress");
                String branch= jsonWExtra.getString("branch");
                String acNo= jsonWExtra.getString("acNo");
                String decisionmakerChildHealth= jsonWExtra.getString("decisionmakerChildHealth");
                String ifBankAccont= jsonWExtra.getString("ifBankAccont");
                String recordStatus= jsonWExtra.getString("recordStatus");
                String cookingFuel= jsonWExtra.getString("cookingFuel");
                String membersId= jsonWExtra.getString("membersId");
                String pinCode= jsonWExtra.getString("pinCode");
                String isApproved= jsonWExtra.getString("isApproved");
                String ifscCode= jsonWExtra.getString("ifscCode");
                String acHolderName= jsonWExtra.getString("acHolderName");


                String womenExtraQuery="INSERT INTO WOMENEXTRA (MEMBERS_ID,EDUCATION,COOKING_FUEL,DECISIONMAKER_OWN_HEALTH,DECISIONMAKER_CHILD_HEALTH,if_bank_account,AC_HOLDER_NAME,BANK_NAME,BRANCH,AC_NO, IFSC_CODE,BANK_DISTANCE,POSTOFFICE_NAME,POSTOFFICE_ADDRESS,PIN_CODE,POST_OFFICE_AC,HOEMO_CODE,IS_APPROVED,IS_NEW) VALUES (" +
                        "'"+ membersId +"','"+ education +"','"+ cookingFuel +"','"+ decisionmakerOwnHealth +"','"+ decisionmakerChildHealth +"'," +
                        "'"+ ifBankAccont +"','"+ acHolderName +"','"+ bankName +"','"+ branch +"','"+ acNo +"','"+ ifscCode +"'," +
                        "'"+bankDistance+"','"+ postofficeName +"','"+ postofficeAddress +"','"+ pinCode +"','"+ postOfficeAc +"'," +
                        "'"+ hoemoCode +"','"+ isApproved +"','')";
                ExeuteQuery(womenExtraQuery);

            }
            JSONArray jsonArraychildTracking=jsonObject.getJSONArray("childTracking");
            for(int cTrak=0;cTrak<jsonArraychildTracking.length();cTrak++){

                JSONObject jsonObjectChildTRacking = jsonArraychildTracking.getJSONObject(cTrak);
                String liquidOtherThanBf= jsonObjectChildTRacking.getString("liquidOtherThanBf");
                String isAvailable = jsonObjectChildTRacking.getString("isAvailable");
                String naReasonBoth = jsonObjectChildTRacking.getString("naReasonBoth");
                String ifCounselOnFeedInfant= jsonObjectChildTRacking.getString("ifCounselOnFeedInfant");
                String childHeight = jsonObjectChildTRacking.getString("childHeight");
                String childWeight = jsonObjectChildTRacking.getString("childWeight");
                String ifStartedSolidFood =jsonObjectChildTRacking.getString("ifStartedSolidFood");
                String childImmunizationStatus = jsonObjectChildTRacking.getString("childImmunizationStatus");
                String source = jsonObjectChildTRacking.getString("source");
                String currentlyBf = jsonObjectChildTRacking.getString("currentlyBf");
                String ifUsingContraceptive = jsonObjectChildTRacking.getString("ifUsingContraceptive");
                String timeStamp= jsonObjectChildTRacking.getString("timeStamp");
                String recordStatus= jsonObjectChildTRacking.getString("recordStatus");
                String naReasonChild = jsonObjectChildTRacking.getString("naReasonChild");
                String stage = jsonObjectChildTRacking.getString("stage");
                String membersId = jsonObjectChildTRacking.getString("membersId");
                String childMuac= jsonObjectChildTRacking.getString("childMuac");
                String naReasonMother= jsonObjectChildTRacking.getString("naReasonMother");
                String methodContraceptive= jsonObjectChildTRacking.getString("methodContraceptive");
                String spendOnFood = jsonObjectChildTRacking.getString("spendOnFood");
                String surveyorId = jsonObjectChildTRacking.getString("surveyorId");
                String ifCounselOnSelffeed = jsonObjectChildTRacking.getString("ifCounselOnSelffeed");
                String subStage= jsonObjectChildTRacking.getString("subStage");
                String isApproved= jsonObjectChildTRacking.getString("isApproved");

                String childTracking="INSERT INTO CHILD_TRACKING (MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,NA_REASON_MOTHER,NA_REASON_CHILD,NA_REASON_BOTH,CURRENTLY_BF,IF_USING_CONTRACEPTIVE,METHOD_CONTRACEPTIVE,IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD, CHILD_IMMUNIZATION_STATUS,CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) VALUES (" +
                        "'"+ membersId +"','"+ stage + "','"+ subStage +"','"+ isAvailable +"','"+ naReasonMother + "', " +
                        "'"+ naReasonChild +"','"+ naReasonBoth +"','"+ currentlyBf +"','"+ ifUsingContraceptive + "','"+ methodContraceptive +"','"+ ifCounselOnFeedInfant +"','"+ ifCounselOnSelffeed +"'," +
                        "'"+ liquidOtherThanBf +"','"+ ifStartedSolidFood +"','"+ spendOnFood +"','"+ childImmunizationStatus +"'," +
                        "'"+ childHeight +"','"+ childWeight +"','"+ childMuac +"','" + surveyorId +"','"+ timeStamp +"','"+ source +"'," +
                        "'"+ isApproved +"','')";

                ExeuteQuery(childTracking);
            }

            JSONArray jsonArraychildExtra =jsonObject.getJSONArray("childExtra");

            for(int cExtra=0;cExtra<jsonArraychildExtra.length();cExtra++){

                JSONObject jsonObjeCExtra = jsonArraychildExtra.getJSONObject(cExtra);
                String whichMonthSolidFood=jsonObjeCExtra.getString("whichMonthSolidFood");
                String childImmunizationStatus=jsonObjeCExtra.getString("childImmunizationStatus");
                String currentlyBf=jsonObjeCExtra.getString("currentlyBf");
                String whenStopBf=jsonObjeCExtra.getString("whenStopBf");
                String fullTerm =jsonObjeCExtra.getString("fullTerm");
                String dodelivery =jsonObjeCExtra.getString("dodelivery");
                String deliveryPlace =jsonObjeCExtra.getString("deliveryPlace");
                String ifStartedSolidFood =jsonObjeCExtra.getString("ifStartedSolidFood");
                String birthWt =jsonObjeCExtra.getString("birthWt");
                String childOrder =jsonObjeCExtra.getString("childOrder");
                String fFeedKhees =jsonObjeCExtra.getString("ifFeedKhees");
                String recordStatus =jsonObjeCExtra.getString("recordStatus");
                String membersId =jsonObjeCExtra.getString("membersId");
                String anythingBeforeBf=jsonObjeCExtra.getString("anythingBeforeBf");
                String whenFirstBf=jsonObjeCExtra.getString("whenFirstBf");
                String isApproved=jsonObjeCExtra.getString("isApproved");

                String childExtraQuery="INSERT INTO CHILDEXTRA (MEMBERS_ID,DODELIVERY,DELIVERY_PLACE,CHILD_ORDER,BIRTH_WT,FULL_TERM,WHEN_FIRST_BF,IF_FEED_KHEES,CURRENTLY_BF,WHEN_STOP_BF,ANYTHING_BEFORE_BF,IF_STARTED_SOLID_FOOD, WHICH_MONTH_SOLID_FOOD,CHILD_IMMUNIZATION_STATUS,IS_APPROVED,IS_NEW) VALUES (" +
                        "'"+ membersId +"','"+ dodelivery + "','"+ deliveryPlace + "','"+ childOrder +"','"+ birthWt +"'," +
                        "'"+ fullTerm +"','"+ whenFirstBf +"'," +
                        "'"+ fFeedKhees +"','"+ currentlyBf +"','"+ whenStopBf +"','"+ anythingBeforeBf +"'," +
                        "'"+ ifStartedSolidFood + "','"+ whichMonthSolidFood + "','"+ childImmunizationStatus +"','"+ isApproved +"','')";
                ExeuteQuery(childExtraQuery);

            }
            JSONArray jsonArraypregnant=jsonObject.getJSONArray("pregnant");
            for(int preg=0;preg<jsonArraypregnant.length();preg++){

                JSONObject jsonObPregbnent = jsonArraypregnant.getJSONObject(preg);

                String timeStamp= jsonObPregbnent.getString("timeStamp");
                String recordStatus= jsonObPregbnent.getString("recordStatus");
                String pregnancyId= jsonObPregbnent.getString("pregnancyId");
                String membersId= jsonObPregbnent.getString("membersId");
                String lmpDate= jsonObPregbnent.getString("lmpDate");
                String surveyorId= jsonObPregbnent.getString("surveyorId");
                String source= jsonObPregbnent.getString("source");
                String isApproved= jsonObPregbnent.getString("isApproved");
                String isActive= jsonObPregbnent.getString("isActive");
                String orderOfPregnancy= jsonObPregbnent.getString("orderOfPregnancy");

                String QueryPregnent="INSERT INTO PREGNANT (PREGNANCY_ID,MEMBERS_ID,ORDER_OF_PREGNANCY,LMP_DATE,IS_ACTIVE,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) VALUES (" +
                        "'"+ pregnancyId +"','"+ membersId +"','"+ orderOfPregnancy +"','"+ lmpDate +"','"+ isActive +"'," +
                        "'"+ surveyorId+"','" + timeStamp +"','"+ source +"','"+ isApproved +"','')";

                ExeuteQuery(QueryPregnent);
            }



//            fragmentt = new HomeFragment();
//            if (fragmentt != null) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.mainframe, fragmentt);
//                fragmentTransaction.commit();
//            }

        }catch (Exception e){
            Log.d("RanjeetPullData",e.toString());
        }

        return completed;

    }
}
