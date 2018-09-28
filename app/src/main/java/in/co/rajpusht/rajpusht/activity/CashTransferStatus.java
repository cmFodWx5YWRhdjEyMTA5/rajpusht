package in.co.rajpusht.rajpusht.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.adapter.CashTransferAdapter;
import in.co.rajpusht.rajpusht.model.CashTransferModel;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

/**
 * Created by LENOVO on 18-Sep-18.
 */

public class CashTransferStatus extends AppCompatActivity {
    private List<CashTransferModel> cashTransferModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CashTransferAdapter mAdapter;
    private SearchView searchView;

    Spinner spinner;
    ImageView back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_transfer);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_status);
        mAdapter = new CashTransferAdapter(cashTransferModelList);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Failed");
        categories.add("Exit");
        categories.add("Complete");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        back = (ImageView)findViewById(R.id.imageback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        ImageView imageInfo = (ImageView)findViewById(R.id.infoCashTransfer);
        imageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCashTransferInfo();
            }
        });


       // fetchTransferStatusData();
        new CashTransferAsync().execute();



    }




    public class CashTransferAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashTransferStatus.this);
            progressDialog.setMessage("Data Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            String json = null;
            cashTransferModelList.clear();

            SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String district = preferences2.getString("district", "Err");
            String project = preferences2.getString("project", "Err");
            String aaganwadi_center = preferences2.getString("anganwadiCenter", "Err");
            aaganwadi_center = aaganwadi_center.replace(" ", "%20");



            try
            {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/cashTransferStatus?project="+project+"&district="+district+"&anganwadiCenter="+aaganwadi_center );
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Accept-Language", "en-US");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Authorization", "Bearer "+ mtoken);


                response = httpClient.execute(httpPost);
                String sresponseget = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());


                Log.d("CashTransferStatus", value);



                JSONObject jsonObj = new JSONObject(value);

                JSONArray jsonDataArray = jsonObj.getJSONArray("data");

                for(int i =0; i< jsonDataArray.length(); i++)
                {
                    JSONObject jsonObjList = jsonDataArray.getJSONObject(i);

                    String _id = jsonObjList.getString("_id");

                    JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");
                  //  JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");


                    String beneficiaryName = jsonSection1.getString("beneficiaryName");
                  //  String beneficiaryGuradian = jsonGuardian.getString("name");

                    JSONObject jsonInstallmentStatus = jsonObjList.getJSONObject("beneficiaryInstallmentStatus");
                    String i1 = jsonInstallmentStatus.getString("i1");
                    String i2 = jsonInstallmentStatus.getString("i2");
                    String i3 = jsonInstallmentStatus.getString("i3");
                    String i4 = jsonInstallmentStatus.getString("i4");
                    String i5 = jsonInstallmentStatus.getString("i5");



                    CashTransferModel cashTransferModel = new CashTransferModel();
                    cashTransferModel.setName(beneficiaryName);
                    cashTransferModel.setReceived(i1);
                    cashTransferModel.setSubmitted(i2);
                    cashTransferModel.setDue(i3);
                    cashTransferModel.setEligible(i4);
                    cashTransferModel.setExit(i5);
                    cashTransferModelList.add(cashTransferModel);


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


    public void showCashTransferInfo() {
        final Dialog dialogCoupon;
        dialogCoupon = new Dialog(this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.dialog_cash_transfer_info);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);

        dialogCoupon.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialogCoupon.show();

    }


    private void fetchTransferStatusData() {
        CashTransferModel ctm = new CashTransferModel("Sunita", "1", "3", "0", "0", "0");
        cashTransferModelList.add(ctm);

        CashTransferModel ctm2 = new CashTransferModel("Swati", "1", "2", "4", "0", "0");
        cashTransferModelList.add(ctm2);

        CashTransferModel ctm3 = new CashTransferModel("Megha", "3", "0", "0", "0", "0");
        cashTransferModelList.add(ctm3);

        CashTransferModel ctm4 = new CashTransferModel("kavya", "1", "1", "1", "5", "0");
        cashTransferModelList.add(ctm4);

        CashTransferModel ctm5 = new CashTransferModel("Arpita", "1", "1", "0", "0", "0");
        cashTransferModelList.add(ctm5);

        CashTransferModel ctm6 = new CashTransferModel("Susane", "2", "1", "5", "0", "0");
        cashTransferModelList.add(ctm6);

        CashTransferModel ctm7 = new CashTransferModel("Preety", "3", "1", "0", "0", "0");
        cashTransferModelList.add(ctm7);

        CashTransferModel ctm8 = new CashTransferModel("Neha", "1", "4", "0", "3", "0");
        cashTransferModelList.add(ctm8);

        CashTransferModel ctm9 = new CashTransferModel("Libya", "1", "1", "0", "2", "0");
        cashTransferModelList.add(ctm9);

        CashTransferModel ctm10 = new CashTransferModel("Pragya", "1", "1", "0", "0", "0");
        cashTransferModelList.add(ctm10);


    }
}