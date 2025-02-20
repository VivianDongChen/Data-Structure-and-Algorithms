package Algorithms.DivideAndConquer;

public class LeetCode_0427 {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        return helper (grid,0,0,grid.length);
    }

    private Node helper(int[][] grid, int i, int j, int n){
        if(allSame(grid, i,j,n)){
            return new Node(grid[i][j] == 1? true:false,true);
        }

        Node node = new Node(true,false);
        node.topLeft = helper(grid,i,j,n/2);
        node.topRight = helper(grid,i,j+n/2,n/2);
        node.bottomLeft = helper(grid,i+n/2,j,n/2);
        node.bottomRight = helper(grid,i+n/2,j+n/2, n/2);
        return node;
    }

    private boolean allSame(int[][] grid, int i,int j,int n){
        for(int x = i; x < i+n; x++) {
            for (int y = j; y < j + n; y++) {
                if (grid[x][y] != grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_0427 test = new LeetCode_0427();

        // Test Case 1: [[0,1],[1,0]]
        int[][] grid1 = {
                {0, 1},
                {1, 0}
        };
        System.out.println("Test Case 1:");
        Node root1 = test.construct(grid1);
        System.out.println(root1.val);

        // Test Case 2: [[1,1,1,1,0,0,0,0], ...] (8x8 matrix)
        int[][] grid2 = {
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0}
        };
        System.out.println("Test Case 2:");
        Node root2 = test.construct(grid2);
        System.out.println(root2.val);
    }

}
