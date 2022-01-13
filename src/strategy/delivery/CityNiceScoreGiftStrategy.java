package strategy.delivery;

import database.Database;
import distribution.recipient.Child;

import strategy.comparator.CityNameComparator;
import strategy.comparator.CityScoreComparator;
import strategy.comparator.IdComparator;
import strategy.comparator.MultiComparator;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Strategy class used for city score gift assignment strategy
 */
public final class CityNiceScoreGiftStrategy implements GiftAssigningStrategy {

    @Override
    public ArrayList<Child> applyStrategy() {

        List<Comparator<Child>> comparators = new ArrayList<Comparator<Child>>();
        // sort children first by city average score, then by city name, then by id
        comparators.add(new CityScoreComparator());
        comparators.add(new CityNameComparator());
        comparators.add(new IdComparator());

        MultiComparator<Child> multiComparator = new MultiComparator<Child>(comparators);

        // return children list sorted by the criteria mentioned above
        return Database.getInstance().getChildren().stream()
                .sorted(multiComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
