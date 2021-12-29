package com.scanlibrary;

import static com.scanlibrary.ResultFragment.file;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tom_roush.pdfbox.android.PDFBoxResourceLoader;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;

import java.io.IOException;

public class PdfText extends AppCompatActivity {
    EditText textfrompdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_text);
        textfrompdf = findViewById(R.id.textfrompdf);
        PDFBoxResourceLoader.init(getApplicationContext());

        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String ocr_text = pdfTextStripper.getText(document);
            textfrompdf.setText(ocr_text);




        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}