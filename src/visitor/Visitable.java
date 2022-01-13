package visitor;

public interface Visitable {

    /**
     * Method used in the visitor design pattern, accepts a visitor
     * @param v the visitor
     */
    void accept(Visitor v);
}
