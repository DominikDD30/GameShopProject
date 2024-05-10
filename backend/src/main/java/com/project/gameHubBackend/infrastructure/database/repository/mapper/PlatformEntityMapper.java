package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.Platform;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlatformEntityMapper {

    @Mapping(target = "platformGames", ignore = true)
    Platform mapFromEntity(PlatformEntity entity);

    @Mapping(target = "platformGames", ignore = true)
    PlatformEntity mapToEntity(Platform platform);

}
