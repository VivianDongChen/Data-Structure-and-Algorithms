package HackerRank;

//这个代码可能还存在问题，第一个测试用例的结果是7，应该是6

public class TikTok_MaximumPositiveFeedback {
    public static int getMaxPositiveFeedback(String videoFeedback) {
        int n = videoFeedback.length();
        int totalOnes = 0; // 统计原始 '1' 的总数

        // 先统计 '1' 的数量
        for (char c : videoFeedback.toCharArray()) {
            if (c == '1') {
                totalOnes++;
            }
        }

        // **特判：全是 '1' 时，必须翻转至少一个，因此答案是 n - 1**
        if (totalOnes == n) {
            return n - 1;
        }

        // 构造增益数组 B
        int[] B = new int[n];
        for (int i = 0; i < n; i++) {
            if (videoFeedback.charAt(i) == '1') {
                B[i] = -1;  // 翻转 '1' 会减少正反馈
            } else {
                B[i] = 1;   // 翻转 '0' 会增加正反馈
            }
        }

        // **修正 Kadane's Algorithm：确保在全负数的情况下，正确选择最大子数组和**
        int currentGain = B[0];  // **修正：起始值必须是第一个元素**
        int maxGain = B[0];      // **修正：确保 Kadane's 计算正确**
        for (int i = 1; i < n; i++) {
            currentGain = Math.max(B[i], currentGain + B[i]);
            maxGain = Math.max(maxGain, currentGain);
        }

        // **如果 maxGain 仍然是负数（全是 -1），必须至少选择一个 -1**
        maxGain = Math.max(0, maxGain);

        // 返回最大正反馈数量
        return totalOnes + maxGain;
    }

    public static void main(String[] args) {
        // ✅ Test Case 1
        String videoFeedback1 = "1000011";
        int result1 = getMaxPositiveFeedback(videoFeedback1);
        System.out.println("Test Case 1: " + result1); // **应为 6**

        // ✅ Test Case 2
        String videoFeedback2 = "1111111";
        int result2 = getMaxPositiveFeedback(videoFeedback2);
        System.out.println("Test Case 2: " + result2); // **应为 6**

        // ✅ Test Case 3
        String videoFeedback3 = "0000000";
        int result3 = getMaxPositiveFeedback(videoFeedback3);
        System.out.println("Test Case 3: " + result3); // **应为 7**

        // ✅ Test Case 4
        String videoFeedback4 = "10";
        int result4 = getMaxPositiveFeedback(videoFeedback4);
        System.out.println("Test Case 4: " + result4); // **应为 2**

        // ✅ Test Case 5
        String videoFeedback5 = "101010";
        int result5 = getMaxPositiveFeedback(videoFeedback5);
        System.out.println("Test Case 5: " + result5); // **应为 4**
    }
}
