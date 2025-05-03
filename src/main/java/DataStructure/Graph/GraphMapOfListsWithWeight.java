package DataStructure.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMapOfListsWithWeight {
    private final Map<Integer, List<int[]>> graph;

    public GraphMapOfListsWithWeight() {
        graph = new HashMap<>();
    }

    public void addEdges(int v1, int v2, int w){
        graph.putIfAbsent(v1, new ArrayList<>());
        graph.putIfAbsent(v2, new ArrayList<>());
        graph.get(v1).add(new int[]{v2, w});
        graph.get(v2).add(new int[]{v1, w});
    }

}
