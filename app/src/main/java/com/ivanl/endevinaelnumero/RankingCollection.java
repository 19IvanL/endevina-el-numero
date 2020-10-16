package com.ivanl.endevinaelnumero;

import java.util.ArrayList;

public class RankingCollection {

    public static ArrayList<Score> rankingList = new ArrayList<Score>();
    private static Boolean botsCreated = false;

    public static void print() {
        for (Score score : rankingList) {
            System.out.println(score.getUsername());
            System.out.println(score.getTries());
            System.out.println(score.getTime());
        }
    }

    public static void addBots() {
        if (!botsCreated) {
            for (int i = 0; i < 10; i++) {
                Score score = new Score();
                score.setUsername("Bot" +
                        (i + 1));
                score.setTries((int)((Math.random() * 50) + 1));
                score.setTime((int)((Math.random() * 60) + 1));
                rankingList.add(score);
            }
            botsCreated = true;
        }
    }

}
