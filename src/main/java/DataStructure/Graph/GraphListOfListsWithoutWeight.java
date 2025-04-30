package DataStructure.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphListOfListsWithoutWeight {
    private int numVertices;
    private final List<List<Integer>> graph;

    public GraphListOfListsWithoutWeight(int numVertices) {
        this.numVertices = numVertices;
        graph = new ArrayList<>();
        for(int i = 0; i < numVertices; i++){
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int v, int w){
        graph.get(v).add(w);
        graph.get(w).add(v);  //undirected graph
    }


}
