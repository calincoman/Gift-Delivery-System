package common;

import database.Database;
import distribution.recipient.Child;
import enums.ElvesType;
import enums.GiftStrategyType;
import enums.ScoreStrategyType;

public final class Utils {

    private Utils() {
    }

    /**
     * Determines what score strategy type must be used for the child given as parameter
     * @param child a Child object
     * @return the score strategy type that must be used for the child
     */
    public static ScoreStrategyType getScoreStrategyType(final Child child) {
        return switch (child.getAgeCategory()) {
            case BABY -> ScoreStrategyType.BABY_STRATEGY;
            case KID -> ScoreStrategyType.KID_STRATEGY;
            case TEEN -> ScoreStrategyType.TEEN_STRATEGY;
            case YOUNG_ADULT -> ScoreStrategyType.YOUNG_ADULT_STRATEGY;
            default -> null;
        };
    }

    /**
     * Determines what gift assigning strategy type must be used in the current year
     * @return the gift strategy type that must be used in the current year
     */
    public static GiftStrategyType getGiftStrategyType() {
        return switch (Database.getInstance().getGiftStrategy()) {
            case ID -> GiftStrategyType.ID;
            case NICE_SCORE -> GiftStrategyType.NICE_SCORE;
            case NICE_SCORE_CITY -> GiftStrategyType.NICE_SCORE_CITY;
            default -> null;
        };
    }

    public static boolean isBudgetElf(ElvesType elf) {
        return (elf.equals(ElvesType.BLACK) || elf.equals(ElvesType.PINK));
    }

    public static boolean isGiftElf(ElvesType elf) {
        return (elf.equals(ElvesType.YELLOW));
    }
}
