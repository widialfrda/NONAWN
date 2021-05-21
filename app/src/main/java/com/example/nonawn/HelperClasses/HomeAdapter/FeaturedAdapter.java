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

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder>{

    //set array from helper class, data from featuredLocations variabel
    ArrayList<FeaturedHelperClass> featuredLocations;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        //get data from featured locations to helper class
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

        //getter didapat dari FeaturedHelperClass
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.tittle.setText(featuredHelperClass.getTittle());
        holder.desc.setText(featuredHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {

        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView tittle, desc;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            tittle = itemView.findViewById(R.id.featured_tittle);
            desc = itemView.findViewById(R.id.featured_desc);


        }
    }
}
