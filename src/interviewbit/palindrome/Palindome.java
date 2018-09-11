package interviewbit.palindrome;

/**
 * https://www.interviewbit.com/problems/palindrome-string/
 */
public class Palindome {
    //we only accept LOWERCASE and UPPERCASE in Unicode specification
    private boolean isValid(Character c) {
        return Character.isLowerCase(c) || Character.isUpperCase(c)
                || Character.isDigit(c);
    }

    public int isPalindrome(String A) {
        if (A.length() <= 1) {
            return 1;
        }

        int fw = 0;
        int len = A.length();
        int bk = len - 1;

        while (fw < bk) {
            while (fw < len && isValid(A.charAt(fw)) == false) {
                fw++;
            }
            while (bk >= 0 && isValid(A.charAt(bk)) == false) {
                bk--;
            }
            if (fw > bk) {
                break;//no use to check any further, equality is symetric
            }

            Character f = Character.toLowerCase(A.charAt(fw));
            Character b = Character.toLowerCase(A.charAt(bk));
            if (f != b) {
                return 0;//short circuit at the first bad sign
            }
            fw++;
            bk--;
        }
        return 1;
    }
}
