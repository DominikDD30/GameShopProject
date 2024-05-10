package com.project.gameHubBackend.infrastructure.rawg.mapper;

import com.project.gameHubBackend.domain.Photo;
import com.project.gameHubBackend.domain.Publisher;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPhoto;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPublisher;
import org.springframework.stereotype.Component;

@Component
public class RawgPublisherMapper {

    public Publisher mapToPublisher(RawgPublisher rawgPublisher){
        return Publisher.builder()
                .publisherName(rawgPublisher.name())
                .build();
    }
}
