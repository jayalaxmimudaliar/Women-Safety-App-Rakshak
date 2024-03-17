package com.example.wsafety;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wsafety.databinding.ActivityLoginInBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class loginIn extends AppCompatActivity {

    ActivityLoginInBinding binding;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://real-4add2-default-rtdb.firebaseio.com/");

    EditText phone;
    Button loginButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
 phone=findViewById(R.id.login_phone);
 final EditText password=findViewById(R.id.login_password);
 loginButton=findViewById(R.id.login_button);
 TextView signUpRedirect =findViewById(R.id.signUpRedirectText);
        TextView forgetRedirect =findViewById(R.id.forgetRedirectText);










        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonetxt=phone.getText().toString();
                String passwordtxt=password.getText().toString();

                if (phonetxt.isEmpty()) {
                    Toast.makeText(loginIn.this,"Enter the Phone Number",Toast.LENGTH_SHORT).show();

                } else if (phonetxt.length()<10|| phonetxt.length()>10) {
                    Toast.makeText(loginIn.this,"Enter valid Phone Number",Toast.LENGTH_SHORT).show();

                }

                else if (passwordtxt.isEmpty()) {
                    binding.loginPassword.setError("Enter the Password");

                }
                else
                {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phonetxt))
                            {
                                final String getpassword=snapshot.child(phonetxt).child("Password").getValue(String.class);

                                if(getpassword.equals(passwordtxt))
                                {
                                    Toast.makeText(loginIn.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(loginIn.this, homepage.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(loginIn.this,"Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(loginIn.this,"Login Failed",Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });






        forgetRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginIn.this, forgetPassword.class)
                );
            }
        });

        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginIn.this,signUp.class ));
            }
        });
    }



}