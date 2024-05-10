package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.GamePurchase;
import com.project.gameHubBackend.infrastructure.database.entity.GamePurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GamePurchaseEntityMapper {

    @Mapping(target = "purchase", ignore = true)
    GamePurchaseEntity mapToEntity(GamePurchase gamePurchase);

}
