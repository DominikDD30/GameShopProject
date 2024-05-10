package com.project.gameHubBackend.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"platformGameId","leftInStock","price","platform"})
@ToString(of = {"platformGameId","leftInStock","price"})
public class PlatformGame {
     Integer platformGameId;
     Integer leftInStock;
     BigDecimal price;
     Platform platform;
     Game game;
}
