package visitor;

import common.Utils;
import database.Database;
import database.DatabaseSearch;
import factory.ElvesFactory;
import solve.YearCounter;

public final class ElvesOperations {

    private ElvesOperations() {
    }

    public static void sendBudgetElves() {
        Database.getInstance().getChildren().stream()
                // get only children which have a budget elf
                .filter(child -> Utils.isBudgetElf(child.getElf()))
                .forEach(child -> child.accept(ElvesFactory.getElf(child.getElf())));
    }

    public static void sendGiftElves() {
        int currentYear = YearCounter.getInstance().getCurrentYear();

        Database.getInstance().getChildren().stream()
                // get only the children which have a gift elf and have not received any presents
                .filter(child -> Utils.isGiftElf(child.getElf()))
                .filter(child -> DatabaseSearch.getOutputChildFromYearById(currentYear,
                        child.getId()).getReceivedGifts().isEmpty())
                .forEach(child -> child.accept(ElvesFactory.getElf(child.getElf())));
    }
}
