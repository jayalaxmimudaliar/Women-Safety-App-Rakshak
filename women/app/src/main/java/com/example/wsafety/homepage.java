package com.example.wsafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class homepage extends AppCompatActivity {
    ImageButton s;
    ImageButton p, h, t, pol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        p = findViewById(R.id.period);
        s = findViewById(R.id.sos);
        h = findViewById(R.id.helpline);
        t = findViewById(R.id.tips);
        pol = findViewById(R.id.police);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this, period_main.class);
                startActivity(intent);
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(homepage.this, emergency2.class));
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(homepage.this, tips.class));
            }
        });

        pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(homepage.this, police.class));
            }
        });


    }
}