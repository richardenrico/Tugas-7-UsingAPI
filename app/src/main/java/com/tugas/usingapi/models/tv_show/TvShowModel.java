package com.tugas.usingapi.models.tv_show;

import com.google.gson.annotations.SerializedName;
import com.tugas.usingapi.helper.LastEpisode;

import java.util.List;

public class TvShowModel {
    @SerializedName("id")
    private String id;

    @SerializedName("poster_path")
    private String imgUrl;

    @SerializedName("name")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("last_air_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("vote_count")
    private String voter;

    private List<LastEpisode> lastEpisode;

    public TvShowModel() {}

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

    public List<LastEpisode> getLastEpisode() {
        return lastEpisode;
    }

    public void setLastEpisode(List<LastEpisode> lastEpisode) {
        this.lastEpisode = lastEpisode;
    }
}
