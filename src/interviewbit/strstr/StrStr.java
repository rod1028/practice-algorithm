package interviewbit.strstr;

/*
 * https://www.interviewbit.com/problems/implement-strstr/
 * best algorithms are KMP and Boyer-Moore
 */

/**
 * KMP algorithm
 * https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
 * https://www.youtube.com/watch?v=V5-7GzOfADQ&
 * https://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 */
class KMP {
    private int[] buildTable(final String needle) {
        int len = 0;//longest previous suffix
        int[] result = new int[needle.length()];

        result[0] = 0;
        int i = 1;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                result[i] = len;
                i++;
                continue;
            }

            //move back the pointer "len" until we find a
            //new suffix that ends at needle[i], if any
            while ((needle.charAt(i) != needle.charAt(len)) &&
                    len > 0) {
                len = result[len - 1];
            }

            result[i] = len;
            i++;
        }

        return result;
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int strStr(final String haystack, final String needle) {
        int needLen = needle.length();
        int haysLen = haystack.length();

        if (needLen == 0 || haysLen == 0 || needLen > haysLen) {
            return -1;
        }
        int[] failures = buildTable(needle);

        int hayPos = 0;
        int nee = 0;

        while (hayPos < haysLen && nee < needLen) {
            if (haystack.charAt(hayPos) == needle.charAt(nee)) {
                hayPos++;
                nee++;
                continue;
            }
            //they do not match
            if (nee == 0) {//no need to reset the needle
                hayPos++;
                continue;
            }
            nee = failures[nee - 1];
        }


        if (nee == needLen) {
            return hayPos - needLen;
        }
        return -1;
    }
}

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
