package database;

import distribution.recipient.Child;
import distribution.shipment.Gift;
import enums.GiftStrategyType;
import fileio.output.OutputData;
import status.change.AnnualChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class used to store the program's data
 */
public final class Database {
    private static Database databaseInstance = null;

    /**
     * Lists with all data required
     */
    private Integer numberOfYears = null;
    private Double santaBudget = null;
    private final ArrayList<Child> children = new ArrayList<Child>();
    private final ArrayList<Gift> gifts = new ArrayList<Gift>();
    private final ArrayList<AnnualChange> annualChanges  = new ArrayList<AnnualChange>();
    /**
     * Map which maps a child to its assigned budget
     */
    private Map<Child, Double> childBudgets = new HashMap<Child, Double>();

    /**
     * the output data
     */
    private OutputData outputData = new OutputData();

    /**
     * the gift assignment strategy type
     */
    private GiftStrategyType giftStrategy = GiftStrategyType.ID;

    private Database() {
    }

    /**
     * Gets the database instance. If it doesn't exist, it creates and returns it.
     * @return the database instance
     */
    public static Database getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    /**
     * Adds to the database the children from the list given as parameter
     * @param newChildren list with the new children to be added
     */
    public void addChildren(final ArrayList<Child> newChildren) {
        this.children.addAll(newChildren);
    }

    /**
     * Adds to the database the gifts from the list given as parameter
     * @param newGifts list with the new gifts to be added
     */
    public void addGifts(final ArrayList<Gift> newGifts) {
        this.gifts.addAll(newGifts);
    }

    /**
     * Adds to the database the annual changes from the list given as parameter
     * @param newAnnualChanges list with the new annual changes to be added
     */
    public void addAnnualChanges(final ArrayList<AnnualChange> newAnnualChanges) {
        this.annualChanges.addAll(newAnnualChanges);
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public Map<Child, Double> getChildBudgets() {
        return childBudgets;
    }

    public OutputData getOutputData() {
        return outputData;
    }

    public GiftStrategyType getGiftStrategy() {
        return giftStrategy;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setChildBudgets(final Map<Child, Double> childBudgets) {
        this.childBudgets = childBudgets;
    }

    public void setOutputData(final OutputData outputData) {
        this.outputData = outputData;
    }

    public void setGiftStrategy(final GiftStrategyType giftStrategy) {
        this.giftStrategy = giftStrategy;
    }
}
