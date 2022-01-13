package strategy.comparator;

import distribution.recipient.Child;

import java.util.Comparator;

/**
 * Comparator class which compares two children by their id
 */
public final class IdComparator implements Comparator<Child> {

    @Override
    public int compare(final Child child1, final Child child2) {
        return child1.getId() - child2.getId();
    }
}
