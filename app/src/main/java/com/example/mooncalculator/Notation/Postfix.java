package com.example.mooncalculator.Notation;

import java.util.Stack;

public class Postfix {
    private String postfix;

    public Postfix(String postfix) {
        this.postfix = postfix;
    }

    static boolean isOperator(char x) {

        switch (x) {
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
        }
        return false;
    }

    // Convert postfix to Prefix expression
    private String getPrefix(String postFix) {
        Stack<String> s = new Stack<String>();

        // length of expression
        int length = postFix.length();

        // reading from right to left
        for (int i = 0; i < length; i++) {

            // check if symbol is operator
            if (isOperator(postFix.charAt(i))) {

                // pop two operands from stack
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();

                // concat the operands and operator
                String temp
                        = postFix.charAt(i) + op2 + op1;

                // Push String temp back to stack
                s.push(temp);
            }

            // if symbol is an operand
            else {

                // push the operand to the stack
                s.push(postFix.charAt(i) + "");
            }
        }

        // concatenate all strings in stack and return the
        // answer
        String ans = "";
        for (String i : s)
            ans += i;
        return ans;
    }

    public String getPrefix() {
        try {
            return getPrefix(postfix);
        } catch (Exception e) {
            return "NAN";
        }
    }


    private boolean isOperand(char x)
    {
        return (x >= 'a' && x <= 'z') ||
                (x >= 'A' && x <= 'Z');
    }

    // Get Infix for a given postfix
    private String getInfix(String exp)
    {
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < exp.length(); i++)
        {
            // Push operands
            if (isOperand(exp.charAt(i)))
            {
                s.push(exp.charAt(i) + "");
            }

            // We assume that input is
            // a valid postfix and expect
            // an operator.
            else
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) +
                        op1 + ")");
            }
        }

        // There must be a single element
        // in stack now which is the required
        // infix.
        return s.peek();
    }


    public String getInfix() {
        try {
            return getInfix(postfix);
        } catch (Exception e) {
            return "NAN";
        }
    }

}
