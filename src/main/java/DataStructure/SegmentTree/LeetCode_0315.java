package DataStructure.SegmentTree;

import java.util.*;

public class LeetCode_0315 {

    class SegmentTree{
        private int[] tree;
        private int n;
        public SegmentTree(int size) {
            n = size;
            tree = new int[4 * size];
        }

        public void update(int index, int value){
            update(0, 0, n - 1, index, value);
        }

        private void update(int node, int start, int end, int index, int value){
            if(start == end){
                tree[node] += value;
            }else{
                int mid = (start + end) / 2;
                if(index <= mid){
                    update(node * 2 + 1, start, mid, index, value);
                }else{
                    update(node * 2 + 2, mid + 1, end, index, value);
                }
                tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
            }
        }

        public int query(int left, int right){
            return query(0,0, n-1, left, right);
        }

        private int query(int node, int start, int end, int left, int right){
            if(end < left || right < start) {
                return 0;
            }else if(left <= start && end <= right){
                return tree[node];
            }else{
                int mid = (start + end) / 2;
                return query(node * 2 + 1, start, mid, left, right) + query(node * 2 + 2, mid + 1, end, left, right);
            }
        }
    }

    public List<Integer> countSmaller(int[] nums){
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        Map<Integer, Integer> rankMap = new HashMap<>();
        for(int i = 0; i < sortedNums.length; i++){
            rankMap.put(sortedNums[i], i);
        }

        List<Integer> res = new ArrayList<>();
        SegmentTree tree = new SegmentTree(sortedNums.length);

        for(int i = nums.length - 1; i >= 0; i--){
            int rank = rankMap.get(nums[i]);
            int count = (rank > 0) ? tree.query(0, rank - 1) : 0;
            res.add(count);
            tree.update(rank, 1);
        }

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        LeetCode_0315 solution = new LeetCode_0315();
        int[] nums = {5, 2, 6, 1};
        List<Integer> result = solution.countSmaller(nums);
        System.out.println(result); // Expected output: [2, 1, 1, 0]
    }
}
