package com.project.gameHubBackend.integration.configuration;

import com.project.gameHubBackend.GameHubBackendApplication;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(PersistenceContainerTestConfiguration.class)
@SpringBootTest(
    classes = GameHubBackendApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class AbstractIT {

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





    @AfterEach
    public void after(){
        opinionJpaRepository.deleteAll();
        publisherGameJpaRepository.deleteAll();
        platformGameJpaRepository.deleteAll();
        categoryGameJpaRepository.deleteAll();
        platformJpaRepository.deleteAll();
        categoryJpaRepository.deleteAll();
        gameJpaRepository.deleteAll();
    }

    @BeforeEach
    public  void  before() {
        opinionJpaRepository.deleteAll();
        categoryGameJpaRepository.deleteAll();
        publisherGameJpaRepository.deleteAll();
        platformGameJpaRepository.deleteAll();
        platformJpaRepository.deleteAll();
        categoryJpaRepository.deleteAll();
        gameJpaRepository.deleteAll();
    }



}
