package DataStructure.SegmentTree;

public class LeetCode_0307 {

    private int[] tree;
    private int n;

    public LeetCode_0307(int[] nums){
        n = nums.length;
        tree = new int[4 * n];
        build(0, 0, n-1,nums);
    }

    private void build(int node, int start, int end, int[] nums){
        if(start == end){
            tree[node] = nums[start];
        }else{
            int mid = (start + end) / 2;
            build(node * 2+ 1, start, mid, nums);
            build(node * 2 + 2, mid + 1, end, nums);
            tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
        }
    }

    public void update(int index, int val){
        update(0, 0, n-1, index, val);
    }

    private void update(int node, int start, int end, int index, int val){
        if(start == end){
             tree[node] = val;
        }else{
            int mid = (start + end) / 2;
            if( index <= mid){
                update(node * 2 + 1, start, mid, index, val);
            }else{
                update(node * 2 + 2, mid +1, end, index, val);
            }
            tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
        }
    }

    public int sumRange(int left, int right){
        return sumRange(0, 0, n-1, left, right);
    }

    private int sumRange(int node, int start, int end, int left, int right){
        if(start > right || end < left){
            return 0;
        }else if(left <=start && end <= right){
            return tree[node];
        }else{
            int mid = (start + end) / 2;
            return sumRange(node * 2 + 1, start, mid, left, right) +sumRange(node * 2 + 2, mid + 1, end, left, right);
        }
    }

    public static void main(String[] args) {
        // Initialize the array
        int[] nums = {1, 3, 5, 7, 9, 11};

        // Create a segment tree from the array
        LeetCode_0307 segmentTree = new LeetCode_0307(nums);

        // Test sumRange queries
        System.out.println("Sum of range [0, 2]: " + segmentTree.sumRange(0, 2)); // Expected: 9
        System.out.println("Sum of range [1, 4]: " + segmentTree.sumRange(1, 4)); // Expected: 24
        System.out.println("Sum of range [3, 5]: " + segmentTree.sumRange(3, 5)); // Expected: 27

        // Update an element
        segmentTree.update(1, 10); // nums[1] = 10

        // Test sumRange queries after the update
        System.out.println("Sum of range [0, 2] after update: " + segmentTree.sumRange(0, 2)); // Expected: 16
        System.out.println("Sum of range [1, 4] after update: " + segmentTree.sumRange(1, 4)); // Expected: 31
    }

}
