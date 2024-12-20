package DataStructure.DisjointSet;

import java.util.*;

/**
 * LeetCode 399: Evaluate Division - 并查集 (Union-Find)
 * 解决问题：给定两个变量之间的除法关系 (a / b = value)，并对查询的除法结果进行求解。
 * 核心思想：使用并查集维护变量之间的连通性和权重关系。

 * 时间复杂度分析
 * - 初始化和建立并查集(Union)：对于每个方程进行一次 union 操作。每次union的时间复杂度为O(α(N))，其中α 是反阿克曼函数，接近常数级别。
 * - 路径压缩 (Find)：在 find 操作中，路径压缩使得树高度趋近于O(1)，时间复杂度为O(α(N))。
 * - 查询操作：每个查询执行两次 find 操作（找到两个节点的根）。每次查询的时间复杂度为O(α(N))。
 * - 总时间复杂度：假设有E个方程，Q个查询：O(E⋅α(N)+Q⋅α(N))     α(N) 很小，接近常数级别，因此总复杂度近似为 O(E+Q)。

 * 空间复杂度分析
 * - 并查集数据结构：parent 和 weight 使用了两个 Map，分别存储每个节点的父节点和权重。空间复杂度为O(N)，其中N 是不同变量的数量。
 * - 输入与输出：存储输入的方程和查询需 O(E+Q) 的空间。
 * - 总空间复杂度：O(N+E+Q)
 */
public class LeetCode_0399_II {

    /**
     * parent Map 用于存储每个节点的父节点。
     * key: 子节点, value: 父节点
     */
    private Map<String, String> parent;

    /**
     * weight Map 用于存储节点与其父节点之间的权重关系。
     * key: 当前节点, value: 父节点 / 当前节点 的值
     */
    private Map<String, Double> weight;

    /**
     * 计算查询中变量的除法结果。
     *
     * @param equations 给定的除法等式列表，每个元素为 ["A", "B"]，表示 A / B。
     * @param values    equations 中每个等式的值，如 A / B = value。
     * @param queries   需要求解的除法查询，每个元素为 ["X", "Y"]，表示 X / Y。
     * @return 每个查询的结果数组，如果无法计算（不连通）则返回 -1.0。
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parent = new HashMap<>();
        weight = new HashMap<>();

        // Step 1: 建立并查集结构，合并变量之间的关系
        for (int i = 0; i < equations.size(); i++) {
            String var1 = equations.get(i).get(0);
            String var2 = equations.get(i).get(1);
            double value = values[i];
            union(var1, var2, value);
        }

        // Step 2: 处理查询，求解变量之间的除法结果
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);
            results[i] = query(var1, var2);
        }
        return results;
    }

    /**
     * 合并两个变量所在的集合，并维护它们之间的权重关系。
     *
     * @param var1 变量 1
     * @param var2 变量 2
     * @param value var1 / var2 的值
     */
    private void union(String var1, String var2, double value) {
        if (!parent.containsKey(var1)) {
            parent.put(var1, var1);
            weight.put(var1, 1.0);
        }
        if (!parent.containsKey(var2)) {
            parent.put(var2, var2);
            weight.put(var2, 1.0);
        }

        // 找到 var1 和 var2 的根节点
        String root1 = find(var1);
        String root2 = find(var2);

        // 如果两个变量不属于同一个集合，则合并
        if (!root1.equals(root2)) {
            parent.put(root1, root2);  // 把 root1 挂到 root2 上
            // 更新 root1 的权重：
            // weight.get(var2) / (weight.get(var1) * value) = (root2/var2)  / ( (root1/var1) * (var1/var2) )= root2/root1
            weight.put(root1, weight.get(var2) / (weight.get(var1) * value));
        }
    }

    /**
     * 查找某个变量的根节点，并通过路径压缩优化查询。
     * 在路径压缩过程中，节点的权重也会更新，使其表示 根节点 / 当前节点 的值。
     * @param var 需要查找的变量
     * @return 该变量所属集合的根节点
     */
    private String find(String var) {
        if (!parent.get(var).equals(var)) {
            String originalParent = parent.get(var);
            String root = find(originalParent); // 递归查找根节点
            parent.put(var, root); // 路径压缩：将当前节点直接指向根节点
            // 更新权重：
            // weight.get(var) * weight.get(originalParent) = originalParent/var * originalParent的父节点/originalParent = ... = root/var
            weight.put(var, weight.get(var) * weight.get(originalParent));
        }
        return parent.get(var);
    }

    /**
     * 查询两个变量之间的除法结果。
     *
     * @param var1 变量 1
     * @param var2 变量 2
     * @return 如果两个变量连通，则返回 var1 / var2 的值；否则返回 -1.0。
     */
    private double query(String var1, String var2) {
        if (!parent.containsKey(var1) || !parent.containsKey(var2)) {
            return -1.0; // 如果变量不存在，返回 -1.0
        }

        String root1 = find(var1); // 找到 var1 的根
        String root2 = find(var2); // 找到 var2 的根

        if (!root1.equals(root2)) {
            return -1.0; // 如果根不同，说明两个变量不连通
        }
        // 返回权重比值  weight.get(var2) / weight.get(var1)  = (root2/var2) / (root1/var1) = var1/var2
        return weight.get(var2) / weight.get(var1);
    }

    /**
     * 主方法：测试 calcEquation 功能
     */
    public static void main(String[] args) {
        List<List<String>> equations1 = Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );

        List<List<String>> equations2 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );
        double[] values2 = {1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );

        LeetCode_0399_II test = new LeetCode_0399_II();
        System.out.println(Arrays.toString(test.calcEquation(equations1, values1, queries1))); // expected: [6.0, 0.5, -1.0, 1.0, -1.0]
        System.out.println(Arrays.toString(test.calcEquation(equations2, values2, queries2))); // expected: [3.75, 0.4, 5.0, 0.2]
    }
}
