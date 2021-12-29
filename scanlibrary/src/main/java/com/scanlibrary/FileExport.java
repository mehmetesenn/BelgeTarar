package com.scanlibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class FileExport extends AppCompatActivity {
   TextView pdffolder;
    String openpdf;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_export);
        pdffolder = findViewById(R.id.pdfFolder);
        Intent pathPdf=getIntent();
        openpdf=pathPdf.getStringExtra("path");
        pdffolder.setText(openpdf);


    }
    public void cancel(View view){


        Intent intent = new Intent(FileExport.this, Home2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);

    }
    public void share(View view){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setDataAndType(Uri.parse(openpdf),"application/pdf");
        share.putExtra(Intent.EXTRA_STREAM,Uri.parse(openpdf));
        startActivity(Intent.createChooser(share,"pdf.."));


    }
    public void Openpdf(View view){
        /*
        Intent open=new Intent();
        open.setDataAndType(Uri.parse(openpdf),"application/pdf");
        Intent chooser = Intent.createChooser(open,"Open Pdf");
        startActivity(chooser);

         */
        Intent pdfview = new Intent(FileExport.this,PdfView.class);
        startActivity(pdfview);





    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(FileExport.this,Home2Activity.class);
        startActivity(intent);
    }
}