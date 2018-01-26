package in.co.rajpusht.rajpusht;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PregnantWomenForm extends AppCompatActivity {

    ImageView p1, p2, p3, p4, p1c, p2c, p3c, p4c, na, nac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant_women_form);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        p1 = (ImageView)findViewById(R.id.p1) ;
        p2 = (ImageView)findViewById(R.id.p2) ;
        p3 = (ImageView)findViewById(R.id.p3) ;
        p4 = (ImageView)findViewById(R.id.p4) ;

        p1c = (ImageView)findViewById(R.id.p1c) ;
        p2c = (ImageView)findViewById(R.id.p2c) ;
        p3c = (ImageView)findViewById(R.id.p3c) ;
        p4c = (ImageView)findViewById(R.id.p4c) ;

        ImageView back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        p1Clicked();

    }




    public void p1Clicked(){

        Fragment fragment = null;
        fragment = new Pw1Fragment();
            p1.setVisibility(View.GONE);
            p2.setVisibility(View.VISIBLE);
            p3.setVisibility(View.VISIBLE);
            p4.setVisibility(View.VISIBLE);
        p1c.setVisibility(View.VISIBLE);
        p2c.setVisibility(View.GONE);
        p3c.setVisibility(View.GONE);
        p4c.setVisibility(View.GONE);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.formframe, fragment);
            fragmentTransaction.commit();
        }

    }

    public void p2Clicked(){

        Fragment fragment = null;
        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.GONE);
        p3.setVisibility(View.VISIBLE);
        p4.setVisibility(View.VISIBLE);

        p1c.setVisibility(View.GONE);
        p2c.setVisibility(View.VISIBLE);
        p3c.setVisibility(View.GONE);
        p4c.setVisibility(View.GONE);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.formframe, fragment);
            fragmentTransaction.commit();
        }
    }

    public void p3Clicked(){

        Fragment fragment = null;
        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.VISIBLE);
        p3.setVisibility(View.GONE);
        p4.setVisibility(View.VISIBLE);

        p1c.setVisibility(View.GONE);
        p2c.setVisibility(View.GONE);
        p3c.setVisibility(View.VISIBLE);
        p4c.setVisibility(View.GONE);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.formframe, fragment);
            fragmentTransaction.commit();
        }
    }

    public void p4Clicked(){

        Fragment fragment = null;
        p1.setVisibility(View.VISIBLE);
        p2.setVisibility(View.VISIBLE);
        p3.setVisibility(View.VISIBLE);
        p4.setVisibility(View.GONE);

        p1c.setVisibility(View.GONE);
        p2c.setVisibility(View.GONE);
        p3c.setVisibility(View.GONE);
        p4c.setVisibility(View.VISIBLE);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.formframe, fragment);
            fragmentTransaction.commit();
        }
    }


    public void pw1(View v){
        p1Clicked();

    }
    public void pw2(View v){
        p2Clicked();

    }
    public void pw3(View v){
        p3Clicked();

    }
    public void pw4(View v){

        p4Clicked();
    }

}
