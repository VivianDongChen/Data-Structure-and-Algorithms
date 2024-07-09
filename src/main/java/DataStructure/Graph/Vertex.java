package DataStructure.Graph;

import java.util.List;
import java.util.Objects;

/**
 * 顶点
 */
public class Vertex {
    public String name;  //顶点的名字
    public List<Edge> edges;  //从这个顶点出发的所有边的列表
    public boolean visited;  //是否被访问过， 用于BFS和DFS
    public int inDegree = 0;  //入度，用于拓扑排序（Kahn's algorithm）
    public int status = 0; //状态，0-未访问，1-访问中 2-访问过 用在拓扑排序（DFS）
    public int dist = INF; //距离，用于迪科斯特拉算法，初始值设为无穷大
    static final Integer INF = Integer.MAX_VALUE;
    public Vertex prev = null; //前一个顶点，用于迪科斯特拉算法中记录前一个顶点



    public Vertex(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String n = name;
        if (visited) {
            n = "\u001B[31m" + name + "\u001B[0m";
        }
        return n + '(' + (dist == Integer.MAX_VALUE ? "∞" : String.valueOf(dist)) + ") <- " + (prev == null ? "null" : prev.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
