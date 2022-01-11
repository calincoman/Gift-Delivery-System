package fileio.input;

import java.util.List;

/**
 * Class which holds all the input data
 */
public final class InputData {
    // the number of years
    private final Integer numberOfYears;
    // initial budget
    private final Double santaBudget;
    // initial list of children
    private final List<ChildInputData> children;
    // initial list of gifts
    private final List<GiftInputData> gifts;
    // list of annual changes
    private final List<AnnualChangeInputData> annualChanges;

    public InputData() {
        this.numberOfYears = null;
        this.santaBudget = null;
        this.children = null;
        this.gifts = null;
        this.annualChanges = null;
    }

    public InputData(final Integer numberOfYears, final Double santaBudget,
                     final List<ChildInputData> children,
                     final List<GiftInputData> gifts,
                     final List<AnnualChangeInputData> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.children = children;
        this.gifts = gifts;
        this.annualChanges = annualChanges;
    }

    /**
     * Constructor which extracts the data from an InputFormat object
     */
    public InputData(final InputFormat inputFormat) {
        this.numberOfYears = inputFormat.getNumberOfYears();
        this.santaBudget = inputFormat.getSantaBudget();
        this.children = inputFormat.getInitialData().getChildren();
        this.gifts = inputFormat.getInitialData().getSantaGiftsList();
        this.annualChanges = inputFormat.getAnnualChanges();
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public List<ChildInputData> getChildren() {
        return children;
    }

    public List<GiftInputData> getGifts() {
        return gifts;
    }

    public List<AnnualChangeInputData> getAnnualChanges() {
        return annualChanges;
    }
}
