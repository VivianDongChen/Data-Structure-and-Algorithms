package DataStructure.BinarySearchTree;

import DataStructure.BinaryTree.TreeNode;

/**
 * Binary Search Tree 二叉搜索树
 */

public class BSTTree1 {

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode root; //根节点

//    /**
//     * 查找关键字对应的值 - 递归实现
//     * @param key 关键字
//     * @return 关键字对应的值
//     */
//    public Object get(int key){
//        return doGet(root,key);
//    }
//
//    private Object doGet(BSTNode node, int key) {
//        if (node == null) {
//            return null;   //没找到
//        }
//
//        if (key < node.key) {
//            return doGet(node.left, key);    //向左找
//        } else if (node.key < key) {
//            return doGet(node.right, key);   //向右找
//        } else {
//            return node.value;     //找到了
//        }
//    }

    /**
     * 查找关键字对应的值 - 非递归实现
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(int key){
        BSTNode node = root;
        while(node != null){
            if(key < node.key){
                node = node.left;
            }else if(node.key < key){
                node = node.right;
            }else{
                return node.value;
            }
        }
        return null;
    }

    /**
     * 查找最小关键字对应值
     * @return 关键字对应的值
     */
    public Object min(){
        return null;
    }

    /**
     * 查找最大关键字对应的值
     * @return 关键字对应的值
     */
    public Object max(){
        return null;
    }

    /**
     * 存储关键字和对应值
     * @param key 关键字
     * @param value 值
     */
    public void put(int key, Object value){

    }

    /**
     * 查找关键字的前驱值
     * @param key 关键字
     * @return 前驱值
     */
    public Object successor(int key){
        return null;
    }

    /**
     * 查找关键字的后继值
     * @param key 关键字
     * @return 后继值
     */
    public Object predecessor(int key){
        return null;
    }

    /**
     * 根据关键字删除
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public Object delete(int key){
        return null;
    }




}
