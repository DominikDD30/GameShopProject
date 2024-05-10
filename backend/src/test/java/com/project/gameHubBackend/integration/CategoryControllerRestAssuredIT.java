package com.project.gameHubBackend.integration;

import com.project.gameHubBackend.api.dto.CategoriesDTO;
import com.project.gameHubBackend.api.dto.CategoryDTO;
import com.project.gameHubBackend.api.dto.mapper.CategoryMapper;
import com.project.gameHubBackend.infrastructure.database.entity.CategoryEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.CategoryJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.CategoryEntityMapper;
import com.project.gameHubBackend.integration.configuration.RestAssuredIntegrationTestBase;
import com.project.gameHubBackend.integration.support.CategoryControllerTestSupport;
import com.project.gameHubBackend.util.EntityFixturesT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CategoryControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
implements CategoryControllerTestSupport
{

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;
    @Autowired
    private CategoryEntityMapper categoryEntityMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void thatCategoryUpdateWorksCorrectly(){
        //given
        CategoryEntity someCategory1 = EntityFixturesT.getSomeCategory1();
        CategoryEntity someCategory2 = EntityFixturesT.getSomeCategory2();
        categoryJpaRepository.saveAll(List.of(someCategory1, someCategory2));

        CategoryDTO categoryDTO1 = categoryMapper.map(categoryEntityMapper.mapFromEntity(someCategory1));
        CategoryDTO categoryDTO2 = categoryMapper.map(categoryEntityMapper.mapFromEntity(someCategory2));
        //when
        CategoriesDTO categoriesDTO = getCategories();
        //then
        Assertions.assertThat(categoriesDTO.getResults())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("categoryId")
                .containsExactlyInAnyOrder(categoryDTO1,categoryDTO2);
    }


}
