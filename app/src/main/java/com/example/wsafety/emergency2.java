package com.example.wsafety;

import static android.Manifest.permission.*;
import static android.Manifest.permission.CALL_PHONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class emergency2 extends AppCompatActivity {

    LinearLayout national,road,fire,railway,pregnancy,child,ambulance,women;
    private static final int PHONE_CALL_REQUEST=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency2);
        national=findViewById(R.id.national);
        road=findViewById(R.id.road);
        fire=findViewById(R.id.fire);
        railway=findViewById(R.id.railway);
        pregnancy=findViewById(R.id.pregnancy);
        child=findViewById(R.id.child);
        ambulance=findViewById(R.id.ambulance);
        women=findViewById(R.id.women);

        if (ContextCompat.checkSelfPermission(emergency2.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(emergency2.this, new String[]{android.Manifest.permission.CALL_PHONE},PHONE_CALL_REQUEST);
        }

        national.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "112"));
                startActivity(intent);
            }
        });

        road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1073"));
                startActivity(intent);
            }
        });
        railway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "182"));
                startActivity(intent);
            }
        });
        pregnancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "108"));
                startActivity(intent);
            }
        });

        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1098"));
                startActivity(intent);
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "101"));
                startActivity(intent);
            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "102"));
                startActivity(intent);
            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "1091"));
                startActivity(intent);
            }
        });

    }
}