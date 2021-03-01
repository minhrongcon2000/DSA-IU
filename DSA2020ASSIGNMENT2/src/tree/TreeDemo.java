/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import java.util.List;

/**
 * @author LTSACH
 */
public class TreeDemo {
    public static void main(String[] args) {
        bstDemo();
    }

    public static void bst2List() {
        BST<Integer> tree = new BST<>();
        tree.add(15);
        tree.add(10);
        tree.add(30);
        tree.add(5);
        tree.add(60);
        tree.add(17);
        tree.println();

        List<Integer> list1 = tree.lnr();
        System.out.println(list1);
    }

    public static void bstDemo() {
        BST<Integer> tree = new BST<>();
        tree.add(15);
        tree.add(10);
        tree.add(30);
        tree.add(5);
        tree.add(60);
        tree.add(17);
        tree.println();

        System.out.printf("search: key=%d => %s\n", 17, tree.search(17) == null ? "not found" : "found");
        System.out.printf("search: key=%d => %s\n", 5, tree.search(5) == null ? "not found" : "found");
        System.out.printf("search: key=%d => %s\n", 77, tree.search(77) == null ? "not found" : "found");

        tree.remove(17);
        tree.println();
    }
}
