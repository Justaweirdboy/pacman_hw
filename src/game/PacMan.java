package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class PacMan extends Entity {


    private int directionX = 0;
    private int directionY = 0;
    private int Score = 0;

    private boolean mouthOpen = true;

    private int HP = 3;

    private int mouthState = 0;

    private Image neutral;

    private boolean killerMode = false;
    private int foodcounter = 240 + 4;


    // Constructor to initialize PacMan's position on the game map
    PacMan(GameMap map, int PositionX, int PositionY) {
        super(map, PositionX, PositionY);
        ImageIcon icon = new ImageIcon("res_files/Pacman/neutral.png");
        neutral = icon.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
        animationImages[0] = neutral;
        animationImages[1] = neutral;


    }

    // Method to handle PacMan's movement within the game
    public void move() {
        // Control movement based on frame counter
        if (--counterForMovement != 0)
            return;

        // Calculate new position based on direction
        int newX = PositionX + directionX * squareSize;
        int newY = PositionY + directionY * squareSize;

        // Check if the new position is valid for movement
        if (canMove(newX, newY)) {
            // Handle interaction with different types of map tiles
            if (map[newY / squareSize][newX / squareSize] == '*') {
                // TODO: Add score and update game map after eating food
                map[newY / squareSize][newX / squareSize] = ' ';
                --foodcounter;
                Score += 10;
            }
            if (map[newY / squareSize][newX / squareSize] == '0') {
                // TODO: Add score and activate killer mode after eating special food
                map[newY / squareSize][newX / squareSize] = ' ';
                --foodcounter;
                killerMode = true;
            }

            // Update PacMan's position
            PositionX = newX;
            PositionY = newY;
        }
        counterForMovement = 2; // Reset movement counter
    }

    // Method to draw PacMan on the game screen
    public void draw(Graphics g, ImageObserver observer) {
        // Get the appropriate image for PacMan's animation
        Image img = animationImages[(mouthState++) % 2];
        // Draw PacMan's image at the current position
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

    public boolean isOver() {
        return HP <= 0 || foodcounter == 0;
    }

    //to reset
    public void Reset() {
        PositionX = StartPositionX;
        PositionY = StartPositionY;
        directionX = 0;
        directionY = 0;
        animationImages[0] = neutral;
        animationImages[1] = neutral;

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

    public int getCounterForMovement() {
        return counterForMovement;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }
}




