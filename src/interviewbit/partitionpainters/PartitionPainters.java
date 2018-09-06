package interviewbit.partitionpainters;

import java.util.ArrayList;
import java.util.Collections;

//Guessing the result, for each one a linear operation is done
//let K = sum(boards) - max(boards) and n=count(boards) time: O(K*n) space O(1)
public class PartitionPainters {
    public int paint(int painters, int time, ArrayList<Integer> boards) {
        if (painters == 0) {
            return 0;
        }
        if (painters == 1) {
            return (int) (sum(boards) * time % 10000003);
        }
        if (painters >= boards.size()) {
            //at leat 1 painter per board, min si max
            return Collections.max(boards) * time % 10000003;
        }

        //the result must be between this two values
        int left = Collections.max(boards);
        int right = (int) sum(boards);
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            boolean isOk = guess(mid, painters, boards);
            if (isOk) {
                //move to the left, maybe we can find something better (smaller)
                result = mid;
                right = mid - 1;
            }
            //move to the right if the guess is too small
            else {
                left = mid + 1;
            }
        }
        return result * time % 10000003;
    }

    private static boolean guess(int len, int painters, ArrayList<Integer> boards) {
        if (painters <= 0 || len <= 0) {
            return false;
        }

        int painter = 0;
        for (Integer board : boards) {
            painter += board;

            if (painter > len) {
                painters--;
                if (painters <= 0) {
                    return false;
                }
                painter = board;
            }
        }
        return true;
    }

    private static long sum(ArrayList<Integer> list) {
        long sum = 0;
        //one for all, is sum
        for (Integer element : list) {
            sum += (long) element;
        }
        return sum;
    }
}


//the brute force, recursive solution
//memoization can be added but is still inneficient
class Solution {
    public int paint(int painters, int time, ArrayList<Integer> boards) {
        if (painters == 0) {
            return 0;
        }
        if (painters == 1) {
            long sum = 0;
            //one for all, is sum
            for (Integer board : boards) {
                sum += (long) board;
            }
            return (int) (sum * time % 10000003);
        }
        if (painters >= boards.size()) {
            //at leat 1 painter per board, min si max
            return Collections.max(boards) * time;
        }
        //group the boards to get the minimal value of max painter time
        //this.boards = boards;
        return calc(0, painters - 1, 0, new int[painters], boards) * time % 10000003;
    }

    //OR Collections.max(Arrays.asList(arr)))
    private static int maxOf(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int v : arr) {
            max = Math.max(max, v);
        }
        return max;
    }

    private int calc(int painterIndex, int paintersLeft, int startIndex,
                     int[] sums, ArrayList<Integer> boards) {
        //base case
        if (paintersLeft < 0) {
            return maxOf(sums);
        }

        int minimumSum = Integer.MAX_VALUE;
        sums[painterIndex] = 0;

        for (int i = startIndex; i < boards.size() - paintersLeft; i++) {
            //attribute the I board to this
            sums[painterIndex] += boards.get(i);
            if (paintersLeft == 0 && i < boards.size() - 1) {
                //I have to take all the boards until the end
                continue;
            }
            int newSum = calc(painterIndex + 1, paintersLeft - 1, i + 1, sums, boards);
            minimumSum = Math.min(minimumSum, newSum);
        }
        return minimumSum;
    }
}

