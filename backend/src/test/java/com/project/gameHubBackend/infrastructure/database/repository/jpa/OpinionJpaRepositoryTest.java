package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.CustomerEntity;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import com.project.gameHubBackend.infrastructure.database.entity.OpinionCustomerGameEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PurchaseEntity;
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
class OpinionJpaRepositoryTest {

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Autowired
    private CategoryGameJpaRepository categoryGameJpaRepository;

    @Autowired
    private PlatformGameJpaRepository platformGameJpaRepository;

    @Autowired
    private PublisherGameJpaRepository publisherGameJpaRepository;
    @Autowired
    private PlatformJpaRepository platformJpaRepository;
    @Autowired
    private GameJpaRepository gameJpaRepository;
    @Autowired
    private OpinionJpaRepository opinionJpaRepository;


    @BeforeEach
    public void beforeEach(){
        opinionJpaRepository.deleteAll();
        categoryGameJpaRepository.deleteAll();
        publisherGameJpaRepository.deleteAll();
        platformGameJpaRepository.deleteAll();
        platformJpaRepository.deleteAll();
        categoryJpaRepository.deleteAll();
        gameJpaRepository.deleteAll();
    }

    @Test
    void thatOpinionCanBeSavedCorrectly() {
        //given
        CustomerEntity someCustomer = EntityFixturesT.getSomeCustomer1();
        GameEntity someGame = EntityFixturesT.getSomeGame1();
        OpinionCustomerGameEntity someOpinion = EntityFixturesT.getSomeOpinion1();

        CustomerEntity savedCustomer = customerJpaRepository.save(someCustomer);
        GameEntity savedGame = gameJpaRepository.save(someGame);
        someOpinion.setCustomer(savedCustomer);
        someOpinion.setGame(savedGame);
        opinionJpaRepository.save(someOpinion);

        //when
        List<OpinionCustomerGameEntity> result = opinionJpaRepository.findAll();
        //then
        Assertions.assertThat(result).hasSize(1);
    }

    @Test
    void thatOpinionCanBeFindForSpecifiedCustomer() {
        //given
        CustomerEntity someCustomer = EntityFixturesT.getSomeCustomer1();
        GameEntity someGame = EntityFixturesT.getSomeGame1();
        OpinionCustomerGameEntity someOpinion = EntityFixturesT.getSomeOpinion1();

        CustomerEntity savedCustomer = customerJpaRepository.save(someCustomer);
        GameEntity savedGame = gameJpaRepository.save(someGame);
        someOpinion.setCustomer(savedCustomer);
        someOpinion.setGame(savedGame);
        opinionJpaRepository.save(someOpinion);

        //when
        OpinionCustomerGameEntity result = opinionJpaRepository
                .getByCustomer_EmailAndGame_Name(someCustomer.getEmail(), someGame.getName());
        //then
        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("opinionCustomerGameId","customer","game")
                .isEqualTo(EntityFixturesT.getSomeOpinion1());
    }

}