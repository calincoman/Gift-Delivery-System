package visitor;

import distribution.recipient.Child;

public interface Visitor {
    public void visit(Child child);
}
