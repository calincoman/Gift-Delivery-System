package strategy.score;

import distribution.recipient.Child;

import java.util.ArrayList;

/**
 * Class for the score calculation strategy of a kid
 */
public class KidScoreStrategy implements ScoreStrategy {
    /**
     * Calculates the average score of the child given as parameter
     * using the formula for the kid age category
     * @param child the child whose average score will be calculated
     * @return double representing the average score of the child
     */
    @Override
    public Double getAverageScore(final Child child) {
        ArrayList<Double> niceScores = child.getNiceScores();
        double scoreNumber = (double) niceScores.size();

        // calculate sum of scores
        double sum = niceScores
                .stream()
                .mapToDouble(Double::doubleValue)
                .reduce(0, Double::sum);

        // calculate the arithmetic average
        double averageScore = (double) sum / scoreNumber;

        // apply the nice score bonus
        averageScore = applyNiceScoreBonus(averageScore, child.getNiceScoreBonus());

        // truncate average score to 10
        return (averageScore > 10) ? 10.0 : averageScore;
    }
}
