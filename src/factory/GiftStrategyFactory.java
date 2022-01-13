package factory;

import enums.GiftStrategyType;

import strategy.delivery.ChildNiceScoreGiftStrategy;
import strategy.delivery.CityNiceScoreGiftStrategy;
import strategy.delivery.GiftAssigningStrategy;
import strategy.delivery.IdGiftStrategy;

/**
 * Factory Class used for creating gift assigning strategy objects
 */
public final class GiftStrategyFactory {

    private GiftStrategyFactory() {
    }

    /**
     * Creates and returns a Gift Strategy object corresponding to the strategy type given as
     * parameter
     * @param giftStrategyType a gift assignment strategy type
     * @return gift strategy object
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
