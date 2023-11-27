package player;

import java.io.Serializable;

public class Player implements Serializable {
    private int score;
    private String name;
    private long time;

    Player(String name, int score, long time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }
}
