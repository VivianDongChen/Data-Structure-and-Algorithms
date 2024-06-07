package algorithms.recursion;

//递归反向打印字符串
public class ReversePrintString {
    public static void f(int n, String str){
        if(n == str.length()){
            return;
        }
        f(n+1,str);
        System.out.println(str.charAt(n));  //在递归后面调用，就是在“归”的时候打印，就是反向的

    }

    public static void main(String[] args) {
        f(0,"abcdefg");
    }
}
