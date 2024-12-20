package Algorithms.KnuthMorrisPratt;

/**
 * 字符串匹配 - 两个指针（暴力匹配）
 */
public class LeetCode_0028_I {
    static int strStr(String str1, String str2){
        //转化为字符数组，以便于遍历
        char[] origin = str1.toCharArray();
        char[] pattern = str2.toCharArray();
        int i = 0;
        while (i <= origin.length - pattern.length) {
            int j;
            for (j = 0; j < pattern.length; j++){
                if(origin[i+j] != pattern[j]){
                    break;
                }
            }
            if(j == pattern.length){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("aaacaaab", "aaab"));
    }
}
