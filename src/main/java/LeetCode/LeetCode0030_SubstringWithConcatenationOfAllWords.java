package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LeetCode0030_SubstringWithConcatenationOfAllWords {
    /**
     * Algorithms and DataStructure: Sliding Window & HashMap
     * Time Complexity: O(num) + O(m * n/m) = O(num) + O(n) = O(num + n) = O(n)  ps:通常 n 会比 num 大，所以时间复杂度主要是 O(n)。
     * Space Complexity: O(num)
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length();
        int m = words[0].length();
        int num = words.length;

        List<Integer> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        if (n < m * num) {
            return res;
        }

        for(String word : words){                 //O(num)
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        for(int i = 0; i < m; i++){     //O(m)
            int r = i;
            int l = i;
            Map<String, Integer> record = new HashMap<>();
            int count = 0;
            while(r <= n - m ){                   //O(n/m)
                String str = s.substring(r , r + m);
                r = r + m;
                if(map.containsKey(str)){       //O(1)
                    record.put(str,record.getOrDefault(str,0)+1);
                    count ++;

                    while(record.get(str) > map.get(str)){
                        String left = s.substring(l, l + m);
                        record.put(left,record.get(left)-1);
                        l = l + m;
                        count --;
                    }

                    if(count == num){
                        res.add(l);
                    }
                }
                else{
                    record.clear();
                    count = 0;
                    l = r;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode0030_SubstringWithConcatenationOfAllWords solution = new LeetCode0030_SubstringWithConcatenationOfAllWords();

        // Test Case 1: Basic example
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        // Expected Output: [0, 9]

        // Test Case 2: Words repeated in the string
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        // Expected Output: [] (Exact match not possible because 'word' appears more than once)

        // Test Case 3: Substring with overlapping words
        System.out.println(solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"}));
        // Expected Output: [6, 9, 12]

        // Test Case 4: Empty string input
        System.out.println(solution.findSubstring("", new String[]{"foo", "bar"}));
        // Expected Output: []

        // Test Case 5: Words array with empty word
        System.out.println(solution.findSubstring("foobarbaz", new String[]{""}));
        // Expected Output: [] (Empty words are invalid)

        // Test Case 6: Single character words
        System.out.println(solution.findSubstring("ababa", new String[]{"a", "b"}));
        // Expected Output: [0, 1, 2, 3]

        // Test Case 7: Large input (Performance test)
        String largeInput = "a".repeat(10000) + "foobar";
        System.out.println(solution.findSubstring(largeInput, new String[]{"foo", "bar"}));
        // Expected Output: [10000]

        // Test Case 8: No matching substrings
        System.out.println(solution.findSubstring("abcde", new String[]{"x", "y", "z"}));
        // Expected Output: []

        // Test Case 9: String shorter than concatenated words
        System.out.println(solution.findSubstring("foo", new String[]{"foo", "bar"}));
        // Expected Output: []

        // Test Case 10: All words match exactly once, no overlap
        System.out.println(solution.findSubstring("foobar", new String[]{"foo", "bar"}));
        // Expected Output: [0]
    }
}
