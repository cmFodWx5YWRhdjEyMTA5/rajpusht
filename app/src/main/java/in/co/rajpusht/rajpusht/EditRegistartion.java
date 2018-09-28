package in.co.rajpusht.rajpusht;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

import extras.DbHelper;
import extras.SessionManager;

public class EditRegistartion extends AppCompatActivity {

    View fragment_pregnant_lady_detail,fragment_young_mother_basic_details,childDetails;

    DbHelper db;
    EditText nameBenificery,phoneNumber,usidNumber,nameaccountholder, bhamashahNumber,aadharNumber,aadharenrollment,personAge, nameofbank, branchname,
            bankaccountnumber, ifsccode, distancetobranch, distancenearestatm, nameofpostoffice, addressofpostoffice, postofficeaccount, postofficepincode,
            confirmnameaccountholder,confirmbankaccountnumber, confirmifsccode, hoemocode,confirmpostofficeAccount,confirmpostpincode;

    TextView datee, time, lmpdate, dob;
    RadioGroup bankgroup;
    Spinner personaReligion,castSpinner,rationcardColorSpinner,relationshipHeadSpinner,typeFamilySpinner,educationComplted,fuelSelectionSpinner,
            decsionSpinner,decsionVisitDoctorSpinner;
    String profileQuery="";

    CheckBox f1,f2,f3,f4,f5,f6,f7,f8,f9;
    TextView beneficairyTest,dateofbirthMother;
    LinearLayout linearCyrrentlyUsing;

    EditText timeHour,timeMin,timeSec;
    String educationItem, accountype = "", reli, cst, rati, rela, fami, fuel, deci, visi,ageS;
    private int mYear, mMonth, mDay, mHour, mMinute,mSec, mYear1, mMonth1, mDay1, mYear2, mMonth2, mDay2;

    int castitem,relationitem,rationcardItem,familyttypeItem,eductionItemPosition, religionitemposi, decisionItemPosition, doctorvisitItemPosition;
    LinearLayout noadhaar, banklayout, postofficeaccountdetail;
    RelativeLayout bas, preg, chi;

    String status;
    View basicclick, pregnentclick, childclick;

    EditText nameOfChild,orderOfBirth,childWeight,childWeightGram;
    TextView dateOfDelivery,addchild;
    EditText pregnetNumnber;
    TextView basic,basicPregment,child;
    Spinner placeofDelivery,sexOfchild,wasChildBorn,birthBreast,firstyellowFeed;

    String DeliveryPlace,childSex,wasChildBornString;
    int deliveryPlaceItemSelected,childSexItemSelected,wasChildBornPosition;

    CheckBox adhaaravailable;

    String ChildWeightFinal="";
    String fuelItemPosition="";

    EditText beneficiaryHusband, pctsId;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registartion);
        getSupportActionBar().setTitle("BASIC");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());


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
                DatePickerDialog dpd = new DatePickerDialog(EditRegistartion.this,
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
                TimePickerDialog tpd = new TimePickerDialog(EditRegistartion.this,
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
                DatePickerDialog dpd = new DatePickerDialog(EditRegistartion.this,
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
                calender.add(Calendar.DATE,-281);
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
                DatePickerDialog dpd = new DatePickerDialog(EditRegistartion.this,
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
        confirmbankaccountnumber = (EditText) findViewById(R.id.confirmbankaccountnumber);
        confirmifsccode = (EditText) findViewById(R.id.confirmifsccode);

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



        dateOfDelivery= (TextView) findViewById(R.id.dateOfDelivery);
        nameOfChild = (EditText) findViewById(R.id.nameOfChild);
        orderOfBirth= (EditText) findViewById(R.id.orderOfBirth);
        childWeight = (EditText) findViewById(R.id.childWeight);
        childWeightGram = (EditText) findViewById(R.id.childWeightGram);
        addchild = (TextView) findViewById(R.id.addchild);
        addchild.setVisibility(View.GONE);

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
                DatePickerDialog dpd = new DatePickerDialog(EditRegistartion.this,
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



//        modeSetting(status,registerMemeberId);



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
//                deliveryPlaceItemSelected=position;

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

               int errorCode=validateBasicForm();
               if(errorCode==0){
                   if(getIntent().getStringExtra("stage").equalsIgnoreCase("PW")){
                       errorCode =  pregentFormVAlidtion();
                       if(errorCode==0){


                           String finalTime= timeHour.getText().toString() + ":" + timeMin.getText().toString() + ":" + timeSec.getText().toString();
                           String familyUpdate="UPDATE familydata set " +

                                   "      RELIGION = '"+ religionitemposi +"'," +

                                   "       CASTE = '"+castitem+"'," +

                                   "       RCARD = '"+rationcardItem+"'," +

                                   "       FAMILY_TYPE = '"+familyttypeItem+"'," +

                                   "       SURVEYOR_ID = '"+session.getSurveyorId()+"'," +

                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) " +

                                   " WHERE FAMILY_ID = '"+getIntent().getStringExtra("familyId")+"' ";

                           executeQuery(familyUpdate);

                           String memberbasicupdate="UPDATE memberbasic" +

                                   "   SET " +

                                   "     NAME = '"+nameBenificery.getText().toString()+"'," +

                                   "     HUSBAND = '"+beneficiaryHusband.getText().toString()+"'," +

                                   "     PCTSID = '"+pctsId.getText().toString()+"'," +

                                   "    DOB = '"+dob.getText().toString()+"'," +

                                   "       AGE = '"+personAge.getText().toString()+"'," +

                                   "       IF_DOB_ASSUMED = 'N'," +

                                   "       AADDHAR = '"+aadharNumber.getText().toString()+"'," +

                                   "       AADDHAR_ENROL_NO = '"+aadharenrollment.getText().toString()+"'," +

                                   "       AADDHAR_DATE_STAMP = '"+datee.getText().toString()+"'," +

                                   "       AADDHAR_TIME_STAMP = '"+finalTime+"'," +

                                   "       BHAMASHA = '"+bhamashahNumber.getText().toString()+"'," +

                                   "       MOBILE = '"+phoneNumber.getText().toString()+"'," +

                                   "       RELATION = '"+String.valueOf(relationitem)+"', " +

                                   "       SURVEYOR_ID = '"+session.getSurveyorId()+"'," +

                                   "       TIME_STAMP = '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"'," +

                                   "       SOURCE = 'M', " +

                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) " +

                                   " WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"' ";

                           executeQuery(memberbasicupdate);


                           String women_extra="UPDATE womenextra" +
                                   "" +
                                   "   SET " +
                                   "" +
                                   "       EDUCATION = '"+String.valueOf(eductionItemPosition)+"'," +
                                   "" +
                                   "       COOKING_FUEL = '"+fuelItemPosition+"'," +
                                   "" +
                                   "       DECISIONMAKER_OWN_HEALTH = '"+String.valueOf(decisionItemPosition)+"'," +
                                   "" +
                                   "       DECISIONMAKER_CHILD_HEALTH = '"+String.valueOf(doctorvisitItemPosition)+"'," +
                                   "" +
                                   "       IF_BANK_ACCOUNT = '"+accountype+"'," +
                                   "" +
                                   "       AC_HOLDER_NAME = '"+nameaccountholder.getText().toString()+"'," +
                                   "" +
                                   "       BANK_NAME = '"+nameofbank.getText().toString()+"'," +
                                   "" +
                                   "       BRANCH = '"+branchname.getText().toString()+"'," +
                                   "" +
                                   "       AC_NO = '"+bankaccountnumber.getText().toString()+"'," +
                                   "" +
                                   "       IFSC_CODE = '"+ifsccode.getText().toString()+"'," +
                                   "" +
                                   "       BANK_DISTANCE = '"+distancetobranch.getText().toString()+"'," +
                                   "" +
                                   "       BANK_ATM_DISTANCE = '"+distancenearestatm.getText().toString()+"'," +

                                   "       POSTOFFICE_NAME = '"+nameofpostoffice.getText().toString()+"'," +
                                   "" +
                                   "       POSTOFFICE_ADDRESS = '"+addressofpostoffice.getText().toString()+"'," +
                                   "" +
                                   "       PIN_CODE = '"+postofficepincode.getText().toString()+"'," +
                                   "" +
                                   "       POST_OFFICE_AC = '"+postofficeaccount.getText().toString()+"'," +

                                   "" +
                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end)" +
                                   "" +
                                   " WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'" ;

                           executeQuery(women_extra);

                           String updatePregnent="UPDATE pregnant" +
                                   "" +
                                   "   SET " +
                                   "" +
                                   "       ORDER_OF_PREGNANCY = '"+Integer.valueOf(pregnetNumnber.getText().toString())+"'," +
                                   "" +
                                   "       LMP_DATE = '"+lmpdate.getText().toString()+"',       " +
                                   "" +
                                   "       SURVEYOR_ID = '"+session.getSurveyorId()+"'," +
                                   "" +
                                   "       TIME_STAMP = '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"'," +
                                   "" +
                                   "       SOURCE = 'M'," +
                                   "" +


                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) " +
                                   "" +
                                   " WHERE PREGNANCY_ID = '"+getIntent().getStringExtra("pregaencyId")+"' AND " +
                                   "" +
                                   "       MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'";

                           executeQuery(updatePregnent);
                           Toast.makeText(EditRegistartion.this, "Record Updated Succesfully", Toast.LENGTH_SHORT).show();
                           onBackPressed();



                       }
                   }


                   else{
                       errorCode =  childformValidation();
                       if(errorCode==0) {

                           String finalTime= timeHour.getText().toString() + ":" + timeMin.getText().toString() + ":" + timeSec.getText().toString();

                           String familyUpdate="UPDATE familydata set " +

                                   "      RELIGION = '"+ religionitemposi +"'," +

                                   "       CASTE = '"+castitem+"'," +

                                   "       RCARD = '"+rationcardItem+"'," +

                                   "       FAMILY_TYPE = '"+familyttypeItem+"'," +

                                   "       SURVEYOR_ID = '"+session.getSurveyorId()+"'," +

                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) " +

                                   " WHERE FAMILY_ID = '"+getIntent().getStringExtra("familyId")+"' ";

                           executeQuery(familyUpdate);

                           String memberbasicupdate="UPDATE MEMBERBASIC" +



                                   "   SET  NAME = '"+nameBenificery.getText().toString()+"'," +

                                   "     HUSBAND = '"+beneficiaryHusband.getText().toString()+"'," +

                                   "     PCTSID = '"+pctsId.getText().toString()+"'," +

                                   "    DOB = '"+dob.getText().toString()+"'," +

                                   "       AGE = '"+personAge.getText().toString()+"'," +

                                   "       IF_DOB_ASSUMED = 'N'," +

                                   "       AADDHAR = '"+aadharNumber.getText().toString()+"'," +

                                   "       AADDHAR_ENROL_NO = '"+aadharenrollment.getText().toString()+"'," +

                                   "       AADDHAR_DATE_STAMP = '"+datee.getText().toString()+"'," +

                                   "       AADDHAR_TIME_STAMP = '"+finalTime+"'," +

                                   "       BHAMASHA = '"+bhamashahNumber.getText().toString()+"'," +

                                   "       MOBILE = '"+phoneNumber.getText().toString()+"'," +

                                   "       RELATION = '"+String.valueOf(relationitem)+"', " +

                                   "       SURVEYOR_ID = '"+session.getSurveyorId()+"'," +

                                   "       TIME_STAMP = '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"'," +

                                   "       SOURCE = 'M', " +

                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) " +
                                   " WHERE MEMBERS_ID = '"+getIntent().getStringExtra("motherId")+"' ";
Log.d("UpdateQuery",memberbasicupdate);

                           executeQuery(memberbasicupdate);



                           String women_extra="UPDATE womenextra" +
                                   "" +
                                   "   SET " +
                                   "" +
                                   "       EDUCATION = '"+String.valueOf(eductionItemPosition)+"'," +
                                   "" +
                                   "       COOKING_FUEL = '"+fuelItemPosition+"'," +
                                   "" +
                                   "       DECISIONMAKER_OWN_HEALTH = '"+String.valueOf(decisionItemPosition)+"'," +
                                   "" +
                                   "       DECISIONMAKER_CHILD_HEALTH = '"+String.valueOf(doctorvisitItemPosition)+"'," +
                                   "" +
                                   "       IF_BANK_ACCOUNT = '"+accountype+"'," +
                                   "" +
                                   "       AC_HOLDER_NAME = '"+nameaccountholder.getText().toString()+"'," +
                                   "" +
                                   "       BANK_NAME = '"+nameofbank.getText().toString()+"'," +
                                   "" +
                                   "       BRANCH = '"+branchname.getText().toString()+"'," +
                                   "" +
                                   "       AC_NO = '"+bankaccountnumber.getText().toString()+"'," +
                                   "" +
                                   "       IFSC_CODE = '"+ifsccode.getText().toString()+"'," +
                                   "" +
                                   "       BANK_DISTANCE = '"+distancetobranch.getText().toString()+"'," +

                                  " BANK_ATM_DISTANCE = '"+distancenearestatm.getText().toString()+"'," +
                                   "" +
                                   "       POSTOFFICE_NAME = '"+nameofpostoffice.getText().toString()+"'," +
                                   "" +
                                   "       POSTOFFICE_ADDRESS = '"+addressofpostoffice.getText().toString()+"'," +
                                   "" +
                                   "       PIN_CODE = '"+postofficepincode.getText().toString()+"'," +
                                   "" +
                                   "       POST_OFFICE_AC = '"+postofficeaccount.getText().toString()+"'," +

                                   "" +
                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end)" +
                                   "" +
                                   " WHERE MEMBERS_ID ='"+getIntent().getStringExtra("motherId")+"'";

                           executeQuery(women_extra);
                           ChildWeightFinal = childWeight.getText().toString() + "." + childWeightGram.getText().toString();

                           String updateChildExtra="UPDATE childextra " +
                                   "" +
                                   "   SET " +
                                   "" +
                                   "       DODELIVERY = '"+dateOfDelivery.getText().toString()+"'," +
                                   "" +
                                   "       DELIVERY_PLACE = '"+String.valueOf(deliveryPlaceItemSelected)+"'," +
                                   "" +
                                   "       CHILD_ORDER = '"+orderOfBirth.getText().toString()+"'," +
                                   "" +
                                   "       BIRTH_WT = '"+ChildWeightFinal+"'," +
                                   "" +
                                   "       FULL_TERM = '"+String.valueOf(wasChildBornPosition)+"',       " +
                                   "" +
                                   "       IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) " +
                                   "" +
                                   " WHERE MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"' ";

                           Log.d("UpdateCjhildExtra",updateChildExtra);

                           executeQuery(updateChildExtra);


                           String cSex;
                           if (childSexItemSelected==1) {

                               cSex="M";
                           }else if(childSexItemSelected==2){
                               cSex="F";
                           }else{
                               cSex="I";
                           }

                           String updateChildNameAge="update memberbasic set stage=case when " +
                               "                              CAST(strftime('%s',date('" + dateOfDelivery.getText().toString() + "','start of month','+12 month'," +
                                       "                                       cast(strftime('%d','" + dateOfDelivery.getText().toString() + "')as text) || ' DAY')) AS integer) > " +
                                       "                                       CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY' END,status=case when " +
                                       "                                       CAST(strftime('%s',date('" + dateOfDelivery.getText().toString() + "','start of month','+12 month'," +
                                       "                                       cast(strftime('%d','" + dateOfDelivery.getText().toString() + "')as text) || ' DAY')) AS integer) > " +
                                       "                                       CAST(strftime('%s',date('now')) as integer) then 'LM' else 'MY'  END " +


                                   ", name = '"+nameOfChild.getText().toString()+"' , sex =  '"+cSex+"' ,  SURVEYOR_ID = '"+session.getSurveyorId()+"',  IS_NEW = (case when IS_NEW='N' then 'N' else 'E' end) , TIME_STAMP = '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())+"' where MEMBERS_ID = '"+getIntent().getStringExtra("memberId")+"'";
                          Log.d("upatedChild",updateChildNameAge);
                           executeQuery(updateChildNameAge);
                           Toast.makeText(EditRegistartion.this, "Record Updated Succesfully", Toast.LENGTH_SHORT).show();
                           onBackPressed();
                       }
                   }
               }
           }
       });


        if(getIntent().getStringExtra("stage").equalsIgnoreCase("PW")){
            childDetails.setVisibility(View.GONE);
            chi.setVisibility(View.GONE);
            profileQuery="select  NAME,DOR,DOENTRY,DOEXIT,DOB,AGE,IF_DOB_ASSUMED,DODEATH,AADDHAR, AADDHAR_ENROL_NO,AADDHAR_DATE_STAMP, AADDHAR_TIME_STAMP,BHAMASHA,MOBILE,f.religion ,f.caste,f.rcard,f.family_type, RELATION,SEX, HANDICAP, IF_MARRIED,EDUCATION,COOKING_FUEL, DECISIONMAKER_OWN_HEALTH, DECISIONMAKER_CHILD_HEALTH, IF_BANK_ACCOUNT, AC_HOLDER_NAME, BANK_NAME, BRANCH, AC_NO,IFSC_CODE,BANK_DISTANCE,BANK_ATM_DISTANCE,POSTOFFICE_NAME,POSTOFFICE_ADDRESS,PIN_CODE,POST_OFFICE_AC,p.PREGNANCY_ID ,p.ORDER_OF_PREGNANCY, p.LMP_DATE,a.IS_APPROVED from memberbasic a left join womenextra w on a.MEMBERS_ID=w.MEMBERS_ID" +
                    " left join familydata f on a.family_id = f.family_id left join pregnant p on a.MEMBERS_ID=p.MEMBERS_ID where a.MEMBERS_ID='"+ getIntent().getStringExtra("memberId")+"' and p.PREGNANCY_ID='"+getIntent().getStringExtra("pregaencyId")+"'";






        Log.d("EditRegister",profileQuery);


        }
        else{
            fragment_pregnant_lady_detail.setVisibility(View.GONE);
            preg.setVisibility(View.GONE);

            profileQuery="select a.family_id, f.RELIGION,f.CASTE,f.RCARD,f.FAMILY_TYPE,a.members_id,a.name as childname,a.husband as husband, a.pctsid as pctsid,b.members_id as mother_id,b.name as name " +

                    ",b.DOB,b.AGE,b.IF_DOB_ASSUMED,b.AADDHAR, b.AADDHAR_ENROL_NO,b.AADDHAR_DATE_STAMP, b.AADDHAR_TIME_STAMP,b.BHAMASHA,b.MOBILE, b.RELATION,w.EDUCATION,w.COOKING_FUEL, w.DECISIONMAKER_OWN_HEALTH, w.DECISIONMAKER_CHILD_HEALTH, w.if_bank_account, w.AC_HOLDER_NAME, w.BANK_NAME, w.BRANCH, w.AC_NO,w.IFSC_CODE, w.BANK_DISTANCE, w.BANK_ATM_DISTANCE,w.POSTOFFICE_NAME,w.POSTOFFICE_ADDRESS,w.PIN_CODE,w.POST_OFFICE_AC,a.sex,c.dodelivery,c.delivery_place,c.child_order,c.birth_wt,c.full_term,a.IS_APPROVED FROM memberbasic a " +
                    " left join familydata f on a.FAMILY_ID=f.FAMILY_ID " +
                    " left join memberbasic b on a.mother_id=b.members_id" +
                    " left join womenextra w on a.MOTHER_ID=w.MEMBERS_ID " +
                    " left join childextra c on a.MEMBERS_ID=c.MEMBERS_ID where b.members_id='"+getIntent().getStringExtra("motherId")+"'";
            Log.d("MYIEDIT REGISTER",profileQuery);

        }

        SQLiteDatabase swl = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = swl.rawQuery(profileQuery,null);

        int total = c.getCount();

        if(total>=1){

            if(c.moveToFirst()){
                do{
                    try {
                        nameBenificery.setText(c.getString(c.getColumnIndex("name")));

                        beneficiaryHusband.setText(c.getString(c.getColumnIndex("husband")));
                        pctsId.setText(c.getString(c.getColumnIndex("pctsid")));

                        phoneNumber.setText(c.getString(c.getColumnIndex("mobile")));
                        bhamashahNumber.setText(c.getString(c.getColumnIndex("bhamasha")));

                        aadharNumber.setText(c.getString(c.getColumnIndex("aaddhar")));

                        if (c.getString(c.getColumnIndex("aaddhar")).equalsIgnoreCase("")) {
                            adhaaravailable.setChecked(true);
                            aadharenrollment.setText(c.getString(c.getColumnIndex("aaddhar_enrol_no")));
                            datee.setText(c.getString(c.getColumnIndex("aaddhar_date_stamp")));

                            String str = c.getString(c.getColumnIndex("aaddhar_time_stamp"));
//                if(childWeightValue.contains(".")){

                            Log.d("AdharTiemStamp", str);
                            String[] separated = str.split("\\:");
                            String hr = separated[0]; // this will contain "Fruit"
                            String min = separated[1];
                            String sec = separated[2];

                            timeHour.setText(hr);
                            timeMin.setText(min);
                            timeSec.setText(sec);


                        }
                        dob.setText(c.getString(c.getColumnIndex("dob")));
                        personAge.setText(c.getString(c.getColumnIndex("age")));
                        personaReligion.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("religion"))));
                        castSpinner.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("caste"))));
                        rationcardColorSpinner.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("rcard"))));
                        relationshipHeadSpinner.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("relation"))));
                        typeFamilySpinner.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("family_type"))));
                        educationComplted.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("education"))));
                        decsionSpinner.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("decisionmaker_own_health"))));
                        decsionVisitDoctorSpinner.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("decisionmaker_child_health"))));

                        if (c.getString(c.getColumnIndex("if_bank_account")).equalsIgnoreCase("N")) {
                            bankgroup.check(R.id.noaccount);
                        }
                        if (c.getString(c.getColumnIndex("if_bank_account")).equalsIgnoreCase("B")) {
                            bankgroup.check(R.id.accountyes);
                            nameaccountholder.setText(c.getString(c.getColumnIndex("ac_holder_name")));
                            confirmnameaccountholder.setText(c.getString(c.getColumnIndex("ac_holder_name")));
                            nameofbank.setText(c.getString(c.getColumnIndex("bank_name")));
                            branchname.setText(c.getString(c.getColumnIndex("branch")));
                            bankaccountnumber.setText(c.getString(c.getColumnIndex("ac_no")));
                            confirmbankaccountnumber.setText(c.getString(c.getColumnIndex("ac_no")));
                            ifsccode.setText(c.getString(c.getColumnIndex("ifsc_code")));
                            confirmifsccode.setText(c.getString(c.getColumnIndex("ifsc_code")));


                        }
                        if (c.getString(c.getColumnIndex("if_bank_account")).equalsIgnoreCase("P")) {
                            bankgroup.check(R.id.accountno);
                            nameofpostoffice.setText(c.getString(c.getColumnIndex("postoffice_name")));
                            addressofpostoffice.setText(c.getString(c.getColumnIndex("postoffice_address")));
                            postofficeaccount.setText(c.getString(c.getColumnIndex("post_office_ac")));
                            confirmpostofficeAccount.setText(c.getString(c.getColumnIndex("post_office_ac")));
                            postofficepincode.setText(c.getString(c.getColumnIndex("pin_code")));
                            confirmpostpincode.setText(c.getString(c.getColumnIndex("pin_code")));

                        }
                        distancetobranch.setText(c.getString(c.getColumnIndex("bank_distance")));
                        distancenearestatm.setText(c.getString(c.getColumnIndex("bank_atm_distance")));

                        if (getIntent().getStringExtra("stage").equalsIgnoreCase("PW")) {
                            pregnetNumnber.setText(c.getString(c.getColumnIndex("order_of_pregnancy")));
                            lmpdate.setText(c.getString(c.getColumnIndex("lmp_date")));
                        } else {


                            nameOfChild.setText(c.getString(c.getColumnIndex("childname")));
                            dateOfDelivery.setText(c.getString(c.getColumnIndex("dodelivery")));
                            placeofDelivery.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("delivery_place"))));
                            deliveryPlaceItemSelected = Integer.parseInt(c.getString(c.getColumnIndex("delivery_place")));
                            orderOfBirth.setText(c.getString(c.getColumnIndex("child_order")));
                            sexOfchild.setSelection(indexOfYesNo(c.getString(c.getColumnIndex("sex"))));

                            childSexItemSelected = indexOfYesNo(c.getString(c.getColumnIndex("sex")));
                            wasChildBorn.setSelection(Integer.parseInt(c.getString(c.getColumnIndex("full_term"))));
                            wasChildBornPosition = Integer.parseInt(c.getString(c.getColumnIndex("full_term")));

                            String childWeightValue = c.getString(c.getColumnIndex("birth_wt"));

                            if (childWeightValue.contains(".")) {
                                double amount = Double.parseDouble(childWeightValue);
                                NumberFormat numberFormat = new DecimalFormat("##.###");
                                String str = numberFormat.format(amount);
//                if(childWeightValue.contains(".")){

                                Log.d("NumberFormat", str);
                                String[] separated = str.split("\\.");
                                String weight = separated[0]; // this will contain "Fruit"
                                String gram = separated[1];

                                childWeight.setText(weight);
                                childWeightGram.setText(gram);
                            } else {
                                childWeight.setText(childWeightValue);
//                            childWeightGram.setText("000");
                            }


                        }

                        if (c.getString(c.getColumnIndex("cooking_fuel")).length() > 0) {

//                        Log.d("CheckedValue",c.getString(c.getColumnIndex("if_using_contraceptive")));

                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("1")) {
                                f1.setChecked(true);
                            }

                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("2")) {
                                f2.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("3")) {
                                f3.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("4")) {
                                f4.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("5")) {
                                f5.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("6")) {
                                f6.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("7")) {
                                f7.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("8")) {
                                f8.setChecked(true);
                            }
                            if (c.getString(c.getColumnIndex("cooking_fuel")).contains("9")) {
                                f9.setChecked(true);
                            }

                        }


                    }catch (Exception e){

                    }

                    if (c.getString(c.getColumnIndex("is_approved")).equalsIgnoreCase("Y")) {
                        save.setEnabled(false);
                        save.setVisibility(View.INVISIBLE);

                    }
//
                }while (c.moveToNext());
            }


        }


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

        nameOfChild.requestFocus();
//    dateOfDelivery.requestFocus();


    }

    public void basicclickedForm(){

//
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
            Toast.makeText(EditRegistartion.this, "Enter Number of child", Toast.LENGTH_SHORT).show();
        }


        if(error==0&&lmpdate.getText().toString().length()==0){
            error =1;
            lmpdate.requestFocus();
            Toast.makeText(EditRegistartion.this, "Please Select Date", Toast.LENGTH_SHORT).show();
        }

        return error;

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
            case "M":
                x=1;
                break;
            case "F":
                x=2;
                break;
            case "I":
                x=3;
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

    public void executeQuery(String Query){

        SQLiteDatabase dQuery= openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cQuery = dQuery.rawQuery(Query, null);

        cQuery.moveToFirst();
        cQuery.close();

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

//        if(error==0 && DeliveryPlace.equalsIgnoreCase("--Select Options--")){
//
//            error=1;
//            Toast.makeText(this, "Enter Delivery Place", Toast.LENGTH_SHORT).show();
//        }
  if(error==0 && deliveryPlaceItemSelected==0){

            error=1;
            Toast.makeText(this, "Enter Delivery Place", Toast.LENGTH_SHORT).show();
        }

        if(error==0 && orderOfBirth.getText().toString().length()==0){

            error=1;
            Toast.makeText(this, "Enter Order of Birth", Toast.LENGTH_SHORT).show();
        }

        if(error==0 && childSexItemSelected==0){

            error=1;
            Toast.makeText(this, "Enter sex of child ", Toast.LENGTH_SHORT).show();
        }

        if(error==0 && childWeight.getText().toString().length()==0 && childWeightGram.getText().toString().length()==0){

            error=1;
            Toast.makeText(this, "Enter Child Weight", Toast.LENGTH_SHORT).show();
        }
        if(error==0 && wasChildBornPosition==0){

            error =1;
            Toast.makeText(this, "Select Full Term", Toast.LENGTH_SHORT).show();
        }





        return error;
    }

    @Override
    public boolean onNavigateUp(){
        finish();
        return true;
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
