package com.example.wsafety;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class homepage2 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton s;
    ImageButton p, h;
    Button t;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage2);
        p = findViewById(R.id.period);
        s = findViewById(R.id.sos);
        h = findViewById(R.id.helpline);
        t = findViewById(R.id.tip);


        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences(loginIn.PREFS_NAME,0);
                SharedPreferences.Editor editor = preferences.edit();

                Intent intent = new Intent(homepage2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage2.this, period_main.class);
                startActivity(intent);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(homepage2.this, emergency2.class));
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(homepage2.this, tips.class));
            }
        });




    }

    public void showPopup(View v)
    {
        PopupMenu popUp=new PopupMenu(this,v);
        popUp.setOnMenuItemClickListener(this);
        popUp.inflate(R.menu.popup_logout);
        popUp.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logOut:
                SharedPreferences preferences =getSharedPreferences(loginIn.newPref,0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("hasLoggedIn",false);
                editor.commit();

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();

                editor1.clear();
                editor1.apply();
                finish();

                startActivity(new Intent(homepage2.this, loginIn.class));
                return true;
            default:
                return false;

        }
    }
}