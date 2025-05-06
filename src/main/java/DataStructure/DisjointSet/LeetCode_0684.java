package DataStructure.DisjointSet;

public class LeetCode_0684 {
    public int[] findRedundantConnection(int[][] edges){
        int n = edges.length;
        UnionFind uf = new UnionFind(n+1);

        for(int[] edge : edges){
            if(!uf.union(edge[0], edge[1])){
                return edge;
            }
        }
        return new int[0];
    }

    class UnionFind{
        int[] parent;
        int[] rank;

        UnionFind(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
            rank = new int[n];
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

            return true;
        }
    }

    public static void main(String[] args) {
        LeetCode_0684 solution = new LeetCode_0684();

        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3}  // This edge forms a cycle
        };

        int[] result = solution.findRedundantConnection(edges);

        System.out.println("Redundant connection: [" + result[0] + ", " + result[1] + "]");
    }
}
