package factory;

import enums.ScoreStrategyType;
import strategy.score.BabyScoreStrategy;
import strategy.score.KidScoreStrategy;
import strategy.score.ScoreStrategy;
import strategy.score.TeenScoreStrategy;
import strategy.score.YoungAdultScoreStrategy;

/**
 * Factory Class used for creating score strategy objects
 */
public final class ScoreStrategyFactory {

    private ScoreStrategyFactory() {
    }

    /**
     * Creates and returns a Score Strategy object corresponding to the strategy type given as
     * parameter
     * @param scoreStrategyType an average score calculation strategy type
     * @return score strategy object
     */
    public static ScoreStrategy getScoreStrategy(final ScoreStrategyType scoreStrategyType) {
        return switch (scoreStrategyType) {
            case BABY_STRATEGY -> new BabyScoreStrategy();
            case KID_STRATEGY -> new KidScoreStrategy();
            case TEEN_STRATEGY -> new TeenScoreStrategy();
            case YOUNG_ADULT_STRATEGY -> new YoungAdultScoreStrategy();
            default -> null;
        };
    }
}
