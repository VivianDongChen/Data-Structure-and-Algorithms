package Algorithms.Sorting.NonComparisonBasedSorting;

import java.util.ArrayList;

/**
 * 基数排序 最低有效数字 LSD（Least significant digit）- 仅针对数字字符
 */
public class RadixSortLSD1 {


        /*
            思路：String[] phoneNumbers = new String[6];
                phoneNumbers[0] = "158";
                phoneNumbers[1] = "151";
                phoneNumbers[2] = "235";
                phoneNumbers[3] = "138";
                phoneNumbers[4] = "139";
                phoneNumbers[5] = "159";

            0
            1 151
            2
            3
            4
            5 135
            6
            7
            8 158 138
            9 139 159

            151 135 158 138 139 159 按个位排

            0
            1
            2
            3 135 138 139
            4
            5 151 158 159
            6
            7
            8
            9

            135 138 139 151 158 159 按十位排

            0
            1 135 138 139 151 158 159
            2
            3
            4
            5
            6
            7
            8
            9

            135 138 139 151 158 159 按百位排
         */

    public static void radixSort(String[] a, int length) {
        //1. 准备桶
        ArrayList<String>[] buckets = new ArrayList[10];  //大小为10的数组，每个里面有一个ArrayList<String>
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        //4. 进行多轮按位桶排序。从最低位循环到最高位， length为数字长度， 最低位的索引就是length -1
        for (int i = length - 1; i >= 0; i--) {

            //2.按照索引位置将相应的字符串放入合适的桶
            for(String s: a){
                //char映射到索引位置: ‘0’ 在编码中对应-> 48, '1' -> 49， 所以索引位置是s.charAt(2) -48 即 s.charAt(2) - ‘0’
                buckets[s.charAt(i) -'0'].add(s);
            }

            //3. 重新取出排好序的字符串，放回到原数组
            int k = 0;
            for(ArrayList<String> bucket:buckets){
                for(String s: bucket){
                    a[k++] = s;
                }
                bucket.clear(); //同时清空这个桶，以备下一个循环使用

            }
//            System.out.println(Arrays.toString(a));
        }

    }
    public static void main(String[] args) {
        String[] phoneNumbers = new String[10];
        phoneNumbers[0] = "13812345678";  // int long
        phoneNumbers[1] = "13912345678";
        phoneNumbers[2] = "13612345678";
        phoneNumbers[3] = "13712345678";
        phoneNumbers[4] = "13512345678";
        phoneNumbers[5] = "13412345678";
        phoneNumbers[6] = "15012345678";
        phoneNumbers[7] = "15112345678";
        phoneNumbers[8] = "15212345678";
        phoneNumbers[9] = "15712345678";

        RadixSortLSD1.radixSort(phoneNumbers,11);
        for(String phoneNumber:phoneNumbers){
            System.out.println(phoneNumber);
        }
    }
}
