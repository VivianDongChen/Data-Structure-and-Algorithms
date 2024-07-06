package Algorithms.TopologicalSort;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 拓扑排序 - Kahn's algorithm 实现
 * 前提条件：图中不能有环，有环的话就有顶点的入度不能减到0，一旦出现这种情况，排序就会中断
 */
public class TopologicalSortKahn1 {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("网页基础");
        Vertex v2 = new Vertex("Java基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring框架");
        Vertex v5 = new Vertex("微服务框架");
        Vertex v6 = new Vertex("数据库");
        Vertex v7 = new Vertex("实战项目");

        v1.edges = List.of(new Edge(v3));
        v2.edges = List.of(new Edge(v3));
        v3.edges = List.of(new Edge(v4));
        v4.edges = List.of(new Edge(v5));
        v5.edges = List.of(new Edge(v7));
        v6.edges = List.of(new Edge(v4));
        v7.edges = List.of();

        List<Vertex> graph = new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6, v7));
        //1. 统计每个顶点的入度
        for (Vertex v : graph) {
            for (Edge edge : v.edges) {
                edge.linked.inDegree++;
            }
        }

        for (Vertex v : graph) {
            System.out.println(v.name + " " + v.inDegree);
        }
        System.out.println("-------------------");

        //2. 将入度为0的顶点加入队列
        LinkedList<Vertex> queue = new LinkedList<>();
        for(Vertex v : graph){
            if(v.inDegree == 0){
                queue.offer(v);
            }
        }
        //3. 队列中不断移除顶点，每移除一个顶点，把它相邻顶点入度减1，若减到0则入队
        while(! queue.isEmpty()){
            Vertex poll = queue.poll();
            System.out.println(poll.name);
            for(Edge edge : poll.edges){
                edge.linked.inDegree--;
                if(edge.linked.inDegree == 0){
                    queue.offer(edge.linked);
                }
            }
        }
    }
}