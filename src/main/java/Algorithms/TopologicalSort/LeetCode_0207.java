package Algorithms.TopologicalSort;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 207. Course Schedule
 */
public class LeetCode_0207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new List[numCourses];
        int[] indegree = new int[numCourses];
        List<Integer> ans  = new ArrayList<>();

        for(int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            if(adj[pre] == null){
                adj[pre] = new ArrayList<>();
            }
            adj[pre].add(course);
            indegree[course]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            int current = queue.poll();
            ans.add(current);

            if(adj[current] != null){
                for(int next: adj[current]){
                    indegree[next]--;
                    if(indegree[next] == 0){
                        queue.add(next);
                    }
                }
            }
        }

        return ans.size() == numCourses;

    }

    public static void main(String[] args) {
        LeetCode_0207 test = new LeetCode_0207();
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        System.out.println(test.canFinish(numCourses, prerequisites));
    }
}
