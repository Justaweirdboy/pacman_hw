package game;

import javax.swing.*;
import java.awt.*;

public class scorePanel extends JPanel {
    private int score;
    private String playerName;
    private long timeElapsed; // Idő milliszekundumban

    public scorePanel() {
        this.score = 0;
        this.playerName = "Player 1";
        this.timeElapsed = 0;
        setPreferredSize(new Dimension(634, 50));
        setBackground(Color.DARK_GRAY);
    }

    public void setScore(int score) {
        this.score = score;
        repaint();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        repaint();
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        // Bal oldali pontszám
        g.drawString("Pontszám: " + score, 10, 20);

        // Középen a játékos név
        FontMetrics fm = g.getFontMetrics();
        int playerNameWidth = fm.stringWidth(playerName);
        int playerNameX = (getWidth() - playerNameWidth) / 2;
        g.drawString(playerName, playerNameX, 20);

        // Jobb oldalon az eltelt idő
        String timeText = "Idő: " + formatTime(timeElapsed);
        int timeTextWidth = fm.stringWidth(timeText);
        int timeTextX = getWidth() - timeTextWidth - 10;
        g.drawString(timeText, timeTextX, 20);
    }

    // Metódus az idő formázására (pl. másodpercek és percekre)
    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
