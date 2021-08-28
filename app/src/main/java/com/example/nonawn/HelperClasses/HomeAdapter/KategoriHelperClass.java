package com.example.nonawn.HelperClasses.HomeAdapter;

import android.graphics.drawable.GradientDrawable;

public class KategoriHelperClass  {

    int image;
    String tittle, description;
    GradientDrawable color;

    public KategoriHelperClass(GradientDrawable color,int image, String tittle, String description){
        this.image = image;
        this.tittle = tittle;
        this.color = color;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTittle() {
        return tittle;
    }

    public GradientDrawable getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }
}
