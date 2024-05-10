package com.project.gameHubBackend.util;

import com.project.gameHubBackend.domain.Category;
import com.project.gameHubBackend.domain.Game;
import com.project.gameHubBackend.domain.Photo;
import com.project.gameHubBackend.domain.Platform;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgGame;
import com.project.gameHubBackend.infrastructure.rawg.model.RawgPlatformWrapper;
import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public class Fixtures {


    public Category getSomeCategory1() {
        return Category.builder()
                .categoryId(1)
                .categoryName("action")
                .backgroundUrl("somersuastdg2535")
                .build();
    }

    public Category getSomeCategory2() {
        return Category.builder()
                .categoryId(2)
                .categoryName("strategy")
                .backgroundUrl("sdgsdadsha")
                .build();
    }

    public Category getSomeCategory3() {
        return Category.builder()
                .categoryId(3)
                .categoryName("adventure")
                .backgroundUrl("sadgahsaijvnsa578")
                .build();
    }

    public Platform getSomePlatform1() {
        return Platform.builder()
                .platformId(2)
                .name( "PC")
                .build();
    }

    public Platform getSomePlatform2() {
        return Platform.builder()
                .platformId(32)
                .name( "PSP")
                .build();
    }

    public Platform getSomePlatform3() {
        return Platform.builder()
                .platformId(17)
                .name( "XBOX")
                .build();
    }

    public Game getSomeGame1(){
        return Game.builder()
                .gameId(1)
                .name("gta")
                .description("gta description")
                .mainPhoto("asgagdbsb3564")
                .isContinuouslyDelivered(true)
                .build();
    }

    public Game getSomeGame2(){
        return Game.builder()
                .gameId(2)
                .name("witcher")
                .description("witcher description")
                .mainPhoto("imasdgashs2636")
                .isContinuouslyDelivered(true)
                .build();
    }
    public Game getSomeGame3(){
        return Game.builder()
                .gameId(3)
                .name("fifa21")
                .description("fifa21 description")
                .mainPhoto("awdahsdhn3464")
                .isContinuouslyDelivered(true)
                .build();
    }







}
