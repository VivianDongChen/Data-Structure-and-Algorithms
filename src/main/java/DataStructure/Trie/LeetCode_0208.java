package DataStructure.Trie;

import java.sql.SQLOutput;

public class LeetCode_0208 {
    class TrieNode{
        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private TrieNode root;

    public LeetCode_0208() {
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;
        for(char ch: word.toCharArray()){
            int index = ch - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    //search the word to see if it is inside the trie
    public boolean search(String word){
        TrieNode node = searchWord(word);
        return node != null && node.isEnd;
    }

    public boolean startWith(String prefix){
        return searchWord(prefix) != null;
    }

    private TrieNode searchWord(String word){
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            int index = ch - 'a';
            if(node.children[index] == null){
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    public static void main(String[] args) {
        LeetCode_0208 testCase = new LeetCode_0208();
        testCase.insert("apple");
        System.out.println(testCase.search("banana"));
        System.out.println(testCase.startWith("banana"));
        System.out.println(testCase.search("apple"));
        System.out.println(testCase.startWith("app"));
    }
}
