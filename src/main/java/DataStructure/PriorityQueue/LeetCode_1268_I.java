package DataStructure.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1268. Search Suggestions  -- 方法1: minHeap
 * Time Complexity：
 * 外层循环遍历 searchWord 的每个前缀，运行L 次。
 * 内层循环遍历 products，对每个字符串执行 startsWith()，运行O(N⋅P)。
 * 总时间复杂度为：O(L⋅N⋅P)
 * N：products 数组的大小（产品数量）。
 * L：searchWord 的长度（前缀数量）。
 * P：products 数组中每个字符串的平均长度。
 */
public class LeetCode_1268_I {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        PriorityQueue<String> pq = new PriorityQueue<>(3, (s1,s2) -> s1.compareTo(s2));    // String.compareTo()
        List<List<String>> res = new ArrayList<>();
        for(int i = 1; i <= searchWord.length(); i++){
            String temp = searchWord.substring(0,i);    //String. substring()
            for(String product : products){
                if(product.startsWith(temp)){    //String.startsWith()
                    pq.offer(product);
                }
            }

            List<String> tempList = new ArrayList<>();
            for(int j = 0; j < 3; j++){
                if(!pq.isEmpty()){
                    tempList.add(pq.poll());
                }
            }
            pq.clear();
            res.add(tempList);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] products1 = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord1 = "mouse";

        String[] products2 = new String[]{"havana"};
        String searchWord2 = "havana";

        LeetCode_1268_I test = new LeetCode_1268_I();

        System.out.println(test.suggestedProducts(products1, searchWord1));  //expected: [[mobile, moneypot, monitor], [mobile, moneypot, monitor], [mouse, mousepad], [mouse, mousepad], [mouse, mousepad]]
        System.out.println(test.suggestedProducts(products2, searchWord2));  //expected: [[havana], [havana], [havana], [havana], [havana], [havana]]

    }

}
