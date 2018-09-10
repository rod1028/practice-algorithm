package interviewbit.zigzagstring;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/zigzag-string/
 */
public class ZigZagString {
    public String convert(String text, int rows) {
        if (rows <= 1 || text.length() <= rows) {
            return text;
        }

        ArrayList<ArrayList<Character>> cRows = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            cRows.add(new ArrayList<Character>());
        }

        boolean goingDown = true;
        int row = 0;

        //less optimal than charAt but more readable
        for (Character c : text.toCharArray()) {
            cRows.get(row).add(c);

            //next row is the tricky part
            if (goingDown) {
                row++;
                if (row >= rows) {
                    row = rows - 2;
                    goingDown = false;
                }
            } else {
                row--;
                if (row < 0) {
                    row = 1;
                    goingDown = true;
                }
            }
        }

        StringBuilder res = new StringBuilder(text.length());
        for (ArrayList<Character> r : cRows) {
            for (Character c : r) {
                res.append(c);
            }
        }
        return res.toString();
    }
}
