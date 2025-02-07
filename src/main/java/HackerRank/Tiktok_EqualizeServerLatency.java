package HackerRank;

import java.util.*;

public class Tiktok_EqualizeServerLatency {
    public static int minAdditionalLatency(int n, List<Integer> latency) {
        int[] inc = new int[1]; // 记录需要增加的最小延迟
        dfs(0, n, latency, inc);
        return inc[0];
    }

    private static int dfs(int i, int n, List<Integer> latency, int[] inc) {
        int l = 2 * i + 1; // 左子节点
        int r = 2 * i + 2; // 右子节点

        // 如果是叶子节点，返回 0
        if (l >= n) {
            return 0;
        }

        // 计算左右子树的最大路径延迟
        int costL = dfs(l, n, latency, inc) + latency.get(l - 1);
        int costR = dfs(r, n, latency, inc) + latency.get(r - 1);

        // 计算当前节点的最长路径
        int d = Math.max(costL, costR);

        // 如果左子树路径较短，需要补齐
        if (costL < d) {
            int diff = d - costL;
            inc[0] += diff; // 记录补偿的延迟
            latency.set(l - 1, latency.get(l - 1) + diff);
            costL = d;
        }

        // 如果右子树路径较短，需要补齐
        if (costR < d) {
            int diff = d - costR;
            inc[0] += diff; // 记录补偿的延迟
            latency.set(r - 1, latency.get(r - 1) + diff);
            costR = d;
        }

        return d;
    }

    public static void main(String[] args) {
        int n = 7;
        List<Integer> latency = new ArrayList<>(Arrays.asList(3, 1, 2, 1, 5, 4)); // 6个元素，对应节点1~6

        int result = minAdditionalLatency(n, latency);
        System.out.println("Minimum Additional Latency: " + result); // **应该输出 3**
    }
}



