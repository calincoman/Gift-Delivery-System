package visitor.type;

import common.Constants;
import database.Database;
import distribution.recipient.Child;
import visitor.BudgetElf;

/**
 * Class defining a black elf
 */
public final class PinkElf extends BudgetElf {

    @Override
    public void modifyBudget(final Child child) {
        // get the current budget of the child
        Double childBudget = Database.getInstance().getChildBudgets().get(child);

        // calculate the new budget
        Double newChildBudget = childBudget + childBudget * Constants.BUDGET_ELF_PERCENTAGE
                / Constants.HUNDRED;

        // update the budget
        Database.getInstance().getChildBudgets().put(child, newChildBudget);
    }

}
