package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.GamesDTO;
import com.project.gameHubBackend.api.dto.mapper.GameMapper;
import com.project.gameHubBackend.business.GameService;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.util.DTOFixturesT;
import com.project.gameHubBackend.util.FixturesT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private GameService gamesService;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private GameController gameController;

    @Test
    void thatGetGamesWorkCorrectly() {
        //given
        when(gamesService.getGames(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),
                Mockito.any(), Mockito.any(),Mockito.any(Integer.class)))
                .thenReturn(List.of(FixturesT.getSomeGame1(), FixturesT.getSomeGame2()));

        when(gameMapper.map(any(Game.class),any(String.class)))
                .thenReturn(DTOFixturesT.getSomeGameDTO1())
                .thenReturn(DTOFixturesT.getSomeGameDTO2());
        //when
        GamesDTO result = gameController.getGames(Optional.of("Strategy"), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(),Optional.of("DESC"), Optional.of("name"),1);
        //then
        assertThat(result).isEqualTo(
                GamesDTO.builder()
                        .results(List.of(DTOFixturesT.getSomeGameDTO1(), DTOFixturesT.getSomeGameDTO2()))
                        .build());
    }



}