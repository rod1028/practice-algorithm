package dungeonprincess;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/dungeon-princess/
 * https://www.programcreek.com/2014/03/leetcode-dungeon-game-java/
 */
public class DungeonPrincess {
    private static int reqHp(int prevHP, int powerup) {
        return Math.max(1, prevHP - powerup);
    }

    public int calculateMinimumHP(ArrayList<ArrayList<Integer>> A) {
        int rows = A.size();
        if (rows == 0) {
            return 0;
        }
        int cols = A.get(0).size();

        if (rows == 1 && cols == 1) {
            return Math.max(1, -A.get(0).get(0) + 1);
        }

        //we start from the end and choose the best way to the start
        //because we go in reverse, we treat negative powerups as positive required
        //health, and if they are positive we cut them to 1
        //also we choose the best between the right and bottom

        int[][] hp = new int[rows][cols];
        hp[rows - 1][cols - 1] = reqHp(1, A.get(rows - 1).get(cols - 1));

        //bottom row, from right to left, has only
        for (int col = cols - 2; col >= 0; col--) {
            int rightVal = hp[rows - 1][col + 1];
            int myVal = A.get(rows - 1).get(col);
            //nowhere to go except right, so we have to have
            //myVal of HP to enter current cell and (+) for the right cell(rightVal)
            hp[rows - 1][col] = reqHp(rightVal, myVal);
        }

        //right column
        for (int row = rows - 2; row >= 0; row--) {
            int botVal = hp[row + 1][cols - 1];
            int myVal = A.get(row).get(cols - 1);
            hp[row][cols - 1] = reqHp(botVal, myVal);
        }

        //inner cells can choose between 2 directions, we wnt the smallest one
        for (int row = rows - 2; row >= 0; row--) {
            for (int col = cols - 2; col >= 0; col--) {
                int myVal = A.get(row).get(col);
                int rightVal = reqHp(hp[row][col + 1], myVal);
                int botVal = reqHp(hp[row + 1][col], myVal);
                hp[row][col] = Math.min(rightVal, botVal);
            }
        }
        //for (int[] row : hp)
        //    System.out.println(Arrays.toString(row));

        return hp[0][0];
    }
}
