package com.example.wsafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.wsafety.databinding.ActivityForgetPasswordBinding;


public class forgetPassword extends AppCompatActivity {
ActivityForgetPasswordBinding binding;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Create your account");
        progressDialog.setMessage("Please Wait");



        binding.forgetloginback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(forgetPassword.this,loginIn.class));
            }
        });


        binding.forgetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.loginEmail.getText().toString();
                progressDialog.dismiss();

                if (email.isEmpty()) {
                    binding.loginEmail.setError("Enter the email");

                } else
                {

                }
            }
        });
    }
}