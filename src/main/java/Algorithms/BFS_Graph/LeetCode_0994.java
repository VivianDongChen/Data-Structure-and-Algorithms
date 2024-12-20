package Algorithms.BFS_Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 时间复杂度 ：O(m × n)
 * 遍历整个网格需要 O(m × n)。BFS 遍历最多会访问每个橙子一次，复杂度仍为 O(m × n)。
 * 因此，总时间复杂度为： O(m×n)
 *
 * 空间复杂度 O(m × n)
 * - 队列：最坏情况下（所有橙子都是腐烂的），队列大小最多为 O(m × n)。
 * - 常量空间：用于存储方向数组 dirs 和循环变量。
 * - 因此，总空间复杂度为：O(m×n)
 */
public class LeetCode_0994 {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int count = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    count++;
                }
                if(grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if(count == 0){
            return 0;
        }
        if(queue.isEmpty()){
            return -1;
        }

        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = -1;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for(int[] dir : dirs) {
                    int x = poll[0] + dir[0];
                    int y = poll[1] + dir[1];

                    if( x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 2;
                        count--;
                    }
                }
            }
            minutes++;
        }
        if(count == 0){
            return minutes;
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        LeetCode_0994 test = new LeetCode_0994();
        int[][] grid1 = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid2 = new int[][]{{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid3 = new int[][]{{0,2}};
        int[][] grid4 = new int[][]{{0}};
        System.out.println(test.orangesRotting(grid1));
        System.out.println(test.orangesRotting(grid2));
        System.out.println(test.orangesRotting(grid3));
        System.out.println(test.orangesRotting(grid4));

    }
}
