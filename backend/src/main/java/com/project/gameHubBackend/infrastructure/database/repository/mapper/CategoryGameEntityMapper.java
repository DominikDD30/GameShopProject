package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.CategoryGame;
import com.project.gameHubBackend.infrastructure.database.entity.CategoryGameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryGameEntityMapper {
    @Mapping(target = "game.photos", ignore = true)
    @Mapping(target = "game.publishers", ignore = true)
    @Mapping(target = "game.gameOpinions", ignore = true)
    @Mapping(target = "game.gameCategories", ignore = true)
    @Mapping(target = "game.gamePlatforms", ignore = true)
    CategoryGameEntity mapToEntity(CategoryGame categoryGame);
}
