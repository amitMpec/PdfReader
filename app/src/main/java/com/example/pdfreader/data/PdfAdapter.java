package com.example.pdfreader.data;

import android.content.Context;
import android.content.Intent;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdfreader.R;
import com.example.pdfreader.ViewPdf;

import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.ViewHolder> {

    ArrayList<PojoClassPdf> pdfs;
    Context context;

    public PdfAdapter(ArrayList<PojoClassPdf> pdfs, Context context) {
        this.pdfs = pdfs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title1 = pdfs.get(position).getTitle();
        String date1 = pdfs.get(position).getDate();
        String location = pdfs.get(position).getLocation();
        double fileSize;
        double inKb = Double.valueOf(date1) / 1024;
        fileSize = inKb / 1024;
        holder.date.setText(String.format("%.2f", fileSize) + "MB");
        holder.title.setText(title1);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPdf.class);
                intent.putExtra("pdfExtra", location);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, date;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.pdfPic);
            title = itemView.findViewById(R.id.titleText);
            date = itemView.findViewById(R.id.dateText);
            constraintLayout = itemView.findViewById(R.id.pdfCard);
        }
    }
}
