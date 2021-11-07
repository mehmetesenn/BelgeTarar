package com.mehmetesen.documentscanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(MainActivity.this,HomeActivity.class);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);

                }catch(InterruptedException e){
                    e.printStackTrace();


                }
                finally{
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();



    }




}