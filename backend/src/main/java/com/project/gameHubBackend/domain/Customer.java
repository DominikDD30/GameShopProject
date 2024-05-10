package com.project.gameHubBackend.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"email"})
@ToString(of = {"customerId", "name", "surname"})
public class Customer {

     Integer customerId;
     String name;
     String surname;
     String email;
     List<Purchase> purchases;
     List<OpinionCustomerGame> opinions;

    public List<Purchase>  getPurchases(){
        return Objects.isNull(purchases)?new ArrayList<>():new ArrayList<>(purchases);
    }
}
