package interviewbit.validnumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * https://www.interviewbit.com/problems/valid-number/
 * Solution - verbose code, time O(2n)when exp and space O(1)
 * Could be done with a regex
 */
public class ValidNumber {
    private static ArrayList<Character> good =
            new ArrayList<Character>(Arrays.asList('-', '+', '.', 'e'));

    private boolean isDigit(Character c) {
        return (c >= '0' && c <= '9');
    }

    private boolean isValidNonDigit(Character c) {
        return good.contains(c);
    }

    private boolean isSpace(Character c) {
        return c == ' ';//ASCII
    }

    private boolean signCheck(Character c,
                              HashMap<Character, Integer> counts, Character firstChar) {
        int cCount = counts.getOrDefault(c, 0);
        //allowed exp have sign too
        int allowed = counts.getOrDefault('e', 0) == 0 ? 1 : 2;

        if (cCount > allowed) {
            return false;
        }
        if (cCount == 0) {
            return true;
        }
        //only the first char
        return firstChar == c;
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isNumber(final String a) {
        if (a.length() == 0) {
            return 0;
        }
        StringBuilder clean = new StringBuilder();
        boolean hasZero = false;
        boolean hasDigitNonZero = false;
        boolean hasNonDigits = false;
        int expIndex = -1;

        HashMap<Character, Integer> counts = new HashMap<>(6);

        //trim spaces, check invalid
        for (Character c : a.toCharArray()) {
            if (isSpace(c)) {
                continue;
            }
            if (isDigit(c) == false && isValidNonDigit(c) == false) {
                return 0;
            }

            hasZero = hasZero || c == '0';
            hasDigitNonZero = hasDigitNonZero || isDigit(c) && c != '0';
            hasNonDigits = hasNonDigits || isValidNonDigit(c);

            int oldCount = counts.getOrDefault(c, 0);
            counts.put(c, oldCount + 1);

            if (c == 'e') {
                expIndex = clean.length();
            }
            clean.append(c);
        }

        if (clean.length() == 0) {
            return 0;
        }

        Character firstChar = clean.charAt(0);
        Character lastChar = clean.charAt(clean.length() - 1);

        //cannot end in a . or , or anything
        if (isDigit(lastChar) == false) {
            return 0;//must end in a digit, invalid 0. or 23e
            //also ensure that the string has at least 1 digit
        }
        if (hasNonDigits == false) {
            return 1; //short circuit, only digits
        }
        if (signCheck('-', counts, firstChar) == false ||
                signCheck('+', counts, firstChar) == false) {
            return 0;
        }
        if (counts.getOrDefault('e', 0) > 1) {//only 0 or 1 exp
            return 0;
        }
        if (firstChar == 'e') {//must start with a digit or sign
            return 0;
        }
        if (counts.getOrDefault('.', 0) > 1) {//only 0 or 1 decimal points
            return 0;
        }

        if (expIndex > -1) {
            String base = clean.substring(0, expIndex);
            String exp = clean.substring(expIndex + 1);
            //  System.out.println(base + " v " + exp);
            if (isNumber(base) == 0) {
                return 0;
            }
            //no decimal exp allowed
            if (isNumber(exp) == 0 || exp.contains(".")) {
                return 0;
            }
        }

        return 1;
    }
}
