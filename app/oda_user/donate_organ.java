package com.app.oda_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class donate_organ extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mOrganDonors;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_organ);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();
        mOrganDonors = findViewById(R.id.mOrganDonors);

        //Query
        Query query = firebaseFirestore.collection("users").document(user.getUid()).collection("user donations");
        //RecyclerOptions
        FirestoreRecyclerOptions<organDonorsModel> options = new FirestoreRecyclerOptions.Builder<organDonorsModel>()
                .setQuery(query, organDonorsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<organDonorsModel, organDonateViewHolder>(options) {
            @NonNull
            @Override
            public organDonateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_organ_donate, parent, false);
                return new organDonateViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull organDonateViewHolder holder, int position, @NonNull organDonorsModel model) {
                holder.list_donate_name.setText(model.getDonor_name());
                holder.list_donated_organ.setText(model.getDonatedOrgan());
            }
        };

        mOrganDonors.setHasFixedSize(false);
        mOrganDonors.setLayoutManager(new LinearLayoutManager(this));
        mOrganDonors.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_donateOrgan);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), donate_an_organ_form.class);
            startActivity(intent);
        });
    }

    private static class organDonateViewHolder extends RecyclerView.ViewHolder {

        private final TextView list_donate_name;
        private final TextView list_donated_organ;

        public organDonateViewHolder(@NonNull View itemView) {
            super(itemView);

            list_donate_name = itemView.findViewById(R.id.list_donate_name);
            list_donated_organ = itemView.findViewById(R.id.list_donated_organ);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}