package com.project.gameHubBackend.infrastructure.rawg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RawgMovieData {

    @JsonProperty(value = "480")
    public String resolution480;

}
