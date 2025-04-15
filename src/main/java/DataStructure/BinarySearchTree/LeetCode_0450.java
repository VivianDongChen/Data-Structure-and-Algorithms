package DataStructure.BinarySearchTree;

/**
 * 删除BST的节点
 */
public class LeetCode_0450 {

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
     */
    public TreeNode deleteNode1(TreeNode node, int key){
        if(node == null){
            return null;
        }

        if(key < node.val){
            node.left = deleteNode1(node.left, key);
        }else if(node.val < key){
            node.right = deleteNode1(node.right,key);
        }else{ //找到了
            if(node.left == null){ //node没左孩子
                return node.right;
            }else if(node.right == null){ //node没右孩子
                return node.left;
            }else{   //node有两个孩子
                TreeNode s = node.right;   //找到比node大的最小节点（右侧的最左边的节点）
                while(s.left != null){
                    s = s.left;
                }
                node.val = s.val;  //用这个节点替换node
                node.right = deleteNode1(node.right,s.val);  //删除这个节点
            }
        }

        return node;
    }

    /**
     * 非递归实现
     */
    public TreeNode deleteNode2(TreeNode root, int key){
        TreeNode parent = null;
        TreeNode curr = root;

        //找到要删除的节点
        while(curr != null &&  curr.val != key){
            parent = curr;
            if(curr.val < key){
                curr = curr.right;
            }else if(curr.val > key){
                curr = curr.left;
            }
        }
        //edge case: 没有找到key，直接返回root.
        if( curr == null){
            return root;
        }

        if(curr.right != null && curr.left != null){
            TreeNode successorParent = curr;
            TreeNode successor = curr.right;

            while(successor.left != null){
                successorParent = successor;
                successor = successor.left;
            }

            curr. val = successor.val;

            curr = successor;
            parent = successorParent;
        }

        TreeNode child = curr.left != null ? curr.left : curr.right;

        if(parent == null){
            return child;
        }
        if(parent.left == curr){
            parent.left = child;
        }else if(parent.right == curr){
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
        LeetCode_0450 tree = new LeetCode_0450();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Before deletion:");
        inorderTraversal(root);

        root = tree.deleteNode1(root, 3);

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


