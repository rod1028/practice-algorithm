package com.cracking.remduplicatessortedarray;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.interviewbit.com/problems/remove-duplicates-from-sorted-array/
 */
public class RemDupSortedArray {
    public static void main(String[] args) {

    }

    private static int solution(ArrayList<Integer> a) {
        if (a.size() <= 1) {
            return a.size();
        }

        int left = 0;
        int right = 1;
        while (right < a.size()) {
            if (a.get(left).equals(a.get(right))) {
                right++;
                continue;
            }

            left++;
            Collections.swap(a, left, right);
            right++;
        }
        return left + 1;
    }
}
