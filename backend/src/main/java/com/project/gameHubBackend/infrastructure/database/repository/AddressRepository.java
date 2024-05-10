package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.infrastructure.database.repository.jpa.AddressJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AddressRepository{
    private AddressJpaRepository addressJpaRepository;

}
