package Algorithms.DivideAndConquer;

public class LeetCode_0395 {

    /*
        dddxaabaaabaacciiiiefbff 排除出现次数少于3的：x,c,e

        ddd aabaaabaa iiii fbff  排除每个字串中出现次数少于3的：b, b, b

        ddd aa aaa aa iiii f ff  排除每个字串中出现次数少于3的：aa, aa, f, ff

        ddd    aaa    iiii

        统计字符串中每个字符的出现次数，移除哪些出现次数 < k 的字符
        剩余的子串，递归做此处理，直至
             - 整个子串长度 < k (排除)
             - 子串中没有出现次数 < k 的字符
     */

    static int longestSubstring(String s, int k){
        //字串落选的情况
        if(s.length() < k){
            return 0;
        }

        int[] counts = new int[26]; //索引对应字符，值用来存储字符出现的次数
        char[] chars = s.toCharArray();  //字符串转换为字符数组，提高遍历的性能
        for(char c : chars){
            counts[c -'a'] ++;   //‘a' -> 0  'b' -> 1...
        }

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int count = counts[c-'a'];
            if(count > 0 && count < k){
                int j = i+1;    //如果遇到连续的出现次数小于k的字符， 需要定义一个指针j,找到最近的一个出现次数大于等于k的字符
                if(j < chars.length && counts[chars[j]-'a'] < k){
                    j++;
                }
                //在i、j处切分, 进入下一个递归
                return Integer.max(longestSubstring(s.substring(0,i), k), longestSubstring(s.substring(j), k));
            }
        }

        //字串入选（没有找到长度小于k的字符）
        return s.length();

    }

    public static void main(String[] args) {
        //                                         i j
        System.out.println(longestSubstring("aaaccbbb", 3)); // 3
        System.out.println(longestSubstring("dddxaabaaabaacciiiiefbff", 3));  //4
        System.out.println(longestSubstring("ababbc", 3)); // 0
        System.out.println(longestSubstring("ababbc", 2)); // 5


    }
}
