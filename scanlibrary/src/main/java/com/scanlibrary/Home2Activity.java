package com.scanlibrary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class Home2Activity extends AppCompatActivity {
    private static final int  PERMISSION_CODE = 1000;
    private int gallerycode=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
    }

    public void check(View v){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission,PERMISSION_CODE);



            }else{
                OpenCamera();

            }

        }else{
            OpenCamera();

        }
    }
    public void folder(View view){
        Intent intent = new Intent(this, Folders2.class);
        startActivity(intent);

    }
    public void gallery(View view){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED &&checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission,gallerycode);
            }else{
                opengallery();

            }
        }else{
            opengallery();

        }

    }
    public void ocr(View view){
        Intent intent = new Intent(this, Ocr2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
    private void OpenCamera() {
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_CAMERA;
        Intent intent = new Intent(this, ScanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE,preference);
        startActivityForResult(intent,REQUEST_CODE);
    }
    private void opengallery(){
        int REQUEST_CODE = 99;
        int preference = ScanConstants.OPEN_MEDIA;
        Intent intent = new Intent(this, ScanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE,preference);
        startActivityForResult(intent,REQUEST_CODE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}