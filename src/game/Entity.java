package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

// Abstract class representing a game entity
public abstract class Entity {
    // Position variables
    protected int PositionX; // X coordinate of the entity
    protected int PositionY; // Y coordinate of the entity
    protected int StartPositionX; // Initial X coordinate of the entity
    protected int StartPositionY; // Initial Y coordinate of the entity

    protected int squareSize; // Size of each square in the game grid

    protected int speed; // Speed of movement
    protected char[][] map; // Game map grid
    protected int counterForMovement = 2; // To move only every second frame

    protected char[] validTiles = {'*', ' ', '0', '-'}; // Valid tiles on the map

    protected Image[] animationImages; // Array to hold animation images

    // Constructor for the Entity class
    Entity(GameMap map, int PositionX, int PositionY) {
        this.map = map.getGameMap(); // Get the game map
        squareSize = map.getSquareSize(); // Get the square size from the map
        speed = squareSize; // Set the speed equal to the square size
        this.PositionX = PositionX * squareSize; // Set the X coordinate of the entity
        this.PositionY = PositionY * squareSize; // Set the Y coordinate of the entity
        StartPositionX = this.PositionX; // Set the initial X coordinate
        StartPositionY = this.PositionY; // Set the initial Y coordinate
        animationImages = new Image[2]; // Initialize the array to hold animation images
    }

    // Method to check if the entity can move to a certain position
    public boolean canMove(int x, int y) {
        int tileX = x / squareSize; // Calculate the X coordinate on the grid
        int tileY = y / squareSize; // Calculate the Y coordinate on the grid

        // Check if the position is within the bounds of the map
        if (tileX < 0 || tileY < 0 || tileY >= map.length || tileX >= map[0].length) {
            return false;
        }

        char tileValue = map[tileY][tileX]; // Get the value of the tile on the map
        // Check if the tile value is among the valid tiles for movement
        for (char ch : validTiles) {
            if (tileValue == ch) {
                return true; // Return true if the tile is valid for movement
            }
        }
        return false; // Return false if the tile is not valid for movement
    }

    // Abstract method to draw the entity on the screen
    public abstract void draw(Graphics g, ImageObserver observer);

    // Getter method for the X coordinate of the entity
    public int getPositionX() {
        return PositionX;
    }

    // Getter method for the Y coordinate of the entity
    public int getPositionY() {
        return PositionY;
    }

    // Abstract method to move the entity
    public abstract void move();

    // Abstract method to reset the entity to its initial position
    public abstract void Reset();

    // Method to set images for animation
    public void setImages(Image image1, Image image2) {
        animationImages[0] = image1; // Set the first image for animation
        animationImages[1] = image2; // Set the second image for animation
    }
}
