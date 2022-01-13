package strategy.score;

import common.Constants;
import distribution.recipient.Child;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Class for the score calculation strategy of a teen
 */
public class TeenScoreStrategy implements ScoreStrategy {
    /**
     * Calculates the average score of the child given as parameter
     * using the formula for the teen age category
     * @param child the child whose average score will be calculated
     * @return double representing the average score of the child
     */
    @Override
    public Double getAverageScore(final Child child) {
        ArrayList<Double> niceScores = child.getNiceScores();
        double scoreNumber = (double) niceScores.size();

        // calculate 1 + 2 + ... + n, with n = number of scores
        double denominator = (double) (scoreNumber * (scoreNumber + 1)) / 2;

        // calculate the weighted sum
        double weightedSum = IntStream.range(0, niceScores.size())
                // the weight for the nice score at index is (index + 1)
                .mapToObj(index -> (index + 1) *  niceScores.get(index))
                .mapToDouble(Double::valueOf)
                .reduce(0, Double::sum);

        // calculate the weighted average
        double averageScore = (double) weightedSum / denominator;

        // apply the nice score bonus
        averageScore = applyNiceScoreBonus(averageScore, child.getNiceScoreBonus());

        // truncate average score to 10
        return (averageScore > Constants.AVERAGE_SCORE_MAX_VALUE)
                ? Constants.AVERAGE_SCORE_MAX_VALUE : averageScore;
    }
}
