package com.project.gameHubBackend.infrastructure.database.repository.mapper;

import com.project.gameHubBackend.domain.GamePurchase;
import com.project.gameHubBackend.domain.Purchase;
import com.project.gameHubBackend.infrastructure.database.entity.GamePurchaseEntity;
import com.project.gameHubBackend.infrastructure.database.entity.PurchaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseEntityMapper {

     @Mapping(source = "gamePurchases", target = "gamePurchases", qualifiedByName = "mapGamePurchases")
     @Mapping(target = "customer",ignore = true)
     @Mapping(target = "address.purchase",ignore = true)
     PurchaseEntity mapToEntity(Purchase purchase);



     @Mapping(target = "customer.customerId",ignore = true)
     @Mapping(target = "customer.email",ignore = true)
     @Mapping(target = "customer.opinions",ignore = true)
     @Mapping(target = "customer.purchases",ignore = true)
     @Mapping(source = "gamePurchases",target = "gamePurchases",qualifiedByName = "mapGamePurchasesFromEntity")
     Purchase mapFromEntity(PurchaseEntity entity);

     @Mapping(target = "customer",ignore = true)
     @Mapping(target = "address",ignore = true)
     @Mapping(target = "gamePurchases",ignore = true)
     Purchase mapFromEntityWithoutExtras(PurchaseEntity entity);



     @Named("mapGamePurchases")
     default List<GamePurchaseEntity> mapGamePurchases(List<GamePurchase> gamePurchases) {
          return gamePurchases.stream().map(this::map).toList();
     }

     @Named("mapGamePurchasesFromEntity")
     default List<GamePurchase> mapGamePurchasesFromEntity(List<GamePurchaseEntity> gamePurchaseEntities) {
          return gamePurchaseEntities.stream().map(this::mapFromEntity).toList();
     }

     @Mapping(target = "purchase",ignore = true)
     GamePurchaseEntity map(GamePurchase gamePurchase);
     @Mapping(target = "purchase",ignore = true)
     GamePurchase mapFromEntity(GamePurchaseEntity gamePurchaseEntity);



}
