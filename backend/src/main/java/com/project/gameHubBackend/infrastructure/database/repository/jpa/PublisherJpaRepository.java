package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.PublisherEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PublisherGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherJpaRepository extends JpaRepository<PublisherEntity,Integer> {
    Optional<PublisherEntity> findByPublisherName(String publisherName);
}
