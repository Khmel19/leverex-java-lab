package by.khmel.players;

import by.khmel.utils.Board;
import by.khmel.utils.Seed;

public abstract class Player {
    private final Board board;
    private final Seed seed;

    public Player(Board board, Seed seed) {
        this.board = board;
        this.seed = seed;
    }


    public void playerMove() {

            int[] rowAndCol = move();
            int row = rowAndCol[0];
            int col = rowAndCol[1];
            board.placeSeed(row, col, seed);

    }


    public Seed getSeed() {
        return seed;
    }


    public Board getBoard() {
        return board;
    }


    protected abstract int[] move();
}
