package DataStructure.BinarySearchTree;

public class LeetCode0450DeleteNodeInaBST {

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

    /**
     * 递归实现
     * @param node
     * @param key
     * @return
     */
    public TreeNode deleteNode1(TreeNode node, int key){
        if(node == null){
            return null;
        }
        if(key < node.val){
            node.left = deleteNode1(node.left, key);
            return node;
        }
        if(node.val < key){
            node.right = deleteNode1(node.right,key);
            return node;
        }
        //找到了

        if(node.left == null){
            return node.right;
        }

        if(node.right == null){
            return node.left;
        }

        TreeNode s = node.right;
        while(s.left != null){
            s = s.left;
        }
        s.right = deleteNode1(node.right,s.val);
        s.left = node.left;
        return s;
    }

    /**
     * 非递归实现
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode2(TreeNode root, int key){
        TreeNode p = root;
        TreeNode parent = null;
        while(p != null){
            if(p.val < key){
                parent = p;
                p = p.right;
            }else if(key < p.val){
                parent = p;
                p = p.left;
            }else{
                break;
            }
        }
        //没找到
        if(p == null){
            return root;
        }
        //找到了
        if (p.left == null){      //情况1或3: 左孩子为空
            root = shift(root,parent, p, p.right);      //删除节点托孤
        } else if(p.right == null){     //情况2或3: 右孩子为空
            root = shift(root,parent, p, p.left);       //删除节点托孤
        }else{                          //情况4: 有两个孩子
            TreeNode s = p.right;       //右子树找到后任
            TreeNode sParent = p;
            while(s.left != null){
                sParent = s;
                s = s.left;
            }
            if(sParent != p){     //删除节点和后任不相邻
                root = shift(root,sParent, s, s.right);    //后任托孤
                s.right = p.right; //顶上去的后任节点的右孩子是删除节点的右孩子
            }
            root = shift(root,parent,p,s);        //删除节点和后任相邻或不相邻  删除节点托孤
            s.left = p.left; //顶上去的后任节点的左孩子是删除节点的左孩子
        }
        return root;
    }

    private TreeNode shift(TreeNode root,TreeNode parent, TreeNode deleted, TreeNode child){
        if(parent == null){    //被删除的是根节点
            root = child;
        }else if(deleted == parent.left){      //被删除的是左孩子
            parent.left = child;
        }else if(deleted == parent.right){         //被删除的是右孩子
            parent.right = child;
        }
        return root;
    }



    public static void main(String[] args) {
        // 测试代码
        /*
                 5
               /   \
              3     6
            /   \    \
           2     4    7

         */
        LeetCode0450DeleteNodeInaBST tree = new LeetCode0450DeleteNodeInaBST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Before deletion:");
        inorderTraversal(root);

        root = tree.deleteNode2(root, 3);

        System.out.println("\nAfter deletion:");
        inorderTraversal(root);
    }

    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }
}


