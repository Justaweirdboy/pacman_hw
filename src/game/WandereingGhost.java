package game;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class WandereingGhost extends Ghost {
    protected WandereingGhost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY, pacMan, GhostImages);

        relativeX = 5 * squareSize;
        relativeY = 5 * squareSize;
        chasePointsX = new int[]{1, 1, 10, 26};
        chasePointsY = new int[]{1, 29, 28, 29};
    }


    @Override
    protected boolean wannaChase() {
        //this ghost only wanders from one point to another
        return false;
    }
}
