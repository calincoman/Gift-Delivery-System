package factory;

import enums.ElvesType;
import visitor.type.BlackElf;
import visitor.Elf;
import visitor.type.PinkElf;
import visitor.type.YellowElf;

/**
 * Factory Class used for creating Elf objects
 */
public final class ElvesFactory {

    private ElvesFactory() {
    }

    /**
     * Creates and returns an Elf object corresponding to the elf type given as parameter
     * @param elfType the type of elf to be created
     * @return Elf object
     */
    public static Elf getElf(final ElvesType elfType) {
        return switch (elfType) {
            case BLACK -> new BlackElf();
            case PINK -> new PinkElf();
            case YELLOW -> new YellowElf();
            default -> null;
        };
    }
}
