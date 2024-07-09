package Algorithms.Prim;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小生成树（Minimum Spanning Tree，MST）问题：给定一个带权无向图，目标是找到一棵生成树，使得这棵生成树包含图中的所有顶点，并且其边权之和最小。
 * 解法1 - Prim算法
 */
public class Prim {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 4), new Edge(v4, 1));
        v2.edges = List.of(new Edge(v1, 2), new Edge(v4, 3), new Edge(v5, 10));
        v3.edges = List.of(new Edge(v1, 4), new Edge(v4, 2), new Edge(v6, 5));
        v4.edges = List.of(new Edge(v1, 1), new Edge(v2, 3), new Edge(v3, 2),
                new Edge(v5, 7), new Edge(v6, 8), new Edge(v7, 4));
        v5.edges = List.of(new Edge(v2, 10), new Edge(v4, 7), new Edge(v7, 6));
        v6.edges = List.of(new Edge(v3, 5), new Edge(v4, 8), new Edge(v7, 1));
        v7.edges = List.of(new Edge(v4, 4), new Edge(v5, 6), new Edge(v6, 1));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6, v7);

        prim(graph, v1);

    }

    private static void prim(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);
        source.dist = 0;

        while(!list.isEmpty()){
            Vertex curr = chooseMinDistVertex(list);
            updateNeighboursDist(curr);
            list.remove(curr);
            curr.visited = true;
        }

        for (Vertex v : graph) {
            System.out.println(v);
        }

    }

    private static void updateNeighboursDist(Vertex curr) {
        for(Edge edge : curr.edges){
            Vertex n = edge.linked;
            if(!n.visited){
                int newDist = edge.weight;       //这里的newDist更新为权重（与Dijkstra算法的唯一区别）
                if(newDist < n.dist) {
                    n.dist = newDist;       //dist记录的最小的距离相邻边的权重
                    n.prev = curr;
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(ArrayList<Vertex> list) {

        Vertex min = list.get(0);
        for(Vertex v : list){
            if(v.dist < min.dist){
                min = v;
            }
        }
        return min;
    }
}
