package com.app.oda_user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth mAuth;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CardView cardView1 = findViewById(R.id.cardView1);

        cardView1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), donor.class);
            startActivity(intent);
        });

        CardView cardView2 = findViewById(R.id.cardView2);

        cardView2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), doctor.class);
            startActivity(intent);
        });

        CardView cardView3 = findViewById(R.id.cardView3);

        cardView3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), recipient.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                mAuth.signOut();
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {

        Intent intent = new Intent(home.this,login.class);
        startActivity(intent);
        finish();

    }
}