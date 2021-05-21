package com.example.nonawn.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.nonawn.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;


    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {

            R.drawable.shopping,
            R.drawable.transfer,
            R.drawable.order_success,
            R.drawable.deliveries,
    };

    int headings[] = {

            R.string.shopping_tittle,
            R.string.transfer_tittle,
            R.string.order_success_tittle,
            R.string.delivery_tittle
    };

    int descriptions [] = {

            R.string.shopping_desc,
            R.string.transfer_desc,
            R.string.order_success_desc,
            R.string.delivery_desc

    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

        //Hooks
        ImageView imageView =  view.findViewById(R.id.slider_image);
        TextView heading =  view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
