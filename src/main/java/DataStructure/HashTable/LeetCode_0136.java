package DataStructure.HashTable;

import java.util.HashSet;

/**
 * 找出出现一次的数字
 * 除了某个元素只出现一次以外，其余每个匀速均出现两次
 * nums的长度大于等于1
 */
public class LeetCode_0136 {

    /**
        方法1: 使用HashSet

        输入：nums = [2,2,1]
        输出：1
        [1]

        输入：nums = [4,1,2,1,2]
        输出：4
        [4]

        思路1

        1. 准备一个 Set 集合，逐一放入数组元素
        2. 遇到重复的，则删除
        3. 最后留下来的，就是那个没有重复的数字
     */
    public int singleNumber1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(Integer num : nums){
            if(!set.add(num)){
                set.remove(num);
            }
        }
        return set.toArray(new Integer[1])[0];
    }

    /**
     * 方法2: 借助异或运算
     *
     *      异或运算：1和1异或得0， 0和0异或得0，1和0异或得1
     *
     *      思路
     *      1. 任何相同的数字异或，结果都是0
     *      2. 任何数字与0异或，结果是数字本身
     *
     *      所有数字进行异或，剩下的就是目标数字
     *
     *      输入：nums = [2,2,1]
     *      输出：1
     *
     *      输入：nums = [4,1,2,1,2]
     *      输出：4
     */
    public int singleNumber2(int[] nums){
       int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    public static void main(String[] args) {
        LeetCode_0136 test = new LeetCode_0136();
        System.out.println(test.singleNumber2(new int[]{2,2,1}));
        System.out.println(test.singleNumber2(new int[]{4,1,2,1,2}));
    }


}
