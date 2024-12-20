package Algorithms.TwoPointers;

/**
 * 最长公共前缀
 */
public class LeetCode_0014 {
    static String longestCommonPrefix(String[] strings){
        /*
        指针j定位具体的字符串
        指针i遍历每个字符串的字符

          情况1: 比较某一列时， 遇到不同字符， i之前的字符就是解
          情况2: 比较某一列时，遇到字符串长度不够，i之前的字符就是解
          情况3: i循环自然结束，此时第一个字符串就是解
        */

        char[] first = strings[0].toCharArray();   //拿到第一个字符串，转化成字符数组，以便于遍历
        for (int i = 0; i < first.length; i++) {
            char ch = first[i];
            for (int j = 1; j < strings.length; j++) {
//                if(strings[j].length() == i){     //情况2 (放在情况1前面，如果不这么做，会索引越界）
//                    return new String(first, 0, i);
//                }
//                if(ch != strings[j].charAt(i)){    //情况1
//                    return new String(first, 0, i);
//                }
                if(strings[j].length() == i || ch != strings[j].charAt(i)){
                    return new String(first, 0, i);
                }
            }
        }
        return strings[0];  //情况3
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // fl
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"})); // 空串
        System.out.println(longestCommonPrefix(new String[]{"ab", "a"})); // a
        System.out.println(longestCommonPrefix(new String[]{"dog", "dogaa", "dogbb"})); // dog
    }
}
