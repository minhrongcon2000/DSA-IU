/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

import list.DLinkedList;

import java.util.List;

/**
 * @author LTSACH
 */
public class Stack<T> {
    private List<T> list;

    public Stack() {
        this.list = new DLinkedList<>();
    }

    public void push(T item) {
        this.list.add(0, item);
    }

    public T pop() {
        T item = this.list.get(0);
        this.list.remove(0);
        return item;
    }

    public T peek() {
        T item = this.list.get(0);
        return item;
    }

    public boolean remove(T item) {
        return this.list.remove(item);
    }

    public boolean contains(T item) {
        return this.list.contains(item);
    }

    public boolean empty() {
        return this.list.isEmpty();
    }

    public int size() {
        return this.list.size();
    }
}