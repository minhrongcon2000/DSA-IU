/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

import java.util.Comparator;


/**
 * @author LTSACH
 */

class IntComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }

}

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(); //for minheap
        //PriorityQueue<Integer> queue = new PriorityQueue<>(new IntComparator()); //for maxheap
        Integer[] array = {15, 17, 24, 6, 8};
        queue.heapify(array);
        System.out.printf("Find %d, result: %s\n", 6, (queue.contains(6) == true ? "found" : "not found"));
        System.out.printf("Find %d, result: %s\n", 12, (queue.contains(12) == true ? "found" : "not found"));
        queue.println();
        System.out.println(new String(new char[80]).replace('\0', '='));
        System.out.println("poping until emty:");
        System.out.println(new String(new char[80]).replace('\0', '-'));
        while (!queue.empty()) {
            System.out.printf("internal heap: %s\n", queue.toString());
            int item = queue.pop();
            System.out.printf("removed item: %d\n", item);
        }

    }
}
