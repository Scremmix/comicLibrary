package com.example.comiclibrary;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import org.w3c.dom.Text;

public class FumettiAdapter extends RecyclerView.Adapter<FumettiAdapter.FumettiHolder>  {
    private Context context;
    private  Cursor contenuto;
    public FumettiAdapter(Context context, Cursor contenuto){
        this.context=context;
        this.contenuto=contenuto;
     }

    @NonNull
    @Override
    public FumettiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.lista_fumetti, parent, false);
        return new FumettiHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FumettiHolder holder, int position) {
        if(contenuto != null)
        {
            holder.copertina.setImageResource(R.drawable.icon);
            holder.titolo.setText("dwkefdpoewwok");
            holder.serie.setText("alallala");
            holder.disponibilita.setText("no");
        }
    }

    @Override
    public int getItemCount() {
        if(contenuto != null)
        {
            return contenuto.getCount();
        }else
            return 0;

    }

    public class FumettiHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView copertina;
        private TextView titolo, serie, disponibilita;

        public FumettiHolder(@NonNull View itemView) {
            super(itemView);
        }


    }
}
