package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.GamePurchaseDTO;
import com.project.gameHubBackend.api.dto.InvoiceDTO;
import com.project.gameHubBackend.api.dto.PurchaseDTO;
import com.project.gameHubBackend.api.dto.PurchaseRequestDTO;
import com.project.gameHubBackend.api.dto.mapper.PurchaseMapper;
import com.project.gameHubBackend.api.dto.mapper.PurchaseRequestMapper;
import com.project.gameHubBackend.business.OpinionService;
import com.project.gameHubBackend.business.purchaseService.PurchaseService;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.domain.PurchaseRequest;
import com.project.gameHubBackend.util.DTOFixturesT;
import com.project.gameHubBackend.util.FixturesT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseControllerTest {

    @Mock
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRequestMapper purchaseRequestMapper;

    @Mock
    private OpinionService opinionService;

    @Mock
    private PurchaseMapper purchaseMapper;

    @InjectMocks
    private PurchaseController purchaseController;


    @Test
    void getPurchasesHistoryForCustomer() {
        //given

        when(purchaseService.getPurchasesForCustomer(any(String.class)))
                .thenReturn(List.of(FixturesT.getSomePurchase1()));

        when(purchaseMapper.map(any(Purchase.class)))
                .thenReturn(DTOFixturesT.getSomePurchaseDTO1());

        when(opinionService.findCustomerOpinionForPurchase(any(GamePurchaseDTO.class),any(String.class)))
                .thenReturn(Optional.ofNullable(DTOFixturesT.getSomeOpinionDTO()));
        //when
        List<PurchaseDTO> result = purchaseController.getPurchasesHistoryForCustomer(FixturesT.getSomeCustomer1().getEmail());
        //then
        assertThat(result).isEqualTo(List.of(DTOFixturesT.getSomePurchaseDTO1()));
    }

    @Test
    void thatMakePurchaseWorkCorrectly() {
        //given
        PurchaseRequestDTO somePurchaseRequest = DTOFixturesT.getSomePurchaseRequestDTO1();

        when(purchaseRequestMapper.map(any(PurchaseRequestDTO.class)))
                .thenReturn(FixturesT.getSomePurchaseRequest1());

        when(purchaseService.makePurchase(any(PurchaseRequest.class)))
                .thenReturn(FixturesT.getSomePurchase1());

        when(purchaseMapper.mapGamePurchase(any(Purchase.class)))
                .thenReturn(DTOFixturesT.getSomeInvoiceDTO1());
        //when
        ResponseEntity<InvoiceDTO> result = purchaseController.makePurchase(somePurchaseRequest);
        //then
        assertThat(result.getBody()).isEqualTo(DTOFixturesT.getSomeInvoiceDTO1());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}