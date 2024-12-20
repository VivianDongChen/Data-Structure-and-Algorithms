package DataStructure.Heap;
/*
  求数组中第K大的元素 - O（nlog(n))
  解体思路
   1. 向小顶堆放入前K个元素
   2. 剩余元素
      - 若 <= 堆顶元素，则略过
      - 若 > 堆顶元素，则替换堆顶元素
   3. 这样小顶堆始终保留的是到目前为止前K大的元素
   4. 循环结束，堆顶元素即为第K大元素
 */
public class LeetCode_0215 {
    public static int findKthLargest(int[] numbers, int k){
        MinHeap minHeap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            minHeap.offer(numbers[i]);
        }
        for (int i = k; i < numbers.length; i++) {
            if(numbers[i] > minHeap.peek()){
                minHeap.replace(numbers[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4},2));
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4));
    }
}
