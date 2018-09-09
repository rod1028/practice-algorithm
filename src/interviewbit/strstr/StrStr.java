package interviewbit.strstr;

/**
 * https://www.interviewbit.com/problems/implement-strstr/
 */

//inneficient algorithm with a small improvement for jumps
public class StrStr {
    private static class Result {
        boolean equal;
        int skipLen;
    }

    public int strStr(final String haystack, final String needle) {
        int needLen = needle.length();
        int haysLen = haystack.length();

        if (needLen == 0 || haysLen == 0 || needLen > haysLen) {
            return -1;
        }

        //we can skip the first characters if the last char is different
        int start = haystack.charAt(needLen - 1) == needle.charAt(needLen - 1) ? 0 : needLen;
        for (int i = start; i < haysLen - needLen + 1; ) {
            Result cmp = compareSub(needle, haystack, i);

            if (cmp.equal) {
                return i;
            }
            //if we found the first letter of the needle
            //when we compared the substring or until the last checked letter
            if (cmp.skipLen > 0) {
                i += cmp.skipLen;
            } else {
                i++;
            }
        }
        return -1;
    }

    private Result compareSub(final String needle, final String haystack, int start) {
        Result res = new Result();
        res.skipLen = 0;
        res.equal = true;
        char firstLet = needle.charAt(0);
        int firstCharFound = 0;

        for (int i = 0; i < needle.length(); i++) {
            char hayChar = haystack.charAt(i + start);
            char needChar = needle.charAt(i);

            if (hayChar == firstLet && firstCharFound == 0) {
                firstCharFound = i;
            }

            if (hayChar != needChar) {
                res.equal = false;
                res.skipLen = firstCharFound > 0 ? firstCharFound : i;
                break;
            }
        }

        return res;
    }
}
