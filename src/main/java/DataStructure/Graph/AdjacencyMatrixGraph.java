package DataStructure.Graph;

public class AdjacencyMatrixGraph {
    private int numVertices;
    private boolean[][] adjMatrix;

    //Constructor
    public AdjacencyMatrixGraph(int numVertices){
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    //Add edges
    public void addEdge(int i, int j){
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;  // for undirected graph
    }

}
