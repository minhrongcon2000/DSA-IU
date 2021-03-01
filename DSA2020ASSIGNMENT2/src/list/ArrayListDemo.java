/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author LTSACH
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        //addItem();
        removeItem();
    }

    public static void useMyArrayList() {
        MyArrayList<String> list = new MyArrayList<>(2);

        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        Iterator<String> it = list.iterator();
        System.out.println("-----------------");
        it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }

        ListIterator<String> listIt = list.listIterator(5);
        System.out.println("-----------------");
        while (listIt.hasPrevious()) {
            String item = listIt.previous();
            System.out.println(item);
        }
    }

    public static void useArrayList() {
        ArrayList<String> al = new ArrayList<String>(-10);
        al.add("Ravi");
        al.add("Vijay");
        al.add("Ravi");
        al.add("Ajay");
        //Traversing list through for-each loop  
        for (String obj : al) {
            System.out.println(obj);
        }
    }

    public static void addItem() {
        List<String> list = new MyArrayList<>();
        //List<String> list = new ArrayList<>();
        //Add elements
        for (int idx = 0; idx < 10; idx++) {
            list.add("" + idx);
        }

        //(1)Print elements - Use Index, travel forward
        System.out.printf("%-32s", "Go forward, use index:");
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();

        //(2)Print elements - Use Index, travel backward
        System.out.printf("%-32s", "Go backward, use index:");
        for (int idx = list.size() - 1; idx >= 0; idx--) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();

        //(3)Print elements - Use Iterator, travel forward
        System.out.printf("%-32s", "Go forward, use Iterator:");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.printf("%s ", item);
        }
        System.out.println();

        //(4)Print elements - Use ListIterator, travel forward
        System.out.printf("%-32s", "Go forward, use ListIterator:");
        ListIterator<String> fwd = list.listIterator();
        while (fwd.hasNext()) {
            String item = fwd.next();
            System.out.printf("%s ", item);
        }
        System.out.println();

        //(5)Print elements - Use ListIterator, travel backward
        System.out.printf("%-32s", "Go backward, use ListIterator:");
        ListIterator<String> bwd = list.listIterator(list.size());
        while (bwd.hasPrevious()) {
            String item = bwd.previous();
            System.out.printf("%s ", item);
        }
        System.out.println();
    }

    public static void removeItem() {
        //List<Integer> list = new MyArrayList<>();
        List<Integer> list = new ArrayList<>();
        //Add elements
        for (int idx = 0; idx < 10; idx++) {
            list.add(idx);
        }

        //(1)Print elements - Use Index, travel forward
        System.out.printf("%-25s", "Before modification:");
        for (int idx = 0; idx < list.size(); idx++) {
            System.out.printf("%s ", list.get(idx));
        }
        System.out.println();
        //(2)Remove odd numbers
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            int item = it.next();
            if (item % 2 != 0) it.remove();
            else it.set(item * 10);
        }
        //(3) Print after changing
        System.out.printf("%-25s", "After modification:");
        it = list.listIterator();
        while (it.hasNext()) {
            System.out.printf("%s ", it.next());
        }
        System.out.println();
    }

}
