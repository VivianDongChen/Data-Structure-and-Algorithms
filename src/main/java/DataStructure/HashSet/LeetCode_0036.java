package DataStructure.HashSet;

import java.util.HashSet;
import java.util.Set;

/**
 * 36. Valid Sudoku
 */
public class LeetCode_0036 {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set= new HashSet<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.'){
                    if(!set.add(board[i][j] + "in row" + i) || !set.add(board[j][i] + "in col" + j) ||
                    !set.add(board[i][j] + " in block" + i/3 +"-" + j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_0036 test = new LeetCode_0036();
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(test.isValidSudoku(board));
    }
}
