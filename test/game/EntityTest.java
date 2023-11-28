package game;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EntityTest {
    private PacMan pacMan;
    private HunterGhost hunterGhost;

    @Before
    public void setUp() throws Exception {
        //to simulate images
        ArrayList<Image> images = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            images.add(null);
        }


        //fake data
        hunterGhost = new HunterGhost(new GameMap(new char[5][5], 22), 0, 0,
                new PacMan(new GameMap(new char[5][5], 22), 0, 0), images);
    }

    @Test
    public void getPositionX() {
        // Assert initial position X is 2
        assertEquals(0, hunterGhost.getPositionX());
    }

    @Test
    public void getPositionY() {
        // Assert initial position Y is 3
        assertEquals(0, hunterGhost.getPositionY());
    }
}