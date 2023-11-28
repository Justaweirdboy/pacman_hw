package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public abstract class Entity {
    protected int PositionX;
    protected int PositionY;
    protected int StartPositionX;
    protected int StartPositionY;

    protected int squareSize;

    protected int speed;
    protected char[][] map;
    protected int counterForMovement = 2;//to move only in every second frame

    protected char[] validTiles = {'*', ' ', '0', '-'};

    protected Image[] animationImages;


    Entity(GameMap map, int PositionX, int PositionY) {
        this.map = map.getGameMap();
        squareSize = map.getSquareSize();
        speed = squareSize;
        this.PositionX = PositionX * squareSize;
        this.PositionY = PositionY * squareSize;
        StartPositionX = this.PositionX;
        StartPositionY = this.PositionY;
        animationImages = new Image[2];
    }

    public boolean canMove(int x, int y) {
        int tileX = x / squareSize;
        int tileY = y / squareSize;

        if (tileX < 0 || tileY < 0 || tileY >= map.length || tileX >= map[0].length) {
            return false;
        }

        char tileValue = map[tileY][tileX];
        for (char ch : validTiles) {
            if (tileValue == ch) {
                return true;
            }
        }
        return false;
    }

    public abstract void draw(Graphics g, ImageObserver observer);


    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }

    public abstract void move();

    public abstract void Reset();

    public void setImages(Image image1, Image image2) {
        animationImages[0] = image1;
        animationImages[1] = image2;
    }


}
