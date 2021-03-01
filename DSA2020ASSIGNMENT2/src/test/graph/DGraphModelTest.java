/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.graph;

import graph.DGraphModel;
import graph.IGraph;
import org.junit.*;
import org.junit.rules.Timeout;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author LTSACH
 */

public class DGraphModelTest {

    public DGraphModelTest() {
    }

    @Rule
    public Timeout globalTimeout = new Timeout(2000);

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: DGraphModel");
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
    public void size() {
        System.out.println("testing ... size()");
        IGraph<Integer> graph = new DGraphModel<>();
        assertEquals(0, graph.size());

        int vertices[] = {1, 2, 3, 4, 5};
        for (int v : vertices) graph.add(v);
        assertEquals(5, graph.size());
    }

    @Test
    public void connect() {
        System.out.println("testing ... connect()");
        IGraph<Integer> graph = new DGraphModel<>();
        assertEquals(0, graph.size());

        int vertices[] = {1, 2, 3, 4, 5};
        for (int v : vertices) graph.add(v);
        assertEquals(5, graph.size());

        int from[] = {1, 1, 1, 1, 2};
        int to[] = {2, 3, 4, 5, 4};
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx];
            int T = to[idx];
            graph.connect(F, T);
        }
        assertEquals(0, graph.inDegree(1));
        assertEquals(4, graph.outDegree(1));

        assertEquals(1, graph.inDegree(2));
        assertEquals(1, graph.outDegree(2));
        assertEquals(2, graph.inDegree(4));
        assertEquals(0, graph.outDegree(4));

        assertEquals(1, graph.inDegree(3));
        assertEquals(0, graph.outDegree(3));
        assertEquals(1, graph.inDegree(5));
        assertEquals(0, graph.outDegree(5));

        //
        List<Integer> outList = graph.getOutwardEdges(1);
        assertEquals(4, outList.size());
        assertEquals(true, outList.contains(2));
        assertEquals(true, outList.contains(3));
        assertEquals(true, outList.contains(4));
        assertEquals(true, outList.contains(5));

        List<Integer> outList2 = graph.getInwardEdges(4);
        assertEquals(2, outList2.size());
        assertEquals(true, outList2.contains(1));
        assertEquals(true, outList2.contains(2));
    }

    @Test
    public void remove() {
        System.out.println("testing ... remove()");
        IGraph<Integer> graph = new DGraphModel<>();
        assertEquals(0, graph.size());

        int vertices[] = {1, 2, 3, 4, 5};
        for (int v : vertices) graph.add(v);
        assertEquals(5, graph.size());

        int from[] = {1, 1, 1, 1, 2};
        int to[] = {2, 3, 4, 5, 4};
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx];
            int T = to[idx];
            graph.connect(F, T);
        }
        //
        graph.remove(1);
        assertEquals(4, graph.size());
        assertEquals(0, graph.inDegree(2));
        assertEquals(1, graph.outDegree(2));
        assertEquals(1, graph.inDegree(4));
        assertEquals(0, graph.outDegree(4));
        assertEquals(0, graph.inDegree(3));
        assertEquals(0, graph.outDegree(3));
        assertEquals(0, graph.inDegree(5));
        assertEquals(0, graph.outDegree(5));
    }

    @Test
    public void disconnect() {
        System.out.println("testing ... disconnect()");
        IGraph<Integer> graph = new DGraphModel<>();
        assertEquals(0, graph.size());

        int vertices[] = {1, 2, 3, 4, 5};
        for (int v : vertices) graph.add(v);
        assertEquals(5, graph.size());

        int from[] = {1, 1, 1, 1, 2};
        int to[] = {2, 3, 4, 5, 4};
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx];
            int T = to[idx];
            graph.connect(F, T);
        }
        //
        graph.disconnect(1, 2);
        assertEquals(5, graph.size());
        assertEquals(0, graph.inDegree(1));
        assertEquals(3, graph.outDegree(1));

        assertEquals(0, graph.inDegree(2));
        assertEquals(1, graph.outDegree(2));
        assertEquals(2, graph.inDegree(4));
        assertEquals(0, graph.outDegree(4));
        assertEquals(1, graph.inDegree(3));
        assertEquals(0, graph.outDegree(3));
        assertEquals(1, graph.inDegree(5));
        assertEquals(0, graph.outDegree(5));
    }

    @Test
    public void connect2() {
        System.out.println("testing ... connect2()");
        IGraph<Integer> graph = new DGraphModel<>();
        assertEquals(0, graph.size());

        int vertices[] = {1, 2, 3, 4, 5};
        for (int v : vertices) graph.add(v);
        assertEquals(5, graph.size());

        int from[] = {1, 1, 1, 1, 2};
        int to[] = {2, 3, 4, 5, 4};
        int weights[] = {100, 200, 300, 400, 500};
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx];
            int T = to[idx];
            graph.connect(F, T, weights[idx]);
        }
        assertEquals(0, graph.inDegree(1));
        assertEquals(4, graph.outDegree(1));

        assertEquals(1, graph.inDegree(2));
        assertEquals(1, graph.outDegree(2));
        assertEquals(2, graph.inDegree(4));
        assertEquals(0, graph.outDegree(4));

        assertEquals(1, graph.inDegree(3));
        assertEquals(0, graph.outDegree(3));
        assertEquals(1, graph.inDegree(5));
        assertEquals(0, graph.outDegree(5));

        //
        List<Integer> outList = graph.getOutwardEdges(1);
        assertEquals(4, outList.size());
        assertEquals(true, outList.contains(2));
        assertEquals(true, outList.contains(3));
        assertEquals(true, outList.contains(4));
        assertEquals(true, outList.contains(5));

        List<Integer> outList2 = graph.getInwardEdges(4);
        assertEquals(2, outList2.size());
        assertEquals(true, outList2.contains(1));
        assertEquals(true, outList2.contains(2));
    }

    @Test
    public void weight() {
        System.out.println("testing ... weight()");
        IGraph<Integer> graph = new DGraphModel<>();
        assertEquals(0, graph.size());

        int vertices[] = {1, 2, 3, 4, 5};
        for (int v : vertices) graph.add(v);
        assertEquals(5, graph.size());

        int from[] = {1, 1, 1, 1, 2};
        int to[] = {2, 3, 4, 5, 4};
        int weights[] = {100, 200, 300, 400, 500};
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx];
            int T = to[idx];
            graph.connect(F, T, weights[idx]);
        }
        for (int idx = 0; idx < from.length; idx++) {
            int F = from[idx];
            int T = to[idx];
            assertEquals(weights[idx], (int) graph.weight(F, T));
        }
    }
}
