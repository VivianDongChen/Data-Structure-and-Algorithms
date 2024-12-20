package Algorithms.BFS_Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Time Complexity：
 * BFS的时间复杂度主要由节点扩展的总次数决定：节点数：迷宫有 rows * columns 个位置，每个位置最多会被访问一次。每次访问节点时，将其周围的 4 个方向尝试加入队列。
 * 时间复杂度：每个节点最多被访问一次，且每次访问需要检查四个方向，所以：O(rows×columns×4)=O(rows×columns)

 * Space Complexity：
 * - 队列空间：在最坏情况下，队列中可能存储迷宫中所有的节点（例如所有位置都可以访问且需要探索）。队列最多需要存储 rows * columns 个节点，所以：O(rows×columns)
 * - 迷宫标记：我们直接在 maze 数组上标记访问状态，不需要额外的空间。
 * - 方向数组：固定大小的二维数组，空间为O(1)。
 * 综合起来，空间复杂度为：O(rows×columns)
 */
public class LeetCode_1926 {
    public int nearestExit(char[][] maze, int[] entrance){
        int row = maze.length;
        int col = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        maze[entrance[0]][entrance[1]] = '+';

        int[][] directions = new int[][] {{0,1}, {0, -1}, {1,0}, {-1, 0}};

        int steps = 0;

        while(!queue.isEmpty()){
             steps++;
             int n = queue.size();
             for(int i = 0; i < n; i++){
                 int[] cur = queue.poll();

                 for(int[] direction : directions){
                     int x = cur[0] + direction[0];
                     int y = cur[1] + direction[1];

                     if(x < 0 || x >= row || y < 0 || y >= col || maze[x][y] == '+'){
                         continue;
                     }
                     if(x == 0 || x == row - 1 || y == 0 || y == col - 1){
                         return steps;
                     }
                     queue.offer(new int[]{x, y});
                     maze[x][y] = '+';
                 }
             }
        }
        return -1;

    }

    public static void main(String[] args) {

        char[][] maze1 = new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        int[] entrance1 = new int[]{0,1};

        char [][] maze2 = new char[][]{{'+','+','+'},{'.','.','.'},{'+','+','+'}};
        int[] entrance2 = new int[]{1,0};

        LeetCode_1926 test = new LeetCode_1926();
        System.out.println(test.nearestExit(maze1, entrance1));  // expected: 1
        System.out.println(test.nearestExit(maze2, entrance2));  // expected: 2
    }



}
