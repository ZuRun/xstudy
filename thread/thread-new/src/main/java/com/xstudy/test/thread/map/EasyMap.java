package com.xstudy.test.thread.map;


/**
 * 自行实现hashmap
 *
 * @author zurun
 * @date 2019-07-13 22:25:39
 */
public class EasyMap<K, V> {
    Node<K, V>[] table;
    private int capacity;
    // 元素数量
    private int size = 0;

    public EasyMap() {
        this(16);
    }

    public EasyMap(int capacity) {
        this.capacity = capacity;
    }

    public void put(K key, V val) {
        resize();
        Node newNode = new Node(key, val, hash(key), null);
        int nodeIndex = indexFor(key);
        if (table[nodeIndex] == null) {
            table[nodeIndex] = newNode;
        } else {
            // 此处应该要遍历链表，判断当key已存在时，覆盖，而不是新增
            Node<K, V> oldNode = table[nodeIndex];
            table[nodeIndex] = newNode;
            newNode.next = oldNode;
        }
        size++;
    }

    /**
     * 暂不考虑key为null
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int index = indexFor(key);
        if (table[index] == null) {
            return null;
        }
        Node<K, V> first = table[index];
        if (first.key.equals(key)) {
            return first.val;
        } else {
            Node<K, V> node = first.next;
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.val;
                }
                node = node.next;
            }
            return null;
        }
    }

    /**
     * 根据key的hash获取此node下标
     *
     * @param key
     * @return
     */
    private int indexFor(K key) {
        return indexFor(key, table.length);
    }

    private int indexFor(K key, int capacity) {
        int index = hash(key) & (capacity - 1);
        return index;
    }

    /**
     * key的hash
     *
     * @param key
     * @return
     */
    int hash(K key) {
        return key.hashCode();
    }

    /**
     * 此方法需要重写
     */
    final void resize() {
        if (table == null) {
            table = new Node[capacity];
            return;
        }
        if (size >= capacity) {
            int newCap = capacity << 1;
            System.out.println("需要扩容,当前：" + capacity + ",table长度：" + size + "，扩容后长度：" + newCap);

            Node[] newTable = new Node[newCap];
            transfer(newTable);
            table = newTable;
            capacity = newCap;
        }

    }

    /**
     * 扩容时，重新计算元素位置
     *
     * @param newTable
     */
    final void transfer(Node<K, V>[] newTable) {
        Node<K, V>[] old = table;
        int newCapacity = newTable.length;
        // 遍历旧数组
        for (int i = 0; i < old.length; i++) {
            Node<K, V> kvNode = old[i];
            if (kvNode != null) {
                // 释放老数组的引用
                old[i] = null;
                // 遍历链表
                while (kvNode != null) {
                    Node<K, V> next = kvNode.next;
                    int index = indexFor(kvNode.key, newCapacity);
                    kvNode.next = newTable[index];
                    newTable[index] = kvNode;
                    // 进入下一轮遍历
                    kvNode = next;
                }

            }
        }
    }

    class Node<K, V> {
        private final K key;
        private V val;
        int hash;
        Node<K, V> next;

        Node(K key, V val, int hash, Node<K, V> next) {
            this.key = key;
            this.val = val;
            this.hash = hash;
            this.next = next;
        }

    }

    public static void main(String[] args) {
        int hash = new Object().hashCode();
        System.out.println(hash);
        System.out.println(Integer.toBinaryString(hash));
        System.out.println(Integer.toBinaryString(15));
        System.out.println(Integer.toBinaryString(hash & 15));
        System.out.println(hash & 15);
        System.out.println("----------------");

        EasyMap<String, String> map = new EasyMap<>();
        for (int i = 0; i < 251; i++) {
            map.put("key" + i, "vvv" + i);

        }
        map.put("key5", "vvv35");

        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
        for (int i = 0; i < 251; i++) {
            System.out.println("key" + i + ":" + map.get("key" + i));
        }
        System.out.println("==========");
    }
}
