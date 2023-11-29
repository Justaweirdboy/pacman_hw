package game;

import java.awt.*;
import java.util.ArrayList;

public class WanderingGhost extends Ghost {
    protected WanderingGhost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY, pacMan, GhostImages);


        chasePointsX = new int[]{1, 26, 1, 26};
        chasePointsY = new int[]{1, 29, 29, 1};
    }


    @Override
    protected boolean wannaChase() {
        //this ghost only wanders from one point to another
        return false;
    }
}
