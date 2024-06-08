package algorithms.recursion.multiRecursion;

import java.util.Arrays;

//递归求斐波那契第n项
public class Fibonacci {
    public static int fibonacciBasic(int n){
        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        int x = fibonacciBasic(n - 1);
        int y = fibonacciBasic(n - 2);
        return x + y;

    }

    public static void main(String[] args) {
        int f = fibonacciBasic(8);
        System.out.println(f);
    }

    /**
     * 使用Memorization（记忆法，也称备忘录，剪枝法）改进
     * 改进后的时间复杂度： O（n), 空间复杂度：O（n)
     * @param n - 第n项
     * @return 第n项的值
     */
    public static int fibonacciMemorization (int n){

        int[] cache = new int[n+1]; //定义一个数组，用来存储fibonacci各项的值
        Arrays.fill(cache,-1); //给数组填充上初始值 [-1，-1，-1，-1，-1]
        cache[0] = 0;  //[0，1，-1，-1，-1]
        cache[1] = 1;
        return f (n,cache);

    }

    public static int f (int n, int[] cache){
        if(cache[n] != -1){
            return cache[n];
        }
        int x = f (n - 1, cache);
        int y = f (n - 2, cache);
        cache[n] = x + y; //[0,1,?,-1,-1]  存入数组
        return cache[n];

    }


}
