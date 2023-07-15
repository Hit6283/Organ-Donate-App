package com.app.oda_user;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class recipient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        CardView cardView1 = findViewById(R.id.cardView1);

        cardView1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),request_organ.class);
            startActivity(intent);
        });

        CardView cardView2 = findViewById(R.id.cardView2);

        cardView2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),contact_us.class);
            startActivity(intent);
        });
    }
}