package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.List;

/**
 * EPI: Recursion, page 298
 * Compute a Gray code.
 */
public class GrayCodeSequence {

    private void generateNext(List<Integer> sequenceSoFar, List<List<Integer>> fullSequences, int len) {
        if (sequenceSoFar.size() == Math.pow(2, len)) {
            fullSequences.add(new ArrayList<>(sequenceSoFar));
            return;
        }
        if (sequenceSoFar.isEmpty()) {
            sequenceSoFar.add(0);
        }
        int lastInSeq = sequenceSoFar.get(sequenceSoFar.size() - 1);
        for (int grayNumber : generateGrayNums(lastInSeq, len)) {
            if (sequenceSoFar.contains(grayNumber)) {
                continue;
            }
            sequenceSoFar.add(grayNumber);
            generateNext(sequenceSoFar, fullSequences, len);
            sequenceSoFar.remove(sequenceSoFar.size() - 1);
        }
    }

    private List<Integer> generateGrayNums(int num, int len) {
        // 010 -> {110, 000, 011} (differ in only one place)
        List<Integer> seq = new ArrayList<>();
        for (int i = 1; i <= len; i++) {
            // Flip each bit
            seq.add((1 << i - 1) ^ num);
        }
        return seq;
    }

    public static void main(String[] args) {
        GrayCodeSequence sequence = new GrayCodeSequence();
        List<List<Integer>> allSequences = new ArrayList<>();
        sequence.generateNext(new ArrayList<>(), allSequences, 3);
        System.out.println(allSequences);
        UnitTester.assertEquals(18, allSequences.size());
    }
}
