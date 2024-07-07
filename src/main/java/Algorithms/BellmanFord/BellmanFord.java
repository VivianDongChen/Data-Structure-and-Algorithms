package Algorithms.BellmanFord;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

import java.util.List;

/**
 * Bellman-Ford算法 -用于计算加权有向图中的单源最短路径问题
 * 可以处理负边
 * 可以检测图中是否有负环（如果图中出现负环，求最短路径是无解的）
 */
public class BellmanFord {

    public static void main(String[] args) {
        // 正常情况
        /*Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));
        List<Vertex> graph = List.of(v4, v5, v6, v1, v2, v3);  */

        // 负边情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);

        // 负环情况
        /*Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2));
        v2.edges = List.of(new Edge(v3, -4));
        v3.edges = List.of(new Edge(v4, 1), new Edge(v1, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);*/


        bellmanFord(graph, v1);
    }


    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        int size = graph.size();
        //1. 处理 顶点个数-1 轮（每次处理每个顶点的每一条边）
        for (int i = 0; i < size -1; i++) {
            //2. 遍历所有顶点
            for(Vertex s: graph){  //s: 起始顶点
                //3. 处理每一条边
                for(Edge edge: s.edges){
                    Vertex e = edge.linked;  //e: 结束顶点
                    if(s.dist != Integer.MAX_VALUE && s.dist + edge.weight < e.dist){
                        e.dist = s.dist + edge.weight;
                        e.prev = s;  //记录来处
                    }
                }
            }
        }

        //4. 如果处理（顶点个数-1）轮之后，还能找到顶点的更小的dist的值：出现了负环（检测负环的方法）
        for(Vertex s: graph) {
            for (Edge edge : s.edges) {
                Vertex e = edge.linked;
                if (s.dist + edge.weight < e.dist) {
                    throw new RuntimeException("出现了负环");
                }
            }
        }

        for(Vertex v : graph){
            System.out.println(v);
        }
    }

}