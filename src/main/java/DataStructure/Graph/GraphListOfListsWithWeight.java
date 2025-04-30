package DataStructure.Graph;

import java.util.List;
import java.util.ArrayList;

public class GraphListOfListsWithWeight {
    private int numVertices;
    private List<List<int[]>> graph;

    public GraphListOfListsWithWeight(int numVertices){
        this.numVertices = numVertices;
        graph = new ArrayList<>();
        for(int i = 0; i < numVertices; i++){
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int v1, int v2, int weight){
        graph.get(v1).add(new int[]{v2,weight});
        graph.get(v2).add(new int[]{v1,weight}); //undirected graph

    }

}
