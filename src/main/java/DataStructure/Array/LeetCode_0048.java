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
            for(int j = 0; j < matrix[i].length / 2; j++){         //j不能超过中间线， 否则会重复置换
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
                matrix[i][matrix[i].length - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode_0048 test = new LeetCode_0048();
        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        test.rotate(matrix1);
        System.out.println(Arrays.deepToString(matrix1));  //expected：[[7, 4, 1], [8, 5, 2], [9, 6, 3]]

        int[][] matrix2 = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        test.rotate(matrix2);
        System.out.println(Arrays.deepToString(matrix2));  //expected：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
    }
}
