package DataStructure.RedBlackTree;

import static DataStructure.RedBlackTree.RedBlackTree.Color.BLACK;
import static DataStructure.RedBlackTree.RedBlackTree.Color.RED;

/**
 * 红黑树
 */
public class RedBlackTree {

    enum Color{
        RED, BLACK;

    }

    Node root;

    static class Node{
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;      // 父节点
        Color color = RED;   //颜色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        //是否是左孩子
        boolean isLeftChild(){
            return parent != null && parent.left == this;
        }


        //叔叔
        Node uncle(){
            if(parent == null || parent.parent == null){   //没有父亲或没有爷爷
                return null;
            }
            if(parent.isLeftChild()){
                return parent.parent.right;
            }else{
                return parent.parent.left;
            }
        }


        //兄弟
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }


    //判断红，黑
    boolean isRed(Node node){
        return node != null && node.color == RED;
    }

    boolean isBlack(Node node){
//        return !isRed(node);
        return node == null || node.color == BLACK;

    }

    //右旋 1. parent的处理   2. 旋转后新根的父子关系
    private void rightRotate(Node pink){
        Node parent = pink.parent;
        Node yellow = pink.left;
        Node green = yellow.right;

        if (green != null){
            green.parent = pink;
        }
        yellow.right =  pink;
        pink.left = green;

        yellow.parent = parent;
        pink.parent = yellow;
        if(parent == null){
            root = yellow;
        }else if(parent.left == pink) {
            parent.left = yellow;
        }else{
            parent.right = yellow;
        }




    }


    //左旋
    private void leftRotate(Node pink){
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;

        if (green != null) {
            green.parent = pink;
        }
        yellow.left = pink;
        pink.right = green;

        yellow.parent = parent;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }

    }

    /**
     * 新增或更新
     * 正常增，遇到红红不平衡进行调整
     * @param key  键
     * @param value  值
     */
    public void put(int key, Object value){
        Node p = root;
        Node parent = null;
        while(p != null){
            parent = p;
            if(key < p.key ){
                p = p.left;
            }else if(p.key < key){
                p = p.right;
            }else{
                p.value = value; //更新
                return;
            }
        }
        Node inserted = new Node(key, value);
        if(parent == null){
            root = inserted;
        }else if(key < parent.key){
            parent.left = inserted;
            inserted.parent = parent;  // parent的处理
        }else {
            parent.right = inserted;
            inserted.parent = parent;    //parent的处理
        }
        fixRedRed(inserted);
    }

    /**
     * 检查插入节点是否需要进行颜色调整，并进行调整
     * @param x 插入节点，默认红色
     */
    void fixRedRed(Node x){
        //case 1: 如果插入节点为根节点，将根节点变黑
        if(x == root){
            x.color = BLACK;
            return;
        }
        //case 2: 插入节点父亲是黑色，无需调整
        if(isBlack(x.parent)){
            return;
        }
        //case 3: 当红红相邻， 叔叔为红时
        //需要将父亲、叔叔变黑，祖父变红，然后对祖父做递归处理
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if(isRed(uncle)){
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
            return;
        }
        //case 4: 当红红相邻，叔叔为黑时
        //  - 父亲为左孩子，插入节点为左孩子，此时即为LL不平衡, 父亲变黑，祖父变红，然后祖父右旋。
        //  - 父亲为左孩子，插入节点为右孩子，此时即为LR不平衡, 父亲左旋变成LL，新的父亲（插入节点）变黑，祖父变红，祖父右旋。
        //  - 父亲为右孩子，插入节点为右孩子，此时即为RR不平衡, 父亲变黑，祖父变红，然后祖父左旋。
        //  - 父亲为右孩子，插入节点为左孩子，此时即为RL不平衡, 父亲右旋变成RR，新的父亲（插入节点）变黑，祖父变红，祖父左旋。
        if(parent.isLeftChild() && x.isLeftChild()){  // LL
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }else if(parent.isLeftChild() ){   //LR
            leftRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }else if(!x.isLeftChild()){       //RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }else{                            //RL
            rightRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }


    /**
     * 删除
     * 正常删，会用到李代桃僵技巧，遇到黑黑不平衡进行调整
     * @param key 要删除节点的键
     */
    public void remove(int key){
        Node deleted = find(key);
        if(deleted == null){
            return;
        }
        doRemove(deleted);
    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    /**
     * 查找删除节点
     */
    private Node find(int key){
        Node p = root;
        while(p != null){
            if(key < p.key){
                p = p.left;
            }else if(p.key < key){
                p = p.right;
            }else{
                return p;
            }
        }
        return null;
    }

    /**
     * 查找剩余节点(删剩下的节点）
     */
    private Node findReplaced(Node deleted){
        if(deleted.left == null && deleted.right == null){
            return null;
        }else if(deleted.left == null){
            return deleted.right;
        } else if (deleted.right == null) {
            return deleted.left;
        }else{
            Node s = deleted.right;
            while(s.left != null){
                s = s.left;
            }
            return s;
        }
    }

    /**
     * 处理双黑（case3，case4, case5)
     * case 3: 被调整节点的兄弟为红，此时两个侄子定为黑（否则不平衡）
     * case 4: 被调整节点的兄弟为黑，两个侄子都为黑
     *    - 将兄弟变红，，目的是将删除节点和兄弟那边的黑色高度同时减少1；
     *    - 如果父亲是红，则需将父亲变为黑，避免红红，此时路径黑节点数目不变
     *    - 如果父亲是黑，说明这条路径则少了一个黑，再次让父节点触发双黑
     * case 5：被调整节点的兄弟为黑，至少一个红侄子
     *    - 如果兄弟是左孩子，左侄子是红，LL不平衡
     *    - 如果兄弟是左孩子，右侄子是红，LR不平衡
     *    - 如果兄弟是右孩子，右侄子是红，RR不平衡
     *    - 如果兄弟是右孩子，左侄子是红，RL不平衡
     * @param x 待调整的节点，可能是deleted，也可能是replaced
     */
    private void fixDoubleBlack(Node x){
        if(x == root){
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        //case 3: 兄弟节点是红色, 转换为case 4或5，然后递归调用
        if(isRed(sibling)){
            if(x.isLeftChild()){
                leftRotate(parent);
            }else{
                rightRotate(parent);
            }
            parent.color = RED;
            sibling.color = BLACK;
            fixDoubleBlack(x);
            return;
        }


        if(sibling != null){
            //case 4: 兄弟是黑色, 两个侄子也是黑色
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                sibling.color = RED;
                if(isRed(parent)){
                    parent.color = BLACK;
                }else{
                    fixDoubleBlack(parent);
                }
            }
            //case 5: 兄弟是黑色，侄子有红色(如果两个侄子都是红色，也是适用的）
            else{
                //LL
                if(sibling.isLeftChild() && isRed(sibling.left)){
                    rightRotate(parent);
                    sibling.left.color = BLACK;
                    sibling.color = parent.color;
                }
                //LR
                else if(sibling.isLeftChild() && isRed(sibling.right)){
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                // RR
                else {
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }

                parent.color = BLACK;
            }
        }else{
            fixDoubleBlack(parent);
        }


    }

    /**
     * case 0: 有两个孩子 => 转化为只有一个孩子或没有孩子的情况进行处理
     * case 1：删的是根节点
     *   - 根节点没有孩子：删完了，直接将root = null
     *   - 根节点有一个孩子（肯定为红，如果是黑，就不平衡了）：用剩余节点替换了根节点的key、value， 根节点孩子 = null， 颜色保持黑色不变
     * 删除的是红色：无需调整颜色
     * 删除的是黑色，造成不平衡
     *    case 2: 删的是黑，剩下的是红，剩下这个红节点变黑
     *    双黑情况：删除节点和剩下节点都是黑，触发双黑，双黑的意思是，少了一个黑
     *    case 3、case 4、case 5
     * @param deleted 要删除的节点
     */
    private void doRemove(Node deleted){
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        if(replaced == null){    //没有孩子
            //case 1   删除的是根节点
            if(deleted == root){
                root = null;
            }else{       //删除的不是根节点
                if(isBlack(deleted)){        //双黑情况, 这里是先调整，再删除
                    fixDoubleBlack(deleted);
                }else{
                    //红色叶子，无需任何处理
                }
                if(deleted.isLeftChild()){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
                deleted.parent = null;   //有助于垃圾回收
            }

            return;

        }

        //有一个孩子
        if(deleted.left == null || deleted.right == null){
            //case 1
            if(deleted == root){
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            }else{
                if(deleted.isLeftChild()){
                    parent.left = replaced;
                }else{
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;  //有助于垃圾回收
            }
            if(isBlack(deleted) && isBlack(replaced)){   //双黑情况， 这里是先删除，再调整（调整的是replaced）
                fixDoubleBlack(replaced);
            }else{
                //case 2
                replaced.color = BLACK;
            }

            return;

        }

        //有两个孩子 => 转化为只有一个孩子或没有孩子的情况进行处理 case0
        //将待删除节点和其后继节点互换key和value
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;

        doRemove(replaced);


    }


}
