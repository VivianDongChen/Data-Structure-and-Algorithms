package Algorithms.Recursion.SingleRecursion;

//递归求和 n + n-1,... + 1
public class Sum {

    //f(n) = f(n-1) + n

    public static long sum(long n){
        if(n == 1){
            return 1;
        }

        return sum(n-1) + n;
    }

    public static void main(String[] args) {
//        System.out.println(sum(30000));  //出现爆栈问题

        //解决爆栈的方法：将递归改为循环
        long n = 100000000;
        long sum = 0;
        for (long i = n; i >= 1; i--) {
            sum += n;
        }
        System.out.println(sum);
    }


}
