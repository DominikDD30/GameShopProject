package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.Category;
import com.project.gameHubBackend.infrastructure.database.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface CategoryEntityMapper {

    @Mapping(target = "categoryGames", ignore = true)
    Category mapFromEntity(CategoryEntity entity);

    @Mapping(target = "categoryGames", ignore = true)
    CategoryEntity mapToEntity(Category category);

}
