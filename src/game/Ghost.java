package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;


enum GhostMode {Chasing, Scattered, Dead}

public abstract class Ghost extends Entity {

    protected PacMan pacMan;


    protected GhostMode ghostMode;


    protected boolean hasSpeed;

    protected int counter = 64;//duration of the Scatter movement


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
    protected Image dead;
    protected Image[] scattered = new Image[2];
    protected ArrayList<Image> GhostImages;
    protected int animationCounter = 0;


    protected Ghost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY);
        this.pacMan = pacMan;

        ghostMode = GhostMode.Chasing;
        this.GhostImages = GhostImages;
        setImages(GhostImages.get(0), GhostImages.get(0));

        ImageIcon icon = new ImageIcon("res_files/frightened/fr1.png");
        scattered[0] = icon.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);

        ImageIcon icon2 = new ImageIcon("res_files/frightened/fr2.png");
        scattered[1] = icon2.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);

        ImageIcon icon3 = new ImageIcon("res_files/dead.png");
        dead = icon3.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);


    }

    //findPath also tells if collision happens
    // Method to find a path for the ghost's movement towards a target
    private boolean findPath(int targetX, int targetY) {
        // Initializing data structures for pathfinding
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        Point[][] previous = new Point[map.length][map[0].length];

        // Adding the starting point to the queue and marking it as visited
        Point start = new Point(PositionX / squareSize, PositionY / squareSize);
        queue.add(start);
        visited[start.y][start.x] = true;

        // Breadth-First Search (BFS) algorithm
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // If we reach the target point, execute movement
            if (current.x * squareSize == targetX && current.y * squareSize == targetY) {
                executeMovement(previous, start, current);
                return PositionX == targetX && PositionY == targetY; // Path found successfully
            }

            // Discover neighboring points
            for (Point neighbor : getNeighbors(current)) {
                if (isValidMove(neighbor.x, neighbor.y) && !visited[neighbor.y][neighbor.x]) {
                    queue.add(neighbor);
                    visited[neighbor.y][neighbor.x] = true;
                    previous[neighbor.y][neighbor.x] = current;
                }
            }
        }

        return PositionX == targetX && PositionY == targetY; // Path not found
    }

    // Helper function to retrieve neighboring points
    private List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(point.x + 1, point.y));
        neighbors.add(new Point(point.x - 1, point.y));
        neighbors.add(new Point(point.x, point.y + 1));
        neighbors.add(new Point(point.x, point.y - 1));
        return neighbors;
    }

    // Helper function to check valid movement
    private boolean isValidMove(int x, int y) {
        return x >= 0 && y >= 0 && y < map.length && x < map[0].length && canMove(x * squareSize, y * squareSize);
    }

    // Helper function to execute movement along the path
    private void executeMovement(Point[][] previous, Point start, Point target) {
        List<Point> path = new ArrayList<>();
        Point current = target;

        while (!current.equals(start)) {
            path.add(current);
            current = previous[current.y][current.x];
        }

        Collections.reverse(path);
        if (path.isEmpty())
            return;
        Point point = path.get(0);

        int targetX = point.x * squareSize;
        int targetY = point.y * squareSize;

        // Perform movement along the path
        if (PositionX < targetX) {
            PositionX += squareSize;
            setImages(GhostImages.get(1), GhostImages.get(2));
        } else if (PositionX > targetX) {
            PositionX -= squareSize;
            setImages(GhostImages.get(3), GhostImages.get(4));
        } else if (PositionY < targetY) {
            PositionY += squareSize;
            setImages(GhostImages.get(7), GhostImages.get(8));
        } else if (PositionY > targetY) {
            PositionY -= squareSize;
            setImages(GhostImages.get(5), GhostImages.get(6));
        }
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
                    counterForMovement = pacMan.getCounterForMovement();
                    return;
                }

                if (!wannaChase()) {
                    //to loop alternate chasing points
                    if (findPath(chasePointsX[chasePointNext] * squareSize, chasePointsY[chasePointNext] * squareSize)) {
                        chasePointNext = (++chasePointNext) % chasePointsX.length;

                    }

                } else {
                    findPath(pacMan.getPositionX() + relativeX, pacMan.getPositionY() + relativeY);
                }
                if (isCollision()) {
                    pacMan.hpLost();
                    needsReset = true;
                    counterForMovement = pacMan.getCounterForMovement();
                    return;
                }


                counterForMovement = pacMan.getCounterForMovement();

            }


            case Scattered -> {

                //only moving for every fourth frame

                //if 10 frame have passed
                if (--counter == 0) {
                    ghostMode = GhostMode.Chasing;
                    counter = 64;
                }
                if (isCollision()) {
                    ghostMode = GhostMode.Dead;
                    counter = 64;
                    pacMan.addScore(200);
                    CounterForSlowMovement = pacMan.getCounterForMovement() * 2;
                    return;

                }
                if (--CounterForSlowMovement == 0) {
                    findPath(StartPositionX, StartPositionY);
                    CounterForSlowMovement = pacMan.getCounterForMovement() * 2;
                }


                if (isCollision()) {
                    ghostMode = GhostMode.Dead;
                    counter = 64;
                    pacMan.addScore(200);
                    CounterForSlowMovement = pacMan.getCounterForMovement() * 2;
                    return;
                }


            }
            case Dead -> {
                if (findPath(StartPositionX, StartPositionY)) {
                    ghostMode = GhostMode.Chasing;
                    counterForMovement = pacMan.getCounterForMovement();
                }
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
        counter = 64;
    }

    //draw the propriate state of the ghost and its position
    public void draw(Graphics g, ImageObserver observer) {
        Image img;
        if (ghostMode == GhostMode.Chasing) {
            img = animationImages[(animationCounter++) % 2];
        } else if (ghostMode == GhostMode.Scattered) {
            img = scattered[(animationCounter++) % 2];
        } else {
            img = dead;
        }

        g.drawImage(img, PositionX, PositionY, observer);
    }

    public void setGhostMode(GhostMode ghostMode) {
        this.ghostMode = ghostMode;
    }
}
