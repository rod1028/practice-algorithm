package interviewbit.simplifydirectorypath;

import java.util.ArrayDeque;
import java.util.StringJoiner;

/**
 * https://www.interviewbit.com/problems/simplify-directory-path/
 * To optimize we can remove the split and the array, and do a custom linear traverse.
 */
public class SimplifyDirectoryPath {
    public class Solution {
        public String simplifyPath(String A) {
            if (A.length() <= 1) {
                return "/";
            }

            String[] pieces = A.split("/");
            ArrayDeque<String> q = new ArrayDeque<>(pieces.length);
            for (String piece : pieces) {
                if (piece.equals(".") || piece.length() == 0) {
                    continue;
                }
                if (piece.equals("..")) {
                    if (q.isEmpty() == false) {
                        q.removeLast();
                    }
                    continue;
                }
                q.addLast(piece);
            }
            if (q.isEmpty()) {
                return "/";
            }

            StringJoiner res = new StringJoiner("/");
            while (q.isEmpty() == false) {
                res.add(q.removeFirst());
            }
            return "/" + res.toString();
        }
    }

}
