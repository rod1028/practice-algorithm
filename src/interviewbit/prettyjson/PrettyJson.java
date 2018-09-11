package interviewbit.prettyjson;

import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.interviewbit.com/problems/pretty-json/
 */
public class PrettyJson {
    private String repeat(String what, int count) {
        return String.join("", Collections.nCopies(count, what));
    }

    private boolean isCloseBracet(Character c) {
        return c == ']' || c == '}';
    }

    private boolean isOpenBracet(Character c) {
        return c == '[' || c == '{';
    }

    private boolean isBrace(Character c) {
        return isOpenBracet(c) || isCloseBracet(c);
    }

    private boolean isComma(Character c) {
        return c == ',';
    }

    private StringBuilder flush(StringBuilder buffer, ArrayList<String> result, int indent) {
        if (buffer.length() == 0) {
            return buffer;
        }
        buffer.insert(0, repeat("\t", indent));
        result.add(buffer.toString());
        return new StringBuilder(12);
    }

    public ArrayList<String> prettyJSON(String a) {
        int len = a.length();
        ArrayList<String> res = new ArrayList<>();

        int indent = 0;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < len; i++) {
            Character c = a.charAt(i);
            if (isBrace(c)) {
                buffer = flush(buffer, res, indent);
                if (isCloseBracet(c)) {
                    indent--;
                }
            }
            buffer.append(c);
            boolean nextCharIsComma = i < len - 1 && isComma(a.charAt(i + 1));

            if ((nextCharIsComma == false && isBrace(c)) || isComma(c)) {
                buffer = flush(buffer, res, indent);
                if (isOpenBracet(c)) {
                    indent++;
                }
            }
        }

        //leftovers, last line
        if (buffer.length() > 0) {
            buffer.insert(0, repeat("\t", indent));
            res.add(buffer.toString());
        }
        return res;
    }
}
