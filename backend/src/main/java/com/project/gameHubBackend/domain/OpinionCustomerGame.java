package com.project.gameHubBackend.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"opinionCustomerGameId"})
@ToString(of = {"opinionCustomerGameId","stars","description","date"})
public class OpinionCustomerGame {

     Integer opinionCustomerGameId;
     Short stars;
     String description;
     OffsetDateTime date;
     Game game;
     Customer customer;
}
