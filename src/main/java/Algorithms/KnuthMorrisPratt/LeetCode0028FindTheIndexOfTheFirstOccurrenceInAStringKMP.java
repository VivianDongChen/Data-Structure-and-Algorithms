package Algorithms.KnuthMorrisPratt;

import java.util.Arrays;

/**
 * 字符串匹配 - Knuth Morris Pratt算法
 */
public class LeetCode0028FindTheIndexOfTheFirstOccurrenceInAStringKMP {

    static int strStr(String str1, String str2){
        char[] origin = str1.toCharArray();
        char[] pattern = str2.toCharArray();
        int[] lsp = lsp(pattern);
        int i = 0;
        int j = 0;
        /*
            1. 匹配成功，i++ j++，直到 j==模式字符串长度
            2. 匹配失败
                j != 0 跳过最长前后缀字符，继续匹配
                j == 0 则 i++
         */
        while(pattern. length - j <= origin.length - i){
            if(origin[i] == pattern[j]){
                i++;
                j++;
            }else if( j == 0){ //j还没有往前走，就发现不匹配
                i++;
            }else{
                j = lsp[j-1]; //数组lsp的值对应最长前后缀的长度，索引对应的是j-1
            }
            if(j == pattern.length){  //找到解
                return i - j;
            }
        }
        return -1;
    }


    /**
     * 求数组的最长前后缀
     * @param pattern
     * @return
     */
    static int[] lsp(char[] pattern){
        /*
           匹配失败后j无需跳转到0， 而是跳转到前面匹配成功的字符串的最长公共子串的后面的位置（这个公共子串是origin的后缀和pattern的前缀），
           使用一个数组来存放这个公共子串的数据：
           最长后缀数组：只跟pattern字符串有关
           1. 索引： 使用了pattern字符串前j个字符串 - 1
           2。 值：最长前后缀的长度（恰好是匹配失败后j要跳转的位置））
        */
        return new int[]{0,0,1,2,3,0,1};  //假设已经求出了这个数组
    }

    public static void main(String[] args) {
        System.out.println(strStr("ababababcabacacababaca", "ababaca"));
        System.out.println("ababababcabacacababaca".indexOf("ababaca"));
//        System.out.println(Arrays.toString(lps("ababaca".toCharArray())));
    }
}
