package interviewbit.reversethestringwords;

import java.util.StringJoiner;

/**
 * https://www.interviewbit.com/problems/reverse-the-string/
 */
public class ReverseTheStringWords {
    private boolean isSpaceAt(String text, int index) {
        return text.charAt(index) == ' ';//works only for ASCII
    }

    private int getNextWord(int endAt, String text, StringJoiner res) {
        boolean foundNonSpace = false;
        int startAt = endAt;
        int i = endAt;

        for (; i >= 0; i--) {
            boolean isSpace = isSpaceAt(text, i);
            if (isSpace) {
                if (foundNonSpace) {//we already have a word
                    break;
                }
                //ignore spaces, act like a trim
                continue;
            }
            //we found something
            if (foundNonSpace == false) {
                foundNonSpace = true;
                endAt = i;//we may have skipped some spaces until here
            }
            startAt = i;
        }
        //add the previous word, if it exists
        if (isSpaceAt(text, startAt) == false) {
            res.add(text.substring(startAt, endAt + 1));
        }
        return i;
    }

    public String reverseWords(String a) {
        StringJoiner res = new StringJoiner(" ");
        int endAt = a.length() - 1;
        if (endAt == 0) {
            return a;
        }

        while (endAt > 0) {
            endAt = getNextWord(endAt, a, res);
        }
        ;

        return res.toString();
    }
}
