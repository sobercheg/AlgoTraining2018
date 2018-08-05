package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EPI: Recursion, page 298
 * Compute a Gray code using the prefix method.
 */
public class GrayCodeSequencePrefix {

    private List<String> generateSequence(int len) {
        if (len == 1) {
            return new ArrayList<>(Arrays.asList("0", "1"));
        }

        List<String> previousSeq = generateSequence(len - 1);

        // Prepend 0 to all values
        List<String> newSeq = previousSeq.stream().map(v -> "0" + v).collect(Collectors.toList());

        // Prepend 1 to all values in inverse order
        List<String> part2 = previousSeq.stream().map(v -> "1" + v).collect(Collectors.toList());
        Collections.reverse(part2);
        newSeq.addAll(part2);
        return newSeq;
    }

    public static void main(String[] args) {
        GrayCodeSequencePrefix g = new GrayCodeSequencePrefix();
        System.out.println(g.generateSequence(3));
    }
}
