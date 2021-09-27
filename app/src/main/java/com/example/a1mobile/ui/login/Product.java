package com.example.a1mobile.ui.login;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Parcelable: https://www.youtube.com/watch?v=OUZcjZkJrvY
 * "Packaged in a way Android understands."
 */
public class Product implements Parcelable {
    private String title;
    private String description;
    private List<String> imageURLs;
    private int price;

    public Product(String title, String description,List<String> imageURLs, int price) {
        this.title = title;
        this.description = description;
        this.imageURLs = imageURLs;
        this.price = price;
    }

    protected Product(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageURLs = in.createStringArrayList();
        price = in.readInt();
    }

    /**
     *  -- from createStringArrayList().
     */
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeStringList(imageURLs);
        parcel.writeInt(price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageURLs='" + imageURLs + '\'' +
                ", price=" + price +
                '}';
    }
}
