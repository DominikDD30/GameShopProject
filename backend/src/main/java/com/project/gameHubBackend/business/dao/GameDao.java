package com.project.gameHubBackend.business.dao;

import com.project.gameHubBackend.domain.DesiredGame;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface GameDao {


    List<Game> getGames(Map<String, String> filters, Pageable pageable);

    void saveGames(List<Game> games);

    Game getByName(String gameName);

    Game getById(Integer gameId);

    void decreaseGamePlatformLeftAmount(DesiredGame games);

    void deactivateGame(Integer gameId);

    Integer getAllGamesCount(Map<String, String> filters);

    List<Game> getAllGames();

    Integer getSize();

}
