package in.co.rajpusht.rajpusht.activity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.adapter.CashCorrectionAdapter;
import in.co.rajpusht.rajpusht.model.CashCorrectionModel;
import in.co.rajpusht.rajpusht.model.CashTransferModel;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

/**
 * Created by LENOVO on 20-Sep-18.
 */

public class CashCorrectionActivity extends AppCompatActivity {
    private List<CashCorrectionModel> cashCorrectionModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CashCorrectionAdapter mAdapter;

    String mDistrict;
    String mProject;
    String mAaganwadi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_correction);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_correction);
        mAdapter = new CashCorrectionAdapter(getApplicationContext(), cashCorrectionModelList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        ImageView imageBack = (ImageView)findViewById(R.id.toolbarBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final TextView toolbarHeader = (TextView)findViewById(R.id.toolbarHeader);
        toolbarHeader.setText("Correction Report");



        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mDistrict = preferences2.getString("district", "Err");
        mProject = preferences2.getString("project", "Err");
        mAaganwadi = preferences2.getString("anganwadiCenter", "Err");


        //fetchCashCorrectionData();for dummy data

        fetchCashCorrectionList();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        fetchCashCorrectionList();
    }

    private void fetchCashCorrectionList() {
        new CashCorrectionAsync().execute(mProject, mDistrict,mAaganwadi);

    }

    public class CashCorrectionAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashCorrectionActivity.this);
            progressDialog.setMessage("Data Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            String json = null;
            cashCorrectionModelList.clear();

            String project = strings [0];
            String dist = strings[1];
            String aaganwadi = strings[2];
            aaganwadi = aaganwadi.replace(" ", "%20");


            try
            {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet(CASH_BASE_URL + "beneficiary/getRejectedBeneficiaryList?project="+project+"&district="+dist+"&anganwadiCenter="+aaganwadi );
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Accept-Language", "en-US");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Authorization", "Bearer "+ mtoken);


                response = httpClient.execute(httpPost);
                String sresponseget = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());

                Log.d("CashCorrectionActivity", value);

                //Storing received json String to shared preferences
                SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferencesJSON.edit();
                editor.putString("string_json",value);
                editor.apply();

//
                JSONObject jsonObj = new JSONObject(value);

                JSONArray jsonDataArray = jsonObj.getJSONArray("data");

                for(int i =0; i< jsonDataArray.length(); i++)
                {
                    JSONObject jsonObjList = jsonDataArray.getJSONObject(i);

                    String rejectedFormNo = jsonObjList.getString("rejectedFormNo");
                    String rejectedReason = jsonObjList.getString("rejectionReason");


                    JSONObject jsonBeneficiaryDetails = jsonObjList.getJSONObject("beneficiaryDetails");
                    JSONObject jsonSection1 = jsonBeneficiaryDetails.getJSONObject("section1");
                    String beneficiaryName = jsonSection1.getString("beneficiaryName");

                    JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");
                    String beneficiaryGuradianName = jsonGuardian.getString("name");
                    String beneficiaryGuradianRelation = jsonGuardian.getString("relation");

                    JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
                    String aadharCardNo = jsonAadhar.getString("aadharCardNo");
                    String aadharEnrollmentNo = jsonAadhar.getString("aadharEnrollmentNo");



                    CashCorrectionModel cashCorrectionModel = new CashCorrectionModel();
                    cashCorrectionModel.setId(aadharCardNo);
                    cashCorrectionModel.setName(beneficiaryName+"\n(w/o): "+beneficiaryGuradianName);
                    cashCorrectionModel.setReason(rejectedReason);
                    cashCorrectionModel.setDue(rejectedFormNo);

                    cashCorrectionModelList.add(cashCorrectionModel);


                }





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
            mAdapter.notifyDataSetChanged();
        }
    }

    private void fetchCashCorrectionData() {

        CashCorrectionModel cashCorrectionModel = new CashCorrectionModel("Sunita Ben", "Account Details", "2nd", "");
        cashCorrectionModelList.add(cashCorrectionModel);

        CashCorrectionModel cashCorrectionModel2 = new CashCorrectionModel("Aaradhna", "Name Incorrect", "3rd", "");
        cashCorrectionModelList.add(cashCorrectionModel2);

        CashCorrectionModel cashCorrectionModel3 = new CashCorrectionModel("Arpita", "Account Details", "4th", "");
        cashCorrectionModelList.add(cashCorrectionModel3);

        CashCorrectionModel cashCorrectionModel4 = new CashCorrectionModel("Preeti", "Account Details", "2nd", "");
        cashCorrectionModelList.add(cashCorrectionModel4);

        CashCorrectionModel cashCorrectionModel5 = new CashCorrectionModel("Swati", "Account Details", "2nd", "");
        cashCorrectionModelList.add(cashCorrectionModel5);

        CashCorrectionModel cashCorrectionModel6 = new CashCorrectionModel("Pragya", "Details Incorrect", "2nd", "");
        cashCorrectionModelList.add(cashCorrectionModel6);

        CashCorrectionModel cashCorrectionModel7 = new CashCorrectionModel("Pareeniti", "Aadhar", "2nd", "");
        cashCorrectionModelList.add(cashCorrectionModel7);

        CashCorrectionModel cashCorrectionModel8 = new CashCorrectionModel("Susuma", "Account Details", "3rd", "");
        cashCorrectionModelList.add(cashCorrectionModel8);

        CashCorrectionModel cashCorrectionModel9 = new CashCorrectionModel("Hena", "Account Details", "2nd", "");
        cashCorrectionModelList.add(cashCorrectionModel9);

        CashCorrectionModel cashCorrectionModel10 = new CashCorrectionModel("Neha", "Account Details", "3rd", "");
        cashCorrectionModelList.add(cashCorrectionModel10);
    }
}
