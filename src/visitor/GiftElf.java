package visitor;

import distribution.recipient.Child;

/**
 * Class defining a gift elf, implements Elf and Visitor interfaces
 * Is a Visitor (visits a Child object)
 */
public abstract class GiftElf implements Visitor, Elf {

    /**
     * Offers a gift to the child given as parameter
     * Is overridden in the gift elf subclasses which extend this class (YellowElf)
     * @param child child whose budget will be modified
     */
    public void offerGift(final Child child) {
    }

    @Override
    public final void visit(final Child child) {
        offerGift(child);
    }
}
