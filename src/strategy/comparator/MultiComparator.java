package strategy.comparator;

import distribution.recipient.Child;

import java.util.Comparator;
import java.util.List;

public class MultiComparator implements Comparator<Child> {
    private List<Comparator<Child>> comparators;

    public MultiComparator(List<Comparator<Child>> comparators) {
        this.comparators = comparators;
    }

    public int compare(Child child1, Child child2) {
        for (Comparator<Child> comparator : comparators) {
            int comparison = comparator.compare(child1, child2);
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }
}
