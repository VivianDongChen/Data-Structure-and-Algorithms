package Algorithms.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode_0210 {
    public int[] findOrder(int numCourses, int[][] prerequisites){
        // create int[] to contain the result, O(V)
        int[] res = new int[numCourses];

        //create graph, O(V*E)
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        //create inDegree Array, O(V)
        int[] inDegree = new int[numCourses];

        //initiate graph and inDegree Array with information from prerequisites, O(E)
        for(int[] pair: prerequisites){
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]]++;
        }

        //create a queue to keep all the vertexes with 0 inDegree, O(V)
        Queue<Integer> queue = new LinkedList<>();

        //offer the first course into queue, O(V)
        for(int j = 0; j < numCourses; j++){
            if(inDegree[j] == 0){
                queue.offer(j);
            }
        }

        //use a variable to keep the count of element in res
        int count = 0;

        //do a topological sort by BFS using queue
        while(!queue.isEmpty()){
            int course = queue.poll(); //each time extract a course with 0 inDegree
            res[count++] = course; // add the course to res, then increase count;
            for(int neighbor: graph.get(course)){   //decrease inDegree of its neighbors
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0){  // if inDegree is 0, offer to the queue
                    queue.offer(neighbor);
                }
            }
        }

        return count == numCourses ? res : new int[0];
    }

    public static void main(String[] args) {
        LeetCode_0210 leetcode = new LeetCode_0210();

        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0}, // 要上1，得先上0
                {2, 0},
                {3, 1},
                {3, 2}
        };

        int[] order = leetcode.findOrder(numCourses, prerequisites);
        System.out.print("CourseOrder:");
        for(int course: order){
            System.out.print(course + " ");
        }

    }


}
