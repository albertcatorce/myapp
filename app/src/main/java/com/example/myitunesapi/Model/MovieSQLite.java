package com.example.myitunesapi.Model;

public class MovieSQLite {

    String trackId;
    String trackName;
    String image;
    String collectionPrice;
    String primaryGenreName;
    String releaseDate;
    String country;
    String longDescription;


    public MovieSQLite(String trackId, String trackName, String image, String collectionPrice, String primaryGenreName, String releaseDate, String country, String longDescription) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.image = image;
        this.collectionPrice = collectionPrice;
        this.primaryGenreName = primaryGenreName;
        this.releaseDate = releaseDate;
        this.country = country;
        this.longDescription = longDescription;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
}
