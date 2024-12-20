package Algorithms.DFS_Graph;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1466 {
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> al = new ArrayList<>(); //定义一个嵌套列表，用来存放每个城市
        for(int i = 0; i < n; i++){   //定义这个嵌套列表中的每一个列表， 用来存放这个城市的每一个相邻城市
            al.add(new ArrayList<>());
        }
        for(var c : connections){  //遍历二维数组（每一条路）
            al.get(c[0]).add(c[1]);   //取每个数组的第一个元素作为列表的索引（from城市），第二个元素是其相邻城市（to城市），放入对应的列表
            al.get(c[1]).add(-c[0]);   //反过来，这个相邻城市对应的列表中放入其from城市，前面加“-”， 表示路是 from -> to
        }
        return dfs(al, new boolean[n], 0);   //进入图的遍历，等遍历完成（所有城市都已经被遍历过），得到的即是结果
    }

    //使用图的深度遍历来检查每一条路
    private int dfs(List<List<Integer>> al, boolean[] visited, int from){
        visited[from] = true;  //用boolean数组来记录该城市是否已经被遍历过
        int count = 0;   //需要调转方向的路
        for(int to : al.get(from)){     //检查与该城市相邻的城市
            if(!visited[Math.abs(to)]){
                //接着递归遍历相邻城市，并加上这一层识别出的count
                //如果该相邻城市为正数说明路径是 列表索引 -> 列表元素, 题目的要求是每一个城市的路的方向是朝向0，所以从城市0开始，如果相邻城市是正数，应该调转方向
                count += dfs(al, visited, Math.abs(to)) + (to > 0? 1:0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n1 = 6;
        int[][] connections1 = {
                {0, 1},
                {1, 3},
                {2, 3},
                {4, 0},
                {4, 5}
        };
        int n2 = 5;
        int[][] connections2 = {
                {1, 0},
                {1, 2},
                {3, 2},
                {3, 4}
        };
        int n3 = 3;
        int[][] connections3 = {
                {1, 0},
                {2, 0},
        };

        LeetCode_1466 test = new LeetCode_1466();
        System.out.println(test.minReorder(n1, connections1));  // expected: 3
        System.out.println(test.minReorder(n2, connections2));  // expected：2
        System.out.println(test.minReorder(n3, connections3));  // expected：0
    }
}
