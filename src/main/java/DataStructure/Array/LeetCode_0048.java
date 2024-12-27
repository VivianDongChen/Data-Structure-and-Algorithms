package DataStructure.Array;

import java.util.Arrays;

public class LeetCode_0048 {
    public void rotate(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix[i].length; j++){    //j要从i开始，否则会重复置换
                int temp =  matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j <= matrix[i].length / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
                matrix[i][matrix[i].length - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode_0048 test = new LeetCode_0048();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        test.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));  //expected：[[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    }
}
