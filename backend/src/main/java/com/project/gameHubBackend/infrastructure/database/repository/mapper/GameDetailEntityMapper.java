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
public interface GameDetailEntityMapper {


    @Mapping(source = "publishers", target = "publishers", qualifiedByName = "mapPublishers")
    @Mapping(source = "gamePlatforms", target = "gamePlatforms", qualifiedByName = "mapGamePlatforms")
    @Mapping(source = "gameCategories", target = "gameCategories", qualifiedByName = "mapGameCategories")
    @Mapping(source = "photos", target = "photos", qualifiedByName = "mapPhotos")
    @Mapping(source = "gameOpinions",target="gameOpinions",qualifiedByName = "mapOpinions")
    Game mapFromEntity(GameEntity entity);


    @Named("mapPhotos")
    default Set<Photo> mapPhotos(Set<PhotoEntity> photoEntities) {
        return photoEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    @Named("mapPublishers")
    default Set<PublisherGame> mapPublishers(Set<PublisherGameEntity> publisherGameEntities) {
        return publisherGameEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }
    @Named("mapGamePlatforms")
    default Set<PlatformGame> mapGamePlatforms(Set<PlatformGameEntity> platformGameEntities) {
        return platformGameEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }
    @Named("mapGameCategories")
    default Set<CategoryGame> mapGameCategories(Set<CategoryGameEntity> categoryGameEntities) {
        return categoryGameEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }
    @Named("mapOpinions")
    default Set<OpinionCustomerGame> mapOpinions(Set<OpinionCustomerGameEntity> opinionEntities) {
        return opinionEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }


    @Mapping(target = "game", ignore = true)
    @Mapping(target = "customer.purchases", ignore = true)
    @Mapping(target = "customer.opinions", ignore = true)
    @Mapping(target = "customer.email", ignore = true)
    OpinionCustomerGame mapFromEntity(OpinionCustomerGameEntity entity);

    @Mapping(target = "game", ignore = true)
    Photo mapFromEntity(PhotoEntity photoEntity);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "publisher.publisherGames", ignore = true)
    PublisherGame mapFromEntity(PublisherGameEntity publisherEntity);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "platform.platformGames", ignore = true)
    PlatformGame mapFromEntity(PlatformGameEntity platformGameEntity);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "category.categoryGames", ignore = true)
    CategoryGame mapFromEntity(CategoryGameEntity categoryGameEntity);
}

