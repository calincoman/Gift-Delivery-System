package visitor.type;

import database.DatabaseSearch;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import fileio.output.ChildOutputData;
import solve.YearCounter;
import visitor.GiftElf;

public class YellowElf extends GiftElf {

    @Override
    public void offerGift(Child child) {

        int year = YearCounter.getInstance().getCurrentYear();

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

        // assign the gift to the child and decrease its quantity by 1
        outputChild.getReceivedGifts().add(gift);
        gift.decreaseQuantity();
    }
}
