package Algorithms.Sorting.ComparisonBasedSorting;

import java.util.Arrays;

/**
 * 归并排序- 自下而上
 * 规律：不需要进行”分“，有序数组区间的宽度规律是：从1个节点到2个节点到4个节点...
 * 最好：O（nlogn)  最坏： O (nlogn)   平均：O (nlogn)   空间：O（n）
 */
public class MergeSortBottomUp {

    /**
     * 合并有序数组
     */
    public static void merge(int[] a1, int i, int iEnd,int j, int jEnd, int[] a2){
        int k = i;
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
            System.arraycopy(a1,i,a2,k,iEnd - i +1);
        }

    }

    public static void sort(int[] a1){
        int n = a1.length;
        int[] a2 = new int[n];
        //width代表有序区间的宽度，取值依次为1，2，4...
        for (int width = 1; width < n; width *= 2) {
            //[left, right]分别代表待合并区间的左右边界
            for (int left = 0; left < n; left += 2 * width) {
                int right = Math.min(left + 2 * width - 1, n-1); //右边界是下一个左边界-1，并不能超过数组最后一个节点、
//                System.out.printf("width %d[%d, %d]%n", width,left,right);  //%d整数占位符
                int m = Math.min(left + width - 1, n-1); //中间值
                merge(a1,left,m,m+1,right,a2);
                System.arraycopy(a2, left, a1, left, right - left + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9,3,7,2,8,5,1,4,6};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
