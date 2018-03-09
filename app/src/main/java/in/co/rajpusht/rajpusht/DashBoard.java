package in.co.rajpusht.rajpusht;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.ArrayList;

import extras.BaseUrl;
import extras.FamilyDetailGetSet;
import extras.SessionManager;

public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SearchView searchView;
    private Boolean exit = false;
    ImageView profile;
    String selectionstatus=null;
    RelativeLayout relativePregnent,realtiveYoungMother,relativeLactingMother;

    Fragment fragmentt = null;

    ArrayList<FamilyDetailGetSet> ArrayfamilyDetails = new ArrayList<FamilyDetailGetSet>();

TextView profilenametext;
SessionManager session;

CheckBox checkChild,checkPregnets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       String vid = getIntent().getStringExtra("vid");
        session= new SessionManager(DashBoard.this);

        checkChild = (CheckBox) findViewById(R.id.checkChild);
        checkPregnets= (CheckBox) findViewById(R.id.checkPregnet);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ImageView menu = (ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
//        pfname = (TextView)hView.findViewById(R.id.pfnm) ;
        profilenametext = (TextView) hView.findViewById(R.id.profilename);
        profilenametext.setText(session.getSurveyorName());

       TextView textLoction= (TextView) hView.findViewById(R.id.textLoction);
       textLoction.setText("Enumeration AWC :"+session.getAwcEng());

        profile = (ImageView)hView.findViewById(R.id.profile) ;
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        });

        relativePregnent = (RelativeLayout) findViewById(R.id.pregnentWomen);
        relativePregnent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intentPregnent = new Intent(getApplicationContext(),PregantWomenFooter.class);
                startActivity(intentPregnent);
            }
        });

        TextView register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkChild.isChecked()&&!checkPregnets.isChecked()){

                    Toast.makeText(DashBoard.this, "Please select appropriate option", Toast.LENGTH_SHORT).show();

                }
                if(checkChild.isChecked()){

                    selectionstatus="checkedChild";


                }
                if(checkPregnets.isChecked()){

                    selectionstatus="checkedPregnent";


                }
                if(checkPregnets.isChecked()&& checkChild.isChecked()){


                    selectionstatus="Pregnentandchild";



                }

                if(selectionstatus!=null) {
                    Intent intentYongMother = new Intent(getApplicationContext(), RegistrationWomen.class);
                    intentYongMother.putExtra("status", selectionstatus);
                    startActivity(intentYongMother);
                }


            }
        });

        realtiveYoungMother= (RelativeLayout) findViewById(R.id.realtiveYoungMother);
        realtiveYoungMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentYongMother = new Intent(getApplicationContext(),RegistrationWomen.class);
                startActivity(intentYongMother);

            }
        });

                relativeLactingMother= (RelativeLayout) findViewById(R.id.relativeLactingMother);
        relativeLactingMother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLactingMother = new Intent(getApplicationContext(),LactingMotherActivity.class);
                startActivity(intentLactingMother);

            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);

        TextView hometext=(TextView) findViewById(R.id.home);
        hometext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
            }
        });

        TextView beneficieryList=(TextView) findViewById(R.id.benificiarylist);
        beneficieryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
            }
        });

        TextView changepassword=(TextView) findViewById(R.id.changepassword);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
            }
        });

        TextView log = (TextView) findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
                session.logoutUser();

            }
        });

        TextView pullData = (TextView) findViewById(R.id.PullData);
        pullData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);



                PullDataWebservice pulldata = new PullDataWebservice();
                pulldata.execute(session.getSurveyorId());



//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
            }
        });

        final TextView pushData = (TextView) findViewById(R.id.pushData);
        pushData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                Log.d("insertedInto","pushdata clicked butotn");
//                JSONArray familyList = pushdata("SELECT FAMILY_ID as familyId, DIST_CODE as distCode, PROJECT_CODE as projectCode, SECTOR_CODE as sectorCode, AWC_CODE,VILLAGE_CODE,RELIGION,CASTE ,RCARD,FAMILY_TYPE,SURVEYOR_ID,IS_APPROVED, IS_NEW AS recordStatus FROM familydata WHERE recordStatus NOT NULL");
//
//
//                JSONArray memberbasicList =pushdata("SELECT MEMBERS_ID, FAMILY_ID, NAME,DOR, DOENTRY, DOEXIT, DOB, AGE, IF_DOB_ASSUMED, DODEATH,AADDHAR, AADDHAR_ENROL_NO, AADDHAR_DATE_STAMP, AADDHAR_TIME_STAMP,BHAMASHA,\n" +
//                        " MOBILE, RELATION, SEX, HANDICAP,IF_MARRIED, MOTHER_ID,STATUS, STAGE, SUB_STAGE, IS_TO_TRACK, SURVEYOR_ID,TIME_STAMP,SOURCE, IS_APPROVED, IS_NEW AS recordStatus FROM memberbasic WHERE recordStatus NOT NULL");
//                JSONArray pwTracking=pushdata("SELECT PREGNANCY_ID,MEMBERS_ID, STAGE,SUB_STAGE,IS_AVAILABLE, NA_REASON,IS_ANC,ANC_DATE,IF_COUNSEL_ON_SELFFEED,IF_COUNSEL_ON_BF,SPEND_ON_FOOD,HEIGHT, WEIGHT,SURVEYOR_ID,\n" +
//                        " TIME_STAMP, SOURCE,IS_NEW AS recordStatus,IS_APPROVED FROM PW_TRACKING WHERE recordStatus NOT NULL");
//                JSONArray women_extra=pushdata("SELECT MEMBERS_ID,EDUCATION,COOKING_FUEL, DECISIONMAKER_OWN_HEALTH, DECISIONMAKER_CHILD_HEALTH,if_bank_account,AC_HOLDER_NAME, BANK_NAME,BRANCH,AC_NO,IFSC_CODE,BANK_DISTANCE,POSTOFFICE_NAME,POSTOFFICE_ADDRESS,PIN_CODE,POST_OFFICE_AC, HOEMO_CODE,IS_APPROVED,IS_NEW AS recordStatus FROM womenextra WHERE recordStatus NOT NULL");
//                JSONArray child_extra=pushdata("SELECT MEMBERS_ID,DODELIVERY,DELIVERY_PLACE,CHILD_ORDER,BIRTH_WT,FULL_TERM,WHEN_FIRST_BF,IF_FEED_KHEES,CURRENTLY_BF,WHEN_STOP_BF,ANYTHING_BEFORE_BF, IF_STARTED_SOLID_FOOD,WHICH_MONTH_SOLID_FOOD,CHILD_IMMUNIZATION_STATUS,IS_APPROVED,IS_NEW AS recordStatus FROM childextra WHERE recordStatus NOT NULL");
//                JSONArray child_tracking = pushdata("SELECT MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,NA_REASON_MOTHER,NA_REASON_CHILD,NA_REASON_BOTH,CURRENTLY_BF,IF_USING_CONTRACEPTIVE,METHOD_CONTRACEPTIVE,IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_IMMUNIZATION_STATUS,CHILD_HEIGHT,CHILD_WEIGHT,\n" +
//                        "CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW AS recordStatus FROM child_tracking WHERE recordStatus NOT NULL ");
//                JSONArray diet=pushdata("\n" +
//                        "SELECT PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS, FEED_F,FEED_F_NOS,FEED_G, FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,IS_APPROVED,IS_NEW AS recordStatus FROM DIET WHERE recordStatus  NOT NULL");
//                JSONArray pregnent=pushdata("SELECT PREGNANCY_ID,MEMBERS_ID,ORDER_OF_PREGNANCY,LMP_DATE,IS_ACTIVE,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW AS recordStatus FROM PREGNANT WHERE recordStatus  NOT NULL");
//                Log.d("JSONCHECK",pregnent.toString());

                JSONArray familyList = pushdata("SELECT FAMILY_ID as familyId, DIST_CODE as distCode, PROJECT_CODE as projectCode, SECTOR_CODE as sectorCode, AWC_CODE as awcCode,VILLAGE_CODE as villageCode,RELIGION as religion,CASTE as cast ,RCARD as rcard,FAMILY_TYPE as familyType,SURVEYOR_ID as surveyorId,IS_APPROVED as isApproved, IS_NEW AS recordStatus FROM familydata WHERE recordStatus NOT NULL");

                JSONArray memberbasicList =pushdata("SELECT MEMBERS_ID as membersId, FAMILY_ID as familyId, NAME as name,DOR as dor, DOENTRY as doentry, DOEXIT as doexit, DOB as dob, AGE as age, IF_DOB_ASSUMED as ifDobAssumed, DODEATH as dodeath,AADDHAR as aaddhar, AADDHAR_ENROL_NO as aaddharEnrolNo, AADDHAR_DATE_STAMP as aaddharDateStamp, AADDHAR_TIME_STAMP as aaddharTimeStamp,BHAMASHA as bhamasha,\n" +
                        " MOBILE as mobile, RELATION as relation, SEX as sex, HANDICAP as handicap,IF_MARRIED as ifMarried, MOTHER_ID as motherId,STATUS as status, STAGE as stage, SUB_STAGE as subStage, IS_TO_TRACK as isToTrack, SURVEYOR_ID as surveyorId,TIME_STAMP as timeStamp,SOURCE as source, IS_APPROVED as isApproved, IS_NEW AS recordStatus FROM memberbasic WHERE recordStatus NOT NULL");
                JSONArray pwTracking=pushdata("SELECT PREGNANCY_ID as pregnancyId,MEMBERS_ID as membersId, STAGE as stage,SUB_STAGE as subStage,IS_AVAILABLE as isAvailable, NA_REASON as naReason,IS_ANC as isAnc,ANC_DATE as ancDate,IF_COUNSEL_ON_SELFFEED as ifCounselOnSelffeed,IF_COUNSEL_ON_BF as ifCounselOnBf,SPEND_ON_FOOD as spendOnFood,HEIGHT as height, WEIGHT as weight,SURVEYOR_ID as surveyorId,\n" +
                        " TIME_STAMP as timeStamp, SOURCE as source,IS_NEW AS recordStatus,IS_APPROVED as isApproved FROM PW_TRACKING WHERE recordStatus NOT NULL");
                JSONArray women_extra=pushdata("SELECT MEMBERS_ID as membersId,EDUCATION as education,COOKING_FUEL as cookingFuel, DECISIONMAKER_OWN_HEALTH as decisionmakerOwnHealth, DECISIONMAKER_CHILD_HEALTH as decisionmakerChildHealth,if_bank_account as ifBankAccont,AC_HOLDER_NAME as acHolderName, BANK_NAME as bankName,BRANCH as branch,AC_NO as acNo,IFSC_CODE as ifscCode,BANK_DISTANCE as bankDistance,POSTOFFICE_NAME as postofficeName,POSTOFFICE_ADDRESS as postofficeAddress,PIN_CODE as pinCode,POST_OFFICE_AC as postOfficeAc, HOEMO_CODE as hoemoCode,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM womenextra WHERE recordStatus NOT NULL");
                JSONArray child_extra=pushdata("SELECT MEMBERS_ID as membersId,DODELIVERY as dodelivery,DELIVERY_PLACE as deliveryPlace,CHILD_ORDER as childOrder,BIRTH_WT as birthWt,FULL_TERM as fullTerm,WHEN_FIRST_BF as whenFirstBf,IF_FEED_KHEES as ifFeedKhees,CURRENTLY_BF as currentlyBf,WHEN_STOP_BF as whenStopBf,ANYTHING_BEFORE_BF as anythingBeforeBf, IF_STARTED_SOLID_FOOD as ifStartedSolidFood,WHICH_MONTH_SOLID_FOOD as whichMonthSolidFood,CHILD_IMMUNIZATION_STATUS as childImmunizationStatus,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM childextra WHERE recordStatus NOT NULL");
                JSONArray child_tracking = pushdata("SELECT MEMBERS_ID as membersId,STAGE as stage,SUB_STAGE as subStage,IS_AVAILABLE as isAvailable,NA_REASON_MOTHER as naReasonMother,NA_REASON_CHILD as naReasonChild,NA_REASON_BOTH as naReasonBoth,CURRENTLY_BF as currentlyBf,IF_USING_CONTRACEPTIVE as ifUsingContraceptive,METHOD_CONTRACEPTIVE as methodContraceptive,IF_COUNSEL_ON_FEED_INFANT as ifCounselOnFeedInfant,IF_COUNSEL_ON_SELFFEED as ifCounselOnSelffeed,LIQUID_OTHER_THAN_BF as liquidOtherThanBf,IF_STARTED_SOLID_FOOD as ifStartedSolidFood,SPEND_ON_FOOD as spendOnFood,CHILD_IMMUNIZATION_STATUS as childImmunizationStatus,CHILD_HEIGHT as childHeight,CHILD_WEIGHT as childWeight,\n" +
                        "CHILD_MUAC as childMuac,SURVEYOR_ID as surveyorId,TIME_STAMP as timeStamp,SOURCE as source,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM child_tracking WHERE recordStatus NOT NULL ");
                JSONArray diet=pushdata("\n" +
                        "SELECT PREGNANCY_ID as pregnancyId,MEMBERS_ID as membersId,STAGE as stage,SUB_STAGE as subStage,FEED_A as feedA,FEED_A_NOS as feedAnos,FEED_B as feedB,FEED_B_NOS as feedBnos,FEED_C as feedC,FEED_C_NOS as feedCnos,FEED_D as feedD,FEED_D_NOS as feedDnos,FEED_E as feedE,FEED_E_NOS as feedEnos, FEED_F as feedF,FEED_F_NOS as feedFnos,FEED_G as feedG, FEED_G_NOS as feedGnos,FEED_H as feedH,FEED_H_NOS as feedHnos,FEED_I as feedI,FEED_I_NOS as feedInos,FEED_J as feedJ,FEED_J_NOS as feedJnos,FEED_K as feedK,FEED_K_NOS as feedKnos,FEED_L as feedL,FEED_L_NOS as feedLnos,FEED_M as feedM,FEED_M_NOS as feedMnos,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM DIET WHERE recordStatus  NOT NULL");
                JSONArray pregnent=pushdata("SELECT PREGNANCY_ID as pregnancyId,MEMBERS_ID as membersId,ORDER_OF_PREGNANCY as orderOfPregnancy,LMP_DATE as lmpDate,IS_ACTIVE as isActive,SURVEYOR_ID as surveyorId,TIME_STAMP as timeStamp,SOURCE as source,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM PREGNANT WHERE recordStatus  NOT NULL");
try {


    JSONObject jsonObject = new JSONObject();
    jsonObject.put("memberBasic", memberbasicList);
    jsonObject.put("pwTracking", pwTracking);
    jsonObject.put("diet", diet);
    jsonObject.put("familyDetails", familyList);
    jsonObject.put("womenExtra", women_extra);
    jsonObject.put("childTracking", child_tracking);
    jsonObject.put("childExtra", child_extra);
    jsonObject.put("pregnant", pregnent);

    String jsonObjectString= jsonObject.toString(0);
    Log.d("JSONCHECK",jsonObjectString);
    Log.d("JSONCHECK",pregnent.toString());
    Log.d("JSONCHECK",women_extra.toString());

  PushData pushDaataClass = new PushData();
   pushDaataClass.execute(jsonObjectString);

}catch (Exception e){

    Log.d("JSONCHECK",e.toString()+"Error pusjh");

}

            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();
        displaySelectedScreen(id);

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainframe, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedScreen(int itemId) {


        //initializing the fragment object which is selected
        switch (itemId) {


            case R.id.nav_home:
//                Intent i = new Intent(this, FragmentHome.class);
////                i.putExtra("title", itemId.getTitle());
//                startActivity(i);

                fragmentt = new HomeFragment();
                break;


        }

        //replacing the fragment
        if (fragmentt != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainframe, fragmentt);
            ft.commit();

        }
    }

 public class PushData  extends AsyncTask<String, String, String>

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

Log.d("DashPushJson",stringName);
Log.d("DashPushJson",new BaseUrl().base_url+"restservice/surveyor/sendsurveydata/"+session.getSurveyorId());

        try {

            HttpResponse response;

            JSONObject jsonObject = new JSONObject();
//
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(
                    new BaseUrl().base_url+"restservice/surveyor/sendsurveydata/"+session.getSurveyorId());
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(stringName, "UTF-8"));
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

        asyncDialog = new ProgressDialog(DashBoard.this);
        asyncDialog.setMessage("Please wait data is sync is going on..");
        asyncDialog.show();


    }

        @Override
        protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
//            Log.d("LoginDetails",aVoid);
//

        try {

            if(aVoid.equalsIgnoreCase("true")|| aVoid.equalsIgnoreCase("false")){


                ExeuteQuery("update familydata set is_new=null");
                ExeuteQuery("update memberbasic set is_new=null");
                ExeuteQuery("update pregnant set is_new=null");
                ExeuteQuery("update womenextra set is_new=null");
                ExeuteQuery("update childextra set is_new=null");
                ExeuteQuery("update pw_tracking set is_new=null");
                ExeuteQuery("update child_tracking set is_new=null");
                ExeuteQuery("update diet set is_new=null");


            }
//
//                uid="ranjeet";

//                if(arraydata!=null){
//                    arraydata.clear();
//                }
//                getCaptured();
            try {

                Toast.makeText(DashBoard.this, "Inserted Succesfully", Toast.LENGTH_SHORT).show();
                asyncDialog.cancel();
                onBackPressed();

            }catch(Exception e){

                Log.d("syncActivity",e.toString()) ;
                asyncDialog.dismiss();

            }




//
//
//

















//
//



        } catch (Exception e) {
            Log.d("Ranjeet", "ranjeet Error" + e.toString());
//                progressDialog.dismiss();
            asyncDialog.dismiss();
        }

    }

        @Override
        protected void onCancelled() {
        super.onCancelled();


    }


    }

    public JSONArray pushdata(String Query){
        JSONArray jsonArrayFamily = new JSONArray();
        String jsonStr="";
Log.d("insertedInto","pushdata");

        JSONObject jsonObject= new JSONObject();

//                String FamiliQuery="SELECT FAMILY_ID, DIST_CODE, PROJECT_CODE, SECTOR_CODE, AWC_CODE,VILLAGE_CODE,RELIGION,CASTE ,RCARD,FAMILY_TYPE,SURVEYOR_ID,IS_APPROVED, IS_NEW AS recordStatus FROM familydata WHERE recordStatus NOT NULL";
        SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        JSONArray resultSet = new JSONArray();
        JSONObject returnObj = new JSONObject();
        Cursor cursor = swl.rawQuery(Query, null);
        Log.d("insertedInto","pushdata"+cursor.getCount());
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {

                    try {

                        if (cursor.getString(i) != null) {
                            Log.d("TAG_NAME", cursor.getString(i));
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        Log.d("TAG_NAME", e.getMessage());
                    }
                }

            }

            resultSet.put(rowObject);
            cursor.moveToNext();
        }

        cursor.close();
        Log.d("TAG_NAME", resultSet.toString());

return resultSet;

    }

    public JSONArray pushmemberbaisc() {

        String memberBaiscQuery = "SELECT MEMBERS_ID, FAMILY_ID, NAME,DOR, DOENTRY, DOEXIT, DOB, AGE, IF_DOB_ASSUMED, DODEATH,AADDHAR, AADDHAR_ENROL_NO, AADDHAR_DATE_STAMP, AADDHAR_TIME_STAMP,BHAMASHA,\n" +
                " MOBILE, RELATION, SEX, HANDICAP,IF_MARRIED, MOTHER_ID,STATUS, STAGE, SUB_STAGE, IS_TO_TRACK, SURVEYOR_ID,TIME_STAMP,SOURCE, IS_APPROVED, IS_NEW AS recordStatus FROM memberbasic WHERE recordStatus NOT NULL";

        SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cfamily = swl.rawQuery(memberBaiscQuery, null);
        int total = cfamily.getCount();
        Log.d("PushMember", "" + total);
        if (total >= 1) {

            if (cfamily.moveToFirst()) {
                do {

                    String ifDobAssumed=cfamily.getString(cfamily.getColumnIndex("if_dob_assumed"));
                    String isToTrack=cfamily.getString(cfamily.getColumnIndex("IS_TO_TRACK"));
                     String dor =cfamily.getString(cfamily.getColumnIndex("dor"));
                       String sex =cfamily.getString(cfamily.getColumnIndex("sex"));
                        String aaddharDateStamp=cfamily.getString(cfamily.getColumnIndex("aaddhar_date_stamp"));
                          String mobile=cfamily.getString(cfamily.getColumnIndex("mobile"));
                         String source=cfamily.getString(cfamily.getColumnIndex("source"));
                      String aaddhar =cfamily.getString(cfamily.getColumnIndex("aaddhar"));
                      String relation =cfamily.getString(cfamily.getColumnIndex("relation"));
                     String familyId=cfamily.getString(cfamily.getColumnIndex("family_id"));
                       String bhamasha=cfamily.getString(cfamily.getColumnIndex("bhamasha"));
                            String recordStatus=cfamily.getString(cfamily.getColumnIndex("recordStatus"));
                            String membersId =cfamily.getString(cfamily.getColumnIndex("Members_id"));
                            String stage=cfamily.getString(cfamily.getColumnIndex("stage"));
                            String dob =cfamily.getString(cfamily.getColumnIndex("dob"));
                            String name=cfamily.getString(cfamily.getColumnIndex("name"));
                            String surveyorId=cfamily.getString(cfamily.getColumnIndex("surveyor_id"));
                            String doentry =cfamily.getString(cfamily.getColumnIndex("doentry"));
                            String subStage =cfamily.getString(cfamily.getColumnIndex("sub_stage"));
                            String ifMarried =cfamily.getString(cfamily.getColumnIndex("if_married"));
                            String age=cfamily.getString(cfamily.getColumnIndex("age"));
                            String status=cfamily.getString(cfamily.getColumnIndex("status"));
                            String doexit=cfamily.getString(cfamily.getColumnIndex("doexit"));



                            Log.d("PushMember",recordStatus);
                            Log.d("PushMember",membersId);
                            Log.d("PushMember",dob);

//MemberBasicGetSet memberBasic = new MemberBasicGetSet(membersId,familyId,name,dor,doentry, doexit,dob,age,
//        ifDobAssumed,dodeath,aaddhar, String aadharenrol, String aadhardate, String aadhartime,
//        String bhamasha, mobile, String relation, String sex, String handicap, String ifmarried,
//        String motherid, String status, String stage, String substage, String trackstatus,
//        String surveyor_id, String timestamp, String source,
//        String isNew,String isEditedMember,String isApprovedMember);


                } while (cfamily.moveToNext());



            }

        }
        return null;
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

            asyncDialog = new ProgressDialog(DashBoard.this);
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

            fragmentt = new HomeFragment();
            if (fragmentt != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.mainframe, fragmentt);
                fragmentTransaction.commit();
            }

        }catch (Exception e){
            Log.d("RanjeetPullData",e.toString());
        }

        return completed;

    }


    public void ExeuteQuery(String query){
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c1 = dbs.rawQuery(query,null);
        c1.moveToFirst();
    }



    }

