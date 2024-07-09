package Algorithms.Kruskal;

import DataStructure.Graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 求最小生成树
 * 解法2 - Kruskal（克鲁斯卡尔）算法（以边为核心的算法）
 */
public class Kruskal {

    static class Edge implements Comparable<Edge> {
        List<Vertex> vertices;
        int start;  //边的起点在List中的索引值
        int end;   //边的终点在List中的索引值
        int weight;   //边的权重

        public Edge(List<Vertex> vertices, int start, int end, int weight) {
            this.vertices = vertices;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }   //按照边的权重的大小来排序

        @Override
        public String toString() {
            return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
        }
    }
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        List<Vertex> vertices = List.of(v1, v2, v3, v4, v5, v6, v7);

        PriorityQueue<Edge> queue = new PriorityQueue<>(List.of(          //定义一个小顶堆，将边放入，权重最小的边在最上面
                new Edge(vertices, 0, 1, 2),  //v1 - v2 权重为2的边
                new Edge(vertices, 0, 2, 4),
                new Edge(vertices, 0, 3, 1),
                new Edge(vertices, 1, 3, 3),
                new Edge(vertices, 1, 4, 10),
                new Edge(vertices, 2, 3, 2),
                new Edge(vertices, 2, 5, 5),
                new Edge(vertices, 3, 4, 7),
                new Edge(vertices, 3, 5, 8),
                new Edge(vertices, 3, 6, 4),
                new Edge(vertices, 4, 6, 6),
                new Edge(vertices, 5, 6, 1)
        ));
        kruskal(vertices.size(), queue);
    }

    /**
     * Kruskal（克鲁斯卡尔）算法
     * @param size 图的顶点的个数
     * @param queue 图的边的集合
     */
    static void kruskal(int size, PriorityQueue<Edge> queue) {
        List<Edge> list = new ArrayList<>();   //用开存储结果的集合
        DisjointSet1 set = new DisjointSet1(size);   //不相交集合
        while(list.size() < size - 1){      //所求边的个数是顶点数 - 1
            Edge poll = queue.poll();      //获取权重最小的边
            int i = set.find(poll.start);
            int j = set.find(poll.end);
            if(i != j) { //两个顶点未相交（未联通）
                list.add(poll);
                set.union(i, j);   //设置两个顶点为联通
            }
        }

        for(Edge edge: list){
            System.out.println(edge);
        }
    }
}
