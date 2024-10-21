package HackerRank;
import java.util.Arrays;

public class MaximizeEngagement {
    public static long getMaxEngagementScore(int[] views, int[] likes) {
        Arrays.sort(views);
        Arrays.sort(likes);

        long sum = 0;
        int i = views.length - 1;
        int j = views.length - 1;
        while ( j >= 0){
            while (j >= 0 && likes[i] <= views[j]){
                j--;
            }
            if(j >= 0){
                sum += likes[i];
                j--;
                i--;
            }else{
                return 0;
            }
        }

        return sum;

    }

    public static void main(String[] args) {
        int[] views = {2, 3, 4, 5, 6};
        int[] likes = {4, 6, 5, 6, 3};
        System.out.println(getMaxEngagementScore(views, likes));  // 输出：21
    }
}