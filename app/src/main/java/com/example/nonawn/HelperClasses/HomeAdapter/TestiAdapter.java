package com.example.nonawn.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nonawn.R;

import java.util.ArrayList;

public class TestiAdapter extends RecyclerView.Adapter<TestiAdapter.TestiHolder>{
    ArrayList<TestiHelperClass> testiLocations;

    public TestiAdapter(ArrayList<TestiHelperClass> testiLocations){
        this.testiLocations = testiLocations;
    }

    @NonNull
    @Override
    public TestiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testi_card_design, parent,false);
        TestiHolder testiHolder = new TestiHolder(view);
        return testiHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestiHolder holder, int position) {

        TestiHelperClass testiHelperClass = testiLocations.get(position);

        holder.testi_image.setImageResource(testiHelperClass.getImage());
        holder.testi_tittle.setText(testiHelperClass.getTittle());
        holder.testi_rating.setRating(testiHelperClass.getRating());
        holder.testi_desc.setText(testiHelperClass.getDesc());
        holder.testi_user.setText(testiHelperClass.getUser());

    }

    @Override
    public int getItemCount() {
        return testiLocations.size();
    }


    public static class TestiHolder extends RecyclerView.ViewHolder {

        ImageView testi_image;
        TextView testi_tittle, testi_desc, testi_user;
        RatingBar testi_rating;

        public TestiHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            testi_image = itemView.findViewById(R.id.img_testi);
            testi_tittle = itemView.findViewById(R.id.tittle_testi);
            testi_rating = itemView.findViewById(R.id.rating_testi);
            testi_desc = itemView.findViewById(R.id.desc_testi);
            testi_user = itemView.findViewById(R.id.user_testi);
        }
    }
}
