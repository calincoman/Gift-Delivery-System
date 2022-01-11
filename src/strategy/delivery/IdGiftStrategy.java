package strategy.delivery;

import common.Utils;
import database.Database;
import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;
import factory.ScoreStrategyFactory;
import fileio.output.ChildOutputData;
import strategy.comparator.IdComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class IdGiftStrategy implements GiftAssigningStrategy {
    @Override
    public ArrayList<Child> applyStrategy() {

        return Database.getInstance().getChildren().stream()
                // sort by ID
                .sorted(new IdComparator())
                .collect(Collectors.toCollection(ArrayList::new));
    }
//
//    @Override
//    public void assignGifts(int year) {
//        ArrayList<Child> sortedChildren = applyStrategy();
//
//        Database.getInstance().getChildren().stream()
//                .sorted(Comparator.comparing(Child::getId))
//                .forEach(child -> {
//                    Database.getInstance().getOutputData().getAnnualChildren().get(year)
//                            .add(new ChildOutputData.Builder(child)
//                                    .withAverageScore(ScoreStrategyFactory.getScoreStrategy(
//                                            Utils.getScoreStrategyType(child)).getAverageScore(child))
//                                    .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
//                                    .withAssignedBudget(Database.getInstance().getChildBudgets().get(child))
//                                    .);
//                });
//
//        Database.getInstance().getChildren().forEach(child -> {
//            // access the ArrayList from the year index
//            Database.getInstance().getOutputData().getAnnualChildren().get(year)
//                    // add a new child output data created using builder pattern
//                    .add(new ChildOutputData.Builder(child)
//                            // add the average score calculated with the strategy corresponding to
//                            // the child's age category
//                            .withAverageScore(ScoreStrategyFactory.getScoreStrategy(
//                                    Utils.getScoreStrategyType(child)).getAverageScore(child))
//                            // add the nice scores of the child
//                            .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
//                            // add the assigned budget
//                            .withAssignedBudget(Database.getInstance().getChildBudgets()
//                                    .get(child))
//                            // assign the gifts to the child
//                            .withReceivedGifts(new ArrayList<Gift>(GiftAssigner
//                                    .assignGiftsToChild(child)))
//                            .build());
//        });
//    }
//
//    /**
//     * Builds and returns a list containing the gifts assigned to a child given as parameter
//     * @param child the child to assign gifts to
//     * @return list containing the gifts assigned to the child given as paramater
//     */
//    @Override
//    public ArrayList<Gift> assignGiftsToChild(Child child) {
//        ArrayList<Gift> receivedGifts = new ArrayList<Gift>();
//        // get the child's assigned budget
//        Double childBudget = Database.getInstance().getChildBudgets().get(child);
//
//        // iterate through the gift preferences of the child
//        for (Category giftCategory : child.getGiftsPreferences()) {
//            // get the cheapest gift in a category
//            Gift gift = DatabaseSearch.getCheapestGiftInCategoryWithBudget(giftCategory,
//                    childBudget);
//            // if no gift of this category was found, go to the next gift preference of the child
//            if (gift == null) {
//                continue;
//            }
//            // check if there are remaining gifts of this type to be assigned
//            if (gift.getQuantity() == 0) {
//                continue;
//            }
//            // add the gift to the list of received gifts
//            receivedGifts.add(gift);
//            // update the child's budget
//            childBudget -= gift.getPrice();
//        }
//
//        return receivedGifts;
//    }
}
