package com.example.wsafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences(loginIn.newPref,0);
                Boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);

                if(hasLoggedIn)
                {
                    startActivity(new Intent(SplashScreen.this, homepage2.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(SplashScreen.this, loginIn.class));
                    finish();

                }
            }
        },2000);

       /* new CountDownTimer(2000,500){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, signUp.class));
                SplashScreen.this.finish();
            }
        }.start();*/

    }
}