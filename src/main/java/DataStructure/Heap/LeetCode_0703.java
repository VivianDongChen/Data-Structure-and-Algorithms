package DataStructure.Heap;

public class LeetCode_0703 {
    private MinHeap minHeap;
    public LeetCode_0703(int k, int[] nums){
        minHeap = new MinHeap(k);
        for(int num:nums){
            add(num);
        }

    }
    //此方法会被不断引用，模拟数据流中新来的元素
    public int add(int val){
        if(!minHeap.isFull()){
            minHeap.offer(val);
        }else if (val > minHeap.peek()){
            minHeap.replace(val);
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        LeetCode_0703 test = new LeetCode_0703
                (3,new int[]{4,5,8,2});

        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }
}
