package com.project.gameHubBackend.integration;

import com.project.gameHubBackend.business.RawgClientService;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGame;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGenre;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPlatform;
import com.project.gameHubBackend.integration.configuration.RestAssuredIntegrationTestBase;
import com.project.gameHubBackend.integration.support.WireMockTestSupport;
import com.project.gameHubBackend.util.RawgFixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class RawgClientServiceITTest extends RestAssuredIntegrationTestBase implements WireMockTestSupport {


    @Autowired
    private RawgClientService rawgClientService;

    @Test
    void getRawgGenres() {
        //given
        stubForRawgGenres(wireMockServer);
        //when
        List<RawgGenre> result = rawgClientService.getRawgGenres();
        //then
        Assertions.assertThat(result).hasSize(19);
        Assertions.assertThat(result).contains(RawgFixtures.getSomeRawgGenre4(), RawgFixtures.getSomeRawgGenre2());
    }


    @Test
    void getRawgPlatforms() {
        //given
        stubForRawgPlatforms(wireMockServer);
        //when
        List<RawgPlatform> result = rawgClientService.getRawgPlatforms();
        //then
        Assertions.assertThat(result).hasSize(14);
        Assertions.assertThat(result).contains(RawgFixtures.getSomeRawgPlatform1(), RawgFixtures.getSomeRawgPlatform3());
    }

    @Test
    void getRawgGames() {
        //given
        stubForRawgGameDetail(wireMockServer,3498);
        stubForRawgMovie(wireMockServer,3498);
        stubForRawgGames(wireMockServer);
        //when
        List<RawgGame> result = rawgClientService.getRawgGames(1);
        //then
        Assertions.assertThat(result).hasSize(3);
        Assertions.assertThat(result.get(0))
                .usingRecursiveComparison()
                .ignoringFields("publishers","short_screenshots","parent_platforms")
                .isEqualTo(RawgFixtures.getRawgGame1());
        Assertions.assertThat(result.get(0).getParent_platforms())
                .usingRecursiveComparison()
                .comparingOnlyFields("platform")
                .isEqualTo(RawgFixtures.getRawgGame1().getParent_platforms());
    }


}