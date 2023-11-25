package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class PacMan extends Entity {


    private int directionX = 0;
    private int directionY = 0;


    PacMan(GameMap map, int PositionX, int PositionY) {
        super(map, PositionX, PositionY);


    }

    public void move() {
        int newX = PositionX + directionX * squareSize;
        int newY = PositionY + directionY * squareSize;

        if (canMove(newX, newY)) {
            if (map[newY / squareSize][newX / squareSize] == '*') {
                //TODO add score
                map[newY / squareSize][newX / squareSize] = ' ';
            }

            PositionX = newX;
            PositionY = newY;
        }
    }


    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillArc(PositionX, PositionY, squareSize, squareSize, 45, 270);
    }

    public void actionPerformed(ActionEvent e) {
        move();

    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(int directionY) {
        this.directionY = directionY;
    }


}
