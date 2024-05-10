package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "publisherGameId")
@ToString(of = {"publisherGameId"})
@Entity
@Table(name = "publisher_game")
public class PublisherGameEntity {

    @Id
    @Column(name = "publisher_game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherGameId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
