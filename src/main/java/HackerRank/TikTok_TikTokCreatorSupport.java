package HackerRank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TikTok_TikTokCreatorSupport {
    public static int maximizeCreatorSupport(List<Integer> impactValue) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> zeros = new ArrayList<>();
        List<Integer> nega = new ArrayList<>();
        for (int x : impactValue) {
            if (x > 0) {
                pos.add(x);
            } else if (x == 0) {
                zeros.add(x);
            } else {
                nega.add(x);
            }
        }
        Collections.sort(nega, Collections.reverseOrder()); //对负值列表 nega 按降序排序。
        long sum = pos.stream().mapToLong(Integer::longValue).sum();  //计算正值列表 pos 的所有元素的总和。
        int count = pos.size(); //初始化支持创作者的数量，初始值为正值创作者的数量。
        if (sum > 0) {
            count += zeros.size();
        }
        for (int neg : nega) {
            sum += neg;
            if (sum > 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 输入数据
        int n = 6;
        List<Integer> impactValue = Arrays.asList(-4, 3, 2, -10, -8, 5);

        // 调用函数
        int maxCreators = maximizeCreatorSupport(impactValue);

        // 输出结果
        System.out.println("Maximum creators supported (n = " + n + "): " + maxCreators);
    }

}
