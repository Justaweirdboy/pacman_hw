package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class Entity {
    protected int PositionX;
    protected int PositionY;
    protected int squareSize;

    protected int speed;
    protected char[][] map;

    protected char[] validTiles = {'*', ' ', '0', 'P'};

    Entity(GameMap map, int PositionX, int PositionY) {
        this.map = map.getGameMap();
        squareSize = map.getSquareSize();
        speed = squareSize;
        this.PositionX = PositionX * squareSize;
        this.PositionY = PositionY * squareSize;
    }

    public boolean canMove(int x, int y) {
        int tileX = x / squareSize;
        int tileY = y / squareSize;

        for (char ch : validTiles)
            if (map[tileY][tileX] == ch)
                return true;

        return false;
    }

    public abstract void draw(Graphics g);

    public abstract void actionPerformed(ActionEvent e);

    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }

    public abstract void move();
}
