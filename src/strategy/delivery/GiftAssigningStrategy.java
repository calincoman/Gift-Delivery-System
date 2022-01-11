package strategy.delivery;

import common.Utils;
import database.Database;
import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;
import factory.ScoreStrategyFactory;
import fileio.output.ChildOutputData;

import java.util.ArrayList;

public interface GiftAssigningStrategy {

    public ArrayList<Child> applyStrategy();

    public default void assignGifts(int year) {
        ArrayList<Child> sortedChildren = applyStrategy();

        sortedChildren.forEach(child -> {
            Database.getInstance().getOutputData().getAnnualChildren().get(year)
                    .add(new ChildOutputData.Builder(child)
                            .withAverageScore(ScoreStrategyFactory.getScoreStrategy(
                                    Utils.getScoreStrategyType(child)).getAverageScore(child))
                            .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
                            .withAssignedBudget(Database.getInstance().getChildBudgets().get(child))
                            .withReceivedGifts(assignGiftsToChild(child))
                            .build());
        });
    }

    public default ArrayList<Gift> assignGiftsToChild(final Child child) {
        ArrayList<Gift> receivedGifts = new ArrayList<Gift>();
        // get the child's assigned budget
        Double childBudget = Database.getInstance().getChildBudgets().get(child);

        // iterate through the gift preferences of the child
        for (Category giftCategory : child.getGiftsPreferences()) {
            // get the cheapest gift in a category
            Gift gift = DatabaseSearch.getCheapestGiftInCategoryWithBudget(giftCategory,
                    childBudget);
            // if no gift of this category was found, go to the next gift preference of the child
            if (gift == null) {
                continue;
            }
            // check if there are remaining gifts of this type to be assigned
            if (gift.getQuantity() == 0) {
                continue;
            }
            // add the gift to the list of received gifts
            receivedGifts.add(gift);
            // update the child's budget
            childBudget -= gift.getPrice();
        }

        return receivedGifts;
    }
}
