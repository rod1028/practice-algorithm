package interviewbit.validipaddresses;

import java.util.ArrayList;

/**
 * https://www.interviewbit.com/problems/valid-ip-addresses/
 */
public class ValidIpAdresses {
    private String join(String digits, int[] dots) {
        return join(digits, dots[0], dots[1], dots[2]);
    }

    private String join(String digits, int p1, int p2, int p3) {
        if (p1 < 0 || p1 >= p2 || p2 >= p3 || p3 >= digits.length()) {
            throw new IllegalArgumentException();
        }
        //optimal but ugly, the compiler will transform it to a StringBuilder
        return digits.substring(0, p1) +
                '.' + digits.substring(p1, p2) +
                '.' + digits.substring(p2, p3) +
                '.' + digits.substring(p3, digits.length());
    }

    public ArrayList<String> restoreIpAddresses(String digits) {
        if (digits.length() == 12) {
            return new ArrayList<String>() {{
                add(join(digits, 3, 6, 9));
            }};
        }
        if (digits.length() == 4) {
            return new ArrayList<String>() {{
                add(join(digits, 1, 2, 3));
            }};
        }

        ArrayList<String> result = new ArrayList<>();
        rec(digits, 0, new int[3], result);

        return result;
    }

    private void rec(String digits, int piece, int[] dots, ArrayList<String> result) {
        if (piece >= 3) {
            String asStr = digits.substring(dots[2], digits.length());
            int num = Integer.parseInt(asStr, 10);

            if (num > 255 ||
                    (num == 0 && asStr.length() > 1) || //multiple 0
                    (asStr.length() > 1 && asStr.charAt(0) == '0')) { //starts with 0
                return;
            }
            //base case add the end to the result
            result.add(join(digits, dots));
            return;
        }

        int start = piece == 0 ? 0 : dots[piece - 1];
        for (int i = start + 1; i < digits.length(); i++) {
            String asStr = digits.substring(start, i);
            int num = Integer.parseInt(asStr, 10);
            //try to put the next
            if (num > 255) {
                break;//no use to continue, this piece is not valid
            }
            int lenRemaining = digits.length() - i;
            int piecesLeft = 3 - piece;
            if (lenRemaining < piecesLeft) {
                break;//cannot add the dot on i, there are too few digits left
                //to fill the next pieces
            }

            //if there are enough digits for the remainingi pieces try to find them
            if (lenRemaining <= piecesLeft * 3) {
                dots[piece] = i;
                rec(digits, piece + 1, dots, result);
            }

            if (num == 0) {
                break;//cannot add digit after 0
            }
        }
    }
}
