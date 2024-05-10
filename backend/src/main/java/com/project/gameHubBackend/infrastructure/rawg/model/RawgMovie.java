package com.project.gameHubBackend.infrastructure.rawg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public final class RawgMovie {
    private  String preview;
    private  RawgMovieData data;
}


