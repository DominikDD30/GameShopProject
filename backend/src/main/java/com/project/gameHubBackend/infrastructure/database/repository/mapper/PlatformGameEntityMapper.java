package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.PlatformGame;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformGameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlatformGameEntityMapper {

    @Mapping(target = "game.photos", ignore = true)
    @Mapping(target = "game.publishers", ignore = true)
    @Mapping(target = "game.gameOpinions", ignore = true)
    @Mapping(target = "game.gameCategories", ignore = true)
    @Mapping(target = "game.gamePlatforms", ignore = true)
    PlatformGameEntity mapToEntity(PlatformGame platformGame);
}
