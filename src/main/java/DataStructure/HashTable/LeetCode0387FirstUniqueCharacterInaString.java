package DataStructure.HashTable;

import java.util.HashMap;

/**
 * 字符串中的第一个不重复的字符
 * s 中仅包含小写字符
 */
public class LeetCode0387FirstUniqueCharacterInaString {

    /*
        输入: s = "leetcode"
        l t c o d e
        输出: 0

        输入: s = "loveleetcode"
        输出: 2

        输入: s = "aabb"
        输出: -1
     */

    /**
     * 方法1: 字符打散放入int[26]
     */
    public int firstUniqChar1(String s) {
        int[] key = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            key[ch -'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = chars[i];
            if (key[ch -'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法2: 使用HashMap
     */
    public int firstUniqChar2(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            map.put(ch, map.getOrDefault(ch,0)+1);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = chars[i];
            if (map.get(ch) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode0387FirstUniqueCharacterInaString().firstUniqChar2("leetcode"));
        System.out.println(new LeetCode0387FirstUniqueCharacterInaString().firstUniqChar2("loveleetcode"));
        System.out.println(new LeetCode0387FirstUniqueCharacterInaString().firstUniqChar2("aabb"));

    }

}
