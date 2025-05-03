package DataStructure.Graph;

import java.util.LinkedList;

public class GraphArrayOfLinkedListsWithWeight {
    private int numVertices;
    private final LinkedList<int[]>[] graph;

    public GraphArrayOfLinkedListsWithWeight(int numVertices) {
        this.numVertices = numVertices;
        graph = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    public void addEdge(int v1, int v2, int w) {
        graph[v1].add(new int[]{v2, w});
        graph[v2].add(new int[]{v1, w}); //undirected graph
    }

}
