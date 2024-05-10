package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.OpinionDTO;
import com.project.gameHubBackend.api.dto.OpinionRequestDTO;
import com.project.gameHubBackend.api.dto.mapper.OpinionMapper;
import com.project.gameHubBackend.business.CustomerService;
import com.project.gameHubBackend.business.GameService;
import com.project.gameHubBackend.business.OpinionService;
import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.domain.OpinionCustomerGame;
import com.project.gameHubBackend.util.DTOFixturesT;
import com.project.gameHubBackend.util.Fixtures;
import com.project.gameHubBackend.util.FixturesT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpinionControllerTest {

    @Mock
    private OpinionService opinionService;

    @Mock
    private OpinionMapper opinionMapper;
    @Mock
    private CustomerService customerService;

    @Mock
    private GameService gameService;

    @InjectMocks
    private OpinionController opinionController;


    @Test
    void thatMakeOpinionWorksCorrectly() {
        //given
        OpinionRequestDTO opinionRequest = DTOFixturesT.getSomeOpinionRequestDTO1();
        //when then
        Assertions.assertDoesNotThrow(()->opinionController.makeOpinion(opinionRequest));
    }
    @Test
    void thatGetCustomerOpinionForGameWorkCorrectly(){
        //given
        Customer someCustomer = FixturesT.getSomeCustomer1();
        Game someGame = FixturesT.getSomeGame1();
        when(opinionService.getCustomerOpinionForGame(any(String.class),any(String.class)))
                .thenReturn(Optional.of(FixturesT.getSomeOpinion1()));

        when(opinionMapper.mapToDTO(any(OpinionCustomerGame.class)))
                .thenReturn(DTOFixturesT.getSomeOpinionDTO());
        //when
        OpinionDTO result = opinionController.getCustomerOpinionForGame(someCustomer.getEmail(), someGame.getName());
        //then
        Assertions.assertEquals(DTOFixturesT.getSomeOpinionDTO(),result);
    }
}