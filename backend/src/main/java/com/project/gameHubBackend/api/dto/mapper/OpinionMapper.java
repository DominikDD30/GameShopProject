package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.OpinionDTO;
import com.project.gameHubBackend.api.dto.OpinionRequestDTO;
import com.project.gameHubBackend.domain.OpinionCustomerGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OpinionMapper {

    OpinionCustomerGame map(OpinionRequestDTO opinionRequest);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "date", ignore = true)
    OpinionDTO mapToDTO(OpinionCustomerGame opinion);
}
