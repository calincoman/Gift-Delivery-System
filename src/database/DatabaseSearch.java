package database;

import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;
import fileio.output.ChildOutputData;

import java.util.Comparator;

/**
 * Class which contains methods for searching in the Database
 */
public final class DatabaseSearch {

    private DatabaseSearch() {
    }

    /**
     * Search a child by ID in the database
     * @param id the id of the child
     * @return a Child object if the id was found, null otherwise
     */
    public static Child getChildById(final Integer id) {
        return Database.getInstance().getChildren().stream()
                .filter(child -> child.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    /**
     * Search an output child from a specific year by ID in the database
     * @param year the year which the output child belongs to
     * @param id the id of the child
     * @return a ChildOutputData object if the id was found, null otherwise
     */
    public static ChildOutputData getOutputChildFromYearById(final Integer year,
                                                             final Integer id) {
        return Database.getInstance().getOutputData().getAnnualChildren().get(year).stream()
                .filter(childOutputData -> childOutputData.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    /**
     * Search in the database the cheapest gift from a given category
     * @param category the category of the gift
     * @return a Gift object if the gift was found, null otherwise
     */
    public static Gift getCheapestGiftInCategory(final Category category) {
        return Database.getInstance().getGifts().stream()
                // filter after the criteria
                .filter(gift -> gift.getCategory().equals(category))
                // get the gift with the smallest price
                .min(Comparator.comparingDouble(Gift::getPrice))
                .orElse(null);
    }

    /**
     * Search in the database the cheapest gift from a given category which is in a budget asn
     * has quantity greater than 0
     * @param category the category of the gift
     * @param budget the budget which the gift must hold
     * @return a Gift object if the gift was found, null otherwise
     */
    public static Gift getCheapestGiftInCategoryWithBudget(final Category category,
                                                           final Double budget) {
        return Database.getInstance().getGifts().stream()
                // filter after the criteria
                .filter(gift -> gift.getCategory().equals(category) && gift.getPrice() <= budget)
                // get only the gifts that have quantity > 0
                .filter(gift -> gift.getQuantity() > 0)
                // get the gift with the smallest price
                .min(Comparator.comparingDouble(Gift::getPrice))
                .orElse(null);
    }
}
