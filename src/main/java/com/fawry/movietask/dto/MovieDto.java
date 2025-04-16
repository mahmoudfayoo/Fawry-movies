package com.fawry.movietask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Awards")
    private String awards;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("Ratings")
    private List<RatingDto> ratings;
    @JsonProperty("Metascore")
    private String metascore;
    @JsonProperty("ImdbRating")
    private String imdbRating;
    @JsonProperty("ImdbVotes")
    private String imdbVotes;
    @JsonProperty("ImdbID")
    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Dvd")
    private String dvd;
    @JsonProperty("BoxOffice")
    private String boxOffice;
    @JsonProperty("Production")
    private String production;
    @JsonProperty("Website")
    private String website;
    @JsonProperty("Response")
    private String response;

}
