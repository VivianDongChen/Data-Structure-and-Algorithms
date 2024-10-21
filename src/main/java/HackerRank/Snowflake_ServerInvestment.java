package HackerRank;
import java.util.Arrays;

public class Snowflake_ServerInvestment {

    /**
     * 总时间复杂度
     * 在最坏情况下，外层循环执行 n 次，内层循环执行最多 num_server[i] 次。
     * 时间复杂度为：O(n×s)
     * 其中：n 是网络系统的数量。s 是服务器数量的上限（即 num_server[i] 的最大值）。
     * 空间复杂度为 O(n)，因为你需要一个大小为n的数组来存储结果。
     */
    public static int[] getMaxUpgradedServers1(int[] num_server, int[] money, int[] sell, int[] upgrade){
        int n = num_server.length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int y = num_server[i];
            for(int j = y; j > 0; j--){
                if(j * upgrade[i] <= money[i] + (y-j)*sell[i]){
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 优化：内层循环使用二分查找
     * 外层循环：O(n)
     * 二分查找：O(logs)
     * 总时间复杂度：O(n×logs)
     */
    public static int[] getMaxUpgradedServers2(int[] num_server, int[] money, int[] sell, int[] upgrade){
        int n = num_server.length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int right = 0;
            int left = num_server[i];
            while(left <= right){
                int mid = left + (right -left)/2;
                if( mid * upgrade[i] <= money[i] + (num_server[i] - mid) * sell[i]){
                    res[i] = mid;
                    left = mid + 1; //继续查找有没有更大的值
                }else{
                    right = mid - 1;  //否则就减小这个值
                }
            }
        }
        return res;
    }

    /**
     * 数学推导法
     * 我们需要找到最大的升级数量 x，满足以下条件：x×upgrade[i] ≤ money[i]+(y−x)×sell[i]
     * 其中y 是初始服务器数量，x 是我们最终想要升级的服务器数量。
     * 将不等式化简为：x×(upgrade[i]+sell[i]) ≤ money[i]+y×sell[i]
     * 我们最终希望求解满足上述不等式的最大整数
     * 直接求解最大x = min(y, money[i]+y×sell[i]/upgrade[i]+sell[i]）
     * 这里y是初始服务器数量，因为升级的服务器数量不能超过它。
     * 通过上面的公式计算，保证我们在给定资金和卖服务器的条件下找到最优的升级数量。
     * 时间复杂度：O(n)，因为我们只需要遍历每个网络系统一次，并在常数时间内计算最大升级数量。
     * 空间复杂度：O(n)，用于存储结果数组。
     */
    public static int[] getMaxUpgradedServers3(int[] num_server, int[] money, int[] sell, int[] upgrade){
        int n = num_server.length;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int y = num_server[i];
            int x = Math. min(y, (money[i]+y*sell[i])/(upgrade[i]+sell[i]));
            res[i] = x;
        }
        return res;
    }

    public static void main(String[] args) {
        // 示例测试
        int[] num_servers = {4, 3};
        int[] money = {8, 9};
        int[] sell = {4, 2};
        int[] upgrade = {4, 5};

        int[] result = Snowflake_ServerInvestment.getMaxUpgradedServers3(num_servers, money, sell, upgrade);

        System.out.println(Arrays.toString(result));  // 输出：[3, 2]

        // 边界测试
        int[] num_servers2 = {0, 3};
        int[] money2 = {5, 9};
        int[] sell2 = {2, 2};
        int[] upgrade2 = {3, 5};

        int[] result2 = Snowflake_ServerInvestment.getMaxUpgradedServers3(num_servers2, money2, sell2, upgrade2);

        System.out.println(Arrays.toString(result2));  // 输出：[0, 2]

    }
}
