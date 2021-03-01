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

class Node<T> {
    T data;
    Node<T> left, right;

    Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class QueueDemo {
    public static void main(String[] args) {
        Node<Integer> root = createTree();
        List<Integer> list = treeWalker(root);
        System.out.println(list); //[50,30,60,10,40,55,70]
    }

    /*
    Tree:
                 50
                /  \ 
               /    \
              30     60
             /  \    / \
            10   40 55  70
    Walk level-by-level: [50,30,60,10,40,55,70]
    */
    public static Node<Integer> createTree() {
        Node<Integer> n1 = new Node<>(50, null, null);
        Node<Integer> n2 = new Node<>(30, null, null);
        Node<Integer> n3 = new Node<>(60, null, null);
        Node<Integer> n4 = new Node<>(10, null, null);
        Node<Integer> n5 = new Node<>(40, null, null);
        Node<Integer> n6 = new Node<>(55, null, null);
        Node<Integer> n7 = new Node<>(70, null, null);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        return n1;
    }

    public static List<Integer> treeWalker(Node<Integer> root) {
        List<Integer> list = new DLinkedList<>();
        Queue<Node<Integer>> queue = new Queue<>();
        queue.push(root);
        while (!queue.empty()) {
            Node<Integer> node = queue.pop();
            list.add(node.data);
            if (node.left != null) queue.push(node.left);
            if (node.right != null) queue.push(node.right);
        }
        return list;
    }
}
