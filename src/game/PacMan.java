package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.Timer;
import java.util.TimerTask;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class PacMan extends Entity {


    private int directionX = 0;
    private int directionY = 0;
    private int Score = 0;

    private boolean mouthOpen = true;

    private int HP = 3;

    private int mouthState = 0;

    private boolean killerMode = false;


    PacMan(GameMap map, int PositionX, int PositionY) {
        super(map, PositionX, PositionY);


    }

    public void move() {
        if (--counterForMovement != 0)
            return;
        int newX = PositionX + directionX * squareSize;
        int newY = PositionY + directionY * squareSize;

        if (canMove(newX, newY)) {
            if (map[newY / squareSize][newX / squareSize] == '*') {
                //TODO add score
                map[newY / squareSize][newX / squareSize] = ' ';
                Score += 10;
            }
            if (map[newY / squareSize][newX / squareSize] == '0') {
                //TODO add score
                map[newY / squareSize][newX / squareSize] = ' ';
                killerMode = true;
            }

            PositionX = newX;
            PositionY = newY;
        }
        counterForMovement = 2;
    }


    public void draw(Graphics g, ImageObserver observer) {

        Image img = animationImages[(mouthState++) % 2];
        g.drawImage(img, PositionX, PositionY, observer);
    }


    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }


    public boolean isMouthOpen() {
        return mouthOpen;
    }

    public void setMouthOpen(boolean mouthOpen) {
        this.mouthOpen = mouthOpen;
    }

    public void hpLost() {
        //returns true if pacman dies
        --HP;
    }

    public boolean isDead() {
        return HP == 0;
    }

    public void Reset() {
        PositionX = StartPositionX;
        PositionY = StartPositionY;
        directionX = 0;
        directionY = 0;

    }

    public int getHP() {
        return HP;
    }

    public void addScore(int points) {
        Score += points;
    }

    public int getScore() {
        return Score;
    }

    public boolean isKillerMode() {
        return killerMode;
    }

    public void setKillerMode(boolean killerMode) {
        this.killerMode = killerMode;
    }
}




