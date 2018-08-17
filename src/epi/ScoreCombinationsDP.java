package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EPI: Dynamic Programming, page 306
 * Score Combinations
 */
public class ScoreCombinationsDP {

    private List<List<Integer>> getCombinations(int finalScore, List<Integer> possibleScores) {
        // For each score sum up to finalScore calculate all possible combos like this:
        // for (score : scores):
        //   if scores[k - score] exists:
        //     scores[k].addAll(scores[k - score]).add(score)
        List<List<List<Integer>>> combosForScore = new ArrayList<>();
        for (int i = 0; i <= finalScore; i++) {
            combosForScore.add(new ArrayList<>());
        }
        for (int i = 1; i <= finalScore; i++) {
            // For each final score build the next solution from previous
            for (int possibleScore : possibleScores) {
                // For each possible score build a solution which sums up to the given score sum based on prev sol.
                if (i - possibleScore > 0) {
                    // If there are solutions for k - score
                    for (List<Integer> previousCombos : combosForScore.get(i - possibleScore)) {
                        List<Integer> scoreCombo = new ArrayList<>(previousCombos);
                        scoreCombo.add(possibleScore);
                        combosForScore.get(i).add(scoreCombo);
                    }
                } else if (i == possibleScore) {
                    List<Integer> singularCombo = new ArrayList<>();
                    singularCombo.add(possibleScore);
                    combosForScore.get(i).add(singularCombo);
                }
            }
        }
        return combosForScore.get(finalScore);
    }

    public static void main(String[] args) {
        ScoreCombinationsDP combos = new ScoreCombinationsDP();
        System.out.println(combos.getCombinations(12, Arrays.asList(2, 3, 7)));
    }
}
