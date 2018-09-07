package interviewbit.allocatebooks;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.interviewbit.com/problems/allocate-books/
 * Same as parition painters
 */
public class AllocateBooksStudents {
    private static int sum(ArrayList<Integer> books) {
        int sum = 0;
        for (Integer pages : books) {
            sum += pages;
        }
        return sum;
    }

    public int books(ArrayList<Integer> books, int students) {
        if (books == null || students <= 0 || books.size() == 0) {
            return -1;
        }
        if (students == 1) {
            return sum(books);
        }

        if (students > books.size()) {
            return -1; //Each student has to be allocated at least one book.
        }

        int left = Collections.max(books);
        int right = sum(books);
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (guess(books, mid, students)) {
                result = mid;
                right = mid - 1;//search for a better solution, smaller
            } else {
                left = mid + 1;//the number is too low, search bigger one
            }
        }
        return result;
    }

    private static boolean guess(ArrayList<Integer> books, int maxSum, int students) {
        int sum = 0;

        for (Integer pages : books) {
            sum += pages;
            if (sum <= maxSum) {
                continue;
            }
            sum = pages;
            students--;
            if (students == 0) {
                return false;
            }
        }
        return true;
    }
}
