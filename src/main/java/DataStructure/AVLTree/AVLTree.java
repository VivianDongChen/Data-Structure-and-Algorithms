package DataStructure.AVLTree;

/**
 * AVL 树
 *
 *    1. 二叉搜索树在插入和删除时，节点可能失衡
 *    2. 如果在插入和删除时通过旋转, 始终让二叉搜索树保持平衡, 称为自平衡的二叉搜索树
 *    3. AVL 是自平衡二叉搜索树的实现之一 （AVL三个字母取自发明者的名字）
 *
 *
 */
public class AVLTree {

    static class AVLNode{
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; //高度， 初始值为1

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    //求节点的高度（节点为null时，高度为0）
    private int height(AVLNode node){
        return node == null ? 0: node.height;
    }

    //更新节点高度（新增，删除，旋转）
    private void updateHeight(AVLNode node){
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    /*
       平衡因子（balance factor）= 左子树高度 - 右子树高度

       bf = 0, 1, -1 : 平衡
       bf > 1 : 不平衡，左边高
       bf < -1 : 不平衡， 右边高

     */
    private int bf(AVLNode node){
        return height(node.left) - height(node.right);
    }

    /**
     * 右旋
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode rightRotate(AVLNode red){
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;
        yellow.right = red;  //上位
        red.left = green;    //换爹
        updateHeight(red);      //更新高度的这两行代码先后不能改变（先更新下面的红色，再更新上面的黄色）
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 左旋
     * @param red 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode leftRotate(AVLNode red){
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;  // 上位
        red.right = green;    //换爹
        updateHeight(red);      //更新高度的这两行代码先后不能改变（先更新下面的红色，再更新上面的黄色）
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 左右旋：先左旋左子树，再右旋根节点
     * @param node 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode leftRightRotate(AVLNode node){
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    /**
     * 右左旋：先右旋右子树，再左旋根节点
     * @param node 要旋转的节点
     * @return 新的根节点
     */
    private AVLNode rightLeftRotate(AVLNode node){
        node.right = rightRotate(node.right);
        return leftRotate(node);

    }

    /**
     * 检查节点是否失衡，重新平衡代码
     * @param node
     * @return
     */
    private AVLNode balance(AVLNode node){
        if(node == null){
            return null;
        }

        int bf = bf(node);

        if(bf > 1 && bf(node.left) >= 0){           //LL
            return rightRotate(node);

        }else if(bf > 1 && bf(node.left) < 0){     //LR
            return leftRightRotate(node);

        }else if(bf < -1 && bf(node.right) > 0){   //RL
            return rightLeftRotate(node);

        }else if(bf < -1 && bf(node.right) <= 0){   //RR
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;
    /**
     * 新增节点
     * @param key
     * @param value
     */
    public void put(int key, Object value){
        root = doPut(root,key,value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value){
        //1. 找到空位，创建新节点
        if(node == null){
            return new AVLNode(key, value);     //height初始值为1，这里不用赋值
        }
        //2. key已存在，更新
        if(key == node.key){
            node.value = value;              //更新不会造成不平衡，无需更新height
            return node;
        }
        //3. 继续寻找
        if(key < node.key){
           node.left = doPut(node.left, key, value);  //向左找
        }else{
            node.right = doPut(node.right,key,value);   //向右找
        }

        updateHeight(node); //递归回程更新每个节点的高度
        return balance(node);

    }

    /**
     * 删除节点
     * @param key
     */
    public void remove(int key) {
        root = doRemove(root,key);

    }

    private AVLNode doRemove(AVLNode node, int key){  //返回值：删剩下的
        //1. node == null
        if(node == null){
            return null;
        }
        //2. 没找到key
        if( key < node.key){
            node.left = doRemove(node.left, key);
        }else if(node.key < key){
            node.right = doRemove(node.right,key);
        }else{
            //3. 找到key 1）没有孩子 2）只有一个孩子 3）有两个孩子
            if(node.left == null && node.left == null){
                return null;
            }else if(node.left == null){
                node = node.right;   //没有立即返回，而是要指向下面的更新高度和balance
            }else if(node.right == null){
                node = node.left;
            }else{
                AVLNode s = node.right;
                if(s.left!= null){
                    s = s .left;
                }
                //找到后继节点
                s.right = doRemove(node.right,s.key);   //后继节点托孤、移位
                s.left = node.left;
                node = s; //返回后继节点（后继节点就是删剩下的）
            }
        }

        //4. 更新高度
        updateHeight(node);
        //5. balance
        return balance(node);
    }





}
