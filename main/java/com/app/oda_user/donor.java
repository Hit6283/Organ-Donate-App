package com.app.oda_user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class donor extends AppCompatActivity {

    Toolbar toolbar;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CardView cardView1 = findViewById(R.id.cardView1);

        cardView1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),donate_organ.class);
            startActivity(intent);
        });

        CardView cardView2 = findViewById(R.id.cardView2);

        cardView2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),contact_us.class);
            startActivity(intent);
        });
    }
}