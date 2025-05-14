package DataStructure.SegmentTree;

public class SegmentTree {
    private int[] tree;  // Array used to represent the segment tree
    private int n;       // Length of the original array

    // Constructor: Builds the segment tree from the original array
    public SegmentTree(int[] nums) {
        n = nums.length;
        tree = new int[4 * n]; // Allocate enough space
        build(nums, 0, 0, n - 1);
    }

    // Build the segment tree recursively
    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start]; // Leaf node stores original array value
        } else {
            int mid = (start + end) / 2;
            build(nums, 2 * node + 1, start, mid);          // Build left subtree
            build(nums, 2 * node + 2, mid + 1, end);         // Build right subtree
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2]; // Internal node stores sum of children
        }
    }

    // Public method to update an index to a new value
    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    // Recursively update the tree
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val; // Update the leaf node
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node + 1, start, mid, idx, val); // Recurse into left subtree
            } else {
                update(2 * node + 2, mid + 1, end, idx, val); // Recurse into right subtree
            }
            // Update internal node after child update
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Public method to query sum in range [l, r]
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    // Recursively query the sum of a range
    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0; // No overlap
        }
        if (l <= start && end <= r) {
            return tree[node]; // Total overlap
        }
        int mid = (start + end) / 2;
        int left = query(2 * node + 1, start, mid, l, r);
        int right = query(2 * node + 2, mid + 1, end, l, r);
        return left + right; // Partial overlap, return sum
    }

    // Test the segment tree functionality
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(nums);

        System.out.println("Initial sum in range [1, 3]: " + st.query(1, 3)); // Output: 15
        st.update(1, 10); // nums[1] = 10
        System.out.println("After update, sum in range [1, 3]: " + st.query(1, 3)); // Output: 22
        System.out.println("Sum in range [0, 5]: " + st.query(0, 5)); // Output: 43
        st.update(4, 0); // nums[4] = 0
        System.out.println("After second update, sum in range [3, 5]: " + st.query(3, 5)); // Output: 18
    }
}

