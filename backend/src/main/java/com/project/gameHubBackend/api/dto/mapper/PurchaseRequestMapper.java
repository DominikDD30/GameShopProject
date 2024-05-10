package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.DesiredGameDTO;
import com.project.gameHubBackend.api.dto.PurchaseRequestDTO;
import com.project.gameHubBackend.domain.DeliveryType;
import com.project.gameHubBackend.domain.DesiredGame;
import com.project.gameHubBackend.domain.PurchaseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseRequestMapper {
    @Mapping(source = "games", target = "games", qualifiedByName = "mapPurchaseGames")
    @Mapping(source = "deliveryType", target = "deliveryType", qualifiedByName = "mapDeliveryType")
    PurchaseRequest map(PurchaseRequestDTO purchaseDTO);

    @Named("mapPurchaseGames")
    default List<DesiredGame> mapPurchaseGames(List<DesiredGameDTO> games) {
        return games.stream()
                .map(this::map).toList();
    }
    @Named("mapDeliveryType")
    default DeliveryType mapDeliveryType(String deliveryType) {
        return DeliveryType.valueOf(deliveryType);
    }


    DesiredGame map(DesiredGameDTO gameDTO);
}
