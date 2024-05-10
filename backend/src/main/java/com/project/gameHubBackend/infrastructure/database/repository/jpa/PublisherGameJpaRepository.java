package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.PlatformGameEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PublisherGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherGameJpaRepository extends JpaRepository<PublisherGameEntity,Integer> {
}
