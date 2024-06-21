package DataStructure.BinarySearchTree;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 验证二叉搜索树
 */
public class LeetCode0098ValidateBinarySearchTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {};
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 实现方式1: 中序遍历 - 非递归
     */
    public boolean isValidBST1(TreeNode root){
        TreeNode p = root;
        long prev = Long.MIN_VALUE;   //代表上一个值， 初始值要足够小
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(p != null || !stack.isEmpty()){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                TreeNode pop = stack.pop();
                //处理值
                if(prev >= pop.val){   //检查上一个是否比这一个小
                    return false;
                }else{
                    prev = (long)pop.val;
                }
                p = pop.right;
            }
        }
        return true;
    }

    /**
     * 实现方式2-1：中序遍历 - 递归（全局变量记录prev） （效率低）
     */
    long prev = Long.MIN_VALUE;
    public boolean isValidBST2(TreeNode node){

        if(node == null){
            return true;
        }

        boolean a = isValidBST2(node.left);
        if(prev >= node.val){
            return false;
        }else{
            prev = (long)node.val;
        }
        boolean b = isValidBST2(node.right);

        return a && b;

    }

    /**
     * 实现方式2-2：中序遍历 - 递归（全局变量记录prev） （优化版）
     */
    public boolean isValidBST3(TreeNode node){

        if(node == null){
            return true;
        }

        boolean a = isValidBST3(node.left);
        if(!a){                           //提前结束遍历
            return false;
        }

        if(prev >= node.val){
            return false;
        }else{
            prev = (long)node.val;
        }

        return isValidBST3(node.right);

    }

    /**
     * 实现方法2-3：中序遍历 - 递归（局部变量记录perv） （将prev作为一个对象进行传递, ps:传递过程中始终是一个对象））
     * @param node
     * @return
     */
    public boolean isValidBST4(TreeNode node) {

        return doValid4(node, new AtomicLong(Long.MIN_VALUE));  //AtomicLong 可变对象
    }

    private boolean doValid4(TreeNode node, AtomicLong prev){

        if(node == null){
            return true;
        }

        boolean a = doValid4(node.left,prev);
        if(!a){
            return false;
        }

        if(prev.get() >= node.val){
            return false;
        }else{
            prev.set( (long)node.val);
        }

        return doValid4(node.right, prev);

    }

    /*
       能否只判断父亲比左孩子大，比右孩子小？

                  4
                /   \
               2     6
             /   \
            1     3
      答案： 不能

              -∞  4  +∞
                /   \
            -∞ 2 4 4 6 +∞
                   /   \
               4  3 6 6 7 +∞

       需要加上下限

     */

    /**
     * 实现方法3 - 上下限递归实现
     */
    public boolean isValidBST5(TreeNode root){
        return doValid5(root, Long.MIN_VALUE,Long.MAX_VALUE);

    }
    private boolean doValid5(TreeNode node, long min, long max){
        if(node == null){
            return true;
        }
        if(node.val >= max || node.val <= min){
            return false;
        }
        return doValid5(node.right, node.val,max) && doValid5(node.left,min,node.val);

    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4,
                                       new TreeNode(2,new TreeNode(1),new TreeNode(3)),
                                       new TreeNode(6));
        System.out.println(new LeetCode0098ValidateBinarySearchTree().isValidBST5(root1));

        TreeNode root2 = new TreeNode(4,
                                       new TreeNode(2),
                                       new TreeNode(6,new TreeNode(3),new TreeNode(7)));
        System.out.println(new LeetCode0098ValidateBinarySearchTree().isValidBST5(root2));

        TreeNode root3 = new TreeNode(1,
                                       new TreeNode(1),
                                 null);
        System.out.println(new LeetCode0098ValidateBinarySearchTree().isValidBST5(root3));

        TreeNode root4 = new TreeNode(3,
                                  null,
                                       new TreeNode(4,
                                                     new TreeNode(5),
                                               null));
        System.out.println(new LeetCode0098ValidateBinarySearchTree().isValidBST4(root4));
    }
}
