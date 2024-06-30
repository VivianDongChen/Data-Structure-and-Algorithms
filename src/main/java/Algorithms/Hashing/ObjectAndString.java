package Algorithms.Hashing;

/**
 *  字符串的哈希算法
 */
public class ObjectAndString {
    public static void main(java.lang.String[] args) {
        // 生成哈希码的方法1: 调用Object的方法hashCode()
        for (int i = 0; i < 10; i++) {
            Object obj = new Object();
            System.out.println(obj.hashCode());  //不同对象调用hashcode(), 生成的哈西码不同

        }

        System.out.println("------------------------");

        Object obj = new Object();
        for (int i = 0; i < 10; i++) {
            System.out.println(obj.hashCode());  //同一对象调用hashcode(), 生成的哈西码相同
        }

        //生成哈希码的方法2: 重写方法hashCode()
        java.lang.String s1 = "abc";
        java.lang.String s2 = new java.lang.String("abc");

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        //原则：String重写了hashCode()，值相同的字符串生成相同的hash码，尽量让值不同的字符串生成不同的hash码
        /*
          对于abc a*100 + b * 10 + c
          对于bac b*100 + a * 10 + c

          hash = hash * 10 + c => 换成乘以一个比较大的质数，这样冲突的可能性更小  => hash = hash * 31 + c
          => hash = hash * 32 - hash + c => hash = hash * 2^5 - hash + c  => hash = (hash << 5) - hash + c
         */
        int hash = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            System.out.println((int) c);
            hash = (hash << 5) - hash + c;
        }
        System.out.println(hash);

    }


}
