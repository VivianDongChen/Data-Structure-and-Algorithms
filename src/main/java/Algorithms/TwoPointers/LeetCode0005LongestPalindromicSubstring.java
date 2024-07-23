package Algorithms.TwoPointers;

/**
 * 最长回文子串
 */
public class LeetCode0005LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("a"));
        System.out.println(longestPalindrome("bccbcbabcbafa"));
    }

    static int left; // i
    static int right; // j
    static String longestPalindrome(String s){
        left = 0;
        right = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length -1; i++) {
            extend(chars, i, i); //一个字符作为中心点 （回文串包含奇数个字符）
            extend(chars,i, i+1);//两个字符作为中心点（回文串包含偶数个字符）
        }

        return new String(chars, left, right - left + 1);
    }



    /**
     * 中间开花的防止找回文子串
     * @param chars 待查找字符数组
     * @param i 回文字符串左边界指针
     * @param j 回文字符串右边界指针
     */
    static void extend(char[] chars, int i, int j){
        while(i >= 0 && j < chars.length && chars[i] == chars[j]){ //如果是回文，扩大回文范围
            i--;
            j++;
        }
        i++;
        j--;
        if(j - i > right - left){
            left = i;
            right = j;
        }
    }

}
