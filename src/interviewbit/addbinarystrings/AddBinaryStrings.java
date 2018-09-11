package interviewbit.addbinarystrings;

/**
 * https://www.interviewbit.com/problems/add-binary-strings/
 * https://www.geeksforgeeks.org/program-to-add-two-binary-strings/
 * <p>
 * 3 = result 1 reminder 1
 * 2 = result 0 reminder 1
 * 1 = result 1 reminder 0
 * 0 = result 0 reminder 0
 */
public class AddBinaryStrings {
    public String addBinary(String a, String b) {
        int reminder = 0;
        StringBuilder res = new StringBuilder(32);
        int ai = a.length() - 1;
        int bi = b.length() - 1;

        while (ai >= 0 || bi >= 0 || reminder > 0) {
            int sum = reminder;
            reminder = 0;
            sum += (ai >= 0 && a.charAt(ai) == '1') ? 1 : 0;
            sum += (bi >= 0 && b.charAt(bi) == '1') ? 1 : 0;

            ai--;
            bi--;

            res.insert(0, sum % 2 == 1 ? '1' : '0');
            reminder = (sum > 1) ? sum / 2 : 0;
        }

        return res.toString();
    }
}
