package com.app.oda_user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    Button btnRegister, btnLoginHere;
    TextInputEditText txtName, txtEmail, txtPhone, txtPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnLoginHere = findViewById(R.id.btnLoginHere);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPassword);

        btnRegister.setOnClickListener(view -> createUser());

        btnLoginHere.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
            finish();
        });
    }

    private void createUser() {
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String phone = txtPhone.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            txtName.setError("Please Enter Name");
            txtName.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            txtEmail.setError("Please Enter Email");
            txtEmail.requestFocus();
        } else if (TextUtils.isEmpty(phone)) {
            txtPhone.setError("Please Enter Phone Number");
            txtPhone.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            txtPassword.setError("Please Enter Password");
            txtPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                Log.i("TAG",email);
                if (task.isSuccessful()) {
                    Toast.makeText(registration.this, "User Register Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registration.this, login.class));
                    finish();
                } else {
                    Toast.makeText(registration.this, "Registration Error:" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}