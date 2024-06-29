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

    public static void main(String[] args) {
        LeetCode0819MostCommonWord test = new LeetCode0819MostCommonWord();
        String key = test.mostCommonWord1("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
//        String key = e08.mostCommonWord("Bob", new String[]{"hit"});
        System.out.println(key); // ball
    }
}
