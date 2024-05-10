package com.project.gameHubBackend.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"trailerId","previewImage","url"})
@ToString(of = {"trailerId"})
@Entity
@Table(name = "trailer")
public class TrailerEntity {

    @Id
    @Column(name = "trailer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trailerId;

    @Column(name = "url")
    private String url;

    @Column(name = "preview_image")
    private  String previewImage;

}
