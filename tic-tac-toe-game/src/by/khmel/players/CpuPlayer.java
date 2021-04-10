package by.khmel.players;

import by.khmel.utils.Board;
import by.khmel.utils.MiniMaxAlgorithm;
import by.khmel.utils.Seed;

public class CpuPlayer extends Player {

    public CpuPlayer(Board board, Seed seed) {
        super(board, seed);
    }

    @Override
    protected int[] move() {
        System.out.println("\nAI plays for '" + getSeed().getMark() + "', AI made a move: ");

        return MiniMaxAlgorithm.getBestMove(Board.getBoard(), getSeed());
    }
}
