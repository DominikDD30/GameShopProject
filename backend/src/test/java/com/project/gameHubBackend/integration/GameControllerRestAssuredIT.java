package com.project.gameHubBackend.integration;

import com.project.gameHubBackend.api.dto.GameDTO;
import com.project.gameHubBackend.api.dto.GamesDTO;
import com.project.gameHubBackend.api.dto.mapper.GameMapper;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.infrastructure.database.repository.CategoryRepository;
import com.project.gameHubBackend.infrastructure.database.repository.GameRepository;
import com.project.gameHubBackend.infrastructure.database.repository.PlatformRepository;
import com.project.gameHubBackend.integration.configuration.RestAssuredIntegrationTestBase;
import com.project.gameHubBackend.integration.support.GameControllerTestSupport;
import com.project.gameHubBackend.util.FixturesT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;


public class GameControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
implements GameControllerTestSupport
{

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private GameMapper gameMapper;


    @Test
    void thatGetGamesWorksCorrectly(){
        //given
        Game someGame1 = FixturesT.getSomeGame1();
        Game someGame2 = FixturesT.getSomeGame2();
        someGame1.getGameCategories().forEach(cg->categoryRepository.saveCategory(cg.getCategory()));
        someGame1.getGamePlatforms().forEach(gp->platformRepository.savePlatform(gp.getPlatform()));
        gameRepository.saveGames(List.of(someGame1,someGame2));

        GameDTO gameDTO1 = gameMapper.map(someGame1,"PC");
        GameDTO gameDTO2 = gameMapper.map(someGame2,"PC");



        //when
        GamesDTO result = getGames();
        //then
        Assertions.assertThat(result.getResults())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("gameId","gameNumber","photos","gameCategories","platforms","opinions")
                .containsExactlyInAnyOrder(gameDTO2,gameDTO1);
        GameDTO gameDTO = result.getResults().get(0);
        Assertions.assertThat(result.getResults().stream()
                .map(GameDTO::getName).toList()).isSortedAccordingTo(Comparator.reverseOrder());
        Assertions.assertThat(new BigDecimal(String.valueOf(gameDTO.getPrice())))
                .isBetween(BigDecimal.valueOf(10),BigDecimal.valueOf(25));
    }


}
