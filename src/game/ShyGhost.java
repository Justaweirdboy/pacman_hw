package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class ShyGhost extends Ghost {


    protected ShyGhost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY, pacMan, GhostImages);

        relativeX = 1 * squareSize;
        relativeY = 7 * squareSize;
        chasePointsX = new int[]{14, 10};
        chasePointsY = new int[]{30, 28};
    }


    @Override
    protected boolean wannaChase() {
        //chase only if pacman is in the lower half
        return pacMan.getPositionY() > 15 * squareSize;
    }
}
