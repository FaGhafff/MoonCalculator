package com.example.mooncalculator;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.Arrays;

public class Calculate {
    Expression expression;
    Function function;

    public Calculate(String text) {
        init(text);
    }

    private void init(String text) {
        try{
        if (text.contains("→")) {
            System.err.println("fuckfuckfuck");
            String[] textList = text.split("→");
            System.out.println(Arrays.toString(textList));
            function = new Function(textList[0]);
            System.out.println(textList[1].substring(0,textList[1].indexOf("=")));
            expression = new Expression(textList[1].substring(0,textList[1].indexOf("=")),function);
        } else {
        expression = new Expression(text);
            System.err.println("fuckfuckfuckkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");}
    }catch (Exception e){
            System.out.println(e.getMessage());
        expression = new Expression("NaN");}
    }

    public String getExpression() {
        return expression.getExpressionString();
    }

    public String getResult() {
        return String.valueOf(expression.calculate());
    }
}
