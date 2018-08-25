package evaluatearithexpress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.BiFunction;

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
    public static void main(String[] args) {

    }

    private static HashMap<String, BiFunction<Integer, Integer, Integer>> table =
            new HashMap<String, BiFunction<Integer, Integer, Integer>>() {
                {
                    put("+", (Integer a, Integer b) -> a + b);
                    put("-", (Integer a, Integer b) -> a - b);
                    put("*", (Integer a, Integer b) -> a * b);
                    put("/", (Integer a, Integer b) -> a / b);
                }
            };

//    private static ArrayList<String> importantOp = new ArrayList<String>() {{
//        add("*");
//        add("/");
//    }};

    private static void doNextOp(Stack<Integer> numbers, Stack<String> ops) {
        String opStr = ops.pop();
        BiFunction<Integer, Integer, Integer> op = table.get(opStr);
        int b = numbers.pop();
        int a = numbers.pop();
        int c = op.apply(a, b);
        numbers.push(c);
    }

    public int evalRPN(ArrayList<String> A) {
        if (A == null || A.size() == 0) {
            return Integer.MIN_VALUE;
        }
        if (A.size() == 1) {
            return Integer.valueOf(A.get(0));
        }

        Stack<Integer> numbers = new Stack<>();
        Stack<String> ops = new Stack<>();

        for (String s : A) {
            boolean isOperator = table.containsKey(s);
//            boolean isImpOperator = isOperator && importantOp.contains(s);

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

        return numbers.get(0);
    }
}
