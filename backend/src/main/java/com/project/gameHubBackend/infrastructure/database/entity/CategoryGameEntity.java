package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"categoryGameId"})
@ToString(of = "categoryGameId")
@Entity
@Table(name = "category_game")
public class CategoryGameEntity {

    @Id
    @Column(name = "category_game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryGameId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
