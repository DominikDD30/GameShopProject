package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"categoryId"})
@ToString(of = {"categoryId","categoryName"})
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category", unique = true)
    private String categoryName;

    @Column(name = "background_url")
    private String backgroundUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<CategoryGameEntity> categoryGames;

}
