package com.project.gameHubBackend.business;

import com.project.gameHubBackend.business.dao.CategoryDao;
import com.project.gameHubBackend.domain.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
   private CategoryDao categoryDao;

    public List<Category> getCategories() {
       return categoryDao.getCategories();
    }

    public void saveOrUpdateCategory(Category category) {
        categoryDao.getCategoryEntityByName(category.getCategoryName()).ifPresentOrElse(
                existingCategory->categoryDao.updateCategory(existingCategory,category),
                ()->categoryDao.saveCategory(category));
    }
}
