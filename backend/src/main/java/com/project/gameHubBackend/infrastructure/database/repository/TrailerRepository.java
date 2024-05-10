package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.infrastructure.database.repository.jpa.TrailerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TrailerRepository  {
    private TrailerJpaRepository trailerJpaRepository;

}
