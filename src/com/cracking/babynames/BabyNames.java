package com.cracking.babynames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BabyNames {
    public static void main(String[] args) {
        HashMap<String, Integer> names = new HashMap<>();
        names.put("John", 15);
        names.put("Jon", 12);
        names.put("Chris", 13);
        names.put("Kris", 4);
        names.put("Christopher", 19);

        String[][] synonyms = {
                {"Jon", "John"},
                {"Chris", "Kris"},
                {"Chris", "Christopher"}
        };
        //Output: John (27), Kris (36)

        try {
            HashMap<String, Integer> result = solution(names, synonyms);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, Integer> solution(HashMap<String, Integer> names, String[][] synonyms) throws Exception {
        HashMap<String, Integer> resultAggregate = new HashMap<>();

        /*
         * each name has its own Group (arrayList)
         * we go trough all synonyms and merge the groups
         * at the end we are left with the aggregated groups
         * */
        HashMap<String, ArrayList<String>> nameToGroup = new HashMap<>();
        for (String name : names.keySet()) {
            nameToGroup.put(name, new ArrayList<>() {{
                add(name);
            }});
        }

        for (String[] pair : synonyms) {
            String keep = pair[0];
            String duplicate = pair[1];
            ArrayList<String> keepGroup = nameToGroup.get(keep);
            keepGroup.add(duplicate);
            //we remove the reference for the duplicateGroup
            nameToGroup.put(duplicate, keepGroup);
        }

        //now find the distinct groups
        HashSet<ArrayList<String>> distinct = new HashSet<>();
        for (ArrayList<String> group : nameToGroup.values()) {
            distinct.add(group);
        }

        //calculate the total score for each distinct group
        for (ArrayList<String> group : distinct) {
            String first = group.get(0);
            int score = names.get(first);

            for (int i = 1; i < group.size(); i++) {
                String dulicateName = group.get(i);
                if (names.containsKey(dulicateName) == false) {
                    throw new Exception("something is very wrong" + dulicateName);
                }
                int duplicateScore = names.get(dulicateName);
                score += duplicateScore;
            }

            resultAggregate.put(first, score);
        }

        return resultAggregate;
    }
}
