package com.scanlibrary;

import static com.scanlibrary.ResultFragment.file;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromFile(file).load();






    }
    public void Convert(View view){


        Intent Text = new Intent(PdfView.this,PdfText.class);
        startActivity(Text);
    }
}