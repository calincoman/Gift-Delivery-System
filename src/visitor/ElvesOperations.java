package visitor;

import common.Utils;
import database.Database;
import database.DatabaseSearch;
import factory.ElvesFactory;
import solve.YearCounter;

/**
 * Class containing the elves actions
 */
public final class ElvesOperations {

    /**
     * Private constructor so that the class cannot be instantiated outside its scope
     */
    private ElvesOperations() {
    }

    /**
     * Sends the budget elves and applies their effects
     */
    public static void sendBudgetElves() {
        Database.getInstance().getChildren().stream()
                // get only children which have a budget elf
                .filter(child -> Utils.isBudgetElf(child.getElf()))
                // visit the filtered children
                .forEach(child -> child.accept(ElvesFactory.getElf(child.getElf())));
    }

    /**
     * Sends the gift elves and applies their effects
     */
    public static void sendGiftElves() {
        int currentYear = YearCounter.getInstance().getCurrentYear();

        Database.getInstance().getChildren().stream()
                // get only the children which have a gift elf and have not received any presents
                .filter(child -> Utils.isGiftElf(child.getElf()))
                .filter(child -> DatabaseSearch.getOutputChildFromYearById(currentYear,
                        child.getId()).getReceivedGifts().isEmpty())
                // visit the filtered children
                .forEach(child -> child.accept(ElvesFactory.getElf(child.getElf())));
    }
}
