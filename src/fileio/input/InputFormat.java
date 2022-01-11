package fileio.input;


import java.util.List;

/**
 * Class used for reading with Jackson, the data is read in this class first,
 * then it is transferred to the InputData class
 * Has the role of an INPUT BUFFER
 */
public final class InputFormat {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChangeInputData> annualChanges;

    public InputFormat() {
        this.numberOfYears = null;
        this.santaBudget = null;
        this.initialData = null;
        this.annualChanges = null;
    }

    public InputFormat(final Integer numberOfYears, final Double santaBudget,
                       final InitialData initialData,
                       final List<AnnualChangeInputData> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public List<AnnualChangeInputData> getAnnualChanges() {
        return annualChanges;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public void setAnnualChanges(final List<AnnualChangeInputData> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
