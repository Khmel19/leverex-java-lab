package by.khmel.game;

import by.khmel.players.CpuPlayer;
import by.khmel.players.HumanPlayer;
import by.khmel.players.Player;
import by.khmel.utils.Board;
import by.khmel.utils.GameMode;
import by.khmel.utils.GameState;
import by.khmel.utils.Seed;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private final Board board;
    private GameMode gameMode;
    private GameState currentState;
    private Player currentPlayer;
    private Player[] players;
    private static final Scanner in = new Scanner(System.in);


    public Game() {
        this.board = Board.getBoard();
        initGame();

        do {
            currentPlayer.playerMove();
            this.board.paint();
            updateGame(currentPlayer.getSeed());
            if (currentState == GameState.CROSS_WON) {
                System.out.println("\n'X' won! Bye!");
            } else if (currentState == GameState.NOUGHT_WON) {
                System.out.println("\n'O' won! Bye!");
            } else if (currentState == GameState.DRAW) {
                System.out.println("\nIt's Draw! Bye!");
            }

            currentPlayer = (currentPlayer.getSeed() == Seed.CROSS)
                    ? Arrays.stream(players).filter(player -> player.getSeed() == Seed.NOUGHT).findFirst().get()
                    : Arrays.stream(players).filter(player -> player.getSeed() == Seed.CROSS).findFirst().get();
        } while (currentState == GameState.PLAYING);
    }


    public void initGame() {
        setGameMode();
        board.init();
        if (this.gameMode == GameMode.PVP) {
            players = new Player[]{new HumanPlayer(board, Seed.CROSS), new HumanPlayer(board, Seed.NOUGHT)};
            currentPlayer = players[0];
        } else {
            players = new Player[]{new HumanPlayer(board, Seed.NOUGHT), new CpuPlayer(board, Seed.CROSS)};
            selectMoveTurn();
        }
        currentState = GameState.PLAYING;
        board.paint();
    }


    private void selectMoveTurn() {
        boolean validInput = false;
        do {
            System.out.println("Who will move first?\nAI - 1\nMe - 2");
            String turn = in.nextLine();
            if (turn.matches("[1-2]")) {
                if (turn.equals("1")) {
                    currentPlayer = players[1];
                } else {
                    currentPlayer = players[0];
                }
                validInput = true;
            } else {
                System.err.println("This input is not valid");
            }
        } while (!validInput);
    }


    private void setGameMode() {
        System.out.println("Select game mode:\nPVP - 1\nPVE - 2\n");
        boolean validInput = true;
        while (validInput) {
            String gameMode = in.nextLine();
            if (gameMode.matches("[1-2]")) {
                if (gameMode.equals("1")) {
                    this.gameMode = GameMode.PVP;
                } else {
                    this.gameMode = GameMode.PVE;
                }
                break;
            } else {
                System.err.println("You can choose only 1 or 2");
                validInput = false;
            }
        }
    }


    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {
            currentState = GameState.DRAW;
        }
    }
}