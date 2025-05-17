import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = ' ';
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void playGame() {
        boolean gameEnded = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + currentPlayer + "'s turn.");

            int row = -1, col = -1;
            while (true) {
                System.out.print("Enter row (0, 1, 2): ");
                row = scanner.nextInt();
                System.out.print("Enter column (0, 1, 2): ");
                col = scanner.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("Invalid move, try again.");
                }
            }

            if (hasWon(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (isDraw()) {
                printBoard();
                System.out.println("The game is a draw.");
                gameEnded = true;
            } else {
                switchPlayer();
            }
        }

        scanner.close();
    }

    private static void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
                if (j < SIZE - 1) System.out.print(" |");
            }
            System.out.println();
            if (i < SIZE - 1) System.out.println("---+---+---");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean hasWon(char player) {
        for (int i = 0; i < SIZE; i++) {
            if (allEqual(player, board[i][0], board[i][1], board[i][2])) return true;
            if (allEqual(player, board[0][i], board[1][i], board[2][i])) return true;
        }
        return allEqual(player, board[0][0], board[1][1], board[2][2]) ||
               allEqual(player, board[0][2], board[1][1], board[2][0]);
    }

    private static boolean isDraw() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == EMPTY)
                    return false;
        return true;
    }

    private static boolean allEqual(char player, char c1, char c2, char c3) {
        return c1 == player && c2 == player && c3 == player;
    }
}
