package Algorithms.FloydWarshall;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

/**
 * Floyd - Warshall算法 - 多源最短路径算法
 * 可以检测负环
 */
public class FloydWarshall {

    public static void main(String[] args) throws IOException {
        // 正常情况
        /*Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, -2));
        v2.edges = List.of(new Edge(v1, 4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4, 2));
        v4.edges = List.of(new Edge(v2, -1));
        List<Vertex> graph = List.of(v1, v2, v3, v4); */

        // 负环情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -4));
        v3.edges = List.of(new Edge(v4, 1), new Edge(v1, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);

        floydWarshall(graph);
    }
    /*
               直接连通
                   v1  v2  v3  v4
               v1  0   ∞   -2  ∞
               v2  4   0   3   ∞
               v3  ∞   ∞   0   2
               v4  ∞   -1  ∞   0

               k=0 借助v1到达其它顶点
                   v1  v2  v3  v4
               v1  0   ∞   -2  ∞
               v2  4   0   2   ∞       v2-v1-v3 = 4 + (-2) = 2
               v3  ∞   ∞   0   2
               v4  ∞   -1  ∞   0

               k=1 借助v2到达其它顶点
                   v1  v2  v3  v4
               v1  0   ∞   -2  ∞
               v2  4   0   2   ∞
               v3  ∞   ∞   0   2
               v4  3   -1  1   0    v4-v2-v1 = -1 + 4 = 3      v4-v2-v3 = -1 + 2 = 1

               k=2 借助v3到达其它顶点
                   v1  v2  v3  v4
               v1  0   ∞   -2  0    v1-v3-v4 = 0
               v2  4   0   2   4    v2-v3-v4 = 2 + 2 = 4
               v3  ∞   ∞   0   2
               v4  3   -1  1   0

               k=3 借助v4到达其它顶点
                   v1  v2  v3  v4
               v1  0   -1  -2  0   v1-v4-v2 = 0 - 1 = -1    v1-v4-v3 = 0 + 1 = 1 > -2（无需更新）
               v2  4   0   2   4   v2-v4-v1 = 4 + 3 = 7 > 4 (无需更新）v2-v4-v3 = 4 + 1 = 5 > 2 (无需更新）
               v3  5   1   0   2   v3-v4-v1 = 2 + 3 = 5    v3-v4-v2 = 2 - 1 = 1
               v4  3   -1  1   0
        */
    static void floydWarshall(List<Vertex> graph){
        int size = graph.size();
        int[][] dist = new int[size][size];
        Vertex[][] prev = new Vertex[size][size]; //用来记录最短路径的来处
        //1) 二维表格初始化
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);  //纬度i（行）对应的顶点
            //将顶点的边由list转换为map（key：边的终点，value：边的权重）, 以便于快速判断边是否在集合中（在集合中既可以联通，否则无法联通）。
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);   //纬度j（列）对应的顶点
                if( v == u){    //同一个顶点
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = map.getOrDefault(u, Integer.MAX_VALUE);   //u点在map中，说明联通，两点距离即为权重；否则不联通，距离默认值为正无穷
                    prev[i][j] = map.get(u) != null ? v : null; //联通，上一个顶点即为v，否则为null
                }
            }
        }
        print(dist);
        print(prev);

        //2）看能否借路到达其它顶点
        /*
          v2 - v1        v1 - v?
          dist[1][0] + dist[0][0]
          dist[1][0] + dist[0][1]
          dist[1][0] + dist[0][2]
          dist[1][0] + dist[0][3]
         */
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
//                    dist[i][k] + dist[k][j] //i行的顶点，借助k顶点，到达j列的顶点
//                    dist[i][j]
                    if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[k][j]; //借助[k][j]过来，它的prev即为[k][j]的prev
                    }
                    if(i == j && dist[i][j] < 0){
                        throw new RuntimeException("出现了负环");
                    }
                }

            }

        }

        print(dist);
        print(prev);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path(prev,graph, i, j);
            }
        }
    }

    /**
     * 打印顶点二维数组
     * @param dist 顶点二维数组
     */
    static void print(int[][] dist) {
        System.out.println("-------------");
        // 遍历二维数组的每一行
        for (int[] row : dist) {
            // 将行转换为流，并处理每个元素
            System.out.println(Arrays.stream(row).boxed() // 将基本类型int转换为Integer对象
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))  //如果元素等于Integer.MAX_VALUE，则转换为字符串"∞"，否则转换为字符串形式的元素值
                    .map(s -> String.format("%2s", s)) // 将每个字符串格式化为至少两位宽
                    .collect(Collectors.joining(",", "[", "]")));  //将格式化后的字符串连接成一个字符串，并在前后加上方括号，中间以逗号分隔
        }
    }

    /**
     * 打印最短路径来处二维数组（前驱矩阵）
     * @param prev 最短路径来处二维数组
     */
    static void print(Vertex[][] prev) {
        System.out.println("-------------------------");
        for (Vertex[] row : prev) {
            System.out.println(Arrays.stream(row).map(v -> v == null ? "null" : v.name)  //遇到null，打印null，否则打印顶点的名字
                    .map(s -> String.format("%5s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    /**
     * 打印最短路径
     * @param prev 前驱矩阵
     * @param graph 顶点列表
     * @param i 起点在graph的索引
     * @param j 终点在graph的索引
     */
    static void path(Vertex[][] prev, List<Vertex> graph, int i, int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.print("[" + graph.get(i).name + "," + graph.get(j).name + "] ");
        stack.push(graph.get(j).name);
        while (i != j) {
            Vertex p = prev[i][j]; //在前驱矩阵找到从i到j的前一个顶点p， i->...p j
            stack.push(p.name);
            j = graph.indexOf(p);  //j更新为p在graph的索引。
        }
        System.out.println(stack);
    }

    /*
       如果存在负环的情况：
            v1  v2  v3  v4
        v1  0   2   ∞   ∞
        v2  ∞   0   -4  ∞
        v3  1   ∞   0   1
        v4  ∞   ∞   ∞   0

            k=0
            v1  v2  v3  v4
        v1  0   2   ∞   ∞
        v2  ∞   0   -4  ∞
        v3  1   3   0   1
        v4  ∞   ∞   ∞   0

            k=1
            v1  v2  v3  v4
        v1  0   2   -2  ∞
        v2  ∞   0   -4  ∞
        v3  1   3   -1  1       v3 - v3 = -1  对象线上的值出现了负数
        v4  ∞   ∞   ∞   0


     */
}