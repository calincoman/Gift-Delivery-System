package strategy.delivery;

import database.Database;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import strategy.comparator.CityNameComparator;
import strategy.comparator.CityScoreComparator;
import strategy.comparator.IdComparator;
import strategy.comparator.MultiComparator;
import strategy.comparator.NiceScoreComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CityNiceScoreGiftStrategy implements GiftAssigningStrategy {

    @Override
    public ArrayList<Child> applyStrategy() {

        List<Comparator<Child>> comparators = new ArrayList<Comparator<Child>>();
        // sort children first by city average score, then by city name, then by id
        comparators.add(new CityScoreComparator());
        comparators.add(new CityNameComparator());
        comparators.add(new IdComparator());

        MultiComparator multiComparator = new MultiComparator(comparators);

        return Database.getInstance().getChildren().stream()
                .sorted(multiComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}