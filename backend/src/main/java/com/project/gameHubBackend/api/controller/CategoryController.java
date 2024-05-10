package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.api.dto.CategoriesDTO;
import com.project.gameHubBackend.api.dto.mapper.CategoryMapper;
import com.project.gameHubBackend.business.CategoryService;
import com.project.gameHubBackend.domain.Category;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.project.gameHubBackend.api.controller.CategoryController.CATEGORIES;

@RestController
@AllArgsConstructor
@RequestMapping(CATEGORIES)
public class CategoryController {

    public static final String CATEGORIES="/categories";
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @GetMapping
    public CategoriesDTO getCategories(){
        List<Category> categories = categoryService.getCategories();
        return  new CategoriesDTO(categories.stream().map(categoryMapper::map).toList());
    }
}
