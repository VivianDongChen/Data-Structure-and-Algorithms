package DataStructure.Trie;

/**
 * 211. Design Add and Search Words Data Structure
 * 难点： 字符的add和search都是从curr.children开始的
 */
public class LeetCode_0211 {
   class TrieNode{
       private TrieNode[] children;
       private boolean isEnd;

       public TrieNode(){
           children = new TrieNode[26];
           isEnd = false;
       }
   }
   TrieNode root;

   public LeetCode_0211(){
       root = new TrieNode();
   }

   public void addWord(String word){
       TrieNode curr = root;
       for(char ch : word.toCharArray()){
           if(curr.children[ch -'a'] == null){
               curr.children[ch -'a'] = new TrieNode();
           }
           curr = curr.children[ch -'a'];
       }
       curr.isEnd = true;
   }

   public boolean search(String word){
       return searchHelper(word, 0, root);
   }

   private boolean searchHelper(String word, int index, TrieNode curr){
       if(curr == null){
           return false;
       }
       if(index == word.length()){
           return curr.isEnd;
       }
       char ch = word.charAt(index);
       if(ch == '.'){
           for(TrieNode child : curr.children){
               if(searchHelper(word, index + 1, child)){
                   return true;
               }
           }
           return false;
       }else{
           return searchHelper(word, index + 1, curr.children[ch - 'a']);
       }

   }


    public static void main(String[] args) {
        // 创建 WordDictionary 对象
        LeetCode_0211 wordDictionary = new LeetCode_0211();

        // 添加单词
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        // 测试搜索功能
        System.out.println("Search 'pad': " + wordDictionary.search("pad")); // 预期输出: false
        System.out.println("Search 'bad': " + wordDictionary.search("bad")); // 预期输出: true
        System.out.println("Search '.ad': " + wordDictionary.search(".ad")); // 预期输出: true
        System.out.println("Search 'b..': " + wordDictionary.search("b..")); // 预期输出: true
    }
}
