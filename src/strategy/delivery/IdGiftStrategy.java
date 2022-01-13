package strategy.delivery;

import database.Database;

import distribution.recipient.Child;

import strategy.comparator.IdComparator;

import java.util.ArrayList;

import java.util.stream.Collectors;

/**
 * Strategy class used for child id gift assignment strategy
 */
public final class IdGiftStrategy implements GiftAssigningStrategy {
    @Override
    public ArrayList<Child> applyStrategy() {

        return Database.getInstance().getChildren().stream()
                // sort by ID
                .sorted(new IdComparator())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
