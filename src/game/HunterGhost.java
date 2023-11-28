package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class HunterGhost extends Ghost {


    public HunterGhost(GameMap map, int PositionX, int PositionY, PacMan pacMan, ArrayList<Image> GhostImages) {
        super(map, PositionX, PositionY, pacMan, GhostImages);

    }

    @Override
    protected boolean wannaChase() {
        return true;
    }//always chases PacMan


}
