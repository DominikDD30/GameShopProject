package com.project.gameHubBackend.business;

import com.project.gameHubBackend.domain.*;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PlatformGameEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.GameJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.PlatformGameJpaRepository;
import com.project.gameHubBackend.infrastructure.rawg.mapper.RawgGameMapper;
import com.project.gameHubBackend.infrastructure.rawg.mapper.RawgGenreToCategoryMapper;
import com.project.gameHubBackend.infrastructure.rawg.mapper.RawgPlatformMapper;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGame;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGenre;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPlatform;
import com.project.gameHubBackend.util.Functions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StoreUpdateService {
    private final SimulateService simulateService;
    private final GameService gameService;
    private final GoogleService googleService;

    private final PlatformService platformService;
    private final CategoryService categoryService;
        private final RawgClientService rawgClientService;
    private final RawgGameMapper rawgGameMapper;
    private final RawgPlatformMapper rawgPlatformMapper;
    private final RawgGenreToCategoryMapper rawgGenreToCategoryMapper;
    private final GameJpaRepository gameJpaRepository;
    private final PlatformGameJpaRepository platformGameJpaRepository;
    private Integer batchNumber;
    private Integer purchaseCounter;

    @Value("${simulation.updating.external.enabled}")
    private boolean isExternalUpdatingEnabled;

    @Value("${simulation.updating.internal.enabled}")
    private boolean isInternalUpdatingEnabled;


    public StoreUpdateService(SimulateService simulateService, GameService gameService, GoogleService googleService, PlatformService platformService, CategoryService categoryService, RawgClientService rawgClientService, RawgGameMapper rawgGameMapper, RawgPlatformMapper rawgPlatformMapper, RawgGenreToCategoryMapper rawgGenreToCategoryMapper, GameJpaRepository gameJpaRepository, PlatformGameJpaRepository platformGameJpaRepository) {
        this.simulateService = simulateService;
        this.gameService = gameService;
        this.googleService = googleService;
        this.platformService = platformService;
        this.categoryService = categoryService;
        this.rawgClientService = rawgClientService;
        this.rawgGameMapper = rawgGameMapper;
        this.rawgPlatformMapper = rawgPlatformMapper;
        this.rawgGenreToCategoryMapper = rawgGenreToCategoryMapper;
        this.gameJpaRepository = gameJpaRepository;
        this.platformGameJpaRepository = platformGameJpaRepository;
        this.batchNumber =calculateBatchNumber();
        this.purchaseCounter=1;
    }

    private Integer calculateBatchNumber() {
        int size = gameJpaRepository.findAll().size();
       return size==0 ? 1 : (size/15)+1;
    }


    @Scheduled(fixedRateString = "${sheduler.refresh.interval.categories}", timeUnit = TimeUnit.SECONDS)
    public void updateCategories() {
        if(!isExternalUpdatingEnabled) return;

        try {
            log.info("updating Categories");
            List<RawgGenre> rawgGenres = rawgClientService.getRawgGenres();
            ArrayList<Category> categories = new ArrayList<>(rawgGenres.stream().map(rawgGenreToCategoryMapper::mapToCategory).toList());

            List<Category> existingCategories = categoryService.getCategories();
            categories.removeAll(existingCategories);
            categories.forEach(categoryService::saveOrUpdateCategory);
        } catch (Error e) {
            log.error("an error occurred while updating Categories: " + e.getMessage());
        }
    }

    @Scheduled(fixedRateString = "${sheduler.refresh.interval.platforms}", timeUnit = TimeUnit.SECONDS)
    public void updatePlatforms() {
        if(!isExternalUpdatingEnabled) return;

        try {
            log.info("updating Platforms");
            List<RawgPlatform> rawgPlatforms = rawgClientService.getRawgPlatforms();
            ArrayList<Platform> platforms = new ArrayList<>(rawgPlatforms.stream().map(rawgPlatformMapper::mapToPlatform).toList());
            List<Platform> existingPlatforms = platformService.getPlatforms();
            platforms.removeAll(existingPlatforms);
            platforms.forEach(platformService::savePlatform);
        } catch (Error e) {
            log.error("an error occurred while updating Platforms: " + e.getMessage());
        }
    }

    @Scheduled(fixedRateString = "${sheduler.refresh.interval.newGames}", timeUnit = TimeUnit.SECONDS)
    public void fetchNewGames() {
        if(!isExternalUpdatingEnabled) return;

        try {
            log.info("fetching newGames, batch nr: "+batchNumber++);
            List<RawgGame> rawgGames = rawgClientService.getRawgGames(batchNumber++);
            ArrayList<Game> games = new ArrayList<>(rawgGames.stream().map(this::buildGame).toList());
            gameService.saveGames(games);
        } catch (RuntimeException e) {
            log.error("an error occurred while fetching new Games: " + e.getMessage());
        }
    }

    @Scheduled(fixedRateString = "${sheduler.refresh.interval.simulation}", timeUnit = TimeUnit.SECONDS)
    public void simulatePurchases(){
        if(!isInternalUpdatingEnabled) return;
        purchaseCounter++;
        if(purchaseCounter >2) {
            log.info("simulating purchases");
            simulateService.makePurchases(10);
        }
    }

    @Scheduled(fixedRateString = "${sheduler.refresh.interval.updateGames}", timeUnit = TimeUnit.SECONDS)
    public void updateGames() {
        if(!isInternalUpdatingEnabled) return;

        log.info("updating Games");
        var updatedGamePlatforms = gameJpaRepository.findAll().stream()
                .filter(GameEntity::getIsContinuouslyDelivered)
                .peek(gameEntity -> {
                    gameEntity.setIsSoldOut(false);
                    gameJpaRepository.save(gameEntity);
                })
                .flatMap(gameEntity -> gameEntity.getGamePlatforms().stream()
                        .filter(gamePlatform -> gamePlatform.getLeftInStock() < 50)
                )
                .map(this::getNew)
                .collect(Collectors.toSet());
        platformGameJpaRepository.saveAll(updatedGamePlatforms);
    }

    private PlatformGameEntity getNew(PlatformGameEntity toUpdate) {
        //There should be interaction with external Game Supplier
        //For study case we get random amount
        Integer newDelivery = Functions.getRandomAmountWithPossibleZero(50, 100);
        toUpdate.setLeftInStock(toUpdate.getLeftInStock() + newDelivery);
        return toUpdate;
    }
    private Game buildGame(RawgGame rawgGame) {
        Game game = rawgGameMapper.mapToGame(rawgGame);

        Set<CategoryGame> gameCategories = buildGameCategories(rawgGame, game);
        Set<PlatformGame> gamePlatforms = buildGamePlatforms(rawgGame, game);

        return game
                .withGamePlatforms(gamePlatforms)
                .withGameCategories(gameCategories);
    }

    private Set<CategoryGame> buildGameCategories(RawgGame rawgGame, Game game) {
        return rawgGame.getGenres().stream()
                .map(rawgGenreToCategoryMapper::mapToCategory)
                .map(category -> CategoryGame.builder()
                        .game(game)
                        .category(category)
                        .build()).collect(Collectors.toSet());
    }

    private Set<PlatformGame> buildGamePlatforms(RawgGame rawgGame, Game game) {
        Set<PlatformGame> platforms = rawgGame.getParent_platforms().stream()
                .map(rawgPlatformWrapper -> rawgPlatformMapper.mapToPlatform(rawgPlatformWrapper.getPlatform()))
                .map(platform -> PlatformGame.builder()
                        .game(game)
                        .platform(platform)
                        .leftInStock(Functions.getRandomNumber(200,300))
                        .price(platform.getName().equals("PC") ? googleService.searchForProductPrice(rawgGame.getName()) : BigDecimal.valueOf(Functions.getRandomNumber(10,20)))
                        .build()).collect(Collectors.toSet());
        Set<PlatformGame> additionalGamePlatforms = buildAdditionalGamePlatforms(platforms);
        platforms.addAll(additionalGamePlatforms);
        return platforms;
    }

    private Set<PlatformGame> buildAdditionalGamePlatforms(Set<PlatformGame> gamePlatforms) {
        return gamePlatforms.stream()
                .filter(gamePlatform -> gamePlatform.getPlatform().getName().equals("PC") ||
                        gamePlatform.getPlatform().getName().equals("Xbox") ||
                        gamePlatform.getPlatform().getName().equals("PlayStation"))
                .map(gamePlatform -> {
                    Platform platform = gamePlatform.getPlatform();
                    return switch (platform.getName()) {
                        case "PC" -> gamePlatform
                                .withPlatform(platform.withName("Steam Key"))
                                .withLeftInStock(Functions.getRandomNumber(150,250))
                                .withPrice(gamePlatform.getPrice().subtract(BigDecimal.valueOf(Functions.getRandomNumber(0,5))).max(BigDecimal.ONE));
                        case "Xbox" -> gamePlatform
                                .withPlatform(platform.withName("Xbox Live Key"))
                                .withLeftInStock(Functions.getRandomNumber(150,250))
                                .withPrice(gamePlatform.getPrice().subtract(BigDecimal.valueOf(Functions.getRandomNumber(0,5)).max(BigDecimal.ONE)));
                        case "PlayStation" -> gamePlatform
                                .withPlatform(platform.withName("PSN Key"))
                                .withLeftInStock(Functions.getRandomNumber(150,250))
                                .withPrice(gamePlatform.getPrice().subtract(BigDecimal.valueOf(Functions.getRandomNumber(0,5)).max(BigDecimal.ONE)));
                        default -> throw new RuntimeException();
                    };
                }).collect(Collectors.toSet());
    }


}

