package solve;

import common.Utils;
import database.Database;
import distribution.recipient.Child;
import factory.ScoreStrategyFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import java.util.stream.Collectors;

/**
 * Class used to calculate the budgets assigned to each child, the budget unit and
 * the average score sum
 */
public final class Calculator {

    private Calculator() {
    }

    public static double getChildAverageScore(Child child) {
        return ScoreStrategyFactory.getScoreStrategy(
                Utils.getScoreStrategyType(child)).getAverageScore(child);
    }

    /**
     * Calculates the budget assigned to each child
     * @return map which maps a child object to its assigned budget (key is a child object
     *         and value is its budget)
     */
    public static Map<Child, Double> getChildBudgets() {
        // first calculate the budget unit
        double budgetUnit = Calculator.getBudgetUnit();

        return Database.getInstance().getChildren().stream()
                // create the map with a child as key and its budget as value
                .collect(Collectors.toMap(child -> child, child -> budgetUnit
                        // use the corresponding strategy to calculate the average score
                        * ScoreStrategyFactory .getScoreStrategy(Utils.getScoreStrategyType(child))
                        .getAverageScore(child)));
    }

    /**
     * Calculates the budget unit
     * @return double representing the budget unit
     */
    public static double getBudgetUnit() {
        double averageScoreSum = Calculator.getAverageScoreSum();
        // the budget unit is Santa's budget divided by the average score sum
        return (double) Database.getInstance().getSantaBudget() / averageScoreSum;
    }

    /**
     * Calculates the sum of the average scores of each child
     * @return double containing the sum of average scores
     */
    public static double getAverageScoreSum() {
        return Database.getInstance().getChildren().stream()
                .sorted(Comparator.comparing(Child::getId))
                // for each child get its average score
                .map(child -> Calculator.getChildAverageScore(child))
                .mapToDouble(Double::doubleValue)
                // calculate the sum of the transformed stream, now containing the average scores
                .reduce(0, Double::sum);
    }

    /**
     * Calculates the average score of a city as being the arithmetic average of the scores of the
     * children from that city
     * @param city the city whose average score needs to be calculated
     * @return double containing the average score of a city
     */
    public static double getCityAverageScore(String city) {

        // calculate the number of children from the given city
        double childrenCount = (double) Database.getInstance().getChildren().stream()
                .filter(child -> child.getCity().equals(city))
                .count();

        // calculate the sum of the childrens' average scores
        double cityAverageScoreSum = Database.getInstance().getChildren().stream()
                // get only the children from the city given as parameter
                .filter(child -> child.getCity().equals(city))
                .sorted(Comparator.comparing(Child::getId))
                .map(child -> Calculator.getChildAverageScore(child))
                .mapToDouble(Double::doubleValue)
                // calculate the sum of the transformed stream, now containing the average scores
                .reduce(0, Double::sum);

        return cityAverageScoreSum / childrenCount;
    }
}
