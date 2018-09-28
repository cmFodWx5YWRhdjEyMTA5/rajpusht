package in.co.rajpusht.rajpusht;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RadioGroup;

import java.util.Locale;

import extras.SessionManager;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    SessionManager session;
    Locale myLocale;
    RadioGroup language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session= new SessionManager(SplashScreen.this);

        setLocale(session.getLangugae());

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if(session.isLogin()) {


                    String loginStatus = locationcheck();
                    if(loginStatus.equalsIgnoreCase("true")){

                        Intent intentprofile = new Intent(getApplicationContext(),DashBoard.class);
                        startActivity(intentprofile);
                        finish();
                    }
                    else{

                        Intent intentProfile = new Intent(getApplicationContext(),Profile.class);
                        startActivity(intentProfile);
                        finish();
                    }


//                    Intent itentLogin = new Intent(getApplicationContext(),Dashboard.class);
//                    startActivity(itentLogin);
//
//                    finish();

                }else {

                    Intent i = new Intent(SplashScreen.this, Login.class);
                    startActivity(i);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }



    public String  locationcheck(){
        String status;

        String checkQuery="SELECT * FROM ASSIGNED_LOCATION WHERE LOGIN='Y'";
        SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest",MODE_PRIVATE, null);

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
                    session.setSurveyorName(c.getString(c.getColumnIndex("surveyor_name")));
                    session.setSurveyorId(c.getString(c.getColumnIndex("surveyor_id")));


                } while (c.moveToNext());
            }

            status="true";
        }else{
            status="false";

        }

        return status;
    }


    public void setLocale(String lang) {

        try {
            Log.d("Profile", "Inserted Profiel check"+" "+lang);

            session.setLangugae(lang);
            myLocale = new Locale(lang);
            Locale.setDefault(myLocale);

            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.setLocale(myLocale);
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            conf.setLayoutDirection(myLocale);
//            }
            res.updateConfiguration(conf, res.getDisplayMetrics());
//        Intent refresh = new Intent(this, Profile.class);
//        startActivity(refresh);

            getBaseContext().getResources().updateConfiguration(conf,
                    getBaseContext().getResources().getDisplayMetrics());
//            finish();
//            startActivity(getIntent());
        }catch (Exception e){
            Log.d("Profile", "Inserted Profiel check"+e.toString());
        }
//        this.setContentView(R.layout.activity_profile);
    }

}
