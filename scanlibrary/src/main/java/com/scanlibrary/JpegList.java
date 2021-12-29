package com.scanlibrary;

import static com.scanlibrary.ScanFragment.jpegFileList;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JpegList extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewJpegAdapter recyclerViewJpegAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpeg_list);
       recyclerView = findViewById(R.id.recyclerView);
       recyclerViewJpegAdapter=new RecyclerViewJpegAdapter(jpegFileList);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setAdapter(recyclerViewJpegAdapter);








    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(JpegList.this, Home2Activity.class);
        startActivity(intent);
    }
}