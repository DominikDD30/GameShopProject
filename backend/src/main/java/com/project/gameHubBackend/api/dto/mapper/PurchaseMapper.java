package com.project.gameHubBackend.api.dto.mapper;

import com.project.gameHubBackend.api.dto.GamePurchaseDTO;
import com.project.gameHubBackend.api.dto.InvoiceDTO;
import com.project.gameHubBackend.api.dto.PurchaseDTO;
import com.project.gameHubBackend.domain.GamePurchase;
import com.project.gameHubBackend.domain.Purchase;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface PurchaseMapper extends OffsetDateTimeMapper {


    default InvoiceDTO mapGamePurchase(Purchase purchase) {
        return InvoiceDTO.builder()
                .purchaseNumber(purchase.getPurchaseNumber())
                .build();
    }

    default PurchaseDTO map(Purchase purchase) {
        PurchaseDTO purchaseDTO = PurchaseDTO.builder()
                .purchaseNumber(purchase.getPurchaseNumber())
                .dateStarted(mapOffsetDateTimeToString(purchase.getDateStarted()))
                .dateCompleted(mapOffsetDateTimeToString(purchase.getDateCompleted()))
                .status(purchase.getStatus().getName())
                .deliveryType(purchase.getDeliveryType().getName())
                .gamePurchases(purchase.getGamePurchases().stream().map(this::mapGamePurchase).toList())
                .totalPrice(purchase.calculateTotalPrice())
                .pickupPointName(purchase.getPickupPointName())
                .customer(purchase.getCustomer().getName() + " " + purchase.getCustomer().getSurname())
                .build();
        if (Objects.nonNull(purchase.getAddress())) {
            purchaseDTO.setPostalCode(purchase.getAddress().getPostalCode());
            purchaseDTO.setCity(purchase.getAddress().getCity());
            purchaseDTO.setAddress(purchase.getAddress().getAddress());
        }
        return purchaseDTO;
    }

    GamePurchaseDTO mapGamePurchase(GamePurchase gamePurchase);

}
