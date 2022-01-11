package strategy.delivery;

import common.Utils;
import database.Database;
import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;
import factory.ScoreStrategyFactory;
import fileio.output.ChildOutputData;
import solve.YearCounter;

import java.util.ArrayList;

public interface GiftAssigningStrategy {

    public ArrayList<Child> applyStrategy();

    public default void assignGifts() {

        ArrayList<Child> sortedChildren = applyStrategy();
        int currentYear = YearCounter.getInstance().getCurrentYear();

        // for each child, assign gifts to the corresponding output child
        sortedChildren.forEach(child -> DatabaseSearch.getOutputChildFromYearById(currentYear,
                child.getId()).setReceivedGifts(assignGiftsToChild(child)));

//        sortedChildren.forEach(child -> {
//            Database.getInstance().getOutputData().getAnnualChildren().get(year)
//                    .add(new ChildOutputData.Builder(child)
//                            .withAverageScore(ScoreStrategyFactory.getScoreStrategy(
//                                    Utils.getScoreStrategyType(child)).getAverageScore(child))
//                            .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
//                            .withAssignedBudget(Database.getInstance().getChildBudgets()
//                                    .get(child))
//                            .withReceivedGifts(assignGiftsToChild(child))
//                            .build());
//        });
    }

    public default ArrayList<Gift> assignGiftsToChild(final Child child) {

        ArrayList<Gift> receivedGifts = new ArrayList<Gift>();
        // get the child's assigned budget
        Double childBudget = Database.getInstance().getChildBudgets().get(child);

        // iterate through the gift preferences of the child
        for (Category giftCategory : child.getGiftsPreferences()) {
            // get the cheapest gift in a category that has quantity greater than 0 and is
            // in the child's budget
            Gift gift = DatabaseSearch.getCheapestGiftInCategoryWithBudget(giftCategory,
                    childBudget);
            // if no gift with quantity > 0 of this category was found, go to the next gift
            // preference of the child
            if (gift == null) {
                continue;
            }
            // add the gift to the list of received gifts
            receivedGifts.add(gift);
            // decrease the number of gifts of this type by 1
            gift.decreaseQuantity();
            // update the child's budget
            childBudget -= gift.getPrice();
        }

        return receivedGifts;
    }
}
