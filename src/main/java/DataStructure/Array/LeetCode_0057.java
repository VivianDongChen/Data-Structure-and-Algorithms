package DataStructure.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. Insert Interval
 */
public class LeetCode_0057 {
    public int[][] insert(int[][] intervals, int[] newInterval){
        if(intervals== null || intervals.length == 0){
            return new int[][]{newInterval};
        }
        List<int[]> res = new ArrayList<>();
        int i = 0;

        while(i < intervals.length && intervals[i][1] < newInterval[0]){
                res.add(intervals[i]);
                i++;
        }
        while(i < intervals.length && intervals[i][0] <= newInterval[1]){
            newInterval = new int[]{Math.min(intervals[i][0], newInterval[0]),
                Math.max(intervals[i][1], newInterval[1])};
            i++;
        }
        res.add(newInterval);
        while(i < intervals.length){
            res.add(intervals[i]);
            i++;
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        LeetCode_0057 test = new LeetCode_0057();
        int[][] intervals = new int[][]{{1,3},{6,9}};
        int[] newInterval = new int[]{2,5};
        System.out.println(Arrays.deepToString(test.insert(intervals, newInterval)));
    }
}
