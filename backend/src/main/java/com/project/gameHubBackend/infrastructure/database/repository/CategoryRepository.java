package com.project.gameHubBackend.infrastructure.database.repository;

import com.project.gameHubBackend.business.dao.CategoryDao;
import com.project.gameHubBackend.domain.Category;
import com.project.gameHubBackend.infrastructure.database.entity.CategoryEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.CategoryJpaRepository;
import com.project.gameHubBackend.infrastructure.database.repository.mapper.CategoryEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepository implements CategoryDao{

    private CategoryJpaRepository categoryJpaRepository;
    private CategoryEntityMapper categoryEntityMapper;



    @Override
    public List<Category> getCategories() {
        return categoryJpaRepository.findAll().stream().map(categoryEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void saveCategory(Category category) {
        categoryJpaRepository.save(categoryEntityMapper.mapToEntity(category));
    }

    @Override
    public Optional<CategoryEntity> getCategoryEntityByName(String categoryName) {
        return categoryJpaRepository.findByCategoryName(categoryName);
    }

    @Override
    public void updateCategory(CategoryEntity existingCategory, Category categoryUpdate) {
        CategoryEntity categoryUpdateEntity = categoryEntityMapper.mapToEntity(categoryUpdate);
        existingCategory.setBackgroundUrl(categoryUpdateEntity.getBackgroundUrl());
        categoryJpaRepository.save(existingCategory);
    }
}
