package com.project.gameHubBackend.infrastructure.database.repository.mapper;


import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameEntityMapper {


    @Mapping(target = "photos",ignore = true)
    @Mapping(target = "publishers",ignore = true)
    @Mapping(target = "gameOpinions",ignore = true)
    @Mapping(source = "gamePlatforms", target = "gamePlatforms", qualifiedByName = "mapGamePlatforms")
    @Mapping(source = "gameCategories", target = "gameCategories", qualifiedByName = "mapGameCategories")
    Game mapFromEntity(GameEntity entity);

    @Mapping(target = "gameCategories", ignore = true)
    @Mapping(target = "gamePlatforms", ignore = true)
    GameEntity mapToEntity(Game game);


    @Named("mapGamePlatforms")
    default Set<PlatformGame> mapGamePlatforms(Set<PlatformGameEntity> platformGameEntities) {
        return platformGameEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }
    @Named("mapGameCategories")
    default Set<CategoryGame> mapGameCategories(Set<CategoryGameEntity> categoryGameEntities) {
        return categoryGameEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }


    @Named("mapOpinions")
//    /unused/
    default List<OpinionCustomerGame> mapOpinions(List<OpinionCustomerGameEntity> opinionEntities) {
        return opinionEntities.stream().map(this::mapFromEntity).toList();
    }


    @Mapping(target = "game", ignore = true)
    @Mapping(target = "customer", ignore = true)
    OpinionCustomerGame mapFromEntity(OpinionCustomerGameEntity entity);

    @Mapping(target = "game", ignore = true)
    Photo mapFromEntity(PhotoEntity photoEntity);

    @Mapping(target = "platform.platformGames", ignore = true)
    @Mapping(target = "game", ignore = true)
    PlatformGame mapFromEntity(PlatformGameEntity platformGameEntity);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "category.categoryGames", ignore = true)
    CategoryGame mapFromEntity(CategoryGameEntity categoryGameEntity);
}

