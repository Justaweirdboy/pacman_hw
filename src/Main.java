
import game.Game;
import game.GameMap;

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
            Game panel = new Game(map);
            panel.setBackground(Color.BLACK);

            frame.add(panel);

            frame.setSize(634, 722);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}