package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.api.dto.OpinionRequestDTO;
import com.project.gameHubBackend.domain.OpinionCustomerGame;
import com.project.gameHubBackend.infrastructure.database.entity.OpinionCustomerGameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OpinionEntityMapper {
    OpinionCustomerGameEntity mapToEntity(OpinionCustomerGame opinion);

    @Mapping(target = "game",ignore = true)
    @Mapping(target = "customer",ignore = true)
    OpinionCustomerGame mapFromEntity(OpinionCustomerGameEntity opinionEntity);
}
