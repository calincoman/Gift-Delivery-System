package fileio.input;

import enums.Category;
import enums.ElvesType;

import java.lang.annotation.ElementType;
import java.util.List;

/**
 * Class which holds the input data for a Child Update
 * The input data is first loaded in this class when reading
 */
public final class ChildUpdateInputData {
    private Integer id;
    private Double niceScore;
    private List<Category> giftsPreferences;
    private ElvesType elf;

    public Integer getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public void setElf(ElvesType elf) {
        this.elf = elf;
    }
}
