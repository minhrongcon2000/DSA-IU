/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.graph;

import graph.DGraphModel;
import graph.TopoSorter;
import org.junit.*;
import org.junit.rules.Timeout;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author LTSACH
 */
public class TopoSortTest {

    public TopoSortTest() {
    }

    @Rule
    public Timeout globalTimeout = new Timeout(2000);

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: DGraphMode.TopoSort");
        System.out.println(new String(new char[80]).replace('\0', '='));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void topoSort1() {
        System.out.println("testing ... topoSort()");
        DGraphModel<Character> graph = new DGraphModel<>();
        char vertices[] = {'A', 'B', 'C', 'D'};
        char from[] = {'A', 'A', 'B', 'B', 'C'};
        char to[] = {'B', 'C', 'C', 'D', 'D'};
        for (char v : vertices) graph.add(v);
        for (int idx = 0; idx < from.length; idx++) {
            char F = from[idx], T = to[idx];
            graph.connect(F, T);
        }
        TopoSorter<Character> sorter = new TopoSorter<>(graph);
        List<Character> topo = sorter.sort(TopoSorter.BFS);
        //verify for topo-order
        for (int idx = 0; idx < from.length; idx++) {
            char F = from[idx], T = to[idx];
            int Fidx = topo.indexOf(F);
            int Tidx = topo.indexOf(T);
            assertEquals(true, Fidx < Tidx);
        }
        System.out.println(topo);
    }

    @Test
    public void topoSort2() {
        DGraphModel<Integer> graph = new DGraphModel<>();
        int vertices[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int from[] = {0, 0, 1, 3, 3, 3, 3, 4, 6, 6, 6, 8, 8, 9};
        int to[] = {1, 5, 7, 2, 4, 7, 8, 8, 0, 1, 2, 2, 7, 4};
        for (int v : vertices) graph.add(v);
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx], T = to[idx];
            graph.connect(F, T);
        }
        TopoSorter<Integer> sorter = new TopoSorter<>(graph);
        List<Integer> topo = sorter.sort(TopoSorter.BFS);
        //verify for topo-order
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx], T = to[idx];
            int Fidx = topo.indexOf(F);
            int Tidx = topo.indexOf(T);
            assertEquals(true, Fidx < Tidx);
        }
    }
}
