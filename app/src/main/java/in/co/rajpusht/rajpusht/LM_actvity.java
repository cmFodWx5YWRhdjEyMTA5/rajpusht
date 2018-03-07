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

import java.util.ArrayList;
import java.util.List;

public class LM_actvity extends AppCompatActivity {

    Button lm1, lm2, lm3;
    LinearLayout zerothree, threesix, six;
    Dialog dialogCoupon;

    View sbcclayout, dietaryLyout, heightWeightLayout, fragmentnaforlmnmy;
    RelativeLayout relativessbc, relativeDiversity, relativeHeight, holdingTabs;
    View basicclick, pregnentclick, childclick;


    CheckBox a_food, b_food, c_food, d_food, e_food, f_food, g_food, h_food, i_food, j_food, k_food, l_food, m_food;
    EditText a_no, b_no, c_no, d_no, e_no, f_no, g_no, h_no, i_no, j_no, k_no, l_no, m_no;

    String af, bf, cf, df, ef, ff, gf, hf, i_f, jf, kf, lf, mf;
    Integer an, bn, cn, dn, en, fn, gn, hn, in, jn, kn, ln, mn;
    ImageView naButton;
    TextView textvisibilty;


    Spinner breastfeeding, whenstopbreastfeeding, ngoinfantfeed, childtobreast, khees, otherfoodbeforebreastfeed, liquidotherthenbreastfeeding, ngoownfood, householdexpence, semisolidfoodtochild;

    String breastfeedingStrung, whenstopbreastfeedingString, ngoinfantfeedString, childtobreastString, kheesString,
            otherfoodbeforebreastfeedString, ngoownfoodString, householdexpenceString, semisolidfoodtochildString;
    int breastfeedingInte, whenstopbreastfeedingInt, ngoinfantfeedint, childtobreastint, kheesInt,
            otherfoodbeforebreastfeedint, ngoownfoodint, householdexpenceint, semisolidfoodtochildint;
    View viewunderline;

    Button save;
    String pwActive_subStage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lm_actvity);

        getSupportActionBar().setTitle("Child Name : " + getIntent().getStringExtra("name"));

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

        naButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentnaforlmnmy.setVisibility(View.VISIBLE);
                holdingTabs.setVisibility(View.GONE);
            }
        });

        zerothree = (LinearLayout) findViewById(R.id.zerothree);
        threesix = (LinearLayout) findViewById(R.id.threesix);
        six = (LinearLayout) findViewById(R.id.six);


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
                breastfeedingInte = parent.getSelectedItemPosition();

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
                whenstopbreastfeedingInt = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ngoinfantfeed = (Spinner) findViewById(R.id.ngoinfantfeed);

        List<String> Listngoinfantfeed = new ArrayList<String>();
        Listngoinfantfeed.add("--Select Options--");
        Listngoinfantfeed.add("Within one week after birth");
        Listngoinfantfeed.add("Within one month after birth");
        Listngoinfantfeed.add("Within three months after birth");
        Listngoinfantfeed.add("Within six months after birth");
        Listngoinfantfeed.add("Within 12-18 months after birth");
        Listngoinfantfeed.add("Never breastfed");
        ArrayAdapter<String> dataAdapterngoinfantfeed = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Listngoinfantfeed);
        dataAdapterngoinfantfeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ngoinfantfeed.setAdapter(dataAdapterngoinfantfeed);

        ngoinfantfeed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ngoinfantfeedString = String.valueOf(parent.getItemAtPosition(position));
                ngoinfantfeedint = parent.getSelectedItemPosition();
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
                childtobreastint = parent.getSelectedItemPosition();
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
                kheesInt = parent.getSelectedItemPosition();
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
                otherfoodbeforebreastfeedint = parent.getSelectedItemPosition();
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
                ngoownfoodint = parent.getSelectedItemPosition();
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
                householdexpenceint = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        semisolidfoodtochild = (Spinner) findViewById(R.id.semisolidfoodtochild);

        List<String> Listsemisolidfoodtochild = new ArrayList<String>();
        Listsemisolidfoodtochild.add("--Select Options--");
        Listsemisolidfoodtochild.add("Less than Rs. 500 per month");
        Listsemisolidfoodtochild.add("Rs. 500- 1,000 per month");
        Listsemisolidfoodtochild.add("Rs. 1000 – 2000 per month");
        Listsemisolidfoodtochild.add("Rs. 2000 – 4000 per month ");
        Listsemisolidfoodtochild.add("Rs. 4000 – 5,000 per month");
        Listsemisolidfoodtochild.add("Rs. 5,000 and above ");
        ArrayAdapter<String> dataAdaptesemisolidfoodtochild = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Listhouseholdexpence);
        dataAdaptesemisolidfoodtochild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semisolidfoodtochild.setAdapter(dataAdaptesemisolidfoodtochild);

        semisolidfoodtochild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semisolidfoodtochildString = String.valueOf(parent.getItemAtPosition(position));
                semisolidfoodtochildint = parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                zerothree.setVisibility(View.VISIBLE);
                threesix.setVisibility(View.GONE);
                six.setVisibility(View.GONE);

            }
        });

        lm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                zerothree.setVisibility(View.VISIBLE);
                threesix.setVisibility(View.VISIBLE);
                six.setVisibility(View.GONE);
            }
        });

        lm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                zerothree.setVisibility(View.GONE);
                threesix.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
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


            }
        });


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
            pwActive_subStage="lm1";
        }
        if(substage.equalsIgnoreCase("lm2")){
            Log.d("pregnentStatus","inseretdr");
            lm2.setBackgroundResource(R.drawable.circlebutton);
            lm2.setEnabled(true);
            pwActive_subStage="lm2";
        }
        if(substage.equalsIgnoreCase("lm3")){
            lm3.setBackgroundResource(R.drawable.circlebutton);
            lm3.setEnabled(true);
            pwActive_subStage="lm3";
        }

    }
}

