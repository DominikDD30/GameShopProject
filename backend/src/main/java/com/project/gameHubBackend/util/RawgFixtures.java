package com.project.gameHubBackend.util;

import com.project.gameHubBackend.infrastructure.rawg.model.*;
import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public  class RawgFixtures {


    public  RawgGenre getSomeRawgGenre1(){
        return new RawgGenre(4,"Action","https://media.rawg.io/media/games/120/1201a40e4364557b124392ee50317b99.jpg");
    }
    public  RawgGenre getSomeRawgGenre2(){
        return new RawgGenre(10,"Strategy","https://media.rawg.io/media/games/fd9/fd92f105dcd6491bc5d61135033d1f19.jpg");
    }
    public  RawgGenre getSomeRawgGenre3(){
        return new RawgGenre(3,"Adventure","https://media.rawg.io/media/games/364/3642d850efb217c58feab80b8affaa89.jpg");
    }

    public  RawgGenre getSomeRawgGenre4(){
        return new RawgGenre(4,"Action","https://media.rawg.io/media/games/b7d/b7d3f1715fa8381a4e780173a197a615.jpg");
    }

    public RawgPlatform getSomeRawgPlatform1(){
        return new RawgPlatform(1,"PC");
    }
    public RawgPlatform getSomeRawgPlatform2(){
        return new RawgPlatform(2,"PS");
    }
    public RawgPlatform getSomeRawgPlatform3(){
        return new RawgPlatform(3,"Xbox");
    }

    public RawgPlatform getSomeRawgPlatform4(){
        return new RawgPlatform(2,"PlayStation");
    }

    public RawgPhoto getSomeRawgPhoto1(){
        return new RawgPhoto(1,"imasdgashs2636");
    }
    public RawgPhoto getSomeRawgPhoto2(){
        return new RawgPhoto(2,"awdahsdhn3464");
    }
    public RawgPhoto getSomeRawgPhoto3(){return new RawgPhoto(2,"osagodng3");}

    public RawgPhoto getSomeRawgPhoto4(){
        return new RawgPhoto(2,"0wagjesdahsdn6");
    }

    public RawgGameDetail getSomeRawgGameDetail1(){
        return new RawgGameDetail("""
                Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update.\s
                Simultaneous storytelling from three unique perspectives:\s
                Follow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from.\s
                GTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.
                                
                Español
                Rockstar Games se hizo más grande desde su entrega anterior de la serie. Obtienes la construcción del mundo complicada y realista de Liberty City de GTA4 en el escenario de Los Santos, un viejo favorito de los fans, GTA San Andreas. 561 vehículos diferentes (incluidos todos los transportes que puede operar) y la cantidad aumenta con cada actualización.
                Narración simultánea desde tres perspectivas únicas:
                Sigue a Michael, ex-criminal que vive su vida de ocio lejos del pasado, Franklin, un niño que busca un futuro mejor, y Trevor, el pasado exacto del que Michael está tratando de huir.
                GTA Online proporcionará muchos desafíos adicionales incluso para los jugadores experimentados, recién llegados del modo historia. Ahora tendrás otros jugadores cerca que pueden ayudarte con la misma probabilidad que arruinar tu misión. Los jugadores pueden experimentar todas las mecánicas de GTA actualizadas a través del personaje personalizable único, y el contenido de la comunidad combinado con el sistema de nivelación tiende a mantener a todos ocupados y comprometidos.""", null);
    }

    public RawgGameDetail getSomeRawgGameDetail2(){
        return new RawgGameDetail("Witcher description", null);
    }
    public RawgGameDetail getSomeRawgGameDetail3(){return new RawgGameDetail("fifa21 description", null);}

    public RawgGame getRawgGame1() {
        return RawgGame.builder()
                .id(3498)
                .name("Grand Theft Auto V")
                .description_raw(getRawgGameDetail(3498).description_raw())
                .background_image("https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg")
                .short_screenshots(List.of(RawgFixtures.getSomeRawgPhoto3(), RawgFixtures.getSomeRawgPhoto2()))
                .genres(List.of(RawgFixtures.getSomeRawgGenre1()))
                .parent_platforms(List.of(
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform1()),
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform2()),
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform4())))
                .movie(getRawgGameMovie(3498))

                .build();
    }

    public RawgGame getRawgGame2() {
        return RawgGame.builder()
                .id(3328)
                .name("The Witcher 3: Wild Hunt")
                .description_raw(getRawgGameDetail(3328).description_raw())
                .background_image("https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg")
                .short_screenshots(List.of(RawgFixtures.getSomeRawgPhoto1()))
                .genres(List.of(RawgFixtures.getSomeRawgGenre2(), RawgFixtures.getSomeRawgGenre3()))
                .parent_platforms(List.of(
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform1()),
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform2())))
                .movie(getRawgGameMovie(3328))
                .build();
    }

    public RawgGame getRawgGame3() {
        return RawgGame.builder()
                .id(4200)
                .name("Portal 2")
                .description_raw(getRawgGameDetail(4200).description_raw())
                .background_image("https://media.rawg.io/media/games/2ba/2bac0e87cf45e5b508f227d281c9252a.jpg")
                .short_screenshots(List.of(RawgFixtures.getSomeRawgPhoto4()))
                .genres(List.of(RawgFixtures.getSomeRawgGenre1()))
                .parent_platforms(List.of(
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform1()),
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform2()),
                        new RawgPlatformWrapper(RawgFixtures.getSomeRawgPlatform3())))
                .movie(getRawgGameMovie(4200))
                .build();
    }

    public RawgGameDetail getRawgGameDetail(int gameId) {
        return switch (gameId) {
            case 3498 -> RawgFixtures.getSomeRawgGameDetail1();
            case 3328 -> RawgFixtures.getSomeRawgGameDetail2();
            case 4200 -> RawgFixtures.getSomeRawgGameDetail3();
            default -> throw new IllegalStateException("Unexpected value: " + gameId);
        };
    }

    public RawgMovie getRawgGameMovie(int gameId) {
        return switch (gameId) {
            case 3498 -> new RawgMovie("https://media.rawg.io/media/movies/d8a/d8a61a3a12e52114afdbc28f2c813f5c.jpg", new RawgMovieData("https://steamcdn-a.akamaihd.net/steam/apps/256693661/movie480.mp4"));
            case 3328 -> new RawgMovie("shhsra2sdh2", new RawgMovieData("erjgoa83"));
            case 4200 -> new RawgMovie("asdfhfdfbxd", new RawgMovieData("ajof82"));
            default -> throw new IllegalStateException("Unexpected value: " + gameId);
        };
    }



}
