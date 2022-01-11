package strategy.score;

import common.Constants;
import distribution.recipient.Child;

/**
 * Class for the score calculation strategy of a baby
 */
public class BabyScoreStrategy implements ScoreStrategy {
    /**
     * Calculates the average score of the child given as parameter
     * using the formula for the baby age category
     * @param child the child whose average score will be calculated
     * @return double representing the average score of the child
     */
    @Override
    public Double getAverageScore(final Child child) {
        return Constants.DEFAULT_BABY_SCORE;
    }
}
