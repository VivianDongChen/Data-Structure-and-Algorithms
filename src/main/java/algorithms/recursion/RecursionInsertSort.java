package algorithms.recursion;

public class RecursionInsertSort {

    public static void sort(int[] a){
        insertion(a,1);   //low从index1开始，与index0比较

    }

    private static void insertion(int[] a, int low){

        if(low == a.length){       //递归结束
            return;
        }

        int t = a[low];
        int i = low -1;    //i指向low之前的位置

        while( i >= 0 && a[i] > t) {    //没有找到插入t的位置
            a[i+1] = a[i];   //i的值向后挪一个
            i--;   //i指针往前找，再与t比较
        }

        a[i+1] = t;  //找到了或者i<0(i=-1), 将t插入



        insertion(a,low+1);    //递归调用

    }
}
