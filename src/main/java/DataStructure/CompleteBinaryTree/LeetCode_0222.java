package DataStructure.CompleteBinaryTree;

import java.sql.SQLOutput;

/**
 * 222. Count Complete Tree Nodes
 * 1.bit运算
 * 左移操作 1 << h 将1的二进制位向左移动h位，相当于在右侧补上h个0。相当于2的h次方
 * 示例：1<<0 ：0000 0001 : 1
 *      1<<1 ：0000 0010 : 2
 *      1<<2 : 0000 0100 : 4
 *      1<<3 ：0000 1000 : 8
 * 2. 完全二叉树的节点公式是：节点数= 1>>h − 1
 *    高度 h 是从 1 开始计数的。如果只有根节点，那么高度h=1，而不是0。
 */
public class LeetCode_0222 {
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :     // h为null，说明已经找到了叶子节点下面的null节点，返回0， 退出递归
                height(root.right) == h-1 ? countNodes(root.right) + (1 << h) :   //如果右子树高度 = 左子树高度，说明左子树满了，
                                                                                  // 计算并加上左子树高度，然后继续递归右子树
                        countNodes(root.left) + (1 << h-1);    //如果右子树高度 ！= 左子树高度，说明不能确定左子树是否已满，
                                                               //这个时候加上左子树确定满的那部分，继续递归左子树，
                                                               //注意这个时候忽略掉的右子树，似乎在后面的计算中自动得到了补偿
                                                               //（左子树确定满的那部分的添加是有重叠的），这个原理没有搞懂，似乎跟离散数学有关
    }


    private int height(TreeNode node){
        return node == null ? -1 : 1 + height(node.left);  //对于完全二叉树，起始为1，不断往下找，每层加1，
                                                           // 找到最左边的节点对应的null，然后-1，得到的就是高度

    }

    public static void main(String[] args) {
         LeetCode_0222 test = new LeetCode_0222();
         TreeNode root = test.new TreeNode(1);
         root.left = test.new TreeNode(2);
         root.right = test.new TreeNode(3);
         root.left.left = test.new TreeNode(4);
         root.left.right = test.new TreeNode(5);
         root.right.left = test.new TreeNode(6);

        System.out.println(test.countNodes(root));
    }
}
