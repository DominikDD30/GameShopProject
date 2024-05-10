package com.project.gameHubBackend.domain;


import lombok.*;

import java.util.Set;


@With
@Value
@Builder
@EqualsAndHashCode(of = {"name","mainPhoto","description","gamePlatforms"})
@ToString(of = {"gameId", "gameNumber", "name"})
public class Game {

     Integer gameId;
     String gameNumber;
     String name;
     String description;
     String mainPhoto;
     Boolean isContinuouslyDelivered;
     Boolean isSoldOut;
     Trailer trailer;
     Set<Photo> photos;
     Set<PublisherGame> publishers;
     Set<CategoryGame> gameCategories;
     Set<PlatformGame> gamePlatforms;
     Set<OpinionCustomerGame> gameOpinions;
}

