package DataStructure.BinaryTree;

import java.util.LinkedList;

/**
 * 二叉树遍历 - DFS - 非递归实现
 */
public class TreeTraversal_DFS_II {

    //前序遍历
    private static void traversalPreOrder(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>(); //使用Stack记录来时的路
        TreeNode curr = node; //代表当前节点（指针）

        while(curr != null || !stack.isEmpty()){
            if(curr != null){      //一路向左，打印每个节点(root-left)，并将节点压入栈
                stack.push(curr);
                System.out.print(curr.val + " ");
                curr = curr.left;
            }else{                //当节点为null时（左子树没有元素了）
                TreeNode pop = stack.pop();    //弹栈（最左边的元素）
                curr= pop.right;       //看看栈顶元素有没有右子树，如果有的话，赋值给curr，进入下一个循环
            }
        }
    }

    //中序遍历
    private static void traversalInOrder(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>(); //使用Stack记录来时的路
        TreeNode curr = node; //代表当前节点（指针）

        while(curr != null || !stack.isEmpty()){
            if(curr != null){      //一路向左，将节点压入栈
                stack.push(curr);
                curr = curr.left;
            }else{                //当节点为null时（左子树没有元素了）
                TreeNode pop = stack.pop();    //弹栈（最左边的元素）
                System.out.print(pop.val + " ");     //打印栈顶元素(left-root)
                curr= pop.right;       //看看栈顶元素有没有右子树，如果有的话，赋值给curr，进入下一个循环
            }
        }
    }

    //后序遍历
    private static void traversalPostOrder(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>(); //使用Stack记录来时的路
        TreeNode curr = node; //代表当前节点（指针）
        TreeNode pop = null; //最近一次弹栈的元素

        while(curr != null || !stack.isEmpty()){
            if(curr != null){      //一路向左，将节点压入栈
                stack.push(curr);
                curr = curr.left;
            }else{                //当节点为null时（左子树没有元素了）
                TreeNode peek = stack.peek();    //获取栈顶元素
                if(peek.right == null || peek.right == pop){       //如果栈顶元素的右子树是null或者已经处理过了(右孩子等于最近一次弹栈的元素）
                    pop = stack.pop();    //弹栈
                    System.out.print(pop.val + " ");     //打印栈顶元素
                }else{
                    curr = peek.right;
                }
            }
        }
    }


    //前序、中序、后序遍历
    private static void traversal(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>(); //使用Stack记录来时的路

        TreeNode curr = node; //代表当前节点
        TreeNode pop = null;  //最近一次弹栈的元素

        while(curr != null || !stack.isEmpty()){

            if(curr != null){      //一路向左，打印每个节点，并将节点压入栈
                stack.push(curr);
                colorPrintln("前序遍历：" + curr.val,31);
                //待处理左子树
                curr = curr.left;

            }else{                  //左子树处理完了
                TreeNode peek = stack.peek();        //栈顶元素
                if(peek.right == null ){             //没有右子树
                    colorPrintln("中序遍历：" + peek.val,36);
                    pop = stack.pop();
                    colorPrintln("后序遍历：" + pop.val,48);
                }else if(peek.right == pop){         //右子树处理完了
                    pop = stack.pop();
                    colorPrintln("后序遍历：" + pop.val,48);
                }else {
                    colorPrintln("中序遍历：" + peek.val,36);
                    //待处理右子树
                    curr = peek.right;
                }
            }
        }
    }



    public static void colorPrintln(String origin, int color){
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode
                (new TreeNode(new TreeNode(4),
                        2,new TreeNode(5)),
                        1,

                        new TreeNode(new TreeNode(6),
                                3,
                                new TreeNode(7))
                );


        System.out.print("前序遍历：");
        traversalPreOrder(root);
        System.out.println();
        System.out.print("中序遍历：");
        traversalInOrder(root);
        System.out.println();
        System.out.print("后序遍历：");
        traversalPostOrder(root);
        System.out.println();
        System.out.println();
        traversal(root);
    }
}
