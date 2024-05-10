package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.CategoriesDTO;
import com.project.gameHubBackend.api.dto.mapper.CategoryMapper;
import com.project.gameHubBackend.business.CategoryService;
import com.project.gameHubBackend.domain.Category;
import com.project.gameHubBackend.util.DTOFixturesT;
import com.project.gameHubBackend.util.FixturesT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryController categoryController;


    @Test
    void thatGetCategoriesWorkCorrectly() {
        //given
        when(categoryService.getCategories())
                .thenReturn(List.of(FixturesT.getSomeCategory1(), FixturesT.getSomeCategory2(), FixturesT.getSomeCategory3()));

        when(categoryMapper.map(any(Category.class)))
                .thenReturn(DTOFixturesT.getSomeCategoryDTO1())
                .thenReturn(DTOFixturesT.getSomeCategoryDTO2())
                .thenReturn(DTOFixturesT.getSomeCategoryDTO3());
        //when
        CategoriesDTO result = categoryController.getCategories();
        //then
        assertThat(result).isEqualTo(
                CategoriesDTO.builder()
                .results(List.of(DTOFixturesT.getSomeCategoryDTO1(), DTOFixturesT.getSomeCategoryDTO2(), DTOFixturesT.getSomeCategoryDTO3()))
                .build());
    }
}