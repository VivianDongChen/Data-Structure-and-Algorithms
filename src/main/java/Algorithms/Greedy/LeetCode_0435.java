package Algorithms.Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode_0435 {

    public int eraseOverlapIntervals(int[][] intervals){
//        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
        int[] prev =intervals[0];
//        list.add(prev);
        int result = intervals.length-1;
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if(curr[0] >= prev[1]){
//                list.add(curr);
                result--;
                prev = curr;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode_0435 solution = new LeetCode_0435();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals));  // Output: 1
    }
}
