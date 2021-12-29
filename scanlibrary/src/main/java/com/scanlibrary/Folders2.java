package com.scanlibrary;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Folders2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders2);
    }
    public void openPdf(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));


    }
    public void openPicture(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));

    }
}