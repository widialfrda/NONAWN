package com.example.nonawn.HelperClasses.HomeAdapter;

import android.graphics.drawable.GradientDrawable;

public class KategoriHelperClass  {

    int image;
    String tittle;
    GradientDrawable color;

    public KategoriHelperClass(GradientDrawable color,int image, String tittle){
        this.image = image;
        this.tittle = tittle;
        this.color = color;
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
}
