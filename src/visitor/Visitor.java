package visitor;

import distribution.recipient.Child;

public interface Visitor {
    /**
     * Method used in the visitor design pattern, visits a Child object
     * @param child child to be visited
     */
    void visit(Child child);
}
