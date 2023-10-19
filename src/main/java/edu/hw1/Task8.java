package edu.hw1;

public class Task8 {
    static final int SIZE_BOARD = 8;

    static final int[][] MOVES = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    private Task8() {

    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board.length != SIZE_BOARD) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i].length != SIZE_BOARD) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : MOVES) {
                        int newI = i + move[0];
                        int newJ = j + move[1];

                        if (newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[0].length
                            && board[newI][newJ] == 1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
