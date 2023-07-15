package com.app.oda_user;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class doctor_adapter extends RecyclerView.Adapter<doctor_view_holder> {

    List<doctorsModel> list;

    Context context;

    public doctor_adapter(List<doctorsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public doctor_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View doctorView = inflater.inflate(R.layout.list_item_doctors, parent, false);

        doctor_view_holder viewHolder = new doctor_view_holder(doctorView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull doctor_view_holder viewHolder, int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.doctor_name
                .setText(list.get(position).getDoctor_name());
        viewHolder.doctor_province
                .setText(list.get(position).getDoctor_province());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
