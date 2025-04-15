package com.fawry.movietask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RatingDto {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;
}
