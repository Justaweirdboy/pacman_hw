package game;

import java.awt.*;

import java.util.ArrayList;

public class ShyGhost extends Ghost {


    protected ShyGhost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY, pacMan, GhostImages);

        relativeX = -2 * squareSize;
        relativeY = 0 * squareSize;
        chasePointsX = new int[]{1, 5};
        chasePointsY = new int[]{1, 26};
    }


    @Override
    protected boolean wannaChase() {
        //chase only if pacman is in the lower half
        return pacMan.getPositionY() > 15 * squareSize;
    }
}
