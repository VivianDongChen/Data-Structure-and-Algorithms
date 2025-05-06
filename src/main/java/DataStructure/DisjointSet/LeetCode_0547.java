package DataStructure.DisjointSet;

// Number of Province
public class LeetCode_0547 {

    public int findCircleNumber(int[][] isConnected){
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);

        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(isConnected[i][j] == 1){
                    uf.union(i, j);
                }
            }
        }
         return uf.count;
    }

    class UnionFind{
        int[] parent;
        int[] rank;
        int count;

        UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
            count = n;
        }

        public int find(int n){
            if(parent[n] != n){
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }

        public boolean union(int x, int y){
            int xRoot = find(x);
            int yRoot = find(y);

            if(xRoot == yRoot){
                return false;
            }

            if(rank[xRoot] > rank[yRoot]){
                parent[yRoot] = xRoot;
            }else if(rank[yRoot] > rank[xRoot]){
                parent[xRoot] = yRoot;
            }else{
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }

            count --;
            return true;
        }
    }

    public static void main(String[] args) {
        LeetCode_0547 solution = new LeetCode_0547();

        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int result = solution.findCircleNumber(isConnected);
        System.out.println("Number of provinces: " + result);  // Expected output: 2
    }

}
