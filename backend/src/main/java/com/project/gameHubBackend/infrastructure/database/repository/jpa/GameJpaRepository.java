package com.project.gameHubBackend.infrastructure.database.repository.jpa;


import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GameJpaRepository extends JpaRepository<GameEntity, Integer> {

    GameEntity findByName(String name);

    @Modifying
    @Query("UPDATE GameEntity game SET game.isContinuouslyDelivered = false WHERE game.gameId =:gameId")
    void deactivateGame(@Param("gameId") Integer gameId);


    @Query(value = """
                  SELECT COUNT(DISTINCT g.name) AS game_count
                  FROM game g
                           INNER JOIN platform_game platforms ON g.game_id = platforms.game_id
                           INNER JOIN platform platform ON platforms.platform_id = platform.platform_id
                           INNER JOIN category_game categories ON g.game_id = categories.game_id
                           INNER JOIN category category ON categories.category_id = category.category_id
                    WHERE platforms.price >= :priceFrom AND
                     platforms.price <= :priceTo
                     AND g.name LIKE :searchText
                     AND g.is_sold_out = false
                     AND platform.name LIKE :platformFilter
                     AND category.category LIKE :categoryFilter       
            """, nativeQuery = true)
    Integer getAllGamesCount(final @Param("categoryFilter") String category,
                             final @Param("platformFilter") String platform,
                             final @Param("searchText") String searchText,
                             final @Param("priceFrom") BigDecimal priceFrom,
                             final @Param("priceTo") BigDecimal priceTo);



    @Query("""
            SELECT  game FROM GameEntity game
            JOIN FETCH game.gamePlatforms platforms
            JOIN FETCH platforms.platform platform
            JOIN FETCH game.gameCategories categories
            JOIN FETCH categories.category category
            LEFT JOIN  game.trailer trailer
            WHERE platforms.price between :priceFrom AND :priceTo
            AND game.name LIKE :searchText
            AND game.isSoldOut = false
            AND platform.name LIKE :platformFilter
            AND category.categoryName LIKE :categoryFilter
            """)
    List<GameEntity> findGames(
            final @Param("categoryFilter") String category,
            final @Param("platformFilter") String platform,
            final @Param("searchText") String searchText,
            final @Param("priceFrom") BigDecimal priceFrom,
            final @Param("priceTo") BigDecimal priceTo,
            final Pageable pageable);

    @Query("""
            SELECT game FROM GameEntity game
            JOIN FETCH game.gamePlatforms platforms
            JOIN FETCH platforms.platform platform
            JOIN FETCH game.gameCategories categories
            JOIN FETCH categories.category category
            JOIN FETCH game.photos photos 
            LEFT JOIN  game.trailer trailer
            LEFT JOIN  game.gameOpinions opinions 
            WHERE game.gameId = :gameId
            AND NOT (game.isContinuouslyDelivered = false AND game.isSoldOut = true)
            """)
    GameEntity findGameDetails(final @Param("gameId") Integer gameId);


    @Query(value = """
        select count(*) from game;
      """,nativeQuery = true)
    Integer getSize();

    @Query("""
        SELECT game FROM GameEntity game
         INNER JOIN game.gamePlatforms platforms
        """)
    GameEntity getGameWithPlatforms(Integer randomNumber);
}



