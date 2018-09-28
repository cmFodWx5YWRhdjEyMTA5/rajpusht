package in.co.rajpusht.rajpusht.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import in.co.rajpusht.rajpusht.R;
import in.co.rajpusht.rajpusht.activity.CashProfileActivity;

/**
 * Created by LENOVO on 11-Sep-18.
 */

public class DialogAccountReason extends AppCompatActivity{

TextView textViewName, textViewReason, textViewInstallment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setTitle("");
        setContentView(R.layout.dialog_account_reason);



        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String reason = bundle.getString("reason");
        String installmnet = bundle.getString("rejected_form");

        textViewName = (TextView)findViewById(R.id.dialogName);
        textViewReason = (TextView)findViewById(R.id.dialogReason);
        textViewInstallment = (TextView)findViewById(R.id.dialogInstallment);

        textViewName.setText(name);
        textViewReason.setText(reason);
        textViewInstallment.setText(installmnet);

        ImageView dialogCancel = (ImageView)findViewById(R.id.dialogCancel);
        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }





}
