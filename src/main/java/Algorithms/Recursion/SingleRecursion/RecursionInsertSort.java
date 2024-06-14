package Algorithms.Recursion.SingleRecursion;

public class RecursionInsertSort {

    public static void sort1(int[] a) {
        insertion1(a, 1);   //low从index1开始，与index0比较

    }

    private static void insertion1(int[] a, int low) {

        if (low == a.length) {       //递归结束
            return;
        }

        int t = a[low];     //low是未排序区域的左边界
        int i = low - 1;    //i指向low之前的位置（i是未排序区域的右边界）

        //这样的操作比swap效率高（赋值次数少）
        while (i >= 0 && a[i] > t) {    //没有找到插入t的位置
            a[i + 1] = a[i];   //i的值向后挪一个，空出插入位置
            i--;   //i指针往前找，再与t比较
        }

        a[i + 1] = t;  //找到了或者i<0(i=-1), 将t插入


        insertion1(a, low + 1);    //递归调用

    }

    //代码优化
    public static void sort2(int[] a) {
        insertion2(a, 1);

    }

    private static void insertion2(int[] a, int low) {

        if (low == a.length) {
            return;
        }

        int t = a[low];
        int i = low - 1;

        while (i >= 0 && a[i] > t) {
            a[i + 1] = a[i];
            i--;
        }

        if (i + 1 != low) {    //如果low的位置没有变化过（没有进入过循环），没必要再赋值一次
            a[i + 1] = t;
        }


        insertion1(a, low + 1);

    }
}
