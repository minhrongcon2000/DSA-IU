/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author LTSACH
 */
public class DLinkedListDemo {
    public static void demo() {
        DLinkedList<String> list = new DLinkedList();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        System.out.printf("List size: %d\n", list.size());

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.printf("%s\n", item);
        }

        System.out.printf("List size: %d\n", list.size());
        ListIterator<String> it2 = list.listIterator();
        while (it2.hasNext()) {
            String item = it2.next();
            System.out.printf("%s\n", item);
        }

        System.out.printf("List size: %d\n", list.size());
        ListIterator<String> it3 = list.listIterator(list.size());
        while (it3.hasPrevious()) {
            String item = it3.previous();
            System.out.printf("%s\n", item);
        }
    }

    public static void useLastIndexOf() {
        DLinkedList list = new DLinkedList();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("0");
        list.add("1");
        System.out.printf("List size: %d\n", list.size());
        System.out.printf("indexOf: %d: %d\n", 0, list.indexOf("0"));
        System.out.printf("lastTndexOf: %d: %d\n", 0, list.lastIndexOf("0"));
    }

    public static void useIndex() {
        DLinkedList<String> list = new DLinkedList();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        System.out.printf("List size: %d\n", list.size());

        System.out.printf("indexOf: %d: %d\n", 2, list.indexOf("2"));
        System.out.printf("lastTndexOf: %d: %d\n", 2, list.lastIndexOf("2"));

        list.remove("5");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.printf("%s\n", item);
        }


        System.out.printf("Item at: %s\n", list.get(4));

        System.out.printf("List size: %d\n", list.size());
        ListIterator<String> it3 = list.listIterator(list.size());
        while (it3.hasPrevious()) {
            String item = it3.previous();
            System.out.printf("%s\n", item);
        }
    }

    public static void useAddWithIterator() {
        DLinkedList<String> list = new DLinkedList<>();
        for (int idx = 0; idx < 10; idx++) {
            list.add("" + idx);
        }

        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.printf("'%s'\n", item);
            if (Integer.parseInt(item) % 2 != 0) {
                String new_item = String.format("%d", Integer.parseInt(item) * 2);
                it.add(new_item);
            }

        }
        it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }

    }

}
