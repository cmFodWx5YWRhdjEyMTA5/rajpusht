package in.co.rajpusht.rajpusht;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adpter.LocationSelectionAdapter;
import extras.DbHelper;
import extras.ProfileGetSetMethod;
import extras.SessionManager;

public class Profile extends AppCompatActivity {
    RecyclerView recyclerviewSelectLocation;
    ArrayList<ProfileGetSetMethod> profileArray = new ArrayList<ProfileGetSetMethod>();
    SessionManager session;
    TextView profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new SessionManager(getApplicationContext());

        DbHelper db = new DbHelper(this);

        profileName = (TextView) findViewById(R.id.profileName);
        profileName.setText(session.getSurveyorName());

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerviewSelectLocation = (RecyclerView) findViewById(R.id.recyclerviewSelectLocation);
        bindLoation();

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//to validate clicked or not
if(!new Login().selectedId.equalsIgnoreCase("")){

    String updateAssign="UPDATE ASSIGNED_LOCATION SET LOGIN=''";
    updatQuery(updateAssign);
    updatQuery("UPDATE ASSIGNED_LOCATION SET LOGIN='Y' WHERE ID="+new Login().selectedId);

   String updateStatus=locationcheck();

   if(updateStatus.equalsIgnoreCase("true")){
       Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
       Intent intentprofile = new Intent(getApplicationContext(),DashBoard.class);
       startActivity(intentprofile);
       finish();
   }
   else{
       Toast.makeText(Profile.this, "Not Updated ", Toast.LENGTH_SHORT).show();
       Intent intentProfile = new Intent(getApplicationContext(),Profile.class);
       startActivity(intentProfile);
       finish();
   }


}
else{
    Toast.makeText(Profile.this, "Select One Location ", Toast.LENGTH_SHORT).show();


                }



//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);
            }
        });




    }

    public void bindLoation() {
        String bindLoation = "select * from assigned_location";

        SQLiteDatabase dbsflush = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cFlush = dbsflush.rawQuery(bindLoation, null);
        if (cFlush.getCount() >= 1) {

            int i = 0;
            if (cFlush.moveToFirst()) {
                do {


                    ProfileGetSetMethod profilesetset = new ProfileGetSetMethod(cFlush.getString(cFlush.getColumnIndex("id")),
                            cFlush.getString(cFlush.getColumnIndex("village_eng"))+" ("+cFlush.getString(cFlush.getColumnIndex("awc_eng"))+")", cFlush.getString(cFlush.getColumnIndex("surveyor_name")),
                            cFlush.getString(cFlush.getColumnIndex("surveyor_id")),cFlush.getString(cFlush.getColumnIndex("login")));

                    profileArray.add(profilesetset);


//


                } while (cFlush.moveToNext());


                LocationSelectionAdapter LocationAdapter = new LocationSelectionAdapter(profileArray);
                recyclerviewSelectLocation.setAdapter(LocationAdapter);
                LinearLayoutManager linear = new LinearLayoutManager(getApplication());
                linear.setReverseLayout(true);
                linear.setStackFromEnd(true);
                recyclerviewSelectLocation.setLayoutManager(linear);
            }


        }
    }


    public void updatQuery(String updatequery){

        SQLiteDatabase dbsflush = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

        Cursor cFlush = dbsflush.rawQuery(updatequery, null);
        cFlush.moveToFirst();
        cFlush.close();

    }

    public String  locationcheck(){
        String status;

        String checkQuery="SELECT * FROM ASSIGNED_LOCATION WHERE LOGIN='Y'";
        SQLiteDatabase dbs =openOrCreateDatabase("ranjeettest",MODE_PRIVATE, null);

        Cursor c = dbs.rawQuery(checkQuery,null);
        if(c.getCount()>0){

            if (c.moveToFirst()) {
                do {

                    session.setAwcCode(c.getString(c.getColumnIndex("awc_code")));
                    session.setDistCode(c.getString(c.getColumnIndex("dist_code")));
                    session.setProjectCode(c.getString(c.getColumnIndex("project_code")));
                    session.setSectorCode(c.getString(c.getColumnIndex("sector_code")));
                    session.setVillageCode(c.getString(c.getColumnIndex("village_code")));
                    session.setVillageEng(c.getString(c.getColumnIndex("village_eng")));
                    session.setVillageHindi(c.getString(c.getColumnIndex("village_hindi")));
//                    session.setSurveyorName(c.getString(c.getColumnIndex("surveyor_name")));
                    session.setSurveyorId(c.getString(c.getColumnIndex("surveyor_id")));
session.setAwcEng(c.getString(c.getColumnIndex("awc_eng")));

                } while (c.moveToNext());
            }

            status="true";
        }else{
            status="false";

        }

        return status;
    }
}
