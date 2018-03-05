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

import extras.FamilyDetailGetSet;
import extras.MemberBasicGetSet;

public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SearchView searchView;
    private Boolean exit = false;
    ImageView profile;
    String selectionstatus=null;
    RelativeLayout relativePregnent,realtiveYoungMother,relativeLactingMother;

    ArrayList<FamilyDetailGetSet> ArrayfamilyDetails = new ArrayList<FamilyDetailGetSet>();




CheckBox checkChild,checkPregnets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       String vid = getIntent().getStringExtra("vid");


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
            }
        });

        TextView pullData = (TextView) findViewById(R.id.PullData);
        pullData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
            }
        });

        TextView pushData = (TextView) findViewById(R.id.pushData);
        pushData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);


                ArrayList<FamilyDetailGetSet> familyList = pushdata();
//                ArrayList<MemberBasicGetSet> memberbasicList =pushmemberbaisc();


                for (FamilyDetailGetSet s : familyList)
                {
                    Toast.makeText(getApplicationContext(),s.getDist_code() , Toast.LENGTH_SHORT).show();
                }



                Log.d("PushDdetails",familyList.toString()+"");
//                Log.d("PushMember", "" + memberbasicList);

                JSONObject json = new JSONObject();
                JSONArray jsonArray ;

try {
    jsonArray = new JSONArray(familyList);
    Log.d("PushDdetails","length"+jsonArray.length());
    json.accumulate("familyDetails",jsonArray);




    Log.d("PushDdetails",json.toString());
}catch(Exception e){
    Log.d("Ranjeet ",e.toString());
}

//                getPushDate();
//                Toast.makeText(DashBoard.this, "CLicked", Toast.LENGTH_SHORT).show();
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

        Fragment fragmentt = null;

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



        String name=params[0];
        String lat=params[1];
        String lon=params[2];

        try {

            HttpResponse response;

            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("UserId",session.getUserID());

            jsonObject.accumulate("Lat",lat);
            jsonObject.accumulate("Long",lon);
            jsonObject.accumulate("Remarks",name);



            json = jsonObject.toString();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://ecometrix.co.in/api/PmayMobile/InsertUserGeofenceDetails");
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

    public ArrayList<FamilyDetailGetSet> pushdata(){
        JSONArray jsonArrayFamily = new JSONArray();
        String jsonStr="";

        JSONObject jsonObject= new JSONObject();

                String FamiliQuery="SELECT FAMILY_ID, DIST_CODE, PROJECT_CODE, SECTOR_CODE, AWC_CODE,VILLAGE_CODE,RELIGION,CASTE ,RCARD,FAMILY_TYPE,SURVEYOR_ID,IS_APPROVED, IS_NEW AS recordStatus FROM familydata WHERE recordStatus NOT NULL";
        SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cfamily = swl.rawQuery(FamiliQuery, null);
        int total = cfamily.getCount();
        Log.d("PusfFamily",""+total);
        if (total >= 1) {

            if (cfamily.moveToFirst()) {
                do {




                  String  FAMILY_ID =cfamily.getString(cfamily.getColumnIndex("family_id"));
                    String  DIST_CODE=cfamily.getString(cfamily.getColumnIndex("dist_code"));
                    String  PROJECT_CODE=cfamily.getString(cfamily.getColumnIndex("project_code"));
                    String SECTOR_CODE=cfamily.getString(cfamily.getColumnIndex("sector_code"));
                    String  AWC_CODE=cfamily.getString(cfamily.getColumnIndex("awc_code"));
                    String   VILLAGE_CODE=cfamily.getString(cfamily.getColumnIndex("village_code"));
                    String   RELIGION=cfamily.getString(cfamily.getColumnIndex("religion"));
                    String   CAST=cfamily.getString(cfamily.getColumnIndex("caste"));
                    String   RCARD=cfamily.getString(cfamily.getColumnIndex("rcard"));
                    String   FAMILY_TYPE=cfamily.getString(cfamily.getColumnIndex("family_type"));
                    String   SURVEYOR_ID=cfamily.getString(cfamily.getColumnIndex("surveyor_id"));
                    String   IS_APPROVED=cfamily.getString(cfamily.getColumnIndex("is_approved"));
                    String   recordStatus=cfamily.getString(cfamily.getColumnIndex("recordStatus"));

                    Log.d("PusfFamily",FAMILY_ID);
                    Log.d("PusfFamily",recordStatus);
                    Log.d("PusfFamily",CAST);


                    FamilyDetailGetSet familydetails = new FamilyDetailGetSet(FAMILY_ID,Integer.parseInt(RELIGION),Integer.parseInt(CAST),
                            Integer.parseInt(RCARD),Integer.parseInt(FAMILY_TYPE),DIST_CODE,PROJECT_CODE,
                            SECTOR_CODE,AWC_CODE,SURVEYOR_ID,VILLAGE_CODE,recordStatus);

                    ArrayfamilyDetails.add(familydetails);


      } while (cfamily.moveToNext());

            }
        }
return ArrayfamilyDetails;

    }

    public ArrayList<MemberBasicGetSet> pushmemberbaisc() {

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

}
