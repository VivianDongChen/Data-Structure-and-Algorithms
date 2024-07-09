package Algorithms.Dijkstra;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * 迪科斯特拉 - 改进版1
 * - 加入prev，记录最短路径从哪里来
 * - 换一种方式来判断顶点已经被移除了, 这样list参数就不用进行传递了
 */
public class Dijkstra2 {

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
        ArrayList<Vertex> list = new ArrayList<>(graph);
        source.dist = 0;

        while(!list.isEmpty()){
            Vertex curr = chooseMinDistVertex(list);
            updateNeighboursDist(curr);
            list.remove(curr);
            curr.visited = true;
        }

        for(Vertex v : graph){
            System.out.println(v.name + " " + v.dist + " " + (v.prev != null? v.prev.name : "null")); //如果顶点的prev不为null， 打印prev（起点的prev是null）
        }

    }

    private static void updateNeighboursDist(Vertex curr) {
        for(Edge edge : curr.edges){
            Vertex n = edge.linked;
//            if(list.contains(n)){
            if(!n.visited){  //使用顶点本身的属性进行判断
                int newDist = curr.dist + edge.weight;
                if(newDist < n.dist) {
                    n.dist = newDist;
                    n.prev = curr; //发现有更小的临时距离，将节点的prev更新为当前节点
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
