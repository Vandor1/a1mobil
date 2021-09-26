package com.example.a1mobile.ui.login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable: https://www.youtube.com/watch?v=OUZcjZkJrvY
 * "Packaged in a way Android understands."
 */
public class Product implements Parcelable {
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

    protected Product(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageURL = in.readString();
        price = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageURL);
        parcel.writeInt(price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                '}';
    }
}
