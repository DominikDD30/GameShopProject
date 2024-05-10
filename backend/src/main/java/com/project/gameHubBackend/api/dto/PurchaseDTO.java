package com.project.gameHubBackend.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    private String purchaseNumber;
    private String dateStarted;
    private String status;
    private String dateCompleted;
    private List<GamePurchaseDTO> gamePurchases;
    private BigDecimal totalPrice;
    private String deliveryType;
    private String pickupPointName;
    private String postalCode;
    private String city;
    private String address;
    private String customer;
}
