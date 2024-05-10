package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "gamePurchaseId")
@ToString(of = {"gamePurchaseId","quantity","game","platform","price"})
@Entity
@Table(name = "game_purchase")
public class GamePurchaseEntity {

    @Id
    @Column(name = "game_purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gamePurchaseId;

    @Column(name = "game_name")
    private String game;

    @Column(name = "game_image")
    private String gameImage;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "platform")
    private String platform;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

}
