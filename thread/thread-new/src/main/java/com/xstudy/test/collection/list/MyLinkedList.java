package com.xstudy.test.collection.list;

import java.util.NoSuchElementException;

/**
 * @author zurun
 * @date 2019/7/28 16:48:30
 */
public class MyLinkedList<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public int size() {
        return 0;
    }

    public E getFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public E pop() {
        return removeFirst();
    }

    public void add(E e) {
        linkLast(e);
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    /**
     * 第一个不能为空
     *
     * @return
     */
    private E unlinkFirst(Node<E> f) {
        final E item = first.item;
        final Node<E> next = f.next;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return item;
    }

    public void linkLast(E e) {
        final Node<E> l = last;
        Node<E> newNode = new Node<>(last, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        size++;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }
}
