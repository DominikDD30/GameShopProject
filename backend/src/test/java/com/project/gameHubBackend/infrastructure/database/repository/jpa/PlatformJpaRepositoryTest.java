package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.PlatformEntity;
import com.project.gameHubBackend.integration.configuration.PersistenceContainerTestConfiguration;
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

import static com.project.gameHubBackend.util.EntityFixturesT.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PlatformJpaRepositoryTest {

    private PlatformJpaRepository platformJpaRepository;
    private CategoryJpaRepository categoryJpaRepository;
    private CategoryGameJpaRepository categoryGameJpaRepository;
    private PlatformGameJpaRepository platformGameJpaRepository;
    private PublisherGameJpaRepository publisherGameJpaRepository;
    private GameJpaRepository gameJpaRepository;
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
    void thatPlatformCanBeFindByNameCorrectly() {
        //given
        platformJpaRepository.deleteAll();
        PlatformEntity testedPlatform = getSomePlatform2();
        platformJpaRepository.saveAll(List.of(getSomePlatform1(), testedPlatform,getSomePlatform3()));
        //when
        PlatformEntity result = platformJpaRepository.findByName(testedPlatform.getName()).get();
        //then

        Assertions.assertThat(result).isEqualTo(testedPlatform);
    }

    @Test
    void shouldGetAllPlatforms() {
        //given
        platformJpaRepository.deleteAll();
        platformJpaRepository.saveAll(List.of(getSomePlatform1(),getSomePlatform2(),getSomePlatform3()));
        //when
        List<PlatformEntity> result = platformJpaRepository.findAll();
        //then
        Assertions.assertThat(result).hasSize(3);
    }


    @Test
    void shouldNotSaveDuplicatePlatform() {
        //given
        platformJpaRepository.deleteAll();
        platformJpaRepository.saveAll(List.of(getSomePlatform1(),getSomePlatform2(),getSomePlatform3()));
        //when then
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class,()->platformJpaRepository.save(getSomePlatform2()));
    }


}