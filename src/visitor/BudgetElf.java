package visitor;

import distribution.recipient.Child;

public abstract class BudgetElf implements Visitor {

    public void modifyBudget(Child child) {}

    @Override
    public void visit(Child child) {
        modifyBudget(child);
    }
}
