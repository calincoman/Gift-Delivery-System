package strategy.comparator;

import distribution.recipient.Child;

import java.util.Comparator;

/**
 * Comparator class which compares two children by the name of the city they belong to
 * (lexicographically)
 */
public final class CityNameComparator implements Comparator<Child> {

    @Override
    public int compare(final Child child1, final Child child2) {

        return child1.getCity().compareTo(child2.getCity());
    }
}
