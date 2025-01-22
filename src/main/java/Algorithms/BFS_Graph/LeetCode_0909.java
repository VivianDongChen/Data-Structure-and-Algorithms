package Algorithms.BFS_Graph;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 909. Snakes and Ladders
 * 本题目的难点在于：
 * - 理解题目：编号、坐标
 * - 怎样由编号求board的坐标
 * - 将骰子掷出的每一种值视为一种可能，将每一种可能视为这个Graph的下一个节点，并通过BFS进行遍历
 * 时间复杂度：O(n平方）   BFS 遍历棋盘上的所有格子，每个格子只访问一次。每次访问一个格子时，我们最多尝试掷骰子的 6 种可能性。
 * 空间复杂度：O(n平方）   队列和访问数组是主要空间开销
 */
public class LeetCode_0909 {
    public int snakeAndLadders(int[][] board) {
        int n = board.length;
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();   //用于BFS遍历，存放的是编号
        boolean[][] visited = new boolean[n][n];    //为了避免重复访问已经处理过的格子，遍历不会往后走，保证moves的最小
        q.add(1);
        visited[n-1][0] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {   //每扔一次骰子就将队列中的所有可能性拿出来，并增加新的可能性
                int curr = q.poll();
                if (curr == n * n) {  //如果curr已经到达了终点，退出并返回moves
                    return moves;
                }
                for (int j = 1; j <= 6; j++) {    //骰子的6种可能
                    if (curr + j > n * n) { //如果跳出了终点，退出循环
                        break;
                    }
                    int[] pos = findCoordinates(curr + j, n);  //找到每一种可能的编号，并由编号计算出行坐标和列坐标
                    int r = pos[0];
                    int c = pos[1];

                    if(!visited[r][c]){    //该可能没有被遍历过
                        visited[r][c] = true;
                        if(board[r][c] == -1){    //如果是-1，没有梯子，停留该点， 将该点加入队列
                            q.add(curr + j);
                        }else{         //有梯子，找到梯子对应的点，将该点加入队列
                            q.add(board[r][c]);
                        }
                    }
                }
            }
            moves++;   //每扔一次骰子增加一次
        }
        return -1;   //循环结束没有达到终点，返回-1；
    }

    private int[] findCoordinates(int curr, int n){
        int r = n - (curr - 1)/n - 1;    //curr - 1 改为index 0 的坐标, /n代表index为1的行数，因为是自下而上，n - (curr - 1)/n - 1;
        int c = (curr - 1) % n;  //curr - 1 改为index 0 的坐标，%n 代表从每行编号起点开始的index
        //每一行编号的起点都不一样
        if(r % 2 == n % 2){   //如果行坐标和总行数的奇偶相同，那么是从右向左
            return new int[]{r, n-1-c};   //转换为从左向右的列坐标
        }else{
            return new int[]{r, c};   //如果行坐标和总行数的奇偶不同，那么是从左向右
        }
    }

    public static void main(String[] args) {
        LeetCode_0909 solver = new LeetCode_0909();

        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };

        int result = solver.snakeAndLadders(board);
        System.out.println("Minimum number of moves to reach the last square: " + result);

    }





}
