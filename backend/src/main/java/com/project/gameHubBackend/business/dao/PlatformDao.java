package com.project.gameHubBackend.business.dao;

import com.project.gameHubBackend.domain.Platform;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformEntity;

import java.util.List;
import java.util.Optional;

public interface PlatformDao {

    List<Platform> getPlatforms();

    void savePlatform(Platform platform);

}
