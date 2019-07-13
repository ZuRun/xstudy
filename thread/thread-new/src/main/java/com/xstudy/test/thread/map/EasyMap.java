package com.xstudy.test.thread.map;


/**
 * 自行实现hashmap
 *
 * @author zurun
 * @date 2019-07-13 22:25:39
 */
public class EasyMap<K, V> {
    Node<K, V>[] table;
    private int nextSize;

    public EasyMap() {
        this(16);
    }

    public EasyMap(int capacity) {
        nextSize = capacity;
    }

    public void put(K key, V val) {
        if (table == null) {
            resize();
        }
        Node newNode = new Node(key, val, hash(key), null);
        int nodeIndex = getNodeIndex(key);
        if (table[nodeIndex] == null) {
            table[nodeIndex] = newNode;
        } else {
            // 此处应该要遍历链表，判断当key已存在时，覆盖，而不是新增
            Node<K, V> oldNode = table[nodeIndex];
            table[nodeIndex] = newNode;
            newNode.next = oldNode;
        }
    }

    /**
     * 暂不考虑key为null
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int index = getNodeIndex(key);
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
    private int getNodeIndex(K key) {
        int index = hash(key) & (table.length - 1);
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
            table = new Node[nextSize];
        }

    }

    class Node<K, V> {
        private final K key;
        private V val;
        int hash;
        Node next;

        Node(K key, V val, int hash, Node next) {
            this.key = key;
            this.val = val;
            this.hash = hash;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
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
        for (int i = 0; i < 25; i++) {
            map.put("key" + i, "vvv" + i);

        }
        map.put("key5", "vvv35");

        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
        for (int i = 0; i < 25; i++) {
            System.out.println("key" + i + ":" + map.get("key" + i));
        }
        System.out.println("==========");
    }
}
