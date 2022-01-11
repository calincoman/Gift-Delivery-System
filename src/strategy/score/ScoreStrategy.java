package strategy.score;

import distribution.recipient.Child;

/**
 * Interface used in the strategy design pattern
 */
public interface ScoreStrategy {
    /**
     * Calculates the average score of a child
     * Will be overridden in the classes which implement the interface
     */
    Double getAverageScore(Child child);

    default Double applyNiceScoreBonus(Double averageScore, Double niceScoreBonus) {
        averageScore += averageScore * niceScoreBonus / 100;

        return averageScore;
    }
}
