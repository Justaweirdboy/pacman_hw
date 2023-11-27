package main;

import game.Game;
import game.GameMap;
import game.scorePanel;

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
        String fileName = "res_files/map.txt";

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            //using StringBuilder to read txt into it
            StringBuilder inputBuilder = new StringBuilder();


            while (scanner.hasNextLine()) {
                inputBuilder.append(scanner.nextLine()).append("\n");
            }
            scanner.close();

            String input = inputBuilder.toString();

            //create the matrix of the map
            String[] lines = input.split("\n");
            char[][] matrix = new char[lines.length][];

            for (int i = 0; i < lines.length; i++) {
                matrix[i] = lines[i].toCharArray();
            }


            GameMap map = new GameMap(matrix, 22);
            JFrame frame = new JFrame();

            JPanel mainPanel = new JPanel(new CardLayout());
            scorePanel scorePanel = new scorePanel();
            scorePanel.setPreferredSize(new Dimension(634, 50));
            scorePanel.setBackground(Color.DARK_GRAY);

            Game game = new Game(map, scorePanel, mainPanel);
            game.setPreferredSize(new Dimension(634, 722));
            game.setBackground(Color.BLACK);


            frame.setSize(634, 772);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);


            JPanel menuPanel = createMenuPanel(mainPanel, game);
            mainPanel.add(menuPanel, "Menu");


            JPanel gamePanel = createGamePanel(mainPanel, game, scorePanel);
            mainPanel.add(gamePanel, "Game");


            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "Menu"); //

            frame.add(mainPanel);
            frame.setVisible(true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static JPanel createMenuPanel(JPanel mainPanel, Game game) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100)); // Gombok középre igazítása és térköz beállítása

        JButton startGameButton = new JButton("Start Game");
        JButton leaderboardButton = new JButton("Leaderboard");

        // Stílus beállítása a gombokhoz
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        startGameButton.setFont(buttonFont);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setBackground(Color.BLUE);
        startGameButton.setPreferredSize(new Dimension(250, 80));
        startGameButton.setFocusPainted(false);

        leaderboardButton.setFont(buttonFont);
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setBackground(Color.GREEN);
        leaderboardButton.setPreferredSize(new Dimension(250, 80));
        leaderboardButton.setFocusPainted(false);

        // Gombok hozzáadása a menüpanelhez
        menuPanel.add(startGameButton);
        menuPanel.add(leaderboardButton);

        // Gomb eseménykezelők
        startGameButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "Game");

            game.StartGame();
            game.requestFocus();
        });

        leaderboardButton.addActionListener(e -> {
            // Ranglista megjelenítése
        });

        return menuPanel;
    }

    private static JPanel createGamePanel(JPanel mainPanel, Game game, scorePanel scorePanel) {
        JPanel gamePanel = new JPanel(new BorderLayout());


        gamePanel.add(game, BorderLayout.CENTER);
        gamePanel.add(scorePanel, BorderLayout.SOUTH);


        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
            cardLayout.show(mainPanel, "Menu");
        });

        //gamePanel.add(backButton);

        return gamePanel;
    }
}