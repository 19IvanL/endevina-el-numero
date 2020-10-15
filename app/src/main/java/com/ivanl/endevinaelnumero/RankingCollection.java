package com.ivanl.endevinaelnumero;

import java.util.ArrayList;

public class RankingCollection {

    public static ArrayList<Score> rankingList = new ArrayList<Score>();

    public static void print() {
        for (Score score : rankingList) {
            System.out.println(score.getUsername());
            System.out.println(score.getTries());
            System.out.println(score.getTime());
        }
    }

}
