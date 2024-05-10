package com.project.gameHubBackend.util;

import com.project.gameHubBackend.infrastructure.rawg.model.*;
import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public  class RawgFixturesT {


    public  RawgGenre getSomeRawgGenre1(){
        return new RawgGenre(1,"action","somersuastdg2535");
    }
    public  RawgGenre getSomeRawgGenre2(){
        return new RawgGenre(46,"strategy","sdgsdadsha");
    }
    public  RawgGenre getSomeRawgGenre3(){
        return new RawgGenre(42,"adventure","sadgahsaijvnsa578");
    }

    public RawgPlatform getSomeRawgPlatform1(){
        return new RawgPlatform(2,"PC");
    }
    public RawgPlatform getSomeRawgPlatform2(){
        return new RawgPlatform(32,"PSP");
    }
    public RawgPlatform getSomeRawgPlatform3(){
        return new RawgPlatform(17,"XBOX");
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
        return new RawgGameDetail("gta description", null);
    }

    public RawgGameDetail getSomeRawgGameDetail2(){
        return new RawgGameDetail("Witcher description", null);
    }





}
