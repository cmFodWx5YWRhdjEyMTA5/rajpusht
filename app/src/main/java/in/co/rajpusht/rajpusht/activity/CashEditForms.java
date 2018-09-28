package in.co.rajpusht.rajpusht.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.model.Form1Model;
import in.co.rajpusht.rajpusht.model.Form2Model;
import in.co.rajpusht.rajpusht.model.Form3Model;
import in.co.rajpusht.rajpusht.model.Form4Model;
import in.co.rajpusht.rajpusht.model.Form5Model;
import in.co.rajpusht.rajpusht.tool.Constants;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

/**
 * Created by LENOVO on 20-Sep-18.
 */

public class CashEditForms extends AppCompatActivity {

    private Button bf1, bf2, bf3, bf4, bf5, buttonExit;

    String aadharId;
    int formNo = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_edit);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String rejectedForm = bundle.getString("rejected_form");
        aadharId = bundle.getString("aadhar_id");
        TextView textViewname = (TextView) findViewById(R.id.textBeneForms);
        textViewname.setText(name);


        TextView toolbarText = (TextView) findViewById(R.id.toolbarHeader);
        toolbarText.setText("MAMTA");

        ImageView toolbarBack = (ImageView) findViewById(R.id.toolbarBack);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        bf1 = (Button) findViewById(R.id.buttonForm1);
        bf2 = (Button) findViewById(R.id.buttonForm2);
        bf3 = (Button) findViewById(R.id.buttonForm3);
        bf4 = (Button) findViewById(R.id.buttonForm4);
        bf5 = (Button) findViewById(R.id.buttonForm5);
        buttonExit = (Button) findViewById(R.id.buttonExit);
        bf1.setEnabled(false);
        bf2.setEnabled(false);
        bf3.setEnabled(false);
        bf4.setEnabled(false);
        bf5.setEnabled(false);

        disableEditButton();
        enableEditButton(rejectedForm);

        bf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formNo = 1;
                new fetchByAadharIDAsync().execute();
            }
        });


        bf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formNo = 2;
                new fetchByAadharIDAsync().execute();

            }
        });

        bf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formNo =3;
                new fetchByAadharIDAsync().execute();
            }
        });

        bf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formNo =4;
                new fetchByAadharIDAsync().execute();
            }
        });

        bf5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formNo =5;
                new fetchByAadharIDAsync().execute();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    private void disableEditButton() {

        bf1.setEnabled(false);
        bf1.setBackgroundResource(R.drawable.my_button_flat_disable);
        bf1.setText("DISABLED");

        bf2.setEnabled(false);
        bf2.setBackgroundResource(R.drawable.my_button_flat_disable);
        bf2.setText("DISABLED");

        bf3.setEnabled(false);
        bf3.setBackgroundResource(R.drawable.my_button_flat_disable);
        bf3.setText("DISABLED");

        bf4.setEnabled(false);
        bf4.setBackgroundResource(R.drawable.my_button_flat_disable);
        bf4.setText("DISABLED");

        bf5.setEnabled(false);
        bf5.setBackgroundResource(R.drawable.my_button_flat_disable);
        bf5.setText("DISABLED");

    }

    private void enableEditButton(String rejectedForm) {
        switch (rejectedForm) {
            case "1":
                bf1.setEnabled(true);
                bf1.setBackgroundResource(R.drawable.my_button_color_primary);
                bf1.setText("EDIT");
                break;

            case "2":
                bf2.setEnabled(true);
                bf2.setBackgroundResource(R.drawable.my_button_color_primary);
                bf2.setText("EDIT");
                break;

            case "3":
                bf3.setEnabled(true);
                bf3.setBackgroundResource(R.drawable.my_button_color_primary);
                bf3.setText("EDIT");
                break;

            case "4":
                bf4.setEnabled(true);
                bf4.setBackgroundResource(R.drawable.my_button_color_primary);
                bf4.setText("EDIT");
                break;

            case "5":
                bf5.setEnabled(true);
                bf5.setBackgroundResource(R.drawable.my_button_color_primary);
                bf5.setText("EDIT");
                break;

        }

    }


    private void setForm1Fields() {


//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//            String json = preferences.getString("string_json", "");
//            Log.d("jsonStringIneditText", json);
//
//
//            JSONObject jsonObj = null;
//            try {
//                jsonObj = new JSONObject(json);
//                JSONArray jsonDataArray = jsonObj.getJSONArray("data");
//
//                for(int i =0; i< jsonDataArray.length(); i++) {
//                    JSONObject jsonObjList = jsonDataArray.getJSONObject(i);
//
//                    String _id = jsonObjList.getString("_id");
//                    if(id.equalsIgnoreCase(_id)) {
//
//                        //Form1
//                        JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");
//
//                        Form1Model form1Model = new Form1Model();
//                        form1Model.setProject(jsonSection1.getString(Constants.JSON_PROJECT));
//                        form1Model.setSector(jsonSection1.getString(Constants.JSON_SECTOR));
//                        form1Model.setVillage(jsonSection1.getString(Constants.JSON_DISTRICT));
//                        form1Model.setAnganwadi_center(jsonSection1.getString(Constants.JSON_ANGANWADI));
//                        form1Model.setDate_of_registtration(jsonSection1.getString(Constants.JSON_DATE_OF_REGISTRATION));
//                        form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));
//
//                        Log.d("CashBeneficiaryAdapter", form1Model.getBeneficiary_name());
//
//                        Log.d("CashCorrectionGet", form1Model.getBeneficiary_name());
//
//                        form1Model.setAge(jsonSection1.getString(Constants.JSON_AGE));
//                        form1Model.setLiving_children_count(jsonSection1.getString(Constants.JSON_LIVING_CHILDREN_COUNT));
//                        form1Model.setCaste(jsonSection1.getString(Constants.JSON_CASTE));
//                        form1Model.setIs_bpl(jsonSection1.getString(Constants.JSON_IS_BPL));
//                        form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));
//                        form1Model.setBhamashah_number(jsonSection1.getString(Constants.JOSN_BHAMASHAH_NUMBER));
//                        //  form1Model.setDate_time_stamp(jsonSection1.getString(Constants.JSON_DATE_TIME_STAMP));
//
//                        JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
//                        form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));
//
//                        form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));
//
//                        JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
//                        form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
//                        form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));
//
//
//                        JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");
//
//
//                        // form1Model.setOwns_bank_account(jsonSection2.getString(Constants.JSON_OWNS_BANK_ACCOUNT));
//                        form1Model.setAccount_holder_name(jsonSection2.getString(Constants.JSON_ACCOUNT_HOLDER_NAME));
//                        form1Model.setBank_name(jsonSection2.getString(Constants.JSON_BANK_NAME));
//                        form1Model.setBranch_name(jsonSection2.getString(Constants.JSON_BRANCH_NAME));
//                        form1Model.setBank_account_number(jsonSection2.getString(Constants.JSON_BANK_ACCOUNT_NUMBER));
//                        form1Model.setIfsc_code(jsonSection2.getString(Constants.JSON_IFSC_CODE));
//
//
//                        JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");
//
//                        form1Model.setLast_menstrual_period(jsonSection3.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));
//
//
//                        JSONObject jsonANCDetail = jsonSection3.getJSONObject("anc");
//
//                        form1Model.setLast_anc_detail(jsonANCDetail.getString(Constants.JSON_LAST_ANC_DATE));
//                        form1Model.setWeight(jsonANCDetail.getString(Constants.JSON_WEIGHT));
//                        form1Model.setHeight(jsonANCDetail.getString(Constants.JSON_HEIGHT));
//                        form1Model.setPlace_of_anc(jsonANCDetail.getString(Constants.JSON_PLACE_OF_ANC));
//
//
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


    }


    public class fetchByAadharIDAsync extends AsyncTask<String, String, String> {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashEditForms.this);
            progressDialog.setMessage("Fetching Details...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            String json = null;


            JSONObject jsonAadharRoot = new JSONObject();
            try {
                JSONObject jsonAadhar = new JSONObject();
                jsonAadhar.put("aadharCardNo", aadharId);
                jsonAadhar.put("aadharEnrollmentNo", "");
                jsonAadharRoot.put("aadhar", jsonAadhar);
                jsonAadharRoot.put("formNo", formNo);

                Log.d("CashEditFormPush", jsonAadharRoot.toString());


            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/editBeneficiary/getForm");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer " + mtoken);

                httpPost.setEntity(new StringEntity(jsonAadharRoot.toString(), "UTF-8"));
                response = httpClient.execute(httpPost);

                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());

                Log.d("CashEditForms", value);


            } catch (Exception e) {

            }

            return value;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (formNo == 1) {
                Log.d("CashEditForm1", s);
                parseForm1Json(s);
            } else if (formNo == 2) {

                Log.d("CashEditForm2", s);
                parseForm2Json(s);
            } else if(formNo ==3)
            {
                Log.d("CashEditForm3", s);
                parseForm3Json(s);

            }
            else if(formNo ==4)
            {
                Log.d("CashEditForm4", s);
                parseForm4Json(s);

            }
            else if(formNo ==5)
            {
                Log.d("CashEditForm5", s);
                parseForm5Json(s);

            }

            progressDialog.dismiss();
        }
    }


    private void parseForm1Json(String json) {

        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            JSONObject jsonObjList = jsonObj.getJSONObject("data");


            //Form1
            JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

            Form1Model form1Model = new Form1Model();
            form1Model.setProject(jsonSection1.getString(Constants.JSON_PROJECT));


            form1Model.setSector(jsonSection1.getString(Constants.JSON_SECTOR));
            form1Model.setVillage(jsonSection1.getString(Constants.JSON_DISTRICT));
            form1Model.setAnganwadi_center(jsonSection1.getString(Constants.JSON_ANGANWADI));
            form1Model.setDate_of_registtration(jsonSection1.getString(Constants.JSON_DATE_OF_REGISTRATION));
            form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));


            form1Model.setAge(jsonSection1.getString(Constants.JSON_AGE));
            form1Model.setLiving_children_count(jsonSection1.getString(Constants.JSON_LIVING_CHILDREN_COUNT));
            form1Model.setCaste(jsonSection1.getString(Constants.JSON_CASTE));
            form1Model.setIs_bpl(jsonSection1.getString(Constants.JSON_IS_BPL));
            form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));
            form1Model.setBhamashah_number(jsonSection1.getString(Constants.JOSN_BHAMASHAH_NUMBER));

            JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
            form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));

            form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

            JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
            form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
            form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


            JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");


            // form1Model.setOwns_bank_account(jsonSection2.getString(Constants.JSON_OWNS_BANK_ACCOUNT));
            if (jsonSection2.getString(Constants.JSON_ACCOUNT_HOLDER_NAME).equalsIgnoreCase("null")) {
                form1Model.setAccount_holder_name("");

            } else {
                form1Model.setAccount_holder_name(jsonSection2.getString(Constants.JSON_ACCOUNT_HOLDER_NAME));
            }

            if (jsonSection2.getString(Constants.JSON_BANK_NAME).equalsIgnoreCase("null")) {
                form1Model.setBank_name("");

            } else {
                form1Model.setBank_name(jsonSection2.getString(Constants.JSON_BANK_NAME));

            }

            if (jsonSection2.getString(Constants.JSON_BRANCH_NAME).equalsIgnoreCase("null")) {
                form1Model.setBranch_name("");
            } else {
                form1Model.setBranch_name(jsonSection2.getString(Constants.JSON_BRANCH_NAME));
            }

            if (jsonSection2.getString(Constants.JSON_BANK_ACCOUNT_NUMBER).equalsIgnoreCase("null")) {
                form1Model.setBank_account_number("");

            } else {
                form1Model.setBank_account_number(jsonSection2.getString(Constants.JSON_BANK_ACCOUNT_NUMBER));

            }


            if (jsonSection2.getString(Constants.JSON_IFSC_CODE).equalsIgnoreCase("null")) {
                form1Model.setIfsc_code("");
            } else {
                form1Model.setIfsc_code(jsonSection2.getString(Constants.JSON_IFSC_CODE));
            }


            JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");

            String lastMnc = jsonSection3.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD);
            long unixSecondsLMNC = Long.parseLong(lastMnc);
            Date date = new java.util.Date(unixSecondsLMNC);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String formattedDateLMNC = sdf.format(date);

            form1Model.setLast_menstrual_period(formattedDateLMNC);


            JSONObject jsonANCDetail = jsonSection3.getJSONObject("anc");

            String lastAnc = jsonANCDetail.getString(Constants.JSON_LAST_ANC_DATE);
            long unixSecondsAnc = Long.parseLong(lastAnc);
            Date dateANC = new java.util.Date(unixSecondsAnc);
            SimpleDateFormat sdfANC = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String formattedDateANC = sdfANC.format(dateANC);
            form1Model.setLast_anc_detail(formattedDateANC);


            form1Model.setWeight(jsonANCDetail.getString(Constants.JSON_WEIGHT));
            form1Model.setHeight(jsonANCDetail.getString(Constants.JSON_HEIGHT));
            form1Model.setPlace_of_anc(jsonANCDetail.getString(Constants.JSON_PLACE_OF_ANC));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Start Form1 in Edit Mode
        Intent intent = new Intent(CashEditForms.this, CashAddNewBenefeciaryActivity.class);
        intent.putExtra("edit_form_1", "edit_form_1");
        startActivity(intent);
        finish();

    }


    private void parseForm2Json(String json) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            JSONObject jsonObjList = jsonObj.getJSONObject("data");


            //Form1
            JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

            Form1Model form1Model = new Form1Model();
            form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));


            JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
            form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));
            form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

            form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));


            JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
            form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
            form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


            JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");
            form1Model.setLast_menstrual_period(jsonSection2.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));

            JSONObject jsonAnc = jsonSection2.getJSONObject("anc");

            Form2Model form2Model = new Form2Model();

            String lastAnc = jsonAnc.getString(Constants.JSON_LAST_ANC_DATE);
            long unixSecondsAnc = Long.parseLong(lastAnc);
            Date dateANC = new java.util.Date(unixSecondsAnc);
            SimpleDateFormat sdfANC = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String formattedDateANC = sdfANC.format(dateANC);

            form2Model.setLast_anc_date(formattedDateANC);
            form2Model.setWeight(jsonAnc.getString(Constants.JSON_WEIGHT));

            JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");
            form2Model.setExit(jsonSection3.getString(Constants.JSON_EXIT));
            form2Model.setNon_compliance_reason(jsonSection3.getString(Constants.JSON_REASON));


            //Start Form2 in Edit Mode
            Intent intent = new Intent(CashEditForms.this, CashSecondThirdActivity.class);
            intent.putExtra("edit_form_2", "edit_form_2");
            startActivity(intent);
            finish();


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void parseForm3Json(String json) {

            JSONObject jsonObj = null;
            try {
                jsonObj = new JSONObject(json);
                JSONObject jsonObjList = jsonObj.getJSONObject("data");


                //Form1
                JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

                Form1Model form1Model = new Form1Model();
                form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));


                JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
                form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));
                form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

                form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));


                JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
                form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
                form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


                JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");
                form1Model.setLast_menstrual_period(jsonSection2.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));

                JSONObject jsonAnc = jsonSection2.getJSONObject("anc");

                Form3Model form3Model = new Form3Model();

                String lastAnc = jsonAnc.getString(Constants.JSON_LAST_ANC_DATE);
                long unixSecondsAnc = Long.parseLong(lastAnc);
                Date dateANC = new java.util.Date(unixSecondsAnc);
                SimpleDateFormat sdfANC = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String formattedDateANC = sdfANC.format(dateANC);

                form3Model.setLast_anc_date(formattedDateANC);
                form3Model.setWeight(jsonAnc.getString(Constants.JSON_WEIGHT));

                JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");
                form3Model.setExit(jsonSection3.getString(Constants.JSON_EXIT));
                form3Model.setNon_compliance_reason(jsonSection3.getString(Constants.JSON_REASON));


                //Start Form3 in Edit Mode
                Intent intent = new Intent(CashEditForms.this, CashThirdActivity.class);
                intent.putExtra("edit_form_3", "edit_form_3");
                startActivity(intent);
                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }



    }


    private void parseForm4Json(String json) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            JSONObject jsonObjList = jsonObj.getJSONObject("data");


            //Form1
            JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

            Form1Model form1Model = new Form1Model();
            form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));


            JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
            form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));
            form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

            form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));


            JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
            form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
            form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


            JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");
            form1Model.setLast_menstrual_period(jsonSection2.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));


            Form4Model form4Model = new Form4Model();

            String deliveryDate = jsonSection2.getString(Constants.JSON_DELIVERY_DATE);
            long unixSecondsdelivery = Long.parseLong(deliveryDate);
            Date dateDelivery = new java.util.Date(unixSecondsdelivery);
            SimpleDateFormat sdfDelivery = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String formattedDateDelivery = sdfDelivery.format(dateDelivery);

            form4Model.setDelivery_date(formattedDateDelivery);
            form4Model.setBirth_weight(jsonSection2.getString(Constants.JSON_WEIGHT));
            form4Model.setIs_child_alive(jsonSection2.getString(Constants.JSON_IS_CHILD_ALIVE));
            form4Model.setChild_gender(jsonSection2.getString(Constants.JSON_CHILD_GENDER));


            JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");
            form4Model.setExit(jsonSection3.getString(Constants.JSON_EXIT));
            form4Model.setNon_compliance_reason(jsonSection3.getString(Constants.JSON_REASON));


            //Start Form4 in Edit Mode
            Intent intent = new Intent(CashEditForms.this, CashFourthActivity.class);
            intent.putExtra("edit_form_4", "edit_form_4");
            startActivity(intent);
            finish();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseForm5Json(String json) {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(json);
            JSONObject jsonObjList = jsonObj.getJSONObject("data");


            //Form1
            JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

            Form1Model form1Model = new Form1Model();
            form1Model.setBeneficiary_name(jsonSection1.getString(Constants.JSON_BENEFICIARY_NAME));


            JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
            form1Model.setName(jsonGuardian.getString(Constants.JSON_NAME));
            form1Model.setRelation(jsonGuardian.getString(Constants.JSON_RELATION));

            form1Model.setMobile_number(jsonSection1.getString(Constants.JSON_MOBILE_NUMBER));


            JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
            form1Model.setAadhar_card_number(jsonAadhar.getString(Constants.JSON_AADHAR_CARD_NUMBER));
            form1Model.setAadhar_enrolment_number(jsonAadhar.getString(Constants.JSON_AADHAR_ENROLMENT_NUMBER));


            JSONObject jsonSection2 = jsonObjList.getJSONObject("section2");
            form1Model.setLast_menstrual_period(jsonSection2.getString(Constants.JSON_LAST_MENSTRUAL_PERIOD));


            Form5Model form5Model = new Form5Model();

            form5Model.setIs_child_alive(jsonSection2.getString(Constants.JSON_IS_CHILD_ALIVE));
            form5Model.setChild_weight(jsonSection2.getString(Constants.JSON_WEIGHT));

            JSONObject jsonVaccinations = jsonSection2.getJSONObject("vaccinations");
            form5Model.setBcg(jsonVaccinations.getString(Constants.JSON_BCG));
            form5Model.setHepatitis_b(jsonVaccinations.getString(Constants.JSON_HEPATITIS_B));
            form5Model.setOpv(jsonVaccinations.getString(Constants.JSON_OPV));
            form5Model.setPentavalent(jsonVaccinations.getString(Constants.JSON_PENTAVALENT));

            JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");
            form5Model.setExit(jsonSection3.getString(Constants.JSON_EXIT));
            form5Model.setNon_compliance_reason(jsonSection3.getString(Constants.JSON_REASON));


            //Start Form5 in Edit Mode
            Intent intent = new Intent(CashEditForms.this, CashFifthActivity.class);
            intent.putExtra("edit_form_5", "edit_form_5");
            startActivity(intent);
            finish();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
