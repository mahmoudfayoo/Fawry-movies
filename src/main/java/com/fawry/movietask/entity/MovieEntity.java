package com.fawry.movietask.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "movie")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    @Column(name = "release_year")
    private String year;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("writer")
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
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("Ratings")
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;
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
