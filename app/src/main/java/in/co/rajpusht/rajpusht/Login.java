package in.co.rajpusht.rajpusht;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    public static String awc_code="08110080101";
    public static String dist_code="08";
    public static String project_code="11";
    public static String sector_code="008";
    public static String village_code="101";
    public static  String surveyerId="1";
//    public static String awc_code="08110080101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(i);
            }
        });

    }

}
