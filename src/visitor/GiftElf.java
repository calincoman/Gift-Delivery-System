package visitor;

import distribution.recipient.Child;
import fileio.output.ChildOutputData;

public abstract class GiftElf implements Visitor, Elf {

    public void offerGift(Child child) {}

    @Override
    public void visit(Child child) {
        offerGift(child);
    }
}
