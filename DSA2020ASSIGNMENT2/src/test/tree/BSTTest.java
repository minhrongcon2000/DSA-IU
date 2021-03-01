/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.tree;

import org.junit.*;
import org.junit.rules.Timeout;
import tree.BST;
import tree.IBinarySearchTree;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author LTSACH
 */
public class BSTTest {

    public BSTTest() {
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
    public void size() {
        System.out.println("testing ... size()");
        IBinarySearchTree<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
    }

    @Test
    public void remove() {
        System.out.println("testing ... remove()");
        IBinarySearchTree<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        int n = values.length;
        for (int v : values) {
            bst.remove(v);
            assertEquals(--n, bst.size());
        }
    }

    @Test
    public void search() {
        System.out.println("testing ... search()");
        IBinarySearchTree<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        for (int v : values) {
            Integer fv = bst.search(v);
            assertEquals(true, fv != null);
        }
        Integer fv = bst.search(999);
        assertEquals(false, fv != null);
    }

    @Test
    public void ascendingList() {
        System.out.println("testing ... ascendingList()");
        IBinarySearchTree<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        System.out.println(bst);
        int ascValues[] = {10, 20, 25, 50, 60, 70, 80};
        List<Integer> ascList = bst.ascendingList();
        for (int idx = 0; idx < ascValues.length; idx++) {
            int exp = ascValues[idx];
            int real = ascList.get(idx);
            assertEquals(true, exp == real);
        }
    }

    @Test
    public void descendingList() {
        System.out.println("testing ... descendingList()");
        IBinarySearchTree<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        int descValues[] = {80, 70, 60, 50, 25, 20, 10};
        List<Integer> descList = bst.descendingList();
        for (int idx = 0; idx < descValues.length; idx++) {
            int exp = descValues[idx];
            int real = descList.get(idx);
            assertEquals(true, exp == real);
        }
    }

    @Test
    public void nlr() {
        System.out.println("testing ... nlr()");
        BST<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        int expList[] = {50, 20, 10, 25, 70, 60, 80};
        List<Integer> realList = bst.nlr();
        for (int idx = 0; idx < expList.length; idx++) {
            int exp = expList[idx];
            int real = realList.get(idx);
            assertEquals(true, exp == real);
        }
    }

    @Test
    public void lnr() {
        System.out.println("testing ... lnr()");
        BST<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        int expList[] = {10, 20, 25, 50, 60, 70, 80};
        List<Integer> realList = bst.lnr();
        System.out.println(realList);
        for (int idx = 0; idx < expList.length; idx++) {
            int exp = expList[idx];
            int real = realList.get(idx);
            assertEquals(true, exp == real);
        }
    }

    @Test
    public void lrn() {
        System.out.println("testing ... lrn()");
        BST<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        int expList[] = {10, 25, 20, 60, 80, 70, 50};
        List<Integer> realList = bst.lrn();
        System.out.println(realList);
        for (int idx = 0; idx < expList.length; idx++) {
            int exp = expList[idx];
            int real = realList.get(idx);
            assertEquals(true, exp == real);
        }
    }

    @Test
    public void bfs() {
        System.out.println("testing ... lrn()");
        BST<Integer> bst = new BST<>();

        int values[] = {50, 20, 70, 10, 25, 60, 80};
        for (int v : values) bst.add(v);
        assertEquals(7, bst.size());
        int expList[] = {50, 20, 70, 10, 25, 60, 80};
        List<Integer> realList = bst.bfs();
        System.out.println(realList);
        for (int idx = 0; idx < expList.length; idx++) {
            int exp = expList[idx];
            int real = realList.get(idx);
            assertEquals(true, exp == real);
        }
    }

}
