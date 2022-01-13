package solve;

import common.Constants;

/**
 * Singleton class used for keeping the evidence of the current year
 */
public final class YearCounter {
    private static YearCounter yearCounterInstance = null;

    private Integer year = Constants.FIRST_YEAR;

    /**
     * Private constructor so that the class cannot be instantiated outside its scope
     */
    private YearCounter() {
    }

    /**
     * Method which creates the only instance of the class if it didn't exist and returns it
     * @return the only instance of the YearCounter class
     */
    public static YearCounter getInstance() {
        if (yearCounterInstance == null) {
            yearCounterInstance = new YearCounter();
        }
        return yearCounterInstance;
    }

    /**
     * Increases year by 1
     */
    public void increaseYear() {
        ++year;
    }

    /**
     * Returns the current year
     * @return the current year
     */
    public Integer getCurrentYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }
}
