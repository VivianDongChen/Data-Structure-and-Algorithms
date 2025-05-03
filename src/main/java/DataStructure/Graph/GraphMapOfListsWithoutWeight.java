package DataStructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMapOfListsWithoutWeight {
    private final Map<Integer, List<Integer>> graph;

    public GraphMapOfListsWithoutWeight(int numVertices) {
        graph = new HashMap<>();
    }

    public void addEdge(int v1, int v2){
        graph.putIfAbsent(v1, new ArrayList<>());
        graph.putIfAbsent(v2, new ArrayList<>());
        graph.get(v1).add(v2);
        graph.get(v2).add(v1); // undirected graph
    }
}
