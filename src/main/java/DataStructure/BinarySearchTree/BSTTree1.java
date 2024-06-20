package DataStructure.BinarySearchTree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

//    /**
//     * 查找最小关键字对应值 - 递归实现
//     * @return 关键字对应的值
//     */
//    public Object min(){
//        return doMin(root);
//    }
//
//    private Object doMin(BSTNode node){
//        if(node == null){
//            return null;
//        }
//        if(node.left == null){   //最小节点
//            return node.value;
//        }
//        return doMin(node.left);
//
//    }

    /**
     * 查找最小关键字对应值 - 非递归实现
     * @return 关键字对应的值
     */
    public Object min(){
        return doMin(root);
    }
    private Object doMin(BSTNode node){
        if(node == null){
            return null;
        }
        BSTNode p = node;
        while(p.left != null){
            p = p.left;
        }
        return p.value;
    }

//    /**
//     * 查找最大关键字对应的值  - 递归实现
//     * @return 关键字对应的值
//     */
//    public Object max(){
//        return doMax(root);
//    }
//
//    private Object doMax(BSTNode node){
//        if(node == null){
//            return null;
//        }
//        if(node.right == null) {
//            return node.value;
//        }
//        return doMax(node.right);
//    }

    /**
     * 查找最大关键字对应的值  - 非递归实现
     * @return 关键字对应的值
     */
    public Object max(){
        return doMax(root);
    }

    private Object doMax(BSTNode node){
        if(node == null){
            return null;
        }
        BSTNode p = node;
        while(p.right != null){
            p = p.right;
        }
        return p.value;
    }

    /**
     * 存储关键字和对应值
     * @param key 关键字
     * @param value 值
     */
    public void put(int key, Object value){
        //1. key有： 更新
        //2. key没有：新增
        BSTNode node = root;
        BSTNode parent = null;   //新增节点的父节点
        while(node != null){
            parent = node;  //记录父节点
            if(key < node.key){
                node = node.left;
            }else if(node.key < key){
                node = node.right;
            }else{
                //1. 找到key,更新
                node.value = value;
                return;
            }
        }
        //2.没有找到key, 新增
          //parent为空：树为空, 将新节点作为根节点
        if(parent == null){
            root = new BSTNode(key,value);
            return;
        }
          //parent不为空，新增节点
        if(key < parent.key){
            parent.left = new BSTNode(key,value);
        }else{
            parent.right = new BSTNode(key,value);
        }
    }

    /**
     * 查找关键字的后继（后任）值
     * @param key 关键字
     * @return 后任值
     */
    public Object successor(int key){
        BSTNode p = root;
        BSTNode ancestorFromRight = null;  //记录自右而来的祖先
        while(p != null){
            if(key < p.key){
                ancestorFromRight = p;
                p = p.left;
            }else if(p.key < key){
                p = p.right;
            }else{
                break;
            }
        }
        //没有找到节点, 那么它的后任也不存在
        if(p == null){
            return null;
        }
        //找到节点, 情况1: 节点有右子树，此时前任就是右子树的最小值
        if(p.right != null){
            return doMin(p.right);
        }
        //找到节点，情况2: 节点没有右子树，离它最近的，自右而来的祖先就是后任（如果有的话）
        return ancestorFromRight != null ? ancestorFromRight.value:null;
    }

    /**
     * 查找关键字的前驱（前任）值
     * @param key 关键字
     * @return 前任值
     */
    public Object predecessor(int key){
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;  //记录自左而来的祖先
        while(p != null){
            if(key < p.key){
                p = p.left;
            }else if(p.key < key){
                ancestorFromLeft = p;
                p = p.right;
            }else{
                break;
            }
        }
        //没有找到节点, 那么它的前任也不存在
        if(p == null){
            return null;
        }
        //找到节点, 情况1: 节点有左子树，此时前任就是左子树的最大值
        if(p.left != null){
            return doMax(p.left);
        }
        //找到节点，情况2: 节点没有左子树，离它最近的，自左而来的祖先就是前任（如果有的话）
        return ancestorFromLeft != null ? ancestorFromLeft.value:null;
    }

    /**
     * 根据关键字删除 - 非递归
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public Object delete1(int key){
        BSTNode p = root;
        BSTNode parent = null;   //记录目标节点的父节点
        while(p != null){
            if(key < p.key){
                parent = p;
                p = p.left;
            }else if(p.key < key){
                parent = p;
                p = p.right;
            }else{
                break;
            }
        }
        //没有找到节点
        if(p == null){
            return null;
        }
        //找到节点，删除操作
        if(p.left == null ){            //情况1: 被删除节点没有左孩子，将右孩子托孤给parent 或者 情况3: 左右孩子都没有
            shift(parent, p, p.right);
        }else if(p.right == null ){     //情况2: 被删除节点没有右孩子，将左孩子托孤给parent 或者 情况3: 左右孩子都没有
            shift(parent, p, p.left);
        }else{                          //情况4： 被删除的节点左右孩子都有
            //4.1 被删除节点右子树找后任(或者左子树找前任也可以，这里以后任为例）
            BSTNode s = p.right;
            BSTNode sParent = p; //后继节点的父亲
            while(s.left != null){
                sParent = s;
                s = s.left;
            }
            //后继节点即为s
            if(sParent != p){   //删除节点和后继节点不相邻
                //4.2 删除节点和后继节点不相邻，需要处理后继的后事
                shift(sParent, s, s.right);   //s不可能有左孩子
                s.right = p.right;  //顶上去的后继节点的右孩子是删除节点的右孩子
            }
            //4.3 后继取代被删除节点
            shift(parent,p,s);  //后继节点被托孤
            s.left = p.left;    //顶上去的后继节点的左孩子是删除节点的左孩子
        }

        return p.value;

    }

    /**
     * 托孤方法
     * @param parent 被删除节点的父亲
     * @param deleted 被删除的节点
     * @param child 被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child){
        if(parent == null){   //被删除的是根节点
            root = child;
        }else if(deleted == parent.left){   //被删除的是左孩子
            parent.left = child;
        }else{                              //被删除的是右孩子
            parent.right = child;
        }

    }

    /**
     * 根据关键字删除 - 递归
     * @param key 关键字
     * @return 被删除关键字对应值
     */
    public Object delete2(int key){
        ArrayList<Object> result = new ArrayList<>();
        root = doDelete(root, key, result);
        return result.isEmpty() ? null: result.get(0);
    }

    /**
     *         4
     *       /   \
     *      2     6
     *     /       \
     *    1         7
     *
     * 根据关键字删除 - 递归
     * @param node 起点
     * @param key 关键字
     * @return 删剩下的孩子(找到）或null（没找到）
     */
    private BSTNode doDelete(BSTNode node, int key, ArrayList<Object> result){
        if(node == null){
            return null;
        }
        if(key < node.key){
            node.left = doDelete(node.left, key, result);
            return node;
        }
        if(node.key < key){
            node.right = doDelete(node.right,key, result);
            return node;
        }
        result.add(node.value);
        //情况1 - 只有右孩子
        if(node.left == null){
            return node.right;
        }
        //情况2 - 只有左孩子
        if(node.right == null){
            return node.left;
        }
        //情况3 - 有两个孩子
        BSTNode s = node.right;   //找后继节点
        while(s. left != null){
            s = s.left;
        }

        s.right = doDelete(node.right,s.key ,new ArrayList<>());
        s.left = node.left;

        return s;
    }

    /*
     *   找一个区间key对应的值
     *   思路：
     *   - 中序遍历BST：key由小到大
     *   - 反向中序遍历BST：key由大到小
     *
     *
     *             4
     *           /   \
     *          2     6
     *        /  \   / \
     *       1   3  5   7
     *
     */


    /**
     * 找 < key 的所有value - 中序遍历BST
     * @param key 关键字
     * @return 所有value
     */
    public List<Object> less(int key){     //key = 6
        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while(p != null || !stack.isEmpty()){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                BSTNode pop = stack.pop();
                //处理值
                if(pop.key < key){
                    result.add(pop.value);
                }else{
                    break;       // 一但 >= key, 后面的都 >=key 了， 就可以停止遍历了
                }
                p = pop.right;
            }
        }

        return result;
    }

    /**
     * 找 > key 的所有value - 中序遍历BST （遍历不可以提前结束，效率低）
     * @param key 关键字
     * @return 所有value
     */
    public List<Object> greater1(int key){
        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while(p != null || !stack.isEmpty()){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                BSTNode pop = stack.pop();
                //处理值
                if(pop.key > key){
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }

        return result;
    }

    /**
     * 找 > key 的所有value - 反向中序遍历BST （遍历可以提前结束，效率高））
     * @param key 关键字
     * @return 所有value
     */
    public List<Object> greater2(int key){
        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while(p != null || !stack.isEmpty()){
            if(p != null){
                stack.push(p);
                p = p.right;
            }else{
                BSTNode pop = stack.pop();
                //处理值
                if(pop.key > key){
                    result.add(pop.value);
                }else{
                    break;   // 一但 <= key, 后面的都 <=key 了， 就可以停止遍历了
                }
                p = pop.left;
            }
        }

        return result;
    }

    /**
     * 找 >= key1 && <= key2 的所有value - 中序遍历BST
     * @param key1 关键字1
     * @param key2 关键子2
     * @return 所有value
     */
    public List<Object> between(int key1, int key2){
        ArrayList<Object> result = new ArrayList<>();

        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while(p != null || !stack.isEmpty()){
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                BSTNode pop = stack.pop();
                //处理值
                if(pop.key <= key2 && key1 <= pop.key){
                    result.add(pop.value);
                }else if(pop.key > key2){
                    break;        // 一但 >= key2, 后面的都 >= key2 了， 就可以停止遍历了
                }
                p = pop.right;
            }
        }

        return result;
    }




}
