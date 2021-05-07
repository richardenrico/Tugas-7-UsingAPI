package com.tugas.usingapi.helper;

import com.google.gson.annotations.SerializedName;

public class LastEpisode {

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("overview")
    private String overview;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
