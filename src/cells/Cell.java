package cells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell extends JPanel implements ActionListener, KeyListener {

    private char[][] map;
    //size of the cells
    private final int squareSize = 22;
    private final Map<Character, Image> characterImages = new HashMap<>();
    private int pacmanX = 1 * squareSize;
    private int pacmanY = 1 * squareSize;

    private int directionX = 0;
    private int directionY = 0;

    private Timer timer;
    public Cell(){
        //for blocks with no content
        characterImages.put(' ', loadImage("res_files/Walls/empty.png"));

        //borders inside of the map
        characterImages.put('1', loadImage("res_files/Walls/left-top.png"));
        characterImages.put('2', loadImage("res_files/Walls/horizontal.png"));
        characterImages.put('3', loadImage("res_files/Walls/right-top.png"));
        characterImages.put('4', loadImage("res_files/Walls/vertical.png"));
        characterImages.put('5', loadImage("res_files/Walls/left-bottom.png"));
        characterImages.put('6', loadImage("res_files/Walls/right-bottom.png"));

        //walls outside of the map
        characterImages.put('!', loadImage("res_files/Walls/double-left-top.png"));
        characterImages.put('@', loadImage("res_files/Walls/double-horizontal.png"));
        characterImages.put('#', loadImage("res_files/Walls/double-right-top.png"));
        characterImages.put('$', loadImage("res_files/Walls/double-vertical.png"));
        characterImages.put('%', loadImage("res_files/Walls/double-left-bottom.png"));
        characterImages.put('^', loadImage("res_files/Walls/double-right-bottom.png"));

        characterImages.put('*', loadImage("res_files/food.png"));

        timer = new Timer(400, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }
    private Image loadImage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        return icon.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
    }
    public void setCharacters(char[][] matrix){
        map=matrix;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = 0;

        for (char[] row : map) {
            int x = 0;


            for (char c : row) {
                if (characterImages.containsKey(c)) {
                    Image img = characterImages.get(c);
                    g.drawImage(img, x, y, this);
                }
                x += squareSize;
            }
            y += squareSize;
        }
        g.setColor(Color.YELLOW);
        g.fillOval(pacmanX, pacmanY, squareSize, squareSize);


    }
    public void actionPerformed(ActionEvent e) {
        movePacman();
        repaint();
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

    public boolean canMove(int x, int y) {
        int tileX = x / squareSize;
        int tileY = y / squareSize;

        return tileY >= 0 && tileY < map.length && tileX >= 0 && tileX < map[tileY].length && (map[tileY][tileX] == '*' || map[tileY][tileX] == ' ');
    }


    public void keyTyped(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }

    public void keyReleased(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }
}
