package com.ademeridien.gdk2019.moviecatalogue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVShow {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("overview")
    @Expose
    private String overview;

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOverview() {
        return overview;
    }
}