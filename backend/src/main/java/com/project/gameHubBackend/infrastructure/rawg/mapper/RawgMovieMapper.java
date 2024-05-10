package com.project.gameHubBackend.infrastructure.rawg.mapper;

import com.project.gameHubBackend.domain.Trailer;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgMovie;
import org.springframework.stereotype.Component;

@Component
public class RawgMovieMapper {
    public Trailer mapToTrailer(RawgMovie rawgMovie){
        return Trailer.builder()
                .previewImage(rawgMovie.getPreview())
                .url(rawgMovie.getData().getResolution480())
                .build();
    }
}
