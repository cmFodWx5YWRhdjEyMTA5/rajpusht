package in.co.rajpusht.rajpusht;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    Button ym1, ym2, ym3, ym4, ym5;
    LinearLayout one, two, three, four, five, six;
    Spinner breastfeeding, stopbreastfeeding, contraceptive, currentlyusing, counseledNGO, familyexpence;


    View sbcclayout, dietaryLyout, heightWeightLayout,fragmentnaforlmnmy;
    RelativeLayout relativessbc, relativeDiversity, relativeHeight,holdingTabs;
    View basicclick, pregnentclick, childclick;

    Dialog dialogCoupon;

    ImageView naButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

//        setContentView(R.layout.activity_lm_actvity);

        getSupportActionBar().setTitle("Child Name : "+getIntent().getStringExtra("name"));

        View fragment_na = findViewById(R.id.NaFormButtonLayout);


        basicclick = (View) findViewById(R.id.basicclick);
        pregnentclick = (View) findViewById(R.id.pregnentclick);
        childclick = (View) findViewById(R.id.childclick);

        relativessbc = (RelativeLayout) findViewById(R.id.relativessbc);
        relativeDiversity = (RelativeLayout) findViewById(R.id.relativeDiversity);
        relativeHeight = (RelativeLayout) findViewById(R.id.relativeHeight);
        holdingTabs = (RelativeLayout) findViewById(R.id.holdingTabs);


        sbcclayout = (View) findViewById(R.id.sbcclayout);
        dietaryLyout = (View) findViewById(R.id.dietaryLyout);
        heightWeightLayout = (View) findViewById(R.id.heightWeightLayout);


        fragmentnaforlmnmy = (View)findViewById(R.id.NaFormButtonLayout);
        naButton = (ImageView) findViewById(R.id.naButton);

        naButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentnaforlmnmy.setVisibility(View.VISIBLE);
                holdingTabs.setVisibility(View.GONE);
            }
        });


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


        breastfeeding = (Spinner) findViewById(R.id.breastfeeding);
        List<String> listbreastfeeding = new ArrayList<String>();
        listbreastfeeding.add("--Select Options--");
        listbreastfeeding.add("Yes");
        listbreastfeeding.add("No");
        ArrayAdapter<String> breastfeedingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listbreastfeeding);
        breastfeedingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breastfeeding.setAdapter(breastfeedingAdapter);
try {
    stopbreastfeeding = (Spinner) findViewById(R.id.stopbreastfeeding);

    List<String> liststopbreastfeeding = new ArrayList<String>();
    liststopbreastfeeding.add("--Select Options--");
    liststopbreastfeeding.add("Within one week after birth");
    liststopbreastfeeding.add("Within one month after birth");
    liststopbreastfeeding.add("Within three months after birth");
    liststopbreastfeeding.add("Within six months after birth");
    liststopbreastfeeding.add("Within 12 months after birth");
    liststopbreastfeeding.add("Within 12-18 months after birth");
    liststopbreastfeeding.add("Never breastfed");
    ArrayAdapter<String> stopbreastfeedingAdapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, liststopbreastfeeding);
    stopbreastfeedingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    stopbreastfeeding.setAdapter(stopbreastfeedingAdapter);
}catch(Exception e){

    Log.d("Spinner",e.toString());
}

        contraceptive = (Spinner) findViewById(R.id.contraceptive);
        List<String> listcontraceptive = new ArrayList<String>();
        listcontraceptive.add("--Select Options--");
        listcontraceptive.add("Yes");
        listcontraceptive.add("No");
        ArrayAdapter<String> contraceptiveAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listcontraceptive);
        contraceptiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contraceptive.setAdapter(contraceptiveAdapter);

        currentlyusing = (Spinner) findViewById(R.id.currentlyusing);
        List<String> listcurrentlyusing = new ArrayList<String>();
        listcurrentlyusing.add("--Select Options--");
        listcurrentlyusing.add("Female sterilization");
        listcurrentlyusing.add("Male sterilization");
        listcurrentlyusing.add("IUCD/ PPIUCD");
        listcurrentlyusing.add("Injectables");
        listcurrentlyusing.add("Oral pills");
        listcurrentlyusing.add("Condom/Nirodh");
        listcurrentlyusing.add("Withdrawal method");
        listcurrentlyusing.add("Other traditional method");
        listcurrentlyusing.add("Other modern method");
        ArrayAdapter<String> currentlyusingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listcurrentlyusing);
        currentlyusingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentlyusing.setAdapter(currentlyusingAdapter);

        counseledNGO = (Spinner) findViewById(R.id.counseledNGO);
        List<String> listcounseledNGO = new ArrayList<String>();
        listcounseledNGO.add("--Select Options--");
        listcounseledNGO.add("Yes");
        listcounseledNGO.add("No");
        listcounseledNGO.add("Can't Remember");
        ArrayAdapter<String> counseledNGOAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listcounseledNGO);
        counseledNGOAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        counseledNGO.setAdapter(counseledNGOAdapter);

        familyexpence = (Spinner) findViewById(R.id.familyexpence);
        List<String> listfamilyexpence = new ArrayList<String>();
        listfamilyexpence.add("--Select Options--");
        listfamilyexpence.add("Less than Rs. 500 per month");
        listfamilyexpence.add("Rs. 500- 1,000 per month");
        listfamilyexpence.add("Rs. 1000 – 2000 per month");
        listfamilyexpence.add("Rs. 2000 – 4000 per month");
        listfamilyexpence.add("Rs. 4000 – 5,000 per month");
        listfamilyexpence.add("Rs. 5,000 and above");
        ArrayAdapter<String> familyexpenceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listfamilyexpence);
        familyexpenceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyexpence.setAdapter(familyexpenceAdapter);

        ym1 = (Button) findViewById(R.id.YM1Button);
        ym2 = (Button) findViewById(R.id.YM2Button);
        ym3 = (Button) findViewById(R.id.YM3Button);
        ym4 = (Button) findViewById(R.id.YM4Button);
        ym5 = (Button) findViewById(R.id.YM5Button);

        one = (LinearLayout) findViewById(R.id.one);
        two = (LinearLayout) findViewById(R.id.two);
        three = (LinearLayout) findViewById(R.id.three);
        four = (LinearLayout) findViewById(R.id.four);
        five = (LinearLayout) findViewById(R.id.five);
        six = (LinearLayout) findViewById(R.id.six);

        ym1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
            }
        });

        ym2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
            }
        });

        ym3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
            }
        });

        ym4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                one.setVisibility(View.GONE);
                two.setVisibility(View.GONE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
            }
        });
        ym5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentnaforlmnmy.setVisibility(View.GONE);
                holdingTabs.setVisibility(View.VISIBLE);
                one.setVisibility(View.GONE);
                two.setVisibility(View.GONE);
                three.setVisibility(View.GONE);
                four.setVisibility(View.GONE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);
            }
        });





    }



    public void sscclickedForm() {

//        Log.d("Validation","inserted inside fucntion");

        sbcclayout.setVisibility(View.VISIBLE);
        dietaryLyout.setVisibility(View.GONE);
        heightWeightLayout.setVisibility(View.GONE);

        basicclick.setVisibility(View.VISIBLE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("BASIC");

    }

    public void diversityClickForm() {

//        activatedREgistrationForm=2;
        sbcclayout.setVisibility(View.GONE);
        dietaryLyout.setVisibility(View.VISIBLE);
        heightWeightLayout.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.VISIBLE);
        childclick.setVisibility(View.GONE);
//        getSupportActionBar().setTitle("PREGNANT");


    }


    public void heightPregnentForm() {


//        activatedREgistrationForm=3;
        heightWeightLayout.setVisibility(View.VISIBLE);
        sbcclayout.setVisibility(View.GONE);
        dietaryLyout.setVisibility(View.GONE);
        basicclick.setVisibility(View.GONE);
        pregnentclick.setVisibility(View.GONE);
        childclick.setVisibility(View.VISIBLE);
//        getSupportActionBar().setTitle("CHILD");
    }


    public void onBackPressed() {
//        super.onBackPressed();

        dialogCoupon    = new Dialog(MyActivity.this);
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
}
