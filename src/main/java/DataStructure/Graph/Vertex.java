package DataStructure.Graph;

import java.util.List;

/**
 * 顶点
 */
public class Vertex {
    String name;  //顶点的名字
    List<Edge> edges;  //从这个顶点出发的所有边的列表
    boolean visited;  //是否被访问过， 用于BFS和DFS
    int inDegree = 0;  //入度，用于拓扑排序（Kahn's algorithm）
    int status = 0; //状态，0-未访问，1-访问中 2-访问过 用在拓扑排序（DFS）


    public Vertex(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
