package solve;

import common.Utils;
import database.Database;
import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;
import factory.GiftStrategyFactory;
import factory.ScoreStrategyFactory;
import fileio.output.ChildOutputData;
import strategy.delivery.GiftAssigningStrategy;

import java.util.ArrayList;

/**
 * Class used to assign gifts to a child
 */
public final class OutputDataBuilder {

    private OutputDataBuilder() {
    }

    public static void buildOutput() {
        int currentYear = YearCounter.getInstance().getCurrentYear();

        Database.getInstance().getChildren().forEach(child -> {
            Database.getInstance().getOutputData().getAnnualChildren().get(currentYear)
                    // add a new child output data object
                    .add(new ChildOutputData.Builder(child)
                            .withAverageScore(ScoreStrategyFactory.getScoreStrategy(
                                    Utils.getScoreStrategyType(child)).getAverageScore(child))
                            .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
                            .withAssignedBudget(Database.getInstance().getChildBudgets().get(child))
                            .build());
        });
    }

    public static void assignGifts() {

        GiftAssigningStrategy giftAssigningStrategy = GiftStrategyFactory.getGiftStrategy(
                Database.getInstance().getGiftStrategy());

        giftAssigningStrategy.assignGifts();
    }
}
