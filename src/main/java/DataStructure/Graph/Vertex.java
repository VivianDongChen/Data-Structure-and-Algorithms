package DataStructure.Graph;

import java.util.List;

/**
 * 顶点
 */
public class Vertex {
    String name;  //顶点的名字
    List<Edge> edges;  //从这个顶点出发的所有边的列表
    boolean visited;  //是否被访问过


    public Vertex(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
