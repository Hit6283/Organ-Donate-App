package com.app.oda_user;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class doctor_view_holder extends RecyclerView.ViewHolder {
    TextView doctor_name;
    TextView doctor_province;
    View view;
    public doctor_view_holder(@NonNull View itemView) {
        super(itemView);
        doctor_name
                = (TextView)itemView
                .findViewById(R.id.list_doctor_name);
        doctor_province
                = (TextView)itemView
                .findViewById(R.id.list_province);
        view  = itemView;
    }
}
