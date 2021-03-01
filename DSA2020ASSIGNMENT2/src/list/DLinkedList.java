/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author LTSACH
 */
public class DLinkedList<E> implements List<E> {
    private static enum MoveType {
        NEXT, PREV
    }

    ;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /*
    Initialize the double-linked list as shown in Figure 32 in the tutorial.
    The following values should be initialized:
    * head, head.next
    * tail, tail.next
    * size
    */
    public DLinkedList() {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////// Utility methods (private)         ////////////////////
    ////////////////////////////////////////////////////////////////////////// 
    /*
    checkValidIndex: assert that "index" inside of [min, max]
    */
    private void checkValidIndex(int index, int min, int max) {
        if ((index < min) || (index > max)) {
            String message = String.format("Invalid index (=%d)", index);
            throw new IndexOutOfBoundsException(message);
        }
    }

    /*
    getNode(Object o): get node containing data (not applied for meta-nodes: ie., head and tail)
    * search and return the node containing object "o".
    * return "null" if not found
    */
    private Node<E> getNode(Object o) {
        Node<E> curNode = head.next;
        Node<E> foundNode = null;
        while (curNode != tail) {
            if (curNode.element.equals(o)) {
                foundNode = curNode;
                break;
            }
            curNode = curNode.next;
        }
        return foundNode;
    }

    /*
    getNode(int index, int min, int max): get node containing either data or head/tail.
    
    */
    private Node<E> getNode(int index, int min, int max) {
        checkValidIndex(index, min, max);

        Node<E> curNode;
        int curIndex;
        if ((size - index) < (size / 2)) {
            curNode = this.tail;
            curIndex = size;
            while (curIndex > index) {
                curIndex -= 1;
                curNode = curNode.prev;
            }
        } else {
            curNode = head;
            curIndex = -1;
            while (curIndex < index) {
                curIndex += 1;
                curNode = curNode.next;
            }
        }
        return curNode;
    }

    /*
    insertLnewR(Node<E> left, Node<E> newNode, Node<E> right):
    insert newNode to the double-linked list.
    after insertion: left<->newNode <->right
    */
    private void insertLnewR(Node<E> left, Node<E> newNode, Node<E> right) {
        left.next = newNode;
        newNode.next = right;
        right.prev = newNode;
        newNode.prev = left;
        size += 1;
    }

    /*
    removeNode(Node<E> removedNode):
    remove "removedNode" from the double-linked list.
    */
    private void removeNode(Node<E> removedNode) {
        removedNode.prev.next = removedNode.next;
        removedNode.next.prev = removedNode.prev;
        removedNode.update(null, null, null);
        size -= 1;
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////// API of Doubble-Linked List         ///////////////////
    ////////////////////////////////////////////////////////////////////////// 
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> foundNode = getNode(o);
        return foundNode != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new FWDIterator();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(null, null, e);
        insertLnewR(tail.prev, newNode, tail);
        return true;
    }


    @Override
    public boolean remove(Object o) {
        Node<E> foundNode = getNode(o);
        if (foundNode != null) {
            removeNode(foundNode);
            return true;
        } else return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        Node<E> curNode = head.next;
        while (curNode != tail) {
            Node<E> temp = curNode;
            curNode = curNode.next;
            removeNode(temp);
        }
        //reset head and tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }


    @Override
    public E get(int index) {
        Node<E> curNode = getNode(index, 0, size - 1);
        return curNode.element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> curNode = getNode(index, 0, size - 1);
        //
        E oldElement = curNode.element;
        curNode.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        Node<E> curNode = getNode(index, 0, size);
        Node<E> newNode = new Node<>(null, null, element);
        insertLnewR(curNode.prev, newNode, curNode);
    }

    @Override
    public E remove(int index) {
        Node<E> foudNode = getNode(index, 0, size - 1);
        E oldElement = foudNode.element;
        removeNode(foudNode);
        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        int foundIndex = -1;
        int index = 0;
        Node<E> curNode = head.next;
        while (curNode != tail) {
            if (curNode.element.equals(o)) {
                foundIndex = index;
                break;
            }
            curNode = curNode.next;
            index += 1;
        }
        return foundIndex;
    }

    @Override
    public int lastIndexOf(Object o) {
        int foundIndex = -1;
        int index = size - 1;
        Node<E> curNode = tail.prev;
        while (curNode != head) {
            if (curNode.element.equals(o)) {
                foundIndex = index;
                break;
            }
            curNode = curNode.prev;
            index -= 1;
        }
        return foundIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new FBWDIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new FBWDIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        String desc = "[";
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E e = it.next();
            desc += String.format("%s,", e);
        }
        if (desc.endsWith(",")) desc = desc.substring(0, desc.length() - 1);
        desc += "]";
        return desc;
    }

    ////////////////////////////////////////////////////////////////////
    //// BEGIN OF INNER CLASSES                                     ////
    ////////////////////////////////////////////////////////////////////
    private class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, Node<E> next, E element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }

        void update(Node<E> prev, Node<E> next, E element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }
    }

    private class FWDIterator implements Iterator<E> {
        Node<E> curNode;
        boolean afterMove;

        FWDIterator() {
            curNode = DLinkedList.this.head.next;
            afterMove = false;
        }

        @Override
        public boolean hasNext() {
            return curNode != DLinkedList.this.tail;
        }

        @Override
        public E next() {
            E element = curNode.element;
            curNode = curNode.next;
            afterMove = true;
            return element;
        }

        @Override
        public void remove() {
            if (!afterMove) return;
            Node<E> prevNode = curNode.prev;
            removeNode(prevNode);
            afterMove = false;
        }

    }//End of MyIterator

    private class FBWDIterator extends FWDIterator implements ListIterator<E> {
        int curIndex;
        MoveType moveType;

        FBWDIterator() {
            super();
            curIndex = 0;
            moveType = MoveType.NEXT; //default
            afterMove = false;
        }

        FBWDIterator(int index) {
            moveType = MoveType.NEXT;

            if ((index < 0) || (index > DLinkedList.this.size)) {
                String message = String.format("Invalid index (=%d)", index);
                throw new IndexOutOfBoundsException(message);
            }
            //Assign values to curIdex and curNode
            if ((DLinkedList.this.size - index) < DLinkedList.this.size / 2) {
                curNode = DLinkedList.this.tail;
                curIndex = DLinkedList.this.size;
                while (curIndex > index) {
                    curIndex -= 1;
                    curNode = curNode.prev;
                }
            } else {
                curNode = DLinkedList.this.head;
                curIndex = -1;
                while (curIndex < index) {
                    curIndex += 1;
                    curNode = curNode.next;
                }
            }
        }

        @Override
        public E next() {
            E e = super.next();
            curIndex += 1;
            moveType = MoveType.NEXT;
            return e;
        }

        @Override
        public void remove() {
            if (!afterMove) return;
            Node<E> removedNode;
            if (moveType == MoveType.NEXT) {
                removedNode = curNode.prev;
            } else {
                removedNode = curNode;
                curNode = curNode.next;
            }
            removeNode(removedNode);
            afterMove = false;
        }

        @Override
        public boolean hasPrevious() {
            return curNode.prev != DLinkedList.this.head;
        }

        @Override
        public E previous() {
            curNode = curNode.prev;
            curIndex -= 1;
            moveType = MoveType.PREV;
            afterMove = true;
            return curNode.element;
        }

        @Override
        public int nextIndex() {
            return this.curIndex;
        }

        @Override
        public int previousIndex() {
            return curIndex - 1;
        }

        @Override
        public void set(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT) {
                Node<E> prevNode = curNode.prev;
                prevNode.element = e;
            } else {
                curNode.element = e;
            }
        }

        @Override
        public void add(E e) {
            if (!afterMove) return;
            if (moveType == MoveType.NEXT) {
                Node<E> prevNode = curNode.prev; //go to prev
                Node<E> newNode = new Node<>(null, null, e);
                insertLnewR(prevNode.prev, newNode, prevNode);
            } else {
                Node<E> newNode = new Node<>(null, null, e);
                insertLnewR(curNode.prev, newNode, curNode);
                curNode = curNode.prev; // to new node
            }
        }
    }//End of FBWDIterator   
}//End of DLinkedList
