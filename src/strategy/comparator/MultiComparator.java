package strategy.comparator;

import java.util.Comparator;
import java.util.List;

/**
 * Generic Comparator class which contains a list of comparators and compares two objects
 * by the comparators from the list
 * Useful when wanting to sort by multiple criteria
 */
public final class MultiComparator<T> implements Comparator<T> {
    // the list of comparators
    private final List<Comparator<T>> comparators;

    public MultiComparator(final List<Comparator<T>> comparators) {
        this.comparators = comparators;
    }

    @Override
    public int compare(final T o1, final T o2) {
        // iterate through each comparator in the order they were added and compare the objects
        for (Comparator<T> comparator : comparators) {
            int comparisonResult = comparator.compare(o1, o2);
            if (comparisonResult != 0) {
                return comparisonResult;
            }
        }
        return 0;
    }
}
