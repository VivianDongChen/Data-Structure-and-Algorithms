package Algorithms.Backtracking;

/**
 * 79. Word Search
 * 剪枝优化：如果单词中的某个字符在 board 中出现的次数少于单词中该字符的出现次数，可以直接返回 false。
 */
public class LeetCode_0079 {
    public boolean exist(char[][] board, String word){

        // 字符频率剪枝优化
        int[] boardCount = new int[128]; // ASCII 字符集
        int[] wordCount = new int[128];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boardCount[board[i][j]]++;
            }
        }
        for (char c : word.toCharArray()) {
            wordCount[c]++;
        }
        for (char c : word.toCharArray()) {
            if (boardCount[c] < wordCount[c]) {
                return false; // 剪枝
            }
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                boolean res = dfs(board, i, j, word, 0);
                if(res){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#'
                || board[i][j] != word.charAt(index)){
           return false;
        }

        if(index == word.length() - 1){
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '#';

         boolean found = dfs(board, i+1, j, word, index + 1) ||
                 dfs(board, i-1, j, word, index + 1) ||
                 dfs(board, i, j+1, word, index + 1) ||
                 dfs(board, i, j-1, word, index + 1);

        board[i][j] = temp;
        return found;
    }

    public static void main(String[] args) {
        LeetCode_0079 solution = new LeetCode_0079();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word1 = "ABCCED"; // true
        String word2 = "SEE";    // true
        String word3 = "ABCB";   // false

        System.out.println(solution.exist(board, word1)); // true
        System.out.println(solution.exist(board, word2)); // true
        System.out.println(solution.exist(board, word3)); // false
    }
}
