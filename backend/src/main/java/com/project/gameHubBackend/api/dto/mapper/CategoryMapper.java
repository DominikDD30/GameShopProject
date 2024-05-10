package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.CategoryDTO;
import com.project.gameHubBackend.domain.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CategoryMapper {
    CategoryDTO map(Category category);
}
