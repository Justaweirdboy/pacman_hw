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

public class Game extends JPanel implements ActionListener {

    private char[][] map;
    //size of the cells
    private final int squareSize = 22;
    private final Map<Character, Image> characterImages = new HashMap<>();

    private PacMan pacMan;




    private Timer timer;
    public Game(){
        pacMan=new PacMan(this);
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
        g.fillOval(pacMan.getPacmanX(), pacMan.getPacmanY(), squareSize, squareSize);


    }
    public void actionPerformed(ActionEvent e) {
        pacMan.movePacman();
        repaint();
    }









    public int getSquareSize(){
        return squareSize;
    }

    public char[][] getMap() {
        return map;
    }
}
