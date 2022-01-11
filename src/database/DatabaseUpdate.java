package database;

import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.GiftStrategyType;
import solve.YearCounter;
import status.change.AnnualChange;
import status.update.ChildUpdate;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class which contains methods for updating the data from the Database
 */
public class DatabaseUpdate {
    /**
     * Increases age of all children from the database by 1
     */
    public void increaseAge() {
        // increase age by 1 for each child
        Database.getInstance().getChildren().forEach(child -> child.increaseAge());
    }

    /**
     * Eliminates from the database all children that are young adults
     */
    public void eliminateYoungAdults() {
        // eliminate children that are young adults
        Database.getInstance().getChildren().removeIf(child -> child.isYoungAdult());
    }

    public void updateAllData() {
        // get current year
        Integer currentYear = YearCounter.getInstance().getCurrentYear();

        // the annual changes list is indexed from 0, so get the element from year - 1 position
        AnnualChange annualChange = Database.getInstance().getAnnualChanges().get(currentYear - 1);
        // increase age of all children by 1
        this.increaseAge();
        // eliminate children that have become young adults
        this.eliminateYoungAdults();

        // do the updates
        this.updateSantaBudget(annualChange.getNewSantaBudget());
        this.addNewGifts(annualChange.getNewGifts());
        this.addNewChildren(annualChange.getNewChildren());
        this.updateChildren(new ArrayList<ChildUpdate>(annualChange
                .getChildrenUpdates()));
        this.updateGiftStrategy(annualChange.getGiftStrategy());
    }

    /**
     * Updates the Santa Budget
     * @param newBudget the new Santa Budget
     */
    public void updateSantaBudget(final Double newBudget) {
        Database.getInstance().setSantaBudget(newBudget);
    }

    /**
     * Adds new children to the database
     * @param newChildren list containing the new children to be added
     */
    public void addNewChildren(final ArrayList<Child> newChildren) {
        // add all new children which are not young adults
        newChildren.stream()
                .filter(child -> !child.isYoungAdult())
                .forEach(child -> Database.getInstance().getChildren().add(child));
        // keep the children list from the database sorted by id
        Database.getInstance().getChildren().sort(Comparator.comparingInt(Child::getId));
    }

    /**
     * Adds new gifts to the database
     * @param newGifts list containing the new gifts to be added
     */
    public void addNewGifts(final ArrayList<Gift> newGifts) {
        // add all new gifts to Santa's gift list
        newGifts.forEach(gift -> Database.getInstance().getGifts().add(gift));
    }

    /**
     * Updates the children from the database
     * @param childrenUpdates list of ChildUpdate objects containing the children updates
     */
    public void updateChildren(final ArrayList<ChildUpdate> childrenUpdates) {
        childrenUpdates.forEach(childUpdate -> {
            Child child = DatabaseSearch.getChildById(childUpdate.getId());
            // if the child was not found, skip to the next child update
            if (child == null) {
                return;
            }
            // add a new nice score to the child
            child.addNiceScore(childUpdate.getNiceScore());
            // add the new gift preferences to the child
            child.addGiftPreferences(childUpdate.getGiftsPreferences());
            // set the new elf assigned to the child
            child.setElf(childUpdate.getElf());
        });
    }

    public void updateGiftStrategy(final GiftStrategyType newGiftStrategy) {
        Database.getInstance().setGiftStrategy(newGiftStrategy);
    }

    /**
     * Removes a gift from the database
     * @param gift the Gift object to be removed from the database
     */
    public void removeGift(final Gift gift) {
        Database.getInstance().getGifts().remove(gift);
    }
}
