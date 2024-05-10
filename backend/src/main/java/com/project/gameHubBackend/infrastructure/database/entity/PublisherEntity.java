package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"publisherId"})
@ToString(of = {"publisherId","publisherName"})
@Entity
@Table(name = "publisher")
public class PublisherEntity {

    @Id
    @Column(name = "publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherId;

    @Column(name = "publisher_name", unique = true)
    private String publisherName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    private Set<PublisherGameEntity> publisherGames;

}
