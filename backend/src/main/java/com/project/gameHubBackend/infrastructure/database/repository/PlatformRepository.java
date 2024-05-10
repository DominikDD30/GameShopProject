package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.business.dao.PlatformDao;
import com.project.gameHubBackend.domain.Platform;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.PlatformJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.PlatformEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PlatformRepository implements PlatformDao{
    private PlatformJpaRepository platformJpaRepository;
    private PlatformEntityMapper platformEntityMapper;



    @Override
    public List<Platform> getPlatforms() {
        return platformJpaRepository.findAll().stream().map(platformEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void savePlatform(Platform platform) {
        platformJpaRepository.save(platformEntityMapper.mapToEntity(platform));
    }


}
