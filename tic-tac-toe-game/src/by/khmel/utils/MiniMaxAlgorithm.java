package by.khmel.utils;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxAlgorithm {

    private static final int MAX_DEPTH = 9;

    private MiniMaxAlgorithm() {
    }


    public static int miniMax(Board board, int depth, boolean isMax) {
        int boardVal = evaluateBoard(board, depth);

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) > 0 || depth == 0
                || !board.anyMovesAvailable()) {
            return boardVal;
        }

        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < Board.getROWS(); row++) {
                for (int col = 0; col < Board.getCOLS(); col++) {
                    if (board.isCellEmpty(row, col)) {
                        board.getCells()[row][col].setContent(Seed.CROSS);
                        highestVal = Math.max(highestVal, miniMax(board,
                                depth - 1, false));
                        board.getCells()[row][col].setContent(Seed.EMPTY);
                    }
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < Board.getROWS(); row++) {
                for (int col = 0; col < Board.getCOLS(); col++) {
                    if (board.isCellEmpty(row, col)) {
                        board.getCells()[row][col].setContent(Seed.NOUGHT);
                        lowestVal = Math.min(lowestVal, miniMax(board,
                                depth - 1, true));
                        board.getCells()[row][col].setContent(Seed.EMPTY);
                    }
                }
            }
            return lowestVal;
        }
    }

    //Evaluate every legal move on the board and return the best one.

    public static int[] getBestMove(Board board) {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < Board.getROWS(); row++) {
            for (int col = 0; col < Board.getCOLS(); col++) {
                if (board.isCellEmpty(row, col)) {
                    board.getCells()[row][col].setContent(Seed.CROSS);
                    int moveValue = miniMax(board, MAX_DEPTH, false);
                    board.getCells()[row][col].setContent(Seed.EMPTY);
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    /*
     * Evaluate the given board from the perspective of the X player, return
     * 10 if a winning board configuration is found, -10 for a losing one and 0
     * for a draw, weight the value of a win/loss/draw according to how many
     * moves it would take to realise it using the depth of the game tree the
     * board configuration is at.
     */

    private static int evaluateBoard(Board board, int depth) {
        int rowSum = 0;
        int bWidth = Board.getROWS();
        int Xwin = Seed.CROSS.getMark() * bWidth;
        int Owin = Seed.NOUGHT.getMark() * bWidth;

        // Check rows for winner.
        for (int row = 0; row < bWidth; row++) {
            for (int col = 0; col < bWidth; col++) {
                rowSum += board.getCells()[row][col].getContent().getMark();
            }
            if (rowSum == Xwin) {
                return 10 + depth;
            } else if (rowSum == Owin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        // Check columns for winner.
        rowSum = 0;
        for (int col = 0; col < bWidth; col++) {
            for (int row = 0; row < bWidth; row++) {
                rowSum += board.getCells()[row][col].getContent().getMark();
            }
            if (rowSum == Xwin) {
                return 10 + depth;
            } else if (rowSum == Owin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        rowSum = 0;
        for (int i = 0; i < bWidth; i++) {
            rowSum += board.getCells()[i][i].getContent().getMark();
        }
        if (rowSum == Xwin) {
            return 10 + depth;
        } else if (rowSum == Owin) {
            return -10 - depth;
        }

        // Top-right to bottom-left diagonal.
        rowSum = 0;
        int indexMax = bWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += board.getCells()[i][indexMax-i].getContent().getMark();
        }
        if (rowSum == Xwin) {
            return 10 + depth;
        } else if (rowSum == Owin) {
            return -10 - depth;
        }

        return 0;
    }
}