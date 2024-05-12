package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.domain.OrderStatus;
import com.project.gameHubBackend.infrastructure.database.entity.CustomerEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PurchaseEntity;
import com.project.gameHubBackend.infrastructure.database.repository.PurchaseRepository;
import com.project.gameHubBackend.integration.configuration.PersistenceContainerTestConfiguration;
import com.project.gameHubBackend.util.EntityFixturesT;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PurchaseJpaRepositoryTest {

    private PurchaseJpaRepository purchaseJpaRepository;
    private GamePurchaseJpaRepository gamePurchaseJpaRepository;
    private CustomerJpaRepository customerJpaRepository;
    private CategoryJpaRepository categoryJpaRepository;
    private CategoryGameJpaRepository categoryGameJpaRepository;
    private PlatformGameJpaRepository platformGameJpaRepository;
    private PublisherGameJpaRepository publisherGameJpaRepository;
    private PlatformJpaRepository platformJpaRepository;
    private GameJpaRepository gameJpaRepository;
    private OpinionJpaRepository opinionJpaRepository;



    @BeforeEach
    public void beforeEach(){
        gamePurchaseJpaRepository.deleteAll();
        purchaseJpaRepository.deleteAll();
        opinionJpaRepository.deleteAll();
        categoryGameJpaRepository.deleteAll();
        publisherGameJpaRepository.deleteAll();
        platformGameJpaRepository.deleteAll();
        platformJpaRepository.deleteAll();
        categoryJpaRepository.deleteAll();
        gameJpaRepository.deleteAll();
    }

    @Test
    void thatPurchasesCanBeSavedCorrectly() {
        //given
        CustomerEntity someCustomer1 = EntityFixturesT.getSomeCustomer1();
        customerJpaRepository.save(someCustomer1);

        PurchaseEntity somePurchase1 = EntityFixturesT.getSomePurchase1();
        PurchaseEntity somePurchase2 = EntityFixturesT.getSomePurchase2();
        PurchaseEntity somePurchase3 = EntityFixturesT.getSomePurchase3();
        somePurchase1.setCustomer(customerJpaRepository.findByEmail(someCustomer1.getEmail()));
        somePurchase2.setCustomer(customerJpaRepository.findByEmail(someCustomer1.getEmail()));
        somePurchase3.setCustomer(customerJpaRepository.findByEmail(someCustomer1.getEmail()));

        purchaseJpaRepository.save(somePurchase1);
        purchaseJpaRepository.save(somePurchase2);
        purchaseJpaRepository.save(somePurchase3);
        //when
        List<PurchaseEntity> result = purchaseJpaRepository.findAll();
        //then
        Assertions.assertThat(result).hasSize(3);
    }


    @Test
    void thatPurchasesCanBeGetForSpecifiedUser() {
        //given
        List<PurchaseEntity> somePurchases = List.of(EntityFixturesT.getSomePurchase1(), EntityFixturesT.getSomePurchase2());
        CustomerEntity someCustomer = EntityFixturesT.getSomeCustomer1();
        customerJpaRepository.save(someCustomer);

        somePurchases.forEach(purchase -> purchase.setCustomer(someCustomer));
        purchaseJpaRepository.saveAll(somePurchases);
        somePurchases.forEach(purchase -> purchase.getGamePurchases()
                .forEach(gp -> {
                    gp.setPurchase(purchase);
                    gamePurchaseJpaRepository.save(gp);
                }));
        //when
        List<PurchaseEntity> result = purchaseJpaRepository.findAllByCustomerCustomerEmail(someCustomer.getEmail());
        //then
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result).isEqualTo(somePurchases);
    }


}