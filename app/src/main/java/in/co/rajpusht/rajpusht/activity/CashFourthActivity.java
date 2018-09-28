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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.adapter.ViewPagerAdapter;
import in.co.rajpusht.rajpusht.fragment.FragmentCashFourth;
import in.co.rajpusht.rajpusht.fragment.FragmentCashSecondThird;
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

public class CashFourthActivity extends AppCompatActivity
                            implements  FragmentCashFourth.OnCallbackReceivedfourth{
    public static final String TAG = CashFourthActivity.class.getSimpleName();
    private ViewPager viewPager;
    private StepView stepView;

    private RelativeLayout layoutPreview, layoutPreviewInstallation;
    private FloatingActionButton fabPreview;
    private TextView previewTextView, previewTextViewHeader;
    private ImageView cancelPreviewButton;
    String stringPreview = "";

    private Button buttonBack, buttonNext;
    int position = 0;


    //Fragments
    FragmentCashFourth fragmentCashFourth;
    FragmentPreview fragmentPreview;

    String pushDataJson;
    boolean isEditable = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_fourth);

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
                    String [] pArray = stringPreview.split("#");
                    String ANCDate = pArray[0];
                    if(ANCDate.equalsIgnoreCase(""))
                    {
                        viewPager.setCurrentItem(0);
                        position=0;
                        layoutPreview.setVisibility(View.GONE);
                        Toast.makeText(CashFourthActivity.this, "Delivery Date is a required field!", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        methodSubmit();
                    }
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


        if(this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("edit_form_4"))
        {

            //Tell fragments we are in edit form
            SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferencesJSON.edit();
            editor.putString("in_edit_form_4","in_edit_form_4");
            editor.apply();

            isEditable = true;


        } else
        {
           // Toast.makeText(this, "Add new form", Toast.LENGTH_SHORT).show();
            //Tell fragments we are not in edit form
            SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = preferencesJSON.edit();
            editor.putString("in_edit_form_4","not_in_edit_form_4");
            editor.apply();

            isEditable = false;
        }


    }


    @SuppressLint("ResourceType")
    private void setupPreviewTextView() {

        String [] pArray = stringPreview.split("#");
        String colorText = "#"+getResources().getString(R.color.colorPText).substring(3);
        String text = getResources().getString(R.string.fi_1) + "<b><h3><font color='"+colorText+"'>"+ pArray[0] +"</font></h3></b><br>"
                + getResources().getString(R.string.fi_2) + "<b><h3><font color='"+colorText+"'>"+ pArray[1] + "</font></h3></b><br>"
                + getResources().getString(R.string.fi_3) + "<b><h3><font color='"+colorText+"'>"+ pArray[2] + "</font></h3></b><br>"
                + getResources().getString(R.string.fi_4) + "<b><h3><font color='"+colorText+"'>"+ pArray[3] + "</font></h3></b><br>"
                + getResources().getString(R.string.fi_5) + "<b><h3><font color='"+colorText+"'>"+ "</font></h3></b><br>"
                + getResources().getString(R.string.fi_6) + "<b><h3><font color='"+colorText+"'>"+ pArray[4] + "</font></h3></b><br>";
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


        Log.d("CashSecond", String.valueOf(position));
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
              //  Toast.makeText(CashFourthActivity.this, "Submitted", Toast.LENGTH_SHORT).show();

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
        fragmentCashFourth = new FragmentCashFourth();
        fragmentPreview = new FragmentPreview();
        adapter.addFragment(fragmentCashFourth, "");
        adapter.addFragment(fragmentPreview, "");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void updateFourth(String mData) {
        stringPreview = mData;
    }


    //For Form4
    private void pushUpdatedDatatoServer()
    {
        String [] pArray = stringPreview.split("#");

        Form4Model form4Model = new Form4Model();
        form4Model.setDelivery_date(pArray[0].trim());
        form4Model.setIs_child_alive(pArray[1].trim());
        form4Model.setChild_gender(pArray[2].trim());
        form4Model.setBirth_weight(pArray[3].trim());
        form4Model.setNon_compliance_reason(pArray[4].trim());

//        String date_of_delivery = pArray[0];
//        String status_of_delivery = pArray[1];
//        String gender_of_child = pArray[2];
//        String birth_weight = pArray[3];
//        String reason_for_non_compliance = pArray[4];


        JSONObject jsonRootForm = new JSONObject();
        JSONObject jsonForms = new JSONObject();


        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mDistrict = preferences2.getString("district", "Err");
        String mProject = preferences2.getString("project", "Err");

        /** Form4 **/

        Form1Model form1Model = new Form1Model();
        JSONObject jsonForm4 = new JSONObject();

        try {


            JSONObject jsonSection1 = new JSONObject();

            jsonSection1.put("beneficiaryName", form1Model.getBeneficiary_name());
            jsonSection1.put("mobileNo", form1Model.getMobile_number());

            Log.d("CashThird", form1Model.getBeneficiary_name());

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

            String deliveryDate = form4Model.getDelivery_date();
            DateFormat dateFormat =  new SimpleDateFormat("dd/MM/yyyy");
            Date deliverydate = null;
            try {
                deliverydate = (Date)dateFormat.parse(deliveryDate) ;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            jsonSection2.put("deliveryDate", deliverydate.getTime());
            jsonSection2.put("childAlive",Boolean.parseBoolean(form4Model.getIs_child_alive()));
            jsonSection2.put("gender", form4Model.getChild_gender());
            jsonSection2.put("weight", form4Model.getBirth_weight());


            JSONObject jsonSection3 = new JSONObject();
            jsonSection3.put("exit","");
            jsonSection3.put("reason", form4Model.getNon_compliance_reason());

            jsonForm4.put("section1", jsonSection1);
            jsonForm4.put("section2", jsonSection2);
            jsonForm4.put("section3", jsonSection3);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("jsonForm", jsonForm4.toString());

        pushDataJson = jsonForm4.toString();
        new PushCashUpdateAsync().execute();



    }


    public class PushCashUpdateAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashFourthActivity.this);
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
                    httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/updateBeneficiary?formNo=4");

                }else if(isEditable == true)
                {
                    httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/editBeneficiary?formNo=4");

                }
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer "+ mtoken);

                httpPost.setEntity(new StringEntity(pushDataJson, "UTF-8"));
                response = httpClient.execute(httpPost);

                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());


                Log.d("CashFourthActivity", value);

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
                Toast.makeText(CashFourthActivity.this, "Submitted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(CashFourthActivity.this, "Failed to Submit", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
