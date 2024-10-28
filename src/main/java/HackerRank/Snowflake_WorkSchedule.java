package HackerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snowflake_WorkSchedule {
    public static String[] findSchedules(int work_hours, int day_hours, String pattern) {
        List<String> result = new ArrayList<>();
        char[] schedule = pattern.toCharArray();
        int totalFixedHours = 0;
        List<Integer> questionMarks = new ArrayList<>();

        // 计算已确定的工时，并记录 '?' 的位置
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] == '?') {
                questionMarks.add(i);
            } else {
                totalFixedHours += schedule[i] - '0';
            }
        }

        // 计算需要在 '?' 中分配的剩余工时
        int remainingHours = work_hours - totalFixedHours;

        // 回溯寻找所有符合条件的组合
        backtrack(schedule, questionMarks, 0, remainingHours, day_hours, result);
//
//        // 按字典序排序结果
//        Collections.sort(result);

        //result.toArray() 返回的是 Object[]，需要强制类型转换，不够安全。
        //result.toArray(new String[0]) 返回的是 String[]，避免了强制类型转换的风险，代码更简洁和安全。
        return result.toArray(new String[0]);
    }

    // 回溯函数
    private static void backtrack(char[] schedule, List<Integer> questionMarks, int index,
                                  int remainingHours, int day_hours, List<String> result) {
        // 剪枝1：如果剩余工时超过剩余位置的最大可填工时，直接返回
        if (remainingHours > (questionMarks.size() - index) * day_hours) {
            return;
        }
        // 如果所有 '?' 都已替换，并且剩余工时为 0，则保存结果
        if (index == questionMarks.size()) {
            if (remainingHours == 0) {
                result.add(new String(schedule));  //new String(schedule) 将 char[] 转换为 String，以便能够存储在 List<String> 中。
            }
            return;
        }
        // 剪枝2：如果剩余工时正好是每个 '?' 填 0 的总和，直接填 0 并返回
        if (remainingHours == 0) {
            for (int i = index; i < questionMarks.size(); i++) {
                schedule[questionMarks.get(i)] = '0';
            }
            result.add(new String(schedule));
            return;
        }

        // 尝试将 '?' 替换为 0 到 day_hours 之间的所有可能值
        int pos = questionMarks.get(index); //获得当前？的位置
        for (int i = 0; i <= day_hours; i++) {
            if (i <= remainingHours) {
                schedule[pos] = (char) ('0' + i);  // 替换 '?' 为当前尝试的工时， 将整数i转换为对应的字符
                backtrack(schedule, questionMarks, index + 1, remainingHours - i, day_hours, result);
                schedule[pos] = '?';  // 回溯
            }
        }
    }

    public static void main(String[] args) {
        // 示例测试
        String pattern = "08??840";
        int work_hours = 24;
        int day_hours = 4;

        String[] schedules = findSchedules(work_hours, day_hours, pattern);

        // 打印所有可能的时间表
        for (String schedule : schedules) {
            System.out.println(schedule);
        }
    }
}
