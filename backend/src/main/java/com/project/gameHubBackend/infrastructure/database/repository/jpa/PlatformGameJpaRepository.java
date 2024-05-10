package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.PlatformGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformGameJpaRepository extends JpaRepository<PlatformGameEntity,Integer> {


}
