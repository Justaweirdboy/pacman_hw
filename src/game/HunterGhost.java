package game;

import java.awt.*;
import java.awt.event.ActionEvent;

public class HunterGhost extends Ghost {


    public HunterGhost(GameMap map, int PositionX, int PositionY, PacMan pacMan) {
        super(map, PositionX, PositionY, pacMan, 5, 6);
    }

    public void move() {
        switch (ghostMode) {
            case Chasing -> {
                if (findPath(pacMan.PositionX, pacMan.PositionY))
                    ghostMode = GhostMode.MoveAway;
            }
            case MoveAway -> {
                findPath(GhostHouseX, GhostHouseY);

            }
            case Scattered -> {
                if (hasSpeed)
                    findPath(GhostHouseX, GhostHouseY);
                //"half" speed
                hasSpeed = !hasSpeed;
                //if 10 frame have passed
                if (++counter % 10 == 0)
                    ghostMode = GhostMode.Chasing;
            }
            case Dead -> {
                //double speed, kinda teleportation feeling, but dont care
                findPath(GhostHouseX, GhostHouseY);
                findPath(GhostHouseX, GhostHouseY);
                if (PositionX == GhostHouseX && PositionY == GhostHouseY)
                    ghostMode = GhostMode.Chasing;

            }

        }

    }


    public void draw(Graphics g) {
        // Rajzolás a szellem pozíciójába
        g.setColor(Color.RED);
        g.fillOval(PositionX, PositionY, squareSize, squareSize);
    }

    public void actionPerformed(ActionEvent e) {
        move();

    }
}
