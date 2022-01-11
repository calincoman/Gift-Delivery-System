package strategy.comparator;

import distribution.recipient.Child;

import java.util.Comparator;

public class IdComparator implements Comparator<Child> {

    @Override
    public int compare(Child child1, Child child2) {
        return child1.getId() - child2.getId();
    }
}
