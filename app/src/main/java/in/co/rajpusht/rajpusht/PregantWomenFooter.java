package in.co.rajpusht.rajpusht;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
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

    View fragment_preliminary_pw1, fragment_diatary_pw1, fragment_height_weight;

    View basicclick, pregnentclick, childclick;

    RelativeLayout relativessbc, relativeDiversity, relativeHeight;

    String pwActive_subStage;

    TextView anc_date, acndate;
    private int mYear, mMonth, mDay;
    Spinner spinner_consult_self, spinner_spend_food,spinner_spend_on_bf;

    CheckBox a_food,b_food,c_food,d_food,e_food,f_food,g_food,h_food,i_food,j_food,k_food,l_food,m_food;
    EditText a_no,b_no,c_no,d_no,e_no,f_no,g_no,h_no,i_no,j_no,k_no,l_no,m_no;

    EditText height,weight;

    Button PW1Button,PW2Button,PW3Button,PW4Button;
    String subStageMode;
    Button save;

    String spinner_consult_selfString,spend_on_bfString,sepndOfFoodString;
    int spinner_consult_selfInteger,spentOnFoodInt;
    TextView textLmpdate;


    String datePresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregant_women_footer);


        fragment_preliminary_pw1 = findViewById(R.id.sbcclayout);
        fragment_diatary_pw1 = findViewById(R.id.dietaryLyout);
        fragment_height_weight = findViewById(R.id.heightWeightLayout);
        textLmpdate = (TextView) findViewById(R.id.date);
        textLmpdate.setText(getIntent().getStringExtra("lmpDate"));

        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

        basicclick = (View) findViewById(R.id.basicclick);
        pregnentclick = (View) findViewById(R.id.pregnentclick);
        childclick = (View) findViewById(R.id.childclick);

        relativessbc = (RelativeLayout) findViewById(R.id.relativessbc);
        relativeDiversity = (RelativeLayout) findViewById(R.id.relativeDiversity);
        relativeHeight = (RelativeLayout) findViewById(R.id.relativeHeight);

        PW1Button = (Button) findViewById(R.id.PW1Button);
                PW2Button= (Button) findViewById(R.id.PW2Button);
                PW3Button= (Button) findViewById(R.id.PW3Button);
                PW4Button= (Button) findViewById(R.id.PW4Button);



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

                if(subStageMode.equalsIgnoreCase("add")){

                    if(a_food.isChecked()){
                        String af="Y";
                        Integer an=Integer.parseInt(a_no.getText().toString());
                    }
                    else{
                        String af="N";
                        Integer an= null;
                    }
                    if(b_food.isChecked()){
                        String bf="Y";
                        Integer bn=Integer.parseInt(b_no.getText().toString());
                    }
                    else{
                        String bf="N";
                        Integer bn= null;
                    }


                    if(c_food.isChecked()){
                        String cf="Y";
                        Integer cn=Integer.parseInt(c_no.getText().toString());
                    }
                    else{
                        String cf="N";
                        Integer cn= null;
                    }


                    if(d_food.isChecked()){
                        String df="Y";
                        Integer dn=Integer.parseInt(d_no.getText().toString());
                    }
                    else{
                        String df="N";
                        Integer dn= null;
                    }

                    if(e_food.isChecked()){
                        String ef="Y";
                        Integer en=Integer.parseInt(e_no.getText().toString());
                    }
                    else {
                        String ef = "N";
                        Integer en = null;
                    }

                    if(f_food.isChecked()){
                        String ff="Y";
                        Integer fn=Integer.parseInt(f_no.getText().toString());
                    }
                    else{
                        String ff="N";
                        Integer fn=null;
                    }
                    if(g_food.isChecked()){
                        String gf="Y";
                        Integer gn=Integer.parseInt(g_no.getText().toString());
                    }
                    else {
                        String gf="N";
                        Integer gn=null;
                    }

                    if(h_food.isChecked()){
                        String hf="Y";
                        Integer hn=Integer.parseInt(h_no.getText().toString());
                    }
                    else{
                        String hf="N";
                        Integer hn=null;
                    }
                    if(i_food.isChecked()){
                        String i_f="Y";
                        Integer in=Integer.parseInt(i_no.getText().toString());
                    }
                    else {

                        String i_f="N";
                        Integer in=null;
                    }



                    if(j_food.isChecked()){
                        String jf="Y";
                        Integer jn=Integer.parseInt(j_no.getText().toString());
                    }
                    else{

                        String j_f="N";
                        Integer jn=null;
                    }

                    if(k_food.isChecked()){
                        String kf="Y";
                        Integer kn=Integer.parseInt(k_no.getText().toString());
                    }
                    else{
                        String k_f="N";
                        Integer kn=null;
                    }

                    if(l_food.isChecked()){
                        String lf="Y";
                        Integer ln=Integer.parseInt(m_no.getText().toString());
                    }
                    else{
                        String l_f="N";
                        Integer ln=null;
                    }
                    if(m_food.isChecked()){
                        String mf="Y";
                        Integer mn=Integer.parseInt(m_no.getText().toString());
                    }
                    else{
                        String m_f="N";
                        Integer mn=null;
                    }


                String inseertPwTrack="insert into  pw_tracking(pregnancy_id,members_id,stage,sub_stage,is_available,na_reason,is_anc," +
                        "anc_date,if_counsel_on_selffeed,if_counsel_on_bf,spend_on_food,height,weight,surveyor_id,time_stamp,source," +
                        "is_new,is_edited,is_approved) values('"+ getIntent().getStringExtra("pregnancy_id")+"','"
                        +getIntent().getStringExtra("members_id")+"',"+"'PW'"+",'"+pwActive_subStage+"',"+"'Y'"+","+"'' "+","+"'Y'"+",'"+
                    anc_date.getText().toString()+"','" + spinner_consult_selfString+"','"+ spend_on_bfString +"'," +spentOnFoodInt+ "," +
                        Integer.parseInt(height.getText().toString())+","+Integer.parseInt(weight.getText().toString())+","+new Login().surveyerId+","
                    + new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault()).format(new Date()) +","
                    +"'Mobile'"+","+"'Y'"+","+"''"+","+"''"+")";

                    Log.d("insertQuery",inseertPwTrack);
//
                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                    Cursor c = dbs.rawQuery(inseertPwTrack,null);

                    c.moveToFirst();
//                    total = c.getCount();  int
                    c.close();






                    String updateSub_StageInMemberBasic="update memberbasic set sub_stage='"+getIntent().getStringExtra("current_sub_stage")+"' WHERE MEMBERS_ID=(SELECT MEMBERS_ID FROM PREGNANT WHERE PREGNANCY_ID='"+getIntent().getStringExtra("pregnancy_id")+"')";
                    SQLiteDatabase dsbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                    Cursor c3 = dsbs.rawQuery(updateSub_StageInMemberBasic,null);
                    c3.moveToFirst();
                    c3.close();

                    subStageMode="edit";

                }
//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);


            }
        });
        PW1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW1";

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });

        PW2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW2";

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });

        PW3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW3";

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



            }
        });
        PW4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwActive_subStage="PW4";

                stageDataByPWID(getIntent().getStringExtra("pregnancy_id"),pwActive_subStage);



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


    public void checkValidation(){

        if(anc_date.getText().length()==0){
            anc_date.setError("enter date");
        }else if(spinner_consult_selfString.equalsIgnoreCase("--Select Options--")){
            Toast.makeText(this, "Select if counseled", Toast.LENGTH_SHORT).show();
        }else if(spend_on_bfString.equalsIgnoreCase("--Select Options--")){
            Toast.makeText(this, "Select feeding", Toast.LENGTH_SHORT).show();
        }else if(sepndOfFoodString.equalsIgnoreCase("--Select Options--")){
            Toast.makeText(this, "Select amount spend on fooding", Toast.LENGTH_SHORT).show();
        }else if(a_no.getVisibility()==View.VISIBLE && a_no.getText().length()==0){
            a_no.setError("");
        }else if(b_no.getVisibility()==View.VISIBLE && b_no.getText().length()==0){
            b_no.setError("");
        }else if(c_no.getVisibility()==View.VISIBLE && c_no.getText().length()==0){
            c_no.setError("");
        }else if(d_no.getVisibility()==View.VISIBLE && d_no.getText().length()==0){
            d_no.setError("");
        }else if(e_no.getVisibility()==View.VISIBLE && e_no.getText().length()==0){
            e_no.setError("");
        }else if(f_no.getVisibility()==View.VISIBLE && f_no.getText().length()==0){
            f_no.setError("");
        }else if(g_no.getVisibility()==View.VISIBLE && g_no.getText().length()==0){
            g_no.setError("");
        }else if(h_no.getVisibility()==View.VISIBLE && h_no.getText().length()==0){
            h_no.setError("");
        }else if(i_no.getVisibility()==View.VISIBLE && i_no.getText().length()==0){
            i_no.setError("");
        }else if(j_no.getVisibility()==View.VISIBLE && j_no.getText().length()==0){
            j_no.setError("");
        }else if(k_no.getVisibility()==View.VISIBLE && k_no.getText().length()==0){
            k_no.setError("");
        }else if(l_no.getVisibility()==View.VISIBLE && l_no.getText().length()==0){
            l_no.setError("");
        }else if(m_no.getVisibility()==View.VISIBLE && m_no.getText().length()==0){
            m_no.setError("");
        }else if(height.getText().length()==0){
            height.setError("");
        }else if(weight.getText().length()==0){
            weight.setError("");
        }else{

        }
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

        String query="select is_anc,anc_date,IF_COUNSEL_ON_SELFFEED,IF_COUNSEL_ON_BF,SPEND_ON_FOOD,HEIGHT,WEIGHT,IS_APPROVED,FEED_A,FEED_A_NOS,FEED_B,FEED_B_NOS,FEED_C,FEED_C_NOS,FEED_D,FEED_D_NOS,FEED_E,FEED_E_NOS,FEED_F,FEED_F_NOS,FEED_G,FEED_G_NOS,FEED_H,FEED_H_NOS,FEED_I,FEED_I_NOS,FEED_J,FEED_J_NOS,FEED_K,FEED_K_NOS,FEED_L,FEED_L_NOS,FEED_M,FEED_M_NOS FROM PW_TRACKING T LEFT JOIN DIET D ON T.PREGNANCY_ID=D.PREGNANCY_ID AND T.SUB_STAGE=D.SUB_STAGE WHERE T.PREGNANCY_ID='"+pw_id+"' AND T.SUB_STAGE='"+sub_stage+"' ";


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
                    anc_date.setText(String.valueOf(c.getString(c.getColumnIndex("anc_date"))));
                    if(c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("Y")){
                        spinner_consult_self.setSelection(1);
                    }
                    if(c.getString(c.getColumnIndex("if_counsel_on_selffeed")).equalsIgnoreCase("N")){
                        spinner_consult_self.setSelection(2);
                    }

                    if(c.getString(c.getColumnIndex("if_counsel_on_bf")).equalsIgnoreCase("Y")){
                        spinner_spend_on_bf.setSelection(1);
                    }
                    if(c.getString(c.getColumnIndex("if_counsel_on_bf")).equalsIgnoreCase("N")){
                        spinner_spend_on_bf.setSelection(2);
                    }

                                        spinner_spend_food.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("spend_on_food"))));

                         height.setText(c.getString(c.getColumnIndex("height")));
                         weight.setText(c.getString(c.getColumnIndex("weight")));
//
//                        if(c.getString(c.getColumnIndex("feed_a")).equalsIgnoreCase("Y")){
//                            a_food.setChecked(true);
//                            a_no.setVisibility(View.VISIBLE);
//                            a_no.setText(c.getString(c.getColumnIndex("feed_a_nos")));
//                        }
//                        else{
//                            a_food.setChecked(false);
//                            a_no.setVisibility(View.INVISIBLE);
//                            a_no.setText("");
//                        }
//
//
//
//
//
//                    if(c.getString(c.getColumnIndex("feed_b")).equalsIgnoreCase("Y")){
//                        b_food.setChecked(true);
//                        b_no.setVisibility(View.VISIBLE);
//                        b_no.setText(c.getString(c.getColumnIndex("feed_b_nos")));
//                    }
//                    else{
//                        b_food.setChecked(false);
//                        b_no.setVisibility(View.INVISIBLE);
//                        b_no.setText("");
//                    }
//
//                    if(c.getString(c.getColumnIndex("feed_c")).equalsIgnoreCase("Y")){
//                        c_food.setChecked(true);
//                        c_no.setVisibility(View.VISIBLE);
//                        c_no.setText(c.getString(c.getColumnIndex("feed_c_nos")));
//                    }
//                    else{
//                        c_food.setChecked(false);
//                        c_no.setVisibility(View.INVISIBLE);
//                        c_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_d")).equalsIgnoreCase("Y")){
//                        d_food.setChecked(true);
//                        d_no.setVisibility(View.VISIBLE);
//                        d_no.setText(c.getString(c.getColumnIndex("feed_d_nos")));
//                    } else{
//                        d_food.setChecked(false);
//                        d_no.setVisibility(View.INVISIBLE);
//                        d_no.setText("");
//                    }
//
//                    if(c.getString(c.getColumnIndex("feed_e")).equalsIgnoreCase("Y")){
//                        e_food.setChecked(true);
//                        e_no.setVisibility(View.VISIBLE);
//                        e_no.setText(c.getString(c.getColumnIndex("feed_e_nos")));
//                    }
//
//                    else{
//                        e_food.setChecked(false);
//                        e_no.setVisibility(View.INVISIBLE);
//                        e_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_f")).equalsIgnoreCase("Y")){
//                        f_food.setChecked(true);
//                        f_no.setVisibility(View.VISIBLE);
//                        f_no.setText(c.getString(c.getColumnIndex("feed_f_nos")));
//                    }
//
//                    else{
//                        f_food.setChecked(false);
//                        f_no.setVisibility(View.INVISIBLE);
//                        f_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_g")).equalsIgnoreCase("Y")){
//                        g_food.setChecked(true);
//                        g_no.setVisibility(View.VISIBLE);
//                        g_no.setText(c.getString(c.getColumnIndex("feed_g_nos")));
//                    }
//                    else{
//                        g_food.setChecked(false);
//                        g_no.setVisibility(View.INVISIBLE);
//                        g_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_h")).equalsIgnoreCase("Y")){
//                        h_food.setChecked(true);
//                        h_no.setVisibility(View.VISIBLE);
//                        h_no.setText(c.getString(c.getColumnIndex("feed_h_nos")));
//                    }
//                    else{
//                        h_food.setChecked(false);
//                        h_no.setVisibility(View.INVISIBLE);
//                        h_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_i")).equalsIgnoreCase("Y")){
//                        i_food.setChecked(true);
//                        i_no.setVisibility(View.VISIBLE);
//                        i_no.setText(c.getString(c.getColumnIndex("feed_i_nos")));
//                    }
//                    else{
//                        i_food.setChecked(false);
//                        i_no.setVisibility(View.INVISIBLE);
//                        i_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_j")).equalsIgnoreCase("Y")){
//                        j_food.setChecked(true);
//                        j_no.setVisibility(View.VISIBLE);
//                        j_no.setText(c.getString(c.getColumnIndex("feed_j_nos")));
//                    }
//
//                    else{
//                        j_food.setChecked(false);
//                        j_no.setVisibility(View.INVISIBLE);
//                        j_no.setText("");
//                    }
//                    if(c.getString(c.getColumnIndex("feed_k")).equalsIgnoreCase("Y")){
//                        k_food.setChecked(true);
//                        k_no.setVisibility(View.VISIBLE);
//                        k_no.setText(c.getString(c.getColumnIndex("feed_k_nos")));
//                    }
//                    else{
//                        k_food.setChecked(false);
//                        k_no.setVisibility(View.INVISIBLE);
//                        k_no.setText("");
//                    }
//
//                    if(c.getString(c.getColumnIndex("feed_l")).equalsIgnoreCase("Y")){
//                        l_food.setChecked(true);
//                        l_no.setVisibility(View.VISIBLE);
//                        l_no.setText(c.getString(c.getColumnIndex("feed_l_nos")));
//                    }
//                    else{
//                        l_food.setChecked(false);
//                        l_no.setVisibility(View.INVISIBLE);
//                        l_no.setText("");
//                    }
//
//                    if(c.getString(c.getColumnIndex("feed_m")).equalsIgnoreCase("Y")){
//                        m_food.setChecked(true);
//                        m_no.setVisibility(View.VISIBLE);
//                        m_no.setText(c.getString(c.getColumnIndex("feed_m_nos")));
//                    }
//                    else{
//                        m_food.setChecked(false);
//                        m_no.setVisibility(View.INVISIBLE);
//                        m_no.setText("");
//                    }

                    if(c.getString(c.getColumnIndex("is_approved")).equalsIgnoreCase("Y")){
                        save.setEnabled(true);

                    }



                }while (c.moveToNext());

                }
                }
                else {

            subStageMode="add";
        }

    }
}
