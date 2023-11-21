package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class PacMan implements KeyListener {

    private int pacmanX;
    private int pacmanY;
    private int squareSize;
    private int directionX = squareSize;
    private int directionY = squareSize;
    private char[][] map;


    PacMan(Game game){
        game=new Game();
        squareSize=game.getSquareSize();
        map=game.getMap();
        addKeyListener(this);
    }

    public void movePacman() {
        int newX = pacmanX + directionX * squareSize;
        int newY = pacmanY + directionY * squareSize;

        if (canMove(newX, newY)) {
            if (map[newY / squareSize][newX / squareSize] == '*') {
                map[newY / squareSize][newX / squareSize] = ' ';
            }
            pacmanX = newX;
            pacmanY = newY;
        }
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            directionX = 1;
            directionY = 0;
        } else if (key == KeyEvent.VK_LEFT) {
            directionX = -1;
            directionY = 0;
        } else if (key == KeyEvent.VK_UP) {
            directionX = 0;
            directionY = -1;
        } else if (key == KeyEvent.VK_DOWN) {
            directionX = 0;
            directionY = 1;
        }
    }
    public boolean canMove(int x, int y) {
        int tileX = x / squareSize;
        int tileY = y / squareSize;

        return tileY >= 0 && tileY < map.length && tileX >= 0 && tileX < map[tileY].length && (map[tileY][tileX] == '*' || map[tileY][tileX] == ' ');
    }

    public int getPacmanX(){
        return pacmanX;
    }

    public int getPacmanY() {
        return pacmanY;
    }
    public void keyTyped(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }

    public void keyReleased(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }
}
