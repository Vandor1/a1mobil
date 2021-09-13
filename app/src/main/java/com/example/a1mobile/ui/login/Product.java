package com.example.a1mobile.ui.login;

public class Product {
    private String title;
    private String description;
    private String imageURL;
    private int price;

    public Product(String title, String description, String imageURL, int price) {
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
