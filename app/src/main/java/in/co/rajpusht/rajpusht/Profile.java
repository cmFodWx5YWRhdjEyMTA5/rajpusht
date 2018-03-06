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

import java.util.ArrayList;

import Adpter.LocationSelectionAdapter;
import extras.DbHelper;
import extras.ProfileGetSetMethod;

public class Profile extends AppCompatActivity {
    RecyclerView recyclerviewSelectLocation;
    ArrayList<ProfileGetSetMethod> profileArray = new ArrayList<ProfileGetSetMethod>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DbHelper db = new DbHelper(this);

        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerviewSelectLocation = (RecyclerView) findViewById(R.id.recyclerviewSelectLocation);

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(i);
            }
        });

        bindLoation();


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
                            cFlush.getString(cFlush.getColumnIndex("village_eng")), cFlush.getString(cFlush.getColumnIndex("surveyor_name")),
                            cFlush.getString(cFlush.getColumnIndex("surveyor_id")));

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
}
