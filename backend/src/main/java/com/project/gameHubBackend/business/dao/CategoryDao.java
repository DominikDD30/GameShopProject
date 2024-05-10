package com.project.gameHubBackend.business.dao;

import com.project.gameHubBackend.domain.Category;
import com.project.gameHubBackend.infrastructure.database.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;


public interface CategoryDao {

    List<Category> getCategories();

    void saveCategory(Category category);

    Optional<CategoryEntity> getCategoryEntityByName(String categoryName);

    void updateCategory(CategoryEntity existingCategory, Category category);
}
