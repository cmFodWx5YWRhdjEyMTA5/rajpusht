package in.co.rajpusht.rajpusht.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adpter.LocationSelectionAdapter;
import in.co.rajpusht.rajpusht.Login;
import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.adapter.CashProfileAdapter;
import in.co.rajpusht.rajpusht.model.AWCModel;

/**
 * Created by LENOVO on 11-Sep-18.
 */

public class CashProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CashProfileAdapter cashProfileAdapter;
    private List<AWCModel> awcModelList;

    Button btnSelectAWC;
    ImageView back;

    static CashProfileActivity sCashProfileActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_profile);
        getSupportActionBar().hide();

        sCashProfileActivity = this;


        Bundle bundle = getIntent().getExtras();
        String jsonAwcData = bundle.getString("json_awc_data");
       // Toast.makeText(this, jsonAwcData, Toast.LENGTH_SHORT).show();
        awcModelList = new ArrayList<>();
        cashProfileAdapter = new CashProfileAdapter(this, awcModelList);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewProfile);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
       //
        // cashProfileAdapter = new CashProfileAdapter(getApplicationContext(), awcModelList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getBaseContext(),
                LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cashProfileAdapter);

        btnSelectAWC = (Button)findViewById(R.id.btnSelectAWC);
        btnSelectAWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashProfileActivity.this, CashBeneficiaryActivity.class));


            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String mtoken = preferences.getString("token", "Not Saved");
        JWT parsedJWT = new JWT(mtoken);
        Claim subscriptionMetaData = parsedJWT.getClaim("NAME");
        String parsedName = subscriptionMetaData.asString();
        TextView profileName = (TextView)findViewById(R.id.profileName);
        profileName.setText("HELLO : "+parsedName.toUpperCase());


        populateData(jsonAwcData);

        }


        public static CashProfileActivity getInstance() {
         return  sCashProfileActivity;
        }


    private void populateData(String jsonAwcData) {
        awcModelList.clear();

  /*      try {
            JSONObject jsonObject = new JSONObject(jsonAwcData);

            JSONArray jsonArray = jsonObject.getJSONArray("assignedLocation");
            for(int i =0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String sectorName = jsonObject1.getString("sectorName");
                String distName = jsonObject1.getString("distName");
                String awcName = jsonObject1.getString("awcName");

                Log.d("awcName",awcName);

                AWCModel awcModel = new AWCModel();
                awcModel.setSectorName(sectorName);
                awcModel.setDistName(distName);
                awcModel.setAwcName(awcName);

                awcModelList.add(awcModel);
            }
            recyclerView.setAdapter(cashProfileAdapter);
            cashProfileAdapter.notifyDataSetChanged();



        } catch (JSONException e) {
            e.printStackTrace();
        }  */


     try {
            JSONObject jsonObject = new JSONObject(jsonAwcData);

            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i =0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String sectorName = jsonObject1.getString("PROJECT_NAME");
                String distName = jsonObject1.getString("DISTRICT_NAME");
                String awcName = jsonObject1.getString("AWC_NAME");

                Log.d("awcName",awcName);

                AWCModel awcModel = new AWCModel();
                awcModel.setSectorName(sectorName);
                awcModel.setDistName(distName);
                awcModel.setAwcName(awcName);

                awcModelList.add(awcModel);
            }
            recyclerView.setAdapter(cashProfileAdapter);
            cashProfileAdapter.notifyDataSetChanged();



        } catch (JSONException e) {
            e.printStackTrace();
        }




    }


    @Override
    public void onBackPressed() {
        final Dialog dialogCoupon;
        dialogCoupon = new Dialog(this);
        dialogCoupon.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCoupon.setContentView(R.layout.back_dialog);
        dialogCoupon.setCancelable(false);
        dialogCoupon.setCanceledOnTouchOutside(true);

        TextView label = (TextView)dialogCoupon.findViewById(R.id.editCoupon);
        label.setText("You are about to exit!\nAre you sure?");
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
//                Intent intentback = new Intent(getApplicationContext(), Login.class);
//                startActivity(intentback);

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

