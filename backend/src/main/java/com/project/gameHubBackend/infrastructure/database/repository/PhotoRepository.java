package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.infrastructure.database.repository.jpa.PhotoJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PhotoRepository {
    private PhotoJpaRepository photoJpaRepository;

}
