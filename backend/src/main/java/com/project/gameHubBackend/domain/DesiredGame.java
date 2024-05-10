package com.project.gameHubBackend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;


@With
@Value
@Builder
@AllArgsConstructor
public class DesiredGame {
    String gameName;
    String gamePlatform;
    Integer amount;
}

