package in.co.rajpusht.rajpusht;


import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
import java.util.Calendar;
import java.util.Date;

import Adpter.DetailsAdapter;
import extras.BaseUrl;
import extras.BeneficiaryList;
import extras.RecyclerTouchListener;
import extras.SessionManager;

import static android.content.Context.MODE_PRIVATE;




/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    SessionManager session;
    ArrayList<BeneficiaryList> arraybeneficiary = new ArrayList<BeneficiaryList>();
    private SearchView searchView;

    String beneficiaryQuery="select a.Members_id ,a.family_id,a.name,a.husband,a.pctsid,b.members_id as mother_id,b.name as mother,ifnull(a.status,b.status)status,ifnull(a.stage,b.stage)as stage,ifnull(a.sub_stage,b.sub_stage)as sub_stage,strftime('%d/%m/%Y', lmp_date)lmp_date,strftime('%d/%m/%Y', dodelivery)dodelivery,p.PREGNANCY_ID,is_anc,case\n" +
            "when  julianday('now') - julianday(lmp_date)<=98 then 'PW1'\n" +
            "when  julianday('now') - julianday(lmp_date)<=196 then 'PW2'\n" +
            "when  julianday('now') - julianday(lmp_date)<=252 then 'PW3'\n" +
            "when  julianday('now') - julianday(lmp_date)<=280 then 'PW4'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+3 month',cast(strftime('%d',DODELIVERY)as text) ||' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM1'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+6 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM2'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+12 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM3'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+18 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY1'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+24 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY2'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+30 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY3'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+36 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY4'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+38 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY5'\n" +
            "END\n as " +
            "current_sub_stage \n" +
            "FROM memberbasic a inner join familydata f on a.family_id=f.family_id left join memberbasic b on a.mother_id=b.members_id left join (select * from pregnant where is_active='Y') p on a.members_id=p.MEMBERS_ID left join childextra c on a.MEMBERS_ID=c.MEMBERS_ID" +
            " left join (select * from pw_tracking) t on p.PREGNANCY_ID=t.PREGNANCY_ID and t.SUB_STAGE=current_sub_stage where a.is_to_track='Y' and f.awc_code=(select awc_code from assigned_location where login='Y') and current_sub_stage<>'' order by a.sub_stage asc,a.name desc";
//
//String beneficiaryQuery="select a.Members_id ,a.family_id,a.name,b.members_id as mother_id,b.name as mother,ifnull(a.status,b.status)status,ifnull(a.stage,b.stage)as stage,ifnull(a.sub_stage,b.sub_stage)as sub_stage,strftime('%d/%m/%Y', lmp_date)lmp_date,strftime('%d/%m/%Y', dodelivery)dodelivery,p.PREGNANCY_ID,is_anc,(case\n" +
//        "when  julianday('now') - julianday(lmp_date)<=98 then 'PW1'\n" +
//        "when  julianday('now') - julianday(lmp_date)<=196 then 'PW2'\n" +
//        "when  julianday('now') - julianday(lmp_date)<=252 then 'PW3'\n" +
//        "when  julianday('now') - julianday(lmp_date)<=280 then 'PW4'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+3 month',cast(strftime('%d',DODELIVERY)as text) ||' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM1'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+6 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM2'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+12 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM3'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+18 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY1'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+24 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY2'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+30 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY3'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+36 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY4'\n" +
//        "when CAST(strftime('%s',date(DODELIVERY,'start of month','+38 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY5'\n" +
//        "END\n) as " +
//        "current_sub_stage \n" +
//        "FROM memberbasic a left join memberbasic b on a.mother_id=b.members_id left join (select * from pregnant where is_active='Y') p on a.members_id=p.MEMBERS_ID left join childextra c on a.MEMBERS_ID=c.MEMBERS_ID" +
//        " left join (select * from pw_tracking) t on p.PREGNANCY_ID=t.PREGNANCY_ID and t.SUB_STAGE=current_sub_stage where a.is_to_track='Y'  order by a.sub_stage asc,a.name desc";




    public HomeFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        session = new SessionManager(getActivity().getApplicationContext());

        recyclerView = (RecyclerView) v.findViewById(R.id.beneficairyList);


        Calendar c = Calendar.getInstance();

// set the calendar to start of today
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date today = c.getTime();
        long todayInMillis = c.getTimeInMillis();

        if(session.getPresentDate()==0){
            session.setPresentDate(todayInMillis);
        }
        else{
            if(new Date(session.getPresentDate()).before(today)){
                Log.d("RanjeetHomeFragment","inserted invalue part");
                pushDataToserver();
//                session.setPresentDate(todayInMillis);
            }else {
                Log.d("RanjeetHomeFragment", "inserted in else part ");
                Log.d("RanjeetHomeFragment", ""+new Date(session.getPresentDate()));
                Log.d("RanjeetHomeFragment", ""+today);

            }
            session.setPresentDate(todayInMillis);
        }

//automatic time zone
        if(isTimeAutomatic()) {
        }else{

            Intent inett = new Intent(getActivity(),CheckTimeActivity.class);
            startActivity(inett);
            getActivity().finish();
        }

//        if(arraybeneficiary.)

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {


                BeneficiaryList beneficiaryList= arraybeneficiary.get(position);

                if(beneficiaryList.getCurrent_sub_stage().substring(0,2).equalsIgnoreCase("pw")){

                    Intent intetPw1= new Intent(getActivity().getApplicationContext(),PregantWomenFooter.class);
                    intetPw1.putExtra("pregnancy_id",beneficiaryList.getPregnancy_id());
                    intetPw1.putExtra("current_sub_stage",beneficiaryList.getCurrent_sub_stage());
                    intetPw1.putExtra("name",beneficiaryList.getName());
                    intetPw1.putExtra("husband",beneficiaryList.getHusband());
                    intetPw1.putExtra("pctsid",beneficiaryList.getPctsid());
                    intetPw1.putExtra("members_id",beneficiaryList.getMembers_id());
                    intetPw1.putExtra("lmpDate",beneficiaryList.getLmp_date());
                    intetPw1.putExtra("getstage","pw");
                    intetPw1.putExtra("familyId",beneficiaryList.getFamily_id());
                    intetPw1.putExtra("motherId",beneficiaryList.getMotherId());
                    startActivity(intetPw1);
                    getActivity().finish();
                }

                if(beneficiaryList.getCurrent_sub_stage().substring(0,2).equalsIgnoreCase("LM")){

                    Intent intentLm = new Intent(getActivity().getApplicationContext(),LM_actvity.class);
                    intentLm.putExtra("name",beneficiaryList.getName());
                    intentLm.putExtra("husband",beneficiaryList.getHusband());
                    intentLm.putExtra("pctsid",beneficiaryList.getPctsid());
                    intentLm.putExtra("memberId",beneficiaryList.getMembers_id());
                    intentLm.putExtra("current_sub_stage",beneficiaryList.getCurrent_sub_stage());
                    intentLm.putExtra("motherId",beneficiaryList.getMotherId());
                    intentLm.putExtra("familyId",beneficiaryList.getFamily_id());
                    intentLm.putExtra("getstage","LM");
                    intentLm.putExtra("pregnancy_id",beneficiaryList.getPregnancy_id());
                    startActivity(intentLm);
                    getActivity().finish();
                }



                if(beneficiaryList.getCurrent_sub_stage().substring(0,2).equalsIgnoreCase("MY")){

                    Intent intentLm = new Intent(getActivity().getApplicationContext(),MyActivity.class);
                    intentLm.putExtra("name",beneficiaryList.getName());
                    intentLm.putExtra("husband",beneficiaryList.getHusband());
                    intentLm.putExtra("pctsid",beneficiaryList.getPctsid());
                    intentLm.putExtra("memberId",beneficiaryList.getMembers_id());
                    intentLm.putExtra("current_sub_stage",beneficiaryList.getCurrent_sub_stage());
                    intentLm.putExtra("motherId",beneficiaryList.getMotherId());
                    intentLm.putExtra("familyId",beneficiaryList.getFamily_id());
                    intentLm.putExtra("getstage","MY");
                    intentLm.putExtra("pregnancy_id",beneficiaryList.getPregnancy_id());
                    startActivity(intentLm);
                    getActivity().finish();
                }
                Log.d("HomeREceycler"," "+position);







//                startActivity(intetPw1);




//                Toast.makeText(getApplicationContext(), movie.getDropLocation() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));




        getallBeneficairyList();
//
//        Button pw = (Button) v.findViewById(R.id.pww1);
//        pw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), PregnantWomenForm.class);
//                i.putExtra("button","PW1");
//                i.putExtra("name","Bimala Agarwal");
//                startActivity(i);
//            }
//        });
//
//        Button pw2 = (Button) v.findViewById(R.id.pw2);
//        pw2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i2 = new Intent(getActivity(), PregnantWomenForm.class);
//                i2.putExtra("button","PW2");
//                i2.putExtra("name","Sonam Agarwal");
//                startActivity(i2);
//            }
//        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_settings)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
//                movieAdapter.getFilter().filter(query);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:

                //       onCall();   //your logic

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void getallBeneficairyList(){

        SQLiteDatabase dbs = getActivity().openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(beneficiaryQuery , null);
        Log.d("beneficiaryQuery",beneficiaryQuery);

        int total = c.getCount();

        if(total>=1) {

            if (c.moveToFirst()) {
                do {

                    BeneficiaryList benefic = new BeneficiaryList(c.getString(c.getColumnIndex("Members_id")), c.getString(c.getColumnIndex("family_id")), c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("husband")),c.getString(c.getColumnIndex("pctsid")), c.getString(c.getColumnIndex("mother_id")),
                            c.getString(c.getColumnIndex("mother")), c.getString(c.getColumnIndex("status")),
                                    c.getString(c.getColumnIndex("stage")), c.getString(c.getColumnIndex("sub_stage")),
                                            c.getString(c.getColumnIndex("lmp_date")), c.getString(c.getColumnIndex("dodelivery")), c.getString(c.getColumnIndex("current_sub_stage")),c.getString(c.getColumnIndex("pregnancy_id")),c.getString(c.getColumnIndex("is_anc")),
                            c.getString(c.getColumnIndex("mother_id")));
                    arraybeneficiary.add(benefic);


//                          Log.d("awcrecords",c.getString(c.getColumnIndex("awc_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("dist_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("familyid")));

                } while (c.moveToNext());
            }
        }




        DetailsAdapter adapter = new DetailsAdapter(arraybeneficiary, getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linear= new LinearLayoutManager(getActivity().getApplication());
        linear.setReverseLayout(true);
        linear.setStackFromEnd(true);
        recyclerView.setLayoutManager(linear);

    }

    @Override
    public void onResume() {
        super.onResume();
//automatic time zone
//        if(isTimeAutomatic()) {
//        }else{
//
//            Intent inett = new Intent(getActivity(),CheckTimeActivity.class);
//            startActivity(inett);
//            getActivity().finish();
//        }

    }



    public  boolean isTimeAutomatic() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(getActivity().getContentResolver(), Settings.Global.AUTO_TIME, 0)==1;
        } else {
            return android.provider.Settings.System.getInt(getActivity().getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0) == 1;
        }
    }

    public void pushDataToserver(){

//        syncValue=pusgValue;

        JSONArray familyList = pushdata("SELECT FAMILY_ID as familyId, DIST_CODE as distCode, PROJECT_CODE as projectCode, SECTOR_CODE as sectorCode, AWC_CODE as awcCode,VILLAGE_CODE as villageCode,RELIGION as religion,CASTE as cast ,RCARD as rcard,FAMILY_TYPE as familyType,SURVEYOR_ID as surveyorId,IS_APPROVED as isApproved, IS_NEW AS recordStatus FROM familydata WHERE recordStatus <>''");

        JSONArray memberbasicList =pushdata("SELECT MEMBERS_ID as membersId, FAMILY_ID as familyId, NAME as name,HUSBAND as husband,PCTSID as pctsid,DOR as dor, DOENTRY as doentry, DOEXIT as doexit, DOB as dob, AGE as age, IF_DOB_ASSUMED as ifDobAssumed, DODEATH as dodeath,AADDHAR as aaddhar, AADDHAR_ENROL_NO as aaddharEnrolNo, AADDHAR_DATE_STAMP as aaddharDateStamp, AADDHAR_TIME_STAMP as aaddharTimeStamp,BHAMASHA as bhamasha,\n" +
                " MOBILE as mobile, RELATION as relation, SEX as sex, HANDICAP as handicap,IF_MARRIED as ifMarried, MOTHER_ID as motherId,STATUS as status, STAGE as stage, SUB_STAGE as subStage, IS_TO_TRACK as isToTrack, SURVEYOR_ID as surveyorId,TIME_STAMP as timeStamp,SOURCE as source, IS_APPROVED as isApproved, IS_NEW AS recordStatus FROM memberbasic WHERE recordStatus <>''");
        JSONArray pwTracking=pushdata("SELECT PREGNANCY_ID as pregnancyId,MEMBERS_ID as membersId, STAGE as stage,SUB_STAGE as subStage,IS_AVAILABLE as isAvailable, NA_REASON as naReason,IS_ANC as isAnc,ANC_DATE as ancDate,IF_COUNSEL_ON_SELFFEED as ifCounselOnSelffeed,IF_COUNSEL_ON_BF as ifCounselOnBf,SPEND_ON_FOOD as spendOnFood,HEIGHT as height, WEIGHT as weight,SURVEYOR_ID as surveyorId,\n" +
                " TIME_STAMP as timeStamp, SOURCE as source,IS_NEW AS recordStatus,IS_APPROVED as isApproved FROM PW_TRACKING WHERE recordStatus <>''");
        JSONArray women_extra=pushdata("SELECT MEMBERS_ID as membersId,EDUCATION as education,COOKING_FUEL as cookingFuel, DECISIONMAKER_OWN_HEALTH as decisionmakerOwnHealth, DECISIONMAKER_CHILD_HEALTH as decisionmakerChildHealth,if_bank_account as ifBankAccont,AC_HOLDER_NAME as acHolderName, BANK_NAME as bankName,BRANCH as branch,AC_NO as acNo,IFSC_CODE as ifscCode,BANK_DISTANCE as bankDistance,POSTOFFICE_NAME as postofficeName,POSTOFFICE_ADDRESS as postofficeAddress,PIN_CODE as pinCode,POST_OFFICE_AC as postOfficeAc, HOEMO_CODE as hoemoCode,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM womenextra WHERE recordStatus <>''");
        JSONArray child_extra=pushdata("SELECT MEMBERS_ID as membersId,DODELIVERY as dodelivery,DELIVERY_PLACE as deliveryPlace,CHILD_ORDER as childOrder,BIRTH_WT as birthWt,FULL_TERM as fullTerm,WHEN_FIRST_BF as whenFirstBf,IF_FEED_KHEES as ifFeedKhees,CURRENTLY_BF as currentlyBf,WHEN_STOP_BF as whenStopBf,ANYTHING_BEFORE_BF as anythingBeforeBf, IF_STARTED_SOLID_FOOD as ifStartedSolidFood,WHICH_MONTH_SOLID_FOOD as whichMonthSolidFood,CHILD_IMMUNIZATION_STATUS as childImmunizationStatus,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM childextra WHERE recordStatus  <>''");
        JSONArray child_tracking = pushdata("SELECT MEMBERS_ID as membersId,STAGE as stage,SUB_STAGE as subStage,IS_AVAILABLE as isAvailable,NA_REASON_MOTHER as naReasonMother,NA_REASON_CHILD as naReasonChild,NA_REASON_BOTH as naReasonBoth,CURRENTLY_BF as currentlyBf,IF_USING_CONTRACEPTIVE as ifUsingContraceptive,METHOD_CONTRACEPTIVE as methodContraceptive,IF_COUNSEL_ON_FEED_INFANT as ifCounselOnFeedInfant,IF_COUNSEL_ON_SELFFEED as ifCounselOnSelffeed,LIQUID_OTHER_THAN_BF as liquidOtherThanBf,IF_STARTED_SOLID_FOOD as ifStartedSolidFood,SPEND_ON_FOOD as spendOnFood,CHILD_IMMUNIZATION_STATUS as childImmunizationStatus,CHILD_HEIGHT as childHeight,CHILD_WEIGHT as childWeight,\n" +
                "CHILD_MUAC as childMuac,SURVEYOR_ID as surveyorId,TIME_STAMP as timeStamp,SOURCE as source,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM child_tracking WHERE recordStatus <>''");
        JSONArray diet=pushdata("\n" +
                "SELECT PREGNANCY_ID as pregnancyId,MEMBERS_ID as membersId,STAGE as stage,SUB_STAGE as subStage,FEED_A as feedA,FEED_A_NOS as feedAnos,FEED_B as feedB,FEED_B_NOS as feedBnos,FEED_C as feedC,FEED_C_NOS as feedCnos,FEED_D as feedD,FEED_D_NOS as feedDnos,FEED_E as feedE,FEED_E_NOS as feedEnos, FEED_F as feedF,FEED_F_NOS as feedFnos,FEED_G as feedG, FEED_G_NOS as feedGnos,FEED_H as feedH,FEED_H_NOS as feedHnos,FEED_I as feedI,FEED_I_NOS as feedInos,FEED_J as feedJ,FEED_J_NOS as feedJnos,FEED_K as feedK,FEED_K_NOS as feedKnos,FEED_L as feedL,FEED_L_NOS as feedLnos,FEED_M as feedM,FEED_M_NOS as feedMnos,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM DIET WHERE recordStatus <>''");
        JSONArray pregnent=pushdata("SELECT PREGNANCY_ID as pregnancyId,MEMBERS_ID as membersId,ORDER_OF_PREGNANCY as orderOfPregnancy,LMP_DATE as lmpDate,IS_ACTIVE as isActive,SURVEYOR_ID as surveyorId,TIME_STAMP as timeStamp,SOURCE as source,IS_APPROVED as isApproved,IS_NEW AS recordStatus FROM PREGNANT WHERE recordStatus <>''");
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



            Log.d("JSONCHECK",jsonObject.toString(0));

//            logLargeString(jsonObject.toString(0));
//
//            ExeuteQuery("update familydata set is_new=null");
//            ExeuteQuery("update memberbasic set is_new=null");
//            ExeuteQuery("update pregnant set is_new=null");
//            ExeuteQuery("update womenextra set is_new=null");
//            ExeuteQuery("update childextra set is_new=null");
//            ExeuteQuery("update pw_tracking set is_new=null");
//            ExeuteQuery("update child_tracking set is_new=null");
//            ExeuteQuery("update diet set is_new=null");



            PushData pushDaataClass = new PushData();
            pushDaataClass.execute(jsonObject.toString(0));

        }catch (Exception e){

            Log.d("JSONCHECK",e.toString()+"Error pusjh");

        }




    }

    public JSONArray pushdata(String Query){
        JSONArray jsonArrayFamily = new JSONArray();
        String jsonStr="";
        Log.d("insertedInto","pushdata");

        JSONObject jsonObject= new JSONObject();

//                String FamiliQuery="SELECT FAMILY_ID, DIST_CODE, PROJECT_CODE, SECTOR_CODE, AWC_CODE,VILLAGE_CODE,RELIGION,CASTE ,RCARD,FAMILY_TYPE,SURVEYOR_ID,IS_APPROVED, IS_NEW AS recordStatus FROM familydata WHERE recordStatus NOT NULL";
        SQLiteDatabase swl = getActivity().openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

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

            asyncDialog = new ProgressDialog(getActivity());
            asyncDialog.setMessage("Please wait data is sync is going on..");
            asyncDialog.show();


        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            Log.d("LoginDetails",aVoid);
//


            Log.d("pushVAlidtioni","insered");
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


//                    if(syncValue.equalsIgnoreCase("logoutSelected")){
                        session.logoutUser();
//                    }

                }
//
//                uid="ranjeet";

//                if(arraydata!=null){
//                    arraydata.clear();
//                }
//                getCaptured();
                try {

//                    Toast.makeText(getActivity().getApplicationContext(), "Inserted Succesfully", Toast.LENGTH_SHORT).show();
                    asyncDialog.cancel();
//                    onBackPressed();

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

    public void ExeuteQuery(String query){
        SQLiteDatabase dbs = getActivity().openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c1 = dbs.rawQuery(query,null);
        c1.moveToFirst();
    }

}

