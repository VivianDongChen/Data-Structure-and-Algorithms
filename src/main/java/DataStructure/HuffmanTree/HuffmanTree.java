package DataStructure.HuffmanTree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Haffman树及编解码
 */
public class HuffmanTree {
    /*
      Huffman树的构建过程
      1. 将统计了出现频率的字符， 放入优先级列表
      2. 每次出队两个频次最低的元素， 给它俩找个爹
      3. 把爹重新放入队列，重复2～3
      4. 当队列只剩一个元素时， Huffman树构建完成
     */
    static class Node{
        Character ch; //字符
        int freq; //频次
        Node left;
        Node right;
        String code; //编码, 用字符表示，这样比较简单

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int freq() {
            return freq;
        }

        boolean isLeaf(){
            return left == null; //Huffman树为满二叉树，左孩子为空即为叶子节点
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    ", left=" + left +
                    ", right=" + right +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
    String str;
    Map<Character, Node> map = new HashMap<>();  //用map来盛放字符和对应的Node

    /**
     * 构造器接收一个字符串，分别拿到这个字符串中的每一个字符和它的Node
     * @param str
     */
    public HuffmanTree(String str){
        this.str = str;
        char[] chars = str.toCharArray();
        for(char c : chars){
//            if(!map.containsKey(c)){
//                map.put(c,new Node(c));
//            }
//            Node node = map.get(c);
//            node.freq++;
            Node node = map.computeIfAbsent(c,Node::new);
            node.freq++;   //功能1:统计频次
        }
        //功能2: 构造树(将所有node放入优先级队列，以出现频率排序）
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::freq));
        queue.addAll(map.values());
        while(queue.size() >= 2){
            Node x = queue.poll();
            Node y = queue.poll();
            int freq = x.freq() + y.freq();
            queue.offer(new Node(freq,x,y));
        }
        Node root = queue.poll();
        //功能3: 计算每个字符的编码， 功能4: 字符串编码后占用bits
        int sum = dfs(root, new StringBuilder());
        for(Node node : map.values()){
            System.out.println(node + " " + node.code);
        }
        System.out.println("总共占用bits："+ sum);
    }

    private int dfs(Node node, StringBuilder code) {
        int sum = 0;
        if(node.isLeaf()){
            //找到编码, 记录到node
            node.code = code.toString();
            sum = node.freq * code.length();
        }else{
            sum += dfs(node.left, code.append("0"));
            sum += dfs(node.right, code.append("1"));
        }
        if(code.length() > 0){      //回溯到父节点
            code.deleteCharAt(code.length() -1);
        }
        return sum;

    }

    public static void main(String[] args) {
        new HuffmanTree("abbccccccc");

    }
}
