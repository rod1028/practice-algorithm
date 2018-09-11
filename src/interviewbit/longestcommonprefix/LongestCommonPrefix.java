package interviewbit.longestcommonprefix;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/courses/programming/topics/strings/
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(ArrayList<String> a) {
        int len = a.size();
        if (len == 0) {
            return "";
        }
        if (len == 1) {
            return a.get(0);
        }

        StringBuilder res = new StringBuilder(len);
        String first = a.get(0);

        for (int i = 0; i < first.length(); i++) {
            Character c = first.charAt(i);
            //ok can be remove if you duplicate "return res.toString()"
            boolean ok = true;
            //is not so optimal because is not using the CPU cache/data locality
            //it has to read each string, for each char
            //probably taking the first string and removing chars from it
            //while they do not match will be faster, will access each string only once
            for (int j = 1; j < len; j++) {
                if (a.get(j).length() > i && c == a.get(j).charAt(i)) {
                    continue;
                }
                ok = false;
                break;
            }

            if (ok) {
                res.append(c);
            } else {
                break;
            }
        }

        return res.toString();
    }
}
