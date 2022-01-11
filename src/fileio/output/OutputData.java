package fileio.output;

import java.util.ArrayList;

/**
 * Class containing the output data (a list of lists of ChildOutputData)
 */
public final class OutputData {
    private final ArrayList<ArrayList<ChildOutputData>> annualChildren;

    public OutputData() {
        annualChildren = new ArrayList<ArrayList<ChildOutputData>>();
    }

    /**
     * Constructor which initializes the annualChildren field used for output
     */
    public OutputData(final Integer numberOfYears) {
        annualChildren = new ArrayList<ArrayList<ChildOutputData>>();

        for (int counter = 0; counter < numberOfYears; ++counter) {
            annualChildren.add(new ArrayList<ChildOutputData>());
        }
    }

    public ArrayList<ArrayList<ChildOutputData>> getAnnualChildren() {
        return annualChildren;
    }
}
