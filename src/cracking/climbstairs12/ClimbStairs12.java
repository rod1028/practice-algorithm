package cracking.climbstairs12;

public class ClimbStairs12 {
    /**
     * https://www.interviewbit.com/problems/stairs/
     * Cache version is good for multiple queries, but adds space N complexity
     * Basically a fibonacci series
     */
//    static ArrayList<Integer> cache = new ArrayList<>(n);
    public int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n < 3) {
            return n;
        }
        int prev2 = 1;
        int prev1 = 2;

        // cache.add(1);//[1]
        // cache.add(2);//[11][2]
        // cache.add(3);//[111][12][21]
        //         //4   [1111][112][121][211][22]
        //         //5   [11111][1112][1121][1211][2111][122][212][221]

        for (int i = 3; i <= n; i++) {
            // int prev1 = cache.get(i-1);//1 stair diff, 1 way
            // int prev2 = cache.get(i-2);//2 stairs diff, 2 ways

            // cache.add(prev1+prev2);
            int tmp = prev2 + prev1;
            prev2 = prev1;
            prev1 = tmp;
            // cache.add(tmp);
        }
        return prev1;
        // return cache.get(n-1);//zero index based
    }
}
