package com.tugas.usingapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpcomingResponse {
    @SerializedName("results")
    @Expose
    private List<Upcoming> upcomings;

    public List<Upcoming> getUpcomings() {
        return upcomings;
    }
}
