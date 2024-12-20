package DataStructure.HashTable;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 */
public class LeetCode_0003 {
    /*
       思路：

       给定一个字符串 s, 请你找出其中不含重复字符的最长子串的长度
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

    /**
     * 字符串 s 由英文字母、数字、符号和空格组成，这些字符一共128个，并且它们在内部表示为不重复的128个整数。
     * 因此可以使用一个长度为128的数组来存储这些字符最近出现的位置。这个数组中的索引代表字符的 ASCII 值，
     * 值是字符在字符串 s 中对应的索引 + 1。
     */
    public int lengthOfLongestSubstring2(String s) {
        int[] index = new int[128];
        int maxLength = 0;
        int begin = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (index[ch] > begin) {      //即 ch之前的索引 >= begin，就是说ch有重复，如果没有重复，index[ch]在赋值前应为0
                begin = index[ch];    //begin指针 = 之前的索引 + 1
            }
            index[ch] = end + 1;     //更新当前字符的最新位置为end + 1，使用end + 1避免与默认值 0 冲突。
            maxLength = Math.max(maxLength, end - begin + 1);
        }

        return maxLength;
    }



    public int lengthOfLongestSubstring3(String s){
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
        System.out.println(new LeetCode_0003().
                lengthOfLongestSubstring2("abba"));


    }
}
