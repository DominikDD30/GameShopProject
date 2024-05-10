package com.project.gameHubBackend.integration.support;

import com.project.gameHubBackend.api.controller.PurchaseController;
import com.project.gameHubBackend.api.dto.InvoiceDTO;
import com.project.gameHubBackend.api.dto.PurchaseDTO;
import com.project.gameHubBackend.api.dto.PurchaseRequestDTO;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseControllerTestSupport {

    RequestSpecification requestSpecification();

    default InvoiceDTO makePurchase(final PurchaseRequestDTO purchaseRequestDTO){
        return requestSpecification()
                .body(purchaseRequestDTO)
                .post(PurchaseController.PURCHASES)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract()
                .as(InvoiceDTO.class);
    }

    default List<PurchaseDTO> getPurchasesForCustomer(final String customerEmail){
        return requestSpecification()
                .get(PurchaseController.PURCHASES+"/customers"+"/"+customerEmail)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(new TypeRef<List<PurchaseDTO>>() {});
    }
}
