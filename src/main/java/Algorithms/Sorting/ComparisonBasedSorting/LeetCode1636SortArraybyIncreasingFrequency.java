package Algorithms.Sorting.ComparisonBasedSorting;

import java.util.Arrays;

/**
 * 按出现频率排序，数据范围在【-100，100】内
 */
public class LeetCode1636SortArraybyIncreasingFrequency {
    public int[] frequencySort(int[] nums){
        //1.统计出现频率
        int[] count = new int[201];
        for(int i : nums){
            count[i + 100]++;  //i + 100将其映射到0到200的索引范围。
        }
        //2. 比较器 按频率升序，再按数值将序
        return Arrays.stream(nums).boxed().sorted((a,b) ->{ //将整数数组nums转换为流，并使用boxed()方法将其转换为Integer类型的流
            int af = count[a + 100];
            int bf = count[b + 100];
            if(af < bf){
                return -1;
            }else if (af > bf){
                return 1;
            }else{
                return b - a;
            }
        }).mapToInt(Integer :: intValue) .toArray();
        //使用mapToInt(Integer::intValue)将Integer流转换回int类型的流，并使用toArray()方法将其转换为数组。
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,3,2};
        System.out.println(Arrays.toString(new LeetCode1636SortArraybyIncreasingFrequency().frequencySort(nums)));
    }
}
