package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum GhostMode {Chasing, Scattered}

public abstract class Ghost extends Entity {

    protected PacMan pacMan;

    protected GhostMode ghostMode;


    protected boolean hasSpeed;

    protected int counter = 30;//duration of the Scatter movement


    protected int CounterForSlowMovement = 4;//to move in every 4 frame
    protected boolean needsReset = false;
    //to add an alternative chasing point


    protected int[] chasePointsX;
    protected int[] chasePointsY;
    protected int chasePointNext = 0;
    //not every ghost chase pacman directly

    protected int alternatePointChaseDuration = 10;
    protected int relativeX = 0;
    protected int relativeY = 0;

    protected Image[] frightened = new Image[2];
    protected ArrayList<Image> GhostImages;
    protected int animationCounter = 0;


    protected Ghost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY);
        this.pacMan = pacMan;

        ghostMode = GhostMode.Chasing;
        this.GhostImages = GhostImages;
        setImages(GhostImages.get(0), GhostImages.get(0));

        ImageIcon icon = new ImageIcon("res_files/frightened/fr1.png");
        frightened[0] = icon.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);

        ImageIcon icon2 = new ImageIcon("res_files/frightened/fr2.png");
        frightened[1] = icon.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);


    }

    //findPath also tells if collision happens
    private boolean findPath(int targetX, int targetY) {


        if (PositionX < targetX && canMove(PositionX + squareSize, PositionY)) {
            PositionX += squareSize;
            setImages(GhostImages.get(1), GhostImages.get(2));

        } else if (PositionX > targetX && canMove(PositionX - squareSize, PositionY)) {

            PositionX -= squareSize;
            setImages(GhostImages.get(3), GhostImages.get(4));

        } else if (PositionY < targetY && canMove(PositionX, PositionY + squareSize)) {
            if (isInTheFrontOfTheGH()) {
                PositionX += squareSize;
                setImages(GhostImages.get(1), GhostImages.get(2));
                return PositionX == targetX && PositionY == targetY;
            }
            PositionY += squareSize;
            setImages(GhostImages.get(7), GhostImages.get(8));

        } else if (PositionY > targetY && canMove(PositionX, PositionY - squareSize)) {
            PositionY -= squareSize;
            setImages(GhostImages.get(5), GhostImages.get(6));

        }


        return PositionX == targetX && PositionY == targetY;
    }

    public void move() {


        switch (ghostMode) {
            case Chasing -> {
                //only moving every second frame
                if (--counterForMovement != 0)
                    return;
                if (isCollision()) {
                    pacMan.hpLost();
                    needsReset = true;
                    counterForMovement = 2;
                    return;
                }
                if (isTheGhostHouse()) {
                    findPath(13 * squareSize, 11 * squareSize);
                    counterForMovement = 2;
                    return;
                }
                if (!wannaChase()) {
                    //to loop alternate chasing points
                    if (findPath(chasePointsX[chasePointNext] * squareSize, chasePointsY[chasePointNext] * squareSize) || --alternatePointChaseDuration == 0) {
                        chasePointNext = (++chasePointNext) % chasePointsX.length;
                        alternatePointChaseDuration = 10;
                    }

                } else {
                    findPath(pacMan.getPositionX() + relativeX, pacMan.getPositionY() + relativeY);
                }
                if (isCollision()) {
                    pacMan.hpLost();
                    needsReset = true;
                    counterForMovement = 2;
                    return;
                }


                counterForMovement = 2;

            }


            case Scattered -> {
                //only moving for every fourth frame
                if (--CounterForSlowMovement != 0) {

                    return;
                }
                //if 10 frame have passed
                if (--counter == 0) {
                    ghostMode = GhostMode.Chasing;
                    counter = 30;
                }
                if (isCollision()) {
                    Reset();
                    pacMan.addScore(200);
                }
                if (findPath(StartPositionX, StartPositionY))
                    ghostMode = GhostMode.Chasing;

                if (isCollision()) {
                    Reset();
                    pacMan.addScore(200);
                }

                CounterForSlowMovement = 4;
            }


        }

    }

    protected boolean isTheGhostHouse() {
        return 10 * squareSize < PositionX && PositionX < 16 * squareSize && 11 * squareSize < PositionY && PositionY < 16 * squareSize;
    }

    protected boolean isInTheFrontOfTheGH() {
        return 12 * squareSize < PositionX && PositionX < 15 * squareSize && PositionY == 11 * squareSize;
    }

    public boolean NeedsReset() {
        return needsReset;
    }


    protected abstract boolean wannaChase();

    protected boolean isCollision() {
        int pacManRight = pacMan.getPositionX() + squareSize;
        int pacManBottom = pacMan.getPositionY() + squareSize;
        int ghostRight = PositionX + squareSize;
        int ghostBottom = PositionY + squareSize;


        return PositionX < pacManRight && ghostRight > pacMan.getPositionX()
                && PositionY < pacManBottom && ghostBottom > pacMan.getPositionY();
    }

    @Override
    public void Reset() {
        PositionX = StartPositionX;
        PositionY = StartPositionY;
        ghostMode = GhostMode.Chasing;
        needsReset = false;
        counter = 30;
    }

    public void draw(Graphics g, ImageObserver observer) {
        Image img;
        if (ghostMode == GhostMode.Chasing) {
            img = animationImages[(animationCounter++) % 2];
        } else {
            img = frightened[(animationCounter++) % 2];
        }
        g.drawImage(img, PositionX, PositionY, observer);
    }

    public void setGhostMode(GhostMode ghostMode) {
        this.ghostMode = ghostMode;
    }
}
