package in.co.rajpusht.rajpusht;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    View basicclick, pregnentclick, childclick;

    CheckBox a_food, b_food, c_food, d_food, e_food, f_food, g_food, h_food, i_food, j_food, k_food, l_food, m_food;
    EditText a_no, b_no, c_no, d_no, e_no, f_no, g_no, h_no, i_no, j_no, k_no, l_no, m_no;

    CheckBox ca_food, cb_food, cc_food, cd_food, ce_food, cf_food, cg_food, ch_food, ci_food, cj_food, ck_food, cl_food, cm_food;
    EditText ca_no, cb_no, cc_no, cd_no, ce_no, cf_no, cg_no, ch_no, ci_no, cj_no, ck_no, cl_no, cm_no;


    String af, bf, cf, df, ef, ff, gf, hf, i_f, jf, kf, lf, mf;
    Integer an, bn, cn, dn, en, fn, gn, hn, in, jn, kn, ln, mn;

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

    //datebase retrieve strign value
    String when_firstBf,if_feed_khee,current_bf, when_stop_bf,anything_before_bf;
    TextView solidfoodmonthtext;


    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lm_actvity);

        getSupportActionBar().setTitle("Child Name : " + getIntent().getStringExtra("name"));
        session = new SessionManager(LM_actvity.this);

        lm1 = (Button) findViewById(R.id.LM1Button);
        lm2 = (Button) findViewById(R.id.LM2Button);
        lm3 = (Button) findViewById(R.id.LM3Button);
        naButton = (ImageView) findViewById(R.id.naButton);

        relativessbc = (RelativeLayout) findViewById(R.id.relativessbc);
        relativeDiversity = (RelativeLayout) findViewById(R.id.relativeDiversity);
        relativeHeight = (RelativeLayout) findViewById(R.id.relativeHeight);
        holdingTabs = (RelativeLayout) findViewById(R.id.holdingTabs);


        basicclick = (View) findViewById(R.id.basicclick);
        pregnentclick = (View) findViewById(R.id.pregnentclick);
        childclick = (View) findViewById(R.id.childclick);


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

        naButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentnaforlmnmy.setVisibility(View.VISIBLE);
                holdingTabs.setVisibility(View.GONE);
            }
        });

//        zerothree = (LinearLayout) findViewById(R.id.zerothree);
//        threesix = (LinearLayout) findViewById(R.id.threesix);
//        six = (LinearLayout) findViewById(R.id.six);


        height= (EditText) findViewById(R.id.height);
        weight= (EditText) findViewById(R.id.weight);
        muac= (EditText) findViewById(R.id.muac);

        solidfoodmonth= (EditText) findViewById(R.id.solidfoodmonth);

        breastfeeding = (Spinner) findViewById(R.id.breastfeeding);
        List<String> listbreastfeeding = new ArrayList<String>();
        listbreastfeeding.add("--Select Options--");
        listbreastfeeding.add("Yes");
        listbreastfeeding.add("No");
        ArrayAdapter<String> dataAdapterlistbreastfeeding = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listbreastfeeding);
        dataAdapterlistbreastfeeding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breastfeeding.setAdapter(dataAdapterlistbreastfeeding);

        breastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                breastfeedingStrung = String.valueOf(parent.getItemAtPosition(position));
                breastfeedingInte = position;



                if(breastfeedingStrung.equalsIgnoreCase("no")){

                    whenstopbreastfeeding.setVisibility(View.VISIBLE);
                    textvisibilty.setVisibility(View.VISIBLE);
                    viewunderline.setVisibility(View.VISIBLE);

                }
                else{
                    whenstopbreastfeeding.setVisibility(View.GONE);
                    textvisibilty.setVisibility(View.GONE);
                    viewunderline.setVisibility(View.GONE);
                }

                if(breastfeedingStrung.equalsIgnoreCase("yes")){
                    breastfeedingStrung="Y";
                }
                if(breastfeedingStrung.equalsIgnoreCase("No")){
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
                android.R.layout.simple_spinner_item, Listwhenstopbreastfeeding);
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
                android.R.layout.simple_spinner_item, Listngoinfantfeed);
        dataAdapterngoinfantfeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ngoinfantfeed.setAdapter(dataAdapterngoinfantfeed);

        ngoinfantfeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngoinfantfeedString = String.valueOf(parent.getItemAtPosition(position));
                ngoinfantfeedint = position;

                if(ngoinfantfeedString.equalsIgnoreCase("yes")){
                    ngoinfantfeedString="Y";
                }
                if(ngoinfantfeedString.equalsIgnoreCase("No")){
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
                android.R.layout.simple_spinner_item, Listchildtobreast);
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
                android.R.layout.simple_spinner_item, Listkhees);
        dataAdapterListkhees.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        khees.setAdapter(dataAdapterListkhees);

        khees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kheesString = String.valueOf(parent.getItemAtPosition(position));
                kheesInt =position;


                if(kheesString.equalsIgnoreCase("Yes")){
                    kheesString="Y";
                }
                if(kheesString.equalsIgnoreCase("No")){
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
                android.R.layout.simple_spinner_item, Listotherfoodbeforebreastfeed);
        dataAdapterotherfoodbeforebreastfeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        otherfoodbeforebreastfeed.setAdapter(dataAdapterotherfoodbeforebreastfeed);

        otherfoodbeforebreastfeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                otherfoodbeforebreastfeedString = String.valueOf(parent.getItemAtPosition(position));
                otherfoodbeforebreastfeedint = position;


                if(otherfoodbeforebreastfeedString.equalsIgnoreCase("yes")){
                    otherfoodbeforebreastfeedString="Y";
                }
                if(otherfoodbeforebreastfeedString.equalsIgnoreCase("No")){
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
                android.R.layout.simple_spinner_item, Lisliquidotherthenbreastfeeding);
        dataAdapterotherliquidotherthenbreastfeeding.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liquidotherthenbreastfeeding.setAdapter(dataAdapterotherliquidotherthenbreastfeeding);

        liquidotherthenbreastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                liuidotehrbreastfreddingString=String.valueOf(parent.getItemAtPosition(position));
                liuidotehrbreastfreddingInt = position;


                if(liuidotehrbreastfreddingString.equalsIgnoreCase("yes")){
                    liuidotehrbreastfreddingString="Y";
                }
                if(liuidotehrbreastfreddingString.equalsIgnoreCase("No")){
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
                android.R.layout.simple_spinner_item, Listngoownfood);
        dataAdapterListngoownfood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ngoownfood.setAdapter(dataAdapterListngoownfood);

        ngoownfood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngoownfoodString = String.valueOf(parent.getItemAtPosition(position));
                ngoownfoodint = position;

                if(ngoownfoodString.equalsIgnoreCase("yes")){
                    ngoownfoodString="Y";
                }
                if(ngoownfoodString.equalsIgnoreCase("No")){
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
                android.R.layout.simple_spinner_item, Listhouseholdexpence);
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
                android.R.layout.simple_spinner_item, Listsemisolidfoodtochild);
        dataAdaptesemisolidfoodtochild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semisolidfoodtochild.setAdapter(dataAdaptesemisolidfoodtochild);

        semisolidfoodtochild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semisolidfoodtochildString = String.valueOf(parent.getItemAtPosition(position));
                semisolidfoodtochildint = position;

                if(semisolidfoodtochildString.equalsIgnoreCase("yes")){
                    semisolidfoodtochildString="Y";
                }
                if(semisolidfoodtochildString.equalsIgnoreCase("No")){
                    semisolidfoodtochildString="N";
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
                formVisibleInvible(pwActive_subStage);
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
//                zerothree.setVisibility(View.VISIBLE);
//                threesix.setVisibility(View.GONE);
//                six.setVisibility(View.GONE);

            }
        });

        lm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="LM2";
                formVisibleInvible(pwActive_subStage);
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
//                zerothree.setVisibility(View.VISIBLE);
//                threesix.setVisibility(View.VISIBLE);
//                six.setVisibility(View.GONE);
            }
        });

        lm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="LM3";
                formVisibleInvible(pwActive_subStage);
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
//                zerothree.setVisibility(View.GONE);
//                threesix.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);
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


        ca_no = (EditText) findViewById(R.id.a_no);
        cb_no = (EditText) findViewById(R.id.b_no);
        cc_no = (EditText) findViewById(R.id.c_no);
        cd_no = (EditText) findViewById(R.id.d_no);
        ce_no = (EditText) findViewById(R.id.e_no);
        cf_no = (EditText) findViewById(R.id.f_no);
        cg_no = (EditText) findViewById(R.id.g_no);
        ch_no = (EditText) findViewById(R.id.h_no);
        ci_no = (EditText) findViewById(R.id.i_no);
        cj_no = (EditText) findViewById(R.id.j_no);
        ck_no = (EditText) findViewById(R.id.k_no);
        cl_no = (EditText) findViewById(R.id.l_no);
        cm_no = (EditText) findViewById(R.id.m_no);


        a_food = (CheckBox) findViewById(R.id.a_food);
        a_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                  if (a_food.isChecked()) {
                                                      a_no.setVisibility(View.VISIBLE);
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
                                                      b_no.setVisibility(View.VISIBLE);
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
                                                      c_no.setVisibility(View.VISIBLE);
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
                                                      d_no.setVisibility(View.VISIBLE);
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
                                                      e_no.setVisibility(View.VISIBLE);
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
                                                      f_no.setVisibility(View.VISIBLE);
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
                                                      g_no.setVisibility(View.VISIBLE);
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
                                                      h_no.setVisibility(View.VISIBLE);
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
                                                      i_no.setVisibility(View.VISIBLE);
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
                                                      j_no.setVisibility(View.VISIBLE);
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
                                                      k_no.setVisibility(View.VISIBLE);
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
                                                      l_no.setVisibility(View.VISIBLE);
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
                                                      m_no.setVisibility(View.VISIBLE);
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
                                                      h_no.setVisibility(View.VISIBLE);
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

                errorCode = IYCFValidation(pwActive_subStage);
                if (errorCode.equalsIgnoreCase("0")) {

                    dietSelection();

                    if (subStageMode.equalsIgnoreCase("add")) {


                        String inseertLMTrack = "INSERT INTO CHILD_TRACKING (MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,CURRENTLY_BF," +
                                "IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_HEIGHT," +
                                "CHILD_WEIGHT,CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) " +
                                " VALUES ('"+getIntent().getStringExtra("memberId") +"','LM','"+ pwActive_subStage +"',1,'"+ breastfeedingStrung +"'," +
                                "'"+ ngoinfantfeedString +"','"+ ngoownfoodString +"','"+ liuidotehrbreastfreddingString+"'," +
                                "'"+ semisolidfoodtochildString +"',"+ householdexpenceint + ","+ height.getText().toString() +"," +
                                ""+ weight.getText().toString() +","+muac.getText().toString() +","+ session.getSurveyorId()+",'"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "','M','','N')";

                        Log.d("insertQuery", inseertLMTrack);
//
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


                        if(pwActive_subStage.equalsIgnoreCase("LM3")){

                            String childdeitInsert = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                    " ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "','" + caf + "'," + can + ",'" + cbf +
                                    "'," + cbn + ",'" + ccf + "'," + ccn + ",'" + cdf + "'," +cdn + ",'" + cef + "'," +
                                    cen + ",'" + cff + "'," + cfn + ",'" + cgf + "'," + cgn + ",'" + chf + "'," + chn + ",'"
                                    + ci_f + "'," + cin + ",'" + cjf + "'," + cjn + ",'" + ckf + "'," + ckn + ",'" + clf + "'," +
                                    cln + ",'" + cmf + "'," + cmn + ",'M','N','','')";

                            Cursor cinsertchild = dbs.rawQuery(childdeitInsert, null);

                            if (cinsertchild.moveToFirst()) {

                            }
//                    total = c.getCount();  int
                            cinsertchild.close();

                        }


//                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);




                        String updateSub_StageInMemberBasic = "update memberbasic set sub_stage='" + getIntent().getStringExtra("current_sub_stage") + "' WHERE MEMBERS_ID='"+getIntent().getStringExtra("memberId")+"'";
                        SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor c3 = dsbs.rawQuery(updateSub_StageInMemberBasic, null);

                        if (c3.moveToFirst()) {

                        }
//                    c3.moveToFirst();
                        c3.close();

                        String updateChildExtra;
                        if(!pwActive_subStage.equalsIgnoreCase("LM3")){
                            int stopBrestFeading=0;
                            if(breastfeedingStrung.equalsIgnoreCase("n")){
                                stopBrestFeading=whenstopbreastfeedingInt;
                            }


                           updateChildExtra="  UPDATE CHILDEXTRA " +
                                    "SET WHEN_FIRST_BF = "+ childtobreastint +",IF_FEED_KHEES = '"+ kheesString +"',CURRENTLY_BF = '"+ breastfeedingStrung +"', WHEN_STOP_BF = "+ stopBrestFeading +", ANYTHING_BEFORE_BF = '"+ liuidotehrbreastfreddingString +"'," +
                                    "IS_APPROVED = ''," +
                                    "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                                    "WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'";

                        }
                        else{

                             updateChildExtra="  UPDATE CHILDEXTRA " +
                                    "SET IF_STARTED_SOLID_FOOD = '"+ semisolidfoodtochildString +"',WHICH_MONTH_SOLID_FOOD = "+ solidfoodmonth.getText().toString() +"," +
                                    "IS_APPROVED = ''," +
                                    "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'";
                        }

                        SQLiteDatabase dsUpdate = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor cdsUpdate = dsUpdate.rawQuery(updateChildExtra, null);
                        cdsUpdate.moveToFirst();
                        cdsUpdate.close();


                        Toast.makeText(LM_actvity.this, "Record Saved Succesfully", Toast.LENGTH_SHORT).show();

                        subStageMode = "edit";

                    } else if (subStageMode.equalsIgnoreCase("edit")) {

                        Log.d("BreastFeed",householdexpenceint+"");

                        String updateTrackingPw = "UPDATE CHILD_TRACKING SET CURRENTLY_BF = '"+ breastfeedingStrung + "',IF_COUNSEL_ON_FEED_INFANT = '"+ ngoinfantfeedString +"',IF_COUNSEL_ON_SELFFEED = '"+ ngoownfoodString +"',LIQUID_OTHER_THAN_BF = '"+ liuidotehrbreastfreddingString +"'," +
                                "IF_STARTED_SOLID_FOOD = '"+ semisolidfoodtochildString +"',SPEND_ON_FOOD = "+householdexpenceint+",CHILD_HEIGHT = "+ height.getText().toString()+ ",CHILD_WEIGHT = "+ weight.getText().toString()+",CHILD_MUAC = "+ muac.getText().toString()+ "," +
                                "SURVEYOR_ID = "+ session.getSurveyorId() +", TIME_STAMP = '"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "',SOURCE = 'M',IS_APPROVED = '',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)  WHERE MEMBERS_ID = '"+ getIntent().getStringExtra("memberId") +"' AND SUB_STAGE = '"+ pwActive_subStage +"'";


                            SQLiteDatabase dsbss = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor updatedtracking = dsbss.rawQuery(updateTrackingPw, null);
                        updatedtracking.moveToFirst();
//                            dsbss.close();
                        String checkrecordInDiet= " Select * from diet  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("motherId") +
                                "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                        String updateDiet;
                        Cursor cudiet = dsbss.rawQuery(checkrecordInDiet,null);
                        cudiet.moveToFirst();
                        if(cudiet.getCount()>=1) {

                            updateDiet = "UPDATE DIET SET SUB_STAGE = '" + pwActive_subStage + "', FEED_A = '" + af + "',FEED_A_NOS = "
                                    + an + ",FEED_B = '" + bf + "',FEED_B_NOS = " + bn + ",FEED_C = '" + cf + "',FEED_C_NOS = " + cn + ",FEED_D = '"
                                    + df + "', FEED_D_NOS = " + dn + ", FEED_E = '" + ef + "', FEED_E_NOS =" + en + ",FEED_F = '" + ff + "'," +
                                    " FEED_F_NOS = " + fn + ", FEED_G = '" + gf + "', FEED_G_NOS = " + gn + ",FEED_H = '" + hf + "', " +
                                    "FEED_H_NOS = " + hn + ",FEED_I = '" + i_f + "',FEED_I_NOS = " + in + ",FEED_J = '" + jf + "'," +
                                    "FEED_J_NOS = " + jn + ",FEED_K = '" + kf + "', FEED_K_NOS = " + kn + ",FEED_L = '" + lf + "',FEED_L_NOS = " +
                                    "" + ln + ", FEED_M = '" + mf + "',   FEED_M_NOS = " + mn + ",IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("motherId") +
                                    "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                        }
                        else{

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

                        if(pwActive_subStage.equalsIgnoreCase("LM3")){
                            String checkrecordInDietChild= " Select * from diet  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") +
                                    "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                            String updateDietChild;
                            Cursor cudietChild = dsbss.rawQuery(checkrecordInDietChild,null);
                            cudiet.moveToFirst();
                            if(cudiet.getCount()>=1) {

                                updateDietChild = "UPDATE DIET SET SUB_STAGE = '" + pwActive_subStage + "', FEED_A = '" + af + "',FEED_A_NOS = "
                                        + an + ",FEED_B = '" + bf + "',FEED_B_NOS = " + bn + ",FEED_C = '" + cf + "',FEED_C_NOS = " + cn + ",FEED_D = '"
                                        + df + "', FEED_D_NOS = " + dn + ", FEED_E = '" + ef + "', FEED_E_NOS =" + en + ",FEED_F = '" + ff + "'," +
                                        " FEED_F_NOS = " + fn + ", FEED_G = '" + gf + "', FEED_G_NOS = " + gn + ",FEED_H = '" + hf + "', " +
                                        "FEED_H_NOS = " + hn + ",FEED_I = '" + i_f + "',FEED_I_NOS = " + in + ",FEED_J = '" + jf + "'," +
                                        "FEED_J_NOS = " + jn + ",FEED_K = '" + kf + "', FEED_K_NOS = " + kn + ",FEED_L = '" + lf + "',FEED_L_NOS = " +
                                        "" + ln + ", FEED_M = '" + mf + "',   FEED_M_NOS = " + mn + ",IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") +
                                        "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                            }
                            else{
                                updateDietChild = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                        " ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
                                        "'," + bn + ",'" + cf + "'," + cn + ",'" + df + "'," + dn + ",'" + ef + "'," +
                                        en + ",'" + ff + "'," + fn + ",'" + gf + "'," + gn + ",'" + hf + "'," + hn + ",'"
                                        + i_f + "'," + in + ",'" + jf + "'," + jn + ",'" + kf + "'," + kn + ",'" + lf + "'," +
                                        ln + ",'" + mf + "'," + mn + ",'M','N','','')";
                            }
                            SQLiteDatabase dsbschil = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Log.d("updatedDiet", updateDiet);


                            Cursor updatedDietchil = dsbschil.rawQuery(updateDietChild, null);
                            updatedDietchil.moveToFirst();
                            dsbschil.close();

                        }


                        String updateChildExtra;
                        if(!pwActive_subStage.equalsIgnoreCase("LM3")){
                            int stopBrestFeading=0;
                            if(breastfeedingInte==2){
                                stopBrestFeading=whenstopbreastfeedingInt;
                            }

                            Log.d("CheckUpdate",liuidotehrbreastfreddingString);

                            updateChildExtra="  UPDATE CHILDEXTRA " +
                                    "SET WHEN_FIRST_BF = "+ childtobreastint +",IF_FEED_KHEES = '"+ kheesString +"',CURRENTLY_BF = '"+ breastfeedingStrung +"', WHEN_STOP_BF = "+ stopBrestFeading +", ANYTHING_BEFORE_BF = '"+ liuidotehrbreastfreddingString +"'," +
                                    "IS_APPROVED = ''," +
                                    "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                                    "WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'";

                        }
                        else{

                            Log.d("CheckUpdate","Ranjeet else part");

                            updateChildExtra="  UPDATE CHILDEXTRA " +
                                    "SET IF_STARTED_SOLID_FOOD = '"+ semisolidfoodtochildString +"',WHICH_MONTH_SOLID_FOOD = "+ solidfoodmonth.getText().toString() +"," +
                                    "IS_APPROVED = ''," +
                                    "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'";
                        }

                        SQLiteDatabase dsUpdate = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor cdsUpdate = dsUpdate.rawQuery(updateChildExtra, null);
                        cdsUpdate.moveToFirst();
                        cdsUpdate.close();

                        Toast.makeText(LM_actvity.this, "Edited Successfully", Toast.LENGTH_SHORT).show();


                    }
//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);
                }



            }
        });
        checkWidgetvisibility();

        setButtonStage(getIntent().getStringExtra("memberId"), getIntent().getStringExtra("current_sub_stage"));


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
            formVisibleInvible(pwActive_subStage);
        }
        stageDataByLMID(getIntent().getStringExtra("memberId"),pwActive_subStage);
    }


    public String IYCFValidation(String activeStage){
        String errror ="0";

        Log.d("LM_Activity",householdexpenceString);
if(breastfeeding.getVisibility()==View.VISIBLE) {
    if (errror == "0" && breastfeedingStrung.equalsIgnoreCase("--Select Options--")) {
        Toast.makeText(this, "Select Breast Feeding", Toast.LENGTH_SHORT).show();
        errror = "1";
    }

    if (errror == "0" && textvisibilty.getVisibility() == View.VISIBLE) {

        if (errror == "0" && whenstopbreastfeedingString.equalsIgnoreCase("--Select Options--")) {
            Toast.makeText(this, "Select Breast Feeding", Toast.LENGTH_SHORT).show();
            errror = "1";
        }
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
return errror;
    }
//data binding
    public void stageDataByLMID(String memberId,String pwActive_subStage){

        String query="SELECT CURRENTLY_BF, IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_IMMUNIZATION_STATUS, CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS, T.IS_APPROVED from child_tracking T LEFT JOIN DIET D ON T.MEMBERS_ID=D.MEMBERS_ID  where T.MEMBERS_ID='"+memberId+"' AND T.SUB_STAGE='"+pwActive_subStage+"'";
//        //String query="SELECT CURRENTLY_BF,IF_COUNSEL_ON_FEED_INFANT," +
//                "IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_IMMUNIZATION_STATUS," +
//                "CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,T.IS_APPROVED,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D," +
//                "FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J," +
//                "FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS FROM child_tracking T LEFT JOIN DIET D ON T.MEMBERS_ID=D.MEMBERS_ID AND T.SUB_STAGE=D.SUB_STAGE WHERE T.MEMBERS_ID='"+memberId+"' AND T.SUB_STAGE='"+pwActive_subStage+"'";



        Log.d("LACtivty",query);
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(query,null);
        int total = c.getCount();

        String query1="Select * from diet where members_id = '"+getIntent().getStringExtra("motherId")+"'";
        Cursor c2 = dbs.rawQuery(query1,null);
        int total2 = c.getCount();


//        Log.d("pregnentStatus",current_subStage+"");
//        Log.d("pregnentStatus",pregnancy_id+"");
        if (total >= 1) {

            subStageMode="edit";

            Log.d("LACtivty",""+total);


//        int i = 0;
            if (c.moveToFirst()) {
                do {
//                    String query="SELECT CURRENTLY_BF,IF_USING_CONTRACEPTIVE,METHOD_CONTRACEPTIVE,IF_COUNSEL_ON_FEED_INFANT," +
//                            "IF_COUNSEL_ON_SELFFEED,LIQUID_OTHER_THAN_BF,IF_STARTED_SOLID_FOOD,SPEND_ON_FOOD,CHILD_IMMUNIZATION_STATUS," +
//                            "CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,T.IS_APPROVED,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D," +
//                            "FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J," +
//                            "FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS FROM child_tracking T LEFT JOIN DIET D ON T.MEMBERS_ID=D.MEMBERS_ID AND T.SUB_STAGE=D.SUB_STAGE WHERE T.MEMBERS_ID='"+memberId+"' AND T.SUB_STAGE='LM1'";


                    Log.d("CheckDate",""+ c.getString(c.getColumnIndex("currently_bf")));
                    breastfeeding.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("currently_bf"))));

                    Log.d("LA_ACTIVTY_VALUE",c.getString(c.getColumnIndex("currently_bf")));

//
                            whenstopbreastfeeding.setSelection(Integer.parseInt(when_stop_bf));
//
                    try {
//                        if (when_firstBf.length() > 0) {
                            childtobreast.setSelection(Integer.parseInt(when_firstBf));
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty","3"+e.toString());

                    }


                    try {
//                        if (c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("Y")) {
                            ngoownfood.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_counsel_on_selffeed"))));
                        ngoinfantfeed.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_counsel_on_feed_infant"))));
//                        } else {
//                            ngoownfood.setSelection(2);
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty","4"+e.toString());
                    }

                    Log.d("houseHold",c.getString(c.getColumnIndex("spend_on_food"))+"");
                    householdexpence.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("spend_on_food"))));

                    try {
//                        if (if_feed_khee.length() > 0) {

                            khees.setSelection(indexOfYesNo(if_feed_khee));
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty","5"+e.toString());
                    }
                    try {

//                        if (anything_before_bf.length() > 0) {
                        liquidotherthenbreastfeeding.setSelection(indexOfYesNo(anything_before_bf));
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty","6"+e.toString());
                    }


//
                        try {
                            height.setText(c.getString(c.getColumnIndex("child_height")));
                            weight.setText(c.getString(c.getColumnIndex("child_weight")));
                            muac.setText(c.getString(c.getColumnIndex("child_muac")));
                        } catch (Exception e) {
                            Log.d("LACtivty","7"+e.toString());
                        }
//}try

                    if(c2.getCount()>0) {
                        try {
                            if (c2.getString(c2.getColumnIndex("feed_a")).equalsIgnoreCase("Y")) {
                                a_food.setChecked(true);
                                a_no.setVisibility(View.VISIBLE);

                                a_no.setText(c.getString(c.getColumnIndex("feed_a_nos")));
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
                                Log.d("getFeed", c.getString(c.getColumnIndex("feed_b")));
                                Log.d("getFeed", c.getString(c.getColumnIndex("feed_b_nos")));
                                b_food.setChecked(true);
                                b_no.setVisibility(View.VISIBLE);
                                b_no.setText(c.getString(c.getColumnIndex("feed_b_nos")));
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
                                c_no.setVisibility(View.VISIBLE);
                                c_no.setText(c.getString(c.getColumnIndex("feed_c_nos")));
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
                                d_no.setVisibility(View.VISIBLE);
                                d_no.setText(c.getString(c.getColumnIndex("feed_d_nos")));
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
                                e_no.setVisibility(View.VISIBLE);
                                e_no.setText(c.getString(c.getColumnIndex("feed_e_nos")));
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
                                f_no.setVisibility(View.VISIBLE);
                                f_no.setText(c.getString(c.getColumnIndex("feed_f_nos")));
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
                                g_no.setVisibility(View.VISIBLE);
                                g_no.setText(c.getString(c.getColumnIndex("feed_g_nos")));
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
                                h_no.setVisibility(View.VISIBLE);
                                h_no.setText(c.getString(c.getColumnIndex("feed_h_nos")));
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
                                i_no.setVisibility(View.VISIBLE);
                                i_no.setText(c.getString(c.getColumnIndex("feed_i_nos")));
                            } else {
                                i_food.setChecked(false);
                                i_no.setVisibility(View.INVISIBLE);
                                i_no.setText("");
                            }
                            if (c2.getString(c2.getColumnIndex("feed_j")).equalsIgnoreCase("Y")) {
                                j_food.setChecked(true);
                                j_no.setVisibility(View.VISIBLE);
                                j_no.setText(c.getString(c.getColumnIndex("feed_j_nos")));
                            } else {
                                j_food.setChecked(false);
                                j_no.setVisibility(View.INVISIBLE);
                                j_no.setText("");
                            }
                            if (c2.getString(c2.getColumnIndex("feed_k")).equalsIgnoreCase("Y")) {
                                k_food.setChecked(true);
                                k_no.setVisibility(View.VISIBLE);
                                k_no.setText(c.getString(c.getColumnIndex("feed_k_nos")));
                            } else {
                                k_food.setChecked(false);
                                k_no.setVisibility(View.INVISIBLE);
                                k_no.setText("");
                            }

                            if (c2.getString(c2.getColumnIndex("feed_l")).equalsIgnoreCase("Y")) {
                                l_food.setChecked(true);
                                l_no.setVisibility(View.VISIBLE);
                                l_no.setText(c.getString(c.getColumnIndex("feed_l_nos")));
                            } else {
                                l_food.setChecked(false);
                                l_no.setVisibility(View.INVISIBLE);
                                l_no.setText("");
                            }

                            if (c2.getString(c2.getColumnIndex("feed_m")).equalsIgnoreCase("Y")) {
                                m_food.setChecked(true);
                                m_no.setVisibility(View.VISIBLE);
                                m_no.setText(c.getString(c.getColumnIndex("feed_m_nos")));
                            } else {
                                m_food.setChecked(false);
                                m_no.setVisibility(View.INVISIBLE);
                                m_no.setText("");
                            }

                            if (c2.getString(c2.getColumnIndex("is_approved")).equalsIgnoreCase("Y")) {
                                save.setEnabled(true);

                            }


                        } catch (Exception e) {

                            Log.d("Holi", e.toString());

                        }

                    }
                    }
                    while (c.moveToNext()) ;

                }}

        else {

            subStageMode="add";
        }


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
//                    String if_started_solid_food= cr.getString(cr.getColumnIndex("if_started_solid_food"));
//                    String if_started_solid_food= cr.getString(cr.getColumnIndex("if_started_solid_food"));

//                    Log.d("Ranjeet")


if(current_bf.equalsIgnoreCase("N")){
    breastfeeding.setVisibility(View.GONE);
    breastfeedingTextView.setVisibility(View.GONE);
}
if(if_feed_khee.equalsIgnoreCase("Y")){
    khesstest.setVisibility(View.GONE);
    khees.setVisibility(View.GONE);
}

if(when_firstBf.length()>0){
    childBreastTest.setVisibility(View.GONE);
    childtobreast.setVisibility(View.GONE);

}
        if(anything_before_bf.length()>0){
                        otherfoodbeforebreastfeedTest.setVisibility(View.GONE);
                        otherfoodbeforebreastfeed.setVisibility(View.GONE);
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
                solidfoodmonth.setVisibility(View.VISIBLE);
                solidfoodmonthtext.setVisibility(View.GONE);

                khesstest.setVisibility(View.GONE);
                khees.setVisibility(View.GONE);
                break;

        }

    }

    public void dietSelection(){


        if (a_food.isChecked()) {
            af = "Y";
            an = Integer.parseInt(a_no.getText().toString());
        } else {
            af = "N";
            an = null;
        }
        if (b_food.isChecked()) {
            bf = "Y";
            bn = Integer.parseInt(b_no.getText().toString());
        } else {
            bf = "N";
            bn = null;
        }

        if (c_food.isChecked()) {
            cf = "Y";
            cn = Integer.parseInt(c_no.getText().toString());
        } else {
            cf = "N";
            cn = null;
        }


        if (d_food.isChecked()) {
            df = "Y";
            dn = Integer.parseInt(d_no.getText().toString());
        } else {
            df = "N";
            dn = null;
        }

        if (e_food.isChecked()) {
            ef = "Y";
            en = Integer.parseInt(e_no.getText().toString());
        } else {
            ef = "N";
            en = null;
        }

        if (f_food.isChecked()) {
            ff = "Y";
            fn = Integer.parseInt(f_no.getText().toString());
        } else {
            ff = "N";
            fn = null;
        }
        if (g_food.isChecked()) {
            gf = "Y";
            gn = Integer.parseInt(g_no.getText().toString());
        } else {
            gf = "N";
            gn = null;
        }

        if (h_food.isChecked()) {
            hf = "Y";
            hn = Integer.parseInt(h_no.getText().toString());
        } else {
            hf = "N";
            hn = null;
        }
        if (i_food.isChecked()) {
            i_f = "Y";
            in = Integer.parseInt(i_no.getText().toString());
        } else {

            i_f = "N";
            in = null;
        }


        if (j_food.isChecked()) {
            jf = "Y";
            jn = Integer.parseInt(j_no.getText().toString());
        } else {

            jf = "N";
            jn = null;
        }

        if (k_food.isChecked()) {
            kf = "Y";
            kn = Integer.parseInt(k_no.getText().toString());
        } else {
            kf = "N";
            kn = null;
        }

        if (l_food.isChecked()) {
            lf = "Y";
            ln = Integer.parseInt(l_no.getText().toString());
        } else {
            lf = "N";
            ln = null;
        }
        if (m_food.isChecked()) {
            mf = "Y";
            mn = Integer.parseInt(m_no.getText().toString());
        } else {
            mf = "N";
            mn = null;
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
}

