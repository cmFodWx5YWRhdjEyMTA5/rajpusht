package in.co.rajpusht.rajpusht.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import in.co.rajpusht.rajpusht.DashBoard;
import in.co.rajpusht.rajpusht.LM_actvity;
import in.co.rajpusht.rajpusht.Login;
import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.adapter.CashBeneficiaryAdapter;
import in.co.rajpusht.rajpusht.fragment.FragmentPersonal;
import in.co.rajpusht.rajpusht.model.CashBeneficiaryModel;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

public class CashBeneficiaryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private List<CashBeneficiaryModel> cashBeneficiaryModelList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CashBeneficiaryAdapter mAdapter;
    boolean isSuccessful;

    SwipeRefreshLayout swipeRefreshLayout;

    String AWC_NAME, DISTRICT_NAME, PROJECT_NAME, AANGANWADI_CENTER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_beneficiary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.cash_transfer_due_list));
        setSupportActionBar(toolbar);


        Bundle bundle = getIntent().getExtras();
        String sector = bundle.getString("sector");
        AANGANWADI_CENTER = bundle.getString("location");

        String[] DISTRICT_PROJECT = sector.split(",");
        PROJECT_NAME = DISTRICT_PROJECT[0].trim();
        DISTRICT_NAME = DISTRICT_PROJECT[1].trim();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mtoken = preferences.getString("token", "Not Saved");
        JWT parsedJWT = new JWT(mtoken);
        Claim subscriptionMetaData = parsedJWT.getClaim("NAME");
        String parsedName = subscriptionMetaData.asString();
        Claim subcriptionUserName = parsedJWT.getClaim("EMAIL");
        String userEmail = subcriptionUserName.asString();
        View headerView = navigationView.getHeaderView(0);
        TextView textViewUserName = (TextView)headerView.findViewById(R.id.userName);
        textViewUserName.setText(parsedName.toUpperCase());
        TextView textViewUserEmail = (TextView)headerView.findViewById(R.id.userEmail);
        textViewUserEmail.setText(userEmail);



        mRecyclerView = (RecyclerView)findViewById(R.id.cash_beneficiary_recyclerview);
        mAdapter = new CashBeneficiaryAdapter(getApplicationContext(), cashBeneficiaryModelList);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        setRefreshLayout();

      // fetchDummyData();
       fetchCashBeneficiaryList();//Commented to for dummy

    }


    private void setRefreshLayout() {
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchCashBeneficiaryList();

            }
    });

        swipeRefreshLayout.setProgressViewOffset(
                Boolean.FALSE, // scaling animation
                20, // top position of the loading indicator
                200); // max scrolling bottom position of current indicator
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchCashBeneficiaryList();
    }

    private void fetchCashBeneficiaryList() {
        new BeneCashAsync().execute(PROJECT_NAME, DISTRICT_NAME,AANGANWADI_CENTER);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("project",PROJECT_NAME);
        editor.putString("district",DISTRICT_NAME);
        editor.putString("anganwadiCenter", AANGANWADI_CENTER);
        editor.apply();

    }

    public class BeneCashAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashBeneficiaryActivity.this);
            progressDialog.setMessage("Data Loading...");
          //  progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
            swipeRefreshLayout.setRefreshing(Boolean.FALSE);


        }

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            String json = null;
            cashBeneficiaryModelList.clear();

            String project = strings [0];
            String dist = strings[1];
            String aaganwadi_center = strings[2];
            aaganwadi_center = aaganwadi_center.replace(" ", "%20");

            try
            {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpPost = new HttpGet(CASH_BASE_URL + "beneficiary/getBeneficiaryList?project="+project+"&district="+dist+"&anganwadiCenter="+aaganwadi_center);
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Accept-Language", "en-US");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Authorization", "Bearer "+ mtoken);


                response = httpClient.execute(httpPost);
                String sresponseget = response.getEntity().toString();
                Log.w("QueingSystem2", sresponseget);
                value = EntityUtils.toString(response.getEntity());
                Log.d("beneficiaryResponse", value);

                //Storing received json String to shared preferences
                SharedPreferences preferencesJSON = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferencesJSON.edit();
                editor.putString("string_json",value);
                editor.apply();



                int maxLogSize =1000;
                for(int i = 0; i <= value.length() / maxLogSize; i++) {
                    int start = i * maxLogSize;
                    int end = (i+1) * maxLogSize;
                    end = end > value.toString().length() ? value.toString().length() : end;
                    Log.v("beneficiaryResponseLong", value.toString().substring(start, end)); }


                JSONObject jsonObj = new JSONObject(value);
                isSuccessful = jsonObj.getBoolean("success");

                JSONArray jsonDataArray = jsonObj.getJSONArray("data");

                for(int i =0; i< jsonDataArray.length(); i++)
                {
                    JSONObject jsonObjList = jsonDataArray.getJSONObject(i);
                  //  JSONObject jsonForms = jsonObjList.getJSONObject("forms");
                  //  JSONObject jsonForm1 = jsonForms.getJSONObject("form_1");
                    String id = jsonObjList.getString("_id");




                    JSONObject jsonSection1 = jsonObjList.getJSONObject("section1");

                    String mob = jsonSection1.getString("mobileNo");
                    JSONObject jsonGuardian = jsonSection1.getJSONObject("beneficiaryGuardian");


                    String beneficiaryName = jsonSection1.getString("beneficiaryName");
                    String beneficiaryGuradian = jsonGuardian.getString("name");


                    JSONObject jsonSection3 = jsonObjList.getJSONObject("section3");
                    String lmp_date = jsonSection3.getString("lmpDate");


                    JSONObject jsonBenficiaryInstallmentStatus = jsonObjList.getJSONObject("beneficiaryInstallmentStatus");
                    String current_installment = jsonBenficiaryInstallmentStatus.getString("currentInstallment");
                  //  String paidStatus = jsonBenficiaryInstallmentStatus.getString("status");




                    JSONObject jsonANCDetails = jsonSection3.getJSONObject("anc");
                    String last_anc_date = jsonANCDetails.getString("lastANCDate");

                    long last_lmp = Long.parseLong(lmp_date);
                    long last_anc = Long.parseLong(last_anc_date);
                    long diff = last_anc - last_lmp;
                    long noOfweeks = diff / (1000 * 60 * 60 *24 * 7);
                    String currentPhase;
                    if(noOfweeks <= 12)
                    {
                        currentPhase = "PW1";
                    }else if( noOfweeks >12 && noOfweeks <= 24)
                    {
                        currentPhase = "PW2";
                    } else if( noOfweeks >24 && noOfweeks <=36)
                    {
                        currentPhase = "PW3";
                    } else  if(noOfweeks >36 && noOfweeks <=48)
                    {
                        currentPhase = "PW4";
                    } else if(noOfweeks >48 && noOfweeks <= 60)
                    {
                        currentPhase = "LM1";
                    } else  if(noOfweeks > 60 && noOfweeks <72 )
                    {
                        currentPhase = "LM2";
                    }
                    else {
                        currentPhase = "Err";
                    }

                    JSONObject jsonOD = jsonObjList.getJSONObject("otherDetails");
                    String paymentCompleteStatus = jsonOD.getString("paymentComplete");
                    Log.d("last_date_payment", paymentCompleteStatus);

                    String paymentStatus = null;
                    if(paymentCompleteStatus.equalsIgnoreCase("null"))
                    {
                        paymentStatus = "1";
                    } else  if(paymentCompleteStatus.equalsIgnoreCase("true"))
                    {
                        paymentStatus = "2";
                    } else if( paymentCompleteStatus.equalsIgnoreCase("false"))
                    {
                        paymentStatus = "3";
                    }

                    JSONObject jsonAadhar = jsonSection1.getJSONObject("aadhar");
                    String aadharCardNo = jsonAadhar.getString("aadharCardNo");
                    String aadharEnrollmentNo = jsonAadhar.getString("aadharEnrollmentNo");



                    CashBeneficiaryModel cashBeneficiaryModel = new CashBeneficiaryModel();

                    cashBeneficiaryModel.setName(beneficiaryName+ "\n(w/o) " + beneficiaryGuradian + "\n(Mob: "+mob+")");
                    cashBeneficiaryModel.setPhase(currentPhase);
                    cashBeneficiaryModel.setStatus(paymentStatus);
                    cashBeneficiaryModel.setInstallment(current_installment);
                   // cashBeneficiaryModel.setPaid(paidStatus);
                    cashBeneficiaryModel.setAadharId(aadharCardNo+"# "+aadharEnrollmentNo);
                    cashBeneficiaryModel.setId(id);

                    cashBeneficiaryModelList.add(cashBeneficiaryModel);


                     Log.d("beneficiaryGuardian", beneficiaryGuradian);





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


    /** Adding dummy data **/

    private void fetchDummyData() {
        CashBeneficiaryModel cashBeneficiaryModel = new CashBeneficiaryModel("Sunita Ben", "PW2", "1", "2nd" , "N", "123","1");
        cashBeneficiaryModelList.add(cashBeneficiaryModel);

        CashBeneficiaryModel cashBeneficiaryModel2 = new CashBeneficiaryModel("Asha Gill", "PW2", "1","2nd" , "Y", "123","2");
        cashBeneficiaryModelList.add(cashBeneficiaryModel2);

        CashBeneficiaryModel cashBeneficiaryModel3 = new CashBeneficiaryModel("Santi Bai", "PW2","2", "2nd" , "Y", "123","3");
        cashBeneficiaryModelList.add(cashBeneficiaryModel3);

        CashBeneficiaryModel cashBeneficiaryModel4 = new CashBeneficiaryModel("Savitri Bai", "PW3","2", "2nd" , "Y", "123","4");
        cashBeneficiaryModelList.add(cashBeneficiaryModel4);

        CashBeneficiaryModel cashBeneficiaryModel5 = new CashBeneficiaryModel("Foranta Xavior", "PW4", "1","3rd" , "N", "123","5");
        cashBeneficiaryModelList.add(cashBeneficiaryModel5);

        CashBeneficiaryModel cashBeneficiaryModel6 = new CashBeneficiaryModel("Sakeena Roy", "PW4","3", "3rd" , "N", "123","6");
        cashBeneficiaryModelList.add(cashBeneficiaryModel6);

        CashBeneficiaryModel cashBeneficiaryModel7 = new CashBeneficiaryModel("Sweta Tiwari", "PW4","4", "3rd" , "N", "123","7");
        cashBeneficiaryModelList.add(cashBeneficiaryModel7);

        CashBeneficiaryModel cashBeneficiaryModel8 = new CashBeneficiaryModel("Harshita Shukla", "LM1","1", "4th" , "N", "123","8");
        cashBeneficiaryModelList.add(cashBeneficiaryModel8);

        CashBeneficiaryModel cashBeneficiaryModel9 = new CashBeneficiaryModel("Neha Kumari", "LM3","1", "5th" , "N", "123","9");
        cashBeneficiaryModelList.add(cashBeneficiaryModel9);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cash_beneficiary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.add_new) {
            //startActivity(new Intent(this, CashAadharSyncActivity.class));
            Intent intent = new Intent(this, CashAddNewBenefeciaryActivity.class);
          //  intent.putExtra("add_new_form", "add_new_form");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            finish();

        } else if (id == R.id.nav_beneficiarylist) {
          //  startActivity(new Intent(CashBeneficiaryActivity.this, CashBeneficiaryActivity.class));
        } else if(id == R.id.nav_cash_transefer_status) {
             startActivity(new Intent(CashBeneficiaryActivity.this, CashTransferStatus.class));

        } else if(id == R.id.correction_report) {
             startActivity(new Intent(CashBeneficiaryActivity.this, CashCorrectionActivity.class));

        } else if (id == R.id.nav_incentive_status) {
            Toast.makeText(this, "Incentive Status", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_logout) {
            finish();
            CashProfileActivity.sCashProfileActivity.finish();

        }
//        else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
