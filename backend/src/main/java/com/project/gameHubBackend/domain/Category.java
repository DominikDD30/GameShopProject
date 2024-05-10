package com.project.gameHubBackend.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"categoryName"})
@ToString(of = {"categoryId","categoryName"})
public class Category {

     Integer categoryId;
     String categoryName;
     String backgroundUrl;
     Set<CategoryGame> categoryGames;

}
