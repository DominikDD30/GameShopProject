package com.project.gameHubBackend.domain;

public enum OrderStatus {
    PENDING("in pending"),
    PENDING_SHIPMENT("prepared to  shipment"),
    SHIPPED("in shipment"),
    DELIVERED("delivered"),
    CANCELLED("cancelled");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
