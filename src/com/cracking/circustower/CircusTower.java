package com.cracking.circustower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CircusTower {
    public static void main(String[] args) {
        Person[] ppl = new Person[4];
        ppl[0] = new Person(2, 7);
        ppl[1] = new Person(20, 30);
        ppl[2] = new Person(3, 20);
        ppl[3] = new Person(4, 10);

        try {
            int best = CircusTower.solution(ppl);
            System.out.println("Found:" + best);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Person> cloneLargestFit(ArrayList<ArrayList<Person>> towers, Person newTop) {
        ArrayList<Person> result = new ArrayList<>();
        for (ArrayList<Person> tower : towers) {
            Person prevTop = tower.get(tower.size() - 1);
            if (prevTop.canPlaceAbove(newTop) &&
                    tower.size() > result.size()) {
                result = tower;
            }
        }

        return (ArrayList<Person>) result.clone();
    }

    private static int solution(Person[] circus) {
        //sort by one of the properties DESC
        ArrayList<Person> list = new ArrayList<>(Arrays.asList(circus));
        list.sort(Collections.reverseOrder());

        ArrayList<ArrayList<Person>> bestAt = new ArrayList<>(list.size());
        int longest = 0;
        for (int i = 0; i < list.size(); i++) {
            Person top = list.get(i);
            ArrayList<Person> largestFit = cloneLargestFit(bestAt, top);
            largestFit.add(top);
            if (largestFit.size() > longest) {
                longest = largestFit.size();
            }
            bestAt.add(largestFit);//i=index here too
        }

        return longest;
    }
}

class Person implements Comparable<Person> {
    final int height;
    final int weight;

    Person(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    boolean canPlaceAbove(Person p) {
        return this.height > p.height &&
                this.weight > p.weight;
    }

    public int compareTo(Person p) {
        int byHeight = Integer.compare(this.height, p.height);
        if (byHeight == 0) {
            return Integer.compare(this.weight, p.weight);
        }
        return byHeight;
    }
}
