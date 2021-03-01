package test;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

/**
 * @author LTSACH
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
        test.graph.DGraphModelTest.class,
        test.graph.UGraphModelTest.class,
        test.graph.TopoSortTest.class,
        test.tree.BSTTest.class
})
class AssignmentTestSuite2 {
}

public class Assignment2TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AssignmentTestSuite2.class);
        System.out.println(new String(new char[80]).replace('\0', '-'));
        if (result.wasSuccessful()) {
            System.out.println("Congratulation, all the tests are successful!");
        } else {
            System.out.println("There are some tests unsucessful:");
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
}
