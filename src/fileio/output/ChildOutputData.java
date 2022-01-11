package fileio.output;

import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.Category;

import java.util.ArrayList;

/**
 * Class containing the output data of a child
 * Uses Builder pattern
 */
public final class ChildOutputData {
    private Integer id;
    private String lastName;
    private String firstName;
    private String city;
    private Integer age;
    private ArrayList<Category> giftsPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;

    /**
     * Constructor which creates the object from its builder
     */
    private ChildOutputData(final Builder childOutputDataBuilder) {
        this.id = childOutputDataBuilder.id;
        this.lastName = childOutputDataBuilder.lastName;
        this.firstName = childOutputDataBuilder.firstName;
        this.city = childOutputDataBuilder.city;
        this.age = childOutputDataBuilder.age;
        this.giftsPreferences = childOutputDataBuilder.giftsPreferences;
        this.averageScore = childOutputDataBuilder.averageScore;
        this.niceScoreHistory = childOutputDataBuilder.niceScoreHistory;
        this.assignedBudget = childOutputDataBuilder.assignedBudget;
        this.receivedGifts = childOutputDataBuilder.receivedGifts;
    }

    /**
     * Inner Builder class
     */
    public static final class Builder {
        private Integer id;
        private String lastName;
        private String firstName;
        private String city;
        private Integer age;
        private ArrayList<Category> giftsPreferences = new ArrayList<Category>();
        private Double averageScore = null;
        private ArrayList<Double> niceScoreHistory = new ArrayList<Double>();
        private Double assignedBudget = null;
        private ArrayList<Gift> receivedGifts = new ArrayList<Gift>();

        /**
         * Constructor which sets the base fields
         */
        public Builder(final Child child) {
            this.id = child.getId();
            this.lastName = child.getLastName();
            this.firstName = child.getFirstName();
            this.city = child.getCity();
            this.age = child.getAge();
            this.giftsPreferences = new ArrayList<Category>(child.getGiftsPreferences());
        }

        /**
         * Sets the average score and returns the current Builder object
         * @param pAverageScore the average score of a child
         * @return current Builder object with average score set
         */
        public Builder withAverageScore(final Double pAverageScore) {
            this.averageScore = pAverageScore;
            return this;
        }

        /**
         * Sets the nice score history and returns the current Builder object
         * @param pNiceScoreHistory list of nice scores
         * @return current Builder object with nice score history set
         */
        public Builder withNiceScoreHistory(final ArrayList<Double> pNiceScoreHistory) {
            this.niceScoreHistory = pNiceScoreHistory;
            return this;
        }

        /**
         * Sets the assigned budget and returns the current Builder object
         * @param pAssignedBudget the assigned budget of the child
         * @return current Builder object with assigned budget set
         */
        public Builder withAssignedBudget(final Double pAssignedBudget) {
            this.assignedBudget = pAssignedBudget;
            return this;
        }

        /**
         * Sets the received gifts list and returns the current Builder object
         * @param pReceivedGifts the list containing the received gifts
         * @return current Builder object with received gifts list set
         */
        public Builder withReceivedGifts(final ArrayList<Gift> pReceivedGifts) {
            this.receivedGifts = pReceivedGifts;
            return this;
        }

        /**
         * Creates a new ChildOutputData object with the data from the current Builder
         */
        public ChildOutputData build() {
            return new ChildOutputData(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }
}
