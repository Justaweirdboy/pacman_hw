package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMap {
    private char[][] map;
    private int squareSize;

    //

    public GameMap(char[][] map, int squareSize) {
        this.map = map;
        this.squareSize = squareSize;
    }

    public GameMap(String filename, int squareSize) {
        try {
            this.map = loadFromFile(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.squareSize = squareSize;
    }

    public char[][] getGameMap() {
        return map;
    }

    public int getSquareSize() {
        return squareSize;
    }

    private char[][] loadFromFile(String filename) throws FileNotFoundException {
        try {
            File file = new File(filename);
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
            return matrix;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new char[squareSize][squareSize];
        }


    }
}
