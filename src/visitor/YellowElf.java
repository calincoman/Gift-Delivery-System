package visitor;

import database.Database;
import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import fileio.output.ChildOutputData;

public class YellowElf extends GiftElf {

    @Override
    public void offerGift(Child child, int year) {
        // the assigned gifts are stored in the output data of the database, so the gift
        // will be offered to an output child
        ChildOutputData outputChild = DatabaseSearch.getOutputChildFromYearById(year,
                child.getId());

        // get the cheapest gift from the child's favorite category
        Gift gift = DatabaseSearch.getCheapestGiftInCategory(child.getFavoriteCategory());
        if (gift == null) {
            return;
        }
        if (gift.getQuantity() == 0) {
            return;
        }

        // assign the gift to the child
        outputChild.getReceivedGifts().add(gift);
        gift.decreaseQuantity();
    }

    @Override
    public void visit(Child child) {

    }
}
