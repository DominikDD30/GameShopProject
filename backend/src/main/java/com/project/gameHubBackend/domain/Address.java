package com.project.gameHubBackend.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"addressId"})
@ToString(of = {"addressId","postalCode","city","address"})
public class Address {

     Integer addressId;
     String postalCode;
     String city;
     String address;
     Set<Purchase> orders;

}
