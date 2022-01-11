package status.change;

import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.GiftStrategyType;
import fileio.input.AnnualChangeInputData;

import status.update.ChildUpdate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which holds the data contained in an Annual Change
 */
public final class AnnualChange {
    private final Double newSantaBudget;
    private final ArrayList<Gift> newGifts;
    private final ArrayList<Child> newChildren;
    private final ArrayList<ChildUpdate> childrenUpdates;
    private final GiftStrategyType giftStrategy;

    /**
     * Constructor which loads the data for an annual change into the object
     */
    public AnnualChange(final AnnualChangeInputData annualChangeInputData) {
        this.newSantaBudget = annualChangeInputData.getNewSantaBudget();
        // create the new gifts list using the Gift class constructor
        this.newGifts = new ArrayList<Gift>(annualChangeInputData.getNewGifts()
                .stream()
                .map(giftInputData -> new Gift(giftInputData))
                .collect(Collectors.toList()));
        // create the new children list using the Child class constructor
        this.newChildren = new ArrayList<Child>(annualChangeInputData.getNewChildren()
                .stream()
                .map(childInputData -> new Child(childInputData))
                .collect(Collectors.toList()));
        // create the children updates list using the ChildUpdate class constructor
        this.childrenUpdates = new ArrayList<ChildUpdate>(
                annualChangeInputData.getChildrenUpdates()
                        .stream()
                        .map(childUpdateInputData -> new ChildUpdate(childUpdateInputData))
                        .collect(Collectors.toList()));
        // get the new annual gift strategy
        this.giftStrategy = annualChangeInputData.getStrategy();
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public GiftStrategyType getGiftStrategy() {
        return giftStrategy;
    }
}
