package Algorithms.BinarySearch;

public class BinarySearch {

    /*
       Param：a -待查找的升序数组
              target - 待查找的目标值

       Returns：找到则返回索引
                找不到返回：-1
    */

    //二分查找基础版
    public static int binarySearchBasic(int[] a, int target){
        int i = 0, j = a.length -1; //设置指针和初值；
        while(i <= j) {   //i-j范围内有东西
           int m = (i + j) >>> 1;  //二进制右移，计算中间值
           if (target < a[m]) {   // 目标在中间值左边
               j = m - 1;
           } else if(a[m] < target){   //目标在中间值右边
               i = m + 1;
           } else{    //目标等于中间值
               return m;
           }
        }
        return -1;
    }

    // 二分查找改动版
    public static int binarySearchAlternative(int[] a, int target){
        int i = 0, j = a.length;  //第一处改动
        while(i < j) {            //第二处改动
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;            //第三处改动
            } else if(a[m] < target){
                i = m + 1;
            } else{
                return m;
            }
        }
        return -1;
    }

    // 二分查找平衡版
    public static int binarySearchBalanced(int[] a, int target){
        int i = 0, j = a.length;  // j是边界， 不是目标
        while(1 < j - i) {  // j-i 是待查找元素的个数， 只剩一个元素的时候退出循环
            int m = (i + j) >>> 1;
            if (target < a[m]) { // 目标在中间值左侧
                j = m;
            } else{  //目标在中间值处或右侧
                i = m;
            }
        }

        if(a[i] == target){   //判断i这个元素是不是目标值
            return i;
        }else{
            return -1;
        }

}

    //二分查找Leftmost1
    public static int binarySearchLeftmost1(int[] a, int target){
        int i = 0, j = a.length -1;
        int candidate =  -1;
        while(i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if(a[m] < target){
                i = m + 1;
            } else{
                // 记录候选位置
                candidate = m;
                j = m - 1;

            }
        }
        return candidate;
    }

    //二分查找Rightmost1
    public static int binarySearchRightmost1(int[] a, int target){
        int i = 0, j = a.length -1;
        int candidate =  -1;
        while(i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if(a[m] < target){
                i = m + 1;
            } else{
                // 记录候选位置
                candidate = m;
                i = m + 1;

            }
        }
        return candidate;
    }

    //二分查找Leftmost2
    public static int binarySearchLeftmost2(int[] a, int target){
        int i = 0, j = a.length -1;
        while(i <= j) {
            int m = (i + j) >>> 1;  //>>   >
            if (target <= a[m]) {
                j = m - 1;
            } else{
                i = m + 1;
            }
        }
        return i;  //返回大于等于目标的最靠左索引
    }

    //二分查找Rightmost2
    public static int binarySearchRightmost2(int[] a, int target){
        int i = 0, j = a.length -1;
        while(i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else{
                i = m + 1;

            }
        }
        return i - 1; //返回小于等于目标的最靠右索引
    }

}
