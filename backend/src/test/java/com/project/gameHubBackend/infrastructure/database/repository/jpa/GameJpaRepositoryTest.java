package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
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
class GameJpaRepositoryTest {

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
    void thatGamesCanBeSavedCorrectly() {
        //given
        gameJpaRepository.saveAll(List.of(EntityFixturesT.getSomeGame1(), EntityFixturesT.getSomeGame2()));
        //when
        List<GameEntity> result = gameJpaRepository.findAll();
        //then
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    void shouldFailWhenTryToSaveDuplicateGame() {
        //given
        gameJpaRepository.saveAll(List.of(EntityFixturesT.getSomeGame1(), EntityFixturesT.getSomeGame2()));
        //when then
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> gameJpaRepository.save(EntityFixturesT.getSomeGame1()));
    }




}