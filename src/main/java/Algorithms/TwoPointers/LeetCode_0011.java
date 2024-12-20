package Algorithms.TwoPointers;

/**
 * 盛最多水的容器
 */
public class LeetCode_0011 {
    /*
     距离减小的情况下，改变较高的挡板，容量会变少，只有改变较低的挡板，容量才有可能变大
     */
    static int maxArea(int[] height){
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while(i < j){
            int x = height[i];
            int y = height[j];
            max = Math.max(max, (j-i) * Math.min(x,y));
            if(x >= y){
                j--;
            }else{
                i++;
            }
        }
        return max;

    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})); // 49
        System.out.println(maxArea(new int[]{2,1})); // 1
    }

}
