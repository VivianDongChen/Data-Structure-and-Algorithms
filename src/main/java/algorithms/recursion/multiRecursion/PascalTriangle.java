package algorithms.recursion.multiRecursion;

//杨辉三角的递归实现
public class PascalTriangle {


    /**
     * 用递归来计算出每个element的值
     * @param i 行(0开始）
     * @param j 列（0开始）
     * @return  i行j列的值
     */
    private static int element(int i,int j){

        if(j == 0 || i == j) {
            return 1;
        }
        return element(i-1,j-1) + element(i-1,j);
    }

    /**
     * 打印空格 - 将直角三角形转化为正三角形
     * @param n 杨辉三角的高度
     * @param i 杨辉三角的行数
     */
    private static void printSpace(int n,int i){
        int num = (n-1-i) * 2;   //这个公式是基于数值字符宽度为4
        for (int j = 0; j < num; j++) {      //打印num个空格
            System.out.print(" ");
        }

    }
    /**
     * 打印杨辉三角形 - 直接递归（未优化）
     * @param n 杨辉三角的高度
     */
    public static void print(int n){
        for (int i = 0; i < n; i++) {
            printSpace(n,i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",element(i,j));  //带符号打印：每个整数字符宽度为4，左对齐
            }
            System.out.println();
        }
    }


    /**
     * 优化1: 使用二维数组记忆法
     * @param triangle  - 二维数组
     * @param i - 行坐标
     * @param j - 列坐标
     * @return 坐标元素值
     */
    private static int element1(int[][] triangle,int i,int j){
        if(triangle[i][j] != 0){        //如果二维数组已经被赋值，即返回这个二维数组值
            return triangle[i][j];
        }

        if(j == 0 || i == j) {
            triangle[i][j] = 1;         //给二维数组赋值
            return 1;
        }

        triangle[i][j] = element1(triangle,i-1,j-1) + element1(triangle,i-1,j);    //将元素值赋给二维数组

        return triangle[i][j];  //返回元素值
    }



    public static void print1(int n){
        int[][] triangle = new int[n][]; //定义一个空的二维数组， 行数为n, 列数每一行都不同，所以暂不指定
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i+1]; //在此处指定列数，第i行有i+1列；
            printSpace(n,i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",element1(triangle,i,j));
            }
            System.out.println();
        }
    }

    /**
     * 优化1: 使用一维数组记忆法（动态规划）

         0   0   0   0   0   0  初始状态
         1   0   0   0   0   0  i = 0
         1   1   0   0   0   0  i = 1
         1   2   1   0   0   0  i = 2
         1   3   3   1   0   0  i = 3
         1   4   6   4   1   0  i = 4

     */
    private static void createRow(int[] row, int i){
        if(i == 0){
            row[0] = 1;
            return;
        }

        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j-1];
        }
    }



    public static void print2(int n){
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            createRow(row,i);
            printSpace(n,i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d",row[j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        print(5);
        print1(5);
        print2(5);
    }
}
