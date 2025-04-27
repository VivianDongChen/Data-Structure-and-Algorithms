package DataStructure.Graph;

import java.util.LinkedList;

public class AdjacencyListGraph {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;

    //Constructor
    public AdjacencyListGraph(int numVertices) {
        this.numVertices = numVertices;
        adjLists = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    //add edge
    public void addEdge(int src, int dest){
        adjLists[src].add(dest);
        adjLists[dest].add(src);  //for undirected graph
    }
}
