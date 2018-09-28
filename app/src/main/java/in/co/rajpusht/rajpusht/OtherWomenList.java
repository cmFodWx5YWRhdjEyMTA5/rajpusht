package in.co.rajpusht.rajpusht;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Adpter.DetailsAdapter;
import extras.BeneficiaryList;
import extras.RecyclerTouchListener;

public class OtherWomenList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<BeneficiaryList> arraybeneficiary = new ArrayList<BeneficiaryList>();
    String query1="";
    String query2="";
    String beneficiaryQuery="select a.Members_id ,a.family_id,a.name,b.members_id as mother_id,b.name as mother,ifnull(a.status,b.status)status,ifnull(a.stage,b.stage)as stage,ifnull(a.sub_stage,b.sub_stage)as sub_stage,strftime('%d/%m/%Y', lmp_date)lmp_date,strftime('%d/%m/%Y', dodelivery)dodelivery,p.PREGNANCY_ID,is_anc,case\n" +
            "when  julianday('now') - julianday(lmp_date)<=98 then 'PW1'\n" +
            "when  julianday('now') - julianday(lmp_date)<=196 then 'PW2'\n" +
            "when  julianday('now') - julianday(lmp_date)<=252 then 'PW3'\n" +
            "when  julianday('now') - julianday(lmp_date)<=280 then 'PW4'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+3 month',cast(strftime('%d',DODELIVERY)as text) ||' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM1'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+6 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM2'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+12 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'LM3'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+18 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY1'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+24 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY2'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+30 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY3'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+36 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY4'\n" +
            "when CAST(strftime('%s',date(DODELIVERY,'start of month','+38 month',cast(strftime('%d',DODELIVERY)as text) || ' DAY')) AS integer) > CAST(strftime('%s',date('now')) as integer) then 'MY5'\n" +
            "END\n as " +
            "current_sub_stage \n" +
            "FROM memberbasic a inner join familydata f on a.family_id=f.family_id left join memberbasic b on a.mother_id=b.members_id left join (select * from pregnant where is_active='Y') p on a.members_id=p.MEMBERS_ID left join childextra c on a.MEMBERS_ID=c.MEMBERS_ID" +
            " left join (select * from pw_tracking) t on p.PREGNANCY_ID=t.PREGNANCY_ID and t.SUB_STAGE=current_sub_stage where (a.is_to_track<>'Y' or current_sub_stage='') and f.awc_code=(select awc_code from assigned_location where login='Y')  order by a.sub_stage asc,a.name desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_women_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.beneficairyList);
        getallBeneficairyList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                BeneficiaryList beneficiaryList= arraybeneficiary.get(position);
                if(beneficiaryList.getCurrent_sub_stage().substring(0,2).equalsIgnoreCase("pw")) {


String queryStringPregnent = "update pregnant set is_active = 'Y',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) WHERE MEMBERS_ID = '"+beneficiaryList.getPregnancy_id()+"'";
                   String queryUpdateMember = "update memberbasic set IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end), IS_TO_TRACK='Y' WHERE MEMBERS_ID='"+beneficiaryList.getMembers_id()+"' and sub_stage='" +beneficiaryList.getCurrent_sub_stage() +"'";



                    alertBox(queryStringPregnent, queryUpdateMember);
                }

                if(beneficiaryList.getCurrent_sub_stage().substring(0,2).equalsIgnoreCase("LM")|| beneficiaryList.getCurrent_sub_stage().substring(0,2).equalsIgnoreCase("MY")){
                    String QueryCDBM="update memberbasic set doexit='"+ new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date())+"', is_to_track='Y',IS_NEW=(case when IS_NEW='N' then 'N' else 'E' end) where Members_id='"+beneficiaryList.getMembers_id()+"'";
                    alertBox("",QueryCDBM);
                }

            }


        @Override
        public void onLongClick(View view, int position) {

        }
    }));

    }
    public void getallBeneficairyList(){

        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(beneficiaryQuery , null);
        Log.d("beneficiaryQuery",beneficiaryQuery);

        int total = c.getCount();

        if(total>=1) {

            if (c.moveToFirst()) {
                do {

                    BeneficiaryList benefic = new BeneficiaryList(c.getString(c.getColumnIndex("Members_id")), c.getString(c.getColumnIndex("family_id")), c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("husband")),c.getString(c.getColumnIndex("pctsid")), c.getString(c.getColumnIndex("mother_id")),
                            c.getString(c.getColumnIndex("mother")), c.getString(c.getColumnIndex("status")),
                            c.getString(c.getColumnIndex("stage")), c.getString(c.getColumnIndex("sub_stage")),
                            c.getString(c.getColumnIndex("lmp_date")), c.getString(c.getColumnIndex("dodelivery")), c.getString(c.getColumnIndex("current_sub_stage")),c.getString(c.getColumnIndex("pregnancy_id")),c.getString(c.getColumnIndex("is_anc")),
                            c.getString(c.getColumnIndex("mother_id")));
                    arraybeneficiary.add(benefic);


//                          Log.d("awcrecords",c.getString(c.getColumnIndex("awc_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("dist_code")));
//                          Log.d("awcrecords",c.getString(c.getColumnIndex("familyid")));

                } while (c.moveToNext());
            }
        }




        DetailsAdapter adapter = new DetailsAdapter(arraybeneficiary, getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linear= new LinearLayoutManager(getApplication());
        linear.setReverseLayout(true);
        linear.setStackFromEnd(true);
        recyclerView.setLayoutManager(linear);

    }

    public void alertBox(String pregentId,String memeberId ){
      query1=pregentId;
        query2=memeberId;

        AlertDialog.Builder builder = new AlertDialog.Builder(OtherWomenList.this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

        //Setting message manually and performing action on button click
        builder.setMessage("")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

try {
    executeQuery(query1);
}catch (Exception e){

}
try {
    executeQuery(query2);
}catch(Exception e){

}

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Do you want to track ?");
        alert.show();



    }

    public void executeQuery(String Query){

        SQLiteDatabase dQuery= openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cQuery = dQuery.rawQuery(Query, null);

        cQuery.moveToFirst();
        cQuery.close();

    }






}
