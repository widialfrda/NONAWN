package com.example.nonawn.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nonawn.R;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoHolder> {
    ArrayList<PromoHelperClass> promoLocations;

    public PromoAdapter(ArrayList<PromoHelperClass> promoLocations) {
        this.promoLocations = promoLocations;
    }

    @NonNull
    @Override
    public PromoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_card_design, parent, false);
        PromoHolder promoHolder = new PromoHolder(view);
        return promoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PromoHolder holder, int position) {

        PromoHelperClass promoHelperClass = promoLocations.get(position);

        holder.image_promo.setImageResource(promoHelperClass.getImage());
        holder.tittle_promo.setText(promoHelperClass.getTittle());
        holder.desc_promo.setText(promoHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return promoLocations.size();
    }

    public static class PromoHolder extends RecyclerView.ViewHolder{

        ImageView image_promo;
        TextView tittle_promo, desc_promo;

        public PromoHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image_promo = itemView.findViewById(R.id.disc_image);
            tittle_promo = itemView.findViewById(R.id.disc_tittle);
            desc_promo = itemView.findViewById(R.id.disc_desc);

        }
    }
}
