package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLocking;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "platformGameId")
@ToString(of = {"platformGameId","leftInStock","price"})
@Entity
@Table(name = "platform_game")
public class PlatformGameEntity {

    @Id
    @Column(name = "platform_game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer platformGameId;

    @Column(name = "left_in_stock")
    private Integer leftInStock;

    @Column(name = "price")
    private BigDecimal price;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "platform_id")
    private PlatformEntity platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
