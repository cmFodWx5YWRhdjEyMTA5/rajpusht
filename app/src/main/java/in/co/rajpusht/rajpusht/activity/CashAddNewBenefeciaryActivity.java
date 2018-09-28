package in.co.rajpusht.rajpusht.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.shuhart.stepview.StepView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import in.co.rajpusht.rajpusht.Login;
import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.RegistrationWomen;
import in.co.rajpusht.rajpusht.adapter.ViewPagerAdapter;
import in.co.rajpusht.rajpusht.fragment.FragmentBankAccount;
import in.co.rajpusht.rajpusht.fragment.FragmentPreview;
import in.co.rajpusht.rajpusht.fragment.FragmentPersonal;
import in.co.rajpusht.rajpusht.fragment.FragmentPregnancy;
import in.co.rajpusht.rajpusht.model.Form1Model;
import in.co.rajpusht.rajpusht.model.Form2Model;
import in.co.rajpusht.rajpusht.model.Form3Model;
import in.co.rajpusht.rajpusht.model.Form4Model;
import in.co.rajpusht.rajpusht.model.Form5Model;
import in.co.rajpusht.rajpusht.model.Installment1Model;
import in.co.rajpusht.rajpusht.model.Installment2Model;
import in.co.rajpusht.rajpusht.model.Installment3Model;
import in.co.rajpusht.rajpusht.model.Installment4Model;
import in.co.rajpusht.rajpusht.model.Installment5Model;
import in.co.rajpusht.rajpusht.tool.Constants;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

public class CashAddNewBenefeciaryActivity extends AppCompatActivity
        implements FragmentPersonal.OnCallbackReceivedPersonal,
                    FragmentBankAccount.OnCallbackReceivedBankAccount,
                    FragmentPregnancy.OnCallbackReceivedPregnancy{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    StepView stepView;

    //Fragments
    FragmentPersonal fragmentPersonal;
    FragmentBankAccount fragmentBankAccount;
    FragmentPregnancy fragmentPregnancy;
    FragmentPreview fragmentPreview;

    private RelativeLayout layoutPreview, layoutPreviewScreen;
    Button buttonBack, buttonNext;
    private ImageButton previewImageButton;
    private ImageView cancelPreviewButton;
    TextView headerText;
    int position = 0;
    boolean firstVisit = true;
    TextView textViewPersonal, textViewBank, textViewPregnancy;


    private String mPersonalData = "";
    private String mBankAccountData = "";
    private String mPregnancyData = "";

    String pushDataJson;

    FloatingActionButton fabPreview;


    String id = "";
    private  boolean isEditable = false;
    String aadharCheck;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_add_new_benefeciary);
       // changeStatusBarColor();

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);

        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
        viewPager.beginFakeDrag();
        setupStepView();

        fabPreview = (FloatingActionButton)findViewById(R.id.fabPreview);

        textViewPersonal = (TextView)findViewById(R.id.presonalPreview);
        textViewBank = (TextView)findViewById(R.id.bankPreview);
        textViewPregnancy = (TextView)findViewById(R.id.pregnancyPreview);

        layoutPreview = (RelativeLayout)findViewById(R.id.layoutPreview);
        layoutPreview.setVisibility(View.GONE);
        layoutPreviewScreen = (RelativeLayout)findViewById(R.id.layoutPreviewScreen);
        layoutPreviewScreen.setVisibility(View.GONE);
        headerText = (TextView)findViewById(R.id.textViewAddNewHeader);
        buttonNext = (Button)findViewById(R.id.buttonCashNext);
        buttonBack = (Button)findViewById(R.id.buttonCashBack);
        buttonBack.setText("EXIT");
       // buttonBack.setBackgroundResource(R.drawable.my_button_flat_disable);
        previewImageButton = (ImageButton)findViewById(R.id.imageButtonPreview);
        cancelPreviewButton = (ImageView)findViewById(R.id.imageViewCancel);

        Form1Model form1Model = new Form1Model();
        try
        {
            Log.d("CashAddNewBeneficiary", form1Model.getBeneficiary_name() );
        }
        catch (Exception e)
        {

        }


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                viewPager.setCurrentItem(position, true);
                buttonBack.setText("BACK");
                layoutPreviewScreen.setVisibility(View.GONE);

                if(buttonNext.getText().toString().equalsIgnoreCase("SAVE")||buttonNext.getText().toString().equalsIgnoreCase("UPDATE")) {
                    String [] personalArray = mPersonalData.split("#");
                    String [] pregnancyArray = mPregnancyData.split("#");
                    String lmpDate = pregnancyArray[0].trim();
                    String lastAnc = pregnancyArray[1].trim();
                    String aadharCheck = personalArray[11].trim();
                    if(aadharCheck.equalsIgnoreCase("")|| aadharCheck.length()!=12)
                    {
                        viewPager.setCurrentItem(0);
                        position = 0;
                        layoutPreview.setVisibility(View.GONE);
                        Toast.makeText(CashAddNewBenefeciaryActivity.this, "Please Enter Valid Aadhar Number", Toast.LENGTH_SHORT).show();
                    } else if(lmpDate.equalsIgnoreCase(""))
                    {
                        viewPager.setCurrentItem(2);
                        position=2;
                        layoutPreview.setVisibility(View.GONE);
                        Toast.makeText(CashAddNewBenefeciaryActivity.this, "Last Menstrual Period can't be empty", Toast.LENGTH_SHORT).show();

                    } else if(lastAnc.equalsIgnoreCase(""))
                    {
                        viewPager.setCurrentItem(2);
                        position=2;
                        layoutPreview.setVisibility(View.GONE);
                        Toast.makeText(CashAddNewBenefeciaryActivity.this, "Last ANC Field can't be empty", Toast.LENGTH_SHORT).show();

                    } else
                    {
                        showCashSubmitDialog();
                    }

                }

                if(position>=3) {
                    position = 3;
                    layoutPreview.setVisibility(View.VISIBLE);
                    if(isEditable == true)
                    {
                        buttonNext.setText("UPDATE");
                    }
                    else
                    {
                        buttonNext.setText("SAVE");

                    }

                }
                else{
                    layoutPreview.setVisibility(View.GONE);
                    buttonNext.setText("NEXT");
                }

                titleText(position);
            }
        });


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position--;
                viewPager.setCurrentItem(position, true);
                layoutPreviewScreen.setVisibility(View.GONE);
               // Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                if(position<0) {
                    position = 0;
                    onBackPressed();
                }

                if(position == 0) {
                    buttonBack.setText("EXIT");
                }



                if(position>=3) {
                    layoutPreview.setVisibility(View.VISIBLE);
                    if(isEditable == true)
                    {
                        buttonNext.setText("UPDATE");
                    }
                    else
                    {
                        buttonNext.setText("SAVE");

                    }
                }
                else{
                    buttonNext.setText("NEXT");
                    layoutPreview.setVisibility(View.GONE);
                }

                titleText(position);
            }
        });

        fabPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutPreview.setVisibility(View.GONE);
                layoutPreviewScreen.setVisibility(View.VISIBLE);
                setupPreviewScreen();
            }
        });


        cancelPreviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutPreview.setVisibility(View.VISIBLE);
                layoutPreviewScreen.setVisibility(View.GONE);
            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        id = preferences.getString("beneficiary_id", "");


        if(this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("edit_form_1"))
        {

            //Tell fragments we are in edit form
            SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferencesJSON.edit();
            editor.putString("in_edit_form_1","in_edit_form_1");
            editor.apply();

            isEditable = true;


        } else
        {
            //Toast.makeText(this, "Add new form", Toast.LENGTH_SHORT).show();
            //Tell fragments we are not in edit form
            SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferencesJSON.edit();
            editor.putString("in_edit_form_1","not_in_edit_form_1");
            editor.apply();

            isEditable = false;
        }


    }


    private void changeStatusBarColor(){
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getWindow();
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }

    private void setupStepView() {

        stepView = (StepView)findViewById(R.id.step_view) ;
        stepView.setSteps(new ArrayList<String>() {
            {
                add("Personal\nDetails");
                add("Bank Account\nDetails");
                add("Pregnancy\nDetails");
                add("Preview\nForm");
            }});
    }


    private void setupPreviewScreen() {

          setupPersonaltextView();
          setupBankPreview();
          setupPregnancyPreview();

    }


    @SuppressLint("ResourceType")
    private void setupPersonaltextView() {
        String [] pArray = mPersonalData.split("#");
        Log.d("Array", String.valueOf(pArray.length));

        String colorText = "#"+getResources().getString(R.color.colorPText).substring(3);

            for(int i =0; i<=pArray.length; i++) {
                String text = getResources().getString(R.string.p1) + "<b><h3><font color='"+colorText+"'>" + pArray[0] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p2) + "<b><h3><font color='"+colorText+"'>" + pArray[1] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p3) + "<b><h3><font color='"+colorText+"'>" + pArray[2] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p4) + "<b><h3><font color='"+colorText+"'>" + pArray[3] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p5) + "<b><h3><font color='"+colorText+"'>" + pArray[4] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p6) + "<b><h3><font color='"+colorText+"'>" + pArray[5] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p7) + "<b><h3><font color='"+colorText+"'>" + pArray[6] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p8) + "<b><h3><font color='"+colorText+"'>" + pArray[7] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p9) + "<b><h3><font color='"+colorText+"'>" + pArray[8] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p12)+ "<b><h3><font color='"+colorText+"'>" + pArray[11] + "</font></h3></b><br>"
                        + getResources().getString(R.string.p13)+ "<b><h3><font color='"+colorText+"'>" + pArray[12] + "</font></h3></b><br>";

                textViewPersonal.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

            }
    }

    @SuppressLint("ResourceType")
    private void setupBankPreview ()
    {
        String [] pArray = mBankAccountData.split("#");
        Log.d("Array", String.valueOf(pArray.length));

            String colorText = "#"+getResources().getString(R.color.colorPText).substring(3);
            String text = getResources().getString(R.string.b1) + "<b><h3><font color='"+colorText+"'>"+ pArray[0] + "</font></h3></b><br>"
                    + getResources().getString(R.string.b2) + "<b><h3><font color='"+colorText+"'>"+ pArray[1] + "</font></h3></b><br>"
                    + getResources().getString(R.string.b3) + "<b><h3><font color='"+colorText+"'>"+ pArray[2] + "</font></h3></b><br>"
                    + getResources().getString(R.string.b4) + "<b><h3><font color='"+colorText+"'>"+ pArray[3] + "</font></h3></b><br>"
                    + getResources().getString(R.string.b5) + "<b><h3><font color='"+colorText+"'>"+ pArray[4] + "</font></h3></b><br>"
                    + getResources().getString(R.string.b6) + "<b><h3><font color='"+colorText+"'>"+ pArray[5] + "</font></h3></b><br>";

            Log.d("colorhex", getResources().getString(R.color.colorPText).substring(1,3));

            textViewBank.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }

    @SuppressLint("ResourceType")
    private void setupPregnancyPreview() {
        String [] pArray = mPregnancyData.split("#");
        Log.d("Array", String.valueOf(pArray.length));

        String colorText = "#"+getResources().getString(R.color.colorPText).substring(3);
        String text = getResources().getText(R.string.pg1)+ "<b><h3><font color='"+colorText+"'>" + pArray[0] + "</font></h3></b><br>"
                + getResources().getText(R.string.pg2_2)  + "<b><h3><font color='"+colorText+"'>" + pArray[1] + "</font></h3></b><br>"
                + getResources().getText(R.string.pg3) + "<b><h3><font color='"+colorText+"'>" + pArray[2] + "</font></h3></b><br>"
                + getResources().getText(R.string.pg4) + "<b><h3><font color='"+colorText+"'>" + pArray[3] + "</font></h3></b><br>"
                + getResources().getText(R.string.pg5) + "<b><h3><font color='"+colorText+"'>" + pArray[4] + "</font></h3></b><br>";

        textViewPregnancy.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }

    private void titleText(int position) {
        stepView.go(position, true);
        switch(position)
        {
            case 0:
                headerText.setText("Personal Details");
                break;
            case 1:
                headerText.setText("Bank Account Details");
                break;
            case 2:
                headerText.setText("Pregnancy Details");
                break;
            case 3:
                headerText.setText("Preview Form");
                break;
        }

    }


    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentPersonal = new FragmentPersonal();
        fragmentBankAccount = new FragmentBankAccount();
        fragmentPregnancy = new FragmentPregnancy();
        fragmentPreview = new FragmentPreview();
        adapter.addFragment(fragmentPersonal, "");
        adapter.addFragment(fragmentBankAccount, "");
        adapter.addFragment(fragmentPregnancy, "");
        adapter.addFragment(fragmentPreview, "");
        viewPager.setAdapter(adapter);


    }

    @Override
    public void updatePersonal(String mData)
    {
        mPersonalData = mData;
//        String [] pArray = mData.split(",");
//        for(int i =0; i<=pArray.length; i++){
//            Toast.makeText(this, pArray[i], Toast.LENGTH_SHORT).show();
//        }
        Log.d("callbackPersonal", mPersonalData);
       //Toast.makeText(this, mData, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateBankAccount(String mData) {
        mBankAccountData = mData;
        Log.d("callbackBankAccount", mBankAccountData);

    }

    @Override
    public void updatePregnancy(String mData) {
        mPregnancyData = mData;
        Log.d("callbackPregnancy", mPregnancyData);
    }


    private void pushDatatoServer()
    {
        String [] personalArray = mPersonalData.split("#");
        String namebenficiary = personalArray[0].trim();
        String husband = personalArray[1].trim();
        String age = personalArray[2].trim();
        String mobileNumber = personalArray[3].trim();
        String dateOfRegistration = personalArray[4].trim();
        String bhamashahNumber = personalArray[5].trim();
        String livingChilderen = personalArray[6].trim();
        String caste = personalArray[7].trim();
        String bpl = personalArray[8].trim();
        String dateStamp = personalArray[9].trim();
        String timeStamp = personalArray[10].trim();
        String aadhar = personalArray[11].trim();
        String aadharEnroll = personalArray[12].trim();


        String [] bankArray = mBankAccountData.split("#");
        String bankAccount = bankArray[0].trim();
        String nameOfAccountHolder = bankArray[1].trim();
        String nameOfBank = bankArray[2].trim();
        String branchName = bankArray[3].trim();
        String accountNumber = bankArray[4].trim();
        String ifscCode = bankArray[5].trim();


        String [] pregnancyArray = mPregnancyData.split("#");
        String lmpDate = pregnancyArray[0].trim();
        DateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
        Date lmpdate = null;
        try {
            lmpdate = (Date)dateFormat.parse(lmpDate) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String lastAncMamta = pregnancyArray[1].trim();
        DateFormat dateFormat2 =  new SimpleDateFormat("dd/MM/yyyy");
        Date ancdate = null;
        try {
            ancdate = (Date)dateFormat2.parse(lastAncMamta) ;
        } catch (ParseException e) {
            e.printStackTrace();
        }



        String weight = pregnancyArray[2].trim();
        String height = pregnancyArray[3].trim();
        String placeOfANC = pregnancyArray[4].trim();



        JSONObject jsonRootForm = new JSONObject();
        JSONObject jsonForms = new JSONObject();


        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mDistrict = preferences2.getString("district", "Err");
        String mProject = preferences2.getString("project", "Err");
        String mAaganwadi = preferences2.getString("anganwadiCenter", "Err");

        JSONObject jsonForm1 = new JSONObject();
        try {

            /** Form1 **/

            JSONObject jsonSection1 = new JSONObject();
            jsonSection1.put("project",mProject);
            jsonSection1.put("sector", "");
            jsonSection1.put("village", "");
            jsonSection1.put("district", mDistrict);
            jsonSection1.put("anganwadiCenter", mAaganwadi);
            jsonSection1.put("dateOfRegistration", dateOfRegistration);
            jsonSection1.put("beneficiaryName", namebenficiary);
            jsonSection1.put("age", age);
            jsonSection1.put("noOfLivingChildren", livingChilderen);
            jsonSection1.put("caste", caste);
            jsonSection1.put("isBPL", bpl);
            jsonSection1.put("mobileNo", mobileNumber);
            jsonSection1.put("bhamashahNo", bhamashahNumber);
           // jsonSection1.put("date_time_stamp", dateStamp+"# "+timeStamp);


            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", husband);
            jsonBeneficiaryGuardian.put("relation", "Husband");

            JSONObject jsonAadhar = new JSONObject();
            jsonAadhar.put("aadharCardNo", aadhar);
            jsonAadhar.put("aadharEnrollmentNo", aadharEnroll);


            jsonSection1.put("beneficiaryGuardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAadhar);


            JSONObject jsonSection2 = new JSONObject();
           // jsonSection2.put("owns_bank_account", bankAccount);
            jsonSection2.put("bankAccountHolderName", nameOfAccountHolder);
            jsonSection2.put("bankName", nameOfBank);
            jsonSection2.put("bankBranchName", branchName);
            jsonSection2.put("bankAccountNumber", accountNumber);
            jsonSection2.put("bankIFSCCode", ifscCode);


            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("lmpDate", lmpdate.getTime());

            JSONObject jsonANCDetail = new JSONObject();
            jsonANCDetail.put("lastANCDate", ancdate.getTime());
            jsonANCDetail.put("weight", weight);
            jsonANCDetail.put("height", height);
            jsonANCDetail.put("placeOfANC", placeOfANC);
            jsonSection3.put("anc", jsonANCDetail);

//            JSONObject jsonOtherDetails = new JSONObject();
//            jsonOtherDetails.put("aww_name", "");
//            jsonOtherDetails.put("aww_username", "");
//            jsonOtherDetails.put("form_received_date", "");
//            jsonOtherDetails.put("data_entry_date", "");


          //  JSONObject jsonForm1 = new JSONObject();
            jsonForm1.put("section1", jsonSection1);
            jsonForm1.put("section2", jsonSection2);
            jsonForm1.put("section3", jsonSection3);
         //   jsonForm1.put("otherDetails", jsonOtherDetails);

       //     jsonForms.put("form1", jsonForm1);

            /**Form1 End **/


        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Form2 **/

//
//        try {
//            JSONObject jsonForm2 = new JSONObject();
//
//            JSONObject jsonSection1 = new JSONObject();
//
//            jsonSection1.put("beneficiary_name", "");
//            jsonSection1.put("mobile_number", "");
//
//            JSONObject jsonBeneficiaryGuardian = new JSONObject();
//            jsonBeneficiaryGuardian.put("name", "");
//            jsonBeneficiaryGuardian.put("relation", "");
//
//            JSONObject jsonAdhar = new JSONObject();
//            jsonAdhar.put("aadhar_card_number", "");
//            jsonAdhar.put("aadhar_enrolment_number", "");
//
//            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
//            jsonSection1.put("aadhar", jsonAdhar);
//
//
//            JSONObject jsonSection2 = new JSONObject();
//            JSONObject jsonANCDetails = new JSONObject();
//            jsonANCDetails.put("anc_completed", "");
//            jsonANCDetails.put("last_anc_date", "");
//            jsonANCDetails.put("weight", "");
//            jsonSection2.put("anc_details", jsonANCDetails);
//
//            JSONObject jsonSection3 = new JSONObject();
//            jsonSection3.put("non_compliance_reason", "");
//
//
//            JSONObject jsonOtherDetails = new JSONObject();
//            jsonOtherDetails.put("aww_name", "");
//            jsonOtherDetails.put("aww_username", "");
//            jsonOtherDetails.put("form_received_date", "");
//            jsonOtherDetails.put("data_entry_date", "");
//
//            jsonForm2.put("section_1", jsonSection1);
//            jsonForm2.put("section_2", jsonSection2);
//            jsonForm2.put("section_3", jsonSection3);
//            jsonForm2.put("other_details", jsonOtherDetails);
//
//
//            jsonForms.put("form_2", jsonForm2);
//
//
//
//
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        /** Form2 End **/
//
//        /** FOrm3 **/
//
//        try {
//            JSONObject jsonForm3 = new JSONObject();
//
//            JSONObject jsonSection1 = new JSONObject();
//
//            jsonSection1.put("beneficiary_name", "");
//            jsonSection1.put("mobile_number", "");
//
//            JSONObject jsonBeneficiaryGuardian = new JSONObject();
//            jsonBeneficiaryGuardian.put("name", "");
//            jsonBeneficiaryGuardian.put("relation", "");
//
//            JSONObject jsonAdhar = new JSONObject();
//            jsonAdhar.put("aadhar_card_number", "");
//            jsonAdhar.put("aadhar_enrolment_number", "");
//
//            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
//            jsonSection1.put("aadhar", jsonAdhar);
//
//
//            JSONObject jsonSection2 = new JSONObject();
//            JSONObject jsonANCDetails = new JSONObject();
//            jsonANCDetails.put("anc_completed", "");
//            jsonANCDetails.put("last_anc_date", "");
//            jsonANCDetails.put("weight", "");
//            jsonSection2.put("anc_details", jsonANCDetails);
//
//            JSONObject jsonSection3 = new JSONObject();
//            jsonSection3.put("non_compliance_reason", "");
//
//
//            JSONObject jsonOtherDetails = new JSONObject();
//            jsonOtherDetails.put("aww_name", "");
//            jsonOtherDetails.put("aww_username", "");
//            jsonOtherDetails.put("form_received_date", "");
//            jsonOtherDetails.put("data_entry_date", "");
//
//            jsonForm3.put("section_1", jsonSection1);
//            jsonForm3.put("section_2", jsonSection2);
//            jsonForm3.put("section_3", jsonSection3);
//            jsonForm3.put("other_details", jsonOtherDetails);
//
//
//            jsonForms.put("form_3", jsonForm3);
//
//
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        /** Form3 end **/
//
//        /** Form4 **/
//
//
//        try {
//            JSONObject jsonForm4 = new JSONObject();
//
//            JSONObject jsonSection1 = new JSONObject();
//            jsonSection1.put("beneficiary_name", "");
//
//            jsonSection1.put("mobile_number", "");
//
//            JSONObject jsonBeneficiaryGuardian = new JSONObject();
//            jsonBeneficiaryGuardian.put("name", "");
//            jsonBeneficiaryGuardian.put("relation", "");
//
//            JSONObject jsonAdhar = new JSONObject();
//            jsonAdhar.put("aadhar_card_number", "");
//            jsonAdhar.put("aadhar_enrolment_number", "");
//
//            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
//            jsonSection1.put("aadhar", jsonAdhar);
//
//
//            JSONObject jsonSection2 = new JSONObject();
//            jsonSection2.put("delivery_date", "");
//            jsonSection2.put("is_child_alive", "");
//            jsonSection2.put("child_gender", "");
//            jsonSection2.put("birth_weight", "");
//
//
//            JSONObject jsonSection3 = new JSONObject();
//            jsonSection3.put("non_compliance_reason", "");
//
//            JSONObject jsonOtherdetails = new JSONObject();
//            jsonOtherdetails.put("aww_name", "");
//            jsonOtherdetails.put("aww_username", "");
//            jsonOtherdetails.put("form_received_date", "");
//            jsonOtherdetails.put("data_entry_date", "");
//
//
//            jsonForm4.put("section_1", jsonSection1);
//            jsonForm4.put("section_2", jsonSection2);
//            jsonForm4.put("section_3", jsonSection3);
//            jsonForm4.put("other_details", jsonOtherdetails);
//
//            jsonForms.put("form_4", jsonForm4);
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        /** Form4 End **/
//
//
//        try {
//
//            JSONObject jsonForm5 = new JSONObject();
//
//            JSONObject jsonSection1 = new JSONObject();
//            jsonSection1.put("beneficiary_name", "");
//
//            jsonSection1.put("mobile_number", "");
//
//            JSONObject jsonBeneficiaryGuardian = new JSONObject();
//            jsonBeneficiaryGuardian.put("name", "");
//            jsonBeneficiaryGuardian.put("relation", "");
//
//            JSONObject jsonAdhar = new JSONObject();
//            jsonAdhar.put("aadhar_card_number", "");
//            jsonAdhar.put("aadhar_enrolment_number", "");
//
//            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
//            jsonSection1.put("aadhar", jsonAdhar);
//
//
//            JSONObject jsonSection2 = new JSONObject();
//            jsonSection2.put("is_child_alive", "");
//            jsonSection2.put("child_weight", "");
//
//
//            JSONObject jsonVaccinaations = new JSONObject();
//            jsonVaccinaations.put("bcg", "");
//            jsonVaccinaations.put("hepatitis_b", "");
//            jsonVaccinaations.put("opv", "");
//            jsonVaccinaations.put("pentavalent", "");
//
//            jsonSection2.put("vaccinations", jsonVaccinaations);
//
//            JSONObject jsonSection3 = new JSONObject();
//            jsonSection3.put("non_compliance_reason", "");
//
//            JSONObject jsonOtherdetails = new JSONObject();
//            jsonOtherdetails.put("aww_name", "");
//            jsonOtherdetails.put("aww_username", "");
//            jsonOtherdetails.put("form_received_date", "");
//            jsonOtherdetails.put("data_entry_date", "");
//
//
//            jsonForm5.put("section_1", jsonSection1);
//            jsonForm5.put("section_2", jsonSection2);
//            jsonForm5.put("section_3", jsonSection3);
//            jsonForm5.put("other_details", jsonOtherdetails);
//
//
//            jsonForms.put("form_5", jsonForm5);
//
//
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        /** Installemt 1 **/
//
//        try {
//
//            JSONObject jsonInstallment1 = new JSONObject();
//            jsonInstallment1.put("status", "");
//            jsonInstallment1.put("approvedBySectorCoordinator", "");
//            jsonInstallment1.put("approvedByBIDM", "");
//            jsonInstallment1.put("approvedByCDPO", "");
//            jsonInstallment1.put("approvedByDIDM", "");
//            jsonInstallment1.put("approvedByDD", "");
//            jsonInstallment1.put("approvedByBank", "");
//
//
//            JSONObject jsonInstallment2 = new JSONObject();
//            jsonInstallment2.put("status", "");
//            jsonInstallment2.put("approvedBySectorCoordinator", "");
//            jsonInstallment2.put("approvedByBIDM", "");
//            jsonInstallment2.put("approvedByCDPO", "");
//            jsonInstallment2.put("approvedByDIDM", "");
//            jsonInstallment2.put("approvedByDD", "");
//            jsonInstallment2.put("approvedByBank", "");
//
//            JSONObject jsonInstallment3 = new JSONObject();
//            jsonInstallment3.put("status", "");
//            jsonInstallment3.put("approvedBySectorCoordinator", "");
//            jsonInstallment3.put("approvedByBIDM", "");
//            jsonInstallment3.put("approvedByCDPO", "");
//            jsonInstallment3.put("approvedByDIDM", "");
//            jsonInstallment3.put("approvedByDD", "");
//            jsonInstallment3.put("approvedByBank", "");
//
//            JSONObject jsonInstallment4 = new JSONObject();
//            jsonInstallment4.put("status", "");
//            jsonInstallment4.put("approvedBySectorCoordinator", "");
//            jsonInstallment4.put("approvedByBIDM", "");
//            jsonInstallment4.put("approvedByCDPO", "");
//            jsonInstallment4.put("approvedByDIDM", "");
//            jsonInstallment4.put("approvedByDD", "");
//            jsonInstallment4.put("approvedByBank", "");
//
//            JSONObject jsonInstallment5 = new JSONObject();
//            jsonInstallment5.put("status", "");
//            jsonInstallment5.put("approvedBySectorCoordinator", "");
//            jsonInstallment5.put("approvedByBIDM", "");
//            jsonInstallment5.put("approvedByCDPO", "");
//            jsonInstallment5.put("approvedByDIDM", "");
//            jsonInstallment5.put("approvedByDD", "");
//            jsonInstallment5.put("approvedByBank", "");
//
//
//            JSONObject jsonInstallmentStatus = new JSONObject();
//            jsonInstallmentStatus.put("current_installment", "");
//            jsonInstallmentStatus.put("installment_1", jsonInstallment1);
//            jsonInstallmentStatus.put("installment_2", jsonInstallment2);
//            jsonInstallmentStatus.put("installment_3", jsonInstallment3);
//            jsonInstallmentStatus.put("installment_4", jsonInstallment4);
//            jsonInstallmentStatus.put("installment_5", jsonInstallment5);
//
//            jsonForms.put("installment_status", jsonInstallmentStatus);
//
//
//            jsonRootForm.put("forms", jsonForms);
//
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//


        Log.e("jsonForm", jsonForm1.toString());

       // pushDataJson = jsonRootForm.toString();


        pushDataJson = jsonForm1.toString();
        new PushCashAsync().execute();



    }


    public class PushCashAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashAddNewBenefeciaryActivity.this);
            progressDialog.setMessage("Data Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            String json = null;


            try
            {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = null;
                
                if(isEditable == false) {
                    httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/addBeneficiary?formNumber=1");
                }else if(isEditable ==true)
                    {
                        httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/editBeneficiary?formNo=1");

                    }



                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer "+ mtoken);

                httpPost.setEntity(new StringEntity(pushDataJson, "UTF-8"));
                response = httpClient.execute(httpPost);

                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());


                Log.d("pushResponse", value);

                
            }
            catch (Exception e)
            {

            }

            return value;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            JSONObject jsonObj = null;
            boolean isSuccessful = false;
            try {
                jsonObj = new JSONObject(value);
                isSuccessful = jsonObj.getBoolean("success");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            
            if (isSuccessful == true)
            {
                Toast.makeText(CashAddNewBenefeciaryActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(CashAddNewBenefeciaryActivity.this, "Failed to Submit", Toast.LENGTH_SHORT).show();
            }

        }
    }


    //edit Form1
    private void pushUpdatedDatatoServer()
    {
        String [] personalArray = mPersonalData.split("#");
//        String namebenficiary = personalArray[0].trim();
//        String husband = personalArray[1].trim();
//        String age = personalArray[2].trim();
//        String mobileNumber = personalArray[3].trim();
//        String dateOfRegistration = personalArray[4].trim();
//        String bhamashahNumber = personalArray[5].trim();
//        String livingChilderen = personalArray[6].trim();
//        String caste = personalArray[7].trim();
//        String bpl = personalArray[8].trim();
//        String dateStamp = personalArray[9].trim();
//        String timeStamp = personalArray[10].trim();
//        String aadhar = personalArray[11].trim();
//        String aadharEnroll = personalArray[12].trim();

        Form1Model form1Model = new Form1Model();
        form1Model.setBeneficiary_name(personalArray[0]);
        form1Model.setName(personalArray[1]);
        form1Model.setAge(personalArray[3]);
        form1Model.setMobile_number(personalArray[4]);
        form1Model.setDate_of_registtration(personalArray[5]);
        form1Model.setBhamashah_number(personalArray[6]);
        form1Model.setLiving_children_count(personalArray[7]);
        form1Model.setCaste(personalArray[8]);
        form1Model.setIs_bpl(personalArray[9]);
        form1Model.setDate_time_stamp(personalArray[10]+"#"+personalArray[11]);
        form1Model.setAadhar_card_number(personalArray[12]);
        form1Model.setAadhar_enrolment_number(personalArray[13]);



        String [] bankArray = mBankAccountData.split("#");
//        String bankAccount = bankArray[0].trim();
//        String nameOfAccountHolder = bankArray[1].trim();
//        String nameOfBank = bankArray[2].trim();
//        String branchName = bankArray[3].trim();
//        String accountNumber = bankArray[4].trim();
//        String ifscCode = bankArray[5].trim();

        form1Model.setOwns_bank_account(bankArray[0]);
        form1Model.setAccount_holder_name(bankArray[1]);
        form1Model.setBank_name(bankArray[2]);
        form1Model.setBranch_name(bankArray[3]);
        form1Model.setBank_account_number(bankArray[4]);
        form1Model.setIfsc_code(bankArray[5]);


        String [] pregnancyArray = mPregnancyData.split("#");
//        String lmpDate = pregnancyArray[0].trim();
//        String lastAncMamta = pregnancyArray[1].trim();
//        String weight = pregnancyArray[2].trim();
//        String height = pregnancyArray[3].trim();
//        String placeOfANC = pregnancyArray[4].trim();

        form1Model.setLast_menstrual_period(pregnancyArray[0]);
        form1Model.setLast_anc_detail(pregnancyArray[1]);
        form1Model.setWeight(pregnancyArray[2]);
        form1Model.setHeight(pregnancyArray[3]);
        form1Model.setPlace_of_anc(pregnancyArray[4]);



        JSONObject jsonRootForm = new JSONObject();
        JSONObject jsonForms = new JSONObject();


        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mDistrict = preferences2.getString("district", "Err");
        String mProject = preferences2.getString("project", "Err");

      //  Form1Model form1Model = new Form1Model();

        try {

            /** Form1 **/



            JSONObject jsonSection1 = new JSONObject();
            jsonSection1.put("project",mProject);
            jsonSection1.put("sector", form1Model.getSector());
            jsonSection1.put("village", form1Model.getVillage());
            jsonSection1.put("district", mDistrict);
            jsonSection1.put("anganwadi_center", form1Model.getAnganwadi_center());
            jsonSection1.put("date_of_registration", form1Model.getDate_of_registtration());
            jsonSection1.put("beneficiary_name", form1Model.getBeneficiary_name());
            jsonSection1.put("age", form1Model.getAge());
            jsonSection1.put("living_children_count", form1Model.getLiving_children_count());
            jsonSection1.put("caste", form1Model.getCaste());
            jsonSection1.put("is_bpl", form1Model.getIs_bpl());
            jsonSection1.put("mobile_number", form1Model.getMobile_number());
            jsonSection1.put("bhamashah_number", form1Model.getBhamashah_number());
            jsonSection1.put("date_time_stamp", form1Model.getDate_time_stamp());


            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", form1Model.getName());
            jsonBeneficiaryGuardian.put("relation", form1Model.getRelation());

            JSONObject jsonAadhar = new JSONObject();
            jsonAadhar.put("aadhar_card_number", form1Model.getAadhar_card_number());
            jsonAadhar.put("aadhar_enrolment_number", form1Model.getAadhar_enrolment_number());


            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAadhar);


            JSONObject jsonSection2 = new JSONObject();
            jsonSection2.put("owns_bank_account", form1Model.getOwns_bank_account());
            jsonSection2.put("account_holder_name", form1Model.getAccount_holder_name());
            jsonSection2.put("bank_name", form1Model.getBank_name());
            jsonSection2.put("branch_name", form1Model.getBranch_name());
            jsonSection2.put("bank_aacount_number", form1Model.getBank_account_number());
            jsonSection2.put("ifsc_code", form1Model.getIfsc_code());


            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("last_menstrual_period", form1Model.getLast_menstrual_period());

            JSONObject jsonANCDetail = new JSONObject();
            jsonANCDetail.put("last_anc_detail", form1Model.getLast_anc_detail());
            jsonANCDetail.put("weight", form1Model.getWeight());
            jsonANCDetail.put("height", form1Model.getHeight());
            jsonANCDetail.put("place_of_anc", form1Model.getPlace_of_anc());
            jsonSection3.put("anc_details", jsonANCDetail);

            JSONObject jsonOtherDetails = new JSONObject();
            jsonOtherDetails.put("aww_name", form1Model.getAww_name());
            jsonOtherDetails.put("aww_username", form1Model.getAww_username());
            jsonOtherDetails.put("form_received_date", form1Model.getForm_received_date());
            jsonOtherDetails.put("data_entry_date", form1Model.getData_entry_date());


            JSONObject jsonForm1 = new JSONObject();
            jsonForm1.put("section_1", jsonSection1);
            jsonForm1.put("section_2", jsonSection2);
            jsonForm1.put("section_3", jsonSection3);
            jsonForm1.put("other_details", jsonOtherDetails);

            jsonForms.put("form_1", jsonForm1);

            /**Form1 End **/


        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Form2 **/
        Form2Model form2Model = new Form2Model();

        try {
            JSONObject jsonForm2 = new JSONObject();

            JSONObject jsonSection1 = new JSONObject();

            jsonSection1.put("beneficiary_name", form2Model.getBeneficiary_name());
            jsonSection1.put("mobile_number", form2Model.getMobile_number());

            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", form2Model.getName());
            jsonBeneficiaryGuardian.put("relation", form2Model.getRelation());

            JSONObject jsonAdhar = new JSONObject();
            jsonAdhar.put("aadhar_card_number", form2Model.getAadhar_card_number());
            jsonAdhar.put("aadhar_enrolment_number", form2Model.getAadhar_enrolment_number());

            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAdhar);


            JSONObject jsonSection2 = new JSONObject();
            JSONObject jsonANCDetails = new JSONObject();
            jsonANCDetails.put("anc_completed", form2Model.getAnc_completed());
            jsonANCDetails.put("last_anc_date", form2Model.getLast_anc_date());
            jsonANCDetails.put("weight", form2Model.getWeight());
            jsonSection2.put("anc_details", jsonANCDetails);

            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("non_compliance_reason", form2Model.getNon_compliance_reason());


            JSONObject jsonOtherDetails = new JSONObject();
            jsonOtherDetails.put("aww_name", form1Model.getAww_name());
            jsonOtherDetails.put("aww_username", form2Model.getAww_username());
            jsonOtherDetails.put("form_received_date", form2Model.getForm_received_date());
            jsonOtherDetails.put("data_entry_date", form2Model.getData_entry_date());

            jsonForm2.put("section_1", jsonSection1);
            jsonForm2.put("section_2", jsonSection2);
            jsonForm2.put("section_3", jsonSection3);
            jsonForm2.put("other_details", jsonOtherDetails);


            jsonForms.put("form_2", jsonForm2);






        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Form2 End **/

        /** FOrm3 **/


        Form3Model form3Model = new Form3Model();

        try {
            JSONObject jsonForm3 = new JSONObject();

            JSONObject jsonSection1 = new JSONObject();

            jsonSection1.put("beneficiary_name", form3Model.getBeneficiary_name());
            jsonSection1.put("mobile_number", form3Model.getMobile_nummber());

            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", form3Model.getName());
            jsonBeneficiaryGuardian.put("relation", form3Model.getRelation());

            JSONObject jsonAdhar = new JSONObject();
            jsonAdhar.put("aadhar_card_number", form3Model.getAadhar_card_number());
            jsonAdhar.put("aadhar_enrolment_number", form3Model.getAadhar_enrolment_number());

            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAdhar);


            JSONObject jsonSection2 = new JSONObject();
            JSONObject jsonANCDetails = new JSONObject();
            jsonANCDetails.put("anc_completed", form3Model.getAnc_completed());
            jsonANCDetails.put("last_anc_date", form3Model.getLast_anc_date());
            jsonANCDetails.put("weight", form3Model.getWeight());
            jsonSection2.put("anc_details", jsonANCDetails);

            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("non_compliance_reason", form3Model.getNon_compliance_reason());


            JSONObject jsonOtherDetails = new JSONObject();
            jsonOtherDetails.put("aww_name", form3Model.getAww_name());
            jsonOtherDetails.put("aww_username", form3Model.getAww_username());
            jsonOtherDetails.put("form_received_date", form3Model.getForm_received_date());
            jsonOtherDetails.put("data_entry_date", form3Model.getForm_received_date());

            jsonForm3.put("section_1", jsonSection1);
            jsonForm3.put("section_2", jsonSection2);
            jsonForm3.put("section_3", jsonSection3);
            jsonForm3.put("other_details", jsonOtherDetails);


            jsonForms.put("form_3", jsonForm3);






        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Form3 end **/

        /** Form4 **/

        Form4Model form4Model = new Form4Model();

        try {
            JSONObject jsonForm4 = new JSONObject();

            JSONObject jsonSection1 = new JSONObject();
            jsonSection1.put("beneficiary_name", form4Model.getBeneficiary_name());

            jsonSection1.put("mobile_number", form4Model.getMobile_nummber());

            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", form4Model.getName());
            jsonBeneficiaryGuardian.put("relation", form4Model.getRelation());

            JSONObject jsonAdhar = new JSONObject();
            jsonAdhar.put("aadhar_card_number", form4Model.getAadhar_card_number());
            jsonAdhar.put("aadhar_enrolment_number", form4Model.getAadhar_enrolment_number());

            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAdhar);


            JSONObject jsonSection2 = new JSONObject();
            jsonSection2.put("delivery_date", form4Model.getDelivery_date());
            jsonSection2.put("is_child_alive", form4Model.getIs_child_alive());
            jsonSection2.put("child_gender", form4Model.getChild_gender());
            jsonSection2.put("birth_weight", form4Model.getBirth_weight());


            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("non_compliance_reason", form4Model.getNon_compliance_reason());

            JSONObject jsonOtherdetails = new JSONObject();
            jsonOtherdetails.put("aww_name", form4Model.getAww_name());
            jsonOtherdetails.put("aww_username", form4Model.getAww_username());
            jsonOtherdetails.put("form_received_date", form4Model.getForm_received_date());
            jsonOtherdetails.put("data_entry_date", form4Model.getData_entry_date());


            jsonForm4.put("section_1", jsonSection1);
            jsonForm4.put("section_2", jsonSection2);
            jsonForm4.put("section_3", jsonSection3);
            jsonForm4.put("other_details", jsonOtherdetails);

            jsonForms.put("form_4", jsonForm4);




        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Form4 End **/

        /** Form 5 Start **/

        Form5Model form5Model = new Form5Model();

        try {

            JSONObject jsonForm5 = new JSONObject();

            JSONObject jsonSection1 = new JSONObject();
            jsonSection1.put("beneficiary_name", form5Model.getBeneficiary_name());

            jsonSection1.put("mobile_number", form5Model.getMobile_nummber());

            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", form5Model.getName());
            jsonBeneficiaryGuardian.put("relation", form5Model.getRelation());

            JSONObject jsonAdhar = new JSONObject();
            jsonAdhar.put("aadhar_card_number", form5Model.getAadhar_card_number());
            jsonAdhar.put("aadhar_enrolment_number", form5Model.getAadhar_enrolment_number());

            jsonSection1.put("beneficiary_guardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAdhar);


            JSONObject jsonSection2 = new JSONObject();
            jsonSection2.put("is_child_alive", form5Model.getIs_child_alive());
            jsonSection2.put("child_weight", form5Model.getChild_weight());


            JSONObject jsonVaccinaations = new JSONObject();
            jsonVaccinaations.put("bcg", form5Model.getBcg());
            jsonVaccinaations.put("hepatitis_b", form5Model.getHepatitis_b());
            jsonVaccinaations.put("opv", form5Model.getOpv());
            jsonVaccinaations.put("pentavalent", form5Model.getPentavalent());

            jsonSection2.put("vaccinations", jsonVaccinaations);

            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("non_compliance_reason", form5Model.getNon_compliance_reason());

            JSONObject jsonOtherdetails = new JSONObject();
            jsonOtherdetails.put("aww_name", form5Model.getAww_name());
            jsonOtherdetails.put("aww_username", form5Model.getAww_username());
            jsonOtherdetails.put("form_received_date", form5Model.getForm_received_date());
            jsonOtherdetails.put("data_entry_date", form5Model.getData_entry_date());


            jsonForm5.put("section_1", jsonSection1);
            jsonForm5.put("section_2", jsonSection2);
            jsonForm5.put("section_3", jsonSection3);
            jsonForm5.put("other_details", jsonOtherdetails);


            jsonForms.put("form_5", jsonForm5);






        } catch (JSONException e) {
            e.printStackTrace();
        }

        /** Installemt**/



        try {

            Installment1Model installment1Model = new Installment1Model();

            JSONObject jsonInstallment1 = new JSONObject();
            jsonInstallment1.put("status", installment1Model.getStatus());
            jsonInstallment1.put("approvedBySectorCoordinator", installment1Model.getApprovedBySectorCoordinator());
            jsonInstallment1.put("approvedByBIDM", installment1Model.getApprovedByBIDM());
            jsonInstallment1.put("approvedByCDPO", installment1Model.getApprovedByCDPO());
            jsonInstallment1.put("approvedByDIDM", installment1Model.getApprovedByDIDM());
            jsonInstallment1.put("approvedByDD", installment1Model.getApprovedByDD());
            jsonInstallment1.put("approvedByBank", installment1Model.getApprovedByBank());


            Installment2Model installment2Model = new Installment2Model();

            JSONObject jsonInstallment2 = new JSONObject();
            jsonInstallment2.put("status", installment2Model.getStatus());
            jsonInstallment2.put("approvedBySectorCoordinator", installment2Model.getApprovedBySectorCoordinator());
            jsonInstallment2.put("approvedByBIDM", installment2Model.getApprovedByBIDM());
            jsonInstallment2.put("approvedByCDPO", installment2Model.getApprovedByCDPO());
            jsonInstallment2.put("approvedByDIDM", installment2Model.getApprovedByDIDM());
            jsonInstallment2.put("approvedByDD", installment2Model.getApprovedByDD());
            jsonInstallment2.put("approvedByBank", installment2Model.getApprovedByBank());

            Installment3Model installment3Model = new Installment3Model();

            JSONObject jsonInstallment3 = new JSONObject();
            jsonInstallment3.put("status", installment3Model.getStatus());
            jsonInstallment3.put("approvedBySectorCoordinator", installment3Model.getApprovedBySectorCoordinator());
            jsonInstallment3.put("approvedByBIDM", installment3Model.getApprovedByBIDM());
            jsonInstallment3.put("approvedByCDPO", installment3Model.getApprovedByCDPO());
            jsonInstallment3.put("approvedByDIDM", installment3Model.getApprovedByDIDM());
            jsonInstallment3.put("approvedByDD", installment3Model.getApprovedByDD());
            jsonInstallment3.put("approvedByBank", installment3Model.getApprovedByBank());

            Installment4Model installment4Model = new Installment4Model();

            JSONObject jsonInstallment4 = new JSONObject();
            jsonInstallment4.put("status", installment4Model.getStatus());
            jsonInstallment4.put("approvedBySectorCoordinator", installment4Model.getApprovedBySectorCoordinator());
            jsonInstallment4.put("approvedByBIDM", installment4Model.getApprovedByBIDM());
            jsonInstallment4.put("approvedByCDPO", installment4Model.getApprovedByCDPO());
            jsonInstallment4.put("approvedByDIDM", installment4Model.getApprovedByDIDM());
            jsonInstallment4.put("approvedByDD", installment4Model.getApprovedByDD());
            jsonInstallment4.put("approvedByBank", installment4Model.getApprovedByBank());

            Installment5Model installment5Model = new Installment5Model();

            JSONObject jsonInstallment5 = new JSONObject();
            jsonInstallment5.put("status", installment5Model.getStatus());
            jsonInstallment5.put("approvedBySectorCoordinator", installment5Model.getApprovedBySectorCoordinator());
            jsonInstallment5.put("approvedByBIDM", installment5Model.getApprovedByBIDM());
            jsonInstallment5.put("approvedByCDPO", installment5Model.getApprovedByCDPO());
            jsonInstallment5.put("approvedByDIDM", installment5Model.getApprovedByDIDM());
            jsonInstallment5.put("approvedByDD", installment5Model.getApprovedByDD());
            jsonInstallment5.put("approvedByBank", installment5Model.getApprovedByBank());


            JSONObject jsonInstallmentStatus = new JSONObject();
            jsonInstallmentStatus.put("current_installment", installment1Model.getCurrent_installment());
            jsonInstallmentStatus.put("installment_1", jsonInstallment1);
            jsonInstallmentStatus.put("installment_2", jsonInstallment2);
            jsonInstallmentStatus.put("installment_3", jsonInstallment3);
            jsonInstallmentStatus.put("installment_4", jsonInstallment4);
            jsonInstallmentStatus.put("installment_5", jsonInstallment5);

            jsonForms.put("installment_status", jsonInstallmentStatus);


            jsonRootForm.put("forms", jsonForms);

//            JSONArray jsonArray = new JSONArray();
//            jsonArray.put(id);
//            jsonArray.put(jsonRootForm);
//
//            JSONObject jsonFinal = new JSONObject();
//            jsonFinal.put("data", jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("jsonForm", jsonRootForm.toString());

        pushDataJson = jsonRootForm.toString();
        new PushCashUpdateAsync().execute();



    }


    public class PushCashUpdateAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashAddNewBenefeciaryActivity.this);
            progressDialog.setMessage("Data Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            String json = null;


            try
            {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/addBeneficiary?formNumber=1");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer "+ mtoken);

                httpPost.setEntity(new StringEntity(pushDataJson, "UTF-8"));
                response = httpClient.execute(httpPost);

                String sresponse = response.getEntity().toString();
                String value = EntityUtils.toString(response.getEntity());


                Log.d("pushResponse", value);










            }
            catch (Exception e)
            {

            }

            return value;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }




    @Override
    public void onBackPressed() {
        final  Dialog dialogCoupon;
        dialogCoupon = new Dialog(this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.back_dialog);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);

         TextView label = (TextView)dialogCoupon.findViewById(R.id.editCoupon);
         label.setText("Sure to exit, data won't save?");
         label.setTextColor(Color.parseColor("#F43678"));
         label.setTypeface(Typeface.DEFAULT_BOLD);
//        setFinishOnTouchOutside(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        ImageView yes = (ImageView) dialogCoupon.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                dialogCoupon.dismiss();
                finish();
            }
        });

        ImageView no = (ImageView) dialogCoupon.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCoupon.hide();
            }
        });

        dialogCoupon.show();

    }


    private void showCashSubmitDialog() {
        final Dialog dialogCoupon;
        dialogCoupon = new Dialog(this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.back_dialog);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);

        TextView label = (TextView)dialogCoupon.findViewById(R.id.editCoupon);

        if(isEditable == true)
        {
            label.setText("Are you sure to update?");
        }
        else {
            label.setText("Are you sure to save?");
        }


        label.setTextColor(Color.parseColor("#F43678"));
        label.setTypeface(Typeface.DEFAULT_BOLD);
//        setFinishOnTouchOutside(true);
        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        ImageView yes = (ImageView) dialogCoupon.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    pushDatatoServer();
                  //  Toast.makeText(CashAddNewBenefeciaryActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
                    finish();

                dialogCoupon.dismiss();

            }
        });

        ImageView no = (ImageView) dialogCoupon.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCoupon.hide();
            }
        });

        dialogCoupon.show();
    }
}
