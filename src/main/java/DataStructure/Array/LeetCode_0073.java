package DataStructure.Array;

import java.util.Arrays;

/**
 * 73. Set Matrix Zeroes
 * 时间复杂度：O(mn)
 * 空间复杂度：O(1)
 */
public class LeetCode_0073 {
    public void setZeroes(int[][] matrix) {
        boolean fr = false;
        boolean fc = false;
        //第一遍遍历，遍历全部节点。O(mn)
        //如果节点为0，其对应的第一排节点和第一列节点设为0；如果值为0的节点在第一排或第一列，用布尔值标记上，后面需要单独处理。
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    if(i == 0) fr = true;
                    if(j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //第二遍遍历，遍历除第一排和第一列的所有节点。O(mn)
        //如果节点对应的第一排节点或者第一列节点为0， 节点设置为0
         for(int i = 1; i < matrix.length; i++){
             for (int j = 1; j < matrix[0].length; j++){
                 if(matrix[i][0] == 0 || matrix[0][j] == 0){
                     matrix[i][j] = 0;
                 }
             }
         }

         //第三遍遍历，如果fr为true，遍历第一排所有节点，将节点设置为0。O(m)
         if(fr){
             for(int j = 1; j < matrix[0].length; j++){
                 matrix[0][j] = 0;
             }
         }

        //第四遍遍历，如果fc为true，遍历第一列所有节点，将节点设置为0。O(n)
         if(fc){
             for(int i = 1; i < matrix.length; i++){
                 matrix[i][0] = 0;
             }
         }
    }

    public static void main(String[] args) {
        LeetCode_0073 test = new LeetCode_0073();
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        test.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
