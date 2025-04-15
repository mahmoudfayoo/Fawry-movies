package com.fawry.movietask.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String ratingValue;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
}
