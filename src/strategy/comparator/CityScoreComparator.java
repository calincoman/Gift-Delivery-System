package strategy.comparator;

import distribution.recipient.Child;
import solve.Calculator;

import java.util.Comparator;

public class CityScoreComparator implements Comparator<Child> {

    @Override
    public int compare(Child child1, Child child2) {
        double cityScore1 = Calculator.getCityAverageScore(child1.getCity());
        double cityScore2 = Calculator.getCityAverageScore(child2.getCity());

        if (cityScore1 > cityScore2) {
            return -1;
        } else if (cityScore2 > cityScore1) {
            return 1;
        }
        return 0;
    }
}
