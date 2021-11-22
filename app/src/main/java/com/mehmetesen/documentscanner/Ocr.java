package com.mehmetesen.documentscanner;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.mehmetesen.documentscanner.databinding.ActivityOcrBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class Ocr extends AppCompatActivity {
    ActivityOcrBinding binding;

    Bitmap bitmap;
    private static  final int REQUEST_CAMERA_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOcrBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if(ContextCompat.checkSelfPermission(Ocr.this, Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Ocr.this,new String[] {Manifest.permission.CAMERA},REQUEST_CAMERA_CODE);


        }
        binding.capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(Ocr.this);

            }
        });

        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scanned_text = binding.text.getText().toString();
                copyToClipBoard(scanned_text);

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                Uri resultUri =result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),resultUri);
                    getTextFromImage(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    private void getTextFromImage(Bitmap bitmap){

        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();

        if(!recognizer.isOperational()){
            Toast.makeText(Ocr.this, "Error occured", Toast.LENGTH_SHORT).show();

        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<textBlockSparseArray.size();i++){
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");


            }
            binding.text.setText(stringBuilder.toString());
            binding.capture.setText("Ocr Again");
            binding.copy.setVisibility(View.VISIBLE);


        }
    }

    private void copyToClipBoard(String text){
        ClipboardManager clipBoard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data ",text);
        clipBoard.setPrimaryClip(clip);
        Toast.makeText(Ocr.this, "Copied to clipboard  ", Toast.LENGTH_LONG).show();
    }
}