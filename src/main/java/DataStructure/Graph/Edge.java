package DataStructure.Graph;

/**
 * 边
 */
public class Edge {
    Vertex linked; //这条边连接到的终点（目标顶点）
    int weight; //边的权重

    public Edge(Vertex linked) {
        this.linked = linked;
        this.weight = 1;
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}
