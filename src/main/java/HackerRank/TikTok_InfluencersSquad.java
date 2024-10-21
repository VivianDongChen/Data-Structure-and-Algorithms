package HackerRank;
import java.util.HashMap;
import java.util.Map;

public class TikTok_InfluencersSquad {
    public static int findMaxSquadSize(int[] engagementScores) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int score : engagementScores) {
            map.put(score, map.getOrDefault(score, 0) + 1);
        }

        int max = 0;

        for (int score : map.keySet()) {
            int CurrentMax = 0;
            if (!map.containsKey(score - 1)) {
                CurrentMax = map.get(score);
                while(map.containsKey(score+1)){
                    CurrentMax += map.get(score+1);
                    score++;
                }
            }
            max = Math.max(max,CurrentMax);

        }

        return max;
    }

    public static void main(String[] args) {
        int[] engagementScores = {12, 14, 15, 11, 16};
        System.out.println(findMaxSquadSize(engagementScores));  // 输出：3
    }
}

