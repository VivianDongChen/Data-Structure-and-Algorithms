package DataStructure.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组
 */
public class LeetCode0049GroupAnagrams {

    /*
        解法1 思路
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


    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // eat tea ate => aet
        // tan nat     => ant
        // bat         => abt
        // [(aet,{eat,tea}), (ant,{tan})]
        List<List<String>> lists = new LeetCode0049GroupAnagrams().groupingAnagrams1(strs);
        System.out.println(lists);


    }

}
