package com.app.oda_user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class doctor extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    doctor_adapter adapter;
    RecyclerView recyclerView;
    ProgressBar progress_circular;
    List<doctorsModel> doctorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.mDoctors);
        progress_circular = findViewById(R.id.progressBar);

        firebaseFirestore.collection("admins").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progress_circular.setVisibility(View.VISIBLE);
                List<DocumentSnapshot> snap = task.getResult().getDocuments();
                for (int i = 0; i < snap.size(); i++) {
                    firebaseFirestore.collection("admins").document(snap.get(i).getId()).collection("doctors").get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            List<DocumentSnapshot> snapshots = task1.getResult().getDocuments();
                            for (int j = 0; j < snapshots.size(); j++) {
                                doctorsList.add(new doctorsModel(
                                        snapshots.get(j).get("doctor_name").toString(),
                                        snapshots.get(j).get("doctor_province").toString()));
                            }
//                            Log.i("TAG", String.valueOf(snap.size()));
//                            Log.i("TAG", String.valueOf(doctorsList.size()));
                            adapter = new doctor_adapter(doctorsList, getApplication());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(doctor.this));
                            progress_circular.setVisibility(View.GONE);
                        } else {
                            progress_circular.setVisibility(View.GONE);
                        }
                    });
                }

            } else {
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}
