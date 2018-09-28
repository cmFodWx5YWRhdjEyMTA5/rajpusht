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

import java.net.HttpURLConnection;

import in.co.rajpusht.rajpusht.R;

import static in.co.rajpusht.rajpusht.tool.Constants.CASH_BASE_URL;

/**
 * Created by LENOVO on 20-Sep-18.
 */

public class CashFillFormsActivity extends AppCompatActivity {

    private Button bf1 ,bf2, bf3, bf4, bf5, buttonExit;
    String aadharCardNo;
    String aadharEnrollment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_edit);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        TextView textViewname = (TextView)findViewById(R.id.textBeneForms);
        textViewname.setText(name);


        TextView toolbarText = (TextView)findViewById(R.id.toolbarHeader);
        toolbarText.setText("MAMTA");

        ImageView toolbarBack = (ImageView)findViewById(R.id.toolbarBack);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        bf1 = (Button)findViewById(R.id.buttonForm1);
        bf2 = (Button)findViewById(R.id.buttonForm2);
        bf3 = (Button)findViewById(R.id.buttonForm3);
        bf4 = (Button)findViewById(R.id.buttonForm4);
        bf5 = (Button)findViewById(R.id.buttonForm5);

        bf1.setText("FILL");
        bf2.setText("FILL");
        bf3.setText("FILL");
        bf4.setText("FILL");
        bf5.setText("FILL");

        buttonExit = (Button)findViewById(R.id.buttonExit);


        SharedPreferences preferences2 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String aadharId = preferences2.getString("aadharId", "Err");
        String [] aArray = aadharId.split("#");
        aadharCardNo = aArray[0];
        aadharEnrollment = aArray[1];
        new FetchByAadharIDAsync().execute();




        bf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashFillFormsActivity.this, CashAddNewBenefeciaryActivity.class);
              //  intent.putExtra("edit_form_1","edit_form_1");
                startActivity(intent);
            }
        });


        bf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashFillFormsActivity.this, CashSecondThirdActivity.class);
              //  intent.putExtra("edit_form_2", "edit_form_2");
                startActivity(intent);
            }
        });

        bf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashFillFormsActivity.this, CashThirdActivity.class);
                //intent.putExtra("edit_form_3", "edit_form_3");
                startActivity(intent);
            }
        });

        bf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashFillFormsActivity.this, CashFourthActivity.class));
            }
        });

        bf5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashFillFormsActivity.this, CashFifthActivity.class));
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }


    @Override
    protected void onResume() {
        super.onResume();
        new FetchByAadharIDAsync().execute();

    }

    public class FetchByAadharIDAsync extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CashFillFormsActivity.this);
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
                jsonAadhar.put("aadharCardNo",aadharCardNo);
                jsonAadhar.put("aadharEnrollmentNo", aadharEnrollment);
                jsonAadharRoot.put("aadhar", jsonAadhar);


            } catch (JSONException e) {
                e.printStackTrace();
            }




            try
            {
                HttpResponse response;
                JSONObject jsonObject = new JSONObject();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(CASH_BASE_URL + "beneficiary/checkIfFormEditable");

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String mtoken = preferences.getString("token", "Not Saved");

                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", "Bearer "+ mtoken);

                httpPost.setEntity(new StringEntity(jsonAadharRoot.toString(), "UTF-8"));
                response = httpClient.execute(httpPost);

                String sresponse = response.getEntity().toString();
                value = EntityUtils.toString(response.getEntity());


                Log.d("CashFillFormsActivity", value);














            }
            catch (Exception e)
            {

            }

            return value;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            disableButtons(s);
            progressDialog.dismiss();
        }
    }

    public void disableButtons(String json)
    {

        String b1Enable, b2Enable = null, b3Enable = null, b4Enable = null, b5Enable = null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");


            for (int i = 0 ; i< jsonArray.length(); i++)
            {
                b2Enable = jsonArray.getString(0);
                b3Enable= jsonArray.getString(1);
                b4Enable = jsonArray.getString(2);
                b5Enable = jsonArray.getString(3);
               
                
            }
            
            bf1.setEnabled(false);
            bf1.setBackgroundResource(R.drawable.my_button_flat_green);
            bf1.setText("FILLED");
            
            //null : Disabled
            //false: Filled
            //true : Fill
            if(b2Enable.equalsIgnoreCase("null"))
            {
                bf2.setEnabled(false);
                bf2.setBackgroundResource(R.drawable.my_button_flat_disable);
                bf2.setText("Disabled");
            }
            else if(b2Enable.equalsIgnoreCase("false"))
            {
                bf2.setEnabled(false);
                bf2.setBackgroundResource(R.drawable.my_button_flat_green);
                bf2.setText("FILLED");
            } else
            {
                bf2.setEnabled(true);
                bf2.setBackgroundResource(R.drawable.my_button_color_primary);
                bf2.setText("FILL");
            }
            //................//

            if(b3Enable.equalsIgnoreCase("null"))
            {
                bf3.setEnabled(false);
                bf3.setBackgroundResource(R.drawable.my_button_flat_disable);
                bf3.setText("Disabled");
            }
            else if(b3Enable.equalsIgnoreCase("false"))
            {
                bf3.setEnabled(false);
                bf3.setBackgroundResource(R.drawable.my_button_flat_green);
                bf3.setText("FILLED");
            } else
            {
                bf3.setEnabled(true);
                bf3.setBackgroundResource(R.drawable.my_button_color_primary);
                bf3.setText("FILL");
            }

            //................//

            if(b4Enable.equalsIgnoreCase("null"))
            {
                bf4.setEnabled(false);
                bf4.setBackgroundResource(R.drawable.my_button_flat_disable);
                bf4.setText("Disabled");
            }
            else if(b4Enable.equalsIgnoreCase("false"))
            {
                bf4.setEnabled(false);
                bf4.setBackgroundResource(R.drawable.my_button_flat_green);
                bf4.setText("FILLED");
            } else
            {
                bf4.setEnabled(true);
                bf4.setBackgroundResource(R.drawable.my_button_color_primary);
                bf4.setText("FILL");
            }


            if(b5Enable.equalsIgnoreCase("null"))
            {
                bf5.setEnabled(false);
                bf5.setBackgroundResource(R.drawable.my_button_flat_disable);
                bf5.setText("Disabled");
            }
            else if(b5Enable.equalsIgnoreCase("false"))
            {
                bf5.setEnabled(false);
                bf5.setBackgroundResource(R.drawable.my_button_flat_green);
                bf5.setText("FILLED");
            } else
            {
                bf5.setEnabled(true);
                bf5.setBackgroundResource(R.drawable.my_button_color_primary);
                bf5.setText("FILL");
            }

            
            

            Log.d("CashFillFormsActivity2", jsonArray.toString());




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
