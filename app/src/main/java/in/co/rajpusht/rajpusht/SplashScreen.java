package in.co.rajpusht.rajpusht;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.webkit.WebView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

//                if(session.isLogin()) {
//
//
//                    Intent itentLogin = new Intent(getApplicationContext(),Dashboard.class);
//                    startActivity(itentLogin);
//
//                    finish();
//
//                }else {

                    Intent i = new Intent(SplashScreen.this, Login.class);
                    startActivity(i);
//                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
