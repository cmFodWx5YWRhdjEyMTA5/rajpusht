package in.co.rajpusht.rajpusht;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Adpter.NumberChildAdapter;
import extras.ChildExtraGetSet;
import extras.DbHelper;
import extras.FamilyDetailGetSet;
import extras.MemberBasicGetSet;
import extras.PregnantGetSet;
import extras.RecyclerTouchListener;
import extras.SessionManager;
import extras.WomenBasicGetSet;

public class RegistrationWomen extends AppCompatActivity  {
    NumberChildAdapter numberAdpetr;
    String status;
    String childSavingMode;
    CheckBox checkChild,checkPregnets;
    TextView basic,basicPregment,child;
    View basicclick, pregnentclick, childclick;
    CheckBox adhaaravailable;
    String registerMemeberId,familyID,pregnentId;
    int castitem,relationitem,rationcardItem,familyttypeItem,eductionItemPosition, religionitemposi, decisionItemPosition, doctorvisitItemPosition;
    LinearLayout noadhaar, banklayout, postofficeaccountdetail;
    RelativeLayout bas, preg, chi;
    String ChildWeightFinal="";
    String fuelItemPosition="";
   DbHelper db;
    EditText nameBenificery,phoneNumber,usidNumber,nameaccountholder, bhamashahNumber,aadharNumber,aadharenrollment,personAge, nameofbank, branchname,
            bankaccountnumber, ifsccode, distancetobranch, distancenearestatm, nameofpostoffice, addressofpostoffice, postofficeaccount, postofficepincode,
            confirmnameaccountholder,confirmbankaccountnumber, confirmifsccode, hoemocode,confirmpostofficeAccount,confirmpostpincode;
    TextView datee, time, lmpdate, dob;
    RadioGroup bankgroup;
    Spinner personaReligion,castSpinner,rationcardColorSpinner,relationshipHeadSpinner,typeFamilySpinner,educationComplted,fuelSelectionSpinner,
            decsionSpinner,decsionVisitDoctorSpinner;
    View fragment_pregnant_lady_detail,fragment_young_mother_basic_details,childDetails;
    EditText pregnetNumnber;
    String educationItem, accountype = "", reli, cst, rati, rela, fami, fuel, deci, visi,ageS;
    private int mYear, mMonth, mDay, mHour, mMinute,mSec, mYear1, mMonth1, mDay1, mYear2, mMonth2, mDay2;
    Dialog dialogCoupon;

    Spinner placeofDelivery,sexOfchild,wasChildBorn,birthBreast,firstyellowFeed;
    String DeliveryPlace,childSex,wasChildBornString;
    int deliveryPlaceItemSelected,childSexItemSelected,wasChildBornPosition;

    EditText nameOfChild,orderOfBirth,childWeight,childWeightGram;
    TextView dateOfDelivery,addchild;
    String childIdrecyclerviewPosition;

    int activatedREgistrationForm;
    RecyclerView recyclerViewChild;
    ArrayList<String>  arrayNoOfChild = new ArrayList<String>();
    SessionManager session;
    Float chweightvalue;
    LinearLayout linearCyrrentlyUsing;
    String methodcontra="";

    CheckBox f1,f2,f3,f4,f5,f6,f7,f8,f9;
    TextView beneficairyTest,dateofbirthMother;

    EditText timeHour,timeMin,timeSec;

    EditText beneficiaryHusband, pctsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_young_mother);

//        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("BASIC");





        db= new DbHelper(this);
        session = new SessionManager(RegistrationWomen.this);

        fragment_young_mother_basic_details = (View)findViewById(R.id.pregnentbasic);
        fragment_pregnant_lady_detail = (View)findViewById(R.id.pregnentDetails);
        childDetails = findViewById(R.id.childDetails);
        beneficairyTest = (TextView) findViewById(R.id.beneficairyTest);
        dateofbirthMother= (TextView) findViewById(R.id.dateofbirthMother);


        linearCyrrentlyUsing = (LinearLayout) findViewById(R.id.linearCyrrentlyUsing);

        noadhaar = (LinearLayout) findViewById(R.id.noadhaar) ;
        banklayout = (LinearLayout) findViewById(R.id.banklayout) ;
        postofficeaccountdetail = (LinearLayout) findViewById(R.id.postofficeaccountdetail) ;
        bankgroup = (RadioGroup) findViewById(R.id.bankgroup) ;
        bankgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.accountyes)
                {
                    banklayout.setVisibility(View.VISIBLE);
                    postofficeaccountdetail.setVisibility(View.GONE);
                    accountype = "B";

                }
                else if(checkedId == R.id.accountno)
                {
                    postofficeaccountdetail.setVisibility(View.VISIBLE);
                    banklayout.setVisibility(View.GONE);
                    accountype = "P";
                }


                else if(checkedId == R.id.noaccount)
                {
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
                    aadharNumber.setEnabled(false);
                    aadharNumber.setText("");
                }else {
                    noadhaar.setVisibility(View.GONE);
                    aadharNumber.setEnabled(true);
                }
            }
        });

        bas= (RelativeLayout) findViewById(R.id.relativeBasic);
        preg= (RelativeLayout) findViewById(R.id.relativePregnent);
        chi= (RelativeLayout) findViewById(R.id.relativeChild);

               timeHour= (EditText) findViewById(R.id.timeHour);
                timeMin= (EditText) findViewById(R.id.timeMin);
                timeSec= (EditText) findViewById(R.id.timeSec);


        status = getIntent().getStringExtra("status");
//        Log.d("Ranjeet",status);

        basicclick= (View) findViewById(R.id.basicclick);
        pregnentclick= (View) findViewById(R.id.pregnentclick);
        childclick= (View) findViewById(R.id.childclick);


        f1= (CheckBox) findViewById(R.id.f1);
        f2= (CheckBox) findViewById(R.id.f2);
        f3= (CheckBox) findViewById(R.id.f3);
        f4= (CheckBox) findViewById(R.id.f4);
        f5= (CheckBox) findViewById(R.id.f5);
        f6= (CheckBox) findViewById(R.id.f6);
        f7= (CheckBox) findViewById(R.id.f7);
        f8= (CheckBox) findViewById(R.id.f8);
        f9= (CheckBox) findViewById(R.id.f9);


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




                                datee.setText(year + "-"
                                        + (month) + "-" + day);

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
                mSec =c.get(Calendar.SECOND);

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
        dob = (TextView) findViewById(R.id.dob);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datee.setError(null);
               Calendar calender = Calendar.getInstance();
                mYear1 = calender.get(Calendar.YEAR);
                mMonth1 = calender.get(Calendar.MONTH);
                mDay1 = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(RegistrationWomen.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                String month,day;
                                if((monthOfYear+1)<10)
                                {

                                    month="0"+String.valueOf(monthOfYear+1);

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

                                dob.setText(year + "-"
                                        + month + "-" + day);
                                getAge(year, (monthOfYear + 1),dayOfMonth );
                            }
                        }, mYear1, mMonth1, mDay1);



//          calender.add(Calendar.DAY_OF_MONTH,-48);
//          String dod= (calender.get(Calendar.MONTH) + 1) + "-"
//                        + calender.get(Calendar.DATE) + "-" + calender.get(Calendar.YEAR);
//                calender.add(Calendar.DATE,-281);

                Calendar cal = Calendar.getInstance();

                cal.add(Calendar.DAY_OF_MONTH, -4745);
                Date result = cal.getTime();
//                dpd.getDatePicker().setMinDate(calender.getTimeInMillis());
                dpd.getDatePicker().setMaxDate(result.getTime());
                dpd.show();


                dpd.show();
            }
        });

        nameBenificery = (EditText) findViewById(R.id.nameBenificery);

        beneficiaryHusband = (EditText)findViewById(R.id.nameBenificeryhusband);
        pctsId = (EditText)findViewById(R.id.nameBenificerypctsId);

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
        confirmnameaccountholder = (EditText) findViewById(R.id.confirmnameaccountholder);
        confirmnameaccountholder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(nameaccountholder.getText().toString().equals(confirmnameaccountholder.getText().toString())){

                }else{
                    confirmnameaccountholder.setError("Invalid Holder Name");
                }
            }
        });

        confirmbankaccountnumber = (EditText) findViewById(R.id.confirmbankaccountnumber);
        confirmbankaccountnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
if(bankaccountnumber.getText().toString().equals(confirmbankaccountnumber.getText().toString())){

}
else{
    confirmbankaccountnumber.setError("Invalid Account No.");
}
            }
        });
        confirmifsccode = (EditText) findViewById(R.id.confirmifsccode);
        confirmifsccode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
if(confirmifsccode.getText().toString().equals(ifsccode.getText().toString())){

}
else{
    confirmifsccode.setError("Invalid IFSC Code");
}

            }
        });
        confirmpostofficeAccount= (EditText) findViewById(R.id.comfirmPostofficeaccount);
        confirmpostofficeAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(postofficeaccount.getText().toString().equals(confirmpostofficeAccount.getText().toString())){

                }
                else{
                    confirmpostofficeAccount.setError("Invalid Account No.");
                }

            }
        });
                confirmpostpincode= (EditText) findViewById(R.id.confirmpostofficepincode);

        confirmpostpincode.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {


                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                        if(postofficepincode.getText().toString().equals(confirmpostpincode.getText().toString())){

                        }
                        else{
                            confirmpostpincode.setError("Invalid pincode");
//                            Toast.makeText(RegistrationWomen.this, "Postal Pincode Mismatch", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //child name
        edittextValidate();




        recyclerViewChild = (RecyclerView)findViewById(R.id.recyclerviewChildAdd);
        recyclerViewChild.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewChild, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Log.d("checkPosition"," "+position);
                childIdrecyclerviewPosition=arrayNoOfChild.get(position);
//                Log.d("checkPosition",childId);

                getChildDetails(childIdrecyclerviewPosition);



//                Toast.makeText(getApplicationContext(), movie.getDropLocation() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        dateOfDelivery= (TextView) findViewById(R.id.dateOfDelivery);
        nameOfChild = (EditText) findViewById(R.id.nameOfChild);
        orderOfBirth= (EditText) findViewById(R.id.orderOfBirth);
        childWeight = (EditText) findViewById(R.id.childWeight);
        childWeightGram = (EditText) findViewById(R.id.childWeightGram);
        addchild = (TextView) findViewById(R.id.addchild);

        addchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearChildForm();
            }
        });


dateOfDelivery.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

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
                        dateOfDelivery.setText(year + "-"
                                + month + "-" + day);
//                        getAge(year, (monthOfYear + 1),dayOfMonth );
                    }
                }, mYear2, mMonth2, mDay2);
//      dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//        calender.add(Calendar.DATE,-1464);
//        calender.add(Calendar.DATE,4392);
//        dpd.getDatePicker().setMaxDate(Calendar.DATE,4392);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1095);
        Date result = cal.getTime();
        dpd.getDatePicker().setMaxDate(calender.getTimeInMillis());
        dpd.getDatePicker().setMinDate(result.getTime());
        dpd.show();



    }
});


        pregnetNumnber = (EditText)findViewById(R.id.pregnetNumnber);

        basic= (TextView) findViewById(R.id.basic);
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basicclickedForm();


            }
        });
        basicPregment = (TextView) findViewById(R.id.basicPregment);
        basicPregment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basicPregnentClickForm();

            }
        });
        child= (TextView) findViewById(R.id.child);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                childPregnentForm();



            }
        });



            modeSetting(status,registerMemeberId);



        placeofDelivery = (Spinner)  findViewById(R.id.placeofDelivery);
//
        List<String> listplaceofDelivery = new ArrayList<String>();
        listplaceofDelivery.add("--Select Options--");

        listplaceofDelivery.add("Hospital/ PHC/CHC/ Private clinic");
        listplaceofDelivery.add("Home");

        ArrayAdapter<String> postofDelivery = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_birthplace));
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

        ArrayAdapter<String> dataAdaptersexOfchild = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_childgender));
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


        ArrayAdapter<String> dataAdapterwasChildBorn = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_birthbefore9month));
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

        ArrayAdapter<String> dataAdapterbirthBreast = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_childtobreast));
        dataAdapterbirthBreast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthBreast.setAdapter(dataAdapterbirthBreast);


        firstyellowFeed= (Spinner) findViewById(R.id.firstyellowFeed);

        List<String> listfirstyellowFeed = new ArrayList<String>();

        listfirstyellowFeed.add("--Select Options--");
        listfirstyellowFeed.add("Yes");
        listfirstyellowFeed.add("No");
//        listfirstyellowFeed.add("Intersexed");

        ArrayAdapter<String> dataAdapterfirstyellowFeed = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  getResources().getStringArray(R.array.array_khees));
        dataAdapterfirstyellowFeed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstyellowFeed.setAdapter(dataAdapterfirstyellowFeed);
//

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
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_Religion));
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
        listcastSpinner.add("General");
        ArrayAdapter<String> dataAdaptercastSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_cast));
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
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_rationcardColor));
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

        listrelationshipHeadSpinner.add("Sister in law");
        listrelationshipHeadSpinner.add("Niece");
        listrelationshipHeadSpinner.add("Other relative");
        ArrayAdapter<String> dataAdapterrelationshipHeadSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_relationshipHead));
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
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_typeFamily));
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
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_education));
        dataAdaptereducationComplted.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationComplted.setAdapter(dataAdaptereducationComplted);

        educationComplted.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                educationItem = String.valueOf(parent.getItemAtPosition(position));
//                Log.d("CheckSpinner",educationItem );
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
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_fuelSelection));
        dataAdapterfuelSelectionSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelSelectionSpinner.setAdapter(dataAdapterfuelSelectionSpinner);

        fuelSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                fuel = String.valueOf(parent.getItemAtPosition(position));
//                fuelItemPosition=parent.getSelectedItemPosition();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        decsionSpinner= (Spinner) findViewById(R.id.decsionSpinner);

        List<String> listdecsionSpinner = new ArrayList<String>();
        listdecsionSpinner.add("--Select Options--");



        listdecsionSpinner.add("Self");
        listdecsionSpinner.add("Husband");
        listdecsionSpinner.add("Mother in law");
        listdecsionSpinner.add("Father in law");
        listdecsionSpinner.add("Someone else in the family");
        listdecsionSpinner.add("Other relative");
        ArrayAdapter<String> dataAdapterdecsionSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_decsion));
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
        listdecsionVisitDoctorSpinner.add("Husband");
        listdecsionVisitDoctorSpinner.add("Mother in law");
        listdecsionVisitDoctorSpinner.add("Father in law");
        listdecsionVisitDoctorSpinner.add("Someone else in the family");
        listdecsionVisitDoctorSpinner.add("Other relative");
        ArrayAdapter<String> dataAdapterdecsionVisitDoctorSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_decsion_child));
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

//                                        if(fragment_young_mother_basic_details.getVisibility()==View.VISIBLE){
try {
     ChildWeightFinal = childWeight.getText().toString() + "." + childWeightGram.getText().toString();

//    chweightvalue = Float.parseFloat(ChildWeightFinal);


//    Log.d("RegButton",chweightvalue+"");


}catch (Exception e){

}

                                        if (registerMemeberId == null) {


                                                    Log.d("insertedValue","inserted Ito regsitertion");
                                                    int errorCode=validateBasicForm();
                                                    if(errorCode==0) {
//                                                        Log.d("awc_code", new Login().awc_code);
                                                        familyID = createFamilyId(session.getAwcCode());

                                                        insertFamily(familyID, "ADD");
                                                        String memberId = createMemberId(familyID);

                                                        String memberInsterted = insertMemberMaster(familyID, memberId, "ADD");
                                                        String womenExtraInserted = inserWomenExtra(memberId, "ADD");


                                                        if (memberInsterted.equals("1")) {
                                                            registerMemeberId = memberId;

//


                                                        }

                                                        getChildNumbers(registerMemeberId);

                                                        tabMovement();
//        Log.d("familyLog", familyID);}
                                                    }


                                                }
                                                else{



                                                    if(status.equalsIgnoreCase("checkedPregnent")){


                                                      int  errorCode=  validateBasicForm();
//                                                        Log.d("ErrorCode",""+errorCode);
                                                        if(errorCode==0)
                                                        {

                                                        errorCode =  pregentFormVAlidtion();

                                                        if(errorCode==0){
                                                            insertFamily(familyID,"EDIT");
                                                            insertMemberMaster(familyID,registerMemeberId,"EDIT");
                                                            inserWomenExtra(registerMemeberId,"EDIT");
                                                            insertPregnent(registerMemeberId,pregnentId);
                                                            tabMovement();

                                                        }else{
                                                            basicPregnentClickForm();
                                                              }
                                                        }
                                                        else{
//                                                            Log.d("Validation","inserted");
                                                            basicclickedForm();
                                                        }

                                                    }

                                                    if(status.equalsIgnoreCase("checkedChild")){


                                                        beneficairyTest.setText(R.string.nameofbeneficiary);
                                                        dateofbirthMother.setText(R.string.dateofbirthbeneficary);
                                                        int  errorCode=  validateBasicForm();
                                                        Log.d("ErrorCode",""+errorCode);
                                                        if(errorCode==0) {
                                                        errorCode = childformValidation();

                                                            Log.d("ErrorCode","childform validationn"+errorCode);
                                                        if(errorCode==0){

                                                             insertFamily(familyID,"EDIT");
                                                            insertMemberMaster(familyID,registerMemeberId,"EDIT");
                                                            inserWomenExtra(registerMemeberId,"EDIT");


if(childSavingMode.equalsIgnoreCase("new")) {
    insertChildDetails(registerMemeberId, familyID);
    promtForChild();
}
if(childSavingMode.equalsIgnoreCase("edit")){
    Log.d("ChildEdit","Inserted in edit part");
    editChildDetails(childIdrecyclerviewPosition);
}




                                                        }else{

                                                            childPregnentForm();
                                                        }

                                                        }
                                                        else{
                                                            basicclickedForm();
                                                        }

                                                    }

                                                    if(status.equalsIgnoreCase("Pregnentandchild")) {


                                                        int errorCode = validateBasicForm();
//                                                        Log.d("ErrorCode",""+errorCode);
                                                        if (errorCode == 0) {

                                                            errorCode = pregentFormVAlidtion();

                                                            if (errorCode == 0) {
                                                                insertFamily(familyID, "EDIT");
                                                                insertMemberMaster(familyID, registerMemeberId, "EDIT");
                                                                inserWomenExtra(registerMemeberId, "EDIT");
                                                                insertPregnent(registerMemeberId, pregnentId);
//                                                                tabMovement();
                                                                errorCode = childformValidation();
                                                                if (errorCode == 0) {

//                                                                    insertFamily(familyID, "EDIT");
//
//                                                                    insertMemberMaster(familyID, registerMemeberId, "EDIT");
//                                                                    inserWomenExtra(registerMemeberId, "EDIT");


                                                                    if (childSavingMode.equalsIgnoreCase("new")) {
                                                                        insertChildDetails(registerMemeberId, familyID);
                                                                        promtForChild();
                                                                    }
                                                                    if (childSavingMode.equalsIgnoreCase("edit")) {
                                                                        Log.d("ChildEdit", "Inserted in edit part");
                                                                        editChildDetails(childIdrecyclerviewPosition);
                                                                    }


                                                                } else {

                                                                    childPregnentForm();
                                                                }
                                                            } else {
                                                                basicPregnentClickForm();
                                                            }
                                                        }

                                                    }}




//



            }
        });

    }

    public String createFamilyId(String awcCode){




        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);





        Cursor c = dbs.rawQuery("SELECT * FROM familydata where awc_code ='"+awcCode+"'" , null);


//        Log.d("query","SELECT *  FROM familydata where awc_code ="+awcCode );

        c.getCount();

        int count = c.getCount();

//        Log.d("countregister",""+ c.getCount());

      String familyCode= awcCode+String.format("%03d",count+1);

//      Log.d("family_code",familyCode);


return familyCode;

        }

        public String createMemberId(String familyID){


            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            Cursor c = dbs.rawQuery("SELECT * FROM memberbasic where family_id ='"+familyID+"'" , null);


            c.getCount();
            int count = c.getCount();
//               Log.d("countregister",""+ c.getCount());
            String memberId= familyID+String.format("%02d",count+1);

//            Log.d("member_id",memberId);

        return memberId;
        }

        public void insertFamily(String familyId,String mode){

            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            FamilyDetailGetSet family = new FamilyDetailGetSet(familyId,religionitemposi, castitem, rationcardItem, familyttypeItem, session.getDistCode(), session.getProjectCode(),session.getSectorCode(),session.getAwcCode(), session.getSurveyorId(),session.getVillageCode(),"N");
           if(mode.equals("ADD")) {
            long id = db.addFamilyData(family);
             }
        if(mode.equals("EDIT")){

    ContentValues contentValues = new ContentValues();
    contentValues.put("religion","1");
    contentValues.put("caste",castitem);
    contentValues.put("rcard",rationcardItem);
    contentValues.put("family_type",familyttypeItem);


            dbs.update("familydata",contentValues,"family_id="+familyId,null);



}


              SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

              Cursor c = swl.rawQuery("select * from familydata",null);

              int total = c.getCount();

              if(total>=1){

                  if(c.moveToFirst()){
                      do{

//                          Log.d("awcrecords",c.getString(c.getColumnIndex("awc_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("dist_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("familyid")));

                      }while (c.moveToNext());
                  }


              }



              if (c != null && c.moveToLast()) {
              long    lastId = c.getLong(0);

//                  Log.d("printValue",""+lastId);

                String valueIndex=  c.getString(c.getColumnIndex("awc_code"));

//                  Log.d("insedrtValue","inserted vAlue"+valueIndex);
              }




          }


        public String insertMemberMaster(String familyId,String memberId,String mode){

            String finalTime= timeHour.getText().toString() + ":" + timeMin.getText().toString() + ":" + timeSec.getText().toString();

            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
      MemberBasicGetSet memberbasic = new MemberBasicGetSet(memberId, familyId,nameBenificery.getText().toString(),beneficiaryHusband.getText().toString(), pctsId.getText().toString(), "", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()), new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()), dob.getText().toString(), personAge.getText().toString(), "N", "",aadharNumber.getText().toString(),aadharenrollment.getText().toString(), datee.getText().toString(),finalTime, bhamashahNumber.getText().toString(), phoneNumber.getText().toString(), String.valueOf(relationitem), "F", "N", "M", "", "", "", "", "Y",session.getSurveyorId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()), "M", "N", "", "");
            String inserted = "0";
      if(mode.equals("ADD")) {
          long id = db.addMemberBasic(memberbasic);
//          Log.d("insertMember", " " + id);

          if (id != -1) {
              inserted = "1";
          }
      }
      else{
          ContentValues contentValue = new ContentValues();
          contentValue.put("name",nameBenificery.getText().toString());
//          contentValue.put("dor",new SimpleDateFormat("yyy-MM-dd", Locale.getDefault()).format(new Date()));
          contentValue.put("doentry",new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault()).format(new Date()));
//          Log.d("updateValue",memberId);
//          Log.d("MemberID",nameBenificery.getText().toString());

          long uid=dbs.update("memberbasic",contentValue,"Members_id=?",new String[]{registerMemeberId});
          inserted = "1";

      }
                SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                Cursor c = swl.rawQuery("select * from memberbasic", null);
                int total = c.getCount();
                if (total >= 1) {

                    if (c.moveToFirst()) {
                        do {

//                            Log.d("MemberID", "NAME"+c.getString(c.getColumnIndex("name")));
//                            Log.d("MemberID", "DOR"+c.getString(c.getColumnIndex("dor")));
//                            Log.d("MemberID", "FAMILYid"+c.getString(c.getColumnIndex("familyy_id")));
//                            Log.d("MemberID", "MERMBERID"+c.getString(c.getColumnIndex("MemberId")));
//

                        } while (c.moveToNext());
                    }


                }


//

return  inserted;
        }

        public String inserWomenExtra(String memberID,String mode){
            SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
            WomenBasicGetSet womenExtra = new WomenBasicGetSet(memberID, String.valueOf(eductionItemPosition), fuelItemPosition, String.valueOf(decisionItemPosition), String.valueOf(doctorvisitItemPosition), accountype, nameaccountholder.getText().toString(), nameofbank.getText().toString(), branchname.getText().toString(),  bankaccountnumber.getText().toString(), ifsccode.getText().toString(), distancetobranch.getText().toString(), nameofpostoffice.getText().toString(), addressofpostoffice.getText().toString(), postofficepincode.getText().toString(),postofficeaccount.getText().toString(), hoemocode.getText().toString()," ","N"," ",distancenearestatm.getText().toString());
            String inserted = "0";
            if(mode.equals("ADD")) {
                long id = db.addWomenExtra(womenExtra);
//                Log.d("insertMember", " " + id);
                if (id != -1)
                {
                    inserted = "1";
                }
            }
            else{
                ContentValues contentValue = new ContentValues();
                contentValue.put("education",eductionItemPosition);
                contentValue.put("ac_holder_name",nameaccountholder.getText().toString());
                contentValue.put("cooking_fuel",fuelItemPosition);
                contentValue.put("decisionmaker_own_health",String.valueOf(decisionItemPosition));
                contentValue.put("decisionmaker_child_health",String.valueOf(doctorvisitItemPosition));
                contentValue.put("if_bank_account",accountype);
                 contentValue.put("ac_holder_name",nameaccountholder.getText().toString());
              contentValue.put( "bank_name",nameofbank.getText().toString());
                      contentValue.put("branch",branchname.getText().toString());
                contentValue.put("ac_no",bankaccountnumber.getText().toString());
                        contentValue.put("ifsc_code",ifsccode.getText().toString());
                contentValue.put("bank_distance",distancetobranch.getText().toString());
                contentValue.put("bank_atm_distance",distancenearestatm.getText().toString());
                contentValue.put("postoffice_name",nameofpostoffice.getText().toString());
                contentValue.put("postoffice_address",addressofpostoffice.getText().toString());
                contentValue.put("pin_code",postofficepincode.getText().toString());
                contentValue.put("post_office_ac",postofficeaccount.getText().toString());


                long uid=dbs.update("womenextra",contentValue,"Members_id=?",new String[]{registerMemeberId});

                inserted = "1";

            }


            SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            Cursor c = swl.rawQuery("select * from womenextra", null);
            int total = c.getCount();
            if (total >= 1) {

                if (c.moveToFirst()) {
                    do {

//                        Log.d("MemberID", "EDUCATION"+c.getString(c.getColumnIndex("education")));
//                        Log.d("MemberID", "ACCOUNTHOLDER"+c.getString(c.getColumnIndex("acholder_name")));
////
                    } while (c.moveToNext());
                }


            }


            return inserted;
        }


public int validateBasicForm(){

    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    fuelItemPosition="";

        int error =0;
if(nameBenificery.getText().toString().length()==0){

    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
    nameBenificery.requestFocus();
    imm.showSoftInput(nameBenificery, InputMethodManager.SHOW_IMPLICIT);
    error=1;

}

if(error==0 && phoneNumber.getText().toString().length()!=10){

    basicclickedForm();
    phoneNumber.requestFocus();
    imm.showSoftInput(phoneNumber, InputMethodManager.SHOW_IMPLICIT);
    Toast.makeText(this, "Enter valid Phone Number", Toast.LENGTH_SHORT).show();
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

        if(error==0 && datee.getText().length()==0){

            Toast.makeText(this, "Select Adhar Time Stamp ", Toast.LENGTH_SHORT).show();
            error=1;
        }
        if(error==0 && (timeHour.getText().length()==0 ||Integer.parseInt(timeHour.getText().toString())>24 )){
            Toast.makeText(this, "Enter Correct Hour ", Toast.LENGTH_SHORT).show();
            error=1;
        }

        if(error==0  &&(timeMin.getText().length()==0 || Integer.parseInt(timeMin.getText().toString())>60)){
            Toast.makeText(this, "Enter Correct Minute ", Toast.LENGTH_SHORT).show();
            error=1;
        }

        if(error==0  &&(timeSec.getText().length()==0 || Integer.parseInt(timeSec.getText().toString())>60)){

            Toast.makeText(this, "Enter Correct Sec", Toast.LENGTH_SHORT).show();
            error=1;

        }
//
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

    if(error==0 && Integer.parseInt(personAge.getText().toString())< 12){
               Toast.makeText(this, "Age must not be less then 12", Toast.LENGTH_SHORT).show();
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
//    if(error==0 && fuel.equalsIgnoreCase("--Select Options--")){
//        fuelSelectionSpinner.requestFocus();
//        Toast.makeText(this, "Select Fuel", Toast.LENGTH_SHORT).show();
//        error=1;
//    }

    if (f1.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(1);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(1);
        }
        Log.d("MYActivity", fuelItemPosition);
    }
    if (f2.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(2);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(2);
        }
        Log.d("MYActivity", fuelItemPosition);
    } if (f3.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(3);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(3);
        }
        Log.d("MYActivity", fuelItemPosition);
    } if (f4.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(4);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(4);
        }
        Log.d("MYActivity", fuelItemPosition);
    } if (f5.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(5);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(5);
        }
        Log.d("MYActivity", fuelItemPosition);
    } if (f6.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(6);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(6);
        }
        Log.d("MYActivity", fuelItemPosition);
    } if (f7.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(7);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(7);
        }
        Log.d("MYActivity", fuelItemPosition);
    } if (f8.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(8);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(8);
        }
//        Log.d("MYActivity", fuelItemPosition);
    }

    if (f9.isChecked()) {
        if (fuelItemPosition.length() == 0) {
            fuelItemPosition = String.valueOf(8);
        } else {
            fuelItemPosition = fuelItemPosition + "," + String.valueOf(8);
        }
//        Log.d("MYActivity", fuelItemPosition);
    }

//    Log.d("MYActivity", fuelItemPosition);
    if (error==0 && fuelItemPosition.equalsIgnoreCase("")) {
        error = 1;
        Toast.makeText(this, "Select the Cooking  Fuel", Toast.LENGTH_SHORT).show();
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

    if(error==0 && accountype.equalsIgnoreCase("")){
        Toast.makeText(this, "Select Bank Account", Toast.LENGTH_SHORT).show();

        error=1;
    }


    if( accountype.equalsIgnoreCase("B")){



        if(error == 0 && nameaccountholder.getText().toString().length()==0 ){
            nameaccountholder.requestFocus();
            imm.showSoftInput(nameaccountholder, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Account Holder Name", Toast.LENGTH_SHORT).show();
            error =1;
        }

        if(error==0 && !confirmnameaccountholder.getText().toString().equalsIgnoreCase(nameaccountholder.getText().toString())){
            confirmnameaccountholder.requestFocus();
            imm.showSoftInput(confirmnameaccountholder, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Confirm Bank Account Holder Name", Toast.LENGTH_SHORT).show();
            error =1;
        }
        if(error == 0 && nameofbank.getText().toString().length()==0 ){
            nameofbank.requestFocus();
            imm.showSoftInput(nameofbank, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Bank Name", Toast.LENGTH_SHORT).show();
            error =1;
        }

        if(error == 0 && branchname.getText().toString().length()==0 ){
            branchname.requestFocus();
            imm.showSoftInput(branchname, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Branch Name", Toast.LENGTH_SHORT).show();
            error =1;
        }

        if((error == 0 && bankaccountnumber.getText().toString().length()==0 ) ){
            bankaccountnumber.requestFocus();
            imm.showSoftInput(bankaccountnumber, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Bank Account Number", Toast.LENGTH_SHORT).show();
            error =1;
        }

        if(error == 0 &&!confirmbankaccountnumber.getText().toString().equalsIgnoreCase(bankaccountnumber.getText().toString())){
            confirmbankaccountnumber.requestFocus();
            imm.showSoftInput(confirmbankaccountnumber, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Correct  Account Number", Toast.LENGTH_SHORT).show();
            error =1;
        }
        if((error == 0 && ifsccode.getText().toString().length()==0) ){
            ifsccode.requestFocus();
            imm.showSoftInput(ifsccode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Bank IFSC Code", Toast.LENGTH_SHORT).show();
            error =1;
        }

        if(error == 0 && !confirmifsccode.getText().toString().equalsIgnoreCase(ifsccode.getText().toString())){

            confirmifsccode.requestFocus();
            imm.showSoftInput(ifsccode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Confirm Bank IFSC Code", Toast.LENGTH_SHORT).show();
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
        }

        if(error == 0 && !confirmpostofficeAccount.getText().toString().equalsIgnoreCase(postofficeaccount.getText().toString()) ){
            confirmpostofficeAccount.requestFocus();
        imm.showSoftInput(confirmpostofficeAccount, InputMethodManager.SHOW_IMPLICIT);
        Toast.makeText(this, "Enter Post Office Account Number", Toast.LENGTH_SHORT).show();
        error =1;
    }

        if(error == 0 && postofficepincode.getText().toString().length()==0 ){
            postofficepincode.requestFocus();
            imm.showSoftInput(postofficepincode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Post Office Pincode", Toast.LENGTH_SHORT).show();
            error =1;
        }


        if(error == 0 && !confirmpostpincode.getText().toString().equalsIgnoreCase(postofficepincode.getText().toString()) ){
            confirmpostofficeAccount.requestFocus();
            imm.showSoftInput(confirmpostofficeAccount, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Post Office Account Number", Toast.LENGTH_SHORT).show();
            error =1;
        }


        if(error == 0 && hoemocode.getText().toString().length()==0 ){
            hoemocode.requestFocus();
            imm.showSoftInput(hoemocode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Hoemo Code", Toast.LENGTH_SHORT).show();
            error =1;
        }if(error == 0 && confirmpostpincode.getText().toString().length()==0 ){
            confirmpostpincode.requestFocus();
            imm.showSoftInput(confirmpostpincode, InputMethodManager.SHOW_IMPLICIT);
            Toast.makeText(this, "Enter Confirm pincode", Toast.LENGTH_SHORT).show();
            error =1;
        }
    }

    if(error == 0 && distancetobranch.getText().toString().length()==0 ){
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

return error;


}

public int childformValidation(){
     int error=0;
    if(error==0 && nameOfChild.getText().toString().length()==0){
        error=1;
        Toast.makeText(this, "Enter Child Name", Toast.LENGTH_SHORT).show();
    }
    if (error == 0 && dateOfDelivery.getText().toString().length() == 0) {

        error =1;
        Toast.makeText(this, "Enter Date of Delivery", Toast.LENGTH_SHORT).show();
    }

     if(error==0 && DeliveryPlace.equalsIgnoreCase("--Select Options--")){

         error=1;
         Toast.makeText(this, "Enter Delivery Place", Toast.LENGTH_SHORT).show();
     }

    if(error==0 && orderOfBirth.getText().toString().length()==0){

        error=1;
        Toast.makeText(this, "Enter Order of Birth", Toast.LENGTH_SHORT).show();
    }

    if(error==0 && childSex.equalsIgnoreCase("--Select Options--")){

        error=1;
        Toast.makeText(this, "Enter sex of child ", Toast.LENGTH_SHORT).show();
    }

    if(error==0 && childWeight.getText().toString().length()==0 && childWeightGram.getText().toString().length()==0){

        error=1;
        Toast.makeText(this, "Enter Child Weight", Toast.LENGTH_SHORT).show();
    }
    if(error==0 && wasChildBornString.equalsIgnoreCase("--Select Options--")){

        error =1;
        Toast.makeText(this, "Select Full Term", Toast.LENGTH_SHORT).show();
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

        if(ageInt<0){
                   ageS = "0";
                  }else {
                    ageS = ageInt.toString();
                 }
//        String ageS = ageInt.toString();
        personAge.setText(ageS);
        personAge.setEnabled(false);
//        Log.d("age",ageS);

        return ageS;
    }

public String insertPregnent(String memberId,String pregnentID){
   String teppregnemtIDs="";
    String inserted="";
    if(pregnentID==null){
//        Log.d("CheckNull","inserted ");

        pregnentId= generatePregnentID(memberId);
//        Log.d("CheckNull",pregnentId);
    }


    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

    Cursor c = dbs.rawQuery("SELECT * FROM  pregnant where pregnancy_id ='"+pregnentId+"'" , null);
//    Log.d("MANASQUERY","SELECT * FROM  pregnant where pregnancy_id ="+pregnentId);

//            Log.d("query","SELECT *  FROM familydata where awc_code ="+awcCode );
    c.getCount();
    int counted = c.getCount();
//    Log.d("CountPregnent",""+counted);
//    Log.d("CountPregnent",""+pregnentId);

    if(counted==0){
Log.d("checkInsretd","Pregrncy Insreted");
        PregnantGetSet pregnent = new PregnantGetSet(pregnentId, memberId, Integer.valueOf(pregnetNumnber.getText().toString()), lmpdate.getText().toString(),  session.getSurveyorId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()),"M","Y","","N","");
       long id= db.addPregnant(pregnent);
       Log.d("CountPregnent","sucieesrate"+id);

        ContentValues contentUpdate = new ContentValues();
        contentUpdate.put("stage","PW");
        contentUpdate.put("status","PW");
        long uid=dbs.update("memberbasic",contentUpdate,"Members_id=?",new String[]{registerMemeberId});

        Log.d("CountPregnent","sucieesrate"+uid);

        inserted = "1";
    }
    else{



        ContentValues contentValues = new ContentValues();
        contentValues.put("lmp_date",lmpdate.getText().toString());
        contentValues.put("order_of_pregnancy",Integer.valueOf(pregnetNumnber.getText().toString()));
        contentValues.put("IS_EDITED","Y");
        contentValues.put("TIME_STAMP",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));


        dbs.update("pregnant",contentValues,"pregnancy_id=?",new String[]{pregnentID});
//        Log.d("checkInsretd","Pregrncy updated ");
        inserted = "1";

    }




    SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

//    Cursor cr = swl.rawQuery("select * from pregnant", null);
    Cursor cr = swl.rawQuery("select a.Members_id  FROM memberbasic a  left join memberbasic b on a.members_id=b.mother_id  left join (select * from pregnant where IS_ACTIVE='Y')  p on a.members_id=p.MEMBERS_ID  left join childextra c on b.MEMBERS_ID=c.MEMBERS_ID where a.is_to_track='Y' and a.stage <>'' order by a.STAGE,a.NAME", null);
    int total = cr.getCount();
    if (total >= 1) {

        int i=0;
        if (cr.moveToFirst()) {
            do {


                Log.d("MemberID", "orderofPRegncy"+ cr.getString(cr.getColumnIndex("Members_id")));
//                Log.d("MemberID", "lmp"+ cr.getString(cr.getColumnIndex("lmp_date")));
//                Log.d("MemberID", "timestamp"+cr.getString(cr.getColumnIndex("timee_stamp")));
//                Log.d("MemberID", "timestamp"+cr.getString(cr.getColumnIndex("pregnancy_id")));
//

                i++;
            } while (cr.moveToNext());
        }

//        Log.d("recordCountPW",""+i);
    }




return inserted;
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

    @Override
    public void onBackPressed() {

        dialogCoupon    = new Dialog(RegistrationWomen.this);
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

//

    public void modeSetting(String mode,String member){


//    Log.d("memberVAlue",member+ " "+mode+" "+ activatedREgistrationForm);
    if(mode.equals("checkedChild")){
        preg.setVisibility(View.GONE);
        beneficairyTest.setText(R.string.nameofbeneficiary);
//        dateofbirthMother.setText(R.string.dateofbirthbeneficary);
         if(member==null) {

             Log.d("activatedForm","Enterered"+"");
             chi.setEnabled(false);
            child.setEnabled(false);
            basicclickedForm();
//            activatedREgistrationForm=1;
         }else{
         child.setEnabled(true);
             chi.setEnabled(true);
         if(activatedREgistrationForm==3){
//             activatedREgistrationForm=3;
             childSavingMode="new";
             childPregnentForm();

         }

            }
//

    }

    if(mode.equals("checkedPregnent")){

        chi.setVisibility(View.GONE);
       if(member==null){


        basicPregment.setEnabled(false);
//       activatedREgistrationForm=1;
           basicclickedForm();
       }
        else{
    basicPregment.setEnabled(true);
           if(activatedREgistrationForm==2){
//               activatedREgistrationForm=2;
               basicPregnentClickForm();
           }
        }
//
    }

    if(mode.equals("Pregnentandchild")){

        if(member==null) {

            basicPregment.setEnabled(false);
            child.setEnabled(false);
//            activatedREgistrationForm=1;

            basicclickedForm();
        }else{
            basicPregment.setEnabled(true);
            child.setEnabled(true);


            if(activatedREgistrationForm==1){
                basicclickedForm();
//                activatedREgistrationForm=2;
            }
            if(activatedREgistrationForm==2){
//                activatedREgistrationForm=3;
                basicPregnentClickForm();
//                childPregnentForm();
            }
            if(activatedREgistrationForm==3){
                childPregnentForm();
            }

        }
//


    }

    }

    public void basicclickedForm(){

//        Log.d("Validation","inserted inside fucntion");
        activatedREgistrationForm =1;
        fragment_young_mother_basic_details.setVisibility(View.VISIBLE);
        fragment_pregnant_lady_detail.setVisibility(View.GONE);
        childDetails.setVisibility(View.GONE);

        basicclick.setVisibility(View.VISIBLE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.GONE);
        getSupportActionBar().setTitle("BASIC");

    }

    public void basicPregnentClickForm(){

//        activatedREgistrationForm=2;
        fragment_young_mother_basic_details.setVisibility(View.GONE);
        fragment_pregnant_lady_detail.setVisibility(View.VISIBLE);
        childDetails.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.VISIBLE);
        childclick.setVisibility(View.GONE);
        getSupportActionBar().setTitle("PREGNANT");


    }

    public void childPregnentForm(){
        childSavingMode="new";

//        activatedREgistrationForm=3;
        childDetails.setVisibility(View.VISIBLE);
        fragment_young_mother_basic_details.setVisibility(View.GONE);
        fragment_pregnant_lady_detail.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.VISIBLE);
        getSupportActionBar().setTitle("CHILD");

    }


    public int  pregentFormVAlidtion(){

        int error =0;
        if(pregnetNumnber.getText().length()==0)
        {
            error =1;
//              pregnetNumnber.setError("");
                pregnetNumnber.requestFocus();
                 Toast.makeText(RegistrationWomen.this, "Enter Number of child", Toast.LENGTH_SHORT).show();
        }


           if(error==0&&lmpdate.getText().toString().length()==0){
             error =1;
               lmpdate.requestFocus();
                  Toast.makeText(RegistrationWomen.this, "Please Select Date", Toast.LENGTH_SHORT).show();
            }

return error;

    }

    public void tabMovement(){
        if( status.equalsIgnoreCase("checkedChild")){

            if(activatedREgistrationForm==1 ) {
                activatedREgistrationForm = 3;
            }else{
                Toast.makeText(this, " Successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                finish();
            }

        }
        if( status.equalsIgnoreCase("checkedPregnent")){

            if(activatedREgistrationForm==1 ){
                activatedREgistrationForm=2;
            }
            else{

                Toast.makeText(this, " Successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                finish();
            }

        }

        if( status.equalsIgnoreCase("Pregnentandchild")) {
            if (activatedREgistrationForm == 1) {
                activatedREgistrationForm = 2;
            }else if(activatedREgistrationForm == 2){
                activatedREgistrationForm=3;

            }else{
                Toast.makeText(this, " Successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                finish();

            }

        }
        modeSetting(status,registerMemeberId);
    }


    public void insertChildDetails(String motherId, String familyID)
    {

        String childID=createMemberId(familyID);
        String cSex;
        if (childSexItemSelected==1) {

            cSex="M";
        }else if(childSexItemSelected==2){
            cSex="F";
        }else{
            cSex="I";
        }





        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
        MemberBasicGetSet memberbasic = new MemberBasicGetSet(childID, familyID,nameOfChild.getText().toString(),beneficiaryHusband.getText().toString(),pctsId.getText().toString(), new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()), new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()), "", dateOfDelivery.getText().toString(), "", "", "","" ,"", "","", "", " ", "", cSex, "", "", motherId,"" , null, null, "Y", session.getSurveyorId(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()), "M", "N", "", "");

       db.addMemberBasic(memberbasic);
try {
    Cursor idhj=dbs.rawQuery("update memberbasic set stage=case when " +
                    "CAST(strftime('%s',date('" + dateOfDelivery.getText().toString() + "','start of month','+12 month'," +
                    "cast(strftime('%d','" + dateOfDelivery.getText().toString() + "')as text) || ' DAY')) AS integer) > " +
                    "CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY' END,status=case when " +
                    "CAST(strftime('%s',date('" + dateOfDelivery.getText().toString() + "','start of month','+12 month'," +
                    "cast(strftime('%d','" + dateOfDelivery.getText().toString() + "')as text) || ' DAY')) AS integer) > " +
                    "CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY' END WHERE MEMBERS_ID='" + childID + "'",
            null);


//    Cursor idhj=dbs.rawQuery("update memberbasic set stage=case when " +
//                    "CAST(strftime('%s',date(" + dateOfDelivery.getText().toString() + ",'start of month','+12 month'," +
//                    "cast(strftime('%d'," + dateOfDelivery.getText().toString() + ")as text) || ' DAY')) AS integer) > " +
//                    "CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY' END,status=case when " +
//                    "CAST(strftime('%s',date(" + dateOfDelivery.getText().toString() + ",'start of month','+12 month'," +
//                    "cast(strftime('%d'," + dateOfDelivery.getText().toString() + ")as text) || ' DAY')) AS integer) > " +
//                    "CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY' END WHERE MEMBERS_ID='" + childID + "'",
//            null);
idhj.moveToFirst();
idhj.close();

    Log.d("printUpdateVAlue","update memberbasic set stage=case when CAST(strftime('%s',date(" + dateOfDelivery.getText().toString() + ",'start of month','+12 month',cast(strftime('%d'," + dateOfDelivery.getText().toString() + ")as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY' END WHERE MEMBERS_ID='" + childID + "'");
}catch(Exception e){
    Log.d("Raw query","inserted");
}


        ChildExtraGetSet childextra = new   ChildExtraGetSet(childID, dateOfDelivery.getText().toString(), String.valueOf(deliveryPlaceItemSelected), orderOfBirth.getText().toString(),ChildWeightFinal, String.valueOf(wasChildBornPosition), "", "","", "", "", "","","", "","N","");
         db.addChildExtra(childextra);

    }

    public void editChildDetails(String childSelectedId){
        Log.d("ChildEdit","Inserted in edit part database");
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);
        String cSex;
        if (childSexItemSelected==1) {

            cSex="M";
        }else if(childSexItemSelected==2){
            cSex="F";
        }else{
            cSex="I";
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("sex",cSex);
        contentValues.put("name",nameOfChild.getText().toString());

       long jk= dbs.update("memberbasic",contentValues,"Members_id='"+childSelectedId+"'",null);
//Log.d("Updated VAlue",ig+"");

        if(jk==1) {

            ContentValues contentValuesChild = new ContentValues();

            contentValuesChild.put("delivery_place", String.valueOf(deliveryPlaceItemSelected));
            contentValuesChild.put("dodelivery", dateOfDelivery.getText().toString());
            contentValuesChild.put("child_order", orderOfBirth.getText().toString());
            contentValuesChild.put("birth_wt", ChildWeightFinal);
            contentValuesChild.put("full_term", String.valueOf(wasChildBornPosition));
            jk = dbs.update("childextra", contentValuesChild, "Members_id='" + childSelectedId + "'", null);
        }
if(jk==1){
    Toast.makeText(this, "Updated Successfully ", Toast.LENGTH_SHORT).show();
}

        Log.d("Updated VAlue",jk+" ");
    }

    public String calculateStage( Date mindate,Date maxDate,Date compareDate){
if(compareDate.after(mindate) && compareDate.before(maxDate)){
    return "LM";
}
else{
    return "MY";
}

    }


    public void getChildNumbers(String motherID) {

        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery("SELECT Members_id FROM memberbasic where mother_id ='" + motherID + "'", null);
        int total = c.getCount();
        if (total >= 1) {

            int i = 0;
            if (c.moveToFirst()) {
                do {

                    arrayNoOfChild.add(c.getString(c.getColumnIndex("Members_id")));

                    Log.d("NumberOFChild", "orderofPRegncy" + c.getString(c.getColumnIndex("Members_id")));

//


                } while (c.moveToNext());
            }



        }

         numberAdpetr = new NumberChildAdapter(arrayNoOfChild);

        LinearLayoutManager layoutManager
         = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewChild.setLayoutManager(layoutManager);
        recyclerViewChild.setAdapter(numberAdpetr);



    }


    public void promtForChild(){

        dialogCoupon    = new Dialog(RegistrationWomen.this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.promtchildlayout);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    


        Button yes = (Button)dialogCoupon.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();


//                activatedREgistrationForm=1;

                if (arrayNoOfChild != null) {
                    arrayNoOfChild.clear();


                }
                getChildNumbers(registerMemeberId);
                numberAdpetr.notifyDataSetChanged();
                clearChildForm();
                dialogCoupon.hide();



            }
        });
        Button no = (Button)dialogCoupon.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arrayNoOfChild != null) {
                    arrayNoOfChild.clear();


                }
                getChildNumbers(registerMemeberId);
                numberAdpetr.notifyDataSetChanged();
                clearChildForm();

                tabMovement();
                dialogCoupon.hide();
            }
        });

        dialogCoupon.show();

    }
public void clearChildForm(){
    dateOfDelivery.setText("");
    nameOfChild.setText("");
    orderOfBirth.setText("");
    childWeight.setText("");
    childWeight.setText(" ");
    placeofDelivery.setSelection(0);
            sexOfchild.setSelection(0);
            wasChildBorn.setSelection(0);
//            birthBreast.setSelection(0);
//            first;yellowFeed.setSelection(0);
    childSavingMode="new";
    nameOfChild.requestFocus();
//    dateOfDelivery.requestFocus();


}

public void getChildDetails(String childidfromDatabase) {

    childSavingMode = "edit";

    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

    Cursor c = dbs.rawQuery("select b.members_id, b.name,b.sex,c.dodelivery,c.delivery_place,c.child_order,c.birth_wt,c.full_term  from memberbasic b left join childextra c on b.Members_id=c.Members_id where b.Members_id='" + childidfromDatabase + "'", null);
    int total = c.getCount();
    if (total >= 1) {

//        int i = 0;
        if (c.moveToFirst()) {
            do {
//             arrayNoOfChild.add(c.getString(c.getColumnIndex("Members_id")));
                Log.d("NumberOFChild", "orderofPRegncy" + c.getString(c.getColumnIndex("Members_id")));

                dateOfDelivery.setText(c.getString(c.getColumnIndex("dodelivery")));
                nameOfChild.setText(c.getString(c.getColumnIndex("name")));
                orderOfBirth.setText(c.getString(c.getColumnIndex("child_order")));


                        String childWeightValue = c.getString(c.getColumnIndex("birth_wt"));

                        if(childWeightValue.contains(".")){
                double amount = Double.parseDouble(childWeightValue);
                NumberFormat numberFormat = new DecimalFormat("##.###");
           String     str = numberFormat.format(amount);
//                if(childWeightValue.contains(".")){

                Log.d("NumberFormat",str);
                            String[] separated = str.split("\\.");
                           String weight= separated[0]; // this will contain "Fruit"
                            String gram = separated[1];

                            childWeight.setText(weight);
                            childWeightGram.setText(gram);
                        }
                        else{
                            childWeight.setText(childWeightValue);
//                            childWeightGram.setText("000");
                        }


                Log.d("spinnerSelection", "" + c.getString(c.getColumnIndex("delivery_place")));
                Log.d("spinnerSelection", "" + c.getColumnIndex("delivery_place"));
                placeofDelivery.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("delivery_place"))));
                if (c.getString(c.getColumnIndex("sex")).equalsIgnoreCase("M")) {
                    sexOfchild.setSelection(1);
                }
                if (c.getString(c.getColumnIndex("sex")).equalsIgnoreCase("F")) {
                    sexOfchild.setSelection(2);
                }
                if (c.getString(c.getColumnIndex("sex")).equalsIgnoreCase("I")) {
                    sexOfchild.setSelection(3);
                }
//                sexOfchild.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("sex"))));
                wasChildBorn.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("full_term"))));
//                birthBreast.setSelection(0);
//                firstyellowFeed.setSelection(0);
//                childWeight.setText(c.getString(c.getColumnIndex("birth_wt")));
//
            } while (c.moveToNext());
        }


    }


}

    public void edittextValidate(){
//    nameaccountholder,bankaccountnumber, ifsccode, confirmnameaccountholder,confirmbankaccountnumber, confirmifsccode

        nameaccountholder.setCustomSelectionActionModeCallback(new Callback() {


            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });

        confirmnameaccountholder.setCustomSelectionActionModeCallback(new Callback() {


            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });

        bankaccountnumber.setCustomSelectionActionModeCallback(new Callback() {


            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });

        confirmbankaccountnumber.setCustomSelectionActionModeCallback(new Callback() {


            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });

        ifsccode.setCustomSelectionActionModeCallback(new Callback() {


            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });

        confirmifsccode.setCustomSelectionActionModeCallback(new Callback() {


            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



