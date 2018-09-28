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

public class MyActivity extends AppCompatActivity {

    Button ym1, ym2, ym3, ym4, ym5;
    LinearLayout one, two, three, four, five, six,linearCyrrentlyUsing,ImmuniZedLinear;
    Spinner breastfeeding, stopbreastfeeding, contraceptive, currentlyusing, counseledNGO, familyexpence,ImmuniZedLinearSpinner;
   String pwActive_subStage="";
    TextView currentlyUsingTextView,lmpdate;
    Menu menu;

    String breastfeedingString, stopbreastfeedingString, contraceptiveString, currentlyusingString, counseledNGOString, familyexpenceString,ImmuniZedLinearSpinnerString;
int breastfeedingInt, stopbreastfeedingInt, contraceptiveInt, currentlyusingInt, counseledNGOInt, familyexpenceInt,ImmuniZedLinearSpinnerInt;

    View sbcclayout, dietaryLyout, heightWeightLayout,fragmentnaforlmnmy,pTod;
    RelativeLayout relativessbc, relativeDiversity, relativeHeight,holdingTabs;
    View basicclick, pregnentclick, childclick;

    CheckBox a_food,b_food,c_food,d_food,e_food,f_food,g_food,h_food,i_food,j_food,k_food,l_food,m_food;
    EditText a_no,b_no,c_no,d_no,e_no,f_no,g_no,h_no,i_no,j_no,k_no,l_no,m_no;

    String af,bf,cf,df,ef,ff,gf,hf,i_f,jf,kf,lf,mf;
    Integer an,bn,cn,dn,en,fn,gn,hn,in,jn,kn,ln,mn;

    CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;

    View currentlyusingView;

    Dialog dialogCoupon;

    Button saveYM;

    RadioGroup radiogroup;

    String errorCode;
    ImageView naButton;
    String subStageMode;

    EditText weight,  height,muac,solidfoodmonth;
    String methodcontra = "";

    String current_bf,when_stop_bf,CHILD_IMMUNIZATION_STATUS;
    SessionManager session;
    String   pregnentId="";
    EditText pregnetNumnber;
    int mYear2, mMonth2, mDay2;
    TextView profilePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

//        setContentView(R.layout.activity_lm_actvity);
        getSupportActionBar().setTitle("Child : "+getIntent().getStringExtra("name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View fragment_na = findViewById(R.id.NaFormButtonLayout);

        session = new SessionManager(getApplicationContext());

        basicclick = (View) findViewById(R.id.basicclick);
        pregnentclick = (View) findViewById(R.id.pregnentclick);
        childclick = (View) findViewById(R.id.childclick);

        height= (EditText) findViewById(R.id.height);
        weight= (EditText) findViewById(R.id.weight);
        muac= (EditText) findViewById(R.id.muac);

        linearCyrrentlyUsing = (LinearLayout) findViewById(R.id.linearCyrrentlyUsing);

        relativessbc = (RelativeLayout) findViewById(R.id.relativessbc);
        relativeDiversity = (RelativeLayout) findViewById(R.id.relativeDiversity);
        relativeHeight = (RelativeLayout) findViewById(R.id.relativeHeight);
        holdingTabs = (RelativeLayout) findViewById(R.id.holdingTabs);

        currentlyUsingTextView= (TextView) findViewById(R.id.currentlyUsingTextView);

        sbcclayout = (View) findViewById(R.id.sbcclayout);
        dietaryLyout = (View) findViewById(R.id.dietaryLyout);
        heightWeightLayout = (View) findViewById(R.id.heightWeightLayout);
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
                DatePickerDialog dpd = new DatePickerDialog(MyActivity.this,
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

        currentlyusingView = findViewById(R.id.currentlyusingView);
        fragmentnaforlmnmy = (View)findViewById(R.id.NaFormButtonLayout);
        naButton = (ImageView) findViewById(R.id.naButton);

        radiogroup = (RadioGroup) findViewById(R.id.radio);

        ImmuniZedLinear= (LinearLayout) findViewById(R.id.ImmuniZedLinear);

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



        c1= (CheckBox) findViewById(R.id.c1);
        c2= (CheckBox) findViewById(R.id.c2);
        c3= (CheckBox) findViewById(R.id.c3);
        c4= (CheckBox) findViewById(R.id.c4);
        c5= (CheckBox) findViewById(R.id.c5);
        c6= (CheckBox) findViewById(R.id.c6);
        c7= (CheckBox) findViewById(R.id.c7);
        c8= (CheckBox) findViewById(R.id.c8);
        c9= (CheckBox) findViewById(R.id.c9);

        saveYM = (Button) findViewById(R.id.saveYM);
        saveYM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
try {
    savingButtonFunction();
}catch (Exception e){

}
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









        ImmuniZedLinearSpinner= (Spinner) findViewById(R.id.ImmuniZedLinearSpinner);
        List<String> listImmuniZedLinearSpinner = new ArrayList<String>();
        listImmuniZedLinearSpinner.add("--Select Options--");
        listImmuniZedLinearSpinner.add("Yes");
        listImmuniZedLinearSpinner.add("No");
        ArrayAdapter<String> ImmuniZedLinearSpinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_breastfeedingym));
        ImmuniZedLinearSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ImmuniZedLinearSpinner.setAdapter(ImmuniZedLinearSpinnerAdapter);

        ImmuniZedLinearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImmuniZedLinearSpinnerString=valueOfYesNo(position);
                ImmuniZedLinearSpinnerInt=position;

//                if(breastfeedingString.equalsIgnoreCase("YES")){
//                    linearCyrrentlyUsing.setVisibility(View.VISIBLE);
//                }
//                else{
//                    linearCyrrentlyUsing.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        naButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pwActive_subStage="na";
                fragmentnaforlmnmy.setVisibility(View.VISIBLE);
                holdingTabs.setVisibility(View.GONE);
                pTod.setVisibility(View.INVISIBLE);
            }
        });


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


        breastfeeding = (Spinner) findViewById(R.id.breastfeeding);
        List<String> listbreastfeeding = new ArrayList<String>();
        listbreastfeeding.add("--Select Options--");
        listbreastfeeding.add("Yes");
        listbreastfeeding.add("No");
        ArrayAdapter<String> breastfeedingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_breastfeedingym));
        breastfeedingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breastfeeding.setAdapter(breastfeedingAdapter);

        breastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                breastfeedingString=valueOfYesNo(parent.getSelectedItemPosition());

                Log.d("breastBeeding",parent.getSelectedItemPosition()+"");
                Log.d("breastBeeding",position+"");

                breastfeedingInt=position;

                if(breastfeedingInt==2){
                    two.setVisibility(View.VISIBLE);
                }
                else{
                    two.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
try {
    stopbreastfeeding = (Spinner) findViewById(R.id.stopbreastfeeding);

    List<String> liststopbreastfeeding = new ArrayList<String>();
    liststopbreastfeeding.add("--Select Options--");
    liststopbreastfeeding.add("Within one week after birth");
    liststopbreastfeeding.add("Within one month after birth");
    liststopbreastfeeding.add("Within three months after birth");
    liststopbreastfeeding.add("Within six months after birth");
    liststopbreastfeeding.add("Within 12 months after birth");
    liststopbreastfeeding.add("Within 12-18 months after birth");
    liststopbreastfeeding.add("Never breastfed");
    ArrayAdapter<String> stopbreastfeedingAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_stopbreastfeedingym));
    stopbreastfeedingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    stopbreastfeeding.setAdapter(stopbreastfeedingAdapter);

    stopbreastfeeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

           stopbreastfeedingString=String.valueOf(parent.getItemAtPosition(position));
            stopbreastfeedingInt=position;


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });





}catch(Exception e){

    Log.d("Spinner",e.toString());
}

        contraceptive = (Spinner) findViewById(R.id.contraceptive);
        List<String> listcontraceptive = new ArrayList<String>();
        listcontraceptive.add("--Select Options--");
        listcontraceptive.add("Yes");
        listcontraceptive.add("No");
        ArrayAdapter<String> contraceptiveAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_usingcontraceptive));
        contraceptiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contraceptive.setAdapter(contraceptiveAdapter);


        contraceptive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contraceptiveString=valueOfYesNo(position);
                contraceptiveInt=position;

                if(contraceptiveInt==1){
                    linearCyrrentlyUsing.setVisibility(View.VISIBLE);
                    currentlyUsingTextView.setVisibility(View.VISIBLE);
                    currentlyusingView.setVisibility(View.VISIBLE);
                }
                else{
                    linearCyrrentlyUsing.setVisibility(View.GONE);
                    currentlyUsingTextView.setVisibility(View.GONE);
                    currentlyusingView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        currentlyusing = (Spinner) findViewById(R.id.currentlyusing);
        List<String> listcurrentlyusing = new ArrayList<String>();
        listcurrentlyusing.add("--Select Options--");
        listcurrentlyusing.add("Female sterilization");
        listcurrentlyusing.add("Male sterilization");
        listcurrentlyusing.add("IUCD/ PPIUCD");
        listcurrentlyusing.add("Injectables");
        listcurrentlyusing.add("Oral pills");
        listcurrentlyusing.add("Condom/Nirodh");
        listcurrentlyusing.add("Withdrawal method");
        listcurrentlyusing.add("Other traditional method");
        listcurrentlyusing.add("Other modern method");

        ArrayAdapter<String> currentlyusingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listcurrentlyusing);
        currentlyusingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentlyusing.setAdapter(currentlyusingAdapter);


        currentlyusing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentlyusingString=String.valueOf(parent.getItemAtPosition(position));
                currentlyusingInt=position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        counseledNGO = (Spinner) findViewById(R.id.counseledNGO);
        List<String> listcounseledNGO = new ArrayList<String>();
        listcounseledNGO.add("--Select Options--");
        listcounseledNGO.add("Yes");
        listcounseledNGO.add("No");
        listcounseledNGO.add("Can't Remember");
        ArrayAdapter<String> counseledNGOAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_colseledngoym));
        counseledNGOAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        counseledNGO.setAdapter(counseledNGOAdapter);
        counseledNGO.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                counseledNGOString=valueOfYesNo(position);
                counseledNGOInt=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        familyexpence = (Spinner) findViewById(R.id.familyexpence);
        List<String> listfamilyexpence = new ArrayList<String>();
        listfamilyexpence.add("--Select Options--");
        listfamilyexpence.add("Less than Rs. 500 per month");
        listfamilyexpence.add("Rs. 500- 1,000 per month");
        listfamilyexpence.add("Rs. 1000 – 2000 per month");
        listfamilyexpence.add("Rs. 2000 – 4000 per month");
        listfamilyexpence.add("Rs. 4000 – 5,000 per month");
        listfamilyexpence.add("Rs. 5,000 and above");
        ArrayAdapter<String> familyexpenceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_expenceym));
        familyexpenceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyexpence.setAdapter(familyexpenceAdapter);

        familyexpence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                familyexpenceString=String.valueOf(parent.getItemAtPosition(position));
                familyexpenceInt=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ym1 = (Button) findViewById(R.id.YM1Button);
        ym2 = (Button) findViewById(R.id.YM2Button);
        ym3 = (Button) findViewById(R.id.YM3Button);
        ym4 = (Button) findViewById(R.id.YM4Button);
        ym5 = (Button) findViewById(R.id.YM5Button);

        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        three = (LinearLayout) findViewById(R.id.three);
        four = (LinearLayout) findViewById(R.id.four);
        five = (LinearLayout) findViewById(R.id.five);
        six = (LinearLayout) findViewById(R.id.six);

        ym1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="MY1";
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                pTod.setVisibility(View.INVISIBLE);
//                Toast.makeText(MyActivity.this, "Button 1 Clicked", Toast.LENGTH_SHORT).show();

//                one.setVisibility(View.VISIBLE);
//                two.setVisibility(View.VISIBLE);
//                three.setVisibility(View.VISIBLE);
//                four.setVisibility(View.VISIBLE);
//                five.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);
                clearAllForm();
                stageDataByMYID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });

        ym2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwActive_subStage="MY2";
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                pTod.setVisibility(View.INVISIBLE);
//                one.setVisibility(View.VISIBLE);
//                two.setVisibility(View.VISIBLE);
//                three.setVisibility(View.VISIBLE);
//                four.setVisibility(View.VISIBLE);
//                five.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);
                Toast.makeText(MyActivity.this, "Clear All Data", Toast.LENGTH_SHORT).show();
                               clearAllForm();
                stageDataByMYID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });

        ym3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwActive_subStage="MY3";
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                pTod.setVisibility(View.INVISIBLE);
//                one.setVisibility(View.VISIBLE);
//                two.setVisibility(View.VISIBLE);
//                three.setVisibility(View.VISIBLE);
//                four.setVisibility(View.VISIBLE);
//                five.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);
                clearAllForm();
                stageDataByMYID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });

        ym4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="MY4";
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                pTod.setVisibility(View.INVISIBLE);
//                one.setVisibility(View.GONE);
//                two.setVisibility(View.GONE);
//                three.setVisibility(View.VISIBLE);
//                four.setVisibility(View.VISIBLE);
//                five.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);
                clearAllForm();
                stageDataByMYID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });
        ym5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pwActive_subStage="MY5";
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                pTod.setVisibility(View.INVISIBLE);
//                one.setVisibility(View.GONE);
//                two.setVisibility(View.GONE);
//                three.setVisibility(View.GONE);
//                four.setVisibility(View.GONE);
//                five.setVisibility(View.VISIBLE);
//                six.setVisibility(View.VISIBLE);
                clearAllForm();
                stageDataByMYID(getIntent().getStringExtra("memberId"),pwActive_subStage);
            }
        });

        setButtonStage(getIntent().getStringExtra("memberId"), getIntent().getStringExtra("current_sub_stage"));
        checkWidgetvisibility();
        stageDataByMYID(getIntent().getStringExtra("memberId"),pwActive_subStage);

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


    public void onBackPressed() {
//        super.onBackPressed();

        dialogCoupon    = new Dialog(MyActivity.this);
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
                dialogCoupon.hide();
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

    public void setButtonStage(String memberID, String substage) {

        String buttonStage = "select members_id,stage,sub_stage from child_tracking where members_id='" + memberID + "'";
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Log.d("RanjeetMY_ACTIVITY",substage);


        Cursor c = dbs.rawQuery(buttonStage, null);
        Log.d("RanjeetMY_ACTIVITY",c.getCount()+""+buttonStage);
        if (c.getCount() >= 1) {



//        int i = 0;
            if (c.moveToFirst()) {
                do {

                    try {
//             arrayNoOfChild.add(c.getString(c.getColumnIndex("Members_id")));
                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("my1")) {
                            ym1.setBackgroundResource(R.drawable.greencirclecolor);
                            ym1.setEnabled(true);

                        }

                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("my2")) {
                            ym2.setBackgroundResource(R.drawable.greencirclecolor);
                            ym2.setEnabled(true);
                        }

                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("my3")) {
                            ym3.setBackgroundResource(R.drawable.greencirclecolor);
                            ym3.setEnabled(true);
//                        relativeHeight.setVisibility(View.VISIBLE);
//                        linearChildForm.setVisibility(View.VISIBLE);
                        }
                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("my4")) {
                            ym4.setBackgroundResource(R.drawable.greencirclecolor);
                            ym4.setEnabled(true);
//                        relativeHeight.setVisibility(View.VISIBLE);
//                        linearChildForm.setVisibility(View.VISIBLE);
                        }
                        if (c.getString(c.getColumnIndex("sub_stage")).equalsIgnoreCase("my5")) {
                            ym5.setBackgroundResource(R.drawable.greencirclecolor);
                            ym5.setEnabled(true);
//                        relativeHeight.setVisibility(View.VISIBLE);
//                        linearChildForm.setVisibility(View.VISIBLE);
                        }

                    }catch(Exception e){

                    }
//
                } while (c.moveToNext());
            }

        }

        if(substage.equalsIgnoreCase("my1")){
            ym1.setBackgroundResource(R.drawable.circlebutton);
            ym1.setEnabled(true);
            pwActive_subStage="MY1";

//            formVisibleInvible(pwActive_subStage);
        }
        if(substage.equalsIgnoreCase("my2"))
        {
            Log.d("pregnentStatus","inseretdr");
            ym2.setBackgroundResource(R.drawable.circlebutton);
            ym2.setEnabled(true);
            pwActive_subStage="MY2";
//            formVisibleInvible(pwActive_subStage);
        }
        if(substage.equalsIgnoreCase("my3"))
        {

            ym3.setBackgroundResource(R.drawable.circlebutton);
            ym3.setEnabled(true);
            pwActive_subStage="MY3";

//            relativeHeight.setVisibility(View.VISIBLE);
//            linearChildForm.setVisibility(View.VISIBLE);
//            formVisibleInvible(pwActive_subStage);
        }
        if(substage.equalsIgnoreCase("my4"))
        {

            ym4.setBackgroundResource(R.drawable.circlebutton);
            ym4.setEnabled(true);
            pwActive_subStage="MY4";

//            relativeHeight.setVisibility(View.VISIBLE);
//            linearChildForm.setVisibility(View.VISIBLE);
//            formVisibleInvible(pwActive_subStage);
        }
        if(substage.equalsIgnoreCase("my5"))
        {
            ym5.setBackgroundResource(R.drawable.circlebutton);
            ym5.setEnabled(true);
            pwActive_subStage="MY5";
//       relativeHeight.setVisibility(View.VISIBLE);
//       linearChildForm.setVisibility(View.VISIBLE);
//     formVisibleInvible(pwActive_subStage);
              }

        try {

//            Toast.makeText(this, "insert into stagDAta by ", Toast.LENGTH_SHORT).show();
//            stageDataByLMID(getIntent().getStringExtra("memberId"), pwActive_subStage);
        }catch (Exception e){

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

    public String valueOfYesNo(int indexValue){
        String x="0";
        switch (indexValue) {

            case 0:
//
                x="--Select Options--";
                break;

            case 1:
                x="Y";
                break;
            case 2:
                x="N";
                break;


        }
        return x;
    }


public void savingButtonFunction(){
    if(pwActive_subStage.equalsIgnoreCase("ptod")){
        String pregnetQuery1,pregnetQuery2;

        if(pregnetNumnber.getText().toString().length()==0){
            Toast.makeText(this, "Enter No. of Pregnant ", Toast.LENGTH_SHORT).show();
        }
        else if(lmpdate.getText().toString().length()==0){
            Toast.makeText(this, "Select LMP Date", Toast.LENGTH_SHORT).show();

        }else {
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

            Toast.makeText(MyActivity.this, "Record Upated Successfully", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MyActivity.this, "Record Update Successfully", Toast.LENGTH_SHORT).show();

            Intent intetnBeneficiray = new Intent(getApplicationContext(),DashBoard.class);
            startActivity(intetnBeneficiray);
            finish();

        }
        else{
            Toast.makeText(MyActivity.this, "Select Non Availibility Option", Toast.LENGTH_SHORT).show();
        }
    }
    else {

        errorCode= validateMYForm();

        Log.d("LM_Activity", errorCode);
        if (errorCode.equalsIgnoreCase("0")) {

            dietSelection();

            if (subStageMode.equalsIgnoreCase("add")) {
                String inseertLMTrack = "INSERT INTO CHILD_TRACKING (MEMBERS_ID,STAGE,SUB_STAGE,IS_AVAILABLE,CURRENTLY_BF," +
                        "IF_COUNSEL_ON_FEED_INFANT,IF_USING_CONTRACEPTIVE,METHOD_CONTRACEPTIVE,SPEND_ON_FOOD,CHILD_HEIGHT," +
                        "CHILD_WEIGHT,CHILD_MUAC,SURVEYOR_ID,TIME_STAMP,SOURCE,IS_APPROVED,IS_NEW) " +
                        " VALUES ('" + getIntent().getStringExtra("memberId") + "','MY','" + getIntent().getStringExtra("current_sub_stage") + "',1,'" + breastfeedingString + "'," +
                        "'" + counseledNGOString + "','" + contraceptiveString + "','" + methodcontra + "','" + familyexpenceInt + "','" + height.getText().toString() + "'," +
                        "'" + weight.getText().toString() + "','" + muac.getText().toString() + "','" + session.getSurveyorId() + "','" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "','M','','N')";

                Log.d("insertQuery", inseertLMTrack);
//
                SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                Cursor c = dbs.rawQuery(inseertLMTrack, null);

                c.moveToFirst();
//                    total = c.getCount();  int
                c.close();


                String deitInsert = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED) VALUES " +
                        " ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
                        "'," + bn + ",'" + cf + "'," + cn + ",'" + df + "'," + dn + ",'" + ef + "'," +
                        en + ",'" + ff + "'," + fn + ",'" + gf + "'," + gn + ",'" + hf + "'," + hn + ",'"
                        + i_f + "'," + in + ",'" + jf + "'," + jn + ",'" + kf + "'," + kn + ",'" + lf + "'," +
                        ln + ",'" + mf + "'," + mn + ",'M','N','','')";

                        Log.d("InsertQuerydiet", deitInsert);
                Cursor cinsert = dbs.rawQuery(deitInsert, null);

             cinsert.moveToFirst();
//                {

//                }
//                    total = c.getCount();  int
                cinsert.close();



                String updateSub_StageInMemberBasic = "update memberbasic set IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end), sub_stage='" + getIntent().getStringExtra("current_sub_stage") + "' WHERE MEMBERS_ID='" + getIntent().getStringExtra("memberId") + "'";
                SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                Cursor c3 = dsbs.rawQuery(updateSub_StageInMemberBasic, null);

                if (c3.moveToFirst()) {

                }
//                    c3.moveToFirst();
                c3.close();

                String updateChildExtra,query="";
                if(breastfeeding.getVisibility()==View.VISIBLE){
                    query="CURRENTLY_BF = '"+ breastfeedingString +"',WHEN_STOP_BF = '" + stopbreastfeedingInt + "',";
                }
                if(ImmuniZedLinearSpinner.getVisibility()==View.VISIBLE){
                    if(query.length()>0){
                        query=query+','+"CHILD_IMMUNIZATION_STATUS = '" + ImmuniZedLinearSpinnerString + "',";
                    }
                    else{
                        query="CHILD_IMMUNIZATION_STATUS = '" + ImmuniZedLinearSpinnerString + "',";
                    }
                }

                updateChildExtra = "  UPDATE CHILDEXTRA SET " + query +
                        "IS_APPROVED = ''," +
                        "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                        " WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";

                SQLiteDatabase dsUpdate = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                Cursor cdsUpdate = dsUpdate.rawQuery(updateChildExtra, null);
                Log.d("updateLM3", updateChildExtra);

                cdsUpdate.moveToFirst();
                cdsUpdate.close();


                Toast.makeText(MyActivity.this, "Record Saved Succesfully", Toast.LENGTH_SHORT).show();

                subStageMode = "edit";

            } else if (subStageMode.equalsIgnoreCase("edit")) {



                String updateTrackingPw = "UPDATE CHILD_TRACKING SET CURRENTLY_BF = '" + breastfeedingString + "',IF_COUNSEL_ON_FEED_INFANT = '" + counseledNGOString + "',IF_USING_CONTRACEPTIVE = '" + contraceptiveString + "',METHOD_CONTRACEPTIVE = '" + methodcontra + "'," +
                        " SPEND_ON_FOOD = " + familyexpenceInt + ",CHILD_HEIGHT = '" + height.getText().toString() + "',CHILD_WEIGHT = '" + weight.getText().toString() + "',CHILD_MUAC = '" + muac.getText().toString() + "'," +
                        " SURVEYOR_ID = " + session.getSurveyorId() + ", TIME_STAMP = '" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(new Date()) + "',SOURCE = 'M',IS_APPROVED = '',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)  WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "' AND SUB_STAGE = '" + pwActive_subStage + "'";


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
                            "" + ln + ", FEED_M = '" + mf + "',   FEED_M_NOS = " + mn + ",IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") +
                            "' AND SUB_STAGE = '" + pwActive_subStage + "'";
                } else {

                    updateDiet = "INSERT INTO DIET (MEMBERS_ID,STAGE,SUB_STAGE,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS,SOURCE,IS_NEW,IS_EDITED,IS_APPROVED)VALUES" +
                            " ('" + getIntent().getStringExtra("memberId") + "','LM','" + pwActive_subStage + "','" + af + "'," + an + ",'" + bf +
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




                String updateChildExtra;

                updateChildExtra = "  UPDATE CHILDEXTRA " +
                        "SET CURRENTLY_BF = '" + breastfeedingString + "',WHEN_STOP_BF = '" + stopbreastfeedingInt + "',CHILD_IMMUNIZATION_STATUS = '" + ImmuniZedLinearSpinnerString + "'," +
                        "IS_APPROVED = ''," +
                        "IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end)" +
                        " WHERE MEMBERS_ID = '" + getIntent().getStringExtra("memberId") + "'";



                SQLiteDatabase dsUpdate = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                Cursor cdsUpdate = dsUpdate.rawQuery(updateChildExtra, null);
                cdsUpdate.moveToFirst();
                cdsUpdate.close();

                Toast.makeText(MyActivity.this, "Edited Successfully", Toast.LENGTH_SHORT).show();


            }

            Intent intentHome = new Intent(getApplicationContext(),DashBoard.class);
            startActivity(intentHome);
            finish();
//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);
        }

    }





}



public String validateMYForm() {
    String error = "0";
methodcontra="";
    if (breastfeeding.getVisibility() == View.VISIBLE) {
        if (error == "0" && breastfeedingString.equalsIgnoreCase("--Select Options--")) {
            Toast.makeText(this, "Select Breast Feeding", Toast.LENGTH_SHORT).show();
            error = "1";
        }
    }

    if (two.getVisibility() == View.VISIBLE) {

        if (error == "0" && stopbreastfeedingString.equalsIgnoreCase("--Select Options--")) {
            Toast.makeText(this, "Select When Stop Breast Feeding", Toast.LENGTH_SHORT).show();
            error = "1";

        }
    }

    if (contraceptive.getVisibility() == View.VISIBLE) {

        if (error == "0" && contraceptiveString.equalsIgnoreCase("--Select Options--")) {
            Toast.makeText(this, "Select Contractceptive ", Toast.LENGTH_SHORT).show();
            error = "1";

        }
    }


    //checkBox group validation

    if (linearCyrrentlyUsing.getVisibility() == View.VISIBLE) {


        if (c1.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(1);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(1);
            }
            Log.d("MYActivity", methodcontra);
        } if (c2.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(2);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(2);
            }
            Log.d("MYActivity", methodcontra);
        } if (c3.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(3);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(3);
            }
            Log.d("MYActivity", methodcontra);
        } if (c4.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(4);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(4);
            }
            Log.d("MYActivity", methodcontra);
        } if (c5.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(5);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(5);
            }
            Log.d("MYActivity", methodcontra);
        } if (c6.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(6);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(6);
            }
            Log.d("MYActivity", methodcontra);
        } if (c7.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(7);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(7);
            }
            Log.d("MYActivity", methodcontra);
        } if (c8.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(8);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(8);
            }
            Log.d("MYActivity", methodcontra);
        } if (c9.isChecked()) {
            if (methodcontra.length() == 0) {
                methodcontra = String.valueOf(9);
            } else {
                methodcontra = methodcontra + "," + String.valueOf(9);
            }
            Log.d("MYActivity", methodcontra);
        }

        Log.d("MYActivity", methodcontra);
    if (methodcontra == "") {
        error = "1";
        Toast.makeText(this, "Select the Contraceptive Method", Toast.LENGTH_SHORT).show();
    }

}


    if(counseledNGO.getVisibility()==View.VISIBLE)
    {

        if(error == "0" && counseledNGOString.equalsIgnoreCase("--Select Options--"))
        {
            Toast.makeText(this, "Select Counseled NGO/ASHA ", Toast.LENGTH_SHORT).show();
            error = "1";

        }
    }

    if(ImmuniZedLinearSpinner.getVisibility()==View.VISIBLE)
    {

        if(error == "0" && ImmuniZedLinearSpinnerString.equalsIgnoreCase("--Select Options--"))
        {
            Toast.makeText(this, "Select Immunized  ", Toast.LENGTH_SHORT).show();
            error = "1";

        }
    }

    if(familyexpence.getVisibility()==View.VISIBLE)
    {

        if(error == "0" && familyexpenceString.equalsIgnoreCase("--Select Options--"))
        {

            Toast.makeText(this, "Select Spend On Food ", Toast.LENGTH_SHORT).show();
            error = "1";

        }

    }

     if(a_no.getVisibility()==View.VISIBLE && a_no.getText().length()==0){
        a_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(b_no.getVisibility()==View.VISIBLE && b_no.getText().length()==0){
        b_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(c_no.getVisibility()==View.VISIBLE && c_no.getText().length()==0){
        c_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(d_no.getVisibility()==View.VISIBLE && d_no.getText().length()==0){
        d_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(e_no.getVisibility()==View.VISIBLE && e_no.getText().length()==0){
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(f_no.getVisibility()==View.VISIBLE && f_no.getText().length()==0){
        f_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(g_no.getVisibility()==View.VISIBLE && g_no.getText().length()==0){
        g_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(h_no.getVisibility()==View.VISIBLE && h_no.getText().length()==0){
        h_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(i_no.getVisibility()==View.VISIBLE && i_no.getText().length()==0){
        i_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(j_no.getVisibility()==View.VISIBLE && j_no.getText().length()==0){
        j_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(k_no.getVisibility()==View.VISIBLE && k_no.getText().length()==0){
        k_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(l_no.getVisibility()==View.VISIBLE && l_no.getText().length()==0){
        l_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";
    }else if(m_no.getVisibility()==View.VISIBLE && m_no.getText().length()==0){
        m_no.requestFocus();
        Toast.makeText(MyActivity.this, "Enter No of Fed", Toast.LENGTH_SHORT).show();
        error="1";

    }else if(height.getText().length()==0){

        height.requestFocus();
        Toast.makeText(MyActivity.this, "Enter Height", Toast.LENGTH_SHORT).show();
        error="1";

    }else if(weight.getText().length()==0){
        Toast.makeText(MyActivity.this, "Enter Weight", Toast.LENGTH_SHORT).show();
        error="1";
    }
    else if(muac.getText().length()==0){
        Toast.makeText(MyActivity.this, "Enter MUAC ", Toast.LENGTH_SHORT).show();
        error="1";
    }

    return error;
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

                    current_bf= cr.getString(cr.getColumnIndex("currently_bf"));
                    when_stop_bf= cr.getString(cr.getColumnIndex("when_stop_bf"));
                    CHILD_IMMUNIZATION_STATUS=cr.getString(cr.getColumnIndex("child_immunization_status"));
//                    Log.d("Ranjeet")

                    Log.d("LM_activitywhn","stop"+when_stop_bf);
                    Log.d("LM_activity","current"+current_bf);
try {
    breastfeeding.setSelection(indexOfYesNo(current_bf));
    stopbreastfeeding.setSelection(Integer.parseInt(when_stop_bf));
    if (CHILD_IMMUNIZATION_STATUS.equalsIgnoreCase("Y") || CHILD_IMMUNIZATION_STATUS.equalsIgnoreCase("N")) {
        ImmuniZedLinearSpinner.setSelection(indexOfYesNo(CHILD_IMMUNIZATION_STATUS));
    }
}catch (Exception e){

}

                } while (cr.moveToNext());
            }

//        Log.d("recordCountPW",""+i);
        }






    }

    //data binding

    public void stageDataByMYID(String memberId,String pwActive_subStage) {

        saveYM.setEnabled(true);
              saveYM.setVisibility(View.VISIBLE);
        subStageMode = "add";
        String query = "SELECT CURRENTLY_BF, IF_COUNSEL_ON_FEED_INFANT,IF_COUNSEL_ON_SELFFEED,IF_USING_CONTRACEPTIVE,METHOD_CONTRACEPTIVE,SPEND_ON_FOOD,CHILD_IMMUNIZATION_STATUS, CHILD_HEIGHT,CHILD_WEIGHT,CHILD_MUAC,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS, T.IS_APPROVED from child_tracking T LEFT JOIN DIET D ON T.MEMBERS_ID=D.MEMBERS_ID  where T.MEMBERS_ID='" + memberId + "' AND T.SUB_STAGE='" + pwActive_subStage + "'";
//
        Log.d("LACtivty", query);
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
        Cursor c = dbs.rawQuery(query, null);
        int total = c.getCount();
        if(pwActive_subStage.equalsIgnoreCase("MY2") || pwActive_subStage.equalsIgnoreCase("MY3") ){

            ImmuniZedLinearSpinner.setVisibility(View.VISIBLE);
            ImmuniZedLinear.setVisibility(View.VISIBLE);
        }

        else{

            ImmuniZedLinearSpinner.setVisibility(View.GONE);
            ImmuniZedLinear.setVisibility(View.GONE);
        }

//        Log.d("Immunization",CHILD_IMMUNIZATION_STATUS);
try {
    if (pwActive_subStage.equalsIgnoreCase("MY2")) {
        if (!CHILD_IMMUNIZATION_STATUS.equalsIgnoreCase("")) {
//                ImmuniZedLinearSpinner.setVisibility(View.GONE);
//                ImmuniZedLinear.setVisibility(View.GONE);

        }
    }
}catch (Exception e){

}

        if (total >= 1) {

            subStageMode = "edit";

            Log.d("LACtivty", "" + total);


//        int i = 0;
            if (c.moveToFirst()) {
                do {
//
                    Log.d("CheckDate", "" + c.getString(c.getColumnIndex("currently_bf")));
//                    breastfeeding.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("currently_bf"))));
//                    stopbreastfeeding.setSelection(Integer.parseInt(when_stop_bf));
                    Log.d("LA_ACTIVTY_VALUE", c.getString(c.getColumnIndex("currently_bf")));
                    if(current_bf.equalsIgnoreCase("N") && c.getString(c.getColumnIndex("currently_bf")).equalsIgnoreCase("N"))
     {
//                        breastfeeding.setVisibility(View.GONE);
//                        stopbreastfeeding.setVisibility(View.GONE);
//                        one.setVisibility(View.GONE);
//                        two.setVisibility(View.GONE);
                        breastfeeding.setSelection(2);
//                        stopbreastfeeding.setSelection(when_stop_bf);
                        try {
                            Log.d("when_stop", c.getString(c.getColumnIndex("when_stop_bf")));
                            stopbreastfeeding.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("when_stop_bf"))));
                        }catch (Exception e){
                            Log.d("when_stop",e.toString());
                        }

     }
else{

try {
        breastfeeding.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("currently_bf"))));
    Log.d("When_STOP_BF",c.getString(c.getColumnIndex("when_stop_bf")));
    stopbreastfeeding.setSelection(Integer.parseInt(when_stop_bf));
}catch (Exception e){

    Log.d("When_STOP_BF",e.toString());
}

                    }

try {

    contraceptive.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_using_contraceptive"))));

}catch (Exception e){

}
if(c.getString(c.getColumnIndex("method_contraceptive")).length()>0){

    Log.d("CheckedValue",c.getString(c.getColumnIndex("if_using_contraceptive")));

    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("1")){
        c1.setChecked(true);
    }

    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("2")){
        c2.setChecked(true);
    }
    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("3")){
        c3.setChecked(true);
    } if(c.getString(c.getColumnIndex("method_contraceptive")).contains("4")){
        c4.setChecked(true);
    }
    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("5")){
        c5.setChecked(true);
    }
    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("6")){
        c6.setChecked(true);
    }
    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("7")){
        c7.setChecked(true);
    }
    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("8")){
        c8.setChecked(true);
    }
    if(c.getString(c.getColumnIndex("method_contraceptive")).contains("9")){
        c9.setChecked(true);
    }

}

                    try {
//                        if (c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("Y")) {
                        counseledNGO.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_counsel_on_feed_infant"))));
//                        ngoinfantfeed.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("if_counsel_on_feed_infant"))));
//                        } else {
//                            ngoownfood.setSelection(2);
//                        }
                    } catch (Exception e) {
                        Log.d("LACtivty", "4" + e.toString());
                    }
try {
    Log.d("houseHold", c.getString(c.getColumnIndex("spend_on_food")) + "");
    familyexpence.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("spend_on_food"))));

}catch (Exception e){

}


try{

    ImmuniZedLinearSpinner.setSelection(indexOfYesNo(CHILD_IMMUNIZATION_STATUS));

}catch (Exception e){

}


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
                        if (c.getString(c.getColumnIndex("feed_b")).equalsIgnoreCase("Y")) {
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
                        if (c.getString(c.getColumnIndex("feed_c")).equalsIgnoreCase("Y")) {
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
                        if (c.getString(c.getColumnIndex("feed_d")).equalsIgnoreCase("Y")) {
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

                        if (c.getString(c.getColumnIndex("feed_e")).equalsIgnoreCase("Y")) {
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
                        if (c.getString(c.getColumnIndex("feed_f")).equalsIgnoreCase("Y")) {
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
                        if (c.getString(c.getColumnIndex("feed_g")).equalsIgnoreCase("Y")) {
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
                        if (c.getString(c.getColumnIndex("feed_h")).equalsIgnoreCase("Y")) {
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
                        if (c.getString(c.getColumnIndex("feed_I")).equalsIgnoreCase("Y")) {
                            i_food.setChecked(true);
                            i_no.setVisibility(View.VISIBLE);
                            i_no.setText(c.getString(c.getColumnIndex("feed_i_nos")));
                        } else {
                            i_food.setChecked(false);
                            i_no.setVisibility(View.INVISIBLE);
                            i_no.setText("");
                        }
                        if (c.getString(c.getColumnIndex("feed_j")).equalsIgnoreCase("Y")) {
                            j_food.setChecked(true);
                            j_no.setVisibility(View.VISIBLE);
                            j_no.setText(c.getString(c.getColumnIndex("feed_j_nos")));
                        } else {
                            j_food.setChecked(false);
                            j_no.setVisibility(View.INVISIBLE);
                            j_no.setText("");
                        }
                        if (c.getString(c.getColumnIndex("feed_k")).equalsIgnoreCase("Y")) {
                            k_food.setChecked(true);
                            k_no.setVisibility(View.VISIBLE);
                            k_no.setText(c.getString(c.getColumnIndex("feed_k_nos")));
                        } else {
                            k_food.setChecked(false);
                            k_no.setVisibility(View.INVISIBLE);
                            k_no.setText("");
                        }

                        if (c.getString(c.getColumnIndex("feed_l")).equalsIgnoreCase("Y")) {
                            l_food.setChecked(true);
                            l_no.setVisibility(View.VISIBLE);
                            l_no.setText(c.getString(c.getColumnIndex("feed_l_nos")));
                        } else {
                            l_food.setChecked(false);
                            l_no.setVisibility(View.INVISIBLE);
                            l_no.setText("");
                        }

                        if (c.getString(c.getColumnIndex("feed_m")).equalsIgnoreCase("Y")) {
                            m_food.setChecked(true);
                            m_no.setVisibility(View.VISIBLE);
                            m_no.setText(c.getString(c.getColumnIndex("feed_m_nos")));
                        } else {
                            m_food.setChecked(false);
                            m_no.setVisibility(View.INVISIBLE);
                            m_no.setText("");
                        }



                    } catch (Exception e) {

                        Log.d("Holi", e.toString());

                    }
                    Log.d("RanjeetISpproved",""+c.getString(c.getColumnIndex("is_approved")));

                    if (c.getString(c.getColumnIndex("is_approved")).equalsIgnoreCase("Y")) {
                        saveYM.setEnabled(false);
                        Log.d("RanjeetISpproved","Entered inside of condtioon");
                        saveYM.setVisibility(View.INVISIBLE);
                    }
                }
                while (c.moveToNext());

                    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.mymenu, menu);

        MenuItem shareItem = menu.findItem(R.id.mymotherTrack);
        if(checkActivePregency(getIntent().getStringExtra("motherId"))) {
            shareItem.setVisible(true);
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

        Log.d("MYQUERYPRITN","select * from pregnant where members_id='"+motherId+"' and is_active='Y'");
        if(cQuery.getCount()>0){
            return  false;
        }
        else{
            return true;
        }


    }


    // handle button activities


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
            Log.d("ChildcountCalculation","Inserted ");
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
            if(view instanceof Spinner){
                ((Spinner)view).setSelection(0);
            }
//            if(view instanceof CheckBox ){
//                ((CheckBox)view).setChecked(false);
//            }
//
//            if(view instanceof RadioButton){
//                ((RadioButton)view).setChecked(false);
//            }
        }

        for (int i = 0, count = group2.getChildCount(); i < count; ++i) {


            Log.d("ChildcountCalculation","Inserted2 ");
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
            Log.d("ChildcountCalculation","Inserted3");
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


        c1.setChecked(false);
        c2.setChecked(false);
        c3.setChecked(false);
        c4.setChecked(false);
        c5.setChecked(false);
        c6.setChecked(false);
        c7.setChecked(false);
        c8.setChecked(false);
        c9.setChecked(false);

        muac.setText("");
        solidfoodmonth.setText("");
    }
}





