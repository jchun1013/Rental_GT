package com.example.joon1.project_gt.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joon1.project_gt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button loginButton;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();

        email = (EditText) findViewById(R.id.email_edit);
        password = (EditText) findViewById(R.id.password_edit);
        loginButton = (Button) findViewById((R.id.loginButton));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    public void attemptLogin() {
        String inputEmail = email.getText().toString();
        String inputPassword = password.getText().toString();

        if (TextUtils.isEmpty(inputEmail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(inputPassword)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //if (childValue.getBanned() == false) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, HomeActivity.class));
                    finish();
                } else {
//                        if (childValue.getEmailAddress().equals(editEmail.getText().toString())) {
//                            emailList(childValue.getEmailAddress());
//                        }
                    Toast.makeText(Login.this, "Login Attempt Failed", Toast.LENGTH_LONG).show();
                    return;
                }
//                } else {
//                    Toast.makeText(LoginActivity.this, "You have been banned!", Toast.LENGTH_LONG).show();
//                    return;
//                }
            }
        });
    }
}
