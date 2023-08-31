package Solucion;
public class NQueens {
    private static final int N = 8;
    private static int[][] board = new int[N][N];
    public static void main(String[] args) {

        solveNQueens(0);
    }

    private static void solveNQueens(int row) {
        if (row == N) {
            printSolution();
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                solveNQueens(row + 1);
                board[row][col] = 0;
            }
        }
    }

    private static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
            if (col - (row - i) >= 0 && board[i][col - (row - i)] == 1) {
                return false;
            }
            if (col + (row - i) < N && board[i][col + (row - i)] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
