package interviewbit.windowstring;

import java.util.HashMap;
import java.util.Set;

/*
https://www.interviewbit.com/problems/window-string/
 */
public class WindowString {
    private static String best(String currentBest, Window window) {
        if (window.isValid() == false) {
            return currentBest;
        }
        String validSubstr = window.toString();
        if (currentBest.length() == 0 || validSubstr.length() < currentBest.length()) {
            currentBest = validSubstr;
        }
        return currentBest;
    }

    public static String minWindow(String text, String search) {
        String bestWindow = "";
        if (text == null || search == null ||
                search.length() > text.length()) {
            return bestWindow;
        }

        HashMap<Character, Integer> minCount = getCharCount(search);
        Window window = new Window(minCount, text);
        while (window.finished() == false) {

            while (window.finished() == false && window.isValid() == false && window.advanceEnd()) {
                bestWindow = best(bestWindow, window);
            }
            window.advanceStart();
            bestWindow = best(bestWindow, window);
        }

        return bestWindow;
    }

    private static HashMap<Character, Integer> getCharCount(String text) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (char c : text.toCharArray()) {
            int count = result.getOrDefault(c, 0);
            result.put(c, count + 1);
        }
        return result;
    }
}


class Window {
    private Set<Character> chars;
    private final HashMap<Character, Integer> minCount;
    private int startIndex, endIndex;
    private final String original;

    Window(HashMap<Character, Integer> minCount, String text) {
        this.original = text;
        this.minCount = minCount;
        this.chars = minCount.keySet();
        //because startIndex is 0
        updateCount(0, true);
    }

    boolean finished() {
        int maxIndex = original.length() - 1;
        return startIndex >= maxIndex;
    }

    private void updateCount(int index, boolean add) {
        Character character = original.charAt(index);
        if (chars.contains(character) == false) {
            return;
        }
        int currentVal = minCount.get(character);
        if (add) {
            minCount.put(character, currentVal - 1);
        } else {
            minCount.put(character, currentVal + 1);
        }
    }

    void advanceStart() {
        updateCount(startIndex, false);
        startIndex++;
    }

    boolean advanceEnd() {
        int maxIndex = original.length() - 1;
        if (endIndex >= maxIndex) {
            return false;
        }
        endIndex++;
        updateCount(endIndex, true);
        return true;
    }

    boolean isValid() {
        for (Integer count : minCount.values()) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return original.substring(startIndex, endIndex + 1);
    }
}