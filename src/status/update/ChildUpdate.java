package status.update;

import enums.Category;

import enums.ElvesType;
import fileio.input.ChildUpdateInputData;

import java.util.ArrayList;

/**
 * Class which holds the data contained in a Child Update
 */
public final class ChildUpdate {
    private Integer id;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private ElvesType elf;

    /**
     * Constructor which loads the data for a child update into the object
     */
    public ChildUpdate(final ChildUpdateInputData childUpdateInputData) {
        this.id = childUpdateInputData.getId();
        this.niceScore = childUpdateInputData.getNiceScore();
        this.giftsPreferences = new ArrayList<Category>(
                childUpdateInputData.getGiftsPreferences());
        this.elf = childUpdateInputData.getElf();
    }

    public Integer getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public ElvesType getElf() {
        return elf;
    }
}
