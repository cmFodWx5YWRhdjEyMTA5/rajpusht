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

public class PregantWomenFooter extends AppCompatActivity {

    View fragment_preliminary_pw1, fragment_diatary_pw1, fragment_height_weight,fragment_na;

    View basicclick, pregnentclick, childclick,pd;

    RelativeLayout relativessbc, relativeDiversity, relativeHeight,holdingTabs;
    Dialog dialogCoupon;

    String pwActive_subStage;
    String errorCode="0";

    TextView anc_date, acndate,muachole;
    LinearLayout linearMua;
    private int mYear, mMonth, mDay;
    Spinner spinner_consult_self, spinner_spend_food,spinner_spend_on_bf;

    CheckBox a_food,b_food,c_food,d_food,e_food,f_food,g_food,h_food,i_food,j_food,k_food,l_food,m_food;
    EditText a_no,b_no,c_no,d_no,e_no,f_no,g_no,h_no,i_no,j_no,k_no,l_no,m_no;

    String af,bf,cf,df,ef,ff,gf,hf,i_f,jf,kf,lf,mf;
    Integer an,bn,cn,dn,en,fn,gn,hn,in,jn,kn,ln,mn;

    EditText height,weight;

    Button PW1Button,PW2Button,PW3Button,PW4Button;
    String subStageMode;
    Button save;
    ImageView naButton;

    String spinner_consult_selfString,spend_on_bfString,sepndOfFoodString;
    int spinner_consult_selfInteger,spentOnFoodInt;
    TextView textLmpdate;
    CheckBox checkbox,chcekedNAM;
    Spinner pw1_yesno;
    LinearLayout anetalcheck;
    String pw1_yesnoString;
    int pw1_yesNoInt;
    EditText pregnetNumnber;



    String datePresent;

    Spinner placeofDelivery,sexOfchild,wasChildBorn,birthBreast,firstyellowFeed;
    String DeliveryPlace,childSex,wasChildBornString;
    int deliveryPlaceItemSelected,childSexItemSelected,wasChildBornPosition;
    private int mYear2, mMonth2, mDay2;

    EditText nameOfChild,orderOfBirth,childWeight;
    TextView dateOfDelivery,addchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregant_women_footer);


        fragment_preliminary_pw1 = findViewById(R.id.sbcclayout);
        fragment_diatary_pw1 = findViewById(R.id.dietaryLyout);
        fragment_height_weight = findViewById(R.id.heightWeightLayout);
        fragment_na = findViewById(R.id.NaFormButtonLayout);

        linearMua = (LinearLayout) findViewById(R.id.linearMuac);
        muachole = (TextView) findViewById(R.id.muacholer);

        if(getIntent().getStringExtra("getstage").equalsIgnoreCase("pw")){

            linearMua.setVisibility(View.INVISIBLE);
                    muachole.setVisibility(View.INVISIBLE);
        }

        textLmpdate = (TextView) findViewById(R.id.date);
        textLmpdate.setText("LMP : "+getIntent().getStringExtra("lmpDate"));

        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));


        basicclick = (View) findViewById(R.id.basicclick);
        pregnentclick = (View) findViewById(R.id.pregnentclick);
        childclick = (View) findViewById(R.id.childclick);
        pd = (View)findViewById(R.id.pTod);



        relativessbc = (RelativeLayout) findViewById(R.id.relativessbc);
        relativeDiversity = (RelativeLayout) findViewById(R.id.relativeDiversity);
        relativeHeight = (RelativeLayout) findViewById(R.id.relativeHeight);
        holdingTabs = (RelativeLayout) findViewById(R.id.holdingTabs);

        PW1Button = (Button) findViewById(R.id.PW1Button);
                PW2Button= (Button) findViewById(R.id.PW2Button);
                PW3Button= (Button) findViewById(R.id.PW3Button);
                PW4Button= (Button) findViewById(R.id.PW4Button);

        naButton=(ImageView) findViewById(R.id.naButton);



        checkbox = (CheckBox)findViewById(R.id.checkbox);
        chcekedNAM=(CheckBox)findViewById(R.id.chcekedNAM);

        pw1_yesno = (Spinner) findViewById(R.id.pw1_yesno);
        anetalcheck = (LinearLayout) findViewById(R.id.anetalcheck);
        List<String> listanetalcheck = new ArrayList<String>();
        listanetalcheck.add("--Select Options--");
        listanetalcheck.add("Miscarriage/ abortion");
        listanetalcheck.add("Death");
        listanetalcheck.add("Migrated elsewhere");

        ArrayAdapter<String> dataAdapterlistanetalcheck = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listanetalcheck);
        dataAdapterlistanetalcheck.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pw1_yesno.setAdapter(dataAdapterlistanetalcheck);

        pw1_yesno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pw1_yesnoString=String.valueOf(parent.getSelectedItem());
                pw1_yesNoInt = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbox.isChecked()){
                    pw1_yesno.setVisibility(View.VISIBLE);
                    anetalcheck.setVisibility(View.INVISIBLE);

                }
                else{
                    anetalcheck.setVisibility(View.VISIBLE);
                    pw1_yesno.setVisibility(View.INVISIBLE);
                }

            }
        });

        if(checkbox.isChecked()){
            pw1_yesno.setVisibility(View.VISIBLE);

        }






//        datePresent;

        anc_date = (TextView) findViewById(R.id.anc_date);
        anc_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                anc_date.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear = calender.get(Calendar.YEAR);
                mMonth = calender.get(Calendar.MONTH);
                mDay = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(PregantWomenFooter.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                anc_date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
                dpd.show();
            }
        });


        spinner_spend_food = (Spinner) findViewById(R.id.spinner_spend_food);
        List<String> list1 = new ArrayList<String>();
        list1.add("--Select Options--");
        list1.add("Less than Rs. 500 per month");
        list1.add("Rs. 500- 1,000 per month");
        list1.add("Rs. 1000 – 2000 per month");
        list1.add("Rs. 2000 – 4000 per month");
        list1.add("Rs. 4000 – 5,000 per month");
        list1.add("Rs. 5,000 and above");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_spend_food.setAdapter(dataAdapter1);

        spinner_spend_food.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sepndOfFoodString = String.valueOf(parent.getItemAtPosition(position));

                spentOnFoodInt= parent.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner_spend_on_bf=(Spinner) findViewById(R.id.spinner_spend_on_bf);


        List<String> bfList = new ArrayList<>();
        bfList.add("--Select Options--");
        bfList.add("Yes");
        bfList.add("No");

        ArrayAdapter<String> datadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bfList);
        datadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_spend_on_bf.setAdapter(datadapter);

        spinner_spend_on_bf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                spend_on_bfString= String.valueOf(parent.getItemAtPosition(position));

                if(spend_on_bfString.equalsIgnoreCase("yes")){

                    spend_on_bfString="Y";
                }
                if(spend_on_bfString.equalsIgnoreCase("No")){

                    spend_on_bfString="N";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_consult_self = (Spinner) findViewById(R.id.spinner_consult_self);
        List<String> list = new ArrayList<String>();
        list.add("--Select Options--");
        list.add("Yes");
        list.add("No");
        list.add("Can't Remember");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_consult_self.setAdapter(dataAdapter);

        spinner_consult_self.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                spinner_consult_selfString = String.valueOf(parent.getItemAtPosition(position));
                 if(spinner_consult_selfString.equalsIgnoreCase("yes")){
                     spinner_consult_selfString="Y";
                 }
                 if(spinner_consult_selfString.equalsIgnoreCase("No")){
                     spinner_consult_selfString="N";
                 }
                //
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
        });

        a_no= (EditText) findViewById(R.id.a_no);
        b_no= (EditText) findViewById(R.id.b_no);
        c_no= (EditText) findViewById(R.id.c_no);
        d_no= (EditText) findViewById(R.id.d_no);
        e_no= (EditText) findViewById(R.id.e_no);
        f_no= (EditText) findViewById(R.id.f_no);
        g_no= (EditText) findViewById(R.id.g_no);
        h_no= (EditText) findViewById(R.id.h_no);
        i_no= (EditText) findViewById(R.id.i_no);
        j_no= (EditText) findViewById(R.id.j_no);
        k_no= (EditText) findViewById(R.id.k_no);
        l_no= (EditText) findViewById(R.id.l_no);
        m_no= (EditText) findViewById(R.id.m_no);


        a_food= (CheckBox) findViewById(R.id.a_food);
        a_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                if(a_food.isChecked()){
                    a_no.setVisibility(View.VISIBLE);
                }else{
                    a_no.setVisibility(View.GONE);
                }

            }
          }
        );

        b_food= (CheckBox) findViewById(R.id.b_food);
        b_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(b_food.isChecked()){
                                                      b_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      b_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        c_food= (CheckBox) findViewById(R.id.c_food);
        c_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(c_food.isChecked()){
                                                      c_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      c_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        d_food= (CheckBox) findViewById(R.id.d_food);
        d_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(d_food.isChecked()){
                                                      d_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      d_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        e_food= (CheckBox) findViewById(R.id.e_food);
        e_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(e_food.isChecked()){
                                                      e_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                     e_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        f_food= (CheckBox) findViewById(R.id.f_food);
        f_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(f_food.isChecked()){
                                                      f_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      f_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        g_food= (CheckBox) findViewById(R.id.g_food);
        g_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(g_food.isChecked()){
                                                      g_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      g_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        h_food= (CheckBox) findViewById(R.id.h_food);
        h_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(h_food.isChecked()){
                                                      h_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      h_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        i_food= (CheckBox) findViewById(R.id.i_food);
        i_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(i_food.isChecked()){
                                                      i_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      i_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        j_food= (CheckBox) findViewById(R.id.j_food);
        j_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(j_food.isChecked()){
                                                      j_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      j_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        k_food= (CheckBox) findViewById(R.id.k_food);
        k_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(k_food.isChecked()){
                                                      k_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      k_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        l_food= (CheckBox) findViewById(R.id.l_food);
        l_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(l_food.isChecked()){
                                                      l_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      l_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );

        m_food= (CheckBox) findViewById(R.id.m_food);
        m_food.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                  if(m_food.isChecked()){
                                                      m_no.setVisibility(View.VISIBLE);
                                                  }else{
                                                      m_no.setVisibility(View.GONE);
                                                  }

                                              }
                                          }
        );








        height= (EditText) findViewById(R.id.height);
                weight= (EditText) findViewById(R.id.weight);



                //add child

        dateOfDelivery= (TextView) findViewById(R.id.dateOfDelivery);

        nameOfChild = (EditText) findViewById(R.id.nameOfChild);
        orderOfBirth= (EditText) findViewById(R.id.orderOfBirth);
        childWeight = (EditText) findViewById(R.id.childWeight);
        addchild = (TextView) findViewById(R.id.addchild);
        pregnetNumnber = (EditText)findViewById(R.id.pregnetNumnber);



        dateOfDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calender = Calendar.getInstance();
                mYear2 = calender.get(Calendar.YEAR);
                mMonth2 = calender.get(Calendar.MONTH);
                mDay2 = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(PregantWomenFooter.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                String month, day;
                                if ((monthOfYear + 1) < 10) {
                                    month = "0" + String.valueOf(monthOfYear + 1);
                                } else {
                                    month = String.valueOf(monthOfYear + 1);
                                }

                                if (dayOfMonth < 10) {
                                    day = "0" + String.valueOf(dayOfMonth);

                                } else {
                                    day = String.valueOf(dayOfMonth);
                                }
                                dateOfDelivery.setText(year + "-"
                                        + month + "-" + day);
//                                getAge(year, (monthOfYear + 1),dayOfMonth );
                            }
                        }, mYear2, mMonth2, mDay2);
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

                dpd.show();
            }
        });


                placeofDelivery = (Spinner)  findViewById(R.id.placeofDelivery);
//
        List<String> listplaceofDelivery = new ArrayList<String>();
        listplaceofDelivery.add("--Select Options--");

        listplaceofDelivery.add("Hospital/ PHC/CHC/ Private clinic");
        listplaceofDelivery.add("Home");

        ArrayAdapter<String> postofDelivery = new ArrayAdapter<String>(PregantWomenFooter.this,
                android.R.layout.simple_spinner_item, listplaceofDelivery);
        postofDelivery.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        placeofDelivery.setAdapter(postofDelivery);

        placeofDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DeliveryPlace=String.valueOf(parent.getSelectedItem());
                deliveryPlaceItemSelected=parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sexOfchild= (Spinner) findViewById(R.id.sexOfchild);
        List<String> listsexOfchild = new ArrayList<String>();
        listsexOfchild.add("--Select Options--");

        listsexOfchild.add("Male");
        listsexOfchild.add("Female");
        listsexOfchild.add("Intersex");

        ArrayAdapter<String> dataAdaptersexOfchild = new ArrayAdapter<String>(PregantWomenFooter.this,
                android.R.layout.simple_spinner_item, listsexOfchild);
        dataAdaptersexOfchild.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexOfchild.setAdapter(dataAdaptersexOfchild);

        sexOfchild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                childSex=String.valueOf(parent.getSelectedItem());
                childSexItemSelected=parent.getSelectedItemPosition();

                Log.d("SExofChild",""+childSexItemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        wasChildBorn=(Spinner)findViewById(R.id.wasChildBorn);
//
        List<String> listwasChildBorn = new ArrayList<String>();
        listwasChildBorn.add("--Select Options--");

        listwasChildBorn.add("Yes");
        listwasChildBorn.add("No");


        ArrayAdapter<String> dataAdapterwasChildBorn = new ArrayAdapter<String>(PregantWomenFooter.this,
                android.R.layout.simple_spinner_item, listwasChildBorn);
        dataAdapterwasChildBorn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wasChildBorn.setAdapter(dataAdapterwasChildBorn);

        wasChildBorn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                wasChildBornPosition=parent.getSelectedItemPosition();
                wasChildBornString=String.valueOf(parent.getSelectedItem());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        birthBreast= (Spinner) findViewById(R.id.birthBreast);

        List<String> listbirthBreast = new ArrayList<String>();

        listbirthBreast.add("--Select Options--");
        listbirthBreast.add("Yes");
        listbirthBreast.add("No");
//        listbirthBreast.add("Intersexed");

        ArrayAdapter<String> dataAdapterbirthBreast = new ArrayAdapter<String>(PregantWomenFooter.this,
                android.R.layout.simple_spinner_item, listbirthBreast);
        dataAdapterbirthBreast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthBreast.setAdapter(dataAdapterbirthBreast);


        firstyellowFeed= (Spinner) findViewById(R.id.firstyellowFeed);

        List<String> listfirstyellowFeed = new ArrayList<String>();

        listfirstyellowFeed.add("--Select Options--");
        listfirstyellowFeed.add("Yes");
        listfirstyellowFeed.add("No");
//        listfirstyellowFeed.add("Intersexed");

        ArrayAdapter<String> dataAdapterfirstyellowFeed = new ArrayAdapter<String>(PregantWomenFooter.this,
                android.R.layout.simple_spinner_item, listfirstyellowFeed);
        dataAdapterfirstyellowFeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstyellowFeed.setAdapter(dataAdapterfirstyellowFeed);
//




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

                if (pwActive_subStage.equalsIgnoreCase("PD")) {
                    errorCode=childformValidation();
                    if (errorCode.equalsIgnoreCase("0")) {

                        String cSex;
                        if (childSex.equalsIgnoreCase("male")) {

                            cSex="M";
                        }else if(childSex.equalsIgnoreCase("Female")){
                            cSex="F";
                        }else{
                            cSex="I";
                        }
                            String childid=createMemberId(getIntent().getStringExtra("familyId"));
                        String childIntoMemeber="INSERT INTO memberbasic (MEMBERS_ID,FAMILY_ID,NAME,DOR,DOENTRY,DOB,SEX,MOTHER_ID,STATUS,STAGE,IS_TO_TRACK," +
                                "SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) VALUES ('"+ childid +"','" +
                                ""+getIntent().getStringExtra("familyId")+"','"+ nameOfChild.getText().toString()+ "','" +
                                ""+ new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()) +"','" +
                                ""+ new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())+ "','" +
                                ""+  dateOfDelivery.getText().toString() +"','"+ cSex +"','"+ getIntent().getStringExtra("members_id") +
                                "','LM','LM','Y','"+ new Login().surveyerId +"','"+
                                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) +"','Mobile','','N')";

                        String insertChildExtra="INSERT INTO childextra (MEMBERS_ID,DODELIVERY,DELIVERY_PLACE,CHILD_ORDER,BIRTH_WT,FULL_TERM,IS_APPROVED," +
                                "IS_NEW) VALUES ('"+ childid +"','"+ dateOfDelivery.getText().toString() +"'," +
                                "'"+  String.valueOf(deliveryPlaceItemSelected)+ "','"+ orderOfBirth.getText().toString()+ "','" +
                                ""+ childWeight.getText().toString()+"','"+ String.valueOf(wasChildBornPosition)+ "','','N')";

                        String pwtrackoff="UPDATE memberbasic SET IS_TO_TRACK = 'N',SURVEYOR_ID = '"+ new Login().surveyerId +"',TIME_STAMP = '"+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date())  +"',IS_NEW = 'E' WHERE MEMBERS_ID = '"+ getIntent().getStringExtra("members_id") +"'" ;


                        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor c1 = dbs.rawQuery(childIntoMemeber, null);
                        c1.moveToFirst();
                        c1.close();

                        Cursor c2 = dbs.rawQuery(insertChildExtra, null);
                        c2.moveToFirst();
                        c2.close();

                        Cursor c3 = dbs.rawQuery(pwtrackoff, null);
                        c3.moveToFirst();
                        c3.close();


                        Toast.makeText(PregantWomenFooter.this, "Recorded updated Succesfully", Toast.LENGTH_SHORT).show();


                        Intent intentbenefit = new Intent(getApplicationContext(),DashBoard.class);
                        startActivity(intentbenefit);
                        finish();

                    }
                    }


                else if (pwActive_subStage.equalsIgnoreCase("na")) {
                    errorCode = ValidateNa();
                    if (errorCode.equalsIgnoreCase("0")) {

                        String is_available, ancValue = "", IS_TO_TRACK;
                        if (checkbox.isChecked()) {
                            is_available = "N";
                            IS_TO_TRACK = "N";
                        } else {
                            is_available = "Y";
                            ancValue = "N";
                            IS_TO_TRACK = "Y";
                        }
                        String checkRecords = "select * from pw_tracking where sub_stage='" + getIntent().getStringExtra("current_sub_stage") + "' and pregnancy_id ='" + getIntent().getStringExtra("pregnancy_id") + "'";
                        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                        Cursor c = dbs.rawQuery(checkRecords, null);

                        int count = c.getCount();
//                        Log.d("")
                        String NaQuery;
                        if (count == 0) {


                            NaQuery = "INSERT INTO PW_TRACKING (PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,NA_REASON,IS_ANC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES ( '" + getIntent().getStringExtra("pregnancy_id") +
                                    "','" + getIntent().getStringExtra("members_id") + "', 'PW', '" + getIntent().getStringExtra("current_sub_stage") + "','" + is_available + "', " + pw1_yesNoInt + ",'" + ancValue + "', '" + new Login().surveyerId + "','" +
                                    new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault()).format(new Date()) + "','Mobile','N','','')";

                        } else {

                            NaQuery = "UPDATE PW_TRACKING SET IS_AVAILABLE = '" + is_available + "', NA_REASON = " + pw1_yesNoInt + ",IS_ANC = '" + ancValue + "',IS_NEW = 'N', TIME_STAMP='" + new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault()).format(new Date()) + "',SURVEYOR_ID='" + new Login().surveyerId + "',SOURCE='Mobile' " +
                                    "WHERE PREGNANCY_ID = '" + getIntent().getStringExtra("pregnancy_id") + "' AND SUB_STAGE = '" + getIntent().getStringExtra("current_sub_stage") + "'";
                        }

                        SQLiteDatabase dbs4 = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
                        Cursor cdbs4 = dbs4.rawQuery(NaQuery, null);

                        cdbs4.moveToFirst();
                        cdbs4.close();

                        String updateSub_StageInMemberBasic = "update memberbasic set sub_stage='" + getIntent().getStringExtra("current_sub_stage") + "', IS_TO_TRACK='" + IS_TO_TRACK + "' WHERE MEMBERS_ID=(SELECT MEMBERS_ID FROM PREGNANT WHERE PREGNANCY_ID='" + getIntent().getStringExtra("pregnancy_id") + "')";
                        SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
                        Cursor cdsbs = dsbs.rawQuery(updateSub_StageInMemberBasic, null);
                        cdsbs.moveToFirst();
                        dsbs.close();

                        Toast.makeText(PregantWomenFooter.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();


//                    total = c.getCount();  int
                        c.close();
                    }
                } else {

                    errorCode = checkValidation();
                    if (errorCode.equalsIgnoreCase("0")) {

                        dietSelection();

                        if (subStageMode.equalsIgnoreCase("add")) {


                            String inseertPwTrack = "insert into  pw_tracking(pregnancy_id,members_id,stage,sub_stage,is_available,na_reason,is_anc," +
                                    "anc_date,if_counsel_on_selffeed,if_counsel_on_bf,spend_on_food,height,weight,surveyor_id,time_stamp,source," +
                                    "is_new,is_edited,is_approved) values('" + getIntent().getStringExtra("pregnancy_id") + "','"
                                    + getIntent().getStringExtra("members_id") + "'," + "'PW'" + ",'" + pwActive_subStage + "'," + "'Y'" + "," + "'' " + "," + "'Y'" + ",'" +
                                    anc_date.getText().toString() + "','" + spinner_consult_selfString + "','" + spend_on_bfString + "'," + spentOnFoodInt + "," +
                                    Integer.parseInt(height.getText().toString()) + "," + Integer.parseInt(weight.getText().toString()) + "," + new Login().surveyerId + ","
                                    + new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault()).format(new Date()) + ","
                                    + "'Mobile'" + "," + "'Y'" + "," + "''" + "," + "''" + ")";

                            Log.d("insertQuery", inseertPwTrack);
//
                            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor c = dbs.rawQuery(inseertPwTrack, null);

                            c.moveToFirst();
//                    total = c.getCount();  int
                            c.close();


                            String deitInsert = "INSERT INTO DIET (PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                                    " ('" + getIntent().getStringExtra("pregnancy_id") + "','" + getIntent().getStringExtra("members_id") + "','PW','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
                                    "'," + bn + ",'" + cf + "'," + cn + ",'" + df + "'," + dn + ",'" + ef + "'," +
                                    en + ",'" + ff + "'," + fn + ",'" + gf + "'," + gn + ",'" + hf + "'," + hn + ",'"
                                    + i_f + "'," + in + ",'" + jf + "'," + jn + ",'" + kf + "'," + kn + ",'" + lf + "'," +
                                    ln + ",'" + mf + "'," + mn + ",'MOBILE','N','','')";

                            Log.d("InsertQuerydiet", deitInsert);

//                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor cinsert = dbs.rawQuery(deitInsert, null);

                            if (cinsert.moveToFirst()) {

                            }
//                    total = c.getCount();  int
                            cinsert.close();


                            String updateSub_StageInMemberBasic = "update memberbasic set sub_stage='" + getIntent().getStringExtra("current_sub_stage") + "' WHERE MEMBERS_ID=(SELECT MEMBERS_ID FROM PREGNANT WHERE PREGNANCY_ID='" + getIntent().getStringExtra("pregnancy_id") + "')";
                            SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor c3 = dsbs.rawQuery(updateSub_StageInMemberBasic, null);

                            if (c3.moveToFirst()) {

                            }
//                    c3.moveToFirst();
                            c3.close();
                            Toast.makeText(PregantWomenFooter.this, "Record Saved Succesfully", Toast.LENGTH_SHORT).show();

                            subStageMode = "edit";

                        } else if (subStageMode.equalsIgnoreCase("edit")) {

                            String updateTrackingPw = "UPDATE PW_TRACKING SET IS_AVAILABLE = 'Y', NA_REASON = '',IS_ANC = 'Y', " +
                                    "ANC_DATE = '" + anc_date.getText().toString() + "', IF_COUNSEL_ON_SELFFEED = " +
                                    "'" + spinner_consult_selfString + "',IF_COUNSEL_ON_BF = '" + spend_on_bfString + "', SPEND_ON_FOOD = '" +
                                    spentOnFoodInt + "', HEIGHT = '" + Integer.parseInt(height.getText().toString()) + "',WEIGHT = " +
                                    "'" + Integer.parseInt(weight.getText().toString()) + "', SURVEYOR_ID = '" +
                                    new Login().surveyerId + "', TIME_STAMP = '" + new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault()).format(new Date()) + "',SOURCE = 'Mobile', IS_APPROVED = '' WHERE PREGNANCY_ID = '" + getIntent().getStringExtra("pregnancy_id") + "' AND SUB_STAGE = '" + pwActive_subStage + "'";


                            SQLiteDatabase dsbss = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Cursor updatedtracking = dsbss.rawQuery(updateTrackingPw, null);
                            updatedtracking.moveToFirst();
//                            dsbss.close();
String checkrecordInDiet= " Select * from diet  WHERE PREGNANCY_ID = '" + getIntent().getStringExtra("pregnancy_id") +
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
            "" + ln + ", FEED_M = '" + mf + "',   FEED_M_NOS = " + mn + " WHERE PREGNANCY_ID = '" + getIntent().getStringExtra("pregnancy_id") +
            "' AND SUB_STAGE = '" + pwActive_subStage + "'";
}
else{

    updateDiet = "INSERT INTO DIET (PREGNANCY_ID,MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
            " ('" + getIntent().getStringExtra("pregnancy_id") + "','" + getIntent().getStringExtra("members_id") + "','PW','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
            "'," + bn + ",'" + cf + "'," + cn + ",'" + df + "'," + dn + ",'" + ef + "'," +
            en + ",'" + ff + "'," + fn + ",'" + gf + "'," + gn + ",'" + hf + "'," + hn + ",'"
            + i_f + "'," + in + ",'" + jf + "'," + jn + ",'" + kf + "'," + kn + ",'" + lf + "'," +
            ln + ",'" + mf + "'," + mn + ",'MOBILE','N','','')";
}
                            SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                            Log.d("updatedDiet", updateDiet);


                            Cursor updatedDiet = dsbs.rawQuery(updateDiet, null);
                            updatedDiet.moveToFirst();
                            dsbs.close();

                            Toast.makeText(PregantWomenFooter.this, "Edited Successfully", Toast.LENGTH_SHORT).show();


                        }
//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);
                    }
                }
            }
        });
        PW1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW1";

                fragment_na.setVisibility(View.INVISIBLE);
                holdingTabs.setVisibility(View.VISIBLE);
                pd.setVisibility(View.INVISIBLE);

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });

        PW2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW2";
                fragment_na.setVisibility(View.INVISIBLE);
                pd.setVisibility(View.INVISIBLE);
                holdingTabs.setVisibility(View.VISIBLE);

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });

        PW3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW3";
                fragment_na.setVisibility(View.INVISIBLE);
                holdingTabs.setVisibility(View.VISIBLE);
                pd.setVisibility(View.INVISIBLE);


                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });
        PW4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW4";
                fragment_na.setVisibility(View.INVISIBLE);
                holdingTabs.setVisibility(View.VISIBLE);
                pd.setVisibility(View.INVISIBLE);

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });

        naButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="na";
                fragment_na.setVisibility(View.VISIBLE);
                             holdingTabs.setVisibility(View.INVISIBLE);
                pd.setVisibility(View.INVISIBLE);

            }
        });

        setButtonForStages(getIntent().getStringExtra("pregnancy_id"),getIntent().getStringExtra("current_sub_stage"));

    }


    public void sscclickedForm() {

//        Log.d("Validation","inserted inside fucntion");

        fragment_preliminary_pw1.setVisibility(View.VISIBLE);
        fragment_diatary_pw1.setVisibility(View.GONE);
        fragment_height_weight.setVisibility(View.GONE);

        basicclick.setVisibility(View.VISIBLE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("BASIC");

    }

    public void diversityClickForm() {

//        activatedREgistrationForm=2;
        fragment_preliminary_pw1.setVisibility(View.GONE);
        fragment_diatary_pw1.setVisibility(View.VISIBLE);
        fragment_height_weight.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.VISIBLE);
        childclick.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("PREGNANT");


    }


    public void heightPregnentForm() {


//        activatedREgistrationForm=3;
        fragment_height_weight.setVisibility(View.VISIBLE);
        fragment_preliminary_pw1.setVisibility(View.GONE);
        fragment_diatary_pw1.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.VISIBLE);
//        getSupportActionBar().setTitle("CHILD");
    }


    public String checkValidation(){
errorCode="0";
        if(anc_date.getText().length()==0){
anc_date.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Select ANC Date", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(spinner_consult_selfString.equalsIgnoreCase("--Select Options--")){
            spinner_consult_self.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Select  Counseled", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(spend_on_bfString.equalsIgnoreCase("--Select Options--")){
            spinner_spend_on_bf.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Select Feeding", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(sepndOfFoodString.equalsIgnoreCase("--Select Options--")){
            spinner_spend_food.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Select Amount Spend on Fooding", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else  if(a_no.getVisibility()==View.VISIBLE && a_no.getText().length()==0){
            a_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(b_no.getVisibility()==View.VISIBLE && b_no.getText().length()==0){
            b_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(c_no.getVisibility()==View.VISIBLE && c_no.getText().length()==0){
            c_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(d_no.getVisibility()==View.VISIBLE && d_no.getText().length()==0){
            d_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(e_no.getVisibility()==View.VISIBLE && e_no.getText().length()==0){
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(f_no.getVisibility()==View.VISIBLE && f_no.getText().length()==0){
            f_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(g_no.getVisibility()==View.VISIBLE && g_no.getText().length()==0){
            g_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(h_no.getVisibility()==View.VISIBLE && h_no.getText().length()==0){
            h_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(i_no.getVisibility()==View.VISIBLE && i_no.getText().length()==0){
            i_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(j_no.getVisibility()==View.VISIBLE && j_no.getText().length()==0){
            j_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(k_no.getVisibility()==View.VISIBLE && k_no.getText().length()==0){
            k_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(l_no.getVisibility()==View.VISIBLE && l_no.getText().length()==0){
            l_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }else if(m_no.getVisibility()==View.VISIBLE && m_no.getText().length()==0){
            m_no.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
            errorCode="1";

        }else if(height.getText().length()==0){

            height.requestFocus();
            Toast.makeText(PregantWomenFooter.this, "Enter Height", Toast.LENGTH_SHORT).show();
            errorCode="1";

        }else if(weight.getText().length()==0){
            Toast.makeText(PregantWomenFooter.this, "Enter Weight", Toast.LENGTH_SHORT).show();
            errorCode="1";
        }
        return errorCode;
    }


    public void setButtonForStages(String pregnancy_id,String current_subStage ){


        String query="Select T.MEMBERS_ID,T.PREGNANCY_ID,T.STAGE,T.SUB_STAGE FROM pregnant P \n" +
                "LEFT JOIN pw_tracking T ON P.PREGNANCY_ID=T.PREGNANCY_ID\n" +
                "WHERE P.MEMBERS_ID='"+pregnancy_id+"'";


        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(query,null);
        int total = c.getCount();

        Log.d("pregnentStatus",current_subStage+"");
        Log.d("pregnentStatus",pregnancy_id+"");
        if (total >= 1) {

//        int i = 0;
            if (c.moveToFirst()) {
                do {
//             arrayNoOfChild.add(c.getString(c.getColumnIndex("Members_id")));
                  if(c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("PW1")){
                      PW1Button.setBackgroundResource(R.drawable.greencirclecolor);
                      PW1Button.setEnabled(true);
                  }

                    if(c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("PW2")){
                        PW2Button.setBackgroundResource(R.drawable.greencirclecolor);
                        PW2Button.setEnabled(true);
                    }

                    if(c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("PW3")){
                        PW3Button.setBackgroundResource(R.drawable.greencirclecolor);
                        PW3Button.setEnabled(true);
                    }
                    if(c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("PW4")){
                        PW4Button.setBackgroundResource(R.drawable.greencirclecolor);
                        PW4Button.setEnabled(true);
                    }


//
                } while (c.moveToNext());
            }






        }
        if(current_subStage.equalsIgnoreCase("PW1")){
            PW1Button.setBackgroundResource(R.drawable.circlebutton);
            PW1Button.setEnabled(true);
            pwActive_subStage="PW1";
        }
        if(current_subStage.equalsIgnoreCase("PW2")){
            Log.d("pregnentStatus","inseretdr");
            PW2Button.setBackgroundResource(R.drawable.circlebutton);
            PW2Button.setEnabled(true);
            pwActive_subStage="PW2";
        }
        if(current_subStage.equalsIgnoreCase("PW3")){
            PW3Button.setBackgroundResource(R.drawable.circlebutton);
            PW3Button.setEnabled(true);
            pwActive_subStage="PW3";
        } if(current_subStage.equalsIgnoreCase("PW4")) {
            PW4Button.setBackgroundResource(R.drawable.circlebutton);
            PW4Button.setEnabled(true);
            pwActive_subStage="PW4";
        }

        stageDataByPWID(pregnancy_id,pwActive_subStage);

    }

    public void stageDataByPWID(String pw_id,String sub_stage){


//        c
//        String

        String query="select is_anc,anc_date,IF_COUNSEL_ON_SELFFEED,IF_COUNSEL_ON_BF,SPEND_ON_FOOD,HEIGHT,WEIGHT,T.IS_APPROVED,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS FROM PW_TRACKING T LEFT JOIN DIET D ON T.PREGNANCY_ID=D.PREGNANCY_ID AND T.SUB_STAGE=D.SUB_STAGE WHERE T.PREGNANCY_ID='"+pw_id+"' AND T.SUB_STAGE='"+sub_stage+"' ";


        Log.d("PregnentWomen",query);
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(query,null);
        int total = c.getCount();
        Log.d("PregnentWomen",""+total);

//        Log.d("pregnentStatus",current_subStage+"");
//        Log.d("pregnentStatus",pregnancy_id+"");
        if (total >= 1) {

            subStageMode="edit";

            Log.d("PregnentWomen",""+total);


//        int i = 0;
            if (c.moveToFirst()) {
                do {

//                    Log.d("CheckDate",""+ c.getString(c.getColumnIndex("anc_date")));
                    if(String.valueOf((c.getString(c.getColumnIndex("anc_date"))))!="null"){
                        anc_date.setText(String.valueOf(c.getString(c.getColumnIndex("anc_date"))));
                    }

                    try {
                        if (c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("Y")) {
                            spinner_consult_self.setSelection(1);
                        }
                    }catch (Exception e) {
                    }

                    try {
                        if (c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("N")) {
                            spinner_consult_self.setSelection(2);
                        }
                    }catch (Exception e){

                    }
                    try {

                        if (c.getString(c.getColumnIndex("if_counsel_on_bf")).equalsIgnoreCase("Y")) {
                            spinner_spend_on_bf.setSelection(1);
                        }
                    }catch (Exception e){

                    }
                    try {
                        if (c.getString(c.getColumnIndex("if_counsel_on_bf")).equalsIgnoreCase("N")) {
                            spinner_spend_on_bf.setSelection(2);
                        }
                    }catch (Exception e){

                    }
try {
    spinner_spend_food.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("spend_on_food"))));
}catch(Exception e){

}
try {
    height.setText(c.getString(c.getColumnIndex("height")));
    weight.setText(c.getString(c.getColumnIndex("weight")));
}catch (Exception e){

}
//}try
    try {
        if (c.getString(c.getColumnIndex("feed_a")).equalsIgnoreCase("Y")) {
            a_food.setChecked(true);
            a_no.setVisibility(View.VISIBLE);
            a_no.setText(c.getString(c.getColumnIndex("feed_a_nos")));
        } else {
            a_food.setChecked(false);
            a_no.setVisibility(View.INVISIBLE);
            a_no.setText("");
        }


                    if(c.getString(c.getColumnIndex("feed_b")).equalsIgnoreCase("Y")){
                            Log.d("getFeed",c.getString(c.getColumnIndex("feed_b")));
                            Log.d("getFeed",c.getString(c.getColumnIndex("feed_b_nos")));
                        b_food.setChecked(true);
                        b_no.setVisibility(View.VISIBLE);
                        b_no.setText(c.getString(c.getColumnIndex("feed_b_nos")));
                    }
                    else{
                        b_food.setChecked(false);
                        b_no.setVisibility(View.INVISIBLE);
                        b_no.setText("");
                    }

                    if(c.getString(c.getColumnIndex("feed_c")).equalsIgnoreCase("Y")){
                        c_food.setChecked(true);
                        c_no.setVisibility(View.VISIBLE);
                        c_no.setText(c.getString(c.getColumnIndex("feed_c_nos")));
                    }
                    else{
                        c_food.setChecked(false);
                        c_no.setVisibility(View.INVISIBLE);
                        c_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_d")).equalsIgnoreCase("Y")){
                        d_food.setChecked(true);
                        d_no.setVisibility(View.VISIBLE);
                        d_no.setText(c.getString(c.getColumnIndex("feed_d_nos")));
                    } else{
                        d_food.setChecked(false);
                        d_no.setVisibility(View.INVISIBLE);
                        d_no.setText("");
                    }

                    if(c.getString(c.getColumnIndex("feed_e")).equalsIgnoreCase("Y")){
                        e_food.setChecked(true);
                        e_no.setVisibility(View.VISIBLE);
                        e_no.setText(c.getString(c.getColumnIndex("feed_e_nos")));
                    }

                    else{
                        e_food.setChecked(false);
                        e_no.setVisibility(View.INVISIBLE);
                        e_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_f")).equalsIgnoreCase("Y")){
                        f_food.setChecked(true);
                        f_no.setVisibility(View.VISIBLE);
                        f_no.setText(c.getString(c.getColumnIndex("feed_f_nos")));
                    }

                    else{
                        f_food.setChecked(false);
                        f_no.setVisibility(View.INVISIBLE);
                        f_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_g")).equalsIgnoreCase("Y")){
                        g_food.setChecked(true);
                        g_no.setVisibility(View.VISIBLE);
                        g_no.setText(c.getString(c.getColumnIndex("feed_g_nos")));
                    }
                    else{
                        g_food.setChecked(false);
                        g_no.setVisibility(View.INVISIBLE);
                        g_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_h")).equalsIgnoreCase("Y")){
                        h_food.setChecked(true);
                        h_no.setVisibility(View.VISIBLE);
                        h_no.setText(c.getString(c.getColumnIndex("feed_h_nos")));
                    }
                    else{
                        h_food.setChecked(false);
                        h_no.setVisibility(View.INVISIBLE);
                        h_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_I")).equalsIgnoreCase("Y")){
                        i_food.setChecked(true);
                        i_no.setVisibility(View.VISIBLE);
                        i_no.setText(c.getString(c.getColumnIndex("feed_i_nos")));
                    }
                    else{
                        i_food.setChecked(false);
                        i_no.setVisibility(View.INVISIBLE);
                        i_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_j")).equalsIgnoreCase("Y")){
                        j_food.setChecked(true);
                        j_no.setVisibility(View.VISIBLE);
                        j_no.setText(c.getString(c.getColumnIndex("feed_j_nos")));
                    }

                    else{
                        j_food.setChecked(false);
                        j_no.setVisibility(View.INVISIBLE);
                        j_no.setText("");
                    }
                    if(c.getString(c.getColumnIndex("feed_k")).equalsIgnoreCase("Y")){
                        k_food.setChecked(true);
                        k_no.setVisibility(View.VISIBLE);
                        k_no.setText(c.getString(c.getColumnIndex("feed_k_nos")));
                    }
                    else{
                        k_food.setChecked(false);
                        k_no.setVisibility(View.INVISIBLE);
                        k_no.setText("");
                    }

                    if(c.getString(c.getColumnIndex("feed_l")).equalsIgnoreCase("Y")){
                        l_food.setChecked(true);
                        l_no.setVisibility(View.VISIBLE);
                        l_no.setText(c.getString(c.getColumnIndex("feed_l_nos")));
                    }
                    else{
                        l_food.setChecked(false);
                        l_no.setVisibility(View.INVISIBLE);
                        l_no.setText("");
                    }

                    if(c.getString(c.getColumnIndex("feed_m")).equalsIgnoreCase("Y")){
                        m_food.setChecked(true);
                        m_no.setVisibility(View.VISIBLE);
                        m_no.setText(c.getString(c.getColumnIndex("feed_m_nos")));
                    }
                    else{
                        m_food.setChecked(false);
                        m_no.setVisibility(View.INVISIBLE);
                        m_no.setText("");
                    }

                    if(c.getString(c.getColumnIndex("is_approved")).equalsIgnoreCase("Y")){
                        save.setEnabled(true);

                    }


    }catch (Exception e){

    Log.d("Holi",e.toString());

    }


                }while (c.moveToNext());

                }
                }
                else {

            subStageMode="add";
        }

    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        dialogCoupon    = new Dialog(PregantWomenFooter.this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.exit_dialog);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);
//        setFinishOnTouchOutside(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));



        Button yes = (Button)dialogCoupon.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();

                Intent intentback = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intentback);
                finish();
            }
        });
        Button no = (Button)dialogCoupon.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCoupon.hide();
            }
        });

        dialogCoupon.show();


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


    public String ValidateNa(){
        errorCode="0";

        if(!checkbox.isChecked()&&!chcekedNAM.isChecked()){
errorCode="1";
            Toast.makeText(PregantWomenFooter.this, "Please One Selection", Toast.LENGTH_SHORT).show();
        }
        if(checkbox.isChecked() && pw1_yesnoString.equalsIgnoreCase("--Select Options--")){

            errorCode="1";
            Toast.makeText(PregantWomenFooter.this, "Select A Value ", Toast.LENGTH_SHORT).show();
        }

     return   errorCode;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {

            pd.setVisibility(View.VISIBLE);
            fragment_na.setVisibility(View.INVISIBLE);
            holdingTabs.setVisibility(View.INVISIBLE);
            pwActive_subStage="PD";

            // do something here
        }
        return super.onOptionsItemSelected(item);
    }


    public String childformValidation(){
        String error="0";
        if(error=="0" && nameOfChild.getText().toString().length()==0){
            error="1";
            Toast.makeText(PregantWomenFooter.this, "Enter Child Name", Toast.LENGTH_SHORT).show();
        }
        if (error == "0" && dateOfDelivery.getText().toString().length() == 0) {

            error ="1";
            Toast.makeText(PregantWomenFooter.this, "Enter Date of Delivery", Toast.LENGTH_SHORT).show();
        }



        if(error=="0" && DeliveryPlace.equalsIgnoreCase("--Select Options--")){

            error="1";
            Toast.makeText(PregantWomenFooter.this, "Enter Delivery Place", Toast.LENGTH_SHORT).show();
        }

        if(error=="0" && orderOfBirth.getText().toString().length()==0){

            error="1";
            Toast.makeText(PregantWomenFooter.this, "Enter Order of Birth", Toast.LENGTH_SHORT).show();
        }

        if(error=="0" && childSex.equalsIgnoreCase("--Select Options--")){

            error="1";
            Toast.makeText(PregantWomenFooter.this, "Enter sex of child ", Toast.LENGTH_SHORT).show();
        }

        if(error=="0" && childWeight.getText().toString().length()==0){

            error="1";
            Toast.makeText(PregantWomenFooter.this, "Enter Child Weight", Toast.LENGTH_SHORT).show();
        }
        if(error=="0" && wasChildBornString.equalsIgnoreCase("--Select Options--")){

            error ="1";
            Toast.makeText(PregantWomenFooter.this, "Select Full Term", Toast.LENGTH_SHORT).show();
        }





        return error;
    }


    public String createMemberId(String familyID){


        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery("SELECT * FROM memberbasic where family_id ='"+familyID+"'" , null);
        c.getCount();
        int count = c.getCount();
//   Log.d("countregister",""+ c.getCount());
        String memberId= familyID+String.format("%02d",count+1);
//            Log.d("member_id",memberId);
        return memberId;
    }



    }
