package game;

enum GhostMode {Chasing, MoveAway, Scattered, Dead}

public abstract class Ghost extends Entity {

    protected PacMan pacMan;

    protected GhostMode ghostMode;

    protected int GhostHouseX;
    protected int GhostHouseY;
    protected boolean hasSpeed;

    protected int counter = 10;//duration of the Scatter duration


    protected Ghost(GameMap map, int PositionX, int PositionY, PacMan pacMan, int GhostHouseX, int GhostHouseY) {
        super(map, PositionX, PositionY);
        this.pacMan = pacMan;
        this.GhostHouseX = GhostHouseX * squareSize;
        this.GhostHouseY = GhostHouseY * squareSize;
        ghostMode = GhostMode.Chasing;
        hasSpeed = true;
    }

    protected boolean findPath(int targetX, int targetY) {


        if (PositionX < targetX && canMove(PositionX + squareSize, PositionY)) {

            PositionX += squareSize;

        } else if (PositionX > targetX && canMove(PositionX - squareSize, PositionY)) {

            PositionX -= squareSize;

        } else if (PositionY < targetY && canMove(PositionX, PositionY + squareSize)) {

            PositionY += squareSize;

        } else if (PositionY > targetY && canMove(PositionX, PositionY - squareSize)) {

            PositionY -= squareSize;

        }
        return PositionX == targetX && PositionY == targetY;


    }
}
