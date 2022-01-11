package database;

import distribution.recipient.Child;
import distribution.shipment.Gift;

import fileio.input.InputData;
import status.change.AnnualChange;

import java.util.ArrayList;

/**
 * Class used to load and unload data from the database
 */
public final class DatabaseLoader {

    private DatabaseLoader() {
    }

    /**
     * Loads the input data given as parameter to the database
     * @param inputData the input data to be loaded to the database
     */
    public static void loadDatabase(final InputData inputData) {
        Database.getInstance().setNumberOfYears(inputData.getNumberOfYears());
        Database.getInstance().setSantaBudget(inputData.getSantaBudget());
        DatabaseLoader.loadChildren(inputData);
        DatabaseLoader.loadGifts(inputData);
        DatabaseLoader.loadAnnualChanges(inputData);
    }

    /**
     * Unloads the data from the database
     */
    public static void unLoadDatabase() {
        Database.getInstance().setNumberOfYears(null);
        Database.getInstance().setSantaBudget(null);
        Database.getInstance().getChildren().clear();
        Database.getInstance().getGifts().clear();
        Database.getInstance().getAnnualChanges().clear();
        Database.getInstance().getChildBudgets().clear();
    }

    /**
     * Loads the children list from the input data to the database
     * @param inputData the input data
     */
    public static void loadChildren(final InputData inputData) {
        ArrayList<Child> children = new ArrayList<Child>();

        // make a deep copy
        inputData.getChildren().forEach(childInputData ->
                children.add(new Child(childInputData)));

        Database.getInstance().addChildren(children);
    }

    /**
     * Loads the gifts list from the input data to the database
     * @param inputData the input data
     */
    public static void loadGifts(final InputData inputData) {
        ArrayList<Gift> gifts = new ArrayList<Gift>();

        // make a deep copy
        inputData.getGifts().forEach(giftInputData -> gifts.add(new Gift(giftInputData)));

        Database.getInstance().addGifts(gifts);
    }

    /**
     * Loads the annual changes list from the input data to the database
     * @param inputData the input data
     */
    public static void loadAnnualChanges(final InputData inputData) {
        ArrayList<AnnualChange> annualChanges = new ArrayList<AnnualChange>();

        // make a deep copy
        inputData.getAnnualChanges().forEach(annualChangeInputData ->
                annualChanges.add(new AnnualChange(annualChangeInputData)));

        Database.getInstance().addAnnualChanges(annualChanges);
    }
}
