package DataStructure.Trie;

/**
 * 211. Design Add and Search Words Data Structure
 * 难点： 字符的add和search都是从curr.children开始的
 */
public class LeetCode_0211 {
    private LeetCode_0211[] children;   //要素1: 孩子（有26个元素的数组，数组的索引代表a-z，值是null或者not null）
    boolean isEndOfWord; //要素2:是否为EndOfWord

    // Initialize your data structure here.
    public LeetCode_0211() {
        children = new LeetCode_0211[26];
        isEndOfWord = false;
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        LeetCode_0211 curr = this; //将当前对象（this）赋值给局部变量 curr
        for(char c: word.toCharArray()){ // 遍历word的每一个字符
            if(curr.children[c - 'a'] == null) //如果局部变量的孩子的字符位为null
                curr.children[c - 'a'] = new LeetCode_0211();  //在该位置定义一个新的LeetCode_0211（这个时候就不为null了）
            curr = curr.children[c - 'a'];  //遍历下一层
        }
        curr.isEndOfWord = true; //遍历完成后，这个时候curr指向了word的最后一个自符，所以将isEndOfWord赋值为true
    }

    // Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        LeetCode_0211 curr = this;
        for (int i = 0; i < word.length(); ++i) {  //遍历word的所有字符
            char c = word.charAt(i);

            if (c == '.') {//如果字符为“."
                for (LeetCode_0211 ch : curr.children) { //遍历curr孩子中的所有字符
                    if (ch != null && ch.search(word.substring(i + 1))) {
                        return true;//如果孩子返回true，这一层也返回true
                    }
                }
                return false; //否则（循环结束）返回false
            } else if (curr.children[c - 'a'] == null) {       //如果不是“.", 查看是否为null， 如果是的话，返回false
                return false;
            } else {
                curr = curr.children[c - 'a'];       //排除以上两种情况后，curr继续遍历下一层
            }
        }
        return curr.isEndOfWord;  //遍历结束后，看下最后这个curr是否EndOfWord，如果是的话，返回true
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
