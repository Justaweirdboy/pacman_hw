package game;

import javax.swing.*;
import java.awt.*;

public class scorePanel extends JPanel {
    private int score;
    private String playerName;
    private long timeElapsed; // Idő milliszekundumban

    private PacMan pacMan;

    public scorePanel() {

        setPreferredSize(new Dimension(634, 50));
        setBackground(Color.DARK_GRAY);

    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void Init() {
        this.score = 0;

        this.timeElapsed = 0;
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

    public int getScore() {
        return score;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        // score on the left side
        g.drawString("Pontszám: " + score, 10, 20);

        // playername in the middle
        FontMetrics fm = g.getFontMetrics();
        int playerNameWidth = fm.stringWidth(playerName);
        int playerNameX = (getWidth() - playerNameWidth) / 2;
        g.drawString(playerName, playerNameX, 20);

        // elapsed time on the right side
        String timeText = "Idő: " + formatTime(timeElapsed);
        int timeTextWidth = fm.stringWidth(timeText);
        int timeTextX = getWidth() - timeTextWidth - 10;
        g.drawString(timeText, timeTextX, 20);

        g.setColor(Color.RED);
        int hpWidth = fm.stringWidth("Élet:x");
        int hpX = (getWidth() - hpWidth) / 2;
        g.drawString("Élet:" + pacMan.getHP(), hpX, 40);
    }

    // Metódus az idő formázására (pl. másodpercek és percekre)
    private String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
