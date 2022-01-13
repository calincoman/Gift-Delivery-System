package fileio.input;

import enums.Category;
import enums.ElvesType;

import java.util.List;

/**
 * Class which holds the input data for a Child
 * The input data is first loaded in this class when reading
 */
public final class ChildInputData {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;
    private Double niceScore;
    private List<Category> giftsPreferences;

    private Double niceScoreBonus;
    private ElvesType elf;

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }
}
