package DataStructure.DisjointSet;

import java.util.*;

//LeetCode 0721 - Accounts Merge
public class LeetCode_0721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts){
        int n = accounts.size();
        UnionFind uf = new UnionFind(n);

        Map<String, Integer> emailToIndex = new HashMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 1; j < accounts.get(i).size(); j++){
                emailToIndex.putIfAbsent(accounts.get(i).get(j), i);
                uf.union(i, emailToIndex.get(accounts.get(i).get(j)));
            }
        }

        Map<Integer, Set<String>> merged = new HashMap<>();

        for(String email : emailToIndex.keySet()){
            int root = uf.find(emailToIndex.get(email));
            merged.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for(int index : merged.keySet()){
            List<String> emails = new ArrayList<>(merged.get(index));
            emails.add(0,accounts.get(index).get(0));
            res.add(emails);
        }

        return res;
    }

    private class UnionFind {
        private int[] parent;
        private int[] rank;

        UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
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
            }else if (rank[yRoot] > rank[xRoot]){
                parent[xRoot] = yRoot;
            }else{
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        LeetCode_0721 solution = new LeetCode_0721();

        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("Alice", "alicebravo@mail.com"));

        List<List<String>> merged = solution.accountsMerge(accounts);

        System.out.println("Merged Accounts:");
        for (List<String> account : merged) {
            System.out.println(account);
        }
    }
}
