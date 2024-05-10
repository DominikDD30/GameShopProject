package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.Customer;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.domain.GamePurchase;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.infrastructure.database.entity.CustomerEntity;
import com.project.gameHubBackend.infrastructure.database.entity.GameEntity;
import com.project.gameHubBackend.infrastructure.database.entity.GamePurchaseEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PurchaseEntity;
import com.project.gameHubBackend.infrastructure.database.repository.jpa.CustomerJpaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerEntityMapper {
    @Mapping(target = "opinions",ignore = true)
    @Mapping(target = "purchases", ignore= true)
    Customer mapFromEntity(CustomerEntity entity);

    @Mapping(target = "opinions",ignore = true)
    @Mapping(target = "purchases",ignore = true)
    CustomerEntity mapToEntity(Customer customer);


    @Named("mapGamePurchases")
    @SuppressWarnings("unused")
    default List<GamePurchase> mapGamePurchases(List<GamePurchaseEntity> gamePurchaseEntities) {
        return gamePurchaseEntities.stream().map(this::mapFromEntity).toList();
    }
    @Mapping(target = "purchase", ignore = true)
    GamePurchase mapFromEntity(GamePurchaseEntity entity);


}
