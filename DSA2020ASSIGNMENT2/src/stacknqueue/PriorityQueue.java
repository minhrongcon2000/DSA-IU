/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

import heap.Heap;

import java.util.Comparator;

/**
 * @author LTSACH
 */
public class PriorityQueue<T> extends Heap<T> {
    public PriorityQueue() {
        super();
    }

    public PriorityQueue(Comparator<? super T> comparator) {
        super(comparator);
    }
}
