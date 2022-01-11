package fileio.input;

import enums.GiftStrategyType;

import java.util.List;

/**
 * Class which holds the input data for an Annual Change
 * The input data is first loaded in this class when reading
 */
public final class AnnualChangeInputData {
    private Double newSantaBudget;
    private List<GiftInputData> newGifts;
    private List<ChildInputData> newChildren;
    private List<ChildUpdateInputData> childrenUpdates;
    private GiftStrategyType strategy;

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<GiftInputData> getNewGifts() {
        return newGifts;
    }

    public List<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public List<ChildUpdateInputData> getChildrenUpdates() {
        return childrenUpdates;
    }

    public GiftStrategyType getStrategy() {
        return strategy;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public void setNewGifts(final List<GiftInputData> newGifts) {
        this.newGifts = newGifts;
    }

    public void setNewChildren(final List<ChildInputData> newChildren) {
        this.newChildren = newChildren;
    }

    public void setChildrenUpdates(final List<ChildUpdateInputData> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public void setStrategy(GiftStrategyType strategy) {
        this.strategy = strategy;
    }
}
