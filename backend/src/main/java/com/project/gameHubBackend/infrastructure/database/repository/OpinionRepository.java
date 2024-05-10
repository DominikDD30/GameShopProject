package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.infrastructure.database.repository.mapper.OpinionEntityMapper;
import com.project.gameHubBackend.business.dao.OpinionDao;
import com.project.gameHubBackend.domain.OpinionCustomerGame;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.OpinionJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class OpinionRepository implements OpinionDao{
    private OpinionJpaRepository opinionJpaRepository;
    private OpinionEntityMapper opinionMapper;

    public void saveOpinion(OpinionCustomerGame opinion){
        opinionJpaRepository.save(opinionMapper.mapToEntity(opinion));
    }

    @Override
    public Optional<OpinionCustomerGame> getGameOpinionForSpecifiedCustomer(String game, String customerEmail) {
        return Optional.ofNullable(opinionMapper.mapFromEntity(opinionJpaRepository.getByCustomer_EmailAndGame_Name(customerEmail, game)));
    }
}
