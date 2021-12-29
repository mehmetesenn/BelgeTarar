package com.scanlibrary;

import static com.scanlibrary.ScanFragment.nextclick;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scanlibrary.databinding.JpegRowBinding;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewJpegAdapter extends RecyclerView.Adapter<RecyclerViewJpegAdapter.RowHolder> {
    ArrayList<File> jpegArraylist;

    public RecyclerViewJpegAdapter(ArrayList<File> jpegArraylist) {
        this.jpegArraylist=jpegArraylist;

    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     JpegRowBinding binding = JpegRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
     return new RowHolder(binding);
    }

    @Override
    public  void onBindViewHolder(@NonNull final RecyclerViewJpegAdapter.RowHolder holder ,  int position) {
        holder.jpegRowBinding.recyclerRowJpeg.setText(jpegArraylist.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), JpegViewActivity.class);
                nextclick=holder.getAbsoluteAdapterPosition();
                intent.putExtra("pos",nextclick);
                holder.itemView.getContext().startActivity(intent);
            }
        });






    }

    @Override
    public int getItemCount() {
        return jpegArraylist.size();
    }

    public  class RowHolder extends RecyclerView.ViewHolder {
        private JpegRowBinding jpegRowBinding;

        public RowHolder(@NonNull JpegRowBinding jpegRowBinding) {
            super(jpegRowBinding.getRoot());
            this.jpegRowBinding = jpegRowBinding;

        }
    }
}
