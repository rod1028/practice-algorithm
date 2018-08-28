package mineditdistance3w;

/**
 * https://en.wikipedia.org/wiki/Edit_distance
 * https://www.interviewbit.com/problems/edit-distance/
 * https://www.geeksforgeeks.org/edit-distance-dp-5/
 * <p>
 * Given two words A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * Example :
 * edit distance between
 * "Anshuman" and "Antihuman" is 2.
 * <p>
 * Operation 1: Replace s with t.
 * Operation 2: Insert i.
 */
public class MinimumEditDistance3w {
    private static int min(int a, int b, int c) {
        if (a > b) {
            return Math.min(b, c);
        }
        return Math.min(a, c);
    }

    public int minDistance(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();

        //first row and col presumes the strings are empty
        int dp[][] = new int[lenA + 1][lenB + 1];

        //build and remember the prev values
        for (int indexA = 0; indexA <= lenA; indexA++) {
            for (int indexB = 0; indexB <= lenB; indexB++) {

                if (indexA == 0) {
                    //if A is empty, we need to add indexB letters to make them equal
                    dp[indexA][indexB] = indexB;
                    continue;
                }

                if (indexB == 0) {
                    //if B is empty, we need to remove A letters
                    dp[indexA][indexB] = indexA;
                    continue;
                }

                if (A.charAt(indexA - 1) == B.charAt(indexB - 1)) {
                    //no new op, keep the best prev one
                    int prev = dp[indexA - 1][indexB - 1];
                    dp[indexA][indexB] = prev;
                    continue;
                }

                //they are different, we need to find the best out of 3
                //left - insert in A, top - remove from B , top-left replace char
                dp[indexA][indexB] = 1 + //the change plus the best prev one
                        min(dp[indexA - 1][indexB],
                                dp[indexA][indexB - 1],
                                dp[indexA - 1][indexB - 1]);

            }
        }

        return dp[lenA][lenB];
    }
}
