package com.app.oda_user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    Button btnLogin,btnRegisterHere,btnForgetPassword;
    TextInputEditText txtEmail,txtPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRegisterHere = findViewById(R.id.btnRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgetPassword = findViewById(R.id.btnForgetPassword);

        mAuth = FirebaseAuth.getInstance();

        btnForgetPassword.setOnClickListener(view -> {

            EditText resetEmail = new EditText(view.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter Your Email to Received Reset Link.");
            passwordResetDialog.setView(resetEmail);

            passwordResetDialog.setPositiveButton("Yes", (dialogInterface, i) -> {

                String mail = resetEmail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(unused -> Toast.makeText(login.this, "Reset Link Sent to Your Email", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(login.this, "Error!!Reset Link Not Sent" +e.getMessage(), Toast.LENGTH_SHORT).show());
            });

            passwordResetDialog.setNegativeButton("No", (dialogInterface, i) -> {

            });

            passwordResetDialog.create().show();
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(login.this, home.class));
            finish();
        }

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(view -> loginUser());

        btnRegisterHere.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),registration.class);
            startActivity(intent);
            finish();
        });
    }

    private void loginUser() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            txtEmail.setError("Please Enter Email");
            txtEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            txtPassword.setError("Please Enter Password");
            txtPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, home.class));
                    finish();
                } else {
                    Toast.makeText(login.this, "Login Error:" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}