package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.Photo;
import com.project.gameHubBackend.infrastructure.database.entity.PhotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoEntityMapper {
    @Mapping(target = "game", ignore = true)
    PhotoEntity mapToEntity(Photo photo);

}
