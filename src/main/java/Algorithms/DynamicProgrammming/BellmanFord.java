package Algorithms.DynamicProgrammming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BellmanFord求图的单源最短路径 - 动态规划
 */
public class BellmanFord {

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

        /*
            f(v) 用来表示从起点出发，到达 v 这个顶点的最短距离
            初始时
            f(v) = 0   当 v==起点 时
            f(v) = ∞   当 v!=起点 时 未知时设置为无穷大

            之后
            新           旧     所有from
            f(to) = min(f(to), f(from) + from.weight)

            from 从哪来
            to   到哪去

            f(v4) = min( ∞, f(v3) + 11 ) = 20
            f(v4) = min( 20, f(v2) + 15 ) = 20


            v1  v2  v3  v4  v5  v6
            0   ∞   ∞   ∞   ∞   ∞
            0   7   9   ∞   ∞   14  第一轮
            0   7   9   20  23  11  第二轮
            0   7   9   20  20  11  第三轮
            0   7   9   20  20  11  第四轮
            0   7   9   20  20  11  第五轮

            轮数 = 顶点数 - 1

     */

    public static void main(String[] args) {
        List<Edge> edges = List.of(
                new Edge(6, 5, 9),
                new Edge(4, 5, 6),
                new Edge(1, 6, 14),
                new Edge(3, 6, 2),
                new Edge(3, 4, 11),
                new Edge(2, 4, 15),
                new Edge(1, 3, 9),
                new Edge(1, 2, 7)
        );
        //一维数组来缓存结果，索引代表顶点的编号（编号从1开始，为了一一对应，索引0不用了，索引1对应v1（起点）），元素值代表起点到这个顶点的最短距离
        int[] dp = new int[7];
        //初始化一维数组
        dp[1] = 0; //起点的距离已知，是0；
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE; //其它顶点的值是无穷大
        }
        print(dp);
        for(int i = 0;i < dp.length - 2; i++){
            for(Edge e: edges){
                //边指向的顶点的最短距离，等于边的所有的来处的顶点的最短距离 + 两点之前的距离 与 之前最短距离 的最小值
                if(dp[e.from] != Integer.MAX_VALUE){   //如果是正无穷再加会出现负值，导致出错，也没有意义
                    dp[e.to] = Integer.min(dp[e.to], dp[e.from] + e.weight);
                }
        }


        }
        print(dp);
    }

    static void print(int[] dp) {
        System.out.println(Arrays.stream(dp)
                .mapToObj(i -> i == Integer.MAX_VALUE ? "∞" : String.valueOf(i))
                .collect(Collectors.joining(",", "[", "]")));
    }
}
