package DataStructure.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组
 */
public class LeetCode0049GroupAnagrams {

    /**
     * 方法1
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        eat tea ate => aet
        tan nat     => ant
        bat         => abt
        [(aet,{eat,tea}), (ant,{tan})]

        思路
        1. 遍历字符串数组，每个字符串中的字符重新排序后作为 key
        2. 所谓分组，其实就是准备一个集合，把这些单词加入到 key 相同的集合中
        3. 返回分组结果
     */
    public List<List<String>> groupingAnagrams1(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            //将每一个String转换为字符数组，以便排序， 排好序的Array转换为String，即为key
            char[] chars =  str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            //找到key对应的list，如果没有就创建一个
            List<String> list = map.get(key);
            if(list == null) {
                list = new ArrayList<>();
                map.put(key,list);
            }
            //将这个String放入list
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 方法2: 方法1的改进版
     * @param strs
     * @return
     */
    public List<List<String>> groupingAnagrams2(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] chars =  str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            //使用 computeIfAbsent 方法查找键对应的列表。如果键不存在，则创建一个新的空列表。
            // computeIfAbsent 方法接受一个键和一个函数作为参数，如果键不存在，则使用函数创建一个新的值并插入到哈希表中。
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());//

            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

     /**
      * 方法3
        题目中有说明：strs[i] 仅包含小写字母
        key = [2, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]  26
        key = [2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0]  26
        "eaat", "teaa"
     */
     static class ArrayKey{

         int[] key = new int[26];

         @Override
         public boolean equals(Object o) {
             if (this == o) return true;
             if (o == null || getClass() != o.getClass()) return false;
             ArrayKey arrayKey = (ArrayKey) o;
             return Arrays.equals(key, arrayKey.key);
         }

         @Override
         public int hashCode() {
             return Arrays.hashCode(key);
         }

         public ArrayKey(String str){
             for (int i = 0; i < str.length(); i++) {
                 char ch = str.charAt(i); //'a'97, 'b' 98...
                 key[ch -97] ++;
             }
         }
     }

     public List<List<String>> groupingAnagrams3(String[] strs){
         HashMap <ArrayKey, List<String>> map = new HashMap<>();
         for(String str: strs){
             ArrayKey key = new ArrayKey(str);
             List<String> list = map.computeIfAbsent(key, k-> new ArrayList<>());
             list.add(str);
         }
         return new ArrayList<>(map.values());
     }




    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = new LeetCode0049GroupAnagrams().groupingAnagrams3(strs);
        System.out.println(lists);


    }

}
