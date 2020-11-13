package com.example.mooncalculator.Notation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InFix {
    private String infix;

    public InFix(String infix) {
        this.infix = infix;
    }

    private int getPreference(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else return -1;
    }

    private String getPostFixString(String s) {
        Stack<Character> stack = new Stack<>();
        List<String> postFixList = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            char word = s.charAt(i);
            if (word == ' ') {
                continue;
            }
            if (word == '(') {
                stack.push(word);
                flag = false;
            } else if (word == ')') {
                flag = false;
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    } else {
                        postFixList.add(stack.pop() + "");
                    }
                }
            } else if (word == '+' || word == '-' || word == '*' || word == '/') {
                flag = false;
                if (!stack.isEmpty()) {
                    while (!stack.isEmpty() && getPreference(stack.peek()) >= getPreference(word)) {
                        postFixList.add(stack.pop() + "");
                    }
                }
                stack.push(word);
            } else {
                if (flag) {
                    String lastNumber = postFixList.get(postFixList.size() - 1);
                    lastNumber += word;
                    postFixList.set(postFixList.size() - 1, lastNumber);
                } else
                    postFixList.add(word + "");
                flag = true;
            }
        }
        while (!stack.isEmpty()) {
            postFixList.add(stack.pop() + "");
        }
        String result = "";
        for (String s1 : postFixList) {
            result = result.concat(s1);
        }
        return result;
    }

    public String getPostfix() {
        try {
            return getPostFixString(infix);
        } catch (Exception e) {
            return "NAN";
        }
    }

    public String getPrefix() {
        try {
            return getPrefix(infix);
        } catch (Exception e) {
            return "NAN";
        }
    }

    private int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    private String getPrefix(String infix) {

        StringBuilder result = new StringBuilder();
        StringBuilder input = new StringBuilder(infix);
        input.reverse();
        Stack<Character> stack = new Stack<Character>();

        char[] charsExp = new String(input).toCharArray();
        for (int i = 0; i < charsExp.length; i++) {

            if (charsExp[i] == '(') {
                charsExp[i] = ')';
                i++;
            } else if (charsExp[i] == ')') {
                charsExp[i] = '(';
                i++;
            }
        }
        for (int i = 0; i < charsExp.length; i++) {
            char c = charsExp[i];

            //check if char is operator or operand
            if (precedence(c) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            } else if (c == ')') {
                char x = stack.pop();
                while (x != '(') {
                    result.append(x);
                    x = stack.pop();
                }
            } else if (c == '(') {
                stack.push(c);
            } else {
                //character is neither operator nor "("
                result.append(c);
            }
        }

        for (int i = 0; i <= stack.size(); i++) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }
}
