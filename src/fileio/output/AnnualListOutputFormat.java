package fileio.output;

import java.util.ArrayList;

/**
 * Class used for output format with Jackson
 * Contains the ChildOutputData list for a year
 */
public final class AnnualListOutputFormat {
    private ArrayList<ChildOutputData> children;

    public AnnualListOutputFormat(final ArrayList<ChildOutputData> children) {
        this.children = children;
    }

    public ArrayList<ChildOutputData> getChildren() {
        return children;
    }
}
