package game;

import java.awt.*;

import java.util.ArrayList;

public class HunterGhostsBrother extends Ghost {
    protected HunterGhostsBrother(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY, pacMan, GhostImages);

        relativeX = -1 * squareSize;
        relativeY = 0;
        chasePointsX = new int[]{10, 26};
        chasePointsY = new int[]{1, 14};
    }


    @Override
    protected boolean wannaChase() {
        //if pacman has damaged once , he starts chasing
        return pacMan.getHP() < 3;
    }
}
