package DataStructure.BinarySearchTree;

/**
 * 根据前序遍历构造二叉搜索树
 * 题目说明
 * 1. preorder长度 >= 1
 * 2. preorder没有重复值
 */
public class LeetCode_1008 {

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

    /*
         8, 5, 1, 7, 10, 12

                     8
                  /     \
                 5      10
               /   \       \
              1     7       12

     */

    /**
     * 方法1: 依次插入元素(递归）   O(nLog(n))
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder1(int[] preorder){
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert1(root,preorder[i]);
        }
        return root;
    }

    private TreeNode insert1(TreeNode node, int val){   //O(log(n))
        if(node == null){
            return new TreeNode(val);
        }
        if(val < node.val){
            node.left = insert1(node.left,val);
        }else if(node.val < val){
            node.right = insert1(node.right,val);
        }
        return node;
    }

    /**
     * 方法2：上限法（递归）   O(n)      难！！！
     *
     *      8， 5， 1， 7， 10， 12
     *
     *      1. 遍历数组中每一个值，根据值创建节点
     *        - 每个节点若成功创建，都有：左孩子上限、右孩子上限
     *      2. 处理下一个值时，如果超过此上限，那么
     *        - 将null作为上一个节点的孩子
     *        - 不能创建节点对象
     *        - 直到不超过上限为止
     *      3. 重复1.2两步

     */
    public TreeNode bstFromPreorder2(int[] preorder){
        return  insert2(preorder,Integer.MAX_VALUE);
    }


    int i = 0;   //数组的索引

    /**
     * 依次处理preorder中每个值， 返回创建好的节点或null
     *      * 1. 如果超过上限，返回null， 作为孩子返回
     *      * 2. 如果没超过上限，创建节点，并设置其左右孩子，左右孩子完整后返回
     * @param preorder
     * @param max  上限（左或者右）
     * @return
     */
    private TreeNode insert2(int[] preorder, int max){
        if(i == preorder.length){
            return null;
        }
        int val = preorder[i];
        if( max < val){                    //超过上限，返回null
            return null;
        }
        TreeNode node = new TreeNode(val); //没超过上限，创建节点
        i++;
        node.left = insert2(preorder, val);     //左孩子的上限是本身
        node.right = insert2(preorder, max);      //右孩子的上限是自己的上限
        return node;
    }

    /**
     * 方法3：分治法（递归）   O(nlog(n))
     *
     *  [8， 5， 1， 7， 10， 12]      //前序遍历的特点： 左子树和右子树的节点都是连着的
     *  根 8  左 [5，1，7]  右 [10，12]
     *  根 5  左 1  右 7
     *  根 10  左 null  右 12
     */
    public TreeNode bstFromPreorder3(int[] preorder){
        return partition(preorder,0, preorder.length-1);


    }

    private TreeNode partition(int[] preorder, int start, int end){
        if(start > end){
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);   //start是根节点
        int index = start + 1;
        while(index <= end){
            if(preorder[index] > preorder[start]){         //第一个大于start的节点是右子树起点
                    break;
            }
            index++;
        }
        //index就是右子树的起点
        root.left = partition(preorder,start+1, index -1);
        root .right = partition(preorder,index,end);
        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new LeetCode_1008().bstFromPreorder3(new int[]{8,5,1,7,10,12});
        TreeNode t2 = new TreeNode(8,
                                   new TreeNode(5,
                                                 new TreeNode(1),
                                                 new TreeNode(7)),
                                   new TreeNode(10,
                                                 null,
                                                 new TreeNode(12)));
        System.out.println(isSameTree(t1,t2));
    }

    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
}
