package player;

import player.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

// A panel to display and manage the leaderboard
public class LeaderBoard extends JPanel {
    private List<Player> players; // List to hold player data
    private JTable playersTable; // Table to display player information
    private DefaultTableModel tableModel; // Table model to manage table data

    private final JPanel mainpanel; // Reference to the main panel

    // Constructor for LeaderBoard
    public LeaderBoard(JPanel mainpanel) {
        setLayout(new BorderLayout());
        players = new ArrayList<>(); // Initialize the list of players
        this.mainpanel = mainpanel; // Store reference to the main panel

        // Initialize table model and table
        tableModel = new DefaultTableModel(new Object[]{"Név", "Pont", "Játékban töltött idő"}, 0);
        playersTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(playersTable);
        add(scrollPane, BorderLayout.CENTER);

        // Sorting buttons
        JButton sortByNameButton = new JButton("Név szerinti rendezés");
        JButton sortByScoreButton = new JButton("Pont szerinti rendezés");
        JButton Menu = new JButton("Főmenü");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(sortByNameButton);
        buttonsPanel.add(sortByScoreButton);
        buttonsPanel.add(Menu);

        add(buttonsPanel, BorderLayout.SOUTH);

        // Action listeners for sorting buttons
        sortByNameButton.addActionListener(e -> {
            players.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            refreshTable();
        });

        sortByScoreButton.addActionListener(e -> {
            players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
            refreshTable();
        });

        // Action listener for returning to the main menu
        Menu.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
            cardLayout.show(mainpanel, "Menu");
        });
    }

    // Refresh the table with updated player data
    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Player player : players) {
            Object[] row = {player.getName(), player.getScore(), player.getFormatTime()};
            tableModel.addRow(row);
        }
    }

    // Read player data from a file
    public void readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            players = (List<Player>) ois.readObject();
            System.out.println("Leaderboard loaded successfully from file.");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Write player data to a file
    public void writeToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(players);
            System.out.println("Leaderboard written successfully to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter for the list of players
    public List getPlayers() {
        return players;
    }
}
