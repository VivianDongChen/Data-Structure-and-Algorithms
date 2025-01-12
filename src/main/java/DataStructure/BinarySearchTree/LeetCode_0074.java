package DataStructure.BinarySearchTree;

/**
 * 74. Search a 2D Matrix
 * Time Complexity: O(log(m × n))
 * Space Complexity: O(1)
 */
public class LeetCode_0074 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int column = matrix[0].length;

        int end = row * column - 1;
        int start = 0;

        while (start <= end){
            int mid = start + (end - start)/2;
            int m = mid / column;
            int n = mid % column;
            if(target == matrix[m][n]){
                return true;
            }else if(target > matrix[m][n]){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode_0074 test = new LeetCode_0074();

        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };

        int target = 3;

        System.out.println(test.searchMatrix(matrix, target));

    }
}
