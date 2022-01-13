package strategy.comparator;

import distribution.recipient.Child;
import solve.Calculator;

import java.util.Comparator;

/**
 * Comparator class which compares two children by the score of the city they belong to
 */
public final class CityScoreComparator implements Comparator<Child> {

    @Override
    public int compare(final Child child1, final Child child2) {
        // calculate the scores of the cities
        double cityScore1 = Calculator.getCityAverageScore(child1.getCity());
        double cityScore2 = Calculator.getCityAverageScore(child2.getCity());

        // cities with greater scores come first
        if (cityScore1 > cityScore2) {
            return -1;
        } else if (cityScore2 > cityScore1) {
            return 1;
        }
        return 0;
    }
}
