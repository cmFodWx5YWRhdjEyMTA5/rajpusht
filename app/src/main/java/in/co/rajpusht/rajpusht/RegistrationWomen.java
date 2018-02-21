package in.co.rajpusht.rajpusht;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import extras.DbHelper;
import extras.FamilyDetailGetSet;
import extras.MemberBasicGetSet;
import extras.PregnantGetSet;
import extras.WomenBasicGetSet;

public class RegistrationWomen extends AppCompatActivity {

    String status;
    CheckBox checkChild,checkPregnets;
    TextView basic,basicPregment,child;
    View basicclick, pregnentclick, childclick;
    CheckBox adhaaravailable;
    String registerMemeberId,familyID,memberId,pregnentId;
    int castitem,relationitem,rationcardItem,familyttypeItem,eductionItemPosition, religionitemposi, fuelItemPosition, decisionItemPosition, doctorvisitItemPosition;
    LinearLayout noadhaar, banklayout, postofficeaccountdetail;
    RelativeLayout bas, preg, chi;
   DbHelper db;
    EditText nameBenificery,phoneNumber,usidNumber,nameaccountholder, bhamashahNumber,aadharNumber,aadharenrollment,personAge, nameofbank, branchname,
            bankaccountnumber, ifsccode, distancetobranch, distancenearestatm, nameofpostoffice, addressofpostoffice, postofficeaccount, postofficepincode,
            hoemocode;
    TextView datee, time, lmpdate, dob;
    RadioGroup bankgroup;
    Spinner personaReligion,castSpinner,rationcardColorSpinner,relationshipHeadSpinner,typeFamilySpinner,educationComplted,fuelSelectionSpinner,
            decsionSpinner,decsionVisitDoctorSpinner;
    View fragment_pregnant_lady_detail,fragment_young_mother_basic_details;
    EditText pregnetNumnber;
    String educationItem, accountype = "N", reli, cst, rati, rela, fami, fuel, deci, visi;
    private int mYear, mMonth, mDay, mHour, mMinute, mYear1, mMonth1, mDay1, mYear2, mMonth2, mDay2;
    Dialog dialogCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_young_mother);

//        getSupportActionBar().hide();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setTitle("BASIC");

        db= new DbHelper(this);

        fragment_young_mother_basic_details = (View)findViewById(R.id.pregnentbasic);
        fragment_pregnant_lady_detail = (View)findViewById(R.id.pregnentDetails);

        noadhaar = (LinearLayout) findViewById(R.id.noadhaar) ;
        banklayout = (LinearLayout) findViewById(R.id.banklayout) ;
        postofficeaccountdetail = (LinearLayout) findViewById(R.id.postofficeaccountdetail) ;
        bankgroup = (RadioGroup) findViewById(R.id.bankgroup) ;
        bankgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.accountyes) {
                    banklayout.setVisibility(View.VISIBLE);
                    postofficeaccountdetail.setVisibility(View.GONE);
                    accountype = "B";

                }else if(checkedId == R.id.accountno) {
                    postofficeaccountdetail.setVisibility(View.VISIBLE);
                    banklayout.setVisibility(View.GONE);
                    accountype = "P";
                }else if(checkedId == R.id.noaccount) {
                    postofficeaccountdetail.setVisibility(View.GONE);
                    banklayout.setVisibility(View.GONE);
                    accountype = "N";
                }
            }
        });

        adhaaravailable = (CheckBox) findViewById(R.id.adhaaravailable) ;
        adhaaravailable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // update your model (or other business logic) based on isChecked
                if(adhaaravailable.isChecked()){
                    noadhaar.setVisibility(View.VISIBLE);
                }else {
                    noadhaar.setVisibility(View.GONE);
                }
            }
        });

        bas= (RelativeLayout) findViewById(R.id.relativeBasic);
        preg= (RelativeLayout) findViewById(R.id.relativePregnent);
        chi= (RelativeLayout) findViewById(R.id.relativeChild);


        status = getIntent().getStringExtra("status");
        Log.d("Ranjeet",status);

        basicclick= (View) findViewById(R.id.basicclick);
        pregnentclick= (View) findViewById(R.id.pregnentclick);
        childclick= (View) findViewById(R.id.childclick);

        datee = (TextView) findViewById(R.id.datee);
        datee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datee.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear = calender.get(Calendar.YEAR);
                mMonth = calender.get(Calendar.MONTH);
                mDay = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(RegistrationWomen.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                datee.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

                dpd.show();
            }
        });
        time = (TextView) findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setError(null);
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog tpd = new TimePickerDialog(RegistrationWomen.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // Display Selected time in textbox
                                String AM_PM;
                                if (hourOfDay < 12) {
                                    AM_PM = "AM";
                                } else {
                                    AM_PM = "PM";
                                }

                                time.setText(hourOfDay + ":" + minute );
                            }
                        }, mHour, mMinute, false);
                tpd.show();


            }
        });
        lmpdate = (TextView) findViewById(R.id.lmpdate);
        lmpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datee.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear2 = calender.get(Calendar.YEAR);
                mMonth2 = calender.get(Calendar.MONTH);
                mDay2 = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(RegistrationWomen.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                lmpdate.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                                getAge(year, (monthOfYear + 1),dayOfMonth );
                            }
                        }, mYear2, mMonth2, mDay2);
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

                dpd.show();
            }
        });
        dob = (TextView) findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datee.setError(null);
                final Calendar calender = Calendar.getInstance();
                mYear1 = calender.get(Calendar.YEAR);
                mMonth1 = calender.get(Calendar.MONTH);
                mDay1 = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(RegistrationWomen.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                dob.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);
                                getAge(year, (monthOfYear + 1),dayOfMonth );
                            }
                        }, mYear1, mMonth1, mDay1);
//                dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

                dpd.show();
            }
        });

        nameBenificery = (EditText) findViewById(R.id.nameBenificery);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        usidNumber = (EditText) findViewById(R.id.usidNumber);
        nameaccountholder = (EditText) findViewById(R.id.nameaccountholder);
        bhamashahNumber = (EditText) findViewById(R.id.bhamashahNumber);
        aadharNumber = (EditText) findViewById(R.id.aadharNumber);
        aadharenrollment = (EditText) findViewById(R.id.aadharenrollment);
        personAge = (EditText) findViewById(R.id.personAge);
        nameofbank = (EditText) findViewById(R.id.nameofbank);
        branchname = (EditText) findViewById(R.id.branchname);
        bankaccountnumber = (EditText) findViewById(R.id.bankaccountnumber);
        ifsccode = (EditText) findViewById(R.id.ifsccode);
        distancetobranch = (EditText) findViewById(R.id.distancetobranch);
        distancenearestatm = (EditText) findViewById(R.id.distancenearestatm);
        nameofpostoffice = (EditText) findViewById(R.id.nameofpostoffice);
        addressofpostoffice = (EditText) findViewById(R.id.addressofpostoffice);
        postofficeaccount = (EditText) findViewById(R.id.postofficeaccount);
        postofficepincode = (EditText) findViewById(R.id.postofficepincode);
        hoemocode = (EditText) findViewById(R.id.hoemocode);


        pregnetNumnber = (EditText)findViewById(R.id.pregnetNumnber);

        basic= (TextView) findViewById(R.id.basic);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_young_mother_basic_details.setVisibility(View.VISIBLE);
                fragment_pregnant_lady_detail.setVisibility(View.GONE);
                basicclick.setVisibility(View.VISIBLE);
                pregnentclick.setVisibility(View.GONE);
                childclick.setVisibility(View.GONE);
            }
        });
        basicPregment = (TextView) findViewById(R.id.basicPregment);
        basicPregment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_young_mother_basic_details.setVisibility(View.GONE);
                fragment_pregnant_lady_detail.setVisibility(View.VISIBLE);
                basicclick.setVisibility(View.GONE);
                pregnentclick.setVisibility(View.VISIBLE);
                childclick.setVisibility(View.GONE);
                getSupportActionBar().setTitle("PREGNANT");
            }
        });
        child= (TextView) findViewById(R.id.child);
//gyyyh


        if(status.equals("checkedChild")){

            child.setEnabled(false);
            basicclick.setVisibility(View.VISIBLE);
            basicPregment.setVisibility(View.GONE);
            bas.setVisibility(View.VISIBLE);
            preg.setVisibility(View.GONE);
            chi.setVisibility(View.VISIBLE);
        }

        if(status.equals("checkedPregnent")){

                child.setVisibility(View.GONE);
                basicPregment.setEnabled(false);
            basicclick.setVisibility(View.VISIBLE);
            bas.setVisibility(View.VISIBLE);
            preg.setVisibility(View.VISIBLE);
            chi.setVisibility(View.GONE);

        }
         if(status.equals("Pregnentandchild")){
             basicPregment.setEnabled(false);
             basicclick.setVisibility(View.VISIBLE);
             child.setEnabled(false);
             bas.setVisibility(View.VISIBLE);
             preg.setVisibility(View.VISIBLE);
             chi.setVisibility(View.VISIBLE);

        }

        personaReligion=(Spinner) findViewById(R.id.personaReligion);
        List<String> list1 = new ArrayList<String>();
        list1.add("--Select Options--");
        list1.add("Hindu");
        list1.add("Muslim");
        list1.add("Christian");
        list1.add("Sikh");
        list1.add("Jain");
        list1.add("Parsi");
        list1.add("Buddhists");
        list1.add("No religion");
        ArrayAdapter<String> dataAdapterpersonaReligion = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list1);
        dataAdapterpersonaReligion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personaReligion.setAdapter(dataAdapterpersonaReligion);

        personaReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reli = String.valueOf(parent.getItemAtPosition(position));
                religionitemposi =parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        castSpinner=(Spinner) findViewById(R.id.castSpinner);
        List<String> listcastSpinner = new ArrayList<String>();
        listcastSpinner.add("--Select Options--");
        listcastSpinner.add(" Scheduled caste");
        listcastSpinner.add("Scheduled tribe");
        listcastSpinner.add("OBC");
        listcastSpinner.add("None of them");
        ArrayAdapter<String> dataAdaptercastSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listcastSpinner);
        dataAdaptercastSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        castSpinner.setAdapter(dataAdaptercastSpinner);

        castSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cst = String.valueOf(parent.getItemAtPosition(position));
                castitem =parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        rationcardColorSpinner=(Spinner) findViewById(R.id.rationcardColorSpinner);

        List<String> listrationcardColorSpinner = new ArrayList<String>();
        listrationcardColorSpinner.add("--Select Options--");
        listrationcardColorSpinner.add("Blue/ Green (APL)");
        listrationcardColorSpinner.add(" Dark Pink (BPL)");
        listrationcardColorSpinner.add("Yellow (Antodyaya/ Poorest)");

        ArrayAdapter<String> dataAdapterrationcardColorSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listrationcardColorSpinner);
        dataAdapterrationcardColorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rationcardColorSpinner.setAdapter(dataAdapterrationcardColorSpinner);

        rationcardColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rationcardItem = parent.getSelectedItemPosition();
                rati = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        relationshipHeadSpinner=(Spinner) findViewById(R.id.relationshipHeadSpinner);
        List<String> listrelationshipHeadSpinner = new ArrayList<String>();
        listrelationshipHeadSpinner.add("--Select Options--");

        listrelationshipHeadSpinner.add("Head");
        listrelationshipHeadSpinner.add(" Wife");
        listrelationshipHeadSpinner.add(" Daughter ");
        listrelationshipHeadSpinner.add("Daughter in law");
        listrelationshipHeadSpinner.add("Grandchild");
        listrelationshipHeadSpinner.add("Mother");
        listrelationshipHeadSpinner.add("Sister");
        listrelationshipHeadSpinner.add("Mother");
        listrelationshipHeadSpinner.add("Sister in law");
        listrelationshipHeadSpinner.add("Niece");
        listrelationshipHeadSpinner.add("Other relative");
        ArrayAdapter<String> dataAdapterrelationshipHeadSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listrelationshipHeadSpinner);
        dataAdapterrelationshipHeadSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationshipHeadSpinner.setAdapter(dataAdapterrelationshipHeadSpinner);

        relationshipHeadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                relationitem =parent.getSelectedItemPosition();
                rela = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        typeFamilySpinner=(Spinner) findViewById(R.id.typeFamilySpinner);

        List<String> listtypeFamilySpinner = new ArrayList<String>();
        listtypeFamilySpinner.add("--Select Options--");



        listtypeFamilySpinner.add("Nuclear family");
        listtypeFamilySpinner.add("Joint family");
        listtypeFamilySpinner.add("Other");

        ArrayAdapter<String> dataAdaptertypeFamilySpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listtypeFamilySpinner);
        dataAdaptertypeFamilySpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeFamilySpinner.setAdapter(dataAdaptertypeFamilySpinner);
        typeFamilySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                familyttypeItem = parent.getSelectedItemPosition();
                fami = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        educationComplted=(Spinner) findViewById(R.id.educationComplted);
        List<String> listeducationComplted = new ArrayList<String>();
        listeducationComplted.add("--Select Options--");

        listeducationComplted.add("No schooling/ illiterate ");
        listeducationComplted.add(" Primary education (up to class 4)");
        listeducationComplted.add("Middle school (up to class 8)");
        listeducationComplted.add("High School (up to class 10)");
        listeducationComplted.add("Higher secondary (10+2)");
        listeducationComplted.add("Undergrad/ Bachelors/ Prof degree");
        listeducationComplted.add("Masters degree and above");
        ArrayAdapter<String> dataAdaptereducationComplted = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listeducationComplted);
        dataAdaptereducationComplted.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationComplted.setAdapter(dataAdaptereducationComplted);

        educationComplted.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                educationItem = String.valueOf(parent.getItemAtPosition(position));
                Log.d("CheckSpinner",educationItem );
                eductionItemPosition=parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        fuelSelectionSpinner=(Spinner) findViewById(R.id.fuelSelectionSpinner);

        List<String> listfuelSelectionSpinner = new ArrayList<String>();
        listfuelSelectionSpinner.add("--Select Options--");


        listfuelSelectionSpinner.add("Wood");
        listfuelSelectionSpinner.add("Crop residues");
        listfuelSelectionSpinner.add("Dung cakes");
        listfuelSelectionSpinner.add("Coal/ charcoal");
        listfuelSelectionSpinner.add("Kerosene/ Diesel");
        listfuelSelectionSpinner.add("Electricity");
        listfuelSelectionSpinner.add("Liquified petroleum gas/ PNG");
        listfuelSelectionSpinner.add("Bio-gas");
        listfuelSelectionSpinner.add("Others");
        ArrayAdapter<String> dataAdapterfuelSelectionSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listfuelSelectionSpinner);
        dataAdapterfuelSelectionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelSelectionSpinner.setAdapter(dataAdapterfuelSelectionSpinner);

        fuelSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fuel = String.valueOf(parent.getItemAtPosition(position));
                fuelItemPosition=parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        decsionSpinner= (Spinner) findViewById(R.id.decsionSpinner);

        List<String> listdecsionSpinner = new ArrayList<String>();
        listdecsionSpinner.add("--Select Options--");



        listdecsionSpinner.add("Self");
        listdecsionSpinner.add("Husband/Wife");
        listdecsionSpinner.add("Mother in law");
        listdecsionSpinner.add("Father in law");
        listdecsionSpinner.add("Someone else in the family");
        listdecsionSpinner.add("Other relative");
        ArrayAdapter<String> dataAdapterdecsionSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listdecsionSpinner);
        dataAdapterdecsionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decsionSpinner.setAdapter(dataAdapterdecsionSpinner);

        decsionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                deci = String.valueOf(parent.getItemAtPosition(position));
                decisionItemPosition=parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        decsionVisitDoctorSpinner  = (Spinner) findViewById(R.id.decsionVisitDoctorSpinner);
        List<String> listdecsionVisitDoctorSpinner = new ArrayList<String>();
        listdecsionVisitDoctorSpinner.add("--Select Options--");
        listdecsionVisitDoctorSpinner.add("Self");
        listdecsionVisitDoctorSpinner.add("Husband/Wife");
        listdecsionVisitDoctorSpinner.add("Mother in law");
        listdecsionVisitDoctorSpinner.add("Father in law");
        listdecsionVisitDoctorSpinner.add("Someone else in the family");
        listdecsionVisitDoctorSpinner.add("Other relative");
        ArrayAdapter<String> dataAdapterdecsionVisitDoctorSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listdecsionVisitDoctorSpinner);
        dataAdapterdecsionVisitDoctorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        decsionVisitDoctorSpinner.setAdapter(dataAdapterdecsionVisitDoctorSpinner);

        decsionVisitDoctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                visi = String.valueOf(parent.getItemAtPosition(position));
                doctorvisitItemPosition=parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        Button save = (Button) findViewById(R.id.youngMotherSave);
        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if(fragment_young_mother_basic_details.getVisibility()==View.VISIBLE){

                                            int errorCode=validateBasicForm();
                                            if(errorCode==0) {

                                                if (registerMemeberId == null) {
                                                    Log.d("awc_code", new Login().awc_code);
                                                    familyID = createFamilyId(new Login().awc_code);

                                                    insertFamily(familyID,"ADD");
                                                    memberId = createMemberId(familyID);

                                                    String memberInsterted = insertMemberMaster(familyID,memberId,"ADD");
                                                    String womenExtraInserted = inserWomenExtra(memberId,"ADD");


                                                    if(memberInsterted.equals("1")){
                                                        registerMemeberId=memberId;

                                                        fragment_young_mother_basic_details.setVisibility(View.GONE);
                                                        fragment_pregnant_lady_detail.setVisibility(View.VISIBLE);
//           basicPregment.setText("Pregnenet ACtivated");
                                                        basic.setBackgroundResource(0);
                                                        pregnentclick.setVisibility(View.VISIBLE);
                                                        getSupportActionBar().setTitle("PREGNANT");
                                                        basicclick.setVisibility(View.GONE);
                                                        if(status.equalsIgnoreCase("checkedPregnent") || status.equalsIgnoreCase("Pregnentandchild")){

                                                            basicPregment.setEnabled(true);
                                                        }


                                                    }


//        Log.d("familyLog", familyID);

                                                }
                                                else{
                                                    insertFamily(familyID,"EDIT");
                                                    insertMemberMaster(familyID,memberId,"EDIT");
                                                    inserWomenExtra(memberId,"EDIT");

                                                    if(basicPregment.isEnabled()){
                                                        Log.d("CheckNull","insideEnbale"+pregnentId);



                                                        insertPregnent(memberId,pregnentId);
                                                    }
                                                }


                                            }

                                        }else if(fragment_pregnant_lady_detail.getVisibility()==View.VISIBLE){
                                            if(pregnetNumnber.getText().length()==0){
                                                pregnetNumnber.setError("");
                                                Toast.makeText(RegistrationWomen.this, "Enter Number of child", Toast.LENGTH_SHORT).show();
                                            }
                                            else if(lmpdate.getText().toString().length()==0){
                                                Toast.makeText(RegistrationWomen.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                                            }
                                            else {

                                                Log.d("topsecret",dob.getText().toString());
                                                Toast.makeText(RegistrationWomen.this, dob.getText().toString(), Toast.LENGTH_SHORT).show();

                                                insertPregnent(memberId,pregnentId);
                                            }
                                        }




            }
        });

    }

    public String createFamilyId(String awcCode){


//        SQLiteDatabase dbSqli = openOrCreateDatabase("rajpustData",MODE_PRIVATE,null);

        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);


//        Cursor c = dbs.rawQuery("select * from familydata",null);
//
//        if (c != null && c.moveToFirst()) {}


//
        Cursor c = dbs.rawQuery("SELECT * FROM familydata where awc_code ='"+awcCode+"'" , null);
//        String familyId=awcCode+c;

        Log.d("query","SELECT *  FROM familydata where awc_code ="+awcCode );
//        Log.d("awc_code",c.getColumnIndex("sl_no")+"");
        c.getCount();

        int count = c.getCount();
//        Log.d("count",""+count);
        Log.d("countregister",""+ c.getCount());

      String familyCode= awcCode+String.format("%03d",count+1);

      Log.d("family_code",familyCode);

//        if()

return familyCode;

        }

        public String createMemberId(String familyID){


            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            Cursor c = dbs.rawQuery("SELECT * FROM memberbasic where familyy_id ='"+familyID+"'" , null);

//            Log.d("query","SELECT *  FROM familydata where awc_code ="+awcCode );
            c.getCount();
            int count = c.getCount();
               Log.d("countregister",""+ c.getCount());
            String memberId= familyID+String.format("%02d",count+1);

            Log.d("member_id",memberId);

        return memberId;
        }

        public void insertFamily(String familyId,String mode){

            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            FamilyDetailGetSet family = new FamilyDetailGetSet(familyId,1, castitem, rationcardItem, familyttypeItem, new Login().dist_code, new Login().project_code,new Login().sector_code,new Login().awc_code, "1");
if(mode.equals("ADD")) {
    long id = db.addFamilyData(family);
}
if(mode.equals("EDIT")){

    ContentValues contentValues = new ContentValues();
    contentValues.put("religion","1");
    contentValues.put("caste",castitem);
    contentValues.put("rcard",rationcardItem);
    contentValues.put("family_type",familyttypeItem);


    dbs.update("familydata",contentValues,"familyid="+familyId,null);


//    dbs.execSQL("update familydata set (religion,caste,rcard,family_type) =('1',castitem,rationcardItem,familyttypeItem) where familyid=familyId" );
}
//

              SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

              Cursor c = swl.rawQuery("select * from familydata",null);

              int total = c.getCount();

              if(total>=1){

                  if(c.moveToFirst()){
                      do{

                          Log.d("awcrecords",c.getString(c.getColumnIndex("awc_code")));
                          Log.d("awcrecords",c.getString(c.getColumnIndex("dist_code")));
                          Log.d("awcrecords",c.getString(c.getColumnIndex("familyid")));

                      }while (c.moveToNext());
                  }


              }



              if (c != null && c.moveToLast()) {
              long    lastId = c.getLong(0);
//Log.d("vskp",c.getString(c.getColumnIndex(("erm_unique_key"))));
                  Log.d("printValue",""+lastId);

                String valueIndex=  c.getString(c.getColumnIndex("awc_code"));

                  Log.d("insedrtValue","inserted vAlue"+valueIndex);
              }




          }

//            Cursor c = dbs.rawQuery("")


        public String insertMemberMaster(String familyId,String memberId,String mode){

            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
      MemberBasicGetSet memberbasic = new MemberBasicGetSet(memberId, familyId,nameBenificery.getText().toString(), new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()), new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()), " ", "13-04-1991", "15", "N", " ", "1212112"," ", " ", " ", " ", " ", " ", "M", "N", "M", " ", "n", " ", " ", " ", new Login().surveyerId, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()), "Mobile");
            String inserted = "0";
      if(mode.equals("ADD")) {
          long id = db.addMemberBasic(memberbasic);
          Log.d("insertMember", " " + id);

          if (id != -1) {
              inserted = "1";
          }
      }
      else{
          ContentValues contentValue = new ContentValues();
          contentValue.put("name",nameBenificery.getText().toString());
          contentValue.put("dor",new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
          contentValue.put("doentry",new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
          Log.d("updateValue",memberId);
          Log.d("MemberID",nameBenificery.getText().toString());

          long uid=dbs.update("memberbasic",contentValue,"MemberId=?",new String[]{memberId});
          inserted = "1";
//          Log.d("MemberID",""+uid);
      }
                SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                Cursor c = swl.rawQuery("select * from memberbasic", null);
                int total = c.getCount();
                if (total >= 1) {

                    if (c.moveToFirst()) {
                        do {

                            Log.d("MemberID", "NAME"+c.getString(c.getColumnIndex("name")));
                            Log.d("MemberID", "DOR"+c.getString(c.getColumnIndex("dor")));
                            Log.d("MemberID", "FAMILYid"+c.getString(c.getColumnIndex("familyy_id")));
                            Log.d("MemberID", "MERMBERID"+c.getString(c.getColumnIndex("MemberId")));
//                            Log.d("awcrecords", c.getString(c.getColumnIndex("familyid")));

                        } while (c.moveToNext());
                    }


                }


//

return  inserted;
        }

        public String inserWomenExtra(String memberID,String mode){


            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
            WomenBasicGetSet womenExtra = new WomenBasicGetSet(memberID, String.valueOf(eductionItemPosition), "", " ", " ", " ", nameaccountholder.getText().toString(), " ", " ",  "", " ", " ", " ", " ", " ", " ", " "," "," "," ");

            String inserted = "0";

            if(mode.equals("ADD")) {
                long id = db.addWomenExtra(womenExtra);
                Log.d("insertMember", " " + id);

                if (id != -1) {
                    inserted = "1";
                }
            }

            else{


                ContentValues contentValue = new ContentValues();
                contentValue.put("education",eductionItemPosition);
                contentValue.put("acholder_name",nameaccountholder.getText().toString());
//                contentValue.put("doentry",new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
//                Log.d("updateValue",memberId);
//                Log.d("MemberID",nameBenificery.getText().toString());

                long uid=dbs.update("womenextra",contentValue,"MemberId=?",new String[]{memberId});
//          Log.d("MemberID",""+uid);
                inserted = "1";

            }


            SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            Cursor c = swl.rawQuery("select * from womenextra", null);
            int total = c.getCount();
            if (total >= 1) {

                if (c.moveToFirst()) {
                    do {

                        Log.d("MemberID", "EDUCATION"+c.getString(c.getColumnIndex("education")));
                        Log.d("MemberID", "ACCOUNTHOLDER"+c.getString(c.getColumnIndex("acholder_name")));
//                        Log.d("MemberID", c.getString(c.getColumnIndex("familyy_id")));
//                        Log.d("MemberID", c.getString(c.getColumnIndex("MemberId")));
//                            Log.d("awcrecords", c.getString(c.getColumnIndex("familyid")));

                    } while (c.moveToNext());
                }


            }


            return inserted;
        }


public int validateBasicForm(){

    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        int error =0;
if(nameBenificery.getText().toString().length()==0){

    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
    nameBenificery.requestFocus();
    imm.showSoftInput(nameBenificery, InputMethodManager.SHOW_IMPLICIT);
    error=1;

}

if(error==0 && phoneNumber.getText().toString().length()!=10){
    phoneNumber.requestFocus();
    imm.showSoftInput(phoneNumber, InputMethodManager.SHOW_IMPLICIT);
    Toast.makeText(this, "Enter valid Phone Number", Toast.LENGTH_SHORT).show();
    error=1;
}

if(error==0 && usidNumber.getText().toString().length()==0){
    usidNumber.requestFocus();
    imm.showSoftInput(usidNumber, InputMethodManager.SHOW_IMPLICIT);
    Toast.makeText(this, "Enter UID Numnber", Toast.LENGTH_SHORT).show();
error=1;
}


    if(error == 0 && bhamashahNumber.getText().toString().length()==0 ){
        bhamashahNumber.requestFocus();
        imm.showSoftInput(bhamashahNumber, InputMethodManager.SHOW_IMPLICIT);
        Toast.makeText(this, "Enter Bhamashah Number", Toast.LENGTH_SHORT).show();
        error =1;
    }

    if(adhaaravailable.isChecked()){
        if(error == 0 && aadharenrollment.getText().toString().length()==0 ){
            aadharenrollment.requestFocus();
            imm.showSoftInput(aadharenrollment, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Aadharenrollment Number", Toast.LENGTH_SHORT).show();
            error =1;
        }
//        if(error == 0 && datee.getText().toString().length()==0 ){
//            Toast.makeText(this, "Enter Date", Toast.LENGTH_SHORT).show();
//            error =1;
//        } if(error == 0 && time.getText().toString().length()==0 ){
//            Toast.makeText(this, "Enter Time", Toast.LENGTH_SHORT).show();
//            error =1;
//        }
    }else{
        if(error == 0 && aadharNumber.getText().toString().length()!=12 ){
            aadharNumber.requestFocus();
            imm.showSoftInput(aadharNumber, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Valid Aadhar Number", Toast.LENGTH_SHORT).show();
            error =1;
        }
    }


    if(error == 0 && personAge.getText().toString().length()==0 ){
        personAge.requestFocus();
        imm.showSoftInput(personAge, InputMethodManager.SHOW_IMPLICIT);
        Toast.makeText(this, "Enter Age", Toast.LENGTH_SHORT).show();
        error =1;
    }


    if(error==0 && reli.equalsIgnoreCase("--Select Options--")){
        personaReligion.requestFocus();
        Toast.makeText(this, "Select Religion", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && cst.equalsIgnoreCase("--Select Options--")){
        castSpinner.requestFocus();
        Toast.makeText(this, "Select Caste", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && rati.equalsIgnoreCase("--Select Options--")){
        rationcardColorSpinner.requestFocus();
        Toast.makeText(this, "Select RationCard", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && rela.equalsIgnoreCase("--Select Options--")){
        relationshipHeadSpinner.requestFocus();
        Toast.makeText(this, "Select Relation with head of the household", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && fami.equalsIgnoreCase("--Select Options--")){
        typeFamilySpinner.requestFocus();
        Toast.makeText(this, "Select Family Type", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && educationItem.equalsIgnoreCase("--Select Options--")){
        educationComplted.requestFocus();
        Toast.makeText(this, "Select Education", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && fuel.equalsIgnoreCase("--Select Options--")){
        fuelSelectionSpinner.requestFocus();
        Toast.makeText(this, "Select Fuel", Toast.LENGTH_SHORT).show();
        error=1;
    }
    if(error==0 && deci.equalsIgnoreCase("--Select Options--")){
        decsionSpinner.requestFocus();
        Toast.makeText(this, "Select Decision Maker for own health", Toast.LENGTH_SHORT).show();
        error=1;
    }if(error==0 && visi.equalsIgnoreCase("--Select Options--")){
        decsionVisitDoctorSpinner.requestFocus();
        Toast.makeText(this, "Select decision maker for child health", Toast.LENGTH_SHORT).show();
        error=1;
    }

    if(accountype.equalsIgnoreCase("B")){
        if(error == 0 && nameofbank.getText().toString().length()==0 ){
            nameofbank.requestFocus();
            imm.showSoftInput(nameofbank, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Bank Name", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && branchname.getText().toString().length()==0 ){
            branchname.requestFocus();
            imm.showSoftInput(branchname, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Branch Name", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && bankaccountnumber.getText().toString().length()==0 ){
            bankaccountnumber.requestFocus();
            imm.showSoftInput(bankaccountnumber, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Bank Account Number", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && ifsccode.getText().toString().length()==0 ){
            ifsccode.requestFocus();
            imm.showSoftInput(ifsccode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Bank IFSC Code", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && distancetobranch.getText().toString().length()==0 ){
            distancetobranch.requestFocus();
            imm.showSoftInput(distancetobranch, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Distance", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && distancenearestatm.getText().toString().length()==0 ){
            distancenearestatm.requestFocus();
            imm.showSoftInput(distancenearestatm, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Distance from ATM", Toast.LENGTH_SHORT).show();
            error =1;
        }
    }
    if (accountype.equalsIgnoreCase("P")){
        if(error == 0 && nameofpostoffice.getText().toString().length()==0 ){
            nameofpostoffice.requestFocus();
            imm.showSoftInput(nameofpostoffice, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Name of Post Office", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && addressofpostoffice.getText().toString().length()==0 ){
            addressofpostoffice.requestFocus();
            imm.showSoftInput(addressofpostoffice, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Address of Post Office", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && postofficeaccount.getText().toString().length()==0 ){
            postofficeaccount.requestFocus();
            imm.showSoftInput(postofficeaccount, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Post Office Account Number", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && postofficepincode.getText().toString().length()==0 ){
            postofficepincode.requestFocus();
            imm.showSoftInput(postofficepincode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Post Office Pincode", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && hoemocode.getText().toString().length()==0 ){
            hoemocode.requestFocus();
            imm.showSoftInput(hoemocode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Hoemo Code", Toast.LENGTH_SHORT).show();
            error =1;
        }
    }

return error;


}

    public String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        personAge.setText(ageS);
        personAge.setEnabled(false);
        Log.d("age",ageS);

        return ageS;
    }

public String insertPregnent(String memberId,String pregnentID){
   String teppregnemtIDs="";
    String inserted="";
    if(pregnentID==null){
        Log.d("CheckNull","inserted ");

        pregnentId= generatePregnentID(memberId);
        Log.d("CheckNull",pregnentId);
    }


    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

    Cursor c = dbs.rawQuery("SELECT * FROM  pregnant where pregnancy_id ='"+pregnentId+"'" , null);
    Log.d("MANASQUERY","SELECT * FROM  pregnant where pregnancy_id ="+pregnentId);

//            Log.d("query","SELECT *  FROM familydata where awc_code ="+awcCode );
    c.getCount();
    int counted = c.getCount();
    Log.d("CountPregnent",""+counted);
    Log.d("CountPregnent",""+pregnentId);

    if(counted==0){
Log.d("checkInsretd","Pregrncy Insreted");
        PregnantGetSet pregnent = new PregnantGetSet(pregnentId, memberId, Integer.valueOf(pregnetNumnber.getText().toString()), "19/02/2018",  new Login().surveyerId, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()),"mobile","Y","","Y","");
       long id= db.addPregnant(pregnent);
       Log.d("CountPregnent","sucieesrate"+id);


        inserted = "1";
    }
    else{



        ContentValues contentValues = new ContentValues();
        contentValues.put("lmp_date","23/02/2018");
        contentValues.put("orderof_pregnancy",Integer.valueOf(pregnetNumnber.getText().toString()));
        contentValues.put("IS_EDITED","Y");
        contentValues.put("TIMEE_STAMP",new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));


        dbs.update("pregnant",contentValues,"pregnancy_id=?",new String[]{pregnentID});
        Log.d("checkInsretd","Pregrncy updated ");
        inserted = "1";

    }

    SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

    Cursor cr = swl.rawQuery("select * from pregnant", null);
    int total = cr.getCount();
    if (total >= 1) {

        int i=0;
        if (cr.moveToFirst()) {
            do {

                Log.d("MemberID", "orderofPRegncy"+ cr.getString(cr.getColumnIndex("orderof_pregnancy")));
                Log.d("MemberID", "lmp"+ cr.getString(cr.getColumnIndex("lmp_date")));
                Log.d("MemberID", "timestamp"+cr.getString(cr.getColumnIndex("timee_stamp")));
                Log.d("MemberID", "timestamp"+cr.getString(cr.getColumnIndex("pregnancy_id")));
//                        Log.d("MemberID", c.getString(c.getColumnIndex("familyy_id")));
//                        Log.d("MemberID", c.getString(c.getColumnIndex("MemberId")));
//
//             Log.d("awcrecords", c.getString(c.getColumnIndex("familyid")));

                i++;
            } while (cr.moveToNext());
        }

        Log.d("recordCountPW",""+i);
    }




return inserted;
}

public String generatePregnentID(String memberID){


    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

    Cursor c = dbs.rawQuery("SELECT * FROM  pregnant where MemberId ='"+memberID+"'" , null);

//            Log.d("query","SELECT *  FROM familydata where awc_code ="+awcCode );
    c.getCount();
    int count = c.getCount();
    Log.d("countregister",""+ c.getCount());
    String pregantId= memberID+"P"+String.format("%02d",count+1);
    Log.d("pregnemtid",pregantId);
//    return memberId;
    return pregantId;
}

    @Override
    public void onBackPressed() {

        dialogCoupon    = new Dialog(RegistrationWomen.this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.exit_dialog);
        dialogCoupon.setCancelable(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogCoupon.setCanceledOnTouchOutside(true);


        Button yes = (Button)dialogCoupon.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

//
}
