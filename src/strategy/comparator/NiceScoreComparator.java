package strategy.comparator;

import distribution.recipient.Child;

import solve.Calculator;

import java.util.Comparator;

/**
 * Comparator class which compares two children by their average score
 */
public final class NiceScoreComparator implements Comparator<Child> {

    @Override
    public int compare(final Child child1, final Child child2) {
        // calculate the average scores of the children
        double averageScore1 = Calculator.getChildAverageScore(child1);
        double averageScore2 = Calculator.getChildAverageScore(child2);

        // children with greater average scores come first
        if (averageScore1 > averageScore2) {
            return -1;
        } else if (averageScore1 < averageScore2) {
            return 1;
        }
        return 0;
    }
}
