package game;

public class GameMap {
    private char[][] map;
    private int squareSize;

    public GameMap(char[][] map, int squareSize) {
        this.map = map;
        this.squareSize = squareSize;
    }

    public char[][] getGameMap() {
        return map;
    }

    public int getSquareSize() {
        return squareSize;
    }


}
