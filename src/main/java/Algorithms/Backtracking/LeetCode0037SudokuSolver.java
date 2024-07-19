package Algorithms.Backtracking;

import java.util.Arrays;

/**
 * 解数独
 * - 1～9 在每行只能出现一次
 * - 1～9 在每列只能出现一次
 * - 1～9 在每个九宫格只能出现一次
 */
public class LeetCode0037SudokuSolver {

    static void solveSudoku(char[][] table) {
        /*
            1. 不断遍历每个未填的空格
               逐一尝试 1~9 若行、列、九宫格内没有冲突，则填入
            2. 一旦 1~9 都尝试失败，回溯到上一次状态，换数字填入
            3. 关键还是要记录冲突状态
         */
        //行冲突状态
        boolean[][] ca = new boolean[9][9];     //一共9行，每行对应的数据都有9中可能，所以用二维数组表示，
        // ca[i] = {true, true, false, false ,false, false, false, false, false}  它的列数代表1～9数字的索引：
        //   0  1  2  3  4  5  6  7  8
        //   1  2  3  4  5  6  7  8  9

        //列冲突状态
        boolean[][] cb = new boolean[9][9];
        // cb[j] = {false,true,true,false,true,true,true,true,false}

        //九宫格冲突
        boolean[][] cc = new boolean[9][9];    //一共9个九宫格，九宫格和单元格坐标的对应关系为：i/3*3+j/3
        // cc[i/3*3+j/3] = {true,false,true,true,true,true,false,true,true}

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = table[i][j];
                if(ch != '.'){   //初始化冲突状态
                    ca[i][ch - '1'] = true;   //第i行的数字ch被占用了
                    cb[j][ch - '1'] = true;   //第j列的数字ch被占用了
                    cc[i/3*3+j/3][ch - '1'] = true; //第i/3*3+j/3个九宫格的数字ch被占用了
                    // 数字ch对应的索引是 ch - ‘1’， 在 Java 中对数字字符进行相减操作时，得到的结果是对应数字相减的数值。
                }
            }
        }

        dfs(0,0, table, ca, cb, cc);
    }

    static boolean dfs(int i, int j, char[][] table, boolean[][] ca, boolean[][] cb, boolean[][] cc){
        while(table[i][j] != '.'){  //如果不是‘.'， 查找下一个空格, 直到找到‘.'
            if(++j >= 9){  //j++ 然后判断是否 >= 9 , 如果是，进入下一行
                j = 0;
                i++;
            }
            if(i >= 9){
                return true; //找到解；
            }
        }
        //对'.'进行填空
        for (int x = 1; x <= 9 ; x++) {
            //检查冲突
            if(ca[i][x-1] || cb[j][x-1] || cc[i/3*3+j/3][x-1]){
                continue;
            }
            //没有冲突，填入x
            table[i][j] = (char) (x + '0');  //数字 -> 字符  1 + ‘0’ = 49   在 Java 中，当你将整数与字符相加时，实际上会将字符转换为它的 ASCII 编码值来进行运算。因此，1 + '0' 的结果不是字符 '1'，而是一个整数。
            ca[i][x-1] = cb[j][x-1] = cc[i/3*3+j/3][x-1] = true;   //将来第i行不能再存储x了， 将来第j列不能再存储x了， 将来第i/3*3+j/3个九宫格不能再存储x了
            if(dfs(i,j,table,ca,cb,cc)){       //再递归中会检查单元格是否被占用，找到下一个空的单元格，所以这里不需要改变i和j;
                return true; //内部递归没有返回false，这里返回true
            };
            table[i][j] = '.';
            ca[i][x-1] = cb[j][x-1] = cc[i/3*3+j/3][x-1] = false;
        }

        return false;  //9个数字都没有找到解，返回false

    }




    public static void main(String[] args) {
        char[][] table = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        solveSudoku(table);

        print(table);
    }

    static char[][] solved = {
            {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
            {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
            {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
            {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
            {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
            {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
            {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
            {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
            {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
    };

    static void print(char[][] table) {
        for (char[] chars : table) {
            System.out.println(new String(chars));
        }
        System.out.println(Arrays.deepEquals(table, solved));
    }



}
