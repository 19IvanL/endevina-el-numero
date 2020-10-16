package com.ivanl.endevinaelnumero;

public class Score implements Comparable<Score> {

    private String username;
    private int tries, time;

    public Score() {

    }

    public Score(String username, int tries, int time) {
        this.username = username;
        this.tries = tries;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Score o) {
        if (this.tries == o.tries)
            return this.time - o.time;
        else
            return this.tries - o.tries;
    }

}
