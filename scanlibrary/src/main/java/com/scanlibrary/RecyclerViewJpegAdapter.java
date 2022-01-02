package com.scanlibrary;

import static com.scanlibrary.ScanFragment.jpegbitmaplist;
import static com.scanlibrary.ScanFragment.nextclick;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewJpegAdapter extends RecyclerView.Adapter<RecyclerViewJpegAdapter.RowHolder> {
    ArrayList<File> jpegArraylist = new ArrayList<>();

    public RecyclerViewJpegAdapter(ArrayList<File> jpegArraylist){
        this.jpegArraylist =jpegArraylist;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.jpeg_row,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RowHolder holder, int position) {
        holder.imageView.setImageBitmap(jpegbitmaplist.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),JpegViewActivity.class);
                nextclick =holder.getAbsoluteAdapterPosition();
                intent.putExtra("pos",nextclick);
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return jpegArraylist.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewjpeg);
        }
    }
}
