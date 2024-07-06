package Algorithms.Dijkstra;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪科斯特拉 - 单源最短路径算法
 * 从一个顶点出发，到其它每一个顶点的最短路径
 */
public class Dijkstra {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(
                new Edge(v3, 9),
                new Edge(v2, 7),
                new Edge(v6, 14)
        );
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(
                new Edge(v4, 11),
                new Edge(v6, 2)
        );
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v1,v2,v3,v4,v5,v6);

        dijkstra(graph,v1);

    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        //1. 创建一个未访问顶点的集合（未访问顶点默认dist为无穷大）
        ArrayList<Vertex> list = new ArrayList<>(graph);
        //2. 起点临时距离值为0
        source.dist = 0;

        while(!list.isEmpty()){
            //3. 选取当前顶点
            Vertex curr = chooseMinDistVertex(list);
            //4. 更新当前顶点邻居距离
            updateNeighboursDist(curr, list);
            //5. 移除当前顶点
            list.remove(curr);
        }

        for(Vertex v : graph){
            System.out.println(v.name + " " + v.dist);
        }

    }

    private static void updateNeighboursDist(Vertex curr, ArrayList<Vertex> list) {
        for(Edge edge : curr.edges){
            Vertex n = edge.linked;
            if(list.contains(n)){
                int newDist = curr.dist + edge.weight;
                if(newDist < n.dist) {
                    n.dist = newDist;
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
