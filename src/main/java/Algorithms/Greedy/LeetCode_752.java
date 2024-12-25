package Algorithms.Greedy;

import java.util.Arrays;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 */
public class LeetCode_752 {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        Arrays.sort(points,(a, b) -> Integer.compare(a[1], b[1]));
        int arrows = 1;
        int curr = points[0][1];
        for(int i = 1; i < n; i++) {
            if(points[i][0] > curr){
                arrows++;
                curr = points[i][1];
            }
        }
        return arrows;
    }

    public static void main(String[] args) {
        LeetCode_752 test = new LeetCode_752();
        System.out.println(test.findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}));
    }
}
