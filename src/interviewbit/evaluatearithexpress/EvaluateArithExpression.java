package interviewbit.evaluatearithexpress;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * https://www.interviewbit.com/problems/evaluate-expression/
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Examples:
 * <p>
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateArithExpression {
    private static HashMap<String, BinaryOperator<Integer>> table =
            new HashMap<String, BinaryOperator<Integer>>() {
                {
                    put("+", (Integer a, Integer b) -> a + b);
                    put("-", (Integer a, Integer b) -> a - b);
                    put("*", (Integer a, Integer b) -> a * b);
                    put("/", (Integer a, Integer b) -> a / b);
                }
            };

    private static void doNextOp(ArrayDeque<Integer> numbers, ArrayDeque<String> ops) {
        String opStr = ops.pop();
        BiFunction<Integer, Integer, Integer> op = table.get(opStr);
        int b = numbers.pop();
        int a = numbers.pop();
        int c = op.apply(a, b);
        numbers.push(c);
    }

    public int evalRPN(ArrayList<String> reversePolishList) {
        if (reversePolishList == null || reversePolishList.size() == 0) {
            return Integer.MIN_VALUE;
        }
        if (reversePolishList.size() == 1) {
            return Integer.valueOf(reversePolishList.get(0));
        }

        ArrayDeque<Integer> numbers = new ArrayDeque<>();
        ArrayDeque<String> ops = new ArrayDeque<>();

        for (String s : reversePolishList) {// reversePolishList.forEach(s -> {
            boolean isOperator = table.containsKey(s);
            if (isOperator) {
                ops.push(s);

                doNextOp(numbers, ops);
            } else {
                int n = Integer.valueOf(s);
                numbers.push(n);
            }
        }

        while (ops.size() > 0) {
            doNextOp(numbers, ops);
        }

        return numbers.getFirst();
    }
}
