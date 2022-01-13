package strategy.delivery;

import database.Database;
import distribution.recipient.Child;

import strategy.comparator.IdComparator;
import strategy.comparator.MultiComparator;
import strategy.comparator.NiceScoreComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Strategy class used for child score gift assignment strategy
 */
public final class ChildNiceScoreGiftStrategy implements GiftAssigningStrategy {

    @Override
    public ArrayList<Child> applyStrategy() {

        List<Comparator<Child>> comparators = new ArrayList<Comparator<Child>>();
        // sort children first by average score, then by id
        comparators.add(new NiceScoreComparator());
        comparators.add(new IdComparator());

        MultiComparator<Child> multiComparator = new MultiComparator<Child>(comparators);

        return Database.getInstance().getChildren().stream()
                .sorted(multiComparator)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
