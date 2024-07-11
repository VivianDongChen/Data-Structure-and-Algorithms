package Algorithms.Greedy;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 活动选择问题 - 贪心解法
 * LeetCode 435 无重叠区间本质就是活动选择问题
 */
public class ActivitySelectionProblem {
    /*
      要在一个会议室举办n个活动
      - 每个活动它们各自的起始和结束时间
      - 找出在时间上互不冲突的活动组合，能够最充分利用会议室（举办的活动次数最多）

      例1
          0  1  2  3  4  5  6  7  8  9
             |-----)
                |-----)
                   |-----)

      例2
          0  1  2  3  4  5  6  7  8  9
             ｜--）
                   ｜--）
          ｜-----------------）
                         ｜-----）
                                  ｜--）
                         ｜-----------）

      几种贪心策略
        1. 优先选择持续时间最短的活动 - 不可行
            0   1   2   3   4   5   6   7   8   9
        1       |---------------)                      选中
        2                   |-------)
        3                       |---------------)      选中

        2. 优先选择冲突最少的活动 - 不可行
        编号 0   1   2   3   4   5   6   7   8   9
        1   |-------)                                       3   选中
        2       |-------)                                   4
        3       |-------)                                   4
        4       |-------)                                   4
        5           |-------)                               4   选中
        6               |-------)                           2
        7                   |-----------)                   4   选中
        8                           |-------)               4
        9                           |-------)               4
       10                           |-------)               4
       11                               |-------)           3   选中

        3. 优先选择最先开始的活动 - 不可行
            0   1   2   3   4   5   6   7   8   9
        2       |---)                               选中
        3           |---)                           选中
        4               |---)                       选中
        1   |-----------------------------------)

        4. 优先选择最先结束的活动 - 可行



     */
    static class Activity{
        int index;  //活动的编号
        int start;  //活动的起始时间
        int finish;  //活动的结束时间

        public Activity(int index, int start, int finish) {
            this.index = index;
            this.start = start;
            this.finish = finish;
        }

        public int getFinish() {
            return finish;
        }

        @Override
        public String toString() {
            return "Activity("+ index + ")" ;
        }
    }

    public static void main(String[] args) {
        Activity[] activities = new Activity[]{
                new Activity(0,1,3),
                new Activity(2,3,5),
                new Activity(1,2,4),
        };
        Arrays.sort(activities, Comparator.comparingInt(Activity::getFinish));  //按照结束时间排好序
        System.out.println(Arrays.toString(activities));
        select(activities, activities.length);

    }

    public static void select(Activity[] activities, int n) {
        List<Activity> result = new ArrayList<>();   //定义一个存放结果的列表
        Activity prev = activities[0];
        result.add(prev); //第一个（结束时间最早的那个）一定会被加入列表
        for (int i = 1; i < n; i++) {
            Activity curr = activities[i];
            if(curr.start >= prev.finish){ //没有冲突
                result.add(curr);
                prev = curr;
            }
        }
        for(Activity activity : result){
            System.out.println(activity);
        }
    }

}
