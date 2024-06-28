package DataStructure.HashTable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void testGet(){
        HashTable table = new HashTable();
        HashTable.Entry e0 = new HashTable.Entry(0, "沙", "沙僧");
        e0.next = new HashTable.Entry(16, "于", "于谦");
        HashTable.Entry e1 = new HashTable.Entry(1, "空", "空见");

        table.table[0] = e0;
        table.table[1] = e1;

        assertEquals("沙僧", table.get(0, "沙"));
        assertEquals("于谦", table.get(16, "于"));
        assertNull(table.get(0, "空"));
        assertEquals("空见", table.get(1, "空"));
        assertNull(table.get(3, "空"));
    }

    @Test
    void testPut(){
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三"); // 1 % 16 == 1
        table.put(17, "li", "李四");   // 17 % 16 == 1
        table.put(2, "wang", "王五");  // 2

        assertEquals(3, table.size);
        assertEquals("张三", table.table[1].value);
        assertEquals("李四", table.table[1].next.value);
        assertEquals("王五", table.table[2].value);

        table.put(1, "zhang", "张4");
        table.put(17, "li", "李5");
        assertEquals("张4", table.table[1].value);
        assertEquals("李5", table.table[1].next.value);
    }

    @Test
    void testRemove1() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三");  // 1
        table.put(17, "li", "李四");    // 1
        table.put(2, "wang", "王五");

        table.remove(1, "zhang");
        assertEquals(2, table.size);
        assertEquals("李四", table.table[1].value);
        assertNull(table.table[1].next);
    }

    @Test
    void testRemove2() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三"); // 1
        table.put(17, "li", "李四");   // 1
        table.put(2, "wang", "王五");

        table.remove(17, "li");
        assertEquals(2, table.size);
        assertEquals("张三", table.table[1].value);
        assertNull(table.table[1].next);
    }

}