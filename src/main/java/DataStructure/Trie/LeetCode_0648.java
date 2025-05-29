package DataStructure.Trie;

import java.util.Arrays;
import java.util.List;

//Replace Words  hngtgt
public class LeetCode_0648 {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private void insert(TrieNode root, String word){
        TrieNode node = root;
        for(char c : word.toCharArray()){
            int index = c -'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.word = word;
    }

    public String replaceWords (List<String> dictionary, String sentence){
        StringBuilder res = new StringBuilder();
        TrieNode root = new TrieNode();
        for(String word : dictionary){
            insert(root, word);
        }

        for(String word : sentence.split("\\s+")){
            if(res.length() > 0){
                res.append(" ");
            }
            TrieNode node = root;
            for(char c : word.toCharArray()){
                int index = c - 'a';
                if(node.children[index] == null || node.word != null){
                    break;
                }
                node = node.children[index];

            }
            res.append(node.word != null ? node.word : word);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        LeetCode_0648 test = new LeetCode_0648();
        List<String> dictionary = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        String res = test.replaceWords(dictionary, sentence);
        System.out.println(res);  // Expected: "the cat was rat by the bat"
    }

}