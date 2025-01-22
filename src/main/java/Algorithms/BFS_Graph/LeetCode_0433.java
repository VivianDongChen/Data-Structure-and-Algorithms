package Algorithms.BFS_Graph;
import java.util.*;

/**
 * 433. Minimum Genetic Mutation
 * 难点：将每次突变视为一层BFS
 */
public class LeetCode_0433 {
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> q = new LinkedList<>();
        HashSet<String> vis = new HashSet<String>();
        List<String> banks = Arrays.asList(bank);
        int steps = 0;
        q.add(start);
        vis.add(start);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {  //一层BFS代表突变一次
                String s = q.poll();
                if (s.equals(end)) return steps;    //突变成功，退出，返回次数
                char[] ca = s.toCharArray();  //将s变成 char 数组
                for (int j = 0; j < 8; j++) {   //每一个字符都有可能突变
                    char oc = ca[j];       //依次取出char数组的每个字符, 先放入oc
                    for (int k = 0; k < 4; k++) {      //每一次突变有4种可能
                        ca[j] = "ACGT".charAt(k);    //使用A,C,G,T来替换这个字符
                        String t = new String(ca);    //替换字符后的char数组转换为t
                        if (!vis.contains(t) && banks.contains(t)) {   //如果t没有遍历过，且在banks中
                            q.add(t);  //将t放入q
                            vis.add(t);   //标注为已经遍历过
                        }
                    }
                    ca[j] = oc;   //还原
                }
            }
            steps += 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        LeetCode_0433 solver = new LeetCode_0433();

        // 测试用例
        String startGene = "AACCGGTT";
        String endGene = "AACCGGTA";
        String[] bank = {"AACCGGTA"};

        int result = solver.minMutation(startGene, endGene, bank);
        System.out.println("Minimum mutations to reach the target gene: " + result);
    }
}
