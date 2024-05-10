package com.project.gameHubBackend.infrastructure.rawg.mapper;

import com.project.gameHubBackend.domain.Platform;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPlatform;
import org.springframework.stereotype.Component;

@Component
public class RawgPlatformMapper {

    public Platform mapToPlatform(RawgPlatform rawgPlatform){
        return Platform.builder()
                .name(rawgPlatform.name())
                .build();
    }
}
