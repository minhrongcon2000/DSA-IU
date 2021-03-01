/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stacknqueue;

/**
 * @author LTSACH
 */
//11.1110.0011.0111
public class StackDemo {
    public static void main(String[] args) {
        //toBinary(99999); //12 = b1100
        //balancedExpression();
        stack_op();
    }

    public static void toBinary(int dec) {
        int backvalue = dec;
        Stack<Character> stack = new Stack<>();
        int counter = 0;
        while (dec > 0) {
            if (counter >= 4) {
                stack.push('.');
                counter = 0;
            }
            char bit = (char) ('0' + dec % 2);
            stack.push(bit);
            dec = dec / 2;
            counter++;
        }
        //
        System.out.printf("%d=b", backvalue);
        while (!stack.empty()) {
            char bit = stack.pop();
            System.out.print(bit);
        }
        System.out.println();
    }

    public static void balancedExpression() {
        String input = "{4*(3+5) + [(4/754) + 455]}";
        String openDelims = "([{";
        String closeDelims = ")]}";
        Stack<Character> stack = new Stack<>();
        boolean matched = true;
        for (int idx = 0; idx < input.length(); idx++) {
            char inChar = input.charAt(idx);
            int foundInOpenList = containOf(openDelims, inChar);
            if (foundInOpenList >= 0) stack.push(inChar);
            else {
                int foundInCloseList = containOf(closeDelims, inChar);
                if (foundInCloseList >= 0) {
                    char top = stack.pop();
                    char expectedChar = openDelims.charAt(foundInCloseList);
                    if (top != expectedChar) {
                        System.out.printf("Unmatched for %c and %c\n", expectedChar, inChar);
                        matched = false;
                        break;
                    }
                }//if
            }//else
        }//for
        if (matched && stack.empty()) System.out.println("The expression is balanced!");
        else System.out.println("The expression is unbalanced!");
    }

    private static int containOf(String input, char inChar) {
        for (int idx = 0; idx < input.length(); idx++) {
            if (input.charAt(idx) == inChar) {
                return idx;
            }
        }
        return -1;
    }

    public static void stack_op() {
        String input = "EAS*Y*QUE***ST***IO*N***";
        Stack<Character> stack = new Stack<>();
        System.out.println("Sequences of chars popped from the stack:");
        for (int idx = 0; idx < input.length(); idx++) {
            Character ch = input.charAt(idx);
            if (ch == '*') {
                char top = stack.pop();
                System.out.println(top);
            } else stack.push(ch);
        }
    }
}
