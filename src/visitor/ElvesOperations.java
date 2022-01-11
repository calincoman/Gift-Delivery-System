package visitor;

import common.Utils;
import database.Database;
import factory.ElvesFactory;

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
        Database.getInstance().getChildren().stream()
                // get only the children which have a gift elf
                .filter(child -> Utils.isGiftElf(child.getElf()))
                .forEach(child -> child.accept(ElvesFactory.getElf(child.getElf())));
    }
}
