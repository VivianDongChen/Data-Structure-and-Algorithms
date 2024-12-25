package Algorithms.BinarySearch;

import DataStructure.PriorityQueue.LeetCode_1268_I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1268. Search Suggestions  --- 方法2: Binary Search
 * 时间复杂度
 * -排序：O(N⋅logN)，其中N是 products 的长度。
 * -每个前缀的二分查找：O(logN)，searchWord的长度为L，则总共O(L⋅logN)。
 * -前缀匹配检查：每个前缀最多检查 3 个字符串，复杂度为O(1)。
 * 总复杂度：O(N⋅logN+L⋅logN)
 * -空间复杂度
 * -返回结果需要O(L⋅3) 的空间。
 * -额外空间主要是排序的空间开销：Arrays.sort(products) 对 String[] 排序的 空间复杂度是O(N)
 */
public class LeetCode_1268_II {
    public List<List<String>> suggestedProducts(String[] products, String searchWord){
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(products);

        int start = 0;
        int n = products.length;
        String prefix = "";
        for(char c: searchWord.toCharArray()){
            prefix += c;
            start = binarySearchPrefix(products,prefix,start);
            List<String> templist = new ArrayList<>();
            for(int i = start; i < n; i++){
                if(templist.size() < 3 && products[i].startsWith(prefix)){
                    templist.add(products[i]);
                }
            }
            res.add(templist);
        }
        return res;
    }

    /**
     * 使用二分查找快速找到第一个符合当前前缀的产品
     */
    private int binarySearchPrefix(String[] products, String prefix, int start){
        int i = start;
        int j = products.length - 1;
        while(i < j) {
            int mid = i + (j - i) / 2;
            if (products[mid].compareTo(prefix) < 0) {   //(products[mid] < prefix
                i = mid + 1;
            } else {      //(products[mid] >= prefix
                j = mid;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        String[] products1 = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord1 = "mouse";

        String[] products2 = new String[]{"havana"};
        String searchWord2 = "havana";

        String[] products3 = new String[]{"bags","baggage","banner","box","cloths"};
        String searchWord3 = "bags";

        LeetCode_1268_II test = new LeetCode_1268_II();

        System.out.println(test.suggestedProducts(products1, searchWord1));  //expected: [[mobile, moneypot, monitor], [mobile, moneypot, monitor], [mouse, mousepad], [mouse, mousepad], [mouse, mousepad]]
        System.out.println(test.suggestedProducts(products2, searchWord2));  //expected: [[havana], [havana], [havana], [havana], [havana], [havana]]
        System.out.println(test.suggestedProducts(products3, searchWord3));  //expected: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
    }
}
