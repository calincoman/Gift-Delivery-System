package factory;

import enums.ElvesType;
import visitor.type.BlackElf;
import visitor.Elf;
import visitor.type.PinkElf;
import visitor.type.YellowElf;

public final class ElvesFactory {

    private ElvesFactory() {
    }

    public static Elf getElf(final ElvesType elfType) {
        return switch (elfType) {
            case BLACK -> new BlackElf();
            case PINK -> new PinkElf();
            case YELLOW -> new YellowElf();
            default -> null;
        };
    }
}
