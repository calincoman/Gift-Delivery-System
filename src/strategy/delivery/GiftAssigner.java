package strategy.delivery;

import database.Database;
import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;

import java.util.ArrayList;

/**
 * Class used to assign gifts to a child
 */
public final class GiftAssigner {

    private GiftAssigner() {
    }

    /**
     * Builds and returns a list containing the gifts assigned to a child given as parameter
     * @param child the child to assign gifts to
     * @return list containing the gifts assigned to the child given as paramater
     */
    public static ArrayList<Gift> assignGiftsToChild(final Child child) {
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
            // add the gift to the list of received gifts
            receivedGifts.add(gift);
            // update the child's budget
            childBudget -= gift.getPrice();
        }

        return receivedGifts;
    }
}
