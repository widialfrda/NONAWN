package com.example.nonawn.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nonawn.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriHolder> {

    ArrayList<KategoriHelperClass> kategoriLocations;

    public KategoriAdapter(ArrayList<KategoriHelperClass> kategoriLocations){
        this.kategoriLocations = kategoriLocations;
    }

    @NonNull
    @Override
    public KategoriHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_design, parent, false);
        KategoriHolder kategoriHolder = new KategoriHolder(view);
        return kategoriHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriHolder holder, int position) {

        KategoriHelperClass kategoriHelperClass = kategoriLocations.get(position);

        holder.image_kategori.setImageResource(kategoriHelperClass.getImage());
        holder.tittle_kategori.setText(kategoriHelperClass.getTittle());
        holder.relativeLayout.setBackground(kategoriHelperClass.getColor());
    }

    @Override
    public int getItemCount() {
        return kategoriLocations.size();
    }

    public static class KategoriHolder extends RecyclerView.ViewHolder{

        ImageView image_kategori;
        TextView tittle_kategori;
        RelativeLayout relativeLayout;

        public KategoriHolder (@NonNull View itemView) {
            super(itemView);

            //Hooks
            relativeLayout = itemView.findViewById(R.id.categ_bg);
            image_kategori = itemView.findViewById(R.id.categ_image);
            tittle_kategori = itemView.findViewById(R.id.categ_tittle);


        }
    }
}
