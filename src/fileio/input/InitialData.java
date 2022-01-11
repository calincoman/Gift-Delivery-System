package fileio.input;

import java.util.List;

/**
 * Class which holds the initial data from the input
 * Used for reading with Jackson, is nested in InputFormat
 */
public final class InitialData {
    private List<ChildInputData> children;
    private List<GiftInputData> santaGiftsList;

    public List<ChildInputData> getChildren() {
        return children;
    }

    public List<GiftInputData> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setChildren(final List<ChildInputData> children) {
        this.children = children;
    }

    public void setSantaGiftsList(final List<GiftInputData> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
