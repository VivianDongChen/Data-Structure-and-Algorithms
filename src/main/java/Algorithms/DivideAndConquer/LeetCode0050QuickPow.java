package Algorithms.DivideAndConquer;

/**
 * 快速幂 - 分治
 */
public class LeetCode0050QuickPow {

    /**
     * 传统做法 O（n)
     * @param x 基数
     * @param n 指数
     * @return 幂
     */
    static double myPow1(double x, int n) {
        double mul = 1;
        for (int i = 0; i < n; i++) {
            mul *= x;
        }
        return mul;
    }

    /**
     * 快速幂 - 为了处理负数幂，又抽出一个方法
     * @param x 基数
     * @param n 指数
     * @return 幂
     */
    static double myPow2(double x, long n){
        long p = n;  //
        if(n < 0){
            p = -n;    //测试用例中的-2147483648为最小负整数，它的-超过了最大正整数2147483647， 解决办法：将n变为long
        }
        double r = myPow2Positive(x,p);
        return n > 0 ? r : 1/r;
    }

    /**
     * 快速幂 - 处理n >= 0的情况
     * @param x 基数
     * @param n 指数
     * @return 幂
     */
    static double myPow2Positive(double x, long n) {

        /*

                           2^16                 65536  乘四次
                   /                  \
                 2^8                  2^8        256*256  乘三次
              /      \              /      \
             2^4     2^4           2^4     2^4    16*16  乘二次
            /  \     /  \         /  \     /  \
          2^2 2^2  2^2 2^2       2^2 2^2  2^2 2^2   4*4  乘一次
          /\  /\   /\  /\       /\  /\    /\  /\
         2 2 2 2  2 2 2 2      2 2 2 2   2 2 2  2    2*2


                  2^10
              /         \
            2^5         2^5    2*2^4  奇数的幂还要多乘一个2
           /  \        /  \
        2 2^2 2^2    2 2^2 2^2
         / \  / \     / \  / \
        2  2  2  2   2  2  2  2

     */
        if(n == 0){     //解决n为0的问题
            return 1.0;
        }
        if(n == 1){
            return x;
        }
        double y = myPow2Positive(x,n / 2);

        /*
        另一种判断奇偶数的方法 - 通过二进制来判断奇偶数（尾数位为0为偶数，尾数位为1为奇数）
            1   001
            3   011
            5   101
            7   111
                001 &  通过按位于001来得到尾数位
                -----
                001

            2   010
            4   100
            6   110
            8  1000
                001 &
                -----
                000

            二进制按位与运算规律：
            1 & 1 = 1
            1 & 0 = 0
            0 & 1 = 0
            0 & 0 = 0

            任何数与0按位于结果为0
            任何数与1按位于结果为其本身

         */

//        if(n % 2 == 0){ //偶数
//            return y * y;
//        }else{  //奇数
//            return x * y * y ;
//        }

        if((n & 1) == 0){ //偶数
            return y * y;
        }else{  //奇数
            return x * y * y ;
        }
    }

    public static void main(String[] args) {
        System.out.println(myPow2(2, 16));  // 65536
        System.out.println(myPow2(2, 10));  // 1024
        System.out.println(myPow2(2, 0)); // 1.0             //任何数的0次方都等于1
        System.out.println(myPow2(2, -2)); // 0.25    2^-2 = 1/2^2   //负数幂代表倒数的幂。例如，a^-b, 即（1/a）^b

        System.out.println(myPow2(2, -2147483648));
//        System.out.println(myPow(2.1, 3)); // 9.261
    }
}
