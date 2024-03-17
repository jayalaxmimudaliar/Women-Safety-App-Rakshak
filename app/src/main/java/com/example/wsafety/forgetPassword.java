package com.example.wsafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wsafety.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forgetPassword extends AppCompatActivity {
ActivityForgetPasswordBinding binding;
EditText emailbox;
Button forgetbtn;
FirebaseAuth auth;
TextView backLogin;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailbox=findViewById(R.id.forget_email);
        forgetbtn=findViewById(R.id.forgetbutton);
        backLogin=findViewById(R.id.forgetloginback);
        auth=FirebaseAuth.getInstance();

        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forgetPassword.this, loginIn.class));
            }
        });

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=emailbox.getText().toString();

                if(userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
                {
                    emailbox.setError("Enter proper Email Address");
                    emailbox.requestFocus();
                }

               FirebaseAuth.getInstance().sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forgetPassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(forgetPassword.this, "Unable to send Email", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


       /* binding=ActivityForgetPasswordBinding.inflate(getLayoutInflater());
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
        });*/
    }
}