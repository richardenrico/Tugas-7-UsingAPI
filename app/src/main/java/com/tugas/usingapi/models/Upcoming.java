package com.tugas.usingapi.models;

import com.google.gson.annotations.SerializedName;

public class Upcoming {
    private String id;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String imgUrl;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("vote_count")
    private String voter;

    public Upcoming() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }
}
