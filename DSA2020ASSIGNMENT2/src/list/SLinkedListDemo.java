/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author LTSACH
 */
public class SLinkedListDemo {
    public static void useSLinkedList() {
        SLinkedList<String> list = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }

        list.add("1");
        list.add("0");
        System.out.println("List size: " + list.size());
        System.out.println("IndexOf: " + list.indexOf("1"));
        System.out.println("lastIndexOf: " + list.lastIndexOf("1"));
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
        list.clear();
        list.add("3");
        list.add("2");
        list.remove("2");
        list.add("5");
        System.out.println("List size: " + list.size());
        System.out.println("Contain 2: " + list.contains("5"));
        it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }

    public static void testIterator() {
        SLinkedList<String> list = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }

        list.add("1");
        list.add("0");
        Iterator<String> it = list.iterator();

        System.out.println("------------");
        System.out.println("List size: " + list.size());
        System.out.println("IndexOf: " + list.indexOf("1"));
        System.out.println("lastIndexOf: " + list.lastIndexOf("1"));
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }

        //
        it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (item.equals("2")) {
                it.remove();
            }
            if (item.equals("4")) {
                it.remove();
            }
        }

        System.out.println("------------");
        System.out.println("List size: " + list.size());
        System.out.println("IndexOf: " + list.indexOf("1"));
        System.out.println("lastIndexOf: " + list.lastIndexOf("1"));

        it = list.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }

    public static void testIteratorBackward() {
        SLinkedList<String> list = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }

        list.add("1");
        list.add("0");
        ListIterator<String> it = list.listIterator(list.size());

        System.out.println("------------");
        System.out.println("List size: " + list.size());
        System.out.println("IndexOf: " + list.indexOf("1"));
        System.out.println("lastIndexOf: " + list.lastIndexOf("1"));
        while (it.hasPrevious()) {
            String item = it.previous();
            System.out.println(item);
        }

        //
        /*
        it = list.listIterator();
        while(it.hasNext()){
            String item = it.next();
            if(item.equals("2")){
                it.remove();
            }
            if(item.equals("4")){
                it.remove();
            }
        }
*/

        System.out.println("------------");
        System.out.println("List size: " + list.size());
        System.out.println("IndexOf: " + list.indexOf("1"));
        System.out.println("lastIndexOf: " + list.lastIndexOf("1"));

        it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
    }

    public static void testIterator_Set_With_Next() {
        System.out.println("testing ... listIterator.set with next()");
        List<String> list = new SLinkedList<>();
        for (int idx = 0; idx < 10; idx++) {
            list.add("" + idx);
        }
        System.out.println("--------------");
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }

        it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            if (Integer.parseInt(item) % 2 != 0) {
                String new_item = String.format("%d", Integer.parseInt(item) * 2);
                it.set(new_item);
            }
        }
        System.out.println("--------------");
        it = list.listIterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
        }
        System.out.println("--------------");
        for (int idx = 0; idx < list.size(); idx++) {
            String line = String.format("%s", list.get(idx));
            System.out.println(line);
        }

    }

}
