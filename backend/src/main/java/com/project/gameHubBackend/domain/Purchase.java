package com.project.gameHubBackend.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@With
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"purchaseNumber"})
@ToString(of = {"purchaseId", "purchaseNumber", "dateStarted", "dateCompleted", "status", "deliveryType"})
public class Purchase {

    private Integer purchaseId;
    private String purchaseNumber;
    private OffsetDateTime dateStarted;
    private OffsetDateTime dateCompleted;
    private OrderStatus status;
    private DeliveryType deliveryType;
    private String emailToSend;
    private Customer customer;
    private String pickupPointName;
    private Address address;
    private List<GamePurchase> gamePurchases;

    public BigDecimal calculateTotalPrice(){
        return gamePurchases.stream()
                .map(gamePurchase ->gamePurchase.getPrice()
                        .multiply(BigDecimal.valueOf(gamePurchase.getQuantity()))
                )
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public void addGamePurchase(GamePurchase gamePurchase){
        if(gamePurchases ==null){
            gamePurchases =new ArrayList<>();
        }
        gamePurchases.add(gamePurchase);
        gamePurchase.setPurchase(this);
    }
}
