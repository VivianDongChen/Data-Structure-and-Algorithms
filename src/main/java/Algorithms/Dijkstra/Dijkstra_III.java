package Algorithms.Dijkstra;

import DataStructure.Graph.Edge;
import DataStructure.Graph.Vertex;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 迪科斯特拉 - 改进版2
 * - 使用优先级队列来确定当前顶点（临时距离最小的那个就是）
 */
public class Dijkstra_III {

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
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist)); //定义一个优先级队列， 比较器中比较的是顶点的临时距离
        source.dist = 0;
        for(Vertex v : graph){   //将顶点全部放入优先级队列
            queue.offer(v);
        }

        while(!queue.isEmpty()){
            System.out.println(queue);
            Vertex curr = queue.peek(); //优先级队列的第一个即为dist最小值
            updateNeighboursDist(curr, queue);
            queue.poll(); //用poll()方法移除
        }

        for(Vertex v : graph){
            System.out.println(v.name + " " + v.dist + " " + (v.prev != null? v.prev.name : "null"));
        }

    }

    private static void updateNeighboursDist(Vertex curr, PriorityQueue<Vertex> queue) {
        for(Edge edge : curr.edges){
            Vertex n = edge.linked;
            if(!n.visited){
                int newDist = curr.dist + edge.weight;
                if(newDist < n.dist) {
                    queue.remove(n); //移除旧的顶点n;
                    n.dist = newDist;
                    n.prev = curr;
                    queue.offer(n); //将更新后的n点重新加入queue（自动重新排序）
                }
            }
        }
    }

}
