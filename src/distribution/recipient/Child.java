package distribution.recipient;

import common.Constants;
import enums.AgeCategory;
import enums.Category;
import enums.ElvesType;
import fileio.input.ChildInputData;
import visitor.Visitable;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.stream.Collectors;

/**
 * Class which represents a child
 */
public final class Child extends Person implements Visitable {
    private Integer id;
    private ArrayList<Double> niceScores;
    private ArrayList<Category> giftsPreferences;

    private Double niceScoreBonus;
    private ElvesType elf;

    /**
     * Constructor which creates a Child object from the child input data
     */
    public Child(final ChildInputData childInputData) {
        super(childInputData);
        this.id = childInputData.getId();
        this.niceScores = new ArrayList<Double>(List.of(childInputData.getNiceScore()));
        this.giftsPreferences = new ArrayList<Category>(childInputData.getGiftsPreferences());
        this.niceScoreBonus = childInputData.getNiceScoreBonus();
        this.elf = childInputData.getElf();
    }

    /**
     * Constructor for the Builder pattern
     */
    private Child(Builder builder) {
        super(builder);
        this.id = builder.id;
        this.niceScores = builder.niceScores;
        this.giftsPreferences = builder.giftsPreferences;
        this.niceScoreBonus = builder.niceScoreBonus;
        this.elf = builder.elf;
    }

    /**
     * Returns the age category of the child
     * @return the age category
     */
    public AgeCategory getAgeCategory() {
        Integer age = this.getAge();
        if (age <= Constants.BABY_MAX_AGE) {
            return AgeCategory.BABY;
        } else if (age <= Constants.KID_MAX_AGE) {
            return AgeCategory.KID;
        } else if (age <= Constants.TEEN_MAX_AGE) {
            return AgeCategory.TEEN;
        } else {
            return AgeCategory.YOUNG_ADULT;
        }
    }

    /**
     * Determines if a child is a young adult or not
     * @return true if the child is young adult, false otherwise
     */
    public boolean isYoungAdult() {
        return (this.getAge() > Constants.TEEN_MAX_AGE);
    }

    /**
     * Adds a new nice score to the child
     * @param newNiceScore the new nice score to be added
     */
    public void addNiceScore(final Double newNiceScore) {
        if (newNiceScore != null) {
            this.niceScores.add(newNiceScore);
        }
    }

    /**
     * Adds new gift preferences to the child
     * @param giftPreferences list containing new gift preferences to be added
     */
    public void addGiftPreferences(final ArrayList<Category> giftPreferences) {
        // for each new category to be added, remove all its appearances from the old list
        giftPreferences.forEach(category -> this.giftsPreferences
                .removeIf(category1 -> category1.getValue().equals(category.getValue())));
        // add the new gift categories to the beginning of the gift preferences list, in the order
        // they appear in the list given as parameter
        giftPreferences.stream()
                // eliminate possible duplicates
                .distinct()
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(category -> this.giftsPreferences.add(0, category));
    }

    /**
     * Gets the favorite gift category of the child
     * @return Category enum containing the child's favorite gift category
     */
    public Category getFavoriteCategory() {
        // the child's favorite category is situated at index 0 in the gifts preferences list
        return giftsPreferences.get(0);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public static class Builder {
        // mandatory
        private String lastName;
        private String firstName;
        private Integer age;
        private String city;
        private Integer id;
        private ArrayList<Double> niceScores;
        private ArrayList<Category> giftsPreferences;
        private ElvesType elf;

        // optional
        private Double niceScoreBonus = 0.0;

        public Builder(final ChildInputData childInputData) {
            this.lastName = childInputData.getLastName();
            this.firstName = childInputData.getFirstName();
            this.age = childInputData.getAge();
            this.city = childInputData.getCity();
            this.id = childInputData.getId();
            this.niceScores = new ArrayList<Double>(List.of(childInputData.getNiceScore()));
            this.giftsPreferences = new ArrayList<Category>(childInputData.getGiftsPreferences());
            this.elf = childInputData.getElf();
        }

        public Builder withNiceScoreBonus(Double niceScoreBonus) {
            this.niceScoreBonus = niceScoreBonus;
            return this;
        }

        public Child build() {
            return new Child(this);
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

        public Integer getId() {
            return id;
        }

        public ArrayList<Double> getNiceScores() {
            return niceScores;
        }

        public ArrayList<Category> getGiftsPreferences() {
            return giftsPreferences;
        }

        public ElvesType getElf() {
            return elf;
        }

        public Double getNiceScoreBonus() {
            return niceScoreBonus;
        }
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<Double> getNiceScores() {
        return niceScores;
    }

    public ArrayList<Category> getGiftsPreferences() {
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

    public void setNiceScores(final ArrayList<Double> niceScores) {
        this.niceScores = niceScores;
    }

    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public void setNiceScoreBonus(Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public void setElf(ElvesType elf) {
        this.elf = elf;
    }
}
