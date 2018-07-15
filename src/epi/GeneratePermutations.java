package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * EPI: Recursion, page 287
 * Generate permutations.
 */
public class GeneratePermutations {

    // Method 1: generate perms for A[1:n] by swapping A[0] with each element.
    private List<List<Integer>> generateBySwapUsingLists(List<Integer> array) {
        if (array.size() == 1) {
            List<List<Integer>> perms = new ArrayList<>();
            perms.add(array);
            return perms;
        }

        // Swap perms[num] with the num element and generate perms for
        List<List<Integer>> perms = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Collections.swap(array, 0, i);
            List<List<Integer>> subPerms = generateBySwapUsingLists(new ArrayList<>(array.subList(1, array.size())));
            for (List<Integer> perm : subPerms) {
                perm.add(0, array.get(0));
            }
            perms.addAll(subPerms);

        }
        return perms;
    }

    public static void main(String[] args) {
        GeneratePermutations p = new GeneratePermutations();
        List<Integer> perm = new ArrayList<>();
        perm.add(1);
        perm.add(2);
        perm.add(3);
        System.out.println(p.generateBySwapUsingLists(perm));
        UnitTester.assertEquals(6, p.generateBySwapUsingLists(perm).size());
    }
}
