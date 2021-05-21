package com.example.nonawn.HelperClasses.HomeAdapter;

public class TestiHelperClass {

    int image, rating;
    String tittle, desc, user;

    public TestiHelperClass(int image, int rating, String tittle, String desc, String user) {
        this.image = image;
        this.tittle = tittle;
        this.desc = desc;
        this.user = user;
    }

    public int getImage() {
        return image;
    }

    public int getRating() {
        return rating;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDesc() {
        return desc;
    }

    public String getUser() {
        return user;
    }
}
