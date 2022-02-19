package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.net.URI;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class ViewPdf extends AppCompatActivity {

    PDFView pdfView;
    String videoLoc;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView = findViewById(R.id.pdfView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            videoLoc = (String) bundle.get("pdfExtra");
            uri = Uri.fromFile(new File(videoLoc));
        }

        pdfView.fromUri(uri)
                .enableSwipe(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .spacing(10)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .load();

    }
}