package HackerRank;

import java.util.Arrays;

public class Amazon_MinimizeMaximumParcels {
    public int minMaxPackages(int[] packages, int extra_packages) {
        int left = Arrays.stream(packages).max().getAsInt(); // 最小可能的最大值
        int right = Arrays.stream(packages).sum() + extra_packages; // 最大可能的最大值

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canDistribute(packages, extra_packages, mid)) {
                right = mid; // 试图降低 max_limit
            } else {
                left = mid + 1; // 需要更大的 max_limit
            }
        }

        return left; // left == right 时，即为最小可能的最大值
    }

    private boolean canDistribute(int[] packages, int extra_packages, int max_limit) {
        int needed = 0;

        for (int p : packages) {
            if (p < max_limit) { // 仅填充小于 max_limit 的仓库
                needed += (max_limit - p);
            }

            if (needed >= extra_packages) return true; // 超过了 available 包裹数，说明 max_limit 过小
        }

        return false;
    }


    public static void main(String[] args) {
        Amazon_MinimizeMaximumParcels sol = new Amazon_MinimizeMaximumParcels();
        int[] packages1 = {7,5,1,9,1};
        int extra_packages1 = 25;
        System.out.println(sol.minMaxPackages(packages1, extra_packages1)); // 应该输出 10
        int[] packages2 = {1,2,3};
        int extra_packages2 = 3;
        System.out.println(sol.minMaxPackages(packages2, extra_packages2));   //输出3
        int[] packages3 = {1};
        int extra_packages3 = 3;
        System.out.println(sol.minMaxPackages(packages3, extra_packages3));   //输出4

    }
}


