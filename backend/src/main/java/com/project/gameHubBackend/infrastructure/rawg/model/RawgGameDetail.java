package com.project.gameHubBackend.infrastructure.rawg.model;

import lombok.NoArgsConstructor;

import java.util.List;


public record RawgGameDetail(String description_raw, List<RawgPublisher> publishers) {

}
