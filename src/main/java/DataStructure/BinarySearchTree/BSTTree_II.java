package DataStructure.BinarySearchTree;

/**
 * 二叉搜索树, 泛型版本
 */

public class BSTTree_II<K extends Comparable<K>, V> {      //K的上限是Comparable<K>， 也就是说K必须是Comparable<K>的子类型， K就是可以比较的

    static class BSTNode<K,V> {
        K key;
        V value;
        BSTNode<K,V> left;
        BSTNode<K,V>  right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K,V>  left, BSTNode<K,V>  right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<K,V> root; //根节点

    /**
     * 查找关键字对应的值 - 非递归实现
     * @param key 关键字
     * @return 关键字对应的值
     */
    public V get(K key){
        BSTNode<K,V> node = root;
        while(node != null){
            /*
             -1 key < node.key
             0  key = node.key
             1  key > node.key
             */
            int result = key.compareTo(node.key);

            if(result < 0){
                node = node.left;
            }else if(result > 0){
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

