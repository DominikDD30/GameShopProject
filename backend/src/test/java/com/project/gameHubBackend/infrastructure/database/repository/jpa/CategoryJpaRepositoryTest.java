package com.project.gameHubBackend.infrastructure.database.repository.jpa;

import com.project.gameHubBackend.infrastructure.database.entity.CategoryEntity;
import com.project.gameHubBackend.integration.configuration.PersistenceContainerTestConfiguration;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
class CategoryJpaRepositoryTest  {

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
    void thatCategoryCanBeFindByNameCorrectly() {
        //given
        CategoryEntity testedCategory = getSomeCategory2();
        categoryJpaRepository.saveAll(List.of(getSomeCategory1(), testedCategory,getSomeCategory3()));
        //when
        CategoryEntity result = categoryJpaRepository.findByCategoryName(testedCategory.getCategoryName()).get();
        //then
        Assertions.assertThat(result).isEqualTo(testedCategory);
    }

    @Test
    void shouldGetAllCategories() {
        //given
        categoryJpaRepository.saveAll(List.of(getSomeCategory1(), getSomeCategory2(),getSomeCategory3()));
        //when
        List<CategoryEntity> result = categoryJpaRepository.findAll();
        //then
        Assertions.assertThat(result).hasSize(3);
    }

    @Test
    void shouldUpdateCategoryCorrectly() {
        //given
        CategoryEntity testedCategory = getSomeCategory2();
        categoryJpaRepository.saveAll(List.of(getSomeCategory1(), testedCategory));
        String newBackgroundUrl = "newUrl2453";
        testedCategory.setBackgroundUrl(newBackgroundUrl);
        categoryJpaRepository.save(testedCategory);
        //when
        CategoryEntity result = categoryJpaRepository.findByCategoryName(testedCategory.getCategoryName()).get();
        //then
        Assertions.assertThat(result.getBackgroundUrl()).isEqualTo(newBackgroundUrl);
    }


}