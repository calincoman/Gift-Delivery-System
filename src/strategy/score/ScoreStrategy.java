package strategy.score;

import common.Constants;
import distribution.recipient.Child;

/**
 * Interface used in the score strategy design pattern
 */
public interface ScoreStrategy {
    /**
     * Calculates the average score of a child
     * Will be overridden in the classes which implement the interface
     */
    Double getAverageScore(Child child);

    /**
     * Applies nice score bonus to an average score
     * @param averageScore average score to be modified
     * @param niceScoreBonus the nice score bonus
     * @return the nice score with the applied bonus
     */
    default Double applyNiceScoreBonus(Double averageScore, Double niceScoreBonus) {
        averageScore += averageScore * niceScoreBonus / Constants.HUNDRED;

        return averageScore;
    }
}
