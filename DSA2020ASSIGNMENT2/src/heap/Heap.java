/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author LTSACH
 */
public class Heap<T> implements IHeap<T> {
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private T[] elements;
    private int size;
    private Comparator<? super T> comparator;

    public Heap() {
        this(null);
    }

    public Heap(Comparator<? super T> comparator) {
        this.elements = (T[]) new Object[10];
        this.size = 0;
        this.comparator = comparator;
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////// Utility methods (private)         ////////////////////
    ////////////////////////////////////////////////////////////////////////// 
    private boolean aLTb(T a, T b) {
        if (this.comparator == null) return a.hashCode() < b.hashCode();
        else return this.comparator.compare(a, b) < 0;
    }

    private void checkCapacity(int minCapacity) {
        if ((minCapacity < 0) || (minCapacity > MAX_CAPACITY))
            throw new OutOfMemoryError("Not enough memory to store the array");
        if (minCapacity < this.elements.length)
            return;
        else {
            //grow: oldCapacity >> 1 (= oldCapacity/2)
            int oldCapacity = this.elements.length;
            int newCapacity = oldCapacity + oldCapacity >> 1;
            if (newCapacity < 0)
                newCapacity = MAX_CAPACITY;
            this.elements = Arrays.copyOf(this.elements, newCapacity);
        }
    }

    private void swap(int a, int b) {
        T temp = this.elements[a];
        this.elements[a] = this.elements[b];
        this.elements[b] = temp;
    }

    private void reheapUp(int position) {
        if (position <= 0) return;
        int parent = (position - 1) / 2;
        if (aLTb(this.elements[position], this.elements[parent])) {
            this.swap(position, parent);
            reheapUp(parent);
        }
    }

    private void reheapDown(int position) {
        int leftChild = position * 2 + 1;
        int rightChild = position * 2 + 2;
        int lastPosition = this.size - 1;

        if (leftChild <= lastPosition) {
            int smallChild = leftChild; //by default => leftChild valid but rightChild invalid
            if (rightChild <= lastPosition) {
                if (aLTb(this.elements[leftChild], this.elements[rightChild]))
                    smallChild = leftChild;
                else smallChild = rightChild;
            }

            if (aLTb(this.elements[smallChild], this.elements[position])) {
                this.swap(smallChild, position);
                reheapDown(smallChild);
            }
        }
    }

    private int getItem(T item) {
        int foundIdx = -1;
        for (int idx = 0; idx < this.size; idx++) {
            if (this.elements[idx].equals(item)) {
                foundIdx = idx;
                break;
            }
        }
        return foundIdx;
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////// API of Doubble-Linked List         ///////////////////
    ////////////////////////////////////////////////////////////////////////// 

    @Override
    public void push(T item) {
        checkCapacity(this.size + 1);
        this.elements[this.size] = item;
        this.size += 1;
        this.reheapUp(this.size - 1);
    }

    @Override
    public T pop() {
        T item = this.elements[0];
        this.elements[0] = this.elements[this.size - 1];
        this.size -= 1;
        reheapDown(0);
        return item;
    }

    @Override
    public T peek() {
        if (this.size == 0) throw new RuntimeException("Peeking from an empty heap!");
        return this.elements[0];
    }

    @Override
    public void remove(T item) {
        int foundIdx = this.getItem(item);
        if (foundIdx == -1) return;

        //Remove item
        int copyCount = (this.size - 1) - (foundIdx + 1) + 1;
        System.arraycopy(this.elements, foundIdx + 1, this.elements, foundIdx, copyCount);
        this.size -= 1;

        //Determine valid heap [0-> (new size -1)]
        int startOldData = foundIdx;
        int lastOldData = this.size - 1;
        this.size = foundIdx;

        //reheapify from startOldData
        for (int idx = startOldData; idx <= lastOldData; idx++)
            this.push(this.elements[idx]);
    }

    @Override
    public boolean contains(T item) {
        boolean found = false;
        for (int idx = 0; idx < this.size; idx++) {
            if (this.elements[idx].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public void heapify(T[] array) {
        for (T item : array) this.push(item);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.elements = (T[]) new Object[10];
    }

    @Override
    public String toString() {
        String desc = "[";
        for (int idx = 0; idx < this.size - 1; idx++)
            desc += this.elements[idx] + ",";
        desc += this.elements[this.size - 1] + "]";
        return desc;
    }

    public void println() {
        System.out.println(this.toString());
    }
}
