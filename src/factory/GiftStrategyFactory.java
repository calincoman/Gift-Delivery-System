package factory;

import enums.GiftStrategyType;
import enums.ScoreStrategyType;
import strategy.delivery.ChildNiceScoreGiftStrategy;
import strategy.delivery.CityNiceScoreGiftStrategy;
import strategy.delivery.GiftAssigningStrategy;
import strategy.delivery.IdGiftStrategy;
import strategy.score.BabyScoreStrategy;
import strategy.score.KidScoreStrategy;
import strategy.score.ScoreStrategy;
import strategy.score.TeenScoreStrategy;
import strategy.score.YoungAdultScoreStrategy;

public final class GiftStrategyFactory {

    private GiftStrategyFactory() {
    }

    /**
     * Creates and returns a Gift Strategy object corresponding to the strategy type given as
     * parameter
     * @param giftStrategyType a gift assignment strategy type
     * @return strategy object
     */
    public static GiftAssigningStrategy getGiftStrategy(final GiftStrategyType giftStrategyType) {
        return switch (giftStrategyType) {
            case ID -> new IdGiftStrategy();
            case NICE_SCORE -> new ChildNiceScoreGiftStrategy();
            case NICE_SCORE_CITY -> new CityNiceScoreGiftStrategy();
            default -> null;
        };
    }
}
