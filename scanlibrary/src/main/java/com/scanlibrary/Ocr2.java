package com.scanlibrary;

import static com.scanlibrary.JpegViewActivity.posi;
import static com.scanlibrary.ScanFragment.jpegbitmaplist;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.scanlibrary.databinding.ActivityOcr2Binding;

public class Ocr2 extends AppCompatActivity {
    ActivityOcr2Binding binding;
    Bitmap bitmap;
    private static  final int REQUEST_CAMERA_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOcr2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getTextFromImage(jpegbitmaplist.get(posi));






        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scanned_text = binding.text.getText().toString();
                copyToClipBoard(scanned_text);

            }
        });


    }



    private void getTextFromImage(Bitmap bitmap){

        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();

        if(!recognizer.isOperational()){
            Toast.makeText(Ocr2.this, "Error occured", Toast.LENGTH_SHORT).show();

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



        }
    }

    private void copyToClipBoard(String text){
        ClipboardManager clipBoard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied data ",text);
        clipBoard.setPrimaryClip(clip);
        Toast.makeText(Ocr2.this, "Copied to clipboard  ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(Ocr2.this,Home2Activity.class);
        startActivity(intent);
    }
}