package Algorithms.TwoPointers;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

/**
 * 最小覆盖子串
 */
public class LeetCode_0076 {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(minWindow("aaabbbbbcdd", "abcdd")); // abbbbbcdd
    }

    /**
     * 自定义一个类来记录结果的左右边界
     */
    static class Result {
        int i;
        int j;

        public Result(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    /*
      1. 统计目标串需要的各种字符个数，统计原始串 i～j 范围各种字符个数
      2. 如果原始串 i~j 范围内不满足条件，j++ 扩大范围，直到满足条件 j 停下来
      3. 一旦满足条件 i++ 缩小范围，直到再次不满足条件 ...
      4. 重复 2. 3. 两步，直至j到达原始串末尾
     */
    static String minWindow (String s, String t){
        char[] target = t.toCharArray();
        int[] targetCount = new int[128]; //用于统计target数组中的字符个数的整数数组，target数组的字符全是大写或者小写字母， 所以用128。
        for (char ch : target) {
            targetCount[ch]++; //字符做索引，对应它的unicode，大写字母A-Z的Unicode范围是65-90。小写字母a-z的Unicode范围是97-122。
        }
//        print(targetCount);
        int passTotal = 0; //条件总数
        for(int count : targetCount){
            if(count > 0){
                passTotal++;
            }
        }
//        System.out.println("条件总数： " + passTotal);

        int passed = 0; //已通过的条件数
        char[] source = s.toCharArray();
        int[] sourceCount = new int[128];
        int i = 0;
        int j = 0;
        Result res = null;  //使用自定义类，进去循环之前赋值为null;
        while(j < source.length){
            char right = source[j];
            sourceCount[right]++;
            if(sourceCount[right] == targetCount[right]){
                passed++;
            }
//            System.out.println("处理的字符：" + right + " 通过数：" + passed);
//            print(sourceCount);
//            System.out.println("------------------------------");
            //若已满足所有条件，缩小i范围，更新范围内字符计数和通过条件数
            while(passed == passTotal && i<=j){   //找到了一个解
                if (res == null) {
                    res = new Result(i, j);
                } else {
                    if (j - i < res.j - res.i) {
                        res = new Result(i, j);
                    }
                }
                char left = source[i];
                sourceCount[left]--;
                if(sourceCount[left] < targetCount[left]){
                    passed--;
                }
                i++;
            }
            j++;
        }
        return res == null ? "" : new String(source, res.i, res.j - res.i + 1);

    }

    /**
     * 打印数组中值不为0的元素
     * @param count 数组
     */
    static void print(int[] count) {
        System.out.println(IntStream.range(0, count.length)
                .filter(i -> count[i] != 0)
                .boxed()
                .collect(toMap(i -> (char) i.intValue(), i -> count[i])));
    }
}
