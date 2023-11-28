package game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PacManTest {

    private PacMan pacMan;

    @Before
    public void setUp() throws Exception {
        // Initialize PacMan object before each test
        //false data in GameMap
        GameMap map = new GameMap(new char[5][5], 0);
        pacMan = new PacMan(map, 0, 0); // Initialize PacMan at position (0, 0)
    }

    @Test
    public void setDirectionX() {
        pacMan.setDirectionX(1); // Set direction X to 1
        assertEquals(1, pacMan.getDirectionX()); // Check if direction X is set properly
    }

    @Test
    public void setDirectionY() {
        pacMan.setDirectionY(-1); // Set direction Y to -1
        assertEquals(-1, pacMan.getDirectionY()); // Check if direction Y is set properly
    }

    @Test
    public void isOver_HPZero() {
        pacMan.hpLost();
        pacMan.hpLost();
        pacMan.hpLost(); // Simulate PacMan losing all HP
        assertTrue(pacMan.isOver()); // Check if the game is over due to zero HP
    }

}