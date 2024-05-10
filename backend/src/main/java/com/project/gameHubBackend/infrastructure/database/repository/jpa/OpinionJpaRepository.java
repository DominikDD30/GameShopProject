package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.domain.OpinionCustomerGame;
import com.project.gameHubBackend.infrastructure.database.entity.OpinionCustomerGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionJpaRepository extends JpaRepository<OpinionCustomerGameEntity,Integer> {


    OpinionCustomerGameEntity getByCustomer_EmailAndGame_Name(String customerEmail,String GameName);
}
