package com.project.gameHubBackend.business.dao;

import com.project.gameHubBackend.domain.OpinionCustomerGame;

import java.util.Optional;

public interface OpinionDao {
    void saveOpinion(OpinionCustomerGame opinion);

    Optional<OpinionCustomerGame> getGameOpinionForSpecifiedCustomer(String game, String customerEmail);
}
