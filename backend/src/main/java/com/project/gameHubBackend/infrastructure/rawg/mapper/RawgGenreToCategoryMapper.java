package com.project.gameHubBackend.infrastructure.rawg.mapper;

import com.project.gameHubBackend.domain.Category;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGenre;
import org.springframework.stereotype.Component;

@Component
public class RawgGenreToCategoryMapper {

    public Category mapToCategory(RawgGenre rawgGenre){
        return Category.builder()
                .categoryName(rawgGenre.name())
                .backgroundUrl(rawgGenre.image_background())
                .build();
    }
}
