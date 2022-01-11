package strategy.comparator;

import distribution.recipient.Child;

import java.util.Comparator;

public class CityNameComparator implements Comparator<Child> {

    @Override
    public int compare(Child child1, Child child2) {

        return child1.getCity().compareTo(child2.getCity());
    }
}
