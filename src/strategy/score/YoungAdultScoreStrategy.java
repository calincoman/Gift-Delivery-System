package strategy.score;

import distribution.recipient.Child;

/**
 * Class for the score calculation strategy of a young adult
 */
public class YoungAdultScoreStrategy implements ScoreStrategy {
    /**
     * Calculates the average score of the child given as parameter
     * using the formula for the young adult age category
     * @param child the child whose average score will be calculated
     * @return double representing the average score of the child
     */
    @Override
    public Double getAverageScore(final Child child) {
        // the young adults do not receive gifts, so return null
        return null;
    }
}
