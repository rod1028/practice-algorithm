package interviewbit.lengthlastword;

public class LengthOfTheLastWord {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int lengthOfLastWord(final String text) {
        int result = 0;
        boolean foundNonSpace = false;

        for (int i = text.length() - 1; i >= 0; i--) {
            boolean isSpace = text.charAt(i) == ' ';
            if (isSpace) {
                if (foundNonSpace) {
                    //the last word finish here
                    break;
                }
                //act like a trimRight()
                continue;
            }
            foundNonSpace = true;
            result++;
        }
        return result;
    }

}
