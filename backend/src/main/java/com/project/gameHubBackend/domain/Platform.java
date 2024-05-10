package com.project.gameHubBackend.domain;

import lombok.*;

import java.util.Set;
@With
@Value
@Builder
@EqualsAndHashCode(of = "name")
@ToString(of = {"platformId","name"})
public class Platform {
     Integer platformId;
     String name;
     Set<PlatformGame> platformGames;
}
