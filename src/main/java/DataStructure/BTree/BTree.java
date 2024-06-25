package DataStructure.BTree;

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


        //向children指定索引处插入child
        void insertChild(Node child, int index){
            System. arraycopy(children, index, children, index + 1, keyNumber -index + 1);
            children[index] = child;
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
        doPut(root,key);

    }

    private void doPut(Node node, int key){
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
        }else{              //非叶子节点
            doPut(node.children[i], key);    //  继续递归插入
        }
    }

    //3. 删除

}
