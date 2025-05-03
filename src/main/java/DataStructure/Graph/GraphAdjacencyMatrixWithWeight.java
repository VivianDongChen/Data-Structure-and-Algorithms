package DataStructure.Graph;

public class GraphAdjacencyMatrixWithWeight {
    private final int numVertices;
    private final int[][] graph;

    public GraphAdjacencyMatrixWithWeight(int numVertices) {
        this.numVertices = numVertices;
        graph = new int[numVertices][numVertices];
    }

    public void addEdges(int v1, int v2, int w){
        graph[v1][v2] = w;
        graph[v2][v1] = w;
    }
}
