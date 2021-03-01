/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author LTSACH
 */
public class Queue<T> {
    private List<T> list;

    public Queue() {
        this.list = new LinkedList<>();
    }

    public void push(T item) {
        this.list.add(item);
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