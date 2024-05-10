package com.project.gameHubBackend.infrastructure.rawg.mapper;

import com.project.gameHubBackend.domain.Photo;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPhoto;
import org.springframework.stereotype.Component;

@Component
public class RawgPhotoMapper {

    public Photo mapToPhoto(RawgPhoto rawgPhoto){
        return Photo.builder()
                .url(rawgPhoto.image())
                .build();
    }
}
