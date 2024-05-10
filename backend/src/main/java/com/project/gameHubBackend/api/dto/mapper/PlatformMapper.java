package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.PlatformDTO;
import com.project.gameHubBackend.domain.Platform;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlatformMapper {

    PlatformDTO map(Platform platform);
}
