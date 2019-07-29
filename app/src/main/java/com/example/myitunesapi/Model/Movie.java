package com.example.myitunesapi.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    String trackName;
    String image;
    String collectionPrice;
    String primaryGenreName;

    public Movie(String trackName, String image, String collectionPrice, String primaryGenreName) {
        this.trackName = trackName;
        this.image = image;
        this.collectionPrice = collectionPrice;
        this.primaryGenreName = primaryGenreName;
    }

    protected Movie(Parcel in) {
        trackName = in.readString();
        image = in.readString();
        collectionPrice = in.readString();
        primaryGenreName = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(trackName);
        parcel.writeString(image);
        parcel.writeString(collectionPrice);
        parcel.writeString(primaryGenreName);
    }
}
