package com.app.oda_user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class donate_an_organ_form extends AppCompatActivity {

    String[] items = {"Eyes","Kidneys (2)","One Kidney","Lungs (2)","One Lung","Liver","Heart","Pancreas","Intestines"};

    AutoCompleteTextView selectOrgan;
    ArrayAdapter<String> adapterItems;

    TextInputEditText txtDonateName,txtDonateBirth,txtDonateAddress,txtDonatePhone,txtDonateBloodGroup,txtDonateNote;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_an_organ_form);

        selectOrgan = findViewById(R.id.select_donate_organ);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);

        selectOrgan.setAdapter(adapterItems);

        selectOrgan.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), "Organ: "+item, Toast.LENGTH_SHORT).show();
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        txtDonateName = findViewById(R.id.txtDonateName);
        txtDonateBirth = findViewById(R.id.txtDonateBirth);
        txtDonateAddress = findViewById(R.id.txtDonateAddress);
        txtDonatePhone = findViewById(R.id.txtDonatePhone);
        txtDonateBloodGroup = findViewById(R.id.txtDonateBloodGroup);
        selectOrgan = findViewById(R.id.select_donate_organ);
        txtDonateNote = findViewById(R.id.txtDonateNote);

        Button btnDonateSubmit = findViewById(R.id.btnDonateSubmit);
        Map<String, Object> data1 = new HashMap<>();
        data1.put("donor_id", user.getUid());

        db.collection("users").document(user.getUid()).set(data1);

        Map<String, Object> data = new HashMap<>();
        btnDonateSubmit.setOnClickListener(view -> {

            data.put("donor_name", txtDonateName.getText().toString());
            data.put("donor_birthdate", txtDonateBirth.getText().toString());
            data.put("donor_address", txtDonateAddress.getText().toString());
            data.put("donor_phone", txtDonatePhone.getText().toString());
            data.put("donor_bloodGroup", txtDonateBloodGroup.getText().toString());
            data.put("donatedOrgan", selectOrgan.getText().toString());
            data.put("donor_note", txtDonateNote.getText().toString());

            db.collection("users").document(user.getUid()).collection("user donations").document(txtDonateName.getText().toString()).set(data);

            Intent intent = new Intent(getApplicationContext(), donate_organ.class);
            startActivity(intent);
            finish();
        });

        Calendar calendar = Calendar.getInstance();

        txtDonateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(donate_an_organ_form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        txtDonateBirth.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}