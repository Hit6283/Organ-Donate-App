package com.app.oda_user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class request_organ extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mOrganRequests;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_organ);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();
        mOrganRequests = findViewById(R.id.mOrganRequests);

        //Query
        Query query = firebaseFirestore.collection("users").document(user.getUid()).collection("user requests");
        //RecyclerOptions
        FirestoreRecyclerOptions<organRequestModel> options = new FirestoreRecyclerOptions.Builder<organRequestModel>()
                .setQuery(query, organRequestModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<organRequestModel, organRequestViewHolder>(options) {
            @NonNull
            @Override
            public organRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_organ_request, parent, false);
                return new organRequestViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull organRequestViewHolder holder, int position, @NonNull organRequestModel model) {
                holder.list_request_name.setText(model.getRecipient_name());
                holder.list_requested_organ.setText(model.getRequestedOrgan());
            }
        };

        mOrganRequests.setHasFixedSize(false);
        mOrganRequests.setLayoutManager(new LinearLayoutManager(this));
        mOrganRequests.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_reqOrgan);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), request_organ_form.class);
            startActivity(intent);
        });
    }

    private static class organRequestViewHolder extends RecyclerView.ViewHolder {

        private final TextView list_request_name;
        private final TextView list_requested_organ;

        public organRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            list_request_name = itemView.findViewById(R.id.list_request_name);
            list_requested_organ = itemView.findViewById(R.id.list_requested_organ);
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