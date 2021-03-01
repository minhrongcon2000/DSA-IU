/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Iterator;
import java.util.List;

/**
 * @author LTSACH
 */

public class GraphDemo {
    public static void main(String[] args) {
        //mstDemo();
        //dijkstraDemo();
        TopoSortDemo();
//        ugraphDemo();
    }

    public static void ugraphDemo() {
        UGraphModel<String> graph = new UGraphModel<>();
        graph.add("HCM");
        graph.add("LongAn");
        graph.add("BinhDuong");
        graph.add("DongNai");
        graph.add("BinhThuan");
        graph.connect("HCM", "LongAn");
        graph.connect("HCM", "BinhDuong");
        graph.connect("HCM", "DongNai");
        graph.connect("DongNai", "BinhThuan");
        graph.println();
    }

    public static void TopoSortDemo() {
        DGraphModel<Character> model = new DGraphModel<>();
        for (int idx = 0; idx < 10; idx++) {
            model.add((char) ('0' + idx));
        }

        model.connect('0', '1');
        model.connect('0', '5');
        model.connect('1', '7');
        model.connect('3', '2');
        model.connect('3', '4');
        model.connect('3', '7');
        model.connect('3', '8');
        model.connect('4', '8');
        model.connect('6', '0');
        model.connect('6', '1');
        model.connect('6', '2');
        model.connect('8', '2');
        model.connect('8', '7');
        model.connect('9', '4');
        model.println();

        TopoSorter<Character> sorter = new TopoSorter<>(model);
        List<Character> topo = sorter.sort(TopoSorter.DFS);
        System.out.println(topo);
    }

    public static void mstDemo() {
        UGraphModel<Character> model = new UGraphModel<>();
        model.add('A');
        model.add('B');
        model.add('C');
        model.add('D');
        model.add('E');
        model.add('F');
        model.connect('A', 'B', 6);
        model.connect('A', 'C', 3);

        model.connect('B', 'C', 2);
        model.connect('B', 'D', 5);

        model.connect('C', 'D', 3);
        model.connect('C', 'E', 4);

        model.connect('D', 'E', 2);
        model.connect('D', 'F', 3);

        model.connect('E', 'F', 5);

        model.println();

        model.disconnect('C', 'A');
        model.disconnect('C', 'B');
        model.disconnect('C', 'D');
        model.disconnect('C', 'E');

        model.println();

        UGraphAlgorithm<Character> mst = new UGraphAlgorithm<>(model);
        UGraphModel<Character> tree = mst.minSpanningTree();
        tree.println();
    }

    public static void dijkstraDemo() {
        DGraphModel<Character> model = new DGraphModel<>();
        model.add('0');
        model.add('1');
        model.add('2');
        model.add('3');
        model.add('4');
        model.connect('0', '1', 5);
        model.connect('0', '2', 3);
        model.connect('0', '4', 2);

        model.connect('1', '2', 2);
        model.connect('1', '3', 6);

        model.connect('2', '1', 1);
        model.connect('2', '3', 2);

        model.connect('4', '1', 6);
        model.connect('4', '2', 10);
        model.connect('4', '3', 4);
        model.println();
        model.remove('1');
        model.println();

        DGraphAlgorithm<Character> finder = new DGraphAlgorithm(model);
        List<Path> list = finder.dijkstra(model, '0');
        System.out.println("---");
        Iterator<Path> it = list.iterator();
        while (it.hasNext()) {
            Path path = it.next();
            System.out.println(path);
        }
    }
}
