package interviewbit.redundantbracets;

import java.util.ArrayDeque;

/**
 * https://www.interviewbit.com/problems/redundant-braces/
 * https://www.geeksforgeeks.org/expression-contains-redundant-bracket-not/
 */
public class RedundantBraces {
    private boolean isSign(Character c) {
        switch (c) {
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
        }
        return false;
    }

    public int braces(String A) {

        ArrayDeque<Character> stack = new ArrayDeque<>(A.length());

        for (Character c : A.toCharArray()) {
            if (c == ')') {
                if (stack.peek() == '(') {
                    //we found a 2nd (())
                    return 1;
                }

                boolean atLeastOneSign = false;
                while (stack.peek() != '(') {
                    if (isSign(stack.pop())) {
                        atLeastOneSign = true;
                    }
                }
                stack.pop();//pop the ( too

                if (atLeastOneSign == false) {
                    return 1;//it was a (a)
                }

                continue;
            }
            stack.push(c);
        }

        return 0;
    }
}
