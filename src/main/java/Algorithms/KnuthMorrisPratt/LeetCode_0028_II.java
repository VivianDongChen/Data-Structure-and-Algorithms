package Algorithms.KnuthMorrisPratt;

import java.util.Arrays;

/**
 * 字符串匹配 - Knuth Morris Pratt算法
 */
public class LeetCode_0028_II {

    static int strStr(String str1, String str2){
        char[] origin = str1.toCharArray();
        char[] pattern = str2.toCharArray();
        int[] lsp = lps(pattern);
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
                j = lsp[j-1]; //索引j-1对应的值就是此时最长前后缀的长度
            }
            if(j == pattern.length){  //找到解
                return i - j;
            }
        }
        return -1;
    }


    /**
     * 求数组的最长前后缀（longest prefix suffix ）
     * @param pattern 数组
     * @return 最长前后缀数组
     */
    static int[] lps(char[] pattern){
        /*
           匹配失败后j无需跳转到0， 而是跳转到前面匹配成功的字符串的最长公共子串的后面的位置（这个公共子串是origin的后缀和pattern的前缀， 也就是pattern的前后缀），
           使用一个数组来存放这个子串的数据：

           最长前后缀数组：只跟pattern字符串有关
           1. 索引： 使用了pattern字符串前j个字符串 - 1
           2. 值：最长前后缀的长度（恰好是匹配失败后j要跳转的位置））

           如何找一个数组的最长前后缀：

           1.将一个数组复制一份， 这样排列：

                       j
                    a  a  a  d  e  f
                 a  a  a  d  e  f
                       i

             对于d来讲， aaa是它前面的字符串，aa是这个字符串的前缀，aa是这个字符串的后缀, 把这个字符串的长度（j+1）记录到lsp[i]， 然后j++， i++


            2. 如果 pattern[i] != pattern[j]

                2.1 j = 0, i++

                2.2 j!= 0  j回到它对应的最长前后缀之后的位置， 然后再与i进行比较

                          j
                    a  a  a  d  e  f
                 a  a  a  d  e  f
                          i

                    j = 2 lsp[2-1] = 1

                       j
                    a  a  a  d  e  f
                 a  a  a  d  e  f
                          i

                   j继续后退  j = 1 lsp[0] = 0

                    j
                    a  a  a  d  e  f
                 a  a  a  d  e  f
                          i

                   这个时候说明e之前的字串没有共同前后缀，i++

        */
        int[] lps = new int[pattern.length];    //lsp[0] = 0;
        int i = 1;
        int j = 0;
        while(i < pattern.length){
            if(pattern[i] == pattern[j]){
                lps[i] = j + 1;
                i++;
                j++;
            }else if(j == 0){
                i++;
            }else{
                j = lps[j-1];
            }

        }
        return lps;

    }

    public static void main(String[] args) {
        System.out.println(strStr("ababababcabacacababaca", "ababaca"));
        System.out.println("ababababcabacacababaca".indexOf("ababaca"));
        System.out.println(Arrays.toString(lps("ababaca".toCharArray())));
    }
}
