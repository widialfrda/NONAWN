package com.example.nonawn.HelperClasses.HomeAdapter;

public class FeaturedHelperClass {

    int image;
    String tittle, description;

    public FeaturedHelperClass(int image, String tittle, String description) {
        this.image = image;
        this.tittle = tittle;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }
}
