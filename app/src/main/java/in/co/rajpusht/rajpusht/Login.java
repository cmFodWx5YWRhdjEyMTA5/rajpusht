package in.co.rajpusht.rajpusht;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import extras.BaseUrl;
import extras.DbHelper;

public class Login extends AppCompatActivity {

    public static String awc_code="08110080101";
    public static String dist_code="08";
    public static String project_code="11";
    public static String sector_code="008";
    public static String village_code="101";
    public static  String surveyerId="1";
//    public static String awc_code="08110080101";

    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DbHelper db = new DbHelper(this);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = (EditText) findViewById(R.id.username
        );
        password = (EditText) findViewById(R.id.password);

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().length()==0 || password.getText().toString().length()==0) {
                    Toast.makeText(Login.this, "Enter  Username and  Password", Toast.LENGTH_SHORT).show();
                }
                else {
//validateLogin();

                    LoginDetails login = new LoginDetails();
                    login.execute(username.getText().toString(),password.getText().toString());
//
                }
            }
        });

    }

    public void validateLogin(){

    }

    public class LoginDetails  extends AsyncTask<String, String, String>

    {

        String uid;
        String result;
        String value;
        ProgressDialog asyncDialog ;

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String json = null;



            String name=params[0];
            String password=params[1];
//            String lon=params[2];

            try {

                HttpResponse response;

                JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("UserId",session.getUserID());

//                jsonObject.accumulate("Lat",lat);
//                jsonObject.accumulate("Long",lon);
//                jsonObject.accumulate("Remarks",name);



                json = jsonObject.toString();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(new BaseUrl().base_url+"restservice/surveyor/user/"+name+"/"+password);
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setEntity(new StringEntity(json, "UTF-8"));
//  httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

//                httpPost.setHeader("Accept-Encoding", "application/json");
//                httpPost.setHeader("Accept-Language", "en-US");
                response = httpClient.execute(httpPost);

//                String the_string_response = convertResponseToString(response);
                String sresponse = response.getEntity().toString();
                Log.w("QueingSystem", sresponse);
//                Log.w("QueingSystem", EntityUtils.toString(response.getEntity()));
                value= EntityUtils.toString(response.getEntity());

                Log.w("LoginDetails",value);


            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());

            }

            return value;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            asyncDialog = new ProgressDialog(Login.this);
            asyncDialog.setMessage("Please wait data Validating..");
            asyncDialog.show();


        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
//            Log.d("LoginDetails",aVoid);
//


            String flush="delete from assigned_location";

            SQLiteDatabase dbsflush = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

            Cursor cFlush = dbsflush.rawQuery(flush,null);
            cFlush.moveToFirst();
            cFlush.close();



            try {

                JSONObject josnMainObject = new JSONObject(aVoid);
                JSONArray jsonArrayAssignedLocation = josnMainObject.getJSONArray("assignedLocation");
                for(int i=0;i<jsonArrayAssignedLocation.length();i++){

                    JSONObject jsonSubObject = jsonArrayAssignedLocation.getJSONObject(i);
                    String awcNameHindi = jsonSubObject.getString("awcNameHindi");
                    String sectorcode = jsonSubObject.getString("sectorcode");
                    String distcode = jsonSubObject.getString("distcode");
                    String awccode = jsonSubObject.getString("awccode");
                    String projectcode = jsonSubObject.getString("projectcode");
                    String surveyorId = jsonSubObject.getString("surveyorId");
                    String villageName = jsonSubObject.getString("villageName");
                    String awcName = jsonSubObject.getString("awcName");
                    String villageNameHindi = jsonSubObject.getString("villageNameHindi");
                    String villageCode = jsonSubObject.getString("villageCode");

                    String insertLocation="INSERT INTO ASSIGNED_LOCATION (DIST_CODE,PROJECT_CODE,SECTOR_CODE,VILLAGE_CODE," +
                            "VILLAGE_ENG,VILLAGE_HINDI,AWC_CODE,SURVEYOR_NAME,SURVEYOR_ID) VALUES ('"
                            + distcode +"','"+projectcode +"','"+ sectorcode + "','"+ villageCode +"','"+ villageName +
                            "','"+ villageNameHindi + "','"+ awccode +"','soumya',"+surveyorId +")";

                    SQLiteDatabase dbs = openOrCreateDatabase("ranjeettest", MODE_PRIVATE, null);

                    Cursor c = dbs.rawQuery(insertLocation,null);
                    c.moveToFirst();
                    c.close();



                }

                asyncDialog.dismiss();
                Intent i = new Intent(getApplicationContext(), DashBoard.class);
                    startActivity(i);
                    finish();


            } catch (Exception e) {
                Log.d("Ranjeet", "ranjeet Error" + e.toString());
                Toast.makeText(Login.this, "Enter Valid Username and Password", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
                asyncDialog.dismiss();
//                Intent i = new Intent(getApplicationContext(), DashBoard.class);
//                startActivity(i);
//                finish();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();


        }


    }


}
