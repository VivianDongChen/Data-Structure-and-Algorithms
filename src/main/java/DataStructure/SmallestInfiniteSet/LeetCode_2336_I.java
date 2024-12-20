package DataStructure.SmallestInfiniteSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 最小值无限集合（正整数：1，2，3，4，5...)
 * 无限集合无法显式存储，且初始化整个集合既浪费内存也没有实际意义。
 * 当前设计通过 cur 动态生成无限部分。使用 s 显式存储重新添加的数字，结合实现无限集合的功能。
 * 结果：内存使用效率高，逻辑简单，且能够按需访问最小值。
 */
public class LeetCode_2336_I {

    int cur;       //无限集合当前的最小值
    Set<Integer> s;   //只存储addBack的元素

    public LeetCode_2336_I(){
        cur = 1;     //初始化最小值，正整数无限集合的最小值是1
        s = new HashSet();     //初始化s为一个空的HashSet
    }

    public int popSmallest() {
        if(!s.isEmpty()){       //如果s不为空，最小值在s里面
            int smallest = Collections.min(s);
            s.remove(smallest);
            return smallest;
        }else{     //如果s为空，那么说明没有addback元素，所以最小值就是cur
            cur++;   //pop之后，cur要+1
            return cur - 1;
        }
    }

    public void addBack(int num){
        if(cur > num){  //cur小于num，num就还在无限集合中，所以不需要add
            s.add(num);
        }
    }



    public static void main(String[] args) {
        LeetCode_2336_I test = new LeetCode_2336_I();
        test.addBack(2);
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
        test.addBack(1);
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
        System.out.println(test.popSmallest());
    }
}
