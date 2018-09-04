package interviewbit.distinctnumbersinwindow;

import java.util.ArrayList;
import java.util.HashMap;

class Window {
    private HashMap<Integer, Integer> map = new HashMap<>();

    void add(int key) {
        Integer count = map.getOrDefault(key, 0);
        map.put(key, count + 1);
    }

    void remove(int key) {
        Integer count = map.getOrDefault(key, 0);
        if (count <= 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
    }

    int getDistinct() {
        return map.size();
    }
}

public class DistinctNumbersInWindow {
    public ArrayList<Integer> dNums(ArrayList<Integer> a, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int len = a.size();

        if (k > len) {
            return result; //empty array
        }

        //initialization of window
        Window w = new Window();
        for (int i = 0; i < k; i++) {
            w.add(a.get(i));
        }
        result.add(w.getDistinct());

        for (int i = k; i < len; i++) {
            int oldest = a.get(i - k);
            w.remove(oldest);
            w.add(a.get(i));
            result.add(w.getDistinct());
        }

        return result;
    }
}
