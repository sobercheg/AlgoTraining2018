package epi;

import utils.UnitTester;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * EPI: Recursion, page 289
 * Generate the power set
 */
public class GeneratePowerSet {

    private Set<Set<Integer>> generate(int numOfElements) {
        if (numOfElements == 0) {
            Set<Set<Integer>> sets = new LinkedHashSet<>();
            sets.add(new LinkedHashSet<>());
            return sets;
        }
        Set<Set<Integer>> subsets = generate(numOfElements - 1);
        // New subsets = {old subsets} + {old subsets with a new element inserted}
        Set<Set<Integer>> newSubsets = new LinkedHashSet<>(subsets);
        for (Set<Integer> currentSubsets : subsets) {
            Set<Integer> newSet = new LinkedHashSet<>(currentSubsets);
            newSet.add(numOfElements);
            newSubsets.add(newSet);
        }

        return newSubsets;
    }

    public static void main(String[] args) {
        GeneratePowerSet powerSet = new GeneratePowerSet();
        System.out.println(powerSet.generate(4));
        UnitTester.assertEquals(powerSet.generate(2).size(), 4);
        UnitTester.assertEquals(powerSet.generate(3).size(), 8);
        UnitTester.assertEquals(powerSet.generate(4).size(), 16);
    }
}
