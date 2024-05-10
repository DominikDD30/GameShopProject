package com.project.gameHubBackend.business;

import com.project.gameHubBackend.business.dao.GameDao;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.util.FixturesT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameDao gameDao;
    @InjectMocks
    private GameService gameService;



    @ParameterizedTest
    @MethodSource("provideFiltersAndExpectedResult")
    void thatGetGamesWorkCorrectly(List<Optional<String>>filters,List<Game>expectedGames) {
        //given
        Mockito.when(gameDao.getGames(Mockito.anyMap(),Mockito.any()))
                .thenReturn(expectedGames);
        //when
        List<Game> result = gameService.getGames(filters.get(0), filters.get(1), filters.get(2),
                filters.get(3), filters.get(4), Optional.of("ASC"), Optional.of("name"),1);
        //then
        Assertions.assertThat(result).hasSize(expectedGames.size());
    }

    private static Stream<Arguments> provideFiltersAndExpectedResult(){

        return Stream.of(
                Arguments.of(List.of(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),
                        Optional.empty()),List.of(FixturesT.getSomeGame1(),FixturesT.getSomeGame2())),

                Arguments.of(List.of(Optional.of("action"),Optional.empty(),Optional.empty(),Optional.empty(),
                        Optional.empty()),List.of(FixturesT.getSomeGame1())),

                Arguments.of(List.of(Optional.empty(),Optional.empty(),Optional.of("t"),Optional.empty(),
                        Optional.empty()),List.of(FixturesT.getSomeGame1(),FixturesT.getSomeGame2())),

                Arguments.of(List.of(Optional.empty(),Optional.of("PC"),Optional.empty(),
                        Optional.empty(),Optional.of("12")),List.of())
        );
    }
}