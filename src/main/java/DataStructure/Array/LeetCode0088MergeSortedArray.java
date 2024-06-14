package DataStructure.Array;

import java.util.Arrays;

public class LeetCode0088MergeSortedArray {
    /**
     * 题目改编：
     *
     * 将数组a1内两个区间内(i -> leftEnd, j -> rightEnd)的有序元素合并：
     * 例
     * [1,5,6,2,4,10,11]
     * 可以视作两个有序区间
     * [1,5,6] 和 [2,4,10,11]
     * 合并后，结果仍存储于原有空间
     */


    /**
     * 方法1: 递归
     * merge（ [1,5,6] [2,4,10,11]， a2 = []){
     *     merge（ [5,6] [2,4,10,11]， a2 = [1]){
     *         merge（ [5,6] [4,10,11]， a2 = [1,2]){
     *             merge（ [5,6] [10,11]， a2 = [1,2,4]){
     *                 merge（ [6] [10,11]， a2 = [1,2,4,5]){
     *                     merge（ [] [10,11]， a2 = [1,2,4,5,6]){      //i > iEnd
     *                         return a2 = [1,2,4,5,6,10,11]
     *                     }
     *                 }
     *             }
     *         }
     *     }
     * }
     *
     */
    public static void merge1(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2, int k){

        if(i > iEnd){
            System.arraycopy(a1, j, a2, k,jEnd - j + 1);
            return;
        }
        if(j > jEnd) {
            System.arraycopy(a1, i, a2, k,iEnd - i + 1);
            return;
        }


        if(a1[i] < a1[j]){
            a2[k] = a1[i];
            merge1(a1,i+1,iEnd,j, jEnd,a2,k+1);
        }else{
            a2[k] = a1[j];
            merge1(a1,i,iEnd,j+1, jEnd,a2,k+1);
        }

    }

    /**
     * 方法2: 指针法
     *
     *          i
     * 1  5  6
     *        j
     * 2  4  10 11

     *             k
     * 1  2  4  5  6  10  11
     *
     */
    public static void merge2(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2){
        int k = 0;
        while(i <= iEnd && j <= jEnd){
            if(a1[i] < a1[j]){
                a2[k] = a1[i];
                i++;
            }else{
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if(i > iEnd){
            System.arraycopy(a1,j,a2,k,jEnd - j + 1);
        }
        if(j > jEnd){
            System.arraycopy(a1,i,a2,k,iEnd - i + 1);
        }

    }

    public static void main(String[] args) {
        int[] a1 = {1,5,6,2,4,10,11};
        int[] a2 = new int[a1.length];
        merge2(a1,0,2,3,6,a2);
        System.out.println(Arrays.toString(a2));
        System.arraycopy(a2,0,a1,0,a2.length);
        System.out.println(Arrays.toString(a1));
    }
}
