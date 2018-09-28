package in.co.rajpusht.rajpusht;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import extras.SessionManager;

public class LM_actvity extends AppCompatActivity {

    Button lm1, lm2, lm3;
    LinearLayout zerothree, threesix, six;
    Dialog dialogCoupon;

    View sbcclayout, dietaryLyout, heightWeightLayout, fragmentnaforlmnmy;
    RelativeLayout relativessbc, relativeDiversity, relativeHeight, holdingTabs;
    View basicclick, pregnentclick, childclick,pTod;
    Menu menu;

    CheckBox a_food, b_food, c_food, d_food, e_food, f_food, g_food, h_food, i_food, j_food, k_food, l_food, m_food;
    EditText a_no, b_no, c_no, d_no, e_no, f_no, g_no, h_no, i_no, j_no, k_no, l_no, m_no;

    CheckBox ca_food, cb_food, cc_food, cd_food, ce_food, cf_food, cg_food, ch_food, ci_food, cj_food, ck_food, cl_food, cm_food;
    EditText ca_no, cb_no, cc_no, cd_no, ce_no, cf_no, cg_no, ch_no, ci_no, cj_no, ck_no, cl_no, cm_no;


    String af, bf, cf, df, ef, ff, gf, hf, i_f, jf, kf, lf, mf;
    Integer an=1, bn=1, cn=1, dn=1, en=1, fn=1, gn=1, hn=1, in=1, jn=1, kn=1, ln=1, mn=1;

    String caf, cbf, ccf, cdf, cef, cff, cgf, chf, ci_f, cjf, ckf, clf, cmf;
    Integer can, cbn, ccn, cdn, cen, cfn, cgn, chn, cin, cjn, ckn, cln, cmn;

    ImageView naButton;
    TextView textvisibilty,breastfeedingTextView,khesstest,childBreastTest,otherfoodbeforebreastfeedTest;
    EditText weight,  height,muac,solidfoodmonth;
    String errorCode;



TextView semisolidfoodtochildtext;

    Spinner breastfeeding, whenstopbreastfeeding, ngoinfantfeed, childtobreast, khees, otherfoodbeforebreastfeed, liquidotherthenbreastfeeding, ngoownfood, householdexpence,semisolidfoodtochild;
    String breastfeedingStrung, whenstopbreastfeedingString, ngoinfantfeedString, childtobreastString, kheesString,
            otherfoodbeforebreastfeedString, ngoownfoodString, householdexpenceString, semisolidfoodtochildString, liuidotehrbreastfreddingString;

    int breastfeedingInte, whenstopbreastfeedingInt, ngoinfantfeedint, childtobreastint, kheesInt,
            otherfoodbeforebreastfeedint, ngoownfoodint, householdexpenceint,liuidotehrbreastfreddingInt,semisolidfoodtochildint;
    View viewunderline;

    Button save;
    String pwActive_subStage;
    String subStageMode;
    TextView profilePage;
    String   pregnentId="";
    int mYear2, mMonth2, mDay2;


    //datebase retrieve strign value


     String when_firstBf,if_feed_khee,current_bf, when_stop_bf,anything_before_bf,if_started_solid_food,WHICH_MONTH_SOLID_FOOD;
    TextView solidfoodmonthtext,lmpdate;
    LinearLayout linearChildForm,linearMotherForm;
    SessionManager session;
    RadioGroup radiogroup;
    EditText pregnetNumnber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lm_actvity);

        getSupportActionBar().setTitle("Child : " + getIntent().getStringExtra("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        session = new SessionManager(LM_actvity.this);

        lm1 = (Button) findViewById(R.id.LM1Button);
        lm2 = (Button) findViewById(R.id.LM2Button);
        lm3 = (Button) findViewById(R.id.LM3Button);
        naButton = (ImageView) findViewById(R.id.naButton);

//        View view = findViewById(R.id.childbreastNow);

        relativessbc = (RelativeLayout) findViewById(R.id.relativessbc);
        relativeDiversity = (RelativeLayout) findViewById(R.id.relativeDiversity);
        relativeHeight = (RelativeLayout) findViewById(R.id.relativeHeight);
        holdingTabs = (RelativeLayout) findViewById(R.id.holdingTabs);


        basicclick = (View) findViewById(R.id.basicclick);
        pregnentclick = (View) findViewById(R.id.pregnentclick);
        childclick = (View) findViewById(R.id.childclick);
        pTod = (View)findViewById(R.id.pTod);

        pregnetNumnber = (EditText) findViewById(R.id.pregnetNumnber);

        lmpdate = (TextView) findViewById(R.id.lmpdate);
        lmpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                datee.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear2 = calender.get(Calendar.YEAR);
                mMonth2 = calender.get(Calendar.MONTH);
                mDay2 = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(LM_actvity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                String month,day;
                                if((monthOfYear+1)<10){
                                    month="0"+String.valueOf(monthOfYear+1) ;
                                }
                                else {
                                    month=String.valueOf(monthOfYear+1);
                                }

                                if(dayOfMonth<10){
                                    day="0"+String.valueOf(dayOfMonth);

                                }
                                else{
                                    day=String.valueOf(dayOfMonth);
                                }
                                lmpdate.setText(year + "-"
                                        + month + "-" + day);
//                                getAge(year, (monthOfYear + 1),dayOfMonth );
                            }
                        }, mYear2, mMonth2, mDay2);
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                calender.add(Calendar.DATE,-279);
                dpd.getDatePicker().setMinDate(calender.getTimeInMillis());
                dpd.show();
            }
        });

        profilePage = (TextView) findViewById(R.id.profilePage);
        profilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),EditRegistartion.class);
                intent.putExtra("memberId",getIntent().getStringExtra("memberId"));
                intent.putExtra("motherId",getIntent().getStringExtra("motherId"));
                intent.putExtra("pregaencyId",getIntent().getStringExtra("pregnancy_id"));
                intent.putExtra("stage",getIntent().getStringExtra("getstage"));
                intent.putExtra("familyId",getIntent().getStringExtra("familyId"));
                startActivity(intent);

            }
        });


        viewunderline= findViewById(R.id.viewunderline);

        sbcclayout = (View) findViewById(R.id.sbcclayout);
        dietaryLyout = (View) findViewById(R.id.dietaryLyout);
        heightWeightLayout = (View) findViewById(R.id.heightWeightLayout);
        fragmentnaforlmnmy = (View) findViewById(R.id.NaFormButtonLayout);
        naButton = (ImageView) findViewById(R.id.naButton);

        textvisibilty= (TextView) findViewById(R.id.visibiltytext);
        breastfeedingTextView = (TextView) findViewById(R.id.breastfeedingTextView);
        khesstest = (TextView) findViewById(R.id.khesstest);
        childBreastTest = (TextView) findViewById(R.id.childBreastTest);
        otherfoodbeforebreastfeedTest = (TextView) findViewById(R.id.otherfoodbeforebreastfeedTest);
        semisolidfoodtochildtext = (TextView)findViewById(R.id.semisolidfoodtochildtext);
        solidfoodmonthtext = (TextView) findViewById(R.id.solidfoodmonthtext);

        linearChildForm = (LinearLayout) findViewById(R.id.linearChildForm);
        linearMotherForm = (LinearLayout) findViewById(R.id.linearMotherForm);

        naButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pwActive_subStage="na";
                fragmentnaforlmnmy.setVisibility(View.VISIBLE);
                holdingTabs.setVisibility(View.GONE);
                pTod.setVisibility(View.INVISIBLE);
            }
        });

//        zerothree = (LinearLayout) findViewById(R.id.zerothree);
//        threesix = (LinearLayout) findViewById(R.id.threesix);
//        six = (LinearLayout) findViewById(R.id.six);

        height= (EditText) findViewById(R.id.height);
        weight= (EditText) findViewById(R.id.weight);
        muac= (EditText) findViewById(R.id.muac);

        solidfoodmonth= (EditText) findViewById(R.id.solidfoodmonth);
        radiogroup = (RadioGroup) findViewById(R.id.radio);

        breastfeeding = (Spinner) findViewById(R.id.breastfeeding);
        List<String> listbreastfeeding = new ArrayList<String>();
        listbreastfeeding.add("--Select Options--");
        listbreastfeeding.add("Yes");
        listbreastfeeding.add("No");
        ArrayAdapter<String> dataAdapterlistbreastfeeding = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_breastfeeding));
        dataAdapterlistbreastfeeding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breastfeeding.setAdapter(dataAdapterlistbreastfeeding);

        breastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                breastfeedingStrung = String.valueOf(parent.getItemAtPosition(position));
                breastfeedingInte = position;



                if(breastfeedingInte==2){

                    whenstopbreastfeeding.setVisibility(View.VISIBLE);
                    textvisibilty.setVisibility(View.VISIBLE);
                    viewunderline.setVisibility(View.VISIBLE);

                }
                else{
                    whenstopbreastfeeding.setVisibility(View.GONE);
                    textvisibilty.setVisibility(View.GONE);
                    viewunderline.setVisibility(View.GONE);
                }

                if(breastfeedingInte==1){
                    breastfeedingStrung="Y";
                }
                if(breastfeedingInte==2){
                    breastfeedingStrung="N";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        whenstopbreastfeeding = (Spinner) findViewById(R.id.whenstopbreastfeeding);
        List<String> Listwhenstopbreastfeeding = new ArrayList<String>();
        Listwhenstopbreastfeeding.add("--Select Options--");
        Listwhenstopbreastfeeding.add("Within one week after birth");
        Listwhenstopbreastfeeding.add("Within one month after birth");
        Listwhenstopbreastfeeding.add("Within three months after birth");
        Listwhenstopbreastfeeding.add("Within six months after birth");
        Listwhenstopbreastfeeding.add("Within 12-18 months after birth");
        Listwhenstopbreastfeeding.add("Never breastfed");
        ArrayAdapter<String> dataAdapterwhenstopbreastfeeding = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_stopbreastfeeding));
        dataAdapterwhenstopbreastfeeding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whenstopbreastfeeding.setAdapter(dataAdapterwhenstopbreastfeeding);


        whenstopbreastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                whenstopbreastfeedingString = String.valueOf(parent.getItemAtPosition(position));
                whenstopbreastfeedingInt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ngoinfantfeed = (Spinner) findViewById(R.id.ngoinfantfeed);

        List<String> Listngoinfantfeed = new ArrayList<String>();
        Listngoinfantfeed.add("--Select Options--");
        Listngoinfantfeed.add("Yes");
        Listngoinfantfeed.add("No");

        ArrayAdapter<String> dataAdapterngoinfantfeed = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_counseledNGOASHA));
        dataAdapterngoinfantfeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ngoinfantfeed.setAdapter(dataAdapterngoinfantfeed);

        ngoinfantfeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngoinfantfeedString = String.valueOf(parent.getItemAtPosition(position));
                ngoinfantfeedint = position;

                if(ngoinfantfeedint==1){
                    ngoinfantfeedString="Y";
                }
                if(ngoinfantfeedint==2){
                    ngoinfantfeedString="N";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        childtobreast = (Spinner) findViewById(R.id.childtobreast);

        List<String> Listchildtobreast = new ArrayList<String>();
        Listchildtobreast.add("--Select Options--");
        Listchildtobreast.add("Within one hour of birth");
        Listchildtobreast.add("Within seven hours of birth");
        Listchildtobreast.add("Within first 24 hours of birth");
        Listchildtobreast.add("Between 1-3 days after birth");
        Listchildtobreast.add("Between 3-7 days after birth");
        Listchildtobreast.add("After 7 days from birth ");
        ArrayAdapter<String> dataAdapterchildtobreast = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_childtobreastlm));
        dataAdapterchildtobreast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childtobreast.setAdapter(dataAdapterchildtobreast);


        childtobreast.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childtobreastString = String.valueOf(parent.getItemAtPosition(position));
                childtobreastint = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        khees = (Spinner) findViewById(R.id.khees);

        List<String> Listkhees = new ArrayList<String>();
        Listkhees.add("--Select Options--");
        Listkhees.add("Yes");
        Listkhees.add("No");

        ArrayAdapter<String> dataAdapterListkhees = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_kheeslm));
        dataAdapterListkhees.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        khees.setAdapter(dataAdapterListkhees);

        khees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kheesString = String.valueOf(parent.getItemAtPosition(position));
                kheesInt =position;


                if(kheesInt==1){
                    kheesString="Y";
                }
                if(kheesInt==2){
                    kheesString="N";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        otherfoodbeforebreastfeed = (Spinner) findViewById(R.id.otherfoodbeforebreastfeed);
        List<String> Listotherfoodbeforebreastfeed = new ArrayList<String>();
        Listotherfoodbeforebreastfeed.add("--Select Options--");
        Listotherfoodbeforebreastfeed.add("Yes");
        Listotherfoodbeforebreastfeed.add("No");
        ArrayAdapter<String> dataAdapterotherfoodbeforebreastfeed = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_otherfood));
        dataAdapterotherfoodbeforebreastfeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        otherfoodbeforebreastfeed.setAdapter(dataAdapterotherfoodbeforebreastfeed);

        otherfoodbeforebreastfeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                otherfoodbeforebreastfeedString = String.valueOf(parent.getItemAtPosition(position));
                otherfoodbeforebreastfeedint = position;


                if(otherfoodbeforebreastfeedint==1){
                    otherfoodbeforebreastfeedString="Y";
                }
                if(otherfoodbeforebreastfeedint==2){
                    otherfoodbeforebreastfeedString="N";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        liquidotherthenbreastfeeding = (Spinner) findViewById(R.id.liquidotherthenbreastfeeding);
        List<String> Lisliquidotherthenbreastfeeding = new ArrayList<String>();
        Lisliquidotherthenbreastfeeding.add("--Select Options--");
        Lisliquidotherthenbreastfeeding.add("Yes");
        Lisliquidotherthenbreastfeeding.add("No");

        ArrayAdapter<String> dataAdapterotherliquidotherthenbreastfeeding = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_liquidfood));
        dataAdapterotherliquidotherthenbreastfeeding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liquidotherthenbreastfeeding.setAdapter(dataAdapterotherliquidotherthenbreastfeeding);

        liquidotherthenbreastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                liuidotehrbreastfreddingString=String.valueOf(parent.getItemAtPosition(position));
                liuidotehrbreastfreddingInt = position;
                if(liuidotehrbreastfreddingInt==1){
                    liuidotehrbreastfreddingString="Y";
                }
                if(liuidotehrbreastfreddingInt==2){
                    liuidotehrbreastfreddingString="N";
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ngoownfood = (Spinner) findViewById(R.id.ngoownfood);
        List<String> Listngoownfood = new ArrayList<String>();
        Listngoownfood.add("--Select Options--");
        Listngoownfood.add("Yes");
        Listngoownfood.add("No");


        ArrayAdapter<String> dataAdapterListngoownfood = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_ongoingfood));
        dataAdapterListngoownfood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ngoownfood.setAdapter(dataAdapterListngoownfood);

        ngoownfood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngoownfoodString = String.valueOf(parent.getItemAtPosition(position));
                ngoownfoodint = position;

                if(ngoownfoodint==1){
                    ngoownfoodString="Y";
                }
                if(ngoownfoodint==2){
                    ngoownfoodString="N";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        householdexpence = (Spinner) findViewById(R.id.householdexpence);
        List<String> Listhouseholdexpence = new ArrayList<String>();
        Listhouseholdexpence.add("--Select Options--");
        Listhouseholdexpence.add("Less than Rs. 500 per month");
        Listhouseholdexpence.add("Rs. 500- 1,000 per month");
        Listhouseholdexpence.add("Rs. 1000 – 2000 per month");
        Listhouseholdexpence.add("Rs. 2000 – 4000 per month ");
        Listhouseholdexpence.add("Rs. 4000 – 5,000 per month");
        Listhouseholdexpence.add("Rs. 5,000 and above ");
        ArrayAdapter<String> dataAdapterhouseholdexpence = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_expencelm));
        dataAdapterhouseholdexpence.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        householdexpence.setAdapter(dataAdapterhouseholdexpence);

        householdexpence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                householdexpenceString = String.valueOf(parent.getItemAtPosition(position));
                householdexpenceint = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        semisolidfoodtochild = (Spinner) findViewById(R.id.semisolidfoodtochild);

        List<String> Listsemisolidfoodtochild = new ArrayList<String>();
        Listsemisolidfoodtochild.add("--Select Options--");
        Listsemisolidfoodtochild.add("Yes");
        Listsemisolidfoodtochild.add("No");

        ArrayAdapter<String> dataAdaptesemisolidfoodtochild = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_semisolidfood));
        dataAdaptesemisolidfoodtochild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semisolidfoodtochild.setAdapter(dataAdaptesemisolidfoodtochild);

        semisolidfoodtochild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semisolidfoodtochildString = String.valueOf(parent.getItemAtPosition(position));
                semisolidfoodtochildint = position;

                if(semisolidfoodtochildint==1){
                    semisolidfoodtochildString="Y";
                    solidfoodmonthtext.setVisibility(View.VISIBLE);
                    solidfoodmonth.setVisibility(View.VISIBLE);
                }
                if(semisolidfoodtochildint==2){
                    semisolidfoodtochildString="N";
                    solidfoodmonthtext.setVisibility(View.GONE);
                    solidfoodmonth.setVisibility(View.GONE);
                    solidfoodmonth.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwActive_subStage="LM1";
                clearAllForm();
                formVisibleInvible(pwActive_subStage);
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                relativeHeight.setVisibility(View.GONE);
                linearChildForm.setVisibility(View.GONE);
                pTod.setVisibility(View.INVISIBLE);
//                zerothree.setVisibility(View.VISIBLE);
//                threesix.setVisibility(View.GONE);
//                six.setVisibility(View.GONE);

                stageDataByLMID(getIntent().getStringExtra("memberId"),pwActive_subStage);

            }
        });

        lm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="LM2";
                clearAllForm();
                formVisibleInvible(pwActive_subStage);
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                relativeHeight.setVisibility(View.GONE);
                linearChildForm.setVisibility(View.GONE);
                pTod.setVisibility(View.INVISIBLE);
//                zerothree.setVisibility(View.VISIBLE);
//                threesix.setVisibility(View.VISIBLE);
//                six.setVisibility(View.GONE);

                stageDataByLMID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });

        lm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="LM3";
                clearAllForm();
                formVisibleInvible(pwActive_subStage);
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                relativeHeight.setVisibility(View.VISIBLE);
                linearChildForm.setVisibility(View.VISIBLE);
                pTod.setVisibility(View.INVISIBLE);
//                zerothree.setVisibility(View.GONE);
//                threesix.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);

                stageDataByLMID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });


        a_no = (EditText) findViewById(R.id.a_no);
        b_no = (EditText) findViewById(R.id.b_no);
        c_no = (EditText) findViewById(R.id.c_no);
        d_no = (EditText) findViewById(R.id.d_no);
        e_no = (EditText) findViewById(R.id.e_no);
        f_no = (EditText) findViewById(R.id.f_no);
        g_no = (EditText) findViewById(R.id.g_no);
        h_no = (EditText) findViewById(R.id.h_no);
        i_no = (EditText) findViewById(R.id.i_no);
        j_no = (EditText) findViewById(R.id.j_no);
        k_no = (EditText) findViewById(R.id.k_no);
        l_no = (EditText) findViewById(R.id.l_no);
        m_no = (EditText) findViewById(R.id.m_no);


        ca_no = (EditText) findViewById(R.id.ca_no);
        cb_no = (EditText) findViewById(R.id.cb_no);
        cc_no = (EditText) findViewById(R.id.cc_no);
        cd_no = (EditText) findViewById(R.id.cd_no);
        ce_no = (EditText) findViewById(R.id.ce_no);
        cf_no = (EditText) findViewById(R.id.cf_no);
        cg_no = (EditText) findViewById(R.id.cg_no);
        ch_no = (EditText) findViewById(R.id.ch_no);
        ci_no = (EditText) findViewById(R.id.ci_no);
        cj_no = (EditText) findViewById(R.id.cj_no);
        ck_no = (EditText) findViewById(R.id.ck_no);
        cl_no = (EditText) findViewById(R.id.cl_no);
        cm_no = (EditText) findViewById(R.id.cm_no);


        a_food = (CheckBox) findViewById(R.id.a_food);
        a_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (a_food.isChecked()) {
//                                                      a_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      a_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        b_food = (CheckBox) findViewById(R.id.b_food);
        b_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (b_food.isChecked()) {
//                                                      b_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      b_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        c_food = (CheckBox) findViewById(R.id.c_food);
        c_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (c_food.isChecked()) {
//                                                      c_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      c_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        d_food = (CheckBox) findViewById(R.id.d_food);
        d_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (d_food.isChecked()) {
//                                                      d_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      d_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        e_food = (CheckBox) findViewById(R.id.e_food);
        e_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (e_food.isChecked()) {
//                                                      e_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      e_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        f_food = (CheckBox) findViewById(R.id.f_food);
        f_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (f_food.isChecked()) {
//                                                      f_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      f_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        g_food = (CheckBox) findViewById(R.id.g_food);
        g_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (g_food.isChecked()) {
//                                                      g_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      g_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        h_food = (CheckBox) findViewById(R.id.h_food);
        h_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (h_food.isChecked()) {
//                                                      h_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      h_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        i_food = (CheckBox) findViewById(R.id.i_food);
        i_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (i_food.isChecked()) {
//                                                      i_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      i_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        j_food = (CheckBox) findViewById(R.id.j_food);
        j_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (j_food.isChecked()) {
//                                                      j_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      j_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        k_food = (CheckBox) findViewById(R.id.k_food);
        k_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (k_food.isChecked()) {
//                                                      k_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      k_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        l_food = (CheckBox) findViewById(R.id.l_food);
        l_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (l_food.isChecked()) {
//                                                      l_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      l_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        m_food = (CheckBox) findViewById(R.id.m_food);
        m_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (m_food.isChecked()) {
//                                                      m_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      m_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );


        ca_food = (CheckBox) findViewById(R.id.ca_food);
        ca_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (ca_food.isChecked()) {
                                                      ca_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      ca_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cb_food = (CheckBox) findViewById(R.id.cb_food);
        cb_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cb_food.isChecked()) {
                                                      cb_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cb_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cc_food = (CheckBox) findViewById(R.id.cc_food);
        cc_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cc_food.isChecked()) {
                                                      cc_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cc_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cd_food = (CheckBox) findViewById(R.id.cd_food);
        cd_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cd_food.isChecked()) {
                                                      cd_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cd_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        ce_food = (CheckBox) findViewById(R.id.ce_food);
        ce_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (ce_food.isChecked()) {
                                                      ce_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      ce_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cf_food = (CheckBox) findViewById(R.id.cf_food);
        cf_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cf_food.isChecked()) {
                                                      cf_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cf_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cg_food = (CheckBox) findViewById(R.id.cg_food);
        cg_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cg_food.isChecked()) {
                                                      cg_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cg_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        ch_food = (CheckBox) findViewById(R.id.ch_food);
        ch_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (ch_food.isChecked()) {
                                                      ch_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      ch_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        ci_food = (CheckBox) findViewById(R.id.ci_food);
        ci_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (ci_food.isChecked()) {
                                                      ci_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      ci_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cj_food = (CheckBox) findViewById(R.id.cj_food);
        cj_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cj_food.isChecked()) {
                                                      cj_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cj_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        ck_food = (CheckBox) findViewById(R.id.ck_food);
        ck_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (ck_food.isChecked()) {
                                                      ck_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      ck_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cl_food = (CheckBox) findViewById(R.id.cl_food);
        cl_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cl_food.isChecked()) {
                                                      cl_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cl_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        cm_food = (CheckBox) findViewById(R.id.cm_food);
        cm_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (cm_food.isChecked()) {
                                                      cm_no.setVisibility(View.VISIBLE);
                                                  } else {
                                                      cm_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );




        relativessbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sscclickedForm();

            }
        });
        relativeDiversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diversityClickForm();
            }
        });

        relativeHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightPregnentForm();
            }
        });

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pwActive_subStage.equalsIgnoreCase("ptod")){
                    String pregnetQuery1,pregnetQuery2;
                    if(pregnetNumnber.getText().toString().length()==0){
                        Toast.makeText(LM_actvity.this, "Enter Pregnant Number", Toast.LENGTH_SHORT).show();
                    }
                    else if(lmpdate.getText().toString().length()==0){
                        Toast.makeText(LM_actvity.this, "Select Lmp Date", Toast.LENGTH_SHORT).show();
                    }else {

                        Log.d("checkPregnantID",pregnentId);
                        if (pregnentId.equalsIgnoreCase("")) {
                            pregnentId = generatePregnentID(getIntent().getStringExtra("motherId"));
                            pregnetQuery1 = "insert into pregnant (PREGNANCY_ID,MEMBERS_ID,ORDER_OF_PREGNANCY,LMP_DATE,IS_ACTIVE,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) values ('" + pregnentId + "','" + getIntent().getStringExtra("motherId") + "','" + pregnetNumnber.getText().toString() + "','" + lmpdate.getText().toString() + "','Y','" + session.getSurveyorId() + "','" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "','M','','N')";
                            pregnetQuery2 = "update memberbasic set status = 'PW', stage = 'PW' where Members_id='" + getIntent().getStringExtra("motherId") + "'";
                        } else {

//                        String pregnetQuery1="insert into pregnant (PREGNANCY_ID,MEMBERS_ID,ORDER_OF_PREGNANCY,LMP_DATE,IS_ACTIVE,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) value('"+pregnentId+"','"+getIntent().getStringExtra("motherId")+"','"+pregnetNumnber.getText().toString()+"','"+lmpdate.getText().toString()+"','Y','"+session.getSurveyorId()+"','"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) +"','M','','N')";
                            pregnetQuery2 = "update memberbasic set status = 'PW', stage = 'PW' where Members_id='" + getIntent().getStringExtra("motherId") + "'";
                            pregnetQuery1 = "update pregnant set PREGNANCY_ID='" + pregnentId + "',MEMBERS_ID ='" + getIntent().getStringExtra("motherId") + "',ORDER_OF_PREGNANCY ='" + pregnetNumnber.getText().toString() + "'," +
                                    " lmp_date='" + lmpdate.getText().toString() + "', is_active='Y', SURVEYOR_ID='" + session.getSurveyorId() + "',TIME_STAMP='" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)";

                        }

                        executeQuery(pregnetQuery1);
                        executeQuery(pregnetQuery2);

                        Toast.makeText(LM_actvity.this, "Record Upated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intentBeneficiary = new Intent(getApplicationContext(),DashBoard.class);
                        startActivity(intentBeneficiary);
                        finish();
                    }

                }

                 else if (pwActive_subStage.equalsIgnoreCase("na")){

                    String valueRadiobutton=radiobuttonGrp();
                    if(!valueRadiobutton.equalsIgnoreCase("notSelected")){
                        String Query="";
                        switch (valueRadiobutton){

                            case "MD":
                                Query = "update child_tracking set na_reason_mother=1,na_reason_child=null,na_reason_both=null,IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId") +"' and sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"'";
break;
                            case "MM":
                             Query = "update child_tracking set na_reason_mother=2,na_reason_child=null,na_reason_both=null,IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId") +"' and sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"'";

                                break;
                            case "CD":

                             Query = "update child_tracking set is_available=0, na_reason_mother=null,na_reason_child=1,na_reason_both=null,IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId") +"' and sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"'";
                                break;
                            case "CM":
                                Query = "update child_tracking set is_available=0, na_reason_mother=null,na_reason_child=2,na_reason_both=null,IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId") +"' and sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"'";
                                break;
                            case "BD":
                                Query = "update child_tracking set is_available=0, na_reason_mother=null,na_reason_child=null,na_reason_both=1,IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId") +"' and sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"'";
                                break;
                            case "BM":
                                Query = "update child_tracking set is_available=0, na_reason_mother=null,na_reason_child=null,na_reason_both=2,IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId") +"' and sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"'";

break;
                        }

                        SQLiteDatabase dQuery= openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor cQuery = dQuery.rawQuery(Query, null);

                         cQuery.moveToFirst();
                         cQuery.close();

if(valueRadiobutton.equalsIgnoreCase("CD") || valueRadiobutton.equalsIgnoreCase("CM") || valueRadiobutton.equalsIgnoreCase("BD")||valueRadiobutton.equalsIgnoreCase("BM")){
    String QueryCDBM="update memberbasic set doexit='"+ new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())+"', is_to_track='N',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+getIntent().getStringExtra("memberId")+"'";
    SQLiteDatabase dQuery1= openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
    Cursor cQuery1 = dQuery1.rawQuery(QueryCDBM, null);
    cQuery1.moveToFirst();
    cQuery1.close();


}
                        Toast.makeText(LM_actvity.this, "Record Update Successfully", Toast.LENGTH_SHORT).show();

                        Intent intetnBeneficiray = new Intent(getApplicationContext(),DashBoard.class);
                        startActivity(intetnBeneficiray);
                        finish();

                    }
                    else{
                        Toast.makeText(LM_actvity.this, "Select Non Availibility Option", Toast.LENGTH_SHORT).show();
                    }
                }
else {

                    errorCode = IYCFValidation(pwActive_subStage);

                    Log.d("LM_Activity", errorCode);
                    if (errorCode.equalsIgnoreCase("0")) {

                        dietSelection();
                        String inseertLMTrack="";
                        if (subStageMode.equalsIgnoreCase("add")) {
                            if(pwActive_subStage.equalsIgnoreCase("LM3")) {


                                inseertLMTrack = "INSERT INTO CHILD_TRACKING (MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,CURRENTLY_BF," +
                                        "IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_HEIGHT," +
                                        "CHILD_WEIGHT,CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) " +
                                        " VALUES ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "',1,'" + breastfeedingStrung + "'," +
                                        "'" + ngoinfantfeedString + "','" + ngoownfoodString + "','" + liuidotehrbreastfreddingString + "'," +
                                        "'" + semisolidfoodtochildString + "'," + householdexpenceint + ",'" + height.getText().toString() + "'," +
                                        "'" + weight.getText().toString() + "','" + muac.getText().toString() + "'," + session.getSurveyorId() + ",'" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "','M','','N')";

                                Log.d("insertQuery", inseertLMTrack);

                            }     else{
                                inseertLMTrack = "INSERT INTO CHILD_TRACKING (MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,CURRENTLY_BF," +
                                        "IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,SPEND_ON_FOOD,CHILD_HEIGHT," +
                                        "CHILD_WEIGHT,CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) " +
                                        " VALUES ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "',1,'" + breastfeedingStrung + "'," +
                                        "'" + ngoinfantfeedString + "','" + ngoownfoodString + "','" + liuidotehrbreastfreddingString + "'," + householdexpenceint + ",'" + height.getText().toString() + "'," +
                                        "'" + weight.getText().toString() + "','" + muac.getText().toString() + "'," + session.getSurveyorId() + ",'" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "','M','','N')";

                            }
                            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor c = dbs.rawQuery(inseertLMTrack, null);

                            c.moveToFirst();
//                    total = c.getCount();  int
                            c.close();


                            String motherdeitInsert = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                    " ('" + getIntent().getStringExtra("motherId") + "','LM','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
                                    "'," + bn + ",'" + cf + "'," + cn + ",'" + df + "'," + dn + ",'" + ef + "'," +
                                    en + ",'" + ff + "'," + fn + ",'" + gf + "'," + gn + ",'" + hf + "'," + hn + ",'"
                                    + i_f + "'," + in + ",'" + jf + "'," + jn + ",'" + kf + "'," + kn + ",'" + lf + "'," +
                                    ln + ",'" + mf + "'," + mn + ",'M','N','','')";

//                        Log.d("InsertQuerydiet", motherdeitInsert);
                            Cursor cinsert = dbs.rawQuery(motherdeitInsert, null);

                            if (cinsert.moveToFirst()) {

                            }
//                    total = c.getCount();  int
                            cinsert.close();


                            if (pwActive_subStage.equalsIgnoreCase("LM3")) {



                                String childdeitInsert = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                        " ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "','" + caf + "'," + can + ",'" + cbf +
                                        "'," + cbn + ",'" + ccf + "'," + ccn + ",'" + cdf + "'," + cdn + ",'" + cef + "'," +
                                        cen + ",'" + cff + "'," + cfn + ",'" + cgf + "'," + cgn + ",'" + chf + "'," + chn + ",'"
                                        + ci_f + "'," + cin + ",'" + cjf + "'," + cjn + ",'" + ckf + "'," + ckn + ",'" + clf + "'," +
                                        cln + ",'" + cmf + "'," + cmn + ",'M','N','','')";

                                Cursor cinsertchild = dbs.rawQuery(childdeitInsert, null);

                                Log.d("LM3Print",childdeitInsert);

                                if (cinsertchild.moveToFirst()) {

                                }
//                    total = c.getCount();  int
                                cinsertchild.close();

                            }


//                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);


                            String updateSub_StageInMemberBasic = "update memberbasic set IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end), sub_stage='" + getIntent().getStringExtra("current_sub_stage") + "' WHERE MEMBERS_ID='" + getIntent().getStringExtra("memberId") + "'";
                            SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor c3 = dsbs.rawQuery(updateSub_StageInMemberBasic, null);

                            if (c3.moveToFirst()) {

                            }
//                    c3.moveToFirst();
                            c3.close();

                            String updateChildExtra;
                            int stopBrestFeading = 0;
                            if (breastfeedingStrung.equalsIgnoreCase("n")) {
                                stopBrestFeading = whenstopbreastfeedingInt;
                            }

//                            Log.d("updateLM3", semisolidfoodtochildString);
                            if (!pwActive_subStage.equalsIgnoreCase("LM3")) {

                                if(khees.getVisibility()==View.VISIBLE) {
                                    updateChildExtra = "  UPDATE CHILDEXTRA " +
                                            "SET WHEN_FIRST_BF = " + childtobreastint + ",IF_FEED_KHEES = '" + kheesString + "',CURRENTLY_BF = '" + breastfeedingStrung + "', WHEN_STOP_BF = " + stopBrestFeading + ", ANYTHING_BEFORE_BF = '" + liuidotehrbreastfreddingString + "'," +
                                            "IS_APPROVED = ''," +
                                            "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                                            "WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";
                                }else{

                                    updateChildExtra = "  UPDATE CHILDEXTRA " +
                                            "SET WHEN_FIRST_BF = " + childtobreastint + ",CURRENTLY_BF = '" + breastfeedingStrung + "', WHEN_STOP_BF = " + stopBrestFeading + ", ANYTHING_BEFORE_BF = '" + liuidotehrbreastfreddingString + "'," +
                                            "IS_APPROVED = ''," +
                                            "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                                            "WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";

                                }

                            } else {


                                updateChildExtra = "  UPDATE CHILDEXTRA " +
                                        "SET CURRENTLY_BF = '" + breastfeedingStrung + "', WHEN_STOP_BF = " + stopBrestFeading + ",IF_STARTED_SOLID_FOOD = '" + semisolidfoodtochildString + "',WHICH_MONTH_SOLID_FOOD = '" + solidfoodmonth.getText().toString() + "'," +
                                        "IS_APPROVED = ''," +
                                        "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";
                            }

                            SQLiteDatabase dsUpdate = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor cdsUpdate = dsUpdate.rawQuery(updateChildExtra, null);
                            Log.d("updateLM3", updateChildExtra);

                            cdsUpdate.moveToFirst();
                            cdsUpdate.close();

                            Toast.makeText(LM_actvity.this, "Record Saved Succesfully", Toast.LENGTH_SHORT).show();

                            subStageMode = "edit";

                        } else if (subStageMode.equalsIgnoreCase("edit")) {
                            String updateTrackingPw="";
                            Log.d("BreastFeed", householdexpenceint + "");
                            if(pwActive_subStage.equalsIgnoreCase("LM3")) {
                                updateTrackingPw = "UPDATE CHILD_TRACKING SET CURRENTLY_BF = '" + breastfeedingStrung + "',IF_COUNSEL_ON_FEED_INFANT = '" + ngoinfantfeedString + "',IF_COUNSEL_ON_SELFFEED = '" + ngoownfoodString + "',LIQUID_OTHER_THAN_BF = '" + liuidotehrbreastfreddingString + "'," +
                                        "IF_STARTED_SOLID_FOOD = '" + semisolidfoodtochildString + "',SPEND_ON_FOOD = " + householdexpenceint + ",CHILD_HEIGHT = '" + height.getText().toString() + "',CHILD_WEIGHT = '" + weight.getText().toString() + "',CHILD_MUAC = '" + muac.getText().toString() + "'," +
                                        "SURVEYOR_ID = " + session.getSurveyorId() + ", TIME_STAMP = '" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "',SOURCE = 'M',IS_APPROVED = '',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                            }
                            else{
                                updateTrackingPw = "UPDATE CHILD_TRACKING SET CURRENTLY_BF = '" + breastfeedingStrung + "',IF_COUNSEL_ON_FEED_INFANT = '" + ngoinfantfeedString + "',IF_COUNSEL_ON_SELFFEED = '" + ngoownfoodString + "',LIQUID_OTHER_THAN_BF = '" + liuidotehrbreastfreddingString + "'," +
                                        "SPEND_ON_FOOD = " + householdexpenceint + ",CHILD_HEIGHT = '" + height.getText().toString() + "',CHILD_WEIGHT = '" + weight.getText().toString() + "',CHILD_MUAC = '" + muac.getText().toString() + "'," +
                                        "SURVEYOR_ID = " + session.getSurveyorId() + ", TIME_STAMP = '" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "',SOURCE = 'M',IS_APPROVED = '',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "' AND SUB_STAGE = '" + pwActive_subStage + "'";

                            }

                            SQLiteDatabase dsbss = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor updatedtracking = dsbss.rawQuery(updateTrackingPw, null);
                            updatedtracking.moveToFirst();
//                            dsbss.close();
                            String checkrecordInDiet = " Select * from diet  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("motherId") +
                                    "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                            String updateDiet;
                            Cursor cudiet = dsbss.rawQuery(checkrecordInDiet, null);
                            cudiet.moveToFirst();
                            if (cudiet.getCount() >= 1) {

                                updateDiet = "UPDATE DIET SET SUB_STAGE = '" + pwActive_subStage + "', FEED_A = '" + af + "',FEED_A_NOS = "
                                        + an + ",FEED_B = '" + bf + "',FEED_B_NOS = " + bn + ",FEED_C = '" + cf + "',FEED_C_NOS = " + cn + ",FEED_D = '"
                                        + df + "', FEED_D_NOS = " + dn + ", FEED_E = '" + ef + "', FEED_E_NOS =" + en + ",FEED_F = '" + ff + "'," +
                                        " FEED_F_NOS = " + fn + ", FEED_G = '" + gf + "', FEED_G_NOS = " + gn + ",FEED_H = '" + hf + "', " +
                                        "FEED_H_NOS = " + hn + ",FEED_I = '" + i_f + "',FEED_I_NOS = " + in + ",FEED_J = '" + jf + "'," +
                                        "FEED_J_NOS = " + jn + ",FEED_K = '" + kf + "', FEED_K_NOS = " + kn + ",FEED_L = '" + lf + "',FEED_L_NOS = " +
                                        "" + ln + ", FEED_M = '" + mf + "',   FEED_M_NOS = " + mn + ",IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("motherId") +
                                        "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                            } else {

                                updateDiet = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                        " ('" + getIntent().getStringExtra("motherId") + "','LM','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
                                        "'," + bn + ",'" + cf + "'," + cn + ",'" + df + "'," + dn + ",'" + ef + "'," +
                                        en + ",'" + ff + "'," + fn + ",'" + gf + "'," + gn + ",'" + hf + "'," + hn + ",'"
                                        + i_f + "'," + in + ",'" + jf + "'," + jn + ",'" + kf + "'," + kn + ",'" + lf + "'," +
                                        ln + ",'" + mf + "'," + mn + ",'M','N','','')";
                            }
                            SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Log.d("updatedDiet", updateDiet);


                            Cursor updatedDiet = dsbs.rawQuery(updateDiet, null);
                            updatedDiet.moveToFirst();
                            dsbs.close();



                            if (pwActive_subStage.equalsIgnoreCase("LM3")) {
                                String checkrecordInDietChild = " Select * from diet  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") +
                                        "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                                String updateDietChild;
                                Cursor cudietChild = dsbss.rawQuery(checkrecordInDietChild, null);
                                cudiet.moveToFirst();
                                if (cudiet.getCount() >= 1) {

                                    updateDietChild = "UPDATE DIET SET SUB_STAGE = '" + pwActive_subStage + "', FEED_A = '" + caf + "',FEED_A_NOS = "
                                            + can + ",FEED_B = '" + cbf + "',FEED_B_NOS = " + cbn + ",FEED_C = '" + ccf + "',FEED_C_NOS = " + ccn + ",FEED_D = '"
                                            + cdf + "', FEED_D_NOS = " + cdn + ", FEED_E = '" + cef + "', FEED_E_NOS =" + cen + ",FEED_F = '" + cff + "'," +
                                            " FEED_F_NOS = " + cfn + ", FEED_G = '" + cgf + "', FEED_G_NOS = " + cgn + ",FEED_H = '" + chf + "', " +
                                            "FEED_H_NOS = " + chn + ",FEED_I = '" + ci_f + "',FEED_I_NOS = " + cin + ",FEED_J = '" + cjf + "'," +
                                            "FEED_J_NOS = " + cjn + ",FEED_K = '" + ckf + "', FEED_K_NOS = " + ckn + ",FEED_L = '" + clf + "',FEED_L_NOS = " +
                                            "" + cln + ", FEED_M = '" + cmf + "',   FEED_M_NOS = " + cmn + ",IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") +
                                            "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                                } else {
                                    updateDietChild = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                            " ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "','" + caf + "'," + can + ",'" + cbf +
                                            "'," + cbn + ",'" + ccf + "'," + ccn + ",'" + cdf + "'," + cdn + ",'" + cef + "'," +
                                            cen + ",'" + cff + "'," + cfn + ",'" + cgf + "'," + cgn + ",'" + chf + "'," + chn + ",'"
                                            + ci_f + "'," + cin + ",'" + cjf + "'," + cjn + ",'" + ckf + "'," + ckn + ",'" + clf + "'," +
                                            cln + ",'" + cmf + "'," + cmn + ",'M','N','','')";
                                }
                                SQLiteDatabase dsbschil = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                                Log.d("updatedDiet", updateDiet);


                                Cursor updatedDietchil = dsbschil.rawQuery(updateDietChild, null);
                                updatedDietchil.moveToFirst();
                                dsbschil.close();

                            }


                            String updateChildExtra;
                            int stopBrestFeading = 0;
                            if (breastfeedingInte == 2) {
                                stopBrestFeading = whenstopbreastfeedingInt;
                            }
                            if (!pwActive_subStage.equalsIgnoreCase("LM3")) {


                                if(khees.getVisibility()==View.VISIBLE) {
                                    Log.d("CheckUpdate", liuidotehrbreastfreddingString);

                                    updateChildExtra = "  UPDATE CHILDEXTRA " +
                                            "SET WHEN_FIRST_BF = " + childtobreastint + ",IF_FEED_KHEES = '" + kheesString + "',CURRENTLY_BF = '" + breastfeedingStrung + "', WHEN_STOP_BF = " + stopBrestFeading + ", ANYTHING_BEFORE_BF = '" + liuidotehrbreastfreddingString + "'," +
                                            "IS_APPROVED = ''," +
                                            "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                                            "WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";

                                }else{
                                    updateChildExtra = "  UPDATE CHILDEXTRA " +
                                            "SET WHEN_FIRST_BF = " + childtobreastint + ",CURRENTLY_BF = '" + breastfeedingStrung + "', WHEN_STOP_BF = " + stopBrestFeading + ", ANYTHING_BEFORE_BF = '" + liuidotehrbreastfreddingString + "'," +
                                            "IS_APPROVED = ''," +
                                            "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                                            "WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";

                                }
                            } else {

                                Log.d("CheckUpdate", "Ranjeet else part");

                                updateChildExtra = "  UPDATE CHILDEXTRA " +
                                        "SET CURRENTLY_BF = '" + breastfeedingStrung + "', WHEN_STOP_BF = " + stopBrestFeading + ", IF_STARTED_SOLID_FOOD = '" + semisolidfoodtochildString + "',WHICH_MONTH_SOLID_FOOD = '" + solidfoodmonth.getText().toString() + "'," +
                                        "IS_APPROVED = ''," +
                                        "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";
                            }

                            SQLiteDatabase dsUpdate = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor cdsUpdate = dsUpdate.rawQuery(updateChildExtra, null);
                            cdsUpdate.moveToFirst();
                            cdsUpdate.close();

                            Toast.makeText(LM_actvity.this, "Edited Successfully", Toast.LENGTH_SHORT).show();


                        }
                Intent i = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(i);
                finish();
                    }

                }

            }
        });



        setButtonStage(getIntent().getStringExtra("memberId"), getIntent().getStringExtra("current_sub_stage"));
              checkWidgetvisibility();
        stageDataByLMID(getIntent().getStringExtra("memberId"),pwActive_subStage);


    }


    public void sscclickedForm() {

//        Log.d("Validation","inserted inside fucntion");

        sbcclayout.setVisibility(View.VISIBLE);
        dietaryLyout.setVisibility(View.GONE);
        heightWeightLayout.setVisibility(View.GONE);

        basicclick.setVisibility(View.VISIBLE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("BASIC");

    }

    public void diversityClickForm() {

//        activatedREgistrationForm=2;
        sbcclayout.setVisibility(View.GONE);
        dietaryLyout.setVisibility(View.VISIBLE);
        heightWeightLayout.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.VISIBLE);
        childclick.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("PREGNANT");


    }


    public void heightPregnentForm() {


//        activatedREgistrationForm=3;
        heightWeightLayout.setVisibility(View.VISIBLE);
        sbcclayout.setVisibility(View.GONE);
        dietaryLyout.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.VISIBLE);
//        getSupportActionBar().setTitle("CHILD");
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        dialogCoupon = new Dialog(LM_actvity.this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.exit_dialog);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);
//        setFinishOnTouchOutside(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        Button yes = (Button) dialogCoupon.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                dialogCoupon.dismiss();
                Intent intentback = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(intentback);
                finish();
            }
        });
        Button no = (Button) dialogCoupon.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCoupon.hide();
            }
        });

        dialogCoupon.show();


    }

    public void IYCFVAlidation() {


    }


    public void setButtonStage(String memberID, String substage) {

        String buttonStage = "select members_id,stage,sub_stage from child_tracking where members_id='" + memberID + "'";
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);



        Cursor c = dbs.rawQuery(buttonStage, null);
        Log.d("RanjeetLM_ACTIVITY",c.getCount()+""+buttonStage);
        if (c.getCount() >= 1) {



//        int i = 0;
            if (c.moveToFirst()) {
                do {
                    try {
//             arrayNoOfChild.add(c.getString(c.getColumnIndex("Members_id")));
                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("lm1")) {
                            lm1.setBackgroundResource(R.drawable.greencirclecolor);
                            lm1.setEnabled(true);

                        }

                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("lm2")) {
                            lm2.setBackgroundResource(R.drawable.greencirclecolor);
                            lm2.setEnabled(true);
                        }

                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("lm3")) {
                            lm3.setBackgroundResource(R.drawable.greencirclecolor);
                            lm3.setEnabled(true);
                            relativeHeight.setVisibility(View.VISIBLE);
                            linearChildForm.setVisibility(View.VISIBLE);
                        }

                    }catch (Exception e){

                    }
//
                } while (c.moveToNext());
            }

        }

        if(substage.equalsIgnoreCase("lm1")){
            lm1.setBackgroundResource(R.drawable.circlebutton);
            lm1.setEnabled(true);
            pwActive_subStage="LM1";

            formVisibleInvible(pwActive_subStage);
        }
        if(substage.equalsIgnoreCase("lm2"))
        {
            Log.d("pregnentStatus","inseretdr");
            lm2.setBackgroundResource(R.drawable.circlebutton);
            lm2.setEnabled(true);
            pwActive_subStage="LM2";
            formVisibleInvible(pwActive_subStage);
        }
        if(substage.equalsIgnoreCase("lm3"))
        {

            lm3.setBackgroundResource(R.drawable.circlebutton);
            lm3.setEnabled(true);
            pwActive_subStage="LM3";

            relativeHeight.setVisibility(View.VISIBLE);
            linearChildForm.setVisibility(View.VISIBLE);
            formVisibleInvible(pwActive_subStage);
        }
        try {

//            Toast.makeText(this, "insert into stagDAta by ", Toast.LENGTH_SHORT).show();
            stageDataByLMID(getIntent().getStringExtra("memberId"), pwActive_subStage);
        }catch (Exception e){

        }
    }


    public String IYCFValidation(String activeStage){
        String errror ="0";

        Log.d("LM_Activity",householdexpenceString);
if(breastfeeding.getVisibility()==View.VISIBLE) {
    if (errror == "0" && breastfeedingStrung.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select Breast Feeding", Toast.LENGTH_SHORT).show();
        errror = "1";
    }
}
            if (errror == "0" && textvisibilty.getVisibility() == View.VISIBLE) {

                if (errror == "0" && whenstopbreastfeedingString.equalsIgnoreCase("--Select Options--")) {
                    Toast.makeText(this, "Select Breast Feeding", Toast.LENGTH_SHORT).show();
                    errror = "1";
                }
            }

if(ngoinfantfeed.getVisibility()==View.VISIBLE) {
    if (errror == "0" && ngoinfantfeedString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select infant feeds Feeding", Toast.LENGTH_SHORT).show();
        errror = "1";

    }
}
if(childtobreast.getVisibility()==View.VISIBLE) {
    if (errror == "0" && childtobreastString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select Child to breast", Toast.LENGTH_SHORT).show();
        errror = "1";
    }
}

if(khees.getVisibility()==View.VISIBLE) {
    if (errror == "0" && kheesString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select Khees", Toast.LENGTH_SHORT).show();
        errror = "1";
    }
}

        if(liquidotherthenbreastfeeding.getVisibility()==View.VISIBLE) {
            if (errror == "0" && liuidotehrbreastfreddingString.equalsIgnoreCase("--Select Options--")) {
                Toast.makeText(this, "Select Khees", Toast.LENGTH_SHORT).show();
                errror = "1";
            }
        }



if(otherfoodbeforebreastfeed.getVisibility()==View.VISIBLE) {
    if (errror == "0" && otherfoodbeforebreastfeedString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select other food before breast", Toast.LENGTH_SHORT).show();
        errror = "1";
    }
}

if(ngoownfood.getVisibility()==View.VISIBLE) {
    if (errror == "0" && ngoownfoodString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select ngo own  Feeding", Toast.LENGTH_SHORT).show();
        errror = "1";
    }
}
if(householdexpence.getVisibility()==View.VISIBLE) {
    if (errror == "0" && householdexpenceString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select spend on food ", Toast.LENGTH_SHORT).show();
        errror = "1";
    }
}
if(semisolidfoodtochild.getVisibility()==View.VISIBLE) {
    if (errror == "0" && semisolidfoodtochildString.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select solid food Feeding", Toast.LENGTH_SHORT).show();
        errror = "1";
    }

}

if(solidfoodmonth.getVisibility()==View.VISIBLE){

    if(errror=="0" && solidfoodmonth.getText().length()==0){
        Toast.makeText(this, "Enter which Month started solid food", Toast.LENGTH_SHORT).show();
        errror="1";
    }
}
     if( a_no.getVisibility()==View.VISIBLE && a_no.getText().length()==0){

        a_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(b_no.getVisibility()==View.VISIBLE && b_no.getText().length()==0){
        b_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(c_no.getVisibility()==View.VISIBLE && c_no.getText().length()==0){
        c_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(d_no.getVisibility()==View.VISIBLE && d_no.getText().length()==0){
        d_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(e_no.getVisibility()==View.VISIBLE && e_no.getText().length()==0){
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(f_no.getVisibility()==View.VISIBLE && f_no.getText().length()==0){
        f_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(g_no.getVisibility()==View.VISIBLE && g_no.getText().length()==0){
        g_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(h_no.getVisibility()==View.VISIBLE && h_no.getText().length()==0){
        h_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(i_no.getVisibility()==View.VISIBLE && i_no.getText().length()==0){
        i_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(j_no.getVisibility()==View.VISIBLE && j_no.getText().length()==0){
        j_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(k_no.getVisibility()==View.VISIBLE && k_no.getText().length()==0){
        k_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }else if(l_no.getVisibility()==View.VISIBLE && l_no.getText().length()==0){
        l_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
    }
    else if(m_no.getVisibility()==View.VISIBLE && m_no.getText().length()==0){
        m_no.requestFocus();
        Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
         errror="1";
}

        if( ca_no.getVisibility()==View.VISIBLE && ca_no.getText().length()==0){

            ca_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cb_no.getVisibility()==View.VISIBLE && cb_no.getText().length()==0){
            cb_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cc_no.getVisibility()==View.VISIBLE && cc_no.getText().length()==0){
            cc_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cd_no.getVisibility()==View.VISIBLE && cd_no.getText().length()==0){
            cd_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(ce_no.getVisibility()==View.VISIBLE && ce_no.getText().length()==0){
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cf_no.getVisibility()==View.VISIBLE && cf_no.getText().length()==0){
            cf_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cg_no.getVisibility()==View.VISIBLE && cg_no.getText().length()==0){
            cg_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(ch_no.getVisibility()==View.VISIBLE && ch_no.getText().length()==0){
            ch_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(ci_no.getVisibility()==View.VISIBLE && ci_no.getText().length()==0){
            ci_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cj_no.getVisibility()==View.VISIBLE && cj_no.getText().length()==0){
            cj_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(ck_no.getVisibility()==View.VISIBLE && ck_no.getText().length()==0){
            ck_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }else if(cl_no.getVisibility()==View.VISIBLE && cl_no.getText().length()==0){
            cl_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }
        else if(cm_no.getVisibility()==View.VISIBLE && cm_no.getText().length()==0){
            cm_no.requestFocus();
            Toast.makeText(LM_actvity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errror="1";
        }

      if(relativeHeight.getVisibility()==View.VISIBLE) {
          if (height.getText().length() == 0) {

              height.requestFocus();
              Toast.makeText(LM_actvity.this, "Enter Height", Toast.LENGTH_SHORT).show();
              errror = "1";

          } else if (weight.getText().length() == 0) {
              Toast.makeText(LM_actvity.this, "Enter Weight", Toast.LENGTH_SHORT).show();
              errror = "1";
          } else if (muac.getText().length() == 0) {
              Toast.makeText(LM_actvity.this, "Enter MUAC ", Toast.LENGTH_SHORT).show();
              errror = "1";
          }

      }





return errror;
    }
//data binding
    public void stageDataByLMID(String memberId,String pwActive_subStage) {
        subStageMode = "add";
        save.setEnabled(true);
        save.setVisibility(View.VISIBLE);
        String query = "SELECT CURRENTLY_BF, IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_IMMUNIZATION_STATUS, CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS, T.IS_APPROVED from child_tracking T LEFT JOIN DIET D ON T.MEMBERS_ID=D.MEMBERS_ID  where T.MEMBERS_ID='" + memberId + "' AND T.SUB_STAGE='" + pwActive_subStage + "'";
//
        Log.d("LACtivty", query);
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
        Cursor c = dbs.rawQuery(query, null);
        int total = c.getCount();
//        Log.d("pregnentStatus",current_subStage+"");
//        Log.d("pregnentStatus",pregnancy_id+"");
        if (total >= 1) {

            subStageMode = "edit";

            Log.d("LACtivty", "" + total);


//        int i = 0;
            if (c.moveToFirst()) {
                do {
//
                    Log.d("CheckDate", "" + c.getString(c.getColumnIndex("currently_bf")));
                    breastfeeding.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("currently_bf"))));

                    Log.d("LA_ACTIVTY_VALUE", c.getString(c.getColumnIndex("currently_bf")));

//
                    try {

                        Log.d("Brestbeedogfristtime",when_stop_bf);
                        whenstopbreastfeeding.setSelection(Integer.parseInt(when_stop_bf));




//
                    }catch (Exception e){
                        Log.d("Brestbeedogfristtime",e.toString());
                    }
                    try {
//                        if (when_firstBf.length() > 0) {
                        childtobreast.setSelection(Integer.parseInt(when_firstBf));
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "3" + e.toString());

                    }


                    try {
//                        if (c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("Y")) {
                        ngoownfood.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_counsel_on_selffeed"))));
                        ngoinfantfeed.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_counsel_on_feed_infant"))));
//                        } else {
//                            ngoownfood.setSelection(2);
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "4" + e.toString());
                    }

                    Log.d("houseHold", c.getString(c.getColumnIndex("spend_on_food")) + "");
                    householdexpence.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("spend_on_food"))));

                    try {
//                        if (if_feed_khee.length() > 0) {

                        khees.setSelection(indexOfYesNo(if_feed_khee));
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "5" + e.toString());
                    }
                    try {

//                        if (anything_before_bf.length() > 0) {
                        liquidotherthenbreastfeeding.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("liquid_other_than_bf"))));
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "6" + e.toString());
                    }
try {
    semisolidfoodtochild.setSelection(indexOfYesNo(if_started_solid_food));
    solidfoodmonth.setText(WHICH_MONTH_SOLID_FOOD);
}catch (Exception e){

}
//
                    try {
                        height.setText(c.getString(c.getColumnIndex("child_height")));
                        weight.setText(c.getString(c.getColumnIndex("child_weight")));
                        muac.setText(c.getString(c.getColumnIndex("child_muac")));
                    } catch (Exception e) {
                        Log.d("LACtivty", "7" + e.toString());
                    }
//}try


                    try {
                        if (c.getString(c.getColumnIndex("feed_a")).equalsIgnoreCase("Y")) {
                            ca_food.setChecked(true);
                            ca_no.setVisibility(View.VISIBLE);

                            ca_no.setText(c.getString(c.getColumnIndex("feed_a_nos")));
                        } else {
                            ca_food.setChecked(false);
                            ca_no.setVisibility(View.INVISIBLE);
                            ca_no.setText("");
                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "8" + e.toString());
                    }

                    try {
                        if (c.getString(c.getColumnIndex("feed_b")).equalsIgnoreCase("Y")) {
                            Log.d("getFeed", c.getString(c.getColumnIndex("feed_b")));
                            Log.d("getFeed", c.getString(c.getColumnIndex("feed_b_nos")));
                            cb_food.setChecked(true);
                            cb_no.setVisibility(View.VISIBLE);
                            cb_no.setText(c.getString(c.getColumnIndex("feed_b_nos")));
                        } else {
                            cb_food.setChecked(false);
                            cb_no.setVisibility(View.INVISIBLE);
                            cb_no.setText("");
                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "9" + e.toString());

                    }

                    try {
                        if (c.getString(c.getColumnIndex("feed_c")).equalsIgnoreCase("Y")) {
                            cc_food.setChecked(true);
                            cc_no.setVisibility(View.VISIBLE);
                            cc_no.setText(c.getString(c.getColumnIndex("feed_c_nos")));
                        } else {
                            cc_food.setChecked(false);
                            cc_no.setVisibility(View.INVISIBLE);
                            cc_no.setText("");
                        }

                    } catch (Exception e) {

                        Log.d("LACtivty", "10" + e.toString());
                    }

                    try {
                        if (c.getString(c.getColumnIndex("feed_d")).equalsIgnoreCase("Y")) {
                            cd_food.setChecked(true);
                            cd_no.setVisibility(View.VISIBLE);
                            cd_no.setText(c.getString(c.getColumnIndex("feed_d_nos")));
                        } else {
                            cd_food.setChecked(false);
                            cd_no.setVisibility(View.INVISIBLE);
                            cd_no.setText("");
                        }

                    } catch (Exception e) {
                        Log.d("LACtivty", "11" + e.toString());

                    }
                    try {

                        if (c.getString(c.getColumnIndex("feed_e")).equalsIgnoreCase("Y")) {
                            ce_food.setChecked(true);
                            ce_no.setVisibility(View.VISIBLE);
                            ce_no.setText(c.getString(c.getColumnIndex("feed_e_nos")));
                        } else {
                            ce_food.setChecked(false);
                            ce_no.setVisibility(View.INVISIBLE);
                            ce_no.setText("");
                        }

                    } catch (Exception e) {

                        Log.d("LACtivty", "12" + e.toString());
                    }

                    try {
                        if (c.getString(c.getColumnIndex("feed_f")).equalsIgnoreCase("Y")) {
                            cf_food.setChecked(true);
                            cf_no.setVisibility(View.VISIBLE);
                            cf_no.setText(c.getString(c.getColumnIndex("feed_f_nos")));
                        } else {
                            cf_food.setChecked(false);
                            cf_no.setVisibility(View.INVISIBLE);
                            cf_no.setText("");
                        }

                    } catch (Exception e) {
                        Log.d("LACtivty", "13" + e.toString());

                    }

                    try {
                        if (c.getString(c.getColumnIndex("feed_g")).equalsIgnoreCase("Y")) {
                            cg_food.setChecked(true);
                            cg_no.setVisibility(View.VISIBLE);
                            cg_no.setText(c.getString(c.getColumnIndex("feed_g_nos")));
                        } else {
                            cg_food.setChecked(false);
                            cg_no.setVisibility(View.INVISIBLE);
                            cg_no.setText("");
                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "14" + e.toString());

                    }

                    try {
                        if (c.getString(c.getColumnIndex("feed_h")).equalsIgnoreCase("Y")) {
                            ch_food.setChecked(true);
                            ch_no.setVisibility(View.VISIBLE);
                            ch_no.setText(c.getString(c.getColumnIndex("feed_h_nos")));
                        } else {
                            ch_food.setChecked(false);
                            ch_no.setVisibility(View.INVISIBLE);
                            ch_no.setText("");
                        }

                    } catch (Exception e) {

                        Log.d("LACtivty", "15" + e.toString());
                    }
                    try {
                        if (c.getString(c.getColumnIndex("feed_I")).equalsIgnoreCase("Y")) {
                            ci_food.setChecked(true);
                            ci_no.setVisibility(View.VISIBLE);
                            ci_no.setText(c.getString(c.getColumnIndex("feed_i_nos")));
                        } else {
                            ci_food.setChecked(false);
                            ci_no.setVisibility(View.INVISIBLE);
                            ci_no.setText("");
                        }
                        if (c.getString(c.getColumnIndex("feed_j")).equalsIgnoreCase("Y")) {
                            cj_food.setChecked(true);
                            cj_no.setVisibility(View.VISIBLE);
                            cj_no.setText(c.getString(c.getColumnIndex("feed_j_nos")));
                        } else {
                            cj_food.setChecked(false);
                            cj_no.setVisibility(View.INVISIBLE);
                            cj_no.setText("");
                        }
                        if (c.getString(c.getColumnIndex("feed_k")).equalsIgnoreCase("Y")) {
                            ck_food.setChecked(true);
                            ck_no.setVisibility(View.VISIBLE);
                            ck_no.setText(c.getString(c.getColumnIndex("feed_k_nos")));
                        } else {
                            ck_food.setChecked(false);
                            ck_no.setVisibility(View.INVISIBLE);
                            ck_no.setText("");
                        }

                        if (c.getString(c.getColumnIndex("feed_l")).equalsIgnoreCase("Y")) {
                            cl_food.setChecked(true);
                            cl_no.setVisibility(View.VISIBLE);
                            cl_no.setText(c.getString(c.getColumnIndex("feed_l_nos")));
                        } else {
                            cl_food.setChecked(false);
                            cl_no.setVisibility(View.INVISIBLE);
                            cl_no.setText("");
                        }

                        if (c.getString(c.getColumnIndex("feed_m")).equalsIgnoreCase("Y")) {
                            cm_food.setChecked(true);
                            cm_no.setVisibility(View.VISIBLE);
                            cm_no.setText(c.getString(c.getColumnIndex("feed_m_nos")));
                        } else {
                            cm_food.setChecked(false);
                            cm_no.setVisibility(View.INVISIBLE);
                            cm_no.setText("");
                        }




                    } catch (Exception e) {

                        Log.d("Holi", e.toString());

                    }

                    if (c.getString(c.getColumnIndex("is_approved")).equalsIgnoreCase("Y")) {
                        save.setEnabled(false);
                        save.setVisibility(View.INVISIBLE);

                    }

                }
                while (c.moveToNext());


                String query1 = "Select * from diet where members_id = '" + getIntent().getStringExtra("motherId") + "' and sub_stage='"+pwActive_subStage+"'";
                Cursor c2 = dbs.rawQuery(query1, null);
                int total2 = c2.getCount();

                Log.d("2ndQuery",query1);
                Log.d("2ndQuery",""+total2);

                if (total2 > 0) {

                    if (c2.moveToFirst()) {
                        do {

                            Log.d("2ndQuery","Inserted");

                            try {
                                Log.d("2ndQuery","Inserted");
                                if (c2.getString(c2.getColumnIndex("feed_a")).equalsIgnoreCase("Y")) {
                                    a_food.setChecked(true);
//                                    a_no.setVisibility(View.VISIBLE);
                                    a_no.setText(c2.getString(c2.getColumnIndex("feed_a_nos")));

                                } else {
                                    a_food.setChecked(false);
                                    a_no.setVisibility(View.INVISIBLE);
                                    a_no.setText("");
                                }
                            } catch (Exception e) {
                                Log.d("LACtivty", "8" + e.toString());
                            }

                            try {
                                if (c2.getString(c2.getColumnIndex("feed_b")).equalsIgnoreCase("Y")) {
                                    Log.d("getFeed", c2.getString(c2.getColumnIndex("feed_b")));
                                    Log.d("getFeed", c2.getString(c2.getColumnIndex("feed_b_nos")));
                                    b_food.setChecked(true);
//                                    b_no.setVisibility(View.VISIBLE);
                                    b_no.setText(c2.getString(c2.getColumnIndex("feed_b_nos")));
                                } else {
                                    b_food.setChecked(false);
                                    b_no.setVisibility(View.INVISIBLE);
                                    b_no.setText("");
                                }
                            } catch (Exception e) {
                                Log.d("LACtivty", "9" + e.toString());

                            }

                            try {
                                if (c2.getString(c2.getColumnIndex("feed_c")).equalsIgnoreCase("Y")) {
                                    c_food.setChecked(true);
//                                    c_no.setVisibility(View.VISIBLE);
                                    c_no.setText(c2.getString(c2.getColumnIndex("feed_c_nos")));
                                } else {
                                    c_food.setChecked(false);
                                    c_no.setVisibility(View.INVISIBLE);
                                    c_no.setText("");
                                }

                            } catch (Exception e) {

                                Log.d("LACtivty", "10" + e.toString());
                            }

                            try {
                                if (c2.getString(c2.getColumnIndex("feed_d")).equalsIgnoreCase("Y")) {
                                    d_food.setChecked(true);
//                                    d_no.setVisibility(View.VISIBLE);
                                    d_no.setText(c2.getString(c2.getColumnIndex("feed_d_nos")));
                                } else {
                                    d_food.setChecked(false);
                                    d_no.setVisibility(View.INVISIBLE);
                                    d_no.setText("");
                                }

                            } catch (Exception e) {
                                Log.d("LACtivty", "11" + e.toString());

                            }
                            try {

                                if (c2.getString(c2.getColumnIndex("feed_e")).equalsIgnoreCase("Y")) {
                                    e_food.setChecked(true);
//                                    e_no.setVisibility(View.VISIBLE);
                                    e_no.setText(c2.getString(c2.getColumnIndex("feed_e_nos")));
                                } else {
                                    e_food.setChecked(false);
                                    e_no.setVisibility(View.INVISIBLE);
                                    e_no.setText("");
                                }

                            } catch (Exception e) {

                                Log.d("LACtivty", "12" + e.toString());
                            }

                            try {
                                if (c2.getString(c2.getColumnIndex("feed_f")).equalsIgnoreCase("Y")) {
                                    f_food.setChecked(true);
//                                    f_no.setVisibility(View.VISIBLE);
                                    f_no.setText(c2.getString(c2.getColumnIndex("feed_f_nos")));
                                } else {
                                    f_food.setChecked(false);
                                    f_no.setVisibility(View.INVISIBLE);
                                    f_no.setText("");
                                }

                            } catch (Exception e) {
                                Log.d("LACtivty", "13" + e.toString());

                            }

                            try {
                                if (c2.getString(c2.getColumnIndex("feed_g")).equalsIgnoreCase("Y")) {
                                    g_food.setChecked(true);
//                                    g_no.setVisibility(View.VISIBLE);
                                    g_no.setText(c2.getString(c2.getColumnIndex("feed_g_nos")));
                                } else {
                                    g_food.setChecked(false);
                                    g_no.setVisibility(View.INVISIBLE);
                                    g_no.setText("");
                                }
                            } catch (Exception e) {
                                Log.d("LACtivty", "14" + e.toString());

                            }

                            try {
                                if (c2.getString(c2.getColumnIndex("feed_h")).equalsIgnoreCase("Y")) {
                                    h_food.setChecked(true);
//                                    h_no.setVisibility(View.VISIBLE);
                                    h_no.setText(c2.getString(c2.getColumnIndex("feed_h_nos")));
                                } else {
                                    h_food.setChecked(false);
                                    h_no.setVisibility(View.INVISIBLE);
                                    h_no.setText("");
                                }

                            } catch (Exception e) {

                                Log.d("LACtivty", "15" + e.toString());
                            }
                            try {
                                if (c2.getString(c2.getColumnIndex("feed_I")).equalsIgnoreCase("Y")) {
                                    i_food.setChecked(true);
//                                    i_no.setVisibility(View.VISIBLE);
                                    i_no.setText(c2.getString(c2.getColumnIndex("feed_i_nos")));
                                } else {
                                    i_food.setChecked(false);
                                    i_no.setVisibility(View.INVISIBLE);
                                    i_no.setText("");
                                }
                                if (c2.getString(c2.getColumnIndex("feed_j")).equalsIgnoreCase("Y")) {
                                    j_food.setChecked(true);
//                                    j_no.setVisibility(View.VISIBLE);
                                    j_no.setText(c2.getString(c2.getColumnIndex("feed_j_nos")));
                                } else {
                                    j_food.setChecked(false);
                                    j_no.setVisibility(View.INVISIBLE);
                                    j_no.setText("");
                                }
                                if (c2.getString(c2.getColumnIndex("feed_k")).equalsIgnoreCase("Y")) {
                                    k_food.setChecked(true);
//                                    k_no.setVisibility(View.VISIBLE);
                                    k_no.setText(c2.getString(c2.getColumnIndex("feed_k_nos")));
                                } else {
                                    k_food.setChecked(false);
                                    k_no.setVisibility(View.INVISIBLE);
                                    k_no.setText("");
                                }

                                if (c2.getString(c2.getColumnIndex("feed_l")).equalsIgnoreCase("Y")) {
                                    l_food.setChecked(true);
//                                    l_no.setVisibility(View.VISIBLE);
                                    l_no.setText(c2.getString(c2.getColumnIndex("feed_l_nos")));
                                } else {
                                    l_food.setChecked(false);
                                    l_no.setVisibility(View.INVISIBLE);
                                    l_no.setText("");
                                }

                                if (c2.getString(c2.getColumnIndex("feed_m")).equalsIgnoreCase("Y")) {
                                    m_food.setChecked(true);
//                                    m_no.setVisibility(View.VISIBLE);
                                    m_no.setText(c2.getString(c2.getColumnIndex("feed_m_nos")));
                                } else {
                                    m_food.setChecked(false);
                                    m_no.setVisibility(View.INVISIBLE);
                                    m_no.setText("");
                                }

                            } catch (Exception e) {

                            }

                        }while(c2.moveToNext());





//

                } else {


                }

            }
        }}
        }


    public void checkWidgetvisibility()
    {
        SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
        String visibilityCheck="select WHEN_FIRST_BF,IF_FEED_KHEES,CURRENTLY_BF,WHEN_STOP_BF,ANYTHING_BEFORE_BF,IF_STARTED_SOLID_FOOD,WHICH_MONTH_SOLID_FOOD,CHILD_IMMUNIZATION_STATUS,IS_APPROVED," +
                "IS_NEW from childextra where members_id='"+getIntent().getStringExtra("memberId")+"'";


        Cursor cr = swl.rawQuery(visibilityCheck, null);
        Log.d("LM_activity",cr.getCount()+"");
        int total = cr.getCount();
        if (total >= 1) {

            int i=0;
            if (cr.moveToFirst()) {
                do {


                    Log.d("LM_activity","Inserted"+"");
                    when_firstBf= cr.getString(cr.getColumnIndex("when_first_bf"));
                     if_feed_khee= cr.getString(cr.getColumnIndex("if_feed_khees"));
                     current_bf= cr.getString(cr.getColumnIndex("currently_bf"));
                    when_stop_bf= cr.getString(cr.getColumnIndex("when_stop_bf"));
                     anything_before_bf= cr.getString(cr.getColumnIndex("anything_before_bf"));
                     if_started_solid_food= cr.getString(cr.getColumnIndex("if_started_solid_food"));
                     WHICH_MONTH_SOLID_FOOD= cr.getString(cr.getColumnIndex("which_month_solid_food"));

//                    Log.d("Ranjeet")



                    Log.d("LM_activitySpinner",when_stop_bf+"");
                    try {
                        if (current_bf.equalsIgnoreCase("N") && breastfeeding.getSelectedItemPosition() == 0) {
                            breastfeeding.setVisibility(View.GONE);
                            breastfeedingTextView.setVisibility(View.GONE);

                        }
                        if (if_feed_khee.equalsIgnoreCase("Y") && pwActive_subStage.equalsIgnoreCase("LM2")) {
                            khesstest.setVisibility(View.GONE);
                            khees.setVisibility(View.GONE);
                        }

                        if (anything_before_bf.length() > 0 && pwActive_subStage.equalsIgnoreCase("LM2")) {
//    childBreastTest.setVisibility(View.GONE);
//    childtobreast.setVisibility(View.GONE);

                        }
                        if (anything_before_bf.length() > 0) {
                            otherfoodbeforebreastfeedTest.setVisibility(View.GONE);
                            otherfoodbeforebreastfeed.setVisibility(View.GONE);
                            View view = findViewById(R.id.otherfoodbeforebreastfeedView);
                            view.setVisibility(View.GONE);

                        }
                    }catch (Exception e){

                    }

                } while (cr.moveToNext());
            }

//        Log.d("recordCountPW",""+i);
        }






    }

    public void formVisibleInvible(String activeStage){

        switch (activeStage){
            case "LM1":
                Log.d("LMActivity",activeStage);

                semisolidfoodtochildtext.setVisibility(View.GONE);
                semisolidfoodtochild.setVisibility(View.GONE);
                solidfoodmonth.setVisibility(View.GONE);
                solidfoodmonthtext.setVisibility(View.GONE);
                break;

            case "LM2":
                Log.d("LMActivity",activeStage);
                semisolidfoodtochildtext.setVisibility(View.GONE);
                semisolidfoodtochild.setVisibility(View.GONE);
                solidfoodmonth.setVisibility(View.GONE);
                solidfoodmonthtext.setVisibility(View.GONE);
                break;
            case "LM3":
                Log.d("LMActivity",activeStage);

                semisolidfoodtochildtext.setVisibility(View.VISIBLE);
                semisolidfoodtochild.setVisibility(View.VISIBLE);
                solidfoodmonth.setVisibility(View.GONE);
                solidfoodmonthtext.setVisibility(View.GONE);

                khesstest.setVisibility(View.GONE);
                khees.setVisibility(View.GONE);
//
                childtobreast.setVisibility(View.GONE);
                childBreastTest.setVisibility(View.GONE);
                View view2= findViewById(R.id.childbreastNow);
                view2.setVisibility(View.GONE);

                otherfoodbeforebreastfeed.setVisibility(View.GONE);
                otherfoodbeforebreastfeedTest.setVisibility(View.GONE);
                View view= findViewById(R.id.otherfoodbeforebreastfeedView);
                view.setVisibility(View.GONE);


                break;

        }

    }

    public void dietSelection(){


        if (a_food.isChecked()) {
            af = "Y";
//            an = Integer.parseInt(a_no.getText().toString());
        } else {
            af = "N";
            an = null;
        }
        if (b_food.isChecked()) {
            bf = "Y";
//            bn = Integer.parseInt(b_no.getText().toString());
        } else {
            bf = "N";
            bn = null;
        }

        if (c_food.isChecked()) {
            cf = "Y";
//            cn = Integer.parseInt(c_no.getText().toString());
        } else {
            cf = "N";
            cn = null;
        }


        if (d_food.isChecked()) {
            df = "Y";
//            dn = Integer.parseInt(d_no.getText().toString());
        } else {
            df = "N";
            dn = null;
        }

        if (e_food.isChecked()) {
            ef = "Y";
//            en = Integer.parseInt(e_no.getText().toString());
        } else {
            ef = "N";
            en = null;
        }

        if (f_food.isChecked()) {
            ff = "Y";
//            fn = Integer.parseInt(f_no.getText().toString());
        } else {
            ff = "N";
            fn = null;
        }
        if (g_food.isChecked()) {
            gf = "Y";
//            gn = Integer.parseInt(g_no.getText().toString());
        } else {
            gf = "N";
            gn = null;
        }

        if (h_food.isChecked()) {
            hf = "Y";
//            hn = Integer.parseInt(h_no.getText().toString());
        } else {
            hf = "N";
            hn = null;
        }
        if (i_food.isChecked()) {
            i_f = "Y";
//            in = Integer.parseInt(i_no.getText().toString());
        } else {

            i_f = "N";
            in = null;
        }


        if (j_food.isChecked()) {
            jf = "Y";
//            jn = Integer.parseInt(j_no.getText().toString());
        } else {

            jf = "N";
            jn = null;
        }

        if (k_food.isChecked()) {
            kf = "Y";
//            kn = Integer.parseInt(k_no.getText().toString());
        } else {
            kf = "N";
            kn = null;
        }

        if (l_food.isChecked()) {
            lf = "Y";
//            ln = Integer.parseInt(l_no.getText().toString());
        } else {
            lf = "N";
            ln = null;
        }
        if (m_food.isChecked()) {
            mf = "Y";
//            mn = Integer.parseInt(m_no.getText().toString());
        } else {
            mf = "N";
            mn = null;
        }

        //child

        if (ca_food.isChecked()) {
            caf = "Y";
            can = Integer.parseInt(ca_no.getText().toString());
        } else {
            caf = "N";
            can = null;
        }
        if (cb_food.isChecked()) {
            cbf = "Y";
            cbn = Integer.parseInt(cb_no.getText().toString());
        } else {
            cbf = "N";
            cbn = null;
        }

        if (cc_food.isChecked()) {
            ccf = "Y";
            ccn = Integer.parseInt(cc_no.getText().toString());
        } else {
            ccf = "N";
            ccn = null;
        }


        if (cd_food.isChecked()) {
            cdf = "Y";
            cdn = Integer.parseInt(cd_no.getText().toString());
        } else {
            cdf = "N";
            cdn = null;
        }

        if (ce_food.isChecked()) {
            cef = "Y";
            cen = Integer.parseInt(ce_no.getText().toString());
        } else {
            cef = "N";
            cen = null;
        }

        if (cf_food.isChecked()) {
            cff = "Y";
            cfn = Integer.parseInt(cf_no.getText().toString());
        } else {
            cff = "N";
            cfn = null;
        }
        if (cg_food.isChecked()) {
            cgf = "Y";
            cgn = Integer.parseInt(cg_no.getText().toString());
        } else {
            cgf = "N";
            cgn = null;
        }

        if (ch_food.isChecked()) {
            chf = "Y";
            chn = Integer.parseInt(ch_no.getText().toString());
        } else {
            chf = "N";
            chn = null;
        }
        if (ci_food.isChecked()) {
            ci_f = "Y";
            cin = Integer.parseInt(ci_no.getText().toString());
        } else {

            ci_f = "N";
            cin = null;
        }


        if (cj_food.isChecked()) {
            cjf = "Y";
            cjn = Integer.parseInt(cj_no.getText().toString());
        } else {

            cjf = "N";
            cjn = null;
        }

        if (ck_food.isChecked()) {
            ckf = "Y";
            ckn = Integer.parseInt(ck_no.getText().toString());
        } else {
            ckf = "N";
            ckn = null;
        }

        if (cl_food.isChecked()) {
            clf = "Y";
            cln = Integer.parseInt(cl_no.getText().toString());
        } else {
            clf = "N";
            cln = null;
        }
        if (cm_food.isChecked()) {
            cmf = "Y";
            cmn = Integer.parseInt(cm_no.getText().toString());
        } else {
            cmf = "N";
            cmn = null;
        }




    }

   public int indexOfYesNo(String optionValue){
       int x=0;
       switch (optionValue) {

           case "Y":
//
             x=1;
               break;

           case "N":
              x=2;
               break;
           case "--Select Options--":
              x=0;
               break;
       }
           return x;
   }


   public String radiobuttonGrp(){




               // get selected radio button from radioGroup
               int selectedId = radiogroup .getCheckedRadioButtonId();
String radioNASelection="notSelected";
               switch(selectedId){

                   case R.id.na1:
                       radioNASelection="MD";
                       break;

                   case R.id.na2:
                       radioNASelection="MM";
                       break;
                   case R.id.na3:
                       radioNASelection="CD";
                       break;
                   case R.id.na4:
                       radioNASelection="CM";
                       break;
                       case R.id.na5:
                           radioNASelection="BD";
                           break;
                   case R.id.na6:
                       radioNASelection="BM";
                       break;


               }

return radioNASelection;
   }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.mymenu, menu);

        MenuItem shareItem = menu.findItem(R.id.mymotherTrack);
        if(getIntent().getStringExtra("current_sub_stage").equalsIgnoreCase("LM3")){

           if(checkActivePregency(getIntent().getStringExtra("motherId"))) {
               shareItem.setVisible(true);
           }
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;

            case R.id.mymotherTrack:

                pwActive_subStage="ptod";
                pTod.setVisibility(View.VISIBLE);
                fragmentnaforlmnmy.setVisibility(View.INVISIBLE);

                holdingTabs.setVisibility(View.INVISIBLE);
//                pwActive_subStage="PD";

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public boolean checkActivePregency(String motherId){
        SQLiteDatabase dQuery= openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cQuery = dQuery.rawQuery("select * from pregnant where members_id='"+motherId+"' and is_active='Y'", null);
;
if(cQuery.getCount()>0){
    return  false;
}
else{
    return true;
}


    }


    public String generatePregnentID(String memberID){


        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery("SELECT * FROM  pregnant where Members_id ='"+memberID+"'" , null);


        c.getCount();
        int count = c.getCount();
//    Log.d("countregister",""+ c.getCount());
        String pregantId= memberID+"P"+String.format("%02d",count+1);
//    Log.d("pregnemtid",pregantId);
//    return memberId;
        return pregantId;
    }

    public void executeQuery(String Query){

        SQLiteDatabase dQuery= openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cQuery = dQuery.rawQuery(Query, null);

        cQuery.moveToFirst();
        cQuery.close();

    }


    public void clearAllForm(){

        ViewGroup group = (ViewGroup)findViewById(R.id.layoutchecki);
        ViewGroup group2 = (ViewGroup)findViewById(R.id.linearMotherForm);
        ViewGroup group3 = (ViewGroup)findViewById(R.id.viewgroup3);

        Log.d("ChildcountCalculation",group.getChildCount()+"");
        Log.d("ChildcountCalculation",group2.getChildCount()+"");
        Log.d("ChildcountCalculation",group3.getChildCount()+"");

        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            Log.d("ChildcountCalculation","Inserted into One");

            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if(view instanceof Spinner){
                ((Spinner)view).setSelection(0);
            }
            if(view instanceof CheckBox ){
                ((CheckBox)view).setChecked(false);
            }

            if(view instanceof RadioButton ){
                ((RadioButton)view).setChecked(false);
            }
        }

        for (int i = 0, count = group2.getChildCount(); i < count; ++i) {

            Log.d("ChildcountCalculation","Inserted into two");
            View view = group2.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if(view instanceof Spinner){
                ((Spinner)view).setSelection(0);
            }
            if(view instanceof CheckBox ){
                ((CheckBox)view).setChecked(false);
            }
            if(view instanceof RadioButton ){
                ((RadioButton)view).setChecked(false);
            }

        }

        for (int i = 0, count = group3.getChildCount(); i < count; ++i) {

            Log.d("ChildcountCalculation","Inserted into three");
            View view3 = group3.getChildAt(i);
            if (view3 instanceof EditText) {
                ((EditText)view3).setText("");
            }
            if(view3 instanceof Spinner){
                ((Spinner)view3).setSelection(0);
            }
            if(view3 instanceof CheckBox ){
                ((CheckBox)view3).setChecked(false);
            }
            if(view3 instanceof RadioButton){
                ((RadioButton)view3).setChecked(false);
            }

        }


        a_food.setChecked(false);
        b_food.setChecked(false);
        c_food.setChecked(false);
        d_food.setChecked(false);
        e_food.setChecked(false);
        f_food.setChecked(false);
        g_food.setChecked(false);
        h_food.setChecked(false);
        i_food.setChecked(false);
        j_food.setChecked(false);
        k_food.setChecked(false);
        l_food.setChecked(false);
        m_food.setChecked(false);
        height.setText("");
        weight.setText("");


        ca_food.setChecked(false);
        cb_food.setChecked(false);
        cc_food.setChecked(false);
        cd_food.setChecked(false);
        ce_food.setChecked(false);
        cf_food.setChecked(false);
        cg_food.setChecked(false);
        ch_food.setChecked(false);
        ci_food.setChecked(false);
        cj_food.setChecked(false);
        ck_food.setChecked(false);
        cl_food.setChecked(false);
        cm_food.setChecked(false);
        muac.setText("");
        solidfoodmonth.setText("");

    }


}

