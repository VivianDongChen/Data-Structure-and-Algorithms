package Algorithms.DFS;

import java.util.*;

/*
    LeetCode 399.Evaluate Division
    如果有Q个查询，总时间复杂度为：O(E+Q⋅(V+E))      V：图中的节点数，E：图中的边数（方程数）
    空间复杂度：
    -图存储了所有的节点和边，空间复杂度为O(V+E)。
    -visited 的空间复杂度为O(V)
    -递归调用栈的深度最坏为O(V)
    总空间复杂度：O(V+E)
 */
public class LeetCode399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> graph = graphBuild(equations, values);  //除数，被除数和值放入一个图结构
        double[] finalAns = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++){
            String divident = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);

            if(! graph.containsKey(divident) || ! graph.containsKey(divisor)){
                finalAns[i] = -1.0;
            }else{
                HashSet<String> visited = new HashSet<>();
                double[] ans = {-1.0};
                double temp = 1.0;
                dfs(divident, divisor, graph,visited, ans, temp);
                finalAns[i] = ans[0];
            }
        }
        return finalAns;
    }

    //使用嵌套的哈希表（邻接表）构建一个加权有向图
    //遍历 equations 和 values 数组，一共O(E)，其中E是方程的数量。
    private HashMap<String, HashMap<String, Double>> graphBuild (List<List<String>> equations, double[] values){
        HashMap<String, HashMap<String,Double>> graph = new HashMap<>();  //  a/b= v  =>  HashMap<a, HashMap<b,v>>

        for(int i = 0; i < equations.size(); i++){
            String divident = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(divident, new HashMap<>());
            graph.putIfAbsent(divisor, new HashMap<>());

            graph.get(divident).put(divisor, value);
            graph.get(divisor).put(divident, 1.0/value);
        }
        return graph;
    }
    //每次查询（DFS）：最坏情况下，需要遍历整个图，时间复杂度为O(V+E)，其中V是图中的节点数，E是边数。
    private void dfs(String start, String end, HashMap<String, HashMap<String, Double>> graph, HashSet<String> visited, double[] ans, double temp){
        if(visited.contains(start)){
            return;
        }
        visited.add(start);

        if(start.equals(end)){
            ans[0] = temp;
            return;
        }

        for( Map.Entry<String, Double> entry : graph.get(start).entrySet()){
            String node = entry.getKey();
            Double val = entry.getValue();

            dfs(node, end, graph, visited, ans, temp * val); //如果路径存在，累乘沿路径的权值；如果不存在，返回 -1.0
        }
    }

    public static void main(String[] args) {
        List<List<String>> equations1 = Arrays.asList(
            Arrays.asList("a","b"), Arrays.asList("b","c"));
        double[] values1 = {2.0,3.0};
        List<List<String>> queries1 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        List<List<String>> equations2 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );
        double[] values2 = {1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );

        LeetCode399 test = new LeetCode399();
        System.out.println(Arrays.toString(test.calcEquation(equations1, values1, queries1))); // expected: [6.0, 0.5, -1.0, 1.0, -1.0]
        System.out.println(Arrays.toString(test.calcEquation(equations2, values2, queries2))); //expected：[3.75, 0.4, 5.0, 0.2]

    }

}
