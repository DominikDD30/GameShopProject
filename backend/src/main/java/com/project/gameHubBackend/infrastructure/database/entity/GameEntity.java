package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "gameId")
@ToString(of = {"gameId", "gameNumber", "name"})
@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column(name = "game_number", unique = true)
    private String gameNumber;

    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;

    @Column(name = "main_photo", unique = true)
    private String mainPhoto;

    @Column(name = "is_continuously_delivered")
    private Boolean isContinuouslyDelivered;

    @Column(name = "is_sold_out")
    private Boolean isSoldOut;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trailer_id")
    private TrailerEntity trailer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game", cascade = CascadeType.REMOVE)
    private Set<PhotoEntity> photos;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private Set<PublisherGameEntity> publishers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @Fetch(FetchMode.JOIN)
    private Set<CategoryGameEntity> gameCategories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    @Fetch(FetchMode.JOIN)
    private Set<PlatformGameEntity> gamePlatforms;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
    private Set<OpinionCustomerGameEntity> gameOpinions;
}
