package DataStructure.HashTable;

import java.util.Arrays;

/**
 * 判断两个单词是否为字母异位词
 * 小写字母组成
 */
public class LeetCode0242ValidAnagram {

    /**
        方法1. 拿到字符数组，排序后比较字符数组
     */
    public boolean isAnagram1(String s, String t){
        return Arrays.equals(getSortedArray(s),getSortedArray(t));
    }

    private static char[] getSortedArray(String s){
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return a;
    }

    /**
        方法2. 字符打散放入 int[26]，比较数组
         a                 g                 m  n           r
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

     */
    public boolean isAnagram2(String s, String t){
        return Arrays.equals(getKey2(s),getKey2(t));
    }

    private static int[] getKey2(String s){
        int[] key = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            key[ch-97]++;
        }
        return key;
    }

    /**
     方法3. 方法2的优化
     */
    public boolean isAnagram3(String s, String t){
        return Arrays.equals(getKey3(s),getKey3(t));
    }

    private static int[] getKey3(String s){
        int[] key = new int[26];
        char[] chars = s.toCharArray();
        for (char ch: chars) {
            key[ch-97]++;
        }
        return key;
    }

    public static void main(String[] args) {
        LeetCode0242ValidAnagram test = new LeetCode0242ValidAnagram();
        System.out.println(test.isAnagram3("abcdefg", "gfedcba"));
    }

}
