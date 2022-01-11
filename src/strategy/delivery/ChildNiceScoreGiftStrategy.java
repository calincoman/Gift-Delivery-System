package strategy.delivery;

import database.Database;
import distribution.recipient.Child;
import distribution.shipment.Gift;
import strategy.comparator.IdComparator;
import strategy.comparator.MultiComparator;
import strategy.comparator.NiceScoreComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChildNiceScoreGiftStrategy implements GiftAssigningStrategy {

    @Override
    public ArrayList<Child> applyStrategy() {

        List<Comparator<Child>> comparators = new ArrayList<Comparator<Child>>();
        // sort children first by average score, then by id
        comparators.add(new NiceScoreComparator());
        comparators.add(new IdComparator());

        MultiComparator multiComparator = new MultiComparator(comparators);

        return Database.getInstance().getChildren().stream()
                .sorted(multiComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
