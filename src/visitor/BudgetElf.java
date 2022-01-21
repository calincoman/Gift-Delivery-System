package visitor;

import distribution.recipient.Child;

/**
 * Class defining a budget elf, implements Elf and Visitor interfaces
 * Is a Visitor (visits a Child object)
 */
public abstract class BudgetElf implements Visitor, Elf {

    /**
     * Modifies the budget of the child given as parameter
     * Is overridden in the budget elf subclasses which extend this class (BlackELf, PinkElf)
     * @param child child whose budget will be modified
     */
    public void modifyBudget(final Child child) {
    }

    @Override
    public final void visit(final Child child) {
        modifyBudget(child);
    }
}
