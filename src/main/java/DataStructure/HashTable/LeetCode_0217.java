package DataStructure.HashTable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 存在重复元素
 */
public class LeetCode_0217 {
    /**
        方法1: 使用HashMap

        输入：nums = [1,2,3,1]
        输出：true

        输入：nums = [1,2,3,4]
        输出：false

        [1,2,3]
     */
    public boolean containsDuplicate1(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int key: nums){
            if(map.containsKey(key)){
                return true;
            }else{
                map.put(key,key);
            }
        }
        return false;
    }

    /**
     * 方法2: 使用HashSet
     */
    public boolean containsDuplicate2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(Integer num:nums){
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }

    /**
     * 方法3: 使用HashTable（改进版）
     */
    public boolean containsDuplicate3(int[] nums){
        HashMap<Integer,Object> map = new HashMap<>(nums.length * 2);
        Object value = new Object();
        for(Integer key: nums){
            Object put = map.put(key,value);
            if(put != null){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode_0217().containsDuplicate3(new int[]{1,2,3,1}));
        System.out.println(new LeetCode_0217().containsDuplicate3(new int[]{1,2,3,4}));
    }
}
