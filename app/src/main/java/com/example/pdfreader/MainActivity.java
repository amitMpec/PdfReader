package com.example.pdfreader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pdfreader.data.PdfAdapter;
import com.example.pdfreader.data.PojoClassPdf;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<PojoClassPdf> pdfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getPdfList();
    }

    protected void getPdfList() {
        pdfList = new ArrayList<>();
        Uri collection;

        final String sortOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        final String selection = MediaStore.Files.FileColumns.MIME_TYPE + " = ?";

        final String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
        final String[] selectionArgs = new String[]{mimeType};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Files.getContentUri("external");
        }

        try (Cursor cursor = getContentResolver().query(collection, null, selection, selectionArgs, sortOrder)) {
            assert cursor != null;

            if (cursor.moveToFirst()) {

                int columnData = cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE);
                int columnName = cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
                int columnLocation = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);

                do {
                    pdfList.add(new PojoClassPdf(cursor.getString(columnData), cursor.getString(columnName), cursor.getString(columnLocation)));
                    Log.d("myPdf", "getPdf: " + cursor.getString(columnData));
                    recyclerView.setAdapter(new PdfAdapter(pdfList, this));
                    //you can get your pdf files
                } while (cursor.moveToNext());
            }
        }

    }
}