package Algorithms.DivideAndConquer;

/**
 * 平方根整数部分 - 分治
 */
public class LeetCode_0069 {

    /*
        99 = 9.?

        1*1 = 1         10 次
        2*2 = 4
        ...
        9*9 = 81
        10*10 = 100


        x = 99

        i j  m    m^2
        1 99 50   >99     7次
        1 49 25   >99
        1 24 12   >99
        1 11 6    <99
        7 11 9    <99
        10 11 10  >99
        10 10 10  >99
        10 9      退出 (i > j)
     */


    static int mySqrt1(int x){
        int i = 1;
        int j = x;
        int r = 0;
        /*
               整个遍历过程中的i和j是逐渐缩小范围，逼近x的平方根的整数部分。
               当循环结束时，i > j，这表示搜索区间已经被完全遍历过了，没有找到精确等于x的平方根。
               此时，返回的结果r是最接近x的平方根的整数部分。
         */
        while( i <= j){
           int m =  (i + j) >>> 1;
           int mm = m * m;  //测试用例2147395599这里会越界， 解决方式1：将int改为long， 解决方式2: 优化代码: mySqrt2
           if(mm == x){
               return m;
           }else if(mm < x){
               i = m + 1;
               r = m; //记录平方比x小的那个数的最大值
           }else{
               j = m - 1;
           }
        }
        return r;
    }

    /**
     * 代码优化
     */
    static int mySqrt2(int x){
        int i = 1;
        int j = x;
        int r = 0;
        while( i <= j){
            int m =  (i + j) >>> 1;
            if(m <= x / m){   //乘法变除法
                i = m + 1;
                r = m;
            }else{
                j = m - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt2(99)); // 9
        System.out.println(mySqrt2(1)); // 1
        System.out.println(mySqrt2(2)); // 1
        System.out.println(mySqrt2(4)); // 2
        System.out.println(mySqrt2(5)); // 2
        System.out.println(mySqrt2(8)); // 2
        System.out.println(mySqrt2(9)); // 3
        System.out.println(mySqrt2(2147395599));
    }
}
