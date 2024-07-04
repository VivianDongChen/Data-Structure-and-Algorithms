package Algorithms.Sorting;

import java.util.ArrayList;

/**
 * 基数排序 - 扩展到适用其它类型的字符
 */
public class RadixSortLSD2 {

    public static void radixSort(String[] a, int length) {

        ArrayList<String>[] buckets = new ArrayList[128];  //可以放下1～127之内的字符
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = length - 1; i >= 0; i--) {

            for(String s: a){
                //直接用char在ASCII编码中对应的数字当作索引
                buckets[s.charAt(i)].add(s);
            }

            int k = 0;
            for(ArrayList<String> bucket:buckets){
                for(String s: bucket){
                    a[k++] = s;
                }
                bucket.clear();

            }
        }
    }
    public static void main(String[] args) {
        String[] phoneNumbers = new String[10];
        phoneNumbers[0] = "a3812345678";
        phoneNumbers[1] = "b3912345678";
        phoneNumbers[2] = "c3612345678";
        phoneNumbers[3] = "d3712345678";
        phoneNumbers[4] = "e3512345678";
        phoneNumbers[5] = "f3412345678";
        phoneNumbers[6] = "g5012345678";
        phoneNumbers[7] = "h5112345678";
        phoneNumbers[8] = "i5212345678";
        phoneNumbers[9] = "j5712345678";

        RadixSortLSD2.radixSort(phoneNumbers,11);
        for(String phoneNumber:phoneNumbers){
            System.out.println(phoneNumber);
        }
    }
}