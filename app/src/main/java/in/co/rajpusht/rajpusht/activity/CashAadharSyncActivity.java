package in.co.rajpusht.rajpusht.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.co.rajpusht.rajpusht.R;

/**
 * Created by LENOVO on 12-Sep-18.
 */

public class CashAadharSyncActivity extends Activity {
    EditText fieldAadhar;
    Button btnSyncAadhar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_aadhar_sync);

        fieldAadhar = (EditText)findViewById(R.id.editTextAadhar);
        btnSyncAadhar = (Button)findViewById(R.id.buttonAadharSync);

        btnSyncAadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashAadharSyncActivity.this, CashAddNewBenefeciaryActivity.class));
                finish();
            }
        });

    }
}