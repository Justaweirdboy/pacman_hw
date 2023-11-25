package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {


    //size of the cells
    private final int squareSize;
    private final Map<Character, Image> characterImages = new HashMap<>();
    private GameMap map;

    private PacMan pacMan;

    private Ghost ghost;

    private Timer timer;


    public Game(GameMap map) {
        this.map = map;
        squareSize = map.getSquareSize();
        pacMan = new PacMan(map, 1, 1);
        ghost = new Ghost(map, pacMan, 22);


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

        addKeyListener(this);

        timer = new Timer(400, this);
        timer.start();
        setFocusable(true);
    }

    private Image loadImage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        return icon.getImage().getScaledInstance(squareSize, squareSize, Image.SCALE_DEFAULT);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = 0;

        for (char[] row : map.getGameMap()) {
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

        pacMan.draw(g);
        ghost.draw(g);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        pacMan.actionPerformed(e);
        ghost.actionPerformed(e);
        repaint();
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            pacMan.setDirectionX(1);
            pacMan.setDirectionY(0);
        } else if (key == KeyEvent.VK_LEFT) {
            pacMan.setDirectionX(-1);
            pacMan.setDirectionY(0);
        } else if (key == KeyEvent.VK_UP) {
            pacMan.setDirectionX(0);
            pacMan.setDirectionY(-1);
        } else if (key == KeyEvent.VK_DOWN) {
            pacMan.setDirectionX(0);
            pacMan.setDirectionY(1);
        }
    }

    public void keyTyped(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }

    public void keyReleased(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }
}
