package com.project.gameHubBackend.infrastructure.rawg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class RawgPlatformWrapper {
    private  RawgPlatform platform;
}
