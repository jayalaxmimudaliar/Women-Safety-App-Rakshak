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

import com.example.wsafety.databinding.ActivitySignUpBinding;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class signUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://real-4add2-default-rtdb.firebaseio.com/");
EditText phone;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final EditText name=findViewById(R.id.signUp_name);
          phone=findViewById(R.id.signup_phone);
        final EditText email=findViewById(R.id.signUp_email);
        final EditText password=findViewById(R.id.signUp_password);

        Button signup=findViewById(R.id.signup_button);
        TextView loginRedirect=findViewById(R.id.loginRedirectText);




   signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nametxt=name.getText().toString();
                final String phonetxt=phone.getText().toString();
                final String emailtxt=email.getText().toString();
               final  String passwordtxt=password.getText().toString();

                if(nametxt.isEmpty())
                {
                    binding.signUpName.setError("Enter the name");
                } else if (emailtxt.isEmpty()) {
                    binding.signUpEmail.setError("Enter the email");

                } else if (passwordtxt.isEmpty()) {
                    binding.signUpPassword.setError("Enter the Password");

                } else if (phonetxt.isEmpty() ) {
                    Toast.makeText(signUp.this,"Enter the Phone Number",Toast.LENGTH_SHORT).show();

                } else if (phonetxt.length()<10|| phonetxt.length()>10) {
                    Toast.makeText(signUp.this,"Enter valid Phone Number",Toast.LENGTH_SHORT).show();

                } else
                {

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phonetxt))
                            {
                                Toast.makeText(signUp.this,"Phone Number is Alread Registered",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("users").child(phonetxt).child("Name").setValue(nametxt);
                                databaseReference.child("users").child(phonetxt).child("Email").setValue(emailtxt);
                                databaseReference.child("users").child(phonetxt).child("Password").setValue(passwordtxt);

                                Toast.makeText(signUp.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }


            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signUp.this,loginIn.class ));
            }
        });




    }


}