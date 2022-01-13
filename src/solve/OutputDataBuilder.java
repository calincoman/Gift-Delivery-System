package solve;

import database.Database;

import factory.GiftStrategyFactory;

import fileio.output.ChildOutputData;
import strategy.delivery.GiftAssigningStrategy;

import java.util.ArrayList;

/**
 * Class used to create the output data
 */
public final class OutputDataBuilder {

    private OutputDataBuilder() {
    }

    /**
     * Creates the output children with all the required data, but without assigning
     * gifts to them
     */
    public static void buildOutput() {
        // get the current year
        int currentYear = YearCounter.getInstance().getCurrentYear();

        Database.getInstance().getChildren().forEach(child -> {
            Database.getInstance().getOutputData().getAnnualChildren().get(currentYear)
                    // add a new output child object with all results except the received gifts
                    .add(new ChildOutputData.Builder(child)
                            .withAverageScore(Calculator.getChildAverageScore(child))
                            .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
                            .withAssignedBudget(Database.getInstance().getChildBudgets()
                                    .get(child))
                            .build());
        });
    }

    /**
     * Assigns gifts to the output children
     */
    public static void assignGifts() {

        // use the gift strategy from the current year
        GiftAssigningStrategy giftAssigningStrategy = GiftStrategyFactory.getGiftStrategy(
                Database.getInstance().getGiftStrategy());

        giftAssigningStrategy.assignGifts();
    }
}
