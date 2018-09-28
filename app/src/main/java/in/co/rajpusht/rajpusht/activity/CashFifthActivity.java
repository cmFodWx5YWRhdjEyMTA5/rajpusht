package in.co.rajpusht.rajpusht.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuhart.stepview.StepView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.adapter.ViewPagerAdapter;
import in.co.rajpusht.rajpusht.fragment.FragmentCashFifth;
import in.co.rajpusht.rajpusht.fragment.FragmentCashFourth;
import in.co.rajpusht.rajpusht.fragment.FragmentPreview;
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

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

/**
 * Created by LENOVO on 17-Sep-18.
 */

public class CashFifthActivity extends AppCompatActivity
                            implements  FragmentCashFifth.OnCallbackReceivedFifth{
    public static final String TAG = CashFifthActivity.class.getSimpleName();
    private ViewPager viewPager;
    private StepView stepView;

    private RelativeLayout layoutPreview, layoutPreviewInstallation;
    private FloatingActionButton fabPreview;
    private TextView previewTextView, previewTextViewHeader;
    private ImageView cancelPreviewButton;
    String stringPreview = "";

    private Button buttonBack, buttonNext;
    int position = 0;

    String pushDataJson;


    //Fragments
    FragmentCashFifth fragmentCashFifth;
    FragmentPreview fragmentPreview;

    boolean isEditable = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_fifth);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.beginFakeDrag();
        setupViewPager(viewPager);
        setupStepView();

        layoutPreview = (RelativeLayout)findViewById(R.id.layoutPreview);
        layoutPreviewInstallation = (RelativeLayout)findViewById(R.id.layoutPreviewInstallation);
        layoutPreview.setVisibility(View.GONE);
        layoutPreviewInstallation.setVisibility(View.GONE);
        previewTextViewHeader = (TextView)findViewById(R.id.previewTextViewHeader);
        previewTextView = (TextView)findViewById(R.id.previewTextView);
        fabPreview = (FloatingActionButton)findViewById(R.id.fabPreview);
        cancelPreviewButton = (ImageView)findViewById(R.id.imageViewCancel);

        buttonBack = (Button)findViewById(R.id.buttonCashBack);
        buttonBack.setText("EXIT");
        buttonNext = (Button)findViewById(R.id.buttonCashNext);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                viewPager.setCurrentItem(position, true);

                if(position >= 1)
                {
                    position = 1;
                }
                if(buttonNext.getText().toString().equalsIgnoreCase("SAVE")||buttonNext.getText().toString().equalsIgnoreCase("UPDATE"))
                {
                    methodSubmit();
                }
                atWhatPosition(position);

            }
        });
        
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                viewPager.setCurrentItem(position, true);

                if(position <= 0)
                {
                    position = 0;
                }
                if (buttonBack.getText().toString().equalsIgnoreCase("EXIT"))
                {
                    onBackPressed();
                }
                atWhatPosition(position);

            }
        });

        fabPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutPreview.setVisibility(View.GONE);
                layoutPreviewInstallation.setVisibility(View.VISIBLE);
                previewTextViewHeader.setText("Conditionality Compliance");
                setupPreviewTextView();
            }
        });

        cancelPreviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutPreview.setVisibility(View.VISIBLE);
                layoutPreviewInstallation.setVisibility(View.GONE);
            }
        });


        if(this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("edit_form_5"))
        {

            //Tell fragments we are in edit form
            SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferencesJSON.edit();
            editor.putString("in_edit_form_5","in_edit_form_5");
            editor.apply();

            isEditable = true;


        } else
        {
            //Toast.makeText(this, "Add new form", Toast.LENGTH_SHORT).show();
            //Tell fragments we are not in edit form
            SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferencesJSON.edit();
            editor.putString("in_edit_form_5","not_in_edit_form_5");
            editor.apply();

            isEditable = false;
        }

    }


    @SuppressLint("ResourceType")
    private void setupPreviewTextView() {

        String [] pArray = stringPreview.split("#");
        String colorText = "#"+getResources().getString(R.color.colorPText).substring(3);
        String text = getResources().getString(R.string.ffi_1) + "<b><h3><font color='"+colorText+"'>"+ pArray[0] +"</font></h3></b><br>"
                + getResources().getString(R.string.ffi_2) + "<b><h3><font color='"+colorText+"'>"+ pArray[1] + "</font></h3></b><br>"
                + getResources().getString(R.string.ffi_3) + "<b><h3><font color='"+colorText+"'>"+ "</font></h3></b><br>"
                + getResources().getString(R.string.ffi_3_1) + "<b><h3><font color='"+colorText+"'>"+ pArray[2] + "</font></h3></b><br>"
                + getResources().getString(R.string.ffi_3_2) + "<b><h3><font color='"+colorText+"'>"+ pArray[3] + "</font></h3></b><br>"
                + getResources().getString(R.string.ffi_3_3) + "<b><h3><font color='"+colorText+"'>"+ pArray[4] + "</font></h3></b><br>"
                + getResources().getString(R.string.ffi_3_4) + "<b><h3><font color='"+colorText+"'>"+ pArray[5] + "</font></h3></b><br>"
                + getResources().getString(R.string.ffi_4) + "<b><h3><font color='"+colorText+"'>"+"</font></h3></b><br>"
                + getResources().getString(R.string.ffi_4_1) + "<b><h3><font color='"+colorText+"'>"+ pArray[6] + "</font></h3></b><br>";
        previewTextView.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

    }


    private void atWhatPosition(int position) {
        stepView.go(position, true);

        switch (position)
        {
            case 0:
                buttonBack.setText("EXIT");
                buttonNext.setText("NEXT");
                layoutPreview.setVisibility(View.GONE);
                layoutPreviewInstallation.setVisibility(View.GONE);
                break;
            case 1:
                buttonBack.setText("BACK");
                if(isEditable == true)
                {
                    buttonNext.setText("UPDATE");
                }
                else
                {
                    buttonNext.setText("SAVE");

                }                layoutPreview.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.back_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        TextView label = (TextView)dialog.findViewById(R.id.editCoupon);
        label.setText(getResources().getString(R.string.onBackPressed));
        label.setTextColor(getResources().getColor(R.color.colorPrimary));
        label.setTypeface(Typeface.DEFAULT_BOLD);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView yes = (ImageView)dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        ImageView no = (ImageView)dialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void methodSubmit() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.back_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        TextView label = (TextView)dialog.findViewById(R.id.editCoupon);
        if(isEditable == true)
        {
            label.setText(getResources().getString(R.string.onUpdatePressed));
        }
        else {
            label.setText(getResources().getString(R.string.onSavePressed));
        }        label.setTextColor(getResources().getColor(R.color.colorPrimary));
        label.setTypeface(Typeface.DEFAULT_BOLD);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView yes = (ImageView)dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Submit the data
                pushUpdatedDatatoServer();
               // Toast.makeText(CashFifthActivity.this, "Submitted", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
                finish();
            }
        });

        ImageView no = (ImageView)dialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void setupStepView() {
        stepView = (StepView)findViewById(R.id.step_view);
        stepView.setSteps(new ArrayList<String>() {
            {
                add("Conditionality\nCompliance");
                add("Preview\nForm");
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentCashFifth = new FragmentCashFifth();
        fragmentPreview = new FragmentPreview();
        adapter.addFragment(fragmentCashFifth, "");
        adapter.addFragment(fragmentPreview, "");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void updateFifth(String mData) {
        stringPreview = mData;
    }


    //For Form4
    private void pushUpdatedDatatoServer()
    {
        String [] pArray = stringPreview.split("#");

        Form5Model form5Model = new Form5Model();

        form5Model.setIs_child_alive(pArray[0].trim());
        form5Model.setChild_weight(pArray[1].trim());
        form5Model.setBcg(pArray[2].trim());
        form5Model.setHepatitis_b(pArray[3].trim());
        form5Model.setOpv(pArray[4].trim());
        form5Model.setPentavalent(pArray[5].trim());
        form5Model.setNon_compliance_reason(pArray[6].trim());

//        String is_child_alive = pArray[0];
//        String child_weight = pArray[1];
//        String bcg = pArray[2];
//        String hepatitis = pArray[3];
//        String opv = pArray[4];
//        String pentavelent = pArray[5];
//        String reason_for_non_compliance = pArray[6];


        JSONObject jsonRootForm = new JSONObject();
        JSONObject jsonForms = new JSONObject();


        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mDistrict = preferences2.getString("district", "Err");
        String mProject = preferences2.getString("project", "Err");

        /** Form4 **/

        Form1Model form1Model = new Form1Model();
        JSONObject jsonForm5 = new JSONObject();

        try {


            JSONObject jsonSection1 = new JSONObject();

            jsonSection1.put("beneficiaryName", form1Model.getBeneficiary_name());
            jsonSection1.put("mobileNo", form1Model.getMobile_number());

            JSONObject jsonBeneficiaryGuardian = new JSONObject();
            jsonBeneficiaryGuardian.put("name", form1Model.getName());
            jsonBeneficiaryGuardian.put("relation", form1Model.getRelation());

            JSONObject jsonAdhar = new JSONObject();
            jsonAdhar.put("aadharCardNo", form1Model.getAadhar_card_number());
            jsonAdhar.put("aadharEnrollmentNo", form1Model.getAadhar_enrolment_number());


            jsonSection1.put("beneficiaryGuardian", jsonBeneficiaryGuardian);
            jsonSection1.put("aadhar", jsonAdhar);


            JSONObject jsonSection2 = new JSONObject();
            jsonSection2.put("lmpDate", Long.parseLong(form1Model.getLast_menstrual_period()));
            jsonSection2.put("childAlive",Boolean.parseBoolean(form5Model.getIs_child_alive()));
            jsonSection2.put("weight", form5Model.getChild_weight());

            JSONObject jsonVaccinations = new JSONObject();
            jsonVaccinations.put("bcg", form5Model.getBcg());
            jsonVaccinations.put("hepatitisB", form5Model.getHepatitis_b());
            jsonVaccinations.put("opv", form5Model.getOpv());
            jsonVaccinations.put("pentavalent", form5Model.getPentavalent());
            jsonSection2.put("vaccinations", jsonVaccinations);


            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("exit",form5Model.getExit());
            jsonSection3.put("reason", form5Model.getNon_compliance_reason());

            jsonForm5.put("section1", jsonSection1);
            jsonForm5.put("section2", jsonSection2);
            jsonForm5.put("section3", jsonSection3);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("jsonForm5", jsonForm5.toString());

        pushDataJson = jsonForm5.toString();
        new PushCashUpdateAsync().execute();



    }


    public class PushCashUpdateAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashFifthActivity.this);
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
                if(isEditable == false)
                {
                    httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/updateBeneficiary?formNo=5");

                }else if(isEditable == true)
                {
                    httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/editBeneficiary?formNo=5");

                }
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer "+ mtoken);

                httpPost.setEntity(new StringEntity(pushDataJson, "UTF-8"));
                response = httpClient.execute(httpPost);

                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());


                Log.d("CashFifthActivity", value);

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
                Toast.makeText(CashFifthActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(CashFifthActivity.this, "Failed to Submit", Toast.LENGTH_SHORT).show();
            }

        }
    }



}
