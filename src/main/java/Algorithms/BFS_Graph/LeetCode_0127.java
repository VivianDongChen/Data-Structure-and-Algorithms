package Algorithms.BFS_Graph;

import java.util.*;

/**
 * 127. Word Ladder
 * 难点：双向BFS
 * 每次遍历前选出 beginSet 和 endSet 中元素较少的那个是 双向 BFS 的优化策略之一，目的是为了 减少搜索空间，提高算法效率。
 */
public class LeetCode_0127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();
        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0; // 如果 endWord 不在 wordList 中，直接返回 0
        }

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {       //两个set取元素较少的进行遍历
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {       //每一层：表示将当前所有单词改一个字符后的所有可能性
                char[] chs = word.toCharArray();      //string转换为Char Array便于遍历

                for (int i = 0; i < chs.length; i++) {
                    char old = chs[i];   //保存原字符

                    for (char c = 'a'; c <= 'z'; c++) {         //每一个字符有26种可能
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;    //找到了，要+1（最后一步）
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }

                    }
                    chs[i] = old;     //字符换回来
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;   //循环结束没有退出，说明没有实现，返回0
    }

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        LeetCode_0127 test = new LeetCode_0127();

        System.out.println(test.ladderLength(beginWord, endWord, wordList));


    }
}
