package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "platformId")
@ToString(of = {"platformId","name"})
@Entity
@Table(name = "platform")
public class PlatformEntity {

    @Id
    @Column(name = "platform_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer platformId;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    private Set<PlatformGameEntity> platformGames;

}
