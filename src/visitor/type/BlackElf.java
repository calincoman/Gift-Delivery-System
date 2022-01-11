package visitor.type;

import common.Constants;
import database.Database;
import distribution.recipient.Child;
import visitor.BudgetElf;

public class BlackElf extends BudgetElf {

    @Override
    public void modifyBudget(Child child) {
        // get the current budget of the child
        Double childBudget = Database.getInstance().getChildBudgets().get(child);

        // calculate the new budget
        Double newChildBudget = childBudget - childBudget * 30 / 100;

        // update the budget
        Database.getInstance().getChildBudgets().put(child, newChildBudget);
    }
}
