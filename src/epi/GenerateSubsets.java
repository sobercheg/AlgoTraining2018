package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EPI: Recursion, page 291
 * Generate All Subsets of Size k
 */
public class GenerateSubsets {

    private void generateSubsetsSizeK(List<Integer> set, int k, int fromIndex, List<Integer> subsetSoFar, List<List<Integer>> subsets) {
        if (k == subsetSoFar.size()) {
            subsets.add(new ArrayList<>(subsetSoFar));
            return;
        }

        for (int i = fromIndex; i < set.size(); i++) {
            subsetSoFar.add(set.get(i));
            generateSubsetsSizeK(set, k, i + 1, subsetSoFar, subsets);
            subsetSoFar.remove(subsetSoFar.size() - 1);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = new ArrayList<>();
        GenerateSubsets s = new GenerateSubsets();
        s.generateSubsetsSizeK(Arrays.asList(1, 2, 3, 4, 5), 2, 0, new ArrayList<>(), subsets);
        System.out.println(subsets);
        UnitTester.assertEquals(10, subsets.size());
    }
}
