package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * So, for this 'Substring with Concatenation of All Words' problem, here’s how I approached it.
 *
 * ## Thought Process
 *
 * The challenge was to find all starting positions in the string `s` where a substring is exactly a concatenation of all the words from the `words` list — each word must appear exactly once, and the words should be placed next to each other without any gaps. I knew that brute force would be too slow for larger inputs, so I thought about using the **Sliding Window technique** combined with **HashMaps** to keep things efficient.
 *
 * ## What I Did
 *
 * 1. **Checked the Easy Cases First**:
 *    - The total length of all the words together is `num * m` (where `num` is the number of words and `m` is the length of each word). If the string `s` is shorter than this, I just returned an empty list since there’s no way a valid substring can fit.
 *
 * 2. **Used a HashMap to Track Word Frequencies**:
 *    - I built a HashMap to store how often each word in the `words` array should appear. This made it easy to check if the words in my current window matched the expected counts.
 *
 * 3. **Handled Multiple Starting Positions with Sliding Window**:
 *    - Because all the words have the same length, I had to slide the window starting from **every offset** between `0` and `m-1` (to account for all possible starting points). For each offset, I used two pointers:
 *      - **Left pointer (`l`)**: Marks the start of the current window.
 *      - **Right pointer (`r`)**: Moves through the string in steps of `m` to grab words one by one.
 *
 * 4. **Tracked Words in the Current Window**:
 *    - If a word from the string matched one in the HashMap, I added it to another HashMap to keep track of the counts in the current window.
 *    - If any word showed up too many times, I shrank the window by moving the left pointer `l` to the right, making sure the counts stayed correct.
 *    - If all the words matched perfectly, I recorded the starting index as a valid result.
 *
 * 5. **Reset the Window if Needed**:
 *    - If I found a word that wasn’t in the `words` list, I cleared the current window and started fresh from the next position.
 *
 * ## Time Complexity
 *
 * - The solution runs in **O(n)** time, where `n` is the length of the input string. The reason is that I slide through the string just a few times, and checking each word or updating counts is done in constant time.
 * - Building the frequency map takes **O(num)** time, but since the length of the string is usually much larger than the number of words, the overall time complexity simplifies to **O(n)**.
 *
 * ## Space Complexity
 *
 * - I use two HashMaps: one to store the word frequencies and another to track the current window’s word counts. So, the space complexity is **O(num)**, where `num` is the number of words.
 *
 * ## Why This Solution Works Well
 *
 * Using a sliding window with multiple offsets ensures I don’t miss any possible starting positions. The HashMaps allow me to efficiently track word frequencies and compare them on the fly. This way, the solution stays both **fast** and **clean**, even with larger inputs.
 *
 * Overall, this approach avoids unnecessary checks, ensures the string is only traversed a few times, and strikes a good balance between readability and performance.
 */
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
