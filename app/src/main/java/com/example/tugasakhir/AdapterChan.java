package com.example.tugasakhir;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterChan extends RecyclerView.Adapter<AdapterChan.ViewHolder> {
    @NonNull
    @Override
    public AdapterChan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChan.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
