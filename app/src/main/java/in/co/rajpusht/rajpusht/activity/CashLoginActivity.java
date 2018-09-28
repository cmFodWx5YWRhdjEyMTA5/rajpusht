package in.co.rajpusht.rajpusht.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.tool.Constants;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by LENOVO on 11-Sep-18.
 */

public class CashLoginActivity extends Activity{

EditText fieldUserName, fieldPassword;
Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cash_login_layout);

        fieldUserName = (EditText)findViewById(R.id.editTextUserName);
        fieldPassword = (EditText)findViewById(R.id.editTextPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        fieldUserName.setText("roy@gmail.com");
        fieldPassword.setText("12345678");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(fieldUserName.getText().toString().equalsIgnoreCase("admin")
//                        && fieldPassword.getText().toString().equalsIgnoreCase("admin")) {
//                    Toast.makeText(CashLoginActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(CashLoginActivity.this, CashProfileActivity.class));
//                    finish();
//                } else {
//                    Toast.makeText(CashLoginActivity.this, "Enter Valid details", Toast.LENGTH_SHORT).show();
//
//                    //Testing phase
//                    startActivity(new Intent(CashLoginActivity.this, CashBeneficiaryActivity.class));
//                    finish();
//                }
                String email = fieldUserName.getText().toString();
                String passsword = fieldPassword.getText().toString();
                new CashLogin().execute(email, passsword);
               // Toast.makeText(CashLoginActivity.this, val, Toast.LENGTH_SHORT).show();

            }
        });





    }



    public  class CashLogin extends AsyncTask<String, String, String>
    {
        String value;
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(CashLoginActivity.this);
            progressDialog.setMessage("Please wait Validating data");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            String json = null;

            String name = params[0];
            String password = params[1];


            try {
//                HttpResponse response;
//                JSONObject jsonObject = new JSONObject();
//                json = jsonObject.toString();
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpPost httpPost = new HttpPost(Constants.BASE_URL + "restservice/surveyor/user/"+name+"/"+password);
//                httpPost.setHeader("Content-Type", "application/json");
//                httpPost.setEntity(new StringEntity(json, "UTF-8"));
//
//                response = httpClient.execute(httpPost);
//                String sresponse = response.getEntity().toString();
//
//                value = EntityUtils.toString(response.getEntity());
//                Log.d("loginResponse", sresponse);
//                Log.d("loginResponseValue", value);



                    HttpResponse response;
                    JSONObject jsonObject = new JSONObject();
                    json = jsonObject.toString();
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://192.168.2.126:3000/login");
                    httppost.setHeader("type", "AWW");
                     httppost.setEntity(new StringEntity(json, "UTF-8"));

                        //add data
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                        nameValuePairs.add(new BasicNameValuePair("username",name ));
                        nameValuePairs.add(new BasicNameValuePair("password", password));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        //execute http post

                        response = httpclient.execute(httppost);

                        String sresponse = response.getEntity().toString();
                         value = EntityUtils.toString(response.getEntity());
                        Log.d("loginResponse", sresponse);
                         Log.d("loginResponseValue", value);








            }catch (Exception e) {

            }

            return value;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  populateData(s);

            progressDialog.dismiss();
            //Toast.makeText(CashLoginActivity.this, s, Toast.LENGTH_SHORT).show();



        }

    }

    private void populateData(String json) {
       // Toast.makeText(this, json, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CashLoginActivity.this, CashProfileActivity.class);
        intent.putExtra("json_awc_data", json);
        startActivity(intent);
        finish();



    }

}
