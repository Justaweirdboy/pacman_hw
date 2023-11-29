package main;

import game.Game;
import game.GameMap;
import game.scorePanel;
import player.LeaderBoard;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // name of the file which contains the map
        String filename = "res_files/map.txt";


        GameMap map = new GameMap(filename, 22);
        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new CardLayout());
        scorePanel scorePanel = new scorePanel();
        scorePanel.setPreferredSize(new Dimension(634, 50));
        scorePanel.setBackground(Color.DARK_GRAY);

        LeaderBoard leaderBoard = new LeaderBoard(mainPanel);
        leaderBoard.readFromFile("res_files/leaderboard.dat");

        Game game = new Game(map, scorePanel, mainPanel, leaderBoard);
        game.setPreferredSize(new Dimension(634, 722));
        game.setBackground(Color.BLACK);


        frame.setSize(634, 772);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        JPanel menuPanel = createMenuPanel(mainPanel, game, scorePanel, leaderBoard);
        mainPanel.add(menuPanel, "Menu");


        JPanel gamePanel = createGamePanel(mainPanel, game, scorePanel);
        mainPanel.add(gamePanel, "Game");

        mainPanel.add(leaderBoard, "LeaderBoard");


        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "Menu"); //

        frame.add(mainPanel);
        frame.setResizable(false);
        frame.setVisible(true);


    }


    private static JPanel createMenuPanel(JPanel mainPanel, Game game, scorePanel scorepanel, LeaderBoard leaderBoard) {
        JPanel menuPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("res_files/bgGIF.gif");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        menuPanel.setBackground(Color.BLACK);
        JPanel inputPanel = new JPanel(new BorderLayout()); // Panel a játékosnév mezőhöz

        JTextField playerNameField = new JTextField(15);
        JButton startGameButton = new JButton("Új Játék");
        JButton leaderboardButton = new JButton("Ranglista");

        // Gombok stílusának beállítása
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        startGameButton.setFont(buttonFont);
        startGameButton.setPreferredSize(new Dimension(200, 60));
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBackground(Color.BLUE);
        startGameButton.setFocusPainted(false);

        leaderboardButton.setFont(buttonFont);
        leaderboardButton.setPreferredSize(new Dimension(200, 60));
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setBackground(Color.GREEN);
        leaderboardButton.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Távolság a frame szélétől és a komponensek

        startGameButton.setOpaque(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setBorderPainted(true);

        //hovering the mouse ove the button effect
        startGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startGameButton.setContentAreaFilled(true);
                startGameButton.setBackground(Color.BLUE); // Ide írd be a színkódodat
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startGameButton.setContentAreaFilled(false);
            }
        });

        startGameButton.addActionListener(e -> {
            String playerName = playerNameField.getText();
            if (!playerName.isEmpty()) {
                scorepanel.setPlayerName(playerName);
                CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
                game.Init();
                scorepanel.setPacMan(game.getPacMan());
                cardLayout.show(mainPanel, "Game");
                game.StartGame();
                game.requestFocus();
            } else {
                JOptionPane.showMessageDialog(menuPanel, "Add meg a neved, vagy Pacman megharap hamm hamm");
            }
        });

        leaderboardButton.addActionListener(e -> {
            leaderBoard.refreshTable();
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "LeaderBoard");
        });

        leaderboardButton.setOpaque(false);
        leaderboardButton.setContentAreaFilled(false);
        leaderboardButton.setBorderPainted(true);

        leaderboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leaderboardButton.setContentAreaFilled(true);
                leaderboardButton.setBackground(Color.GREEN); // Ide írd be a színkódodat
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                leaderboardButton.setContentAreaFilled(false);
            }
        });

        JLabel nameLabel = new JLabel("Add meg a neved:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(nameLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(2, 50, 20, 50); // Kis távolság a névmező előtt
        menuPanel.add(playerNameField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 50, 30, 50); // Kis távolság a gombok között
        menuPanel.add(startGameButton, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 50, 50, 50); // Nagy távolság a leaderboard gombtól az aljáig
        menuPanel.add(leaderboardButton, gbc);

        inputPanel.add(menuPanel, BorderLayout.CENTER);


        CardLayout cardLayout = new CardLayout();
        inputPanel.setLayout(cardLayout);
        cardLayout.show(inputPanel, "Menu");

        startGameButton.addActionListener(e -> cardLayout.show(inputPanel, "Input"));


        return inputPanel;
    }

    private static JPanel createGamePanel(JPanel mainPanel, Game game, scorePanel scorePanel) {
        JPanel gamePanel = new JPanel(new BorderLayout());


        gamePanel.add(game, BorderLayout.CENTER);
        gamePanel.add(scorePanel, BorderLayout.SOUTH);


        return gamePanel;
    }
}