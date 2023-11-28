package game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class scorePanelTest {

    private scorePanel scorePanel;

    @Before
    public void setUp() throws Exception {
        scorePanel = new scorePanel();
        
    }

    @Test
    public void setScore() {
        scorePanel.setScore(100);
        assertEquals(100, scorePanel.getScore());
    }

    @Test
    public void setPlayerName() {
        scorePanel.setPlayerName("Player1");
        assertEquals("Player1", scorePanel.getPlayerName());
    }

    @Test
    public void setTimeElapsed() {
        scorePanel.setTimeElapsed(5000);
        assertEquals(5000, scorePanel.getTimeElapsed());
    }

    @Test
    public void getScore() {
        scorePanel.setScore(150);
        assertEquals(150, scorePanel.getScore());
    }

    @Test
    public void getTimeElapsed() {
        scorePanel.setTimeElapsed(10000);
        assertEquals(10000, scorePanel.getTimeElapsed());
    }

    @Test
    public void getPlayerName() {
        scorePanel.setPlayerName("Player2");
        assertEquals("Player2", scorePanel.getPlayerName());
    }
}