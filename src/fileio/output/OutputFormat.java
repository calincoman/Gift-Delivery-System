package fileio.output;

import java.util.ArrayList;

/**
 * Class which contains the exact output format to be used by Jackson serializer
 * Has the role of an OUTPUT BUFFER
 */
public final class OutputFormat {
    private final ArrayList<AnnualListOutputFormat> annualChildren;

    public OutputFormat() {
        annualChildren = new ArrayList<AnnualListOutputFormat>();
    }

    /**
     * Constructor which extracts the data from an OutputData object into the output format
     */
    public OutputFormat(final OutputData outputData) {
        annualChildren = new ArrayList<AnnualListOutputFormat>();

        outputData.getAnnualChildren().forEach(childrenList -> {
            annualChildren.add(new AnnualListOutputFormat(childrenList));
        });
    }

    public ArrayList<AnnualListOutputFormat> getAnnualChildren() {
        return annualChildren;
    }
}
