package game;

import org.junit.Before;
import org.junit.Test;
import player.LeaderBoard;

import javax.swing.*;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private GameMap map;
    private scorePanel scorePanel;
    private JPanel panel;
    private LeaderBoard leaderBoard;

    @Before
    public void setUp() throws Exception {
        // Initialize necessary objects before each test case
        map = new GameMap(new char[5][5], 22);
        scorePanel = new scorePanel(/* pass required parameters */);
        panel = new JPanel();
        leaderBoard = new LeaderBoard(new JPanel());

        game = new Game(map, scorePanel, panel, leaderBoard);
        game.Init();
    }


    @Test
    public void getPacMan() {
        assertNotNull(game.getPacMan());
    }

    @Test
    public void testKeyPressed() {
        // Testing the right arrow key
        KeyEvent keyEventRight = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D');
        game.keyPressed(keyEventRight);
        assertEquals(1, game.getPacMan().getDirectionX());
        assertEquals(0, game.getPacMan().getDirectionY());

        // Testing the left arrow key
        KeyEvent keyEventLeft = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A');
        game.keyPressed(keyEventLeft);
        assertEquals(-1, game.getPacMan().getDirectionX());
        assertEquals(0, game.getPacMan().getDirectionY());

        // Testing the up arrow key
        KeyEvent keyEventUp = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W');
        game.keyPressed(keyEventUp);
        assertEquals(0, game.getPacMan().getDirectionX());
        assertEquals(-1, game.getPacMan().getDirectionY());

        // Testing the down arrow key
        KeyEvent keyEventDown = new KeyEvent(new JPanel(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S');
        game.keyPressed(keyEventDown);
        assertEquals(0, game.getPacMan().getDirectionX());
        assertEquals(1, game.getPacMan().getDirectionY());

        // Add more assertions based on the expected behavior when different arrow keys are pressed
    }
}