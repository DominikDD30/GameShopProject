package com.project.gameHubBackend.domain;

public enum DeliveryType {
    ELECTRONIC_SHIPMENT("electronic shipment"),
    POSTAL("postal"),
    COURIER("courier"),
    PICKUP_POINT("pickup point");

    private final String name;

    DeliveryType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
