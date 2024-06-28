package DataStructure.HashTable;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 */
public class LeetCode0003LongestSubstringWithoutRepeatingCharacters {
    /*
       思路：

       给定一个字符串 s, 请你超出其中不含重复字符的最长字串的长度
       abcabcbb  3
       a
       ab
       abc
       bca a重复，从不重复的b开始
       cab b重复，从不重复的c开始
       abc
       cb
       b

       bbbb   1
       b

       pwwkew  3
       p
       pw
       w
       wk
       wke
       kew

       [(a,3) (b,1) (c,2)]

         b
           e
        abcabcbb

       要点：
        1.用 begin 和 end 表示子串开始和结束位置，b、e之间始终是不含重复字符的字串
        2.用hash表记录所有e走过的值和其索引，遇到重复的更新其索引即可
        3.从左向右查看每个字符, 如果:
         - 没遇到重复字符，调整 e
         - 遇到重复的字符，调整 b
         - 将当前字符放入 hash 表
        4.end - begin + 1 是当前子串长度， 求最大值即可

     */
    
    public int lengthOfLongestSubstring1(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int begin = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if(map.containsKey(ch) && map.get(ch) >= begin){      //有重复
                begin = map.get(ch)+1;  //begin移动到第一个重复的字符的后面
                map.put(ch,end);
            }else{    //无重复
                map.put(ch,end);
            }
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s){
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            for (int i = begin; i < end; i++) {
                if(s.charAt(i) == s.charAt(end)){
                    begin = i + 1;
                    break;
                }
            }
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode0003LongestSubstringWithoutRepeatingCharacters().
                lengthOfLongestSubstring2("abba"));


    }
}
