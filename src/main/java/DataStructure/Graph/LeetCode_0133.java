package DataStructure.Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 133. Clone Graph
 * 本题目的重点是如何拷贝每一个节点的neighbors，使用哈希表（visited）：
 * 1. 记录原节点与拷贝节点的映射关系：
 *  - visited.put(originalNode, clonedNode) 表示将原始节点和对应的拷贝节点关联起来。
 *  - 后续访问时，可以通过 visited.get(originalNode) 快速找到已经创建的拷贝节点。
 * 2. 标记节点是否已经访问过：
 *  - 如果一个节点已经存在于 visited 中，就说明它已经被访问并拷贝过，避免重复拷贝或进入无限递归（尤其在图中有环的情况下）。
 *    时间复杂度：(O(V + E)), where (V) is the number of nodes and (E) is the number of edges.
 *    空间复杂度：(O(V)).
 */
public class LeetCode_0133 {

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // Map to track visited nodes and their clones
        HashMap<Node, Node> visited = new HashMap<>();

        // Perform DFS to clone the graph
        cloneNode(node, visited);

        // Return the clone of the original node
        return visited.get(node);
    }

    private void cloneNode(Node node, HashMap<Node, Node> visited) {
        // Create a clone for the current node
        Node clonedNode = new Node(node.val);
        visited.put(node, clonedNode);

        // Traverse neighbors
        for (Node neighbor : node.neighbors) {
            // If the neighbor is not visited, clone it recursively
            if (!visited.containsKey(neighbor)) {
                cloneNode(neighbor, visited);
            }
            // Add the cloned neighbor to the current cloned node
            visited.get(node).neighbors.add(visited.get(neighbor));
        }
    }

    public static void main(String[] args) {
        LeetCode_0133 solution = new LeetCode_0133();
        LeetCode_0133.Node node1 = solution.new Node(1);
        LeetCode_0133.Node node2 = solution.new Node(2);
        LeetCode_0133.Node node3 = solution.new Node(3);
        LeetCode_0133.Node node4 = solution.new Node(4);

        // Build the graph
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

         Node clonedGraph = solution.cloneGraph(node1);

        // Print the cloned graph for validation
        System.out.println("Original Node: " + node1.val + ", Cloned Node: " + clonedGraph.val);
        for (Node neighbor : clonedGraph.neighbors) {
            System.out.println("Cloned Node " + clonedGraph.val + " -> Neighbor: " + neighbor.val);
        }

    }



}
