package com.example.test1;

public class Category {
    private  String cat_img, cat_title;

    public Category(){

    }

    public Category(String cat_img, String cat_title){
        this.cat_img = cat_img;
        this.cat_title = cat_title;
    }

    public String getCat_img() {
        return cat_img;
    }

    public void setCat_img(String cat_img) {
        this.cat_img = cat_img;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }
}
