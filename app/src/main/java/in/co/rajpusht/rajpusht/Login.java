package in.co.rajpusht.rajpusht;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import extras.BaseUrl;
import extras.DbHelper;
import extras.SessionManager;
import in.co.rajpusht.rajpusht.Helper.ColorHelper;
import in.co.rajpusht.rajpusht.activity.CashLoginActivity;
import in.co.rajpusht.rajpusht.activity.CashProfileActivity;
import in.co.rajpusht.rajpusht.tool.Constants;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

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
    String userId;
    String Name;
    TextView forgotpassword;
    Dialog dialog;
    EditText editEmailForget;
    final Context context = this;
    Button login;

    ImageView cashLoginLauncher;
    private RadioGroup radioLoginChooser;
    private RadioButton radioLogin, radioBene, radioCash;

    int maxLogSize = 10000;
    String value2;
    boolean isSuccessful = false;

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
        //username.setText("nwadhwa@ipeglobal.com");
        //password.setText("Admin@123");


        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.forget_password);
//            dialog.setCancelable(false);
//            dialog.setCanceledOnTouchOutside(false);


                // set the custom dialog components - text, image and button

                editEmailForget= (EditText) dialog.findViewById(R.id.forgetEmail);



                Button dialogButton = (Button) dialog
                        .findViewById(R.id.forgetButton);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(editEmailForget.getText().length()==0){
                            Toast.makeText(context, "Field can't be blank", Toast.LENGTH_SHORT).show();
                        }


                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        //Radio Group
        radioLoginChooser = (RadioGroup)findViewById(R.id.radioLoginChooser);
        radioBene = (RadioButton)findViewById(R.id.radioBene);
        radioCash = (RadioButton)findViewById(R.id.radioCash);

        radioBene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                radioBene.setBackgroundResource(R.drawable.box);
                radioCash.setBackgroundResource(R.drawable.box_white);
            }
        });

        radioCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username.setText("nwadhwa@ipeglobal.com");
                password.setText("Admin@123");
                radioCash.setBackgroundResource(R.drawable.box);
                radioBene.setBackgroundResource(R.drawable.box_white);
            }
        });

       // Toast.makeText(Login.this, radioBene.getText().toString(), Toast.LENGTH_SHORT).show();






    login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ConenctionCheck",""+checkConnection());


                int selectedId = radioLoginChooser.getCheckedRadioButtonId();
                radioLogin = (RadioButton)findViewById(selectedId);
                String loginChooserText = radioLogin.getText().toString();

                //Toast.makeText(Login.this, radioLogin.getText().toString(), Toast.LENGTH_SHORT).show();
                if(loginChooserText.equalsIgnoreCase("Beneficiary Login"))
                {

                    //Default Login
                    if(username.getText().toString().length()==0 || password.getText().toString().length()==0) {
                        Toast.makeText(Login.this, "Enter  Username and  Password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        login.setEnabled(false);
                        NetConnectivity netConnect = new NetConnectivity();
                        netConnect.execute();
                        //
                    }

                }
                else
                {
                    //  username.setText("nwadhwa@ipeglobal.com");
                     // password.setText("Admin@123");
                    new CashLogin().execute(username.getText().toString(), password.getText().toString());





                }




            }
        });


        cashLoginLauncher = (ImageView)findViewById(R.id.cash_login_launcher);
        cashLoginLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, CashLoginActivity.class));
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

                Log.d("URL", new BaseUrl().base_url+"restservice/surveyor/user/"+name+"/"+password);

//                httpPost.setHeader("Accept-Encoding", "application/json");
//                httpPost.setHeader("Accept-Language", "en-US");
                response = httpClient.execute(httpPost);

//                String the_string_response = convertResponseToString(response);
                String sresponse = response.getEntity().toString();

                Log.w("QueingSystem", sresponse);
//                Log.w("QueingSystem", EntityUtils.toString(response.getEntity()));
                value= EntityUtils.toString(response.getEntity());



         /*****       Log.w("LoginDetails",value);   ****/


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

                Log.v("userId0001", jsonArrayAssignedLocation.toString());///
                Log.v("userId001", jsonArraySurveyorDetails.toString());////

                for(int survid= 0;survid<jsonArraySurveyorDetails.length();survid++){

                    JSONObject jsonObjectSurvey = jsonArraySurveyorDetails.getJSONObject(survid);

                    Name=jsonObjectSurvey.getString("name");
//                     String password=jsonObjectSurvey.getString("");
                    String  email=jsonObjectSurvey.getString("email");
                    String phone=jsonObjectSurvey.getString("mobile");
//                            String loged=jsonObjectSurvey.getString("Yes");
                    userId=jsonObjectSurvey.getString("surveyorId");
//                    session.setLogin();
//                    session.setSurveyorName(Name);


//session.setUserID(userId);
                    String insertSurvoyourId="INSERT INTO SURVEYOR_LOGIN (USER_ID,PASSWORD,LOGED,EMAIL,MOBILE,NAME) VALUES ("+userId+",'"+ password.getText().toString() +"','Y','"+email+"','"+phone+"','"+Name+"')";
                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
                    Cursor c = dbs.rawQuery(insertSurvoyourId,null);
                    c.moveToFirst();
                    c.close();
                    dbs.close();////
                }


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
                    String projectName=jsonSubObject.getString("projectName");
                    String sectorName = jsonSubObject.getString("sectorName");


                    String insertLocation="INSERT INTO ASSIGNED_LOCATION (DIST_CODE,PROJECT_CODE,SECTOR_CODE,VILLAGE_CODE," +
                            "VILLAGE_ENG,VILLAGE_HINDI,AWC_CODE,SURVEYOR_NAME,SURVEYOR_ID,AWC_ENG,AWC_HINDI,sector_name,project_name) VALUES ('"
                            + distcode +"','"+projectcode +"','"+ sectorcode + "','"+ villageCode +"','"+ villageName +
                            "','"+ villageNameHindi + "','"+ awccode +"','"+Name+"',"+surveyorId +",'"+awcName+"','"+awcNameHindi+"','"+sectorName+"','"+projectName+"')";

                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                    Cursor c = dbs.rawQuery(insertLocation,null);
                    c.moveToFirst();
                    c.close();
                    dbs.close();////

                }

             PullDataWebservice pulldata = new PullDataWebservice();
                pulldata.execute(userId);
                asyncDialog.dismiss();
                login.setEnabled(true);
            } catch (Exception e) {
                Log.d("Ranjeet", "ranjeet Error" + e.toString());
                Toast.makeText(Login.this, "Enter Valid Username and Password", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
                asyncDialog.dismiss();
                login.setEnabled(true);
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
            Cursor c1 = dbs.rawQuery(query, null);
            c1.moveToFirst();
            c1.close();
            dbs.close();

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
                Log.v("userId01", value.toString());


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
            asyncDialog.setCancelable(false);
            asyncDialog.setCanceledOnTouchOutside(false);
            asyncDialog.show();


        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            Log.d("LoginDetails",aVoid);

                NewAsyncTask newAsyncTask = new NewAsyncTask();///
                newAsyncTask.execute(aVoid);///
            asyncDialog.dismiss();
//

       /*     try {


                String pullStatus = pullDataMethod(aVoid);
                if (pullStatus.equalsIgnoreCase("1")) {

                    session.setLogin();
                    session.setSurveyorName(Name);
                    session.setUserID(userId);

                    String loginStatus = locationcheck();


                    if (loginStatus.equalsIgnoreCase("true")) {

                        Intent intentprofile = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intentprofile);
                        finish();


                    } else {

                        Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                        startActivity(intentProfile);
                        finish();
                    }

                }

                else{

                    Toast.makeText(Login.this, "Try Again Data Not Synchronise", Toast.LENGTH_SHORT).show();
                }
                }catch(Exception e){

                    Log.d("syncActivity", e.toString());
                    asyncDialog.dismiss();

                }

            asyncDialog.dismiss();  */
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }


    }

    class NewAsyncTask extends AsyncTask<String, String, String> {
        ProgressDialog asyncDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            asyncDialog = new ProgressDialog(Login.this);
            asyncDialog.setMessage("Please wait data sync is going on..");
            asyncDialog.setCancelable(false);
            asyncDialog.setCanceledOnTouchOutside(false);
            asyncDialog.show();


        }


        @Override
        protected String doInBackground(String... params) {
            String aVoid = params[0];
            String pullStatus = pullDataMethod(aVoid);

            return pullStatus;
        }

        @Override
        protected void onPostExecute(String pullStatus) {
            super.onPostExecute(pullStatus);
//            Log.d("LoginDetails",aVoid);

            try {


                if (pullStatus.equalsIgnoreCase("1")) {

                    session.setLogin();
                    session.setSurveyorName(Name);
                    session.setUserID(userId);

                    String loginStatus = locationcheck();

                    SharedPreferences arrayFamilyId = getSharedPreferences("Prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = arrayFamilyId.edit();
                    Set<String> set = new HashSet<String>();
                    set.addAll(ColorHelper.getList());
                    editor.putStringSet("form_filled", set);
                    editor.apply();


                    if (loginStatus.equalsIgnoreCase("true")) {


                        Intent intentprofile = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intentprofile);
                        finish();


                    } else {
                        /********/
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editorLogin = preferences.edit();
                        editorLogin.putString("username_cash", username.getText().toString());
                        editorLogin.putString("password_cash", password.getText().toString());
                        editorLogin.apply();
                        /********/

                        Intent intentProfile = new Intent(getApplicationContext(), Profile.class);
                        startActivity(intentProfile);
                        finish();




                    }

                } else {

                    Toast.makeText(Login.this, "Try Again Data Not Synchronise", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {

                Log.d("syncActivity", e.toString());
                asyncDialog.dismiss();

            }

            asyncDialog.dismiss();

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

           // Toast.makeText(getApplicationContext(), "userID",Toast.LENGTH_SHORT).show();
            //Log.d("userId1", jsonArrayMember.toString());

            //Printing Log for too long string
            for(int i = 0; i <= jsonArrayMember.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArrayMember.toString().length() ? jsonArrayMember.toString().length() : end;
                Log.v("userId1", jsonArrayMember.toString().substring(start, end));
            }

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
//                String isApproved= "Y";
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

                //New Strings for husband and PCTS ID:
                String husband = jsonobjectMember.getString("husband");
                String pctsid = jsonobjectMember.getString("pctsid");
                if(isApproved.equalsIgnoreCase("Y"))
                {
                    ColorHelper.getList().add(familyId);
                    Log.d("isApprovedddd", ColorHelper.getList().toString());
                }
               // String isApprove = jsonobjectMember.getString("isApproved");
               // Log.d("isApprovedddd", name);
               // Log.d("isApprovedddd", isApproved);

                String insertmemberBasic="INSERT INTO memberbasic (MEMBERS_ID,FAMILY_ID,NAME,husband,pctsid,DOR,DOENTRY,DOEXIT,DOB,AGE,IF_DOB_ASSUMED,DODEATH," +
                        "AADDHAR,AADDHAR_ENROL_NO,AADDHAR_DATE_STAMP,AADDHAR_TIME_STAMP,BHAMASHA, MOBILE,RELATION,SEX,HANDICAP," +
                        "IF_MARRIED,MOTHER_ID,STATUS,STAGE,SUB_STAGE,IS_TO_TRACK,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) VALUES (" +
                        "'" +membersId +"','"+ familyId + "','"+ name +"','"+ husband +"','"+ pctsid +"','"+ dor +"','"+ doentry +"','"+ doexit +"','"
                        + dob +"','"+ age +"','"+ ifDobAssumed +"','"+ dodeath +"','"+ aaddhar +"','"
                        + aaddharEnrolNo +"','"+ aaddharDateStamp+ "','"+ aaddharTimeStamp +"','"+ bhamasha +"','"
                        + mobile + "','"+ relation +"','"+ sex +"','"+ handicap +"','"+ ifMarried +"','"+ motherId +"','"
                        + status +"','"+ stage +"','"+ subStage +"','"+ isToTrack +"','"+ surveyorId +"','"+ timeStamp +"','"
                        + source +"','"+ isApproved +"','')";

      /******          Log.d("InsertMmeberABasis",insertmemberBasic); ******/
                ExeuteQuery(insertmemberBasic);
            }
            JSONArray jsonarraypwTracking = jsonObject.getJSONArray("pwTracking");
            //Log.v("userId2", jsonarraypwTracking.toString());
            //Log for too long string
            for(int i = 0; i <= jsonarraypwTracking.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonarraypwTracking.toString().length() ? jsonarraypwTracking.toString().length() : end;
                Log.v("userId2", jsonarraypwTracking.toString().substring(start, end)); }


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
//                String isApproved= "Y";
                String height= jsonObjectTracking.getString("height");
                String naReason= jsonObjectTracking.getString("naReason");

                String pw_tracking="INSERT INTO PW_TRACKING (PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,NA_REASON," +
                        "IS_ANC,ANC_DATE,IF_COUNSEL_ON_SELFFEED,IF_COUNSEL_ON_BF,SPEND_ON_FOOD,HEIGHT,WEIGHT, SURVEYOR_ID,TIME_STAMP,SOURCE," +
                        "IS_APPROVED,IS_NEW) VALUES ('"+ pregnancyId +"','"+ membersId +"','"+ stage +"','"+ subStage +"','"+
                        isAvailable +
                        "','"+ naReason +"','"+ isAnc +"','"+ ancDate +"','"+ ifCounselOnSelffeed +"','"+ ifCounselOnBf +"',"+
                        spendOnFood +","+ height+ ","+ weight +"," +surveyorId +",'"+ timeStamp +"','"+ source +"','"+ isApproved +"','')";
                Log.d("InsertMmeberABasis",pw_tracking);
                ExeuteQuery(pw_tracking);
            }

            JSONArray jsonArraydiet = jsonObject.getJSONArray("diet");
            //Log.v("userId3", jsonArraydiet.toString());
            for(int i = 0; i <= jsonArraydiet.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArraydiet.toString().length() ? jsonArraydiet.toString().length() : end;
                Log.v("userId3", jsonArraydiet.toString().substring(start, end)); }

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
               Log.d("InsertMmeberABasis",dietQuery);
                ExeuteQuery(dietQuery);
            }

            JSONArray jsonArrayfamilyDetails=jsonObject.getJSONArray("familyDetails");
           // Log.v("userId4", jsonArrayfamilyDetails.toString());
            //Log.v("userId3", jsonArraydiet.toString());
            for(int i = 0; i <= jsonArrayfamilyDetails.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArrayfamilyDetails.toString().length() ? jsonArrayfamilyDetails.toString().length() : end;
                Log.v("userId4", jsonArrayfamilyDetails.toString().substring(start, end)); }



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
            //Log.v("userId5", jsonArraywomenExtra.toString());
            for(int i = 0; i <= jsonArraywomenExtra.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArraywomenExtra.toString().length() ? jsonArraywomenExtra.toString().length() : end;
                Log.v("userId5", jsonArraywomenExtra.toString().substring(start, end)); }


            for(int wExtrs =0 ; wExtrs<jsonArraywomenExtra.length();wExtrs++){

                JSONObject jsonWExtra= jsonArraywomenExtra.getJSONObject(wExtrs);
                String postOfficeAc = jsonWExtra.getString("postOfficeAc");
                String education= jsonWExtra.getString("education");
                String decisionmakerOwnHealth= jsonWExtra.getString("decisionmakerOwnHealth");
                String bankName= jsonWExtra.getString("bankName");
                String postofficeName= jsonWExtra.getString("postofficeName");
                String hoemoCode= jsonWExtra.getString("hoemoCode");
                String bankDistance= jsonWExtra.getString("bankDistance");
                String bank_atm_Distance= jsonWExtra.getString("atmDistance");
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


                String womenExtraQuery="INSERT INTO WOMENEXTRA (MEMBERS_ID,EDUCATION,COOKING_FUEL,DECISIONMAKER_OWN_HEALTH,DECISIONMAKER_CHILD_HEALTH,if_bank_account,AC_HOLDER_NAME,BANK_NAME,BRANCH,AC_NO, IFSC_CODE,BANK_DISTANCE,POSTOFFICE_NAME,POSTOFFICE_ADDRESS,PIN_CODE,POST_OFFICE_AC,HOEMO_CODE,IS_APPROVED,IS_NEW,bank_atm_distance) VALUES (" +
                        "'"+ membersId +"','"+ education +"','"+ cookingFuel +"','"+ decisionmakerOwnHealth +"','"+ decisionmakerChildHealth +"'," +
                        "'"+ ifBankAccont +"','"+ acHolderName +"','"+ bankName +"','"+ branch +"','"+ acNo +"','"+ ifscCode +"'," +
                        "'"+bankDistance+"','"+ postofficeName +"','"+ postofficeAddress +"','"+ pinCode +"','"+ postOfficeAc +"'," +
                        "'"+ hoemoCode +"','"+ isApproved +"','','"+bank_atm_Distance+"')";

              Log.d("InsertMmeberABasis",womenExtraQuery);
                ExeuteQuery(womenExtraQuery);

            }
            JSONArray jsonArraychildTracking=jsonObject.getJSONArray("childTracking");
            //Log.v("userId6", jsonArraychildTracking.toString());
            for(int i = 0; i <= jsonArraychildTracking.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArraychildTracking.toString().length() ? jsonArraychildTracking.toString().length() : end;
                Log.v("userId6", jsonArraychildTracking.toString().substring(start, end)); }

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
//                String isApproved= "Y";

                String childTracking="INSERT INTO CHILD_TRACKING (MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,NA_REASON_MOTHER,NA_REASON_CHILD,NA_REASON_BOTH,CURRENTLY_BF,IF_USING_CONTRACEPTIVE,METHOD_CONTRACEPTIVE,IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD, CHILD_IMMUNIZATION_STATUS,CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) VALUES (" +
                        "'"+ membersId +"','"+ stage + "','"+ subStage +"','"+ isAvailable +"','"+ naReasonMother + "', " +
                        "'"+ naReasonChild +"','"+ naReasonBoth +"','"+ currentlyBf +"','"+ ifUsingContraceptive + "','"+ methodContraceptive +"','"+ ifCounselOnFeedInfant +"','"+ ifCounselOnSelffeed +"'," +
                        "'"+ liquidOtherThanBf +"','"+ ifStartedSolidFood +"','"+ spendOnFood +"','"+ childImmunizationStatus +"'," +
                        "'"+ childHeight +"','"+ childWeight +"','"+ childMuac +"','" + surveyorId +"','"+ timeStamp +"','"+ source +"'," +
                        "'"+ isApproved +"','')";
          Log.d("InsertMmeberABasis",childTracking);
                ExeuteQuery(childTracking);
            }

            JSONArray jsonArraychildExtra =jsonObject.getJSONArray("childExtra");

           // Log.v("userId7", jsonArraychildExtra.toString());
            for(int i = 0; i <= jsonArraychildExtra.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArraychildExtra.toString().length() ? jsonArraychildExtra.toString().length() : end;
                Log.v("userId7", jsonArraychildExtra.toString().substring(start, end)); }


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

            //Log.v("userId8", jsonArraypregnant.toString());
            for(int i = 0; i <= jsonArraypregnant.toString().length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > jsonArraypregnant.toString().length() ? jsonArraypregnant.toString().length() : end;
                Log.v("userId8", jsonArraypregnant.toString().substring(start, end)); }


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

completed="1";

//            fragmentt = new HomeFragment();
//            if (fragmentt != null) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.mainframe, fragmentt);
//                fragmentTransaction.commit();
//            }

        }catch (Exception e){
            completed="0";
            Log.d("RanjeetPullData",e.toString());
        }

        return completed;

    }



    public static boolean isOnline() {
        boolean b = true;
        try{
            InetSocketAddress sa = new InetSocketAddress("103.203.138.163",80);
            Socket ss = new Socket();
            ss.connect(sa, 500);
            ss.close();
        }catch(Exception e) {
            b = false;
        }
        return b;
    }
public boolean  checkConnection() {
    boolean value = false;
    ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    if (netInfo != null && netInfo.isConnected()) {
        try {
            URL url = new URL("http://103.203.138.163");   // Change to "http://google.com" for www  test.
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(100 * 1000);          // 10 s.
            urlc.connect();
            if (urlc.getResponseCode() == 200) {        // 200 = "OK" code (http connection is fine).
                Log.wtf("Connection", "Success !");
                value = true;
                return value;
            } else {
                value = false;
                return value;

            }
        } catch (Exception e1) {
            value = false;
            return value;
        }


    }
    return value;
}


class NetConnectivity extends  AsyncTask<String,String,String>{

    @Override
    protected String doInBackground(String... strings) {

      boolean value =  checkConnection();


      String netStatus=String.valueOf(value);
      Log.d("ConenctionTest",""+value);
        return netStatus;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);



        if(s.equalsIgnoreCase("true")){

            LoginDetails login = new LoginDetails();
            login.execute(username.getText().toString(), password.getText().toString());
        }
        else{
            login.setEnabled(true);
            Toast.makeText(Login.this, "Please check Internet Connectivty", Toast.LENGTH_SHORT).show();
        }
    }


}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    /**** Cash Login ***/

    public  class CashLogin extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage("Please wait Validating data");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            String json = null;

            String name = params[0];
            String password = params[1];


           try {
            /*    HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                json = jsonObject.toString();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.BASE_URL + "restservice/surveyor/user/"+name+"/"+password);
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setEntity(new StringEntity(json, "UTF-8"));

                response = httpClient.execute(httpPost);
                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());  */


                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                json = jsonObject.toString();
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(CASH_BASE_URL+"login?type=aww");
                httppost.setEntity(new StringEntity(json, "UTF-8"));

                //add data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username",name ));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //execute http post

                response = httpclient.execute(httppost);

                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());
                Log.d("loginResponse", sresponse);
                Log.d("loginResponseValue", value);


              JSONObject jsonObj = new JSONObject(value);

              isSuccessful = jsonObj.getBoolean("success");
              //Log.d("loginresSuccess", String.valueOf(isSuccessFul));

              JSONObject data = jsonObj.getJSONObject("data");
              String token = data.getString("loginToken");
                Log.d("loginresponse", token);
               // Toast.makeText(Login.this,token, Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token",token);
                editor.apply();



                HttpResponse responseget;

                JSONObject jsonObjectget = new JSONObject();
//
                HttpClient httpClientget = new DefaultHttpClient();
                HttpGet httpPostget = new HttpGet(CASH_BASE_URL+"beneficiary/assignedLocationList");
                httpPostget.setHeader("Content-Type", "application/json");
              //  httpPost.setEntity(new StringEntity(stringName, "UTF-8"));
//  httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                httpPostget.setHeader("Accept", "application/json");
                httpPostget.setHeader("Accept-Language", "en-US");

                SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences2.getString("token", "Not Saved");
                Log.d("loginresponseMToken", mtoken);

                httpPostget.setHeader("Authorization", "Bearer "+ mtoken);


                responseget = httpClientget.execute(httpPostget);

//                String the_string_response = convertResponseToString(response);
                String sresponseget = responseget.getEntity().toString();
                Log.w("QueingSystem2", sresponseget);
//                Log.w("QueingSystem", EntityUtils.toString(response.getEntity()));
                value2 = EntityUtils.toString(responseget.getEntity());
                Log.d("loginresponseget", value2);




            }catch (Exception e) {

            }

            return value2;//Mine
           // return value;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            populateData(s);

            progressDialog.dismiss();
            //Toast.makeText(CashLoginActivity.this, s, Toast.LENGTH_SHORT).show();



        }



    }

    private void populateData(String json) {
        // Toast.makeText(this, json, Toast.LENGTH_SHORT).show();

      /*  Intent intent = new Intent(this, CashProfileActivity.class);
        intent.putExtra("json_awc_data", json);
        startActivity(intent);
        finish();  */

       if(isSuccessful==true) {
            Intent intent = new Intent(this, CashProfileActivity.class);
            intent.putExtra("json_awc_data", json);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(context, "Failed to fetch", Toast.LENGTH_SHORT).show();
        }



    }



    /*** Cash Login End ***/



}

