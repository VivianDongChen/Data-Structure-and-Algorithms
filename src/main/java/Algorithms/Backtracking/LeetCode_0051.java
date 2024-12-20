package Algorithms.Backtracking;

import java.util.Arrays;

/**
 * N皇后 - 回溯
 * 对于一个n*n的棋盘：
 * - 左斜线的个数 = n*2 -1 ， 一条左斜线上的i+j相同
 * - 右斜线的个数 = n*2 -1 ， 一条右斜线上的i-j相同
 */
public class LeetCode_0051 {
    public static void main(String[] args) {

        int n = 4;

        boolean[] ca = new boolean[n]; //记录冲突（列）true false false false
        boolean[] cb = new boolean[2*n - 1]; //记录冲突（左斜线）i+j相等，即为在一条左斜线上
        boolean[] cc = new boolean[2*n - 1]; //记录冲突（右斜线）i-j相等，即为在一条右斜线上， 为了避免结果出现负数，可以转化为 n-1-（i-j）

        char[][] tables = new char[n][n];  //没有放皇后用“."表示，放了皇后用“Q”表示

        for(char[] t : tables){      //初始化数组
            Arrays.fill(t, '.');
        }
        for(char[] t : tables){      //打印数组
            System.out.println(new String(t));
        }
        dfs(0, n, tables, ca, cb, cc);
    }

    /**
     *
     * @param i  目前的行数
     * @param n  棋盘的纬度
     * @param tables 代表棋盘的二维数组
     * @param ca 记录列冲突的数组
     * @param cb 记录左斜线冲突的数组
     * @param cc 记录右斜线冲突的数组
     */
    static void dfs(int i, int n, char[][] tables, boolean[] ca, boolean[] cb, boolean[] cc){
        if(i == n){  //找到解(每一行都放入了一个皇后）
            for(char[] t : tables){
                System.out.println(new String(t));
            }
            return;
        }
        for (int j = 0; j < n; j++) {   //将皇后放入这一行的每一列
            if(ca[j] || cb[i+j] || cc[n-1-(i-j)]){  //如果这个单元格有冲突，跳过
                continue;
            }
            tables[i][j] = 'Q';
            ca[j] = cb[i+j] = cc[n-1-(i-j)] = true;  //记录冲突
            dfs(i+1, n, tables, ca, cb, cc);
            tables[i][j] = '.';
            ca[j] = cb[i+j] = cc[n-1-(i-j)] = false;
        }
    }
}
