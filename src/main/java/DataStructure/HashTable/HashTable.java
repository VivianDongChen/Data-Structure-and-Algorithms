package DataStructure.HashTable;

import com.google.common.hash.Hashing;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 哈希表
 *
 * 给每份数据分配一个编号，放入表格（数组）
 * 建立编号与表格索引的关系，将来就可以通过编号快速查找数据
 * 1. 理想情况编号当唯一，数据能容纳所有数据
 * 2. 现实是不能为了容纳所有数据造一个超大数组，编号也有可能重复
 * 解决
 * 1. 有限长度的数组，以【拉链】方式存储数据
 * 2. 允许编号适当重复，通过数据自身来进行区分
 */
public class HashTable {


    /**
     * 节点类
     */
    static class Entry{

        int hash; //哈希码
        Object key;  //键
        Object value; //值
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];   //一个2的N次方大小的数组, 存储所有链表的头
    int size = 0; //元素个数
    float loadFactor = 0.75f;
    int threshold = (int)(loadFactor * table.length);    //16*0.75 = 12 阈值

    /*
       hash码映射索引
       索引、hash、数组长度的关系最简单的可以用求模运算（Mod）：
       Hash % 数组长度 = 索引
       但是求模运算的性能不好，可以将求模运算替换为性能更好的位运算：
       - 前提：数组长度是2的N次方
       - hash % 数组长度 等价于 hash &（数组长度 - 1）   &：按位于
     */

    /**
     * 根据hash码获取value
     */
    Object get(int hash, Object key){
        int idx = hash & (table.length - 1);
        if(table[idx] == null){       //链表头为空，即没有链表
            return null;
        }
        //遍历链表
        Entry p = table[idx];
        while(p != null){
            if(p.key.equals(key)){
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 向hash表存入新key value，
     * - 如果idx处是null， 新增
     * - 如果key重复，则更新value,
     * - 如果没有找到key，在链表尾部新增(尾插法）   也可以头插法（JDK的HashTable早期使用的头插法，后来为了避免多线程状态下造成死循环，改成了尾插法）
     */
    void put(int hash, Object key, Object value){
        int idx = hash & (table.length - 1);
        if(table[idx] == null){
            //1. idx处有空位，直接新增
            table[idx] = new Entry(hash,key,value);
        }else{
            //2. idx处无空位，沿链表查找，有重复key更新，否则新增
            Entry p = table[idx];
            while(true){
                if(p.key.equals(key)){
                    p.value = value;  //更新
                    return;
                }
                if (p.next == null) {
                    break;
                }
                p = p.next;
            }
            p.next = new Entry(hash,key,value); //新增
        }
        size ++;
        if(size > threshold){
            resize();
        }
    }

    private void resize(){
        Entry[] newTable = new Entry[table.length  << 1];    //左移一位， 即乘以2
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];  //拿到每个链表头
            if( p != null){
                // 拆分链表，移动到新数组
                /*
                  拆分规律
                  - 一个链表最多拆成两个
                  - hash & table.length == 0 的一组
                  - hash & table.length ！= 0 的一组

                                                           p
                  0 -> 8 -> 16 -> 24 -> 32 -> 40 -> 48 -> null
                                          a
                  0 -> 16 -> 32 -> 48 -> null
                              b
                  8 -> 24 -> 40 -> null
                 */
                Entry a = null;
                Entry b = null;
                Entry aHead = null;  //a链表头
                Entry bHead = null;  //b链表头
                while (p != null){
                    if((p.hash & table.length) == 0){
                        if(a != null){
                            a.next = p;
                        }else{
                            aHead = p;
                        }
                        a = p;  //分配到a链表
                    }else{
                        if(b != null){
                            b.next = p;
                        }else{
                            bHead = p;
                        }
                        b = p;  //分配到b链表
                    }
                    p = p.next;
                }
                //规律：a链表保持索引位置不变，b链表索引位置+table.length
                if(a != null){
                    a.next = null;
                    newTable[i] = aHead;
                }
                if(b != null){
                    b.next = null;
                    newTable[i+ table.length] = bHead;
                }
            }
        }
        table = newTable;
        threshold = (int)(loadFactor * table.length);
    }

    /**
     * 根据hash码删除，返回删除的value
     */
    Object remove(int hash, Object key){
        int idx = hash & (table.length - 1);
        if(table[idx] == null){       //链表头为空，即没有链表
            return null;
        }
        //遍历链表
        Entry p = table[idx];
        Entry prev = null;
        while(p != null){
            if(p.key.equals(key)){
                //找到了，删除
                if(prev == null){   //如果删除的是链表头
                    table[idx] = p.next;
                }else{
                    prev.next = p.next;
                }
                size--;
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    /*
                1）为什么计算索引位置用式子：【hash & (数组长度-1)】 等价于 【hash % 数组长度】
                    10进制中去除以 10，100，1000时，余数就是被除数的后1，2，3 位
                                10^1 10^2 10^3
                    2进制中去除以 10，100，1000时，余数也是被除数的后1，2，3 位
                                2^1 2^2 2^3 2^4
                    因此求余数就是求二进制的后几位，而保留二进制后几位可以通过与
                    1，3，7，11 ... 等数字按位与来实现，这些数字恰巧是数组长度-1

                2）为什么旧日链表会拆分成两条，一条 hash & 旧数组长度==0 另一条！=0 ?
                    旧数组长度换算成二进制后，其中的 1 就是我们要检查的倒数第几位
                    旧数组长度 8 二进制 => 1000 检查倒数第4位
                    旧数组长度 16 二进制 => 10000 检查倒数第5位
                    hash & 旧数组长度 就是用来检查扩容前后索引位置（余数）会不会变

                3）为什么拆分后的两条链表，一个原索引不变，另一个是原索引+旧数组长度 ？

                注意：它们都有个共同的前提：数组长度是2的n次方
             */

    public Object get(Object key){
        int hash = hash(key);
        return get(hash,key);
    }

    public void put(Object key, Object value){
        int hash = hash(key);
        put(hash, key,value);
    }
    public Object remove(Object key){
        int hash = hash(key);
        return remove(hash, key);
    }
    private static int hash(Object key) {
        if(key instanceof String k){   //如果是String类型的key，用murmurHash函数生成hash码
            return Hashing.murmur3_32_fixed().hashString(k, StandardCharsets.UTF_8).asInt();
        }
        return key.hashCode();  //其它类型的key，还是用JDK自带的方式生成hash码
    }


    public void print(){
        int[] sums = new int[table.length];   //创建一个数组，其长度与哈希表的数组长度相同，用于存储每个索引位置上链表中的节点数量。
        for (int i = 0; i < table.length; i++) {
            Entry p = table[i];
            while (p != null){
                sums[i] ++;
                p = p.next;
            }
        }
        //将sums数组转换为流，使用Collectors.groupingBy统计每个节点数量出现的次数，并将结果存储在collect映射中。
        Map<Integer, Long> collect = Arrays.stream(sums).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(collect);

    }

    public static void main(String[] args) throws IOException {

        //测试Object哈希算法的散列性能(冲突测试）
        HashTable table1 = new HashTable();
        for (int i = 0; i < 200000; i++) {
            Object obj = new Object();
            table1.put(obj,obj);    //使用一个循环插入20万个键值对，键和值均为新创建的Object对象。
        }
        table1.print();

        //测试String哈希算法/MurmurHash的散列性能（冲突测试）
        HashTable table2 = new HashTable();
        //从文件words中读取所有行，并存储在一个List<String> 中
        List<String> strings = Files.readAllLines(Path.of
                ("words"));
        for(String string : strings){
            table2.put(string, string);
        }
        table2.print();

        int hash = Hashing.murmur3_32_fixed().hashString("abc", StandardCharsets.UTF_8).asInt();
        System.out.println(hash);
    }

}
