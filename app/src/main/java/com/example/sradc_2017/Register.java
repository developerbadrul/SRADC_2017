package com.example.sradc_2017;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText editEmail, txtPassword, txtConfirmPassword;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editEmail = findViewById(R.id.editText2);
        txtPassword = findViewById(R.id.editText4);
        txtConfirmPassword = findViewById(R.id.editText3);
        btn_register = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmpassword = txtConfirmPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)){
                    Toast.makeText(Register.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                if (password.equals(confirmpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Register.this, "Registration Complete, Login Please", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(Register.this, "Registration Faild, Retry", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                }
            }
        });
    }
}
