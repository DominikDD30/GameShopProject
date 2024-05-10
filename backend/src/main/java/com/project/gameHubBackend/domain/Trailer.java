package com.project.gameHubBackend.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"trailerId","previewImage","url"})
@ToString(of = {"trailerId"})
public class Trailer {

     Integer trailerId;
     String previewImage;
     String url;
     Game game;
}
