package solve;

import common.Constants;
import database.Database;

public final class YearCounter {
    private static YearCounter yearCounterInstance = null;

    private Integer year = Constants.FIRST_YEAR;

    /**
     * Private constructor so that the class cannot be instantiated outside its scope
     */
    private YearCounter() {
    }

    public static YearCounter getInstance() {
        if (yearCounterInstance == null) {
            yearCounterInstance = new YearCounter();
        }
        return yearCounterInstance;
    }

    public void increaseYear() {
        ++year;
    }

    public Integer getCurrentYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
