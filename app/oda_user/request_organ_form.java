package com.app.oda_user;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class request_organ_form extends AppCompatActivity {

    String[] items = {"Eyes","Kidneys (2)","One Kidney","Lungs (2)","One Lung","Liver","Heart","Pancreas","Intestines"};

    AutoCompleteTextView selectOrgan;
    ArrayAdapter<String> adapterItems;

    TextInputEditText txtRequestName,txtRequestBirth,txtRequestAddress,txtRequestPhone,txtRequestBloodGroup,txtRequestNote;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_organ_form);

        selectOrgan = findViewById(R.id.select_request_organ);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);

        selectOrgan.setAdapter(adapterItems);

        selectOrgan.setOnItemClickListener((parent, view, position, id) -> {
            String item = parent.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(), "Organ: "+item, Toast.LENGTH_SHORT).show();
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        txtRequestName = findViewById(R.id.txtRequestName);
        txtRequestBirth = findViewById(R.id.txtRequestBirth);
        txtRequestAddress = findViewById(R.id.txtRequestAddress);
        txtRequestPhone = findViewById(R.id.txtRequestPhone);
        txtRequestBloodGroup = findViewById(R.id.txtRequestBloodGroup);
        selectOrgan = findViewById(R.id.select_request_organ);
        txtRequestNote = findViewById(R.id.txtRequestNote);

        Button btnRequestSubmit = findViewById(R.id.btnRequestSubmit);
        Map<String, Object> data1 = new HashMap<>();
        data1.put("recipient_id", user.getUid());

        db.collection("users").document(user.getUid()).set(data1);

        Map<String, Object> data = new HashMap<>();
        btnRequestSubmit.setOnClickListener(view -> {

            data.put("recipient_name", txtRequestName.getText().toString());
            data.put("recipient_birthdate", txtRequestBirth.getText().toString());
            data.put("recipient_address", txtRequestAddress.getText().toString());
            data.put("recipient_phone", txtRequestPhone.getText().toString());
            data.put("recipient_bloodGroup", txtRequestBloodGroup.getText().toString());
            data.put("requestedOrgan", selectOrgan.getText().toString());
            data.put("recipient_note", txtRequestNote.getText().toString());

            db.collection("users").document(user.getUid()).collection("user requests").document(txtRequestName.getText().toString()).set(data);

            Intent intent = new Intent(getApplicationContext(), request_organ.class);
            startActivity(intent);
            finish();
        });

        Calendar calendar = Calendar.getInstance();

        txtRequestBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(request_organ_form.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        txtRequestBirth.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}