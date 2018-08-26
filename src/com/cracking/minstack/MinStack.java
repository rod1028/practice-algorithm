package com.cracking.minstack;

import java.util.ArrayDeque;

/**
 * https://www.interviewbit.com/problems/min-stack/
 */
public class MinStack {
    private final ArrayDeque<Integer> stack = new ArrayDeque<>();
    private final ArrayDeque<Integer> min = new ArrayDeque<>();

    public void push(int x) {
        if (min.size() == 0 || x <= min.peek()) {
            min.push(x);
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.size() == 0) {
            return;
        }
        int v = stack.pop();
        if (v == min.peek()) {
            min.pop();
        }
    }

    public int top() {
        if (stack.size() == 0) {
            return -1;
        }
        return stack.peek();
    }

    public int getMin() {
        if (min.size() == 0) {
            return -1;
        }
        return min.peek();
    }
}
