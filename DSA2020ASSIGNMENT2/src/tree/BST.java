/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import stacknqueue.Queue;
import stacknqueue.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LTSACH
 */
public class BST<T> implements IBinarySearchTree<T>, ITreeWalker<T> {
    private int size;
    Node<T> root;

    public BST() {
        this.size = 0;
        this.root = null;
    }

    ///
    private int key(T item) {
        return item.hashCode();
    }

    private Node<T> add(Node<T> root, T item) {
        if (root == null) return new Node<>(item, null, null);
        if (key(item) < key(root.item))
            root.left = this.add(root.left, item);
        else
            root.right = this.add(root.right, item);
        return root;
    }

    ///
    @Override
    public void add(T item) {
        /*YOUR CODE HERE*/
        root = add(root, item);
        size += 1;
    }

    private Node<T> remove(Node<T> root, Object key) {
        if (root == null) return null;
        if (key.hashCode() < key(root.item)) {
            root.left = remove(root.left, key);
        } else if (key.hashCode() > key(root.item)) {
            root.right = remove(root.right, key);
        } else {
            /*YOUR CODE HERE*/
            //Remember to decrease size
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node<T> smallest = root;
            while (smallest.left != null) {
                smallest = smallest.left;
            }
            root.right = remove(root.right, smallest.item);
        }
        return root;
    }

    @Override
    public void remove(Object key) {
        remove(root, key);
        size--;
    }

    private T search(Node<T> root, Object key) {
        if (root == null) return null;
        /*YOUR CODE HERE*/
        if (key.hashCode() < key(root.item)) return search(root.left, key);
        if (key.hashCode() > key(root.item)) return search(root.right, key);
        return root.item;//remove this line
    }

    @Override
    public T search(Object key) {
        return search(this.root, key);
    }

    @Override
    public int size() {
        return this.size;
    }

    public String toString() {
        return this.root.toString();
    }

    public void println() {
        System.out.println(this.toString());
    }

    private void ascendingList(Node<T> root, List<T> list) {
        if (root == null) return;
        ascendingList(root.left, list);
        list.add(root.item);
        ascendingList(root.right, list);
    }

    @Override
    public List<T> ascendingList() {
        /*YOUR CODE HERE*/
        List<T> output = new ArrayList<>();
        ascendingList(root, output);
        return output; //remove this line
    }

    private void descendingList(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
        if (root == null) return;
        descendingList(root.right, list);
        list.add(root.item);
        descendingList(root.left, list);
    }

    @Override
    public List<T> descendingList() {
        List<T> list = new LinkedList<>();
        descendingList(root, list);
        return list;
    }

    @Override
    public List<T> dfs() {
        List<T> list = new LinkedList<>();
        /*YOUR CODE HERE*/
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        HashMap<T, Boolean> visited = new HashMap<>();
        while (!stack.empty()) {
            Node<T> currNode = stack.peek();
            Boolean isVisited = visited.get(currNode.item);
            if (isVisited == null) list.add(currNode.item);
            visited.put(currNode.item, true);
            if (currNode.left != null && visited.get(currNode.left.item) == null) stack.push(currNode.left);
            else if (currNode.right != null && visited.get(currNode.right.item) == null) stack.push(currNode.right);
            else stack.pop();
        }
        return list;
    }

    @Override
    public List<T> bfs() {
        List<T> list = new LinkedList<>();

        /*YOUR CODE HERE*/
        Queue<Node<T>> queue = new Queue<>();
        queue.push(root);
        while (!queue.empty()) {
            Node<T> currNode = queue.pop();
            list.add(currNode.item);
            if (currNode.left != null) queue.push(currNode.left);
            if (currNode.right != null) queue.push(currNode.right);
        }
        return list;
    }

    private void nlr(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
        if (root != null) {
            list.add(root.item);
            nlr(root.left, list);
            nlr(root.right, list);
        }
    }

    private void lrn(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
        if (root != null) {
            lrn(root.left, list);
            lrn(root.right, list);
            list.add(root.item);
        }
    }

    private void lnr(Node<T> root, List<T> list) {
        /*YOUR CODE HERE*/
        if (root != null) {
            lnr(root.left, list);
            list.add(root.item);
            lnr(root.right, list);
        }
    }

    @Override
    public List<T> nlr() {
        /*YOUR CODE HERE*/
        List<T> output = new ArrayList<>();
        nlr(root, output);
        return output;
    }

    @Override
    public List<T> lrn() {
        /*YOUR CODE HERE*/
        List<T> output = new ArrayList<>();
        lrn(root, output);
        return output;
    }

    @Override
    public List<T> lnr() {
        /*YOUR CODE HERE*/
        List<T> output = new ArrayList<>();
        lnr(root, output);
        return output;
    }

    //
    public class Node<T> {
        T item;
        Node<T> left, right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.item = data;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            String desc = "";
            if (this.left == null && this.right == null)
                desc = String.format("(%s)", item);
            if (this.left == null && this.right != null)
                desc = String.format("(%s () %s)", item, this.right.toString());
            if (this.left != null && this.right == null)
                desc = String.format("(%s %s ())", item, this.left.toString());
            if (this.left != null && this.right != null)
                desc = String.format("(%s %s %s)", item,
                        this.left.toString(), this.right.toString());
            return desc;
        }
    }
}
