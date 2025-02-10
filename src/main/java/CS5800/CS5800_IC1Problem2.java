package CS5800;

public class CS5800_IC1Problem2 {
    public static int findKthElement(int[] arr1, int[] arr2, int k) {
        int low = Math.max(0, k - arr2.length); // Minimum possible elements from arr1
        int high = Math.min(arr1.length, k);  // Maximum possible elements from arr1

        while (low < high) {
            int mid1 = (low + high) / 2;  // mid1: Number of elements chosen from arr1
            int mid2 = k - mid1;  // mid2: Number of elements chosen from arr2

            if (mid1 > 0 && arr1[mid1 - 1] > arr2[mid2]) {
                high = mid1 - 1;  // Reduce arr1 selection
            } else if (mid2 > 0 && arr2[mid2 - 1] > arr1[mid1]) {
                low = mid1 + 1;  // Increase arr1 selection
            } else {
                return Math.min(arr1[mid1], arr2[mid2]);
            }
        }

        int mid1 = low;
        int mid2 = k - mid1;

//        if (mid1 >= arr1.length) return arr2[mid2];
//        if (mid2 >= arr2.length) return arr1[mid1];

        return Math.min(arr1[mid1], arr2[mid2]);   //实际上返回的是第k+1小的元素，所以传入的才是n, n-1
    }

    public static int productArrays(int[] arr1, int[] arr2) {
        int n = arr1.length;

        // Find the two middle elements of the merged array
        int mid1 = findKthElement(arr1, arr2, n - 1); // First middle element
        int mid2 = findKthElement(arr1, arr2, n);     // Second middle element

        return mid1 * mid2; // Return their product
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 6, 10};
        int[] arr2 = {4, 5, 6, 9, 12};
        System.out.println(productArrays(arr1, arr2));  // Expected Output: 6

        int[] arr3 = {1, 12, 15, 26, 38};
        int[] arr4 = {2, 13, 17, 30, 45};
        System.out.println(productArrays(arr3,arr4));  // Expected Output: 17
    }

}


