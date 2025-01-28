package DataStructure.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 212. Word Search II
 * 使用到的数据结构和算法：Trie，DFS遍历， 回溯
 * 理解难点：
 * 将路径上已经访问过的字符标记为 #，然后在上下左右的递归全部完成之后再恢复为原值，其主要目的是为后续新的路径探索做好准备，避免冲突或错误。
 * 新的路径探索意味着从 findWords 方法中的新起点开始遍历，即在二维网格 board 中选择一个新的起点节点。
 * 每次从新的起点出发，DFS 都会从当前节点开始沿着可能的路径进行匹配，遇到不匹配时会立即返回。
 */
public class LeetCode_0212 {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = buildTrie(words);    //将words中的String放入一个trie中
        Set<String> res = new HashSet<>();
        for (int i = 0; i < board.length; i++) {     //遍历这个board
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, trie, res, i, j);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, Trie node, Set<String> res, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
                board[i][j] == '#' || node.next[board[i][j] - 'a'] == null) {
            return;     // 越界、已访问或无法匹配，直接返回
        }

        if (node.next[board[i][j] - 'a'].word != null) {   //到达这个条件说明：前面每一层的字符都已经匹配成功，当前节点是某个单词的结尾
            res.add(node.next[board[i][j] - 'a'].word);       //当前单词匹配成功， 放入res
        }

        // Go to next char
        node = node.next[board[i][j] - 'a'];   // （这一层匹配上了）转到下一层节点
        char c = board[i][j];  // 保存当前位置字符
        board[i][j] = '#';     // 标记当前位置为已访问

        // 递归探索上下左右四个方向
        dfs(board, node, res, i - 1, j); // 上
        dfs(board, node, res, i + 1, j); // 下
        dfs(board, node, res, i, j - 1); // 左
        dfs(board, node, res, i, j + 1); // 右

        board[i][j] = c; // 恢复当前位置，回溯。
    }

    public Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String w : words) {      //遍历列表中的每一个String，依次放入Trie
            Trie p = root;
            for (char c : w.toCharArray()) {     //遍历String中的每一个字符
                if (p.next[c - 'a'] == null) {
                    p.next[c - 'a'] = new Trie();
                }
                p = p.next[c - 'a'];       //p指向下一层
            }
            p.word = w;     //p到达最后一层，赋值word
        }
        return root;
    }

    private class Trie {
        Trie[] next = new Trie[26];
        String word = null;   // word记录路径上的String
    }


    public static void main(String[] args) {
        LeetCode_0212 solution = new LeetCode_0212();

        // 定义 board
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        // 定义 words
        String[] words = {"oath", "pea", "eat", "rain"};

        // 调用 findWords 方法
        List<String> result = solution.findWords(board, words);

        // 输出结果
        System.out.println("Found Words: " + result);
    }


}
