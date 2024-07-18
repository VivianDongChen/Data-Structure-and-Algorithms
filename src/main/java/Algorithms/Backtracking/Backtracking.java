package Algorithms.Backtracking;

import java.util.LinkedList;

/**
 * 回溯
 *     程序在运行过程中分成了多个阶段
 *     通过某些手段，将数据恢复到之前某一阶段，这就称之为回溯
 *     手段包括：
 *          方法栈
 *          自定义栈
 */
public class Backtracking {

    public static void main(String[] args) {
        rec1(1);
        rec2(1, new LinkedList<>());
        rec3(1, new LinkedList<>());
    }

    static void rec1(int n) {
        if (n == 3) {
            return;
        }
        System.out.println(n);
        rec1(n + 1);         //n作为局部变量，不能被其它方法调用修改，递归前和递归后没有变化
        System.out.println(n);
    }

    static void rec2(int n, LinkedList<String> list) {
        if (n == 3) {
            return;
        }
        System.out.println("before:" + list);
        list.push("a");
        rec2(n + 1, list);    //list在递归前和递归后不一致，原因：多次方法调用，使用的是同一个对象（集合或数组），数据没有办法恢复
        System.out.println("after:" + list);
    }

    static void rec3(int n, LinkedList<String> list) {
        if (n == 3) {
            return;
        }
        System.out.println("before:" + list);
        list.push("a");
        rec3(n + 1, list);
        list.pop(); // 手动恢复
        System.out.println("after:" + list);
    }
}
