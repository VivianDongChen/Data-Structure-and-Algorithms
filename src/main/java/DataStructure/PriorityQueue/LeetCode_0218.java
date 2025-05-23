package DataStructure.PriorityQueue;

import java.util.*;

//The Skyline Problem
public class LeetCode_0218 {
    public List<List<Integer>> getSkyline(int[][] buildings){
        List<int[]> edges = new ArrayList<>();  // create a container to contain to all the edges

        for(int[] b : buildings){ //traverse through buildings to get every edges
            edges.add(new int[]{b[0], -b[2]}); //left edge
            edges.add(new int[]{b[1],b[2]});  //right edge
        }

        //sort edges
        Collections.sort(edges, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        //process critical edges using priority queue
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(0);
        int preMaxHeight = 0;

        for(int[] edge : edges){
            int x = edge[0], h = edge[1];

            if(h < 0){
                maxHeap.add(-h);
            }else{
                maxHeap.remove(h);
            }

            int currentMaxHeight = maxHeap.peek();
            if(currentMaxHeight != preMaxHeight){
                res.add(Arrays.asList(x, currentMaxHeight));
                preMaxHeight = currentMaxHeight;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode_0218 solver = new LeetCode_0218();

        // 测试用例 1（LeetCode 官方样例）
        int[][] buildings1 = {
            {2, 9, 10},
            {3, 7, 15},
            {5, 12, 12},
            {15, 20, 10},
            {19, 24, 8}
        };

        List<List<Integer>> result1 = solver.getSkyline(buildings1);
        System.out.println("Test Case 1 Output:");
        printSkyline(result1);

        // 测试用例 2：单个建筑
        int[][] buildings2 = {
            {1, 5, 11}
        };

        List<List<Integer>> result2 = solver.getSkyline(buildings2);
        System.out.println("\nTest Case 2 Output:");
        printSkyline(result2);

        // 测试用例 3：重叠建筑
        int[][] buildings3 = {
            {1, 3, 3},
            {2, 4, 4},
            {5, 6, 1}
        };

        List<List<Integer>> result3 = solver.getSkyline(buildings3);
        System.out.println("\nTest Case 3 Output:");
        printSkyline(result3);
    }

    // 打印结果的辅助方法
    private static void printSkyline(List<List<Integer>> skyline) {
        for (List<Integer> point : skyline) {
            System.out.println(point);
        }
    }

}
