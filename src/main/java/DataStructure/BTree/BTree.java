package DataStructure.BTree;

import java.awt.*;
import java.util.Arrays;

/**
 * B- 树
 */
public class BTree {

    static class Node{
        int[] keys; //多个关键字，用数组表示
        Node[]  children; //多个孩子，用数组表示
        int keyNumber; //有效关键字数目；
        boolean leaf = true; //是否是叶子节点
        int t; //最小度数（最小孩子数）

        public Node(int t){  //t>=2
            this.t = t;
            this.children = new Node[2*t];  //最多孩子数2*t， children数组大小
            this.keys = new int[2*t - 1];   //最多关键字数2*t-1， keys数组大小
        }

        public Node(int[] keys) {
            this.keys = keys;
            this.keyNumber = keys.length;
            this.children = new Node[keys.length + 1];
        }

        @Override
        public String toString(){
            return Arrays.toString(Arrays.copyOfRange(keys,0,keyNumber));
        }

        /**
         * 多路查找
         */
        Node get(int key){
            int i = 0;
            while(i < keyNumber){
                if(keys[i] == key){
                    return this;
                }
                if(keys[i] > key){
                    break;
                }
                i++;
            }
            //执行到此时，keys[i] > key 或 i== keyNumber
            //叶子节点
            if(leaf){
                return null;
            }
            //非叶子节点
            return children[i].get(key);   //去第i个孩子中递归调用查找
        }

        /**
         * 向keys指定索引处插入key, [1,3,5] => [1,3,3,5] => [1,2,3,5]
         * @param key
         * @param index
         */
        void insertKey(int key, int index){
            //copy elements to the right of the index
            System.arraycopy(keys, index, keys, index +1,keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }


        /**
         * 向children指定索引处插入child
         * @param child
         * @param index
         */
        void insertChild(Node child, int index){
            //疑问：为什么不是System. arraycopy(children, index, children, index + 1, keyNumber - index +1) ？
            System. arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        /**
         * 移除指定index处的key
         * @param index 指定索引
         * @return 被移除的key
         */
        int removeKey(int index){
            int t = keys[index];
            System.arraycopy(keys,index + 1, keys, index, --keyNumber - index);
            return t;
        }

        /**
         * 移除最左边的key
         * @return 被移除的key
         */
        int removeLeftmostKey(){
            removeKey(0);
        }

        /**
         * 移除最右边的Key
         * @return 被移除的key
         */
        int removeRightmostKey(){
            removeKey(keyNumber - 1);
        }

        /**
         * 移除指定index处的child
         * @param index 指定索引
         * @return 被移除的child
         */
        Node removeChild(int index){
            Node t = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null; // Help GC
            return t;
        }

        /**
         * 移除最左边的child
         * @return 被移除的child
         */
        Node removeLeftmostChild(){
            removeChild(0);
        }

        /**
         * 移除最右边的child
         * @return
         */
        Node removeRightmostChild(){
            removeChild(keyNumber);
        }

        /**
         * index孩子处左边的兄弟
         * @param index
         * @return
         */
        Node childLeftSibling(int index){
            return index > 0 ? children[index - 1] : null;
        }

        /**
         * index孩子处右边的兄弟
         * @param index
         * @return
         */
        Node childRightSibling(int index){
            return index == keyNumber ?  null : children[index + 1];
        }

        /**
         * 复制当前节点的所有key和child到target
         * @param target
         */
        void moveToTarget(Node target){
            int start = target.keyNumber;
            if(!leaf){
                for(int i = 0; i <= keyNumber; i++){
                    target.children[start +1] = children[i];
                }
            }
            for (int i = 0; i < keyNumber; i++){
                target.keys[target.keyNumber++] = keys[i];
            }
        }

    }

    Node root;
    int t; //树中节点的最小度数
    final int MIN_KEY_NUMBER;   //最小key的数目
    final int MAX_KEY_NUMBER;   //最大key的数目

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MIN_KEY_NUMBER = t - 1;
        MAX_KEY_NUMBER = 2 * t - 1;
    }

    //1. 是否存在
    public boolean contains(int key){
        return root.get(key) != null;
    }

    //2. 新增 or 更新
    public void put(int key){
        doPut(root,key, null,0);

    }

    private void doPut(Node node, int key, Node parent, int index){
        int i = 0;
        while(i < node.keyNumber){
            if(node.keys[i] == key){
                return; //更新（B树不允许与重复的key）
            }
            if(key < node.keys[i]){
                break;   //找到了插入位置，即为此时的i
            }
            i++;
        }
        if(node.leaf){      //如果当前是叶子节点
            node.insertKey(key,i);
            //插入后可能超过上限

        }else{              //非叶子节点
            doPut(node.children[i], key, node, i);    //  继续递归插入
            //叶子节点分裂后，其父节点也可能超过上限
        }
        if(node.keyNumber == MAX_KEY_NUMBER){
            split(node, parent, index);
        }
    }

    /**
     *
     * @param left 待分裂节点
     * @param parent 待分裂节点的父节点
     * @param index 待分裂节点的在children数组中的索引
     */
    protected void split(Node left, Node parent, int index){
        if(parent == null){     //分裂的是根节点, 需要创建新的根节点，用新的根节点取代原来的根节点
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left,0);
            this.root = newRoot;
            parent = newRoot;
        }
        //1. 创建right节点，把left中t之后的key和child移动过去
        Node right = new Node(t);   //分裂后在left节点右侧的节点（值比较大）
        right.leaf = left.leaf;   //分裂后的节点与当前节点属性一样，分裂前是叶子，分裂后也是叶子，分裂前不是叶子，分裂后也不是
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        if(!left.leaf){      //如果是非叶子节点， 要把孩子也拷贝过去
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        //2. 中间的key（t-1）插入到父节点
        int mid = left.keys[t-1];
        parent.insertKey(mid, index);   //插入到父节点的索引就是原节点的索引
        //3. right节点作为父节点的孩子
        parent.insertChild(right, index + 1);
    }

    //3. 删除

}
