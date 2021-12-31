package com.scanlibrary;

import static com.scanlibrary.ScanFragment.jpegbitmaplist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class JpegViewActivity extends AppCompatActivity {
    ImageView imageView;
    public static int posi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpeg_view);
        imageView=findViewById(R.id.imageview);
        Intent intent = getIntent();
        posi=intent.getIntExtra("pos",0);
        System.out.println("posi:" + posi);
        imageView.setImageBitmap(jpegbitmaplist.get(posi));




    }
    public void ocr(View view){
        Intent intent = new Intent(JpegViewActivity.this,Ocr2.class);
        startActivity(intent);
    }
}