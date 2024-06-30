package DataStructure.HashTable;

import com.google.common.hash.Hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * 出现次数最多的单词
 * 答案会唯一
 */
public class LeetCode0819MostCommonWord {

    /**
     * 方法1
     *         1. 将 paragraph 截取为一个个单词
     *         2. 将单词加入 map 集合，单词本身作为 key，出现次数作为 value，避免禁用词加入
     *         3. 在 map 集合中找到 value 最大的，返回它对应的 key 即可
     */
    public String mostCommonWord1(String paragraph, String[] banned){
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+"); //将段落转换为小写并使用正则表达式分割成单词
        HashMap<String,Integer> map = new HashMap<>();
        Set<String> set = Set.of (banned);  //使用Set存储禁用词，以便快速查找
        for(String key: split){
            if(!set.contains(key)){
                //方式1
//           Integer value = map.get(key);
//            if (value == null){
//               value = 0;
//            }
//            map.put(key,value+1);
                //方式2
//            map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                //方式3
                map.put(key,map.getOrDefault(key,0) + 1);
            }
        }
        Optional<Map.Entry<String, Integer>> optional = map.entrySet().stream().max(Map.Entry.comparingByValue());//使用流操作和 max 方法找到 HashMap 中值最大的键值对
        return optional.map(Map.Entry:: getKey).orElse(null); //如果 Optional 中存在值，使用 map 方法提取键；否则返回 null。

    }

    /**
     * 方法2： 改进版1
     */
    public String mostCommonWord2(String paragraph, String[] banned){
        String[] split = paragraph.toLowerCase().split("[^A-Za-z]+");
        HashMap<String,Integer> map = new HashMap<>();
        Set<String> set = Set.of (banned);
        for(String key: split){
            if(!set.contains(key)){
                map.put(key,map.getOrDefault(key,0) + 1);
            }
        }
        //遍历map，找到value最大的
        int max = 0;
        String maxKey = null;
        for(Map.Entry<String, Integer> e: map.entrySet()){
            if(e.getValue() > max){
                max = e.getValue();
                maxKey = e.getKey();
            }
        }
        return maxKey;

    }

    /**
     * 方法3： 改进版2
     */
    public String mostCommonWord3(String paragraph, String[] banned){
        HashMap<String,Integer> map = new HashMap<>();
        Set<String> set = Set.of (banned);

        char[] chars = paragraph.toLowerCase().toCharArray();  //将String转化为char[]
        StringBuilder sb = new StringBuilder();  //逐个字符判断，再用sb组合成字符串
        for(char ch: chars){
            if(ch >='a' && ch <='z'){
                sb.append(ch);
            }else if(sb.length() > 0){
                String key = sb.toString();
                if(!set.contains(key)){
                    map.put(key,map.getOrDefault(key,0)+1);
                }
//                sb = new StringBuilder();
                sb.setLength(0);
            }

        }
        if(sb.length() > 0){   //处理没有非字母字符出现的情况
            String key = sb.toString();
            if(!set.contains(key)){
                map.put(key,map.getOrDefault(key,0)+1);
            }
        }

        int max = 0;
        String maxKey = null;
        for(Map.Entry<String, Integer> e: map.entrySet()){
            if(e.getValue() > max){
                max = e.getValue();
                maxKey = e.getKey();
            }
        }
        return maxKey;

    }

    public static void main(String[] args) {
        LeetCode0819MostCommonWord test = new LeetCode0819MostCommonWord();
        String key1 = test.mostCommonWord3("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
        String key2 = test.mostCommonWord3("Bob", new String[]{"hit"});
        System.out.println(key1); // ball
        System.out.println(key2); // Bob
    }
}
