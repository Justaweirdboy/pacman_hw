package game;

import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Timer;


public class Game extends JPanel implements KeyListener {


    //size of the cells
    private final int squareSize;
    private final Map<Character, Image> mapImages = new HashMap<>();
    private final ArrayList<Image> PacManImages = new ArrayList<>();
    private final ArrayList<Image> HunterGhostImages = new ArrayList<>();

    private final ArrayList<Image> ShyGhostImages = new ArrayList<>();

    private final ArrayList<Image> WandereingGhostImages = new ArrayList<>();

    private final ArrayList<Image> HunterGhostsBrotherImages = new ArrayList<>();

    private final GameMap map;

    private PacMan pacMan;

    private Ghost hunterGhost;
    private Ghost shyGhost;
    private Ghost wanderingGhost;

    private Ghost hunterGhostsBrother;

    private final scorePanel scorepanel;

    private JButton backButton;

    private final JPanel mainpanel;

    private String PlayerName;

    private Timer timer;

    public Game(GameMap map, scorePanel scorePanel, JPanel panel) {
        this.scorepanel = scorePanel;
        this.map = map;

        this.mainpanel = panel;
        squareSize = map.getSquareSize();


        //for blocks with no content
        mapImages.put(' ', loadImage("res_files/Walls/empty.png"));

        //borders inside of the map
        mapImages.put('1', loadImage("res_files/Walls/left-top.png"));
        mapImages.put('2', loadImage("res_files/Walls/horizontal.png"));
        mapImages.put('3', loadImage("res_files/Walls/right-top.png"));
        mapImages.put('4', loadImage("res_files/Walls/vertical.png"));
        mapImages.put('5', loadImage("res_files/Walls/left-bottom.png"));
        mapImages.put('6', loadImage("res_files/Walls/right-bottom.png"));

        //walls outside of the map
        mapImages.put('!', loadImage("res_files/Walls/double-left-top.png"));
        mapImages.put('@', loadImage("res_files/Walls/double-horizontal.png"));
        mapImages.put('#', loadImage("res_files/Walls/double-right-top.png"));
        mapImages.put('$', loadImage("res_files/Walls/double-vertical.png"));
        mapImages.put('%', loadImage("res_files/Walls/double-left-bottom.png"));
        mapImages.put('^', loadImage("res_files/Walls/double-right-bottom.png"));

        mapImages.put('0', loadImage("res_files/power_pellet.png"));
        mapImages.put('*', loadImage("res_files/food.png"));

        /********************************Pacman stuff****************************/
        PacManImages.add(loadImage("res_files/Pacman/neutral.png"));

        PacManImages.add(loadImage("res_files/Pacman/right_1.png"));
        PacManImages.add(loadImage("res_files/Pacman/right_2.png"));

        PacManImages.add(loadImage("res_files/Pacman/left_1.png"));
        PacManImages.add(loadImage("res_files/Pacman/left_2.png"));

        PacManImages.add(loadImage("res_files/Pacman/up_1.png"));
        PacManImages.add(loadImage("res_files/Pacman/up_2.png"));

        PacManImages.add(loadImage("res_files/Pacman/down_1.png"));
        PacManImages.add(loadImage("res_files/Pacman/down_2.png"));

        /******************************HunterGhost********************************/
        HunterGhostImages.add(loadImage("res_files/HunterGhost/neutral.png"));

        HunterGhostImages.add(loadImage("res_files/HunterGhost/right_1.png"));
        HunterGhostImages.add(loadImage("res_files/HunterGhost/right_2.png"));

        HunterGhostImages.add(loadImage("res_files/HunterGhost/left_1.png"));
        HunterGhostImages.add(loadImage("res_files/HunterGhost/left_2.png"));

        HunterGhostImages.add(loadImage("res_files/HunterGhost/up_1.png"));
        HunterGhostImages.add(loadImage("res_files/HunterGhost/up_2.png"));

        HunterGhostImages.add(loadImage("res_files/HunterGhost/down_1.png"));
        HunterGhostImages.add(loadImage("res_files/HunterGhost/down_2.png"));

        /******************************ShyGhost********************************/
        ShyGhostImages.add(loadImage("res_files/ShyGhost/neutral.png"));

        ShyGhostImages.add(loadImage("res_files/ShyGhost/right_1.png"));
        ShyGhostImages.add(loadImage("res_files/ShyGhost/right_2.png"));

        ShyGhostImages.add(loadImage("res_files/ShyGhost/left_1.png"));
        ShyGhostImages.add(loadImage("res_files/ShyGhost/left_2.png"));

        ShyGhostImages.add(loadImage("res_files/ShyGhost/up_1.png"));
        ShyGhostImages.add(loadImage("res_files/ShyGhost/up_2.png"));

        ShyGhostImages.add(loadImage("res_files/ShyGhost/down_1.png"));
        ShyGhostImages.add(loadImage("res_files/ShyGhost/down_2.png"));

        /******************************WandereingGhost********************************/
        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/neutral.png"));

        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/right_1.png"));
        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/right_2.png"));

        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/left_1.png"));
        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/left_2.png"));

        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/up_1.png"));
        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/up_2.png"));

        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/down_1.png"));
        WandereingGhostImages.add(loadImage("res_files/WandereingGhost/down_2.png"));

        /******************************HunterGhostsBrother********************************/
        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/neutral.png"));

        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/right_1.png"));
        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/right_2.png"));

        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/left_1.png"));
        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/left_2.png"));

        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/up_1.png"));
        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/up_2.png"));

        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/down_1.png"));
        HunterGhostsBrotherImages.add(loadImage("res_files/HunterGhostsBrother/down_2.png"));


        backButton = new JButton("Főmenü");
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
            cardLayout.show(mainpanel, "Menu");
        });

        // hide button and revel only if game has ended
        backButton.setVisible(false);

        Color originalBackground = backButton.getBackground();
        backButton.setBackground(new Color(255, 255, 255, 150));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {

                backButton.setBackground(null);
                //repaint();


            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                Color originalBackground = backButton.getBackground();
                backButton.setBackground(new Color(255, 255, 255, 150));
            }
        });
        // add button to the panel
        this.setLayout(new FlowLayout());
        this.add(backButton);


        addKeyListener(this);
        setFocusable(true);
    }

    public void Init() {
        backButton.setVisible(false);
        scorepanel.Init();
        pacMan = new PacMan(map, 13, 17);
        pacMan.setImages(PacManImages.get(0), PacManImages.get(0));
        hunterGhost = new HunterGhost(map, 11, 13, pacMan, HunterGhostImages);
        shyGhost = new ShyGhost(map, 15, 13, pacMan, ShyGhostImages);
        wanderingGhost = new WandereingGhost(map, 15, 15, pacMan, WandereingGhostImages);
        hunterGhostsBrother = new HunterGhostsBrother(map, 11, 15, pacMan, HunterGhostsBrotherImages);

    }

    public void StartGame() {
        if (timer != null) {
            timer.cancel(); // Ha van már időzítő, leállítjuk
        }
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new Move(), 0, 250);
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
                if (mapImages.containsKey(c)) {
                    Image img = mapImages.get(c);
                    g.drawImage(img, x, y, this);
                }
                x += squareSize;
            }
            y += squareSize;
        }

        pacMan.draw(g, this);
        hunterGhost.draw(g, this);
        shyGhost.draw(g, this);
        wanderingGhost.draw(g, this);
        hunterGhostsBrother.draw(g, this);
        if (pacMan.isDead()) {
            // Game Over felirat
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            String gameOverText = "Game Over";
            int textWidth = g.getFontMetrics().stringWidth(gameOverText);
            g.drawString(gameOverText, (getWidth() - textWidth) / 2, getHeight() / 2);
            backButton.setLocation((getWidth() - backButton.getWidth()) / 2, getHeight() / 2 + 50);
            backButton.setVisible(true);


        }

    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            pacMan.setDirectionX(1);
            pacMan.setDirectionY(0);
            pacMan.setImages(PacManImages.get(1), PacManImages.get(2));
        } else if (key == KeyEvent.VK_LEFT) {
            pacMan.setDirectionX(-1);
            pacMan.setDirectionY(0);
            pacMan.setImages(PacManImages.get(3), PacManImages.get(4));
        } else if (key == KeyEvent.VK_UP) {
            pacMan.setDirectionX(0);
            pacMan.setDirectionY(-1);
            pacMan.setImages(PacManImages.get(5), PacManImages.get(6));
        } else if (key == KeyEvent.VK_DOWN) {
            pacMan.setDirectionX(0);
            pacMan.setDirectionY(1);
            pacMan.setImages(PacManImages.get(7), PacManImages.get(8));
        }
    }

    public void keyTyped(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }

    public void keyReleased(KeyEvent e) {
        //not in use, but it is needed because of the interface
    }

    private class Move extends TimerTask {

        private final long startTime;

        public Move() {
            startTime = System.currentTimeMillis();
        }

        public void run() {

            if (pacMan.isDead()) {
                return;
            }
            long currentTime = System.currentTimeMillis(); //actual time
            long elapsedTime = currentTime - startTime; // to get elapsed time
            scorepanel.setTimeElapsed(elapsedTime);
            scorepanel.setScore(pacMan.getScore());
            if (pacMan.isKillerMode()) {
                setGhostsFrightened();
                pacMan.setKillerMode(false);
            }
            if (hunterGhost.NeedsReset() || shyGhost.NeedsReset() || wanderingGhost.NeedsReset() || hunterGhostsBrother.NeedsReset()) {
                hunterGhost.Reset();
                shyGhost.Reset();
                wanderingGhost.Reset();
                hunterGhostsBrother.Reset();
                pacMan.Reset();
                return;

            }

            pacMan.move();
            hunterGhost.move();
            shyGhost.move();
            wanderingGhost.move();
            hunterGhostsBrother.move();
            repaint();

        }
    }

    private void setGhostsFrightened() {
        hunterGhost.setGhostMode(GhostMode.Scattered);
        shyGhost.setGhostMode(GhostMode.Scattered);
        wanderingGhost.setGhostMode(GhostMode.Scattered);
        hunterGhostsBrother.setGhostMode(GhostMode.Scattered);
    }


}
