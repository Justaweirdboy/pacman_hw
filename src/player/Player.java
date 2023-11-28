package player;

import java.io.Serializable;

public class Player implements Serializable {
    private int score;
    private String name;
    private long time;

    public Player(String name, int score, long time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public String getFormatTime() {
        //to format minutes and seconds
        long seconds = time / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
