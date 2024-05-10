package com.project.gameHubBackend.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"categoryGameId"})
@ToString(of = "categoryGameId")
public class CategoryGame {

     Integer categoryGameId;
     Category category;
     Game game;
}
