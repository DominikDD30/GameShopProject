package com.project.gameHubBackend.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
public class PurchaseRequest {

     String customerName;
     String customerSurname;
     String customerEmail;
     String deliveryEmail;
     String pickupPointName;
     DeliveryType deliveryType;
     List<DesiredGame> games;
     String customerAddressCity;
     String customerAddressPostalCode;
     String customerAddressStreet;
}
