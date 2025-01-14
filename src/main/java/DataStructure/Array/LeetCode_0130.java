package DataStructure.Array;

import java.util.Arrays;

/**
 * 130. Surrounded Regions
 * key: understand the meaning of surrounded
 */
public class LeetCode_0130 {
    public void solve(char[][] board) {
        if(board == null || board.length < 2 || board[0].length < 2){
            return;      //edge cases
        }
        int m = board.length;
        int n = board[0].length;

        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O'){
                BoundaryDFS(board, i,0);
            }
            if(board[i][n - 1] == 'O'){
                BoundaryDFS(board,i,n-1);
            }
        }
        for(int j = 0; j < n; j++){
            if(board[0][j] == 'O'){
                BoundaryDFS(board,0,j);
            }
            if(board[m-1][j] == 'O'){
                BoundaryDFS(board,m-1,j);
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if (board[i][j] == '*'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void BoundaryDFS(char[][] board, int i, int j){
        if(i< 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1|| board[i][j] != 'O'){
            return;
        }
        board[i][j] = '*';

        BoundaryDFS(board,i,j+1);
        BoundaryDFS(board,i,j-1);
        BoundaryDFS(board,i+1,j);
        BoundaryDFS(board,i-1,j);
    }

    public static void main(String[] args) {
        LeetCode_0130 test = new LeetCode_0130();
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        test.solve(board);
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
