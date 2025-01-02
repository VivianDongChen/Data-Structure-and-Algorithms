package HackerRank;

import ch.qos.logback.core.joran.sanity.Pair;

import java.util.*;

public class TikTok_TikTokViralPosts {
    static int solve(int x) {
        int maxGap = 0, prev = -1, pos = 0;
        while (x > 0) {
            if ((x & 1) == 1) { // 检查当前位是否为 1
                if (prev != -1) {
                    maxGap = Math.max(maxGap, pos - prev - 1);
                }
                prev = pos; // 更新前一个 '1' 的位置
            }
            x >>= 1; // 右移检查下一位 相当于x = x >> 1
            pos++;
        }
        return maxGap;
    }
    public static List<Integer> getTopKViralPosts(List<Integer> activityIndex, int k) {
        class Pair {
            int gap;
            int value;
            Pair(int gap, int value) {
                this.gap = gap;
                this.value = value;
            }
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.gap == b.gap) {
                return Integer.compare(a.value, b.value); // 按值升序排列
            }
            return Integer.compare(a.gap, b.gap); // 按 gap 升序排列
        });

        for (int x : activityIndex) {
            Pair p = new Pair(solve(x), x);
            pq.offer(p);
            if (pq.size() > k) {
                pq.poll(); // 移除最小的元素，保持堆大小为 k
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().value);
        }
        Collections.reverse(ans); // 按 gap 降序排列
        return ans;
    }

    public static void main(String[] args) {
        // Test case: activityIndex = [11, 3, 4, 9, 7], k = 3
        List<Integer> activityIndex = Arrays.asList(11, 3, 4, 9, 7);
        int k = 3;

        // Get the top K viral posts
        List<Integer> topPosts = getTopKViralPosts(activityIndex, k);

        // Print the results
        System.out.println("Top " + k + " Viral Posts: " + topPosts);   //expected: [9,11,7]
    }
}
