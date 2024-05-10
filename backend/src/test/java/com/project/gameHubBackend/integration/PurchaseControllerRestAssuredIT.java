package com.project.gameHubBackend.integration;

import com.project.gameHubBackend.api.dto.InvoiceDTO;
import com.project.gameHubBackend.api.dto.PurchaseDTO;
import com.project.gameHubBackend.api.dto.PurchaseRequestDTO;
import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.infrastructure.database.repository.CategoryRepository;
import com.project.gameHubBackend.infrastructure.database.repository.CustomerRepository;
import com.project.gameHubBackend.infrastructure.database.repository.GameRepository;
import com.project.gameHubBackend.infrastructure.database.repository.PlatformRepository;
import com.project.gameHubBackend.integration.configuration.RestAssuredIntegrationTestBase;
import com.project.gameHubBackend.integration.support.PurchaseControllerTestSupport;
import com.project.gameHubBackend.util.DTOFixturesT;
import com.project.gameHubBackend.util.FixturesT;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseControllerRestAssuredIT extends RestAssuredIntegrationTestBase
        implements PurchaseControllerTestSupport {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void thatMakePurchaseWorkCorrectly() {
        //given
        Game someGame1 = FixturesT.getSomeGame1();
        Game someGame2 = FixturesT.getSomeGame2();
        someGame1.getGameCategories().forEach(cg->categoryRepository.saveCategory(cg.getCategory().withCategoryId(null)));
        someGame1.getGamePlatforms().forEach(gp->platformRepository.savePlatform(gp.getPlatform().withPlatformId(null)));
        gameRepository.saveGames(List.of(someGame1,someGame2));

        PurchaseRequestDTO somePurchaseRequest = DTOFixturesT.getSomePurchaseRequestDTO1();
        //when
        InvoiceDTO invoiceDTO = makePurchase(somePurchaseRequest);
        Customer customer = customerRepository.getByEmail(somePurchaseRequest.getCustomerEmail()).get();
        List<PurchaseDTO> result=getPurchasesForCustomer(customer.getEmail());
        //then
        assertThat(invoiceDTO.getPurchaseNumber()).isNotNull();
        assertThat(result).hasSize(1);
    }
}