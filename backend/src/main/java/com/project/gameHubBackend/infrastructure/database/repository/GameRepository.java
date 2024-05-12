package com.project.gameHubBackend.infrastructure.database.repository;


import com.project.gameHubBackend.business.dao.GameDao;
import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.domain.exception.LeftInStockBelowZeroException;
import com.project.gameHubBackend.infrastructure.database.entity.*;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.*;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Repository
@AllArgsConstructor
public class GameRepository implements GameDao {
    private GameJpaRepository gameJpaRepository;
    private PhotoJpaRepository photoJpaRepository;
    private PublisherGameJpaRepository publisherGameJpaRepository;
    private PublisherJpaRepository publisherJpaRepository;
    private CategoryGameJpaRepository categoryGameJpaRepository;
    private PlatformGameJpaRepository platformGameJpaRepository;
    private PlatformJpaRepository platformJpaRepository;
    private CategoryJpaRepository categoryJpaRepository;

    private GameEntityMapper gameEntityMapper;
    private GameDetailEntityMapper gameDetailEntityMapper;
    private PhotoEntityMapper photoEntityMapper;
    private PublisherGameEntityMapper publisherGameEntityMapper;
    private CategoryGameEntityMapper categoryGameEntityMapper;
    private PlatformGameEntityMapper platformGameEntityMapper;


    @Override
    public List<Game> getGames(Map<String, String> filters,Pageable pageable) {


        List<GameEntity> games = gameJpaRepository
                .findGames(filters.get("category"), filters.get("platform"), filters.get("searchText"),
                        new BigDecimal(filters.get("priceFrom")), new BigDecimal(filters.get("priceTo")),pageable);



        return games.stream().map(gameEntityMapper::mapFromEntity).toList();
    }


    @Override
    public void saveGames(List<Game> games) {
        games.forEach(game -> {
            GameEntity gameEntity = gameEntityMapper.mapToEntity(game);
            gameEntity.setGameNumber(UUID.randomUUID().toString());
            GameEntity savedGameEntity = gameJpaRepository.saveAndFlush(gameEntity);

            List<PhotoEntity> photoEntities = game.getPhotos().stream()
                    .map(photo -> photoEntityMapper.mapToEntity(photo))
                    .toList();
            photoEntities.forEach(photoEntity -> photoEntity.setGame(savedGameEntity));
            photoJpaRepository.saveAll(photoEntities);

            saveGameCategories(savedGameEntity, game.getGameCategories());
            saveGamePlatforms(savedGameEntity, game.getGamePlatforms());
            saveGamePublishers(savedGameEntity, game.getPublishers());
        });
    }


    @Override
    public Game getByName(String gameName) {
        GameEntity byName = gameJpaRepository.findByName(gameName);
        return gameEntityMapper.mapFromEntity(byName);
    }

    @Override
    public Game getById(Integer gameId) {
        GameEntity gameEntity = gameJpaRepository.findGameDetails(gameId);
        return gameDetailEntityMapper.mapFromEntity(gameEntity);
    }


    @Override
    public void decreaseGamePlatformLeftAmount(DesiredGame desiredGame) throws ObjectOptimisticLockingFailureException {
        GameEntity gameEntity = gameJpaRepository.findByName(desiredGame.getGameName());
        Optional<PlatformGameEntity> gamePlatform = gameEntity.getGamePlatforms().stream()
                .filter(gp -> gp.getPlatform().getName().equals(desiredGame.getGamePlatform()))
                .findFirst();
        if(gamePlatform.isEmpty())return;

        if (gamePlatform.get().getLeftInStock() - desiredGame.getAmount() < 0) {
            throw new LeftInStockBelowZeroException("Try to decrease Left in Stock when it is already 0");
        } else {
            gamePlatform.get().setLeftInStock(gamePlatform.get().getLeftInStock() - desiredGame.getAmount());
            boolean isSoldOut = gameEntity.getGamePlatforms().stream().filter(gp -> gp.getLeftInStock() != 0).findAny().isEmpty();
            if (isSoldOut) {
                gameEntity.setIsSoldOut(true);
            }
        }

        platformGameJpaRepository.save(gamePlatform.get());
    }

    @Override
    public void deactivateGame(Integer gameId) {
        gameJpaRepository.deactivateGame(gameId);
    }

    @Override
    public Integer getAllGamesCount(Map<String, String> filters) {
        return gameJpaRepository.getAllGamesCount(
                filters.get("category"), filters.get("platform"), filters.get("searchText"),
                new BigDecimal(filters.get("priceFrom")), new BigDecimal(filters.get("priceTo")));
    }

    @Override
    public List<Game> getAllGames() {
        return gameJpaRepository.findAll().stream()
                .map(gameEntity -> gameEntityMapper.mapFromEntity(gameEntity)).toList();
    }

    @Override
    public Integer getSize() {
        return gameJpaRepository.getSize();
    }




    private void saveGameCategories(GameEntity gameEntity, Set<CategoryGame> gameCategories) {
        List<CategoryGameEntity> categoryGameEntities = gameCategories.stream().map(gameCategory -> {
            CategoryEntity categoryEntity = categoryJpaRepository.findByCategoryName(gameCategory.getCategory().getCategoryName()).orElseThrow();
            CategoryGameEntity categoryGameEntity = categoryGameEntityMapper.mapToEntity(gameCategory);
            categoryGameEntity.setCategory(categoryEntity);
            categoryGameEntity.setGame(gameEntity);
            return categoryGameEntity;
        }).toList();
        categoryGameJpaRepository.saveAllAndFlush(categoryGameEntities);
    }


    private void saveGamePlatforms(GameEntity gameEntity, Set<PlatformGame> gamePlatforms) {
        List<PlatformGameEntity> platformGameEntities = gamePlatforms.stream().map(gamePlatform -> {
            PlatformEntity platformEntity = platformJpaRepository.findByName(gamePlatform.getPlatform().getName()).orElseThrow();
            PlatformGameEntity platformGameEntity = platformGameEntityMapper.mapToEntity(gamePlatform);
            platformGameEntity.setPlatform(platformEntity);
            platformGameEntity.setGame(gameEntity);


            return platformGameEntity;
        }).toList();

        platformGameJpaRepository.saveAllAndFlush(platformGameEntities);
    }

    private void saveGamePublishers(GameEntity savedGameEntity, Set<PublisherGame> publishers) {
        List<PublisherGameEntity> publisherGameEntities = publishers.stream()
                .map(publisherGame -> publisherGameEntityMapper.mapToEntity(publisherGame))
                .peek(publisherGameEntity -> publisherGameEntity.setGame(savedGameEntity))
                .peek(pge -> {
                    Optional<PublisherEntity> publisher = publisherJpaRepository
                            .findByPublisherName(pge.getPublisher().getPublisherName());
                    if (publisher.isEmpty()) {
                        publisherJpaRepository.saveAndFlush(pge.getPublisher());
                    }else{
                        pge.setPublisher(publisher.get());
                    }
                }).toList();

        publisherGameJpaRepository.saveAllAndFlush(publisherGameEntities);
    }


}

