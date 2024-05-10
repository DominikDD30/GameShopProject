package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"opinionCustomerGameId"})
@ToString(of = {"opinionCustomerGameId","stars","description"})
@Entity
@Table(name = "opinion_customer_game")
public class OpinionCustomerGameEntity {

    @Id
    @Column(name = "opinion_customer_game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer opinionCustomerGameId;

    @Column(name = "star")
    private Short stars;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    @Basic
    private OffsetDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
