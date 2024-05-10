package com.project.gameHubBackend.util;

import java.util.Random;

public abstract class Functions {



    public static Integer getRandomNumber(int from, int to) {
        Random rand = new Random();
        return rand.nextInt(to - from +1) + from;
    }


    public static Integer getRandomAmountWithPossibleZero(int from,int to) {
        Random rand = new Random();
        int zeroTrigger = rand.nextInt(21) + 1;
        if(zeroTrigger==14){
            return 0;
        }
        else{
            return rand.nextInt(to - from +1) + from;
        }
    }


}
