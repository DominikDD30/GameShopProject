package com.project.gameHubBackend.business;

import com.project.gameHubBackend.api.dto.GameDTO;
import com.project.gameHubBackend.business.dao.GameDao;
import com.project.gameHubBackend.domain.DesiredGame;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.domain.exception.LeftInStockBelowZeroException;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import com.project.gameHubBackend.util.Functions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class GameService {

    private GameDao gameDao;


    public Game getGameByName(String gameName) {
        return gameDao.getByName(gameName);
    }

    public void saveGames(List<Game> games) {
        gameDao.saveGames(games);
    }

    public Game getGameById(Integer gameId) {
        return gameDao.getById(gameId);
    }


    public Integer getAllGamesCount(Optional<String> category, Optional<String> platform, Optional<String> searchText,Optional<String> priceFrom,Optional<String> priceTo) {
        Map<String, String> filters = getFilters(category, platform, searchText, priceFrom, priceTo);
        return gameDao.getAllGamesCount(filters);
    }

    @Transactional
    public void deactivateGame(Integer gameId) {gameDao.deactivateGame(gameId);}
    public List<Game> getGames(Optional<String> category, Optional<String> platform, Optional<String> searchText,
                               Optional<String> priceFrom, Optional<String> priceTo, Optional<String> sortDirection,
                               Optional<String> sortColumn,Integer pageNumber) {

        Map<String, String> filters = getFilters(category, platform, searchText, priceFrom, priceTo);


        return sortColumn.isPresent()
                ? gameDao.getGames(filters,
                PageRequest.of(pageNumber, 32, getSort(sortDirection.orElse("ASC"), sortColumn.get())))
                : gameDao.getGames(filters, PageRequest.of(pageNumber, 32,Sort.unsorted()));
    }

    private Sort getSort(String sortDirection,String sortColumn) {
        String sorting=sortColumn.equals("price")?"platforms.price":"name";
        return (sortDirection.equals("DESC"))?Sort.by(sorting).descending()
                :Sort.by(sorting).ascending();
    }

    public void decreaseLeftInStock(List<DesiredGame> desiredGames)throws LeftInStockBelowZeroException{
        desiredGames.forEach(desiredGame ->gameDao.decreaseGamePlatformLeftAmount(desiredGame));
    }


    private Map<String, String> getFilters(
            Optional<String> category, Optional<String> platform, Optional<String> searchText, Optional<String> priceFrom,
            Optional<String> priceTo) {
        Map<String, String> filters = new HashMap<>();

        filters.put("category", category.orElse("%"));
        filters.put("platform", platform.orElse("%"));
        filters.put("searchText", searchText.map(text -> "%" + text + "%").orElse("%"));
        filters.put("priceFrom", priceFrom.orElse("0"));
        filters.put("priceTo", priceTo.orElse("1000"));
        return filters;
    }


    public List<Game> getAllGames() {
       return gameDao.getAllGames();
    }


}
