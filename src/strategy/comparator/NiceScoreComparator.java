package strategy.comparator;

import common.Utils;
import distribution.recipient.Child;
import factory.ScoreStrategyFactory;

import java.util.Comparator;

public class NiceScoreComparator implements Comparator<Child> {

    @Override
    public int compare(Child child1, Child child2) {
        // calculate the average scores of the children
        Double averageScore1 = ScoreStrategyFactory.getScoreStrategy(
                Utils.getScoreStrategyType(child1)).getAverageScore(child1);
        Double averageScore2 = ScoreStrategyFactory.getScoreStrategy(
                Utils.getScoreStrategyType(child2)).getAverageScore(child2);

        // children with greater average scores come first
        if (averageScore1 > averageScore2) {
            return -1;
        } else if (averageScore1 < averageScore2) {
            return 1;
        }
        return 0;
    }
}
