package BitManipulation;

import java.util.Arrays;

/**
 * 将矩阵中的1或0转换为两位bits, 左边一位代表更新后的状态，右边一位代表原始状态：
 * 01（dead <- live) 对应的int： 1
 * 00（dead <- dead) 对应的int： 0
 * 10（live <- dead) 对应的int： 2
 * 11（live <- live) 对应的int： 3
 * 这样就可以使用bit运算来取之前的状态和更新后的状态了
 */
public class LeetCode_0289 {
    public void gameOfLife(int[][] board){
        int m = board.length;
        int n = board[0].length;
        //第一遍遍历，计算所有节点的neighbor的live数，根据这个数，判断出更新后的状态
        //根据这个状态 将节点转换为对应的两位bits对应的int
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = getNeighborLifes(board,i,j,m,n);
                //这里只需要判断更新后的状态是1的情况
                if(board[i][j] == 1 && (lives >= 2 && lives <= 3)){
                    board[i][j] = 3;
                }
                if(board[i][j] == 0 && lives == 3){
                    board[i][j] = 2;
                }
            }
        }
        //第二遍遍历，取出每个节点的第2位（更新后的状态）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1; // 提取第2位作为更新后状态
            }

        }
    }
    private int getNeighborLifes(int[][] board, int i, int j, int m, int n) {
        int lives = 0;
        for(int x = Math.max(i-1, 0); x < Math.min(i+2,m); x++){    //邻居们的边界
            for( int y = Math.max(j-1, 0); y < Math.min(j+2,n); y++){      //邻居们的边界
                lives += board[x][y] & 1;     // & 1 取对应的二进制数的当前状态
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }

    public static void main(String[] args) {
        LeetCode_0289 test = new LeetCode_0289();
        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        test.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}
