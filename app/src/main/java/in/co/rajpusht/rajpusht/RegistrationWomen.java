package in.co.rajpusht.rajpusht;

import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    String registerMemeberId,familyID,memberId,pregnentId;
    int castitem,relationitem,rationcardItem,familyttypeItem,eductionItemPosition;


   DbHelper db;
    EditText nameBenificery,phoneNumber,UidNumber,pregnetNumnber;

    Spinner personaReligion,castSpinner,rationcardColorSpinner,relationshipHeadSpinner,typeFamilySpinner,educationComplted,fuelSelectionSpinner,
            decsionSpinner,decsionVisitDoctorSpinner;
    View fragment_pregnant_lady_detail,fragment_young_mother_basic_details;
    EditText nameaccountholder;
    String educationItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_young_mother);

//        getSupportActionBar().hide();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//

        db= new DbHelper(this);

        fragment_young_mother_basic_details = (View)findViewById(R.id.pregnentbasic);
        fragment_pregnant_lady_detail = (View)findViewById(R.id.pregnentDetails);






        status = getIntent().getStringExtra("status");
        Log.d("Ranjeet",status);


        nameBenificery = (EditText) findViewById(R.id.nameBenificery);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        UidNumber = (EditText) findViewById(R.id.UidNumber);
        nameaccountholder = (EditText) findViewById(R.id.nameaccountholder);
        pregnetNumnber = (EditText)findViewById(R.id.pregnetNumnber);

        basic= (TextView) findViewById(R.id.basic);
        basicPregment = (TextView) findViewById(R.id.basicPregment);
        child= (TextView) findViewById(R.id.child);

        if(status.equals("checkedChild")){

            child.setEnabled(false);
            basicPregment.setVisibility(View.GONE);
        }

        if(status.equals("checkedPregnent")){

                child.setVisibility(View.GONE);
                basicPregment.setEnabled(false);

        }
         if(status.equals("Pregnentandchild")){
             basicPregment.setEnabled(false);
             child.setEnabled(false);

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








        Button save = (Button) findViewById(R.id.youngMotherSave);
        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


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
           basicPregment.setText("Pregnenet ACtivated");
//           basicPregment.setTextColor(green);
//           basicPregment.setTextColor(green);
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


        int error =0;
if(nameBenificery.getText().toString().length()==0){

    Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
    error=1;

}

if(error==0 && phoneNumber.getText().toString().length()==0){

    Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
    error=1;
}

if(error==0 && UidNumber.getText().toString().length()==0){

    Toast.makeText(this, "Enter UID Numnber", Toast.LENGTH_SHORT).show();
error=1;
}

if(error == 0 && nameaccountholder.getText().toString().length()==0 ){
    Toast.makeText(this, "Enter Account Holder Name", Toast.LENGTH_SHORT).show();
    error =1;
}
if(error==0 && educationItem.equalsIgnoreCase("select option")){
    Toast.makeText(this, "Enter education", Toast.LENGTH_SHORT).show();
    error=1;
}
return error;


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
//
}
